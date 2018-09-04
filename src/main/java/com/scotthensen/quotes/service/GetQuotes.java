package com.scotthensen.quotes.service;

import org.springframework.stereotype.Service;

@Service
public interface GetQuotes 
{
	public GetQuotesResponse getQuotes(GetQuotesRequest request);
}
