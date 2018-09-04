package com.scotthensen.quotes.pubsub;

import java.util.List;

import com.scotthensen.quotes.model.Quote;

public interface QuotesPublisher 
{
	void publish(List<Quote> quotes);
}
