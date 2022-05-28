package com.skilldistillery.gaminghub.services;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import com.skilldistillery.gaminghub.entities.Server;
import com.skilldistillery.gaminghub.repositories.ServerRepository;

public class ServerServiceImpl implements ServerService {
	
	@Autowired
	private ServerRepository serverRepo;
	

	@Override
	public Server getServerById(int serverId) {
		Optional<Server> op = serverRepo.findById(serverId);
		if (op.isPresent()) {
			return op.get();
		} else {
			return null;
		}
	}

	@Override
	public Server createServer(Server server) {
		return serverRepo.saveAndFlush(server);
	}

	@Override
	public Server updateServer(String name, Server server, int serverId) {
		Optional<Server> op = serverRepo.findById(serverId);
		if (op.isPresent()) {
			Server result = op.get();
			if (result.getName().equals(name)) {
				server.setId(serverId);
				return serverRepo.saveAndFlush(server);
			}
			}

			return null;
	}

	@Override
	public boolean deleteServer(String name, int serverId) {
		Optional<Server> op = serverRepo.findById(serverId);
		if(op.isPresent()) {
			Server result = op.get();
			if(result.getName().equals(name)) {
				serverRepo.deleteById(serverId);
				op = serverRepo.findById(serverId);
				return !op.isPresent();
			}
		}
		return false;
	}

	
	
}
