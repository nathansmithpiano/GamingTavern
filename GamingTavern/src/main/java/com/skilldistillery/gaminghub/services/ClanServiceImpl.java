package com.skilldistillery.gaminghub.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.gaminghub.entities.Clan;
import com.skilldistillery.gaminghub.repositories.ClanInformationDTO;
import com.skilldistillery.gaminghub.repositories.ClanRepository;

@Service
public class ClanServiceImpl implements ClanService {

	@Autowired
	private ClanRepository clanRepo;

	@Override
	public List<Clan> index() {
		return clanRepo.findAll();
	}

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
	public Clan getClanByName(String name) {
		return clanRepo.findByName(name);
	}

	@Override
	public Clan createClan(Clan clan) {
		return clanRepo.saveAndFlush(clan);
	}

	@Override
	public Clan updateClan(Clan clan, int clanId) {
		Optional<Clan> op = clanRepo.findById(clanId);
		Clan clans = null;
		if (op.isPresent()) {
			clans = op.get();
			clans.setEnabled(clan.isEnabled());
			clans.setName(clan.getName());
			clans.setDescription(clans.getDescription());
			clans.setImageUrl(clan.getImageUrl());
			clans.setCreated(clan.getCreated());
			clans.setUpdated(clan.getUpdated());
			clanRepo.saveAndFlush(clans);
		}
		return clans;
	}

	@Override
	public boolean deleteClan(int clanId) {
		boolean deleted = false;
		if (clanRepo.existsById(clanId)) {
			clanRepo.deleteById(clanId);
			deleted = true;
		}
		return deleted;
	}

	@Override
	public List<ClanInformationDTO> getClanData() {
		return clanRepo.getClanData();
	}

}
