/**
 * 
 */
package com.networkninja.server.enumeration;

/**
 * @author Akhila Johnson C
 *
 */
public enum Status {
	SERVER_UP("SERVER_UP"),
	SERVER_DOWN("SERVER_DOWN");
	private final String status;
	
	/**
	 * @param string
	 */
	Status(String status) {
		this.status = status;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return this.status;
	}
	
	
	
	
}
