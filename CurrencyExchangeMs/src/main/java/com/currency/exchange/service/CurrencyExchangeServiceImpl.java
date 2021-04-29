package com.currency.exchange.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.currency.exchange.entities.ExchangeValue;
import com.currency.exchange.repository.ExchangeValueRepository;

@Service
public class CurrencyExchangeServiceImpl implements CurrencyExchangeService {
	
	@Autowired
	private ExchangeValueRepository exchangeValueRepository;

	@Override
	public void addConversion(ExchangeValue request) {
		
		exchangeValueRepository.save(request);
	
		
	}

	@Override
	public ExchangeValue updateConversion(Long id, ExchangeValue exchangeValue) {
		if(exchangeValueRepository.findById(id).isPresent()) {
			ExchangeValue c=exchangeValueRepository.findById(id).get();
			
			c.setFrom(exchangeValue.getFrom());
			c.setTo(exchangeValue.getTo());
			c.setConversionMultiple(exchangeValue.getConversionMultiple());
			
			ExchangeValue value = exchangeValueRepository.save(c);
			return new ExchangeValue(value.getId(),value.getFrom(),value.getTo(),value.getConversionMultiple());
		}else {
		
			return null;
		}

	}
}
