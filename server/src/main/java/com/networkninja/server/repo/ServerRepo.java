/**
 * 
 */
package com.networkninja.server.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.networkninja.server.model.Server;

/**
 * @author Akhila Johnson C
 *
 */
public interface ServerRepo extends JpaRepository<Server, Long> {
	Server findByIpAddress(String ipAddress);
}
