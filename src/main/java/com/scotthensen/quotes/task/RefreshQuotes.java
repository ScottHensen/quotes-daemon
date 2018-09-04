package com.scotthensen.quotes.task;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.scotthensen.quotes.model.Quote;
import com.scotthensen.quotes.persistence.enterprise.SecurityEntity;
import com.scotthensen.quotes.persistence.enterprise.SecurityRepository;
import com.scotthensen.quotes.pubsub.QuotesPublisher;
import com.scotthensen.quotes.repository.QuoteRepository;
import com.scotthensen.quotes.svc.GetQuotes;
import com.scotthensen.quotes.svc.GetQuotesRequest;
import com.scotthensen.quotes.svc.GetQuotesResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class RefreshQuotes
{
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss:SSS Z");
	
	@Autowired
	private SecurityRepository securityRepository;
	
	@Autowired
	private QuoteRepository quoteRepository;
	
	@Autowired
	private GetQuotes quotes;
	
	@Autowired
	private QuotesPublisher quotesPublisher;
	
	@Scheduled(cron = "${task.refreshquotes.cron.test}")
	@Scheduled(cron = "${task.refreshquotes.cron.start}")
	@Scheduled(cron = "${task.refreshquotes.cron.mid}")
	@Scheduled(cron = "${task.refreshquotes.cron.stop}")
	public void refreshStockQuotes() throws JsonProcessingException {
		log.debug("The time is now {}", dateFormat.format(new Date()), " ... start refreshStockQuotes");

		List<SecurityEntity> securities = securityRepository.findAll();

		List<String> symbols = 
				securities
					.stream()
					.map(s -> s.getSymbol())
					.collect(Collectors.toList());
		
		log.debug("symbols:" + symbols.toString());
		
		GetQuotesRequest  quotesRequest  = new GetQuotesRequest(symbols);
		GetQuotesResponse quotesResponse = quotes.getQuotes(quotesRequest);

		//TODO:  if null response, throw exception to force an abend
		//TODO:  if get n response.exceptions in a row, throw exception to force an abend
		if (quotesResponse.getHttpException() != null) {
			log.warn("\n>>> quotesResponse.exception: " + quotesResponse.getHttpException());
		}
		else {
			log.debug("quotes: " + quotesResponse.getQuotes().toString());
			quoteRepository.saveAll(quotesResponse.getQuotes());
			
			List<Quote> cached = (List<Quote>) quoteRepository.findAll();
			log.debug("cached: " + cached.toString());
			
			quotesPublisher.publish(cached);
		}
		log.debug("The time is now {}", dateFormat.format(new Date()), " ... stop  refreshStockQuotes");		
	}

}
