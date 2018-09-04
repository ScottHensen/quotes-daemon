package com.scotthensen.quotes.svc.iex;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class IexStockQuote 
{	
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
}