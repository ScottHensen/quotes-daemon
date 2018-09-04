package com.scotthensen.quotes.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

import com.scotthensen.quotes.pubsub.QuotesPublisher;
import com.scotthensen.quotes.pubsub.RedisQuotesPublisher;

@Configuration
@EnableRedisRepositories
public class RedisConfig 
{
	@Bean
	JedisConnectionFactory jedisConnectionFactory() 
	{
		//For Prod, set up a pool and use the stand-alone configuration
		//RedisStandaloneConfiguration config = new RedisStandaloneConfiguration("localhost",6379);
		//return new JedisConnectionFactory(config);
		return new JedisConnectionFactory();
	}

	@Bean
	public RedisTemplate<String, Object> redisStrObjTemplate() {
		final RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
		template.setConnectionFactory(jedisConnectionFactory());
		return template;
	}

	@Bean
	public QuotesPublisher redisPublisher()
	{
		return new RedisQuotesPublisher(redisStrObjTemplate(), topic());
	}
	
	@Bean
	ChannelTopic topic()
	{
		return new ChannelTopic("quotes.stock.iex");
	}
}