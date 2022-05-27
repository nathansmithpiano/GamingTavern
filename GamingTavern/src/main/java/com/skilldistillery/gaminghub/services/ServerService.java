package com.skilldistillery.gaminghub.services;

import com.skilldistillery.gaminghub.entities.Server;

public interface ServerService {
	
	Server getServerById(int serverId);
	
	Server createServer(Server server);
	
	Server updateServer(String name, Server server, int serverId);
	
	boolean deleteServer(String name, int serverId);


}
