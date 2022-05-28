package com.skilldistillery.gaminghub.services;

import com.skilldistillery.gaminghub.entities.Platform;

public interface PlatformService {

	Platform getPlatformById(int platformId);

	Platform createPlatform(Platform plat);

	Platform updatePlatform(String name, Platform plat, int platformId);

	boolean deletePlatform(String name, int platformId);

}
