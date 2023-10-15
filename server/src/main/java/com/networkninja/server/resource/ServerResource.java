package com.networkninja.server.resource;

import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import com.networkninja.server.model.Response;
import com.networkninja.server.service.implementation.ServerServiceImpl;

import lombok.RequiredArgsConstructor;

/**
 * @author Akhila Johnson C
 *
 */
@RestController
@RequestMapping("/server")
@RequiredArgsConstructor
public class ServerResource {
	private final ServerServiceImpl serverService;

	@GetMapping("/list")
	public ResponseEntity<Response> getServers() {
		LocalDateTime currentDateTime = Instant.now().atZone(ZoneId.systemDefault()).toLocalDateTime();

		return ResponseEntity
				.ok(Response.builder().timeStamp(currentDateTime).data(Map.of("servers", serverService.list(30)))
						.message("Servers retrieved").status(HttpStatus.OK).statusCode(HttpStatus.OK.value()).build());
	}
}
