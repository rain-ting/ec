package com.newer.deal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.newer.deal.entiry.Ticker;
import com.newer.deal.repository.TickerRepository;


@RestController
@RequestMapping("/ticker")
public class TickerController {

	@Autowired
	TickerRepository tickerRepository;
	
	@GetMapping
	public Ticker a() {
		
		RestTemplate rest = new RestTemplate();
		
		String url = "https://api.huobi.me/market/detail/merged?symbol=btcusdt";
		
		Ticker ticker = rest.exchange(url, HttpMethod.GET, null, Ticker.class).getBody();
		
		tickerRepository.save(ticker);
		
		return ticker;
	}
}
