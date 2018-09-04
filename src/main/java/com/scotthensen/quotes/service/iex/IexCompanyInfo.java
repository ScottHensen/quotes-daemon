package com.scotthensen.quotes.service.iex;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class IexCompanyInfo 
{
	private String symbol;
	private String companyName;
	private String exchange;
	private String industry;
	private String website;
	private String description;
	private String CEO;
	private String issueType;
	private String sector;
}
