package com.networkninja.server;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.networkninja.server.enumeration.Status;
import com.networkninja.server.model.Server;
import com.networkninja.server.repo.ServerRepo;

@SpringBootApplication
public class NetworkNinjaServerManagerAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(NetworkNinjaServerManagerAppApplication.class, args);
	}

	@Bean
	CommandLineRunner run(ServerRepo serverRepo) {
		return args -> {
			// Server 1
			serverRepo.save(new Server(null, "192.168.1.160", "Ubuntu Linux", "16 GB", "Personal PC",
					"http://localhost:8080/server/image/server1.png", Status.SERVER_UP));

			// Server 2
			serverRepo.save(new Server(null, "192.168.1.161", "Windows Server", "32 GB", "Enterprise Server",
					"http://localhost:8080/server/image/server2.png", Status.SERVER_DOWN));

			// Server 3
			serverRepo.save(new Server(null, "192.168.1.162", "CentOS", "8 GB", "Test Server",
					"http://localhost:8080/server/image/server3.png", Status.SERVER_UP));

			// Server 4
			serverRepo.save(new Server(null, "192.168.1.163", "Red Hat Linux", "64 GB", "Production Server",
					"http://localhost:8080/server/image/server4.png", Status.SERVER_UP));

			// Server 5
			serverRepo.save(new Server(null, "192.168.1.164", "Fedora", "4 GB", "Development Server",
					"http://localhost:8080/server/image/server5.png", Status.SERVER_DOWN));

		};
	}

}
