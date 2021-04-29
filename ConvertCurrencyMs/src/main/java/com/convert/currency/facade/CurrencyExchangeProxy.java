package com.convert.currency.facade;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.convert.currency.entities.ConvertCurrency;

@FeignClient(name="CurrencyExchangeMs",url="http://localhost:8000/")
public interface CurrencyExchangeProxy {
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public ConvertCurrency retrieveExchangeValue(@PathVariable("from") String from, @PathVariable("to") String to);

}
