package com.skilldistillery.gaminghub.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.gaminghub.entities.Platform;
import com.skilldistillery.gaminghub.repositories.PlatformRepository;
import com.skilldistillery.gaminghub.repositories.UserRepository;

@Service
public class PlatformServiceImpl implements PlatformService {
	
		@Autowired
		private PlatformRepository platRepo;

		@Autowired
		private UserRepository userRepo;

		@Override
		public Platform getPlatformById(int platformId) {
			Optional<Platform> op = platRepo.findById(platformId);
			if (op.isPresent()) {
				return op.get();
			} else {
				return null;
			}
		}

		@Override
		public Platform createPlatform(Platform plat) {
			return platRepo.saveAndFlush(plat);
		}

		@Override
		public Platform updatePlatform(String name, Platform plat, int platformId) {
			Optional<Platform> op = platRepo.findById(platformId);
			if (op.isPresent()) {
				Platform result = op.get();
				if (result.getName().equals(name)) {
					plat.setId(platformId);
					return platRepo.saveAndFlush(plat);

				}
			}

			return null;
		}

		@Override
		public boolean deletePlatform(String name, int platformId) {
			Optional<Platform> op = platRepo.findById(platformId);
			if (op.isPresent()) {
				Platform result = op.get();
				if (result.getName().equals(name)) {
					platRepo.deleteById(platformId);
					op = platRepo.findById(platformId);
					return !op.isPresent();
				}
			}
			return false;
		}

	}

