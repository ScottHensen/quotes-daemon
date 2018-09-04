package com.scotthensen.quotes.pubsub;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;

import com.scotthensen.quotes.model.Quote;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RedisQuotesPublisher implements QuotesPublisher 
{	
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	
	@Autowired
	private ChannelTopic topic;
	
	
	public RedisQuotesPublisher() 
	{

	}

	public RedisQuotesPublisher(RedisTemplate<String, Object> redisTemplate, ChannelTopic topic)
	{
		this.redisTemplate = redisTemplate;
		this.topic = topic;
	}
	
	
	@Override
	public void publish(List<Quote> quotes) 
	{
		log.info(topic.getTopic() + quotes.toString());
	
		redisTemplate.convertAndSend(topic.getTopic(), quotes.toString());
	}

}
