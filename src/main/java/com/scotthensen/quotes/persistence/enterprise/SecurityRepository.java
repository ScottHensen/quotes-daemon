package com.scotthensen.quotes.persistence.enterprise;

//import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scotthensen.quotes.persistence.enterprise.SecurityEntity;

@Repository
public interface SecurityRepository extends JpaRepository<SecurityEntity, Integer> 
{
	SecurityEntity findSecurityById(Integer securityId);

	SecurityEntity findSecurityBySymbol(String symbol);
	
//	List<SecurityEntity> findAll();
}
