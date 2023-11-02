package com.networkninja.server.resource;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import com.networkninja.server.enumeration.Status;
import com.networkninja.server.model.Response;
import com.networkninja.server.model.Server;
import com.networkninja.server.service.implementation.ServerServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/server")
@RequiredArgsConstructor
public class ServerResource {
	private final ServerServiceImpl serverService;

	@GetMapping("/list")
	public ResponseEntity<Response> getServers() throws InterruptedException {
		LocalDateTime currentDateTime = Instant.now().atZone(ZoneId.systemDefault()).toLocalDateTime();
		TimeUnit.SECONDS.sleep(3);
		return ResponseEntity
				.ok(Response.builder().timeStamp(currentDateTime).data(Map.of("servers", serverService.list(30)))
						.message("Servers retrieved").status(HttpStatus.OK).statusCode(HttpStatus.OK.value()).build());
	}

	@GetMapping("/ping/{ipAddress}")
	public ResponseEntity<Response> pingServer(@PathVariable("ipAddress") String ipAddress) throws IOException {
		Server server = serverService.ping(ipAddress);

		LocalDateTime currentDateTime = Instant.now().atZone(ZoneId.systemDefault()).toLocalDateTime();

		return ResponseEntity.ok(Response.builder().timeStamp(currentDateTime).data(Map.of("server", server))
				.message(server.getStatus() == Status.SERVER_UP ? "Ping success" : "Ping failed").status(HttpStatus.OK)
				.statusCode(HttpStatus.OK.value()).build());
	}

	@PostMapping("/save")
	public ResponseEntity<Response> saveServer(@RequestBody @Valid Server server) {
		LocalDateTime currentDateTime = Instant.now().atZone(ZoneId.systemDefault()).toLocalDateTime();

		return ResponseEntity.ok(Response.builder().timeStamp(currentDateTime)
				.data(Map.of("server", serverService.create(server))).message("Server created")
				.status(HttpStatus.CREATED).statusCode(HttpStatus.CREATED.value()).build());
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<Response> getServer(@PathVariable("id") Long id) {

		LocalDateTime currentDateTime = Instant.now().atZone(ZoneId.systemDefault()).toLocalDateTime();

		return ResponseEntity
				.ok(Response.builder().timeStamp(currentDateTime).data(Map.of("server", serverService.get(id)))
						.message("Server retrieved").status(HttpStatus.OK).statusCode(HttpStatus.OK.value()).build());
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Response> deleteServer(@PathVariable("id") Long id) {

		LocalDateTime currentDateTime = Instant.now().atZone(ZoneId.systemDefault()).toLocalDateTime();

		return ResponseEntity
				.ok(Response.builder().timeStamp(currentDateTime).data(Map.of("Deleted", serverService.delete(id)))
						.message("Server deleted").status(HttpStatus.OK).statusCode(HttpStatus.OK.value()).build());
	}

	@GetMapping(value = "/image/{fileName}", produces = MediaType.IMAGE_PNG_VALUE)
	public byte[] getServerImage(@PathVariable("fileName") String fileName) throws IOException {
		return Files.readAllBytes(Paths.get(System.getProperty("user.home") + "/Downloads/images/" + fileName));
	}
}
