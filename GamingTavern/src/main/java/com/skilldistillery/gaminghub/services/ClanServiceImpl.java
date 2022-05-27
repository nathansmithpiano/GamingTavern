package com.skilldistillery.gaminghub.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import com.skilldistillery.gaminghub.entities.Clan;
import com.skilldistillery.gaminghub.repositories.ClanRepository;


public class ClanServiceImpl implements ClanService {
	
	@Autowired
	private ClanRepository clanRepo;

	@Override
	public Clan getClanById(int clanId) {
		Optional<Clan> op = clanRepo.findById(clanId);
		if (op.isPresent()) {
			return op.get();
		} else {
			return null;
		}
	}

	@Override
	public Clan createClan(Clan clan) {
		return clanRepo.saveAndFlush(clan);
	}

	@Override
	public Clan updateClan(String name, Clan clan, int clanId) {
		Optional<Clan> op = clanRepo.findById(clanId);
		if (op.isPresent()) {
			Clan result = op.get();
			if (result.getName().equals(name)) {
				clan.setId(clanId);
				return clanRepo.saveAndFlush(clan);
			}
			}

			return null;
	}

	@Override
	public boolean deleteClan(String name, int clanId) {
		Optional<Clan> op = clanRepo.findById(clanId);
		if(op.isPresent()) {
			Clan result = op.get();
			if(result.getName().equals(name)) {
				clanRepo.deleteById(clanId);
				op = clanRepo.findById(clanId);
				return !op.isPresent();
			}
		}
		return false;
	}

}
