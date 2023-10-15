/**
 * 
 */
package com.networkninja.server.service;

import java.io.IOException;
import java.util.Collection;

import com.networkninja.server.model.Server;


/**
 * @author Akhila Johnson C
 *
 */
public interface ServerService {
	Server create(Server server);
	Server ping(String ipAddress) throws IOException;
	Collection<Server> list(int limit);
	Server get(Long id);
	Server update(Server server);
	Boolean delete(Long id);
}
