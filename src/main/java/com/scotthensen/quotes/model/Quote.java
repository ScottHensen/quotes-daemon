package com.scotthensen.quotes.model;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import com.scotthensen.quotes.svc.iex.IexStockQuote;

import lombok.Data;
import lombok.NoArgsConstructor;

@RedisHash("Quote")
@Data
@NoArgsConstructor
public class Quote 
{
	@Id
	private String     id;
	@Indexed
	private String     symbol;
	private String     companyName;
	private String     primaryExchange;
	private String     sector;
	private String     calculationPrice;
	private BigDecimal open;
	private BigDecimal openTime;
	private BigDecimal close;
	private BigDecimal closeTime;
	private BigDecimal high;
	private BigDecimal low;
	private BigDecimal latestPrice;
	private String     latestSource;
	private String     latestTime;
	private BigDecimal latestUpdate;
	private BigDecimal latestVolume;
	private BigDecimal iexRealtimePrice;
	private BigDecimal iexRealtimeSize;
	private BigDecimal iexLastUpdated;
	private BigDecimal delayedPrice;
	private BigDecimal delayedPriceTime;
	private BigDecimal extendedPrice;
	private BigDecimal extendedChange;
	private BigDecimal extendedChangePercent;
	private BigDecimal extendedPriceTime;
	private BigDecimal change;
	private BigDecimal changePercent;
	private BigDecimal iexMarketPercent;
	private BigDecimal iexVolume;
	private BigDecimal avgTotalVolume;
	private BigDecimal iexBidPrice;
	private BigDecimal iexBidSize;
	private BigDecimal iexAskPrice;
	private BigDecimal iexAskSize;
	private BigDecimal marketCap;
	private BigDecimal peRatio;
	private BigDecimal week52High;
	private BigDecimal week52Low;
	private BigDecimal ytdChange;

	//TODO:  this is bad form; a constructor coupled to a vendor feed?  use a mapper instead.
	public Quote(IexStockQuote quote) 
	{
		this.id                    = quote.getSymbol();
		this.symbol                = quote.getSymbol();
		this.companyName           = quote.getCompanyName();
		this.primaryExchange       = quote.getPrimaryExchange();
		this.sector                = quote.getSector();
		this.calculationPrice      = quote.getCalculationPrice();
		this.open                  = quote.getOpen();
		this.openTime              = quote.getOpenTime();
		this.close                 = quote.getClose();
		this.closeTime             = quote.getCloseTime();
		this.high                  = quote.getHigh();
		this.low                   = quote.getLow();
		this.latestPrice           = quote.getLatestPrice();
		this.latestSource          = quote.getLatestSource();
		this.latestTime            = quote.getLatestTime();
		this.latestUpdate          = quote.getLatestUpdate();
		this.latestVolume          = quote.getLatestVolume();
		this.iexRealtimePrice      = quote.getIexRealtimePrice();
		this.iexRealtimeSize       = quote.getIexRealtimeSize();
		this.iexLastUpdated        = quote.getIexLastUpdated();
		this.delayedPrice          = quote.getDelayedPrice();
		this.delayedPriceTime      = quote.getDelayedPriceTime();
		this.extendedPrice         = quote.getExtendedPrice();
		this.extendedChange        = quote.getExtendedChange();
		this.extendedChangePercent = quote.getExtendedChangePercent();
		this.extendedPriceTime     = quote.getExtendedPriceTime();
		this.change                = quote.getChange();
		this.changePercent         = quote.getChangePercent();
		this.iexMarketPercent      = quote.getIexMarketPercent();
		this.iexVolume             = quote.getIexVolume();
		this.avgTotalVolume        = quote.getAvgTotalVolume();
		this.iexBidPrice           = quote.getIexBidPrice();
		this.iexBidSize            = quote.getIexBidSize();
		this.iexAskPrice           = quote.getIexAskPrice();
		this.iexAskSize            = quote.getIexAskSize();
		this.marketCap             = quote.getMarketCap();
		this.peRatio               = quote.getPeRatio();
		this.week52High            = quote.getWeek52High();
		this.week52Low             = quote.getWeek52Low();
		this.ytdChange             = quote.getYtdChange();
	}


	public Quote(String symbol, BigDecimal bid, BigDecimal ask) 
	{
		this.id                    = symbol;
		this.symbol                = symbol;
		this.iexBidPrice           = bid;
		this.iexAskPrice           = ask;
	}

}