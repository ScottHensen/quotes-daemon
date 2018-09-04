package com.scotthensen.quotes.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scotthensen.quotes.persistence.SecurityEntity;

@Repository
public interface SecurityRepository extends JpaRepository<SecurityEntity, Integer> 
{

}
