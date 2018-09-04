package com.scotthensen.quotes.task;

import java.text.SimpleDateFormat;
import java.util.Date;

//import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.scotthensen.quotes.service.iex.IexCompanyInfo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class GetCompanyInfoFromIEX 
{
	//TODO:  this is just a cut-paste from a previous exercise.  need to actually clean it up and make it 
	//       persist to enterprise each morning.
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss:SSS Z");
	private static final String IEX_URL        = "https://api.iextrading.com/1.0";
	private static final String TESLA_COMPANY  = "/stock/sam/company";

	//@Scheduled(cron = "0/30 * * * * *")
	public void getCompanyInfo() {
		log.info("The time is now {}", dateFormat.format(new Date()), " ... start getCompanyInfo");
		
		RestTemplate restTemplate = new RestTemplate();
		IexCompanyInfo company = restTemplate.getForObject(IEX_URL+TESLA_COMPANY, IexCompanyInfo.class);
		log.info(company.toString());
		
		log.info("The time is now {}", dateFormat.format(new Date()), " ... stop  getCompanyInfo");		
	}

}
