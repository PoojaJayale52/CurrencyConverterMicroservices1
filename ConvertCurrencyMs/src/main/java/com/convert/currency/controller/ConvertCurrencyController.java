package com.convert.currency.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.convert.currency.entities.ConvertCurrency;
import com.convert.currency.facade.CurrencyExchangeProxy;

import io.github.resilience4j.retry.annotation.Retry;

@RestController
public class ConvertCurrencyController {
	
	Logger logger= LoggerFactory.getLogger(ConvertCurrency.class);
	
	@Autowired
	private CurrencyExchangeProxy proxy;
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}/amount/{amount}")
   // @Retry(name="convertcurrencyretry")
	
	public ConvertCurrency calculateAmount(@PathVariable String from,@PathVariable String to,
			@PathVariable BigDecimal amount) {
		
		//logger.info("\n\n trying \n\n");
		Map<String, String> uriVariables = new HashMap<>();
		uriVariables.put("from", from);
		uriVariables.put("to", to);
		
		ResponseEntity<ConvertCurrency> responseEntity= new RestTemplate().getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}", ConvertCurrency.class, uriVariables);
		ConvertCurrency convertCurrency= responseEntity.getBody();
		
		return new ConvertCurrency(convertCurrency.getId(),convertCurrency.getFrom(),convertCurrency.getTo(),
				convertCurrency.getConversionMultiple(),amount,amount.multiply(convertCurrency.getConversionMultiple()));
	}
	
	@GetMapping("/currency-exchange-feign/from/{from}/to/{to}/amount/{amount}")
	public ConvertCurrency calculateAmountFeign(@PathVariable String from,@PathVariable String to,
			@PathVariable BigDecimal amount) {
		
		ConvertCurrency convertCurrency=proxy.retrieveExchangeValue(from, to);
		
		return new ConvertCurrency(convertCurrency.getId(),convertCurrency.getFrom(),convertCurrency.getTo(),
				convertCurrency.getConversionMultiple(),amount,amount.multiply(convertCurrency.getConversionMultiple()));
	}


}
