package com.currency.exchange.service;

import com.currency.exchange.entities.ExchangeValue;

public interface CurrencyExchangeService {

	void addConversion(ExchangeValue request);

	ExchangeValue updateConversion(Long id, ExchangeValue currencyConversion);

}
