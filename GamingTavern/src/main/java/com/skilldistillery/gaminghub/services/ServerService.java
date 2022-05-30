package com.skilldistillery.gaminghub.services;

import java.util.List;

import com.skilldistillery.gaminghub.entities.Server;

public interface ServerService {
	
	List<Server> index();
	
	Server getServerById(int serverId);
	
	Server createServer(Server server);
	
	Server updateServer(String name, Server server, int serverId);
	
	boolean deleteServer(String name, int serverId);


}
