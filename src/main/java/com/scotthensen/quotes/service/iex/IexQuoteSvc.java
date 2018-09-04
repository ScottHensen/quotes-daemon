package com.scotthensen.quotes.service.iex;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.scotthensen.quotes.model.Quote;
import com.scotthensen.quotes.service.GetQuotes;
import com.scotthensen.quotes.service.GetQuotesRequest;
import com.scotthensen.quotes.service.GetQuotesResponse;
import com.scotthensen.quotes.service.iex.IexQuote;

@Component
class IexQuoteSvc implements GetQuotes 
{
	@Value("${getquotes.iex.url.base}")
	private String IEX_BASE_URL;
	
	@Value("${getquotes.iex.url.request.types}")
	private String IEX_REQUEST_TYPES;
	
	public GetQuotesResponse getQuotes(GetQuotesRequest request) 
	{
		RestTemplate             restTemplate = new RestTemplate();
		GetQuotesResponse        response     = new GetQuotesResponse();
		HttpClientErrorException exception    = null;
		
		if (request == null || request.getSymbols() == null) {
			exception = 
				new HttpClientErrorException(
						HttpStatus.BAD_REQUEST, 
						"GetQuotesRequest must contain a symbol");
		}
		else 
		{
			try 
			{
				ParameterizedTypeReference<HashMap<String,IexQuote>> quotesMap = 
						new ParameterizedTypeReference<HashMap<String,IexQuote>>() {};
					
				ResponseEntity<HashMap<String,IexQuote>> iexResponse = 
					restTemplate.exchange(
						buildStringUrl(request), 
						HttpMethod.GET, 
						null, 
						quotesMap);
				
				if ((iexResponse.getStatusCode() != HttpStatus.OK) || (iexResponse.getBody().isEmpty()))
				{
					exception =
						new HttpClientErrorException(
							HttpStatus.NOT_FOUND,
							"Symbol(s) provided in GetQuotesRequest were not found at IEX");
					
				}
				else 
				{
					List<Quote> quotes = 
						iexResponse.getBody().entrySet()
							.stream()
							.sorted(Map.Entry.<String, IexQuote>comparingByKey())
							.map(q -> new Quote(q.getValue().getQuote().getSymbol(), 
											    q.getValue().getQuote().getIexBidPrice(), 
											    q.getValue().getQuote().getIexAskPrice()  )	)
							.collect(Collectors.toList());
				
					response.setQuotes(quotes);
				}
			}
			catch (HttpClientErrorException e) 
			{
				exception =
					new HttpClientErrorException(
						HttpStatus.NOT_FOUND,
						"Symbol(s) provided in GetQuotesRequest were not found at IEX");
			}
		}
		response.setHttpException(exception);
		return response;	
	}

	private String buildStringUrl(GetQuotesRequest request) 
	{
		String urlSymbols = String.join(",", request.getSymbols());	
		String url        = IEX_BASE_URL + urlSymbols + IEX_REQUEST_TYPES;

		return url;
	}

}
