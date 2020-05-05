package com.newer.deal.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.newer.deal.entiry.Ticker;


@Repository
public interface TickerRepository extends MongoRepository<Ticker, String> {

}
