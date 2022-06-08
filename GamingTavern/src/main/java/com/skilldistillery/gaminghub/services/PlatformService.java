package com.skilldistillery.gaminghub.services;

import java.util.List;

import com.skilldistillery.gaminghub.entities.Platform;

public interface PlatformService {
	
	List<Platform> index();

	Platform getPlatformById(int platformId);

	Platform createPlatform(Platform plat);

	Platform updatePlatform(String name, Platform plat, int platformId);

	boolean deletePlatform(String name, int platformId);

}
