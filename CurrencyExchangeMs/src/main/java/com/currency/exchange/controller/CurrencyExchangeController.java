package com.currency.exchange.controller;

import java.math.BigDecimal;
import java.util.Objects;

import org.hibernate.cfg.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.currency.exchange.entities.ExchangeValue;
import com.currency.exchange.repository.ExchangeValueRepository;
import com.currency.exchange.service.CurrencyExchangeService;

@RestController
public class CurrencyExchangeController {
	
	Logger logger= LoggerFactory.getLogger(ExchangeValue.class);
     
    @Autowired
    private CurrencyExchangeService currencyExchangeService;
	
	@Autowired
	private ExchangeValueRepository repository;

	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public ExchangeValue retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {
		
		ExchangeValue exchangeValue = repository.findByFromAndTo(from, to);
		return exchangeValue; 
		
		//new ExchangeValue(1000L,"USD","INR",BigDecimal.valueOf(70));
		}
	
	@PostMapping(path="/addConversion", consumes="application/json")
	ResponseEntity<HttpStatus> addConversion(@RequestBody ExchangeValue request){
		boolean ifSuccesfull=Boolean.FALSE;
		if(!Objects.isNull(request) && !Objects.isNull(request.getFrom()) 
				&& !Objects.isNull(request.getTo()) && !Objects.isNull(request.getConversionMultiple()) ){
		
			currencyExchangeService.addConversion(request);
					ifSuccesfull=Boolean.TRUE;
				}
				if(ifSuccesfull)
					return new ResponseEntity<>(HttpStatus.CREATED);
				else
					return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	
	 @PutMapping("/{id}")
		ResponseEntity<HttpStatus> updateConversion(@PathVariable Long id,@RequestBody ExchangeValue currencyConversion){
	    	boolean ifSuccesfull = Boolean.FALSE;{
	    		currencyExchangeService.updateConversion(id,currencyConversion);
	          ifSuccesfull = Boolean.TRUE;
	    	}
	    	if(ifSuccesfull) {
		    	   return new ResponseEntity<>(HttpStatus.CREATED);
		       }
		    		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		       }
	
	
	
}
