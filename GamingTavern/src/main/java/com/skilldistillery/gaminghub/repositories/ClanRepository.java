package com.skilldistillery.gaminghub.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.skilldistillery.gaminghub.entities.Clan;


public interface ClanRepository extends JpaRepository<Clan, Integer> {
	
	Clan findByName(String name);
	
	@Query(value = "select c.id , c.name from clan c", nativeQuery = true)
	List<ClanInformationDTO> getClanData();
	

}
