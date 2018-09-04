package com.scotthensen.quotes.svc;

import org.springframework.stereotype.Service;

@Service
public interface GetQuotes 
{
	public GetQuotesResponse getQuotes(GetQuotesRequest request);
}
