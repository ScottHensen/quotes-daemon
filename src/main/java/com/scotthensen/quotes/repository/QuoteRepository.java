package com.scotthensen.quotes.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.scotthensen.quotes.model.Quote;

@Repository
public interface QuoteRepository extends CrudRepository<Quote, String>
{
	Optional<Quote> findBySymbol(String symbol);
}