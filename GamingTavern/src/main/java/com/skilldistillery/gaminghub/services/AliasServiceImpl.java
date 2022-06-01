package com.skilldistillery.gaminghub.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.gaminghub.entities.Alias;
import com.skilldistillery.gaminghub.repositories.AliasRepository;

@Service
public class AliasServiceImpl implements AliasService {

	@Autowired
	private AliasRepository aliasRepo;

	@Override
	public List<Alias> index() {
		return aliasRepo.findAll();
	}

	@Override
	public Alias getAliasById(int aliasId) {
		Optional<Alias> op = aliasRepo.findById(aliasId);
		if (op.isPresent()) {
			return op.get();
		} else {
			return null;
		}
	}

	@Override
	public Alias createAlias(Alias alias) {
		return aliasRepo.saveAndFlush(alias);
	}

	@Override
	public List<Alias> getAliasesByUsername(String username) {
		return aliasRepo.findByUserUsername(username);
	}

	@Override
	public Alias updateAlias(String user, Alias alias, int aliasId) {
		Optional<Alias> op = aliasRepo.findById(aliasId);
		if (op.isPresent()) {
			Alias result = op.get();
			if (result.getUser().equals(user)) {
				alias.setId(aliasId);
				return aliasRepo.saveAndFlush(alias);

			}
		}

		return null;
	}

	@Override
	public boolean deleteAlias(String username, int aliasId) {
		Optional<Alias> op = aliasRepo.findById(aliasId);
		if (op.isPresent()) {
			Alias result = op.get();
			if (result.getId() == (aliasId)) {
				aliasRepo.deleteById(aliasId);
				op = aliasRepo.findById(aliasId);
				return !op.isPresent();
			}
		}
		return false;

	}

}