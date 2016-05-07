package org.sapient.microservices.application;

import org.sapient.microservices.product.ProductServer;
import org.sapient.microservices.registration.RegistrationServer;
import org.sapient.microservices.resources.ResourceServer;
import org.springframework.boot.SpringApplication;

public class Main {



	public static void main(String[] args) {

		String serverName = "NO-VALUE";

		switch (args.length) {
		case 2:
			// Optionally set the HTTP port to listen on, overrides
			// value in the <server-name>-server.yml file
			System.setProperty("server.port", args[1]);
			// Fall through into ..

		case 1:
			serverName = args[0].toLowerCase();
			break;

		default:
			usage();
			return;
		}

		// Tell server to look for <server-name>-server.properties or
		// <server-name>-server.yml (this app. uses YAML)
		System.setProperty("spring.config.name", serverName + "-server");

		if (serverName.equals("registration")) {
			SpringApplication.run(RegistrationServer.class, args);
		} else if (serverName.equals("products")) {
			SpringApplication.run(ProductServer.class, args);
		} else if (serverName.equals("web")) {
			SpringApplication.run(ResourceServer.class, args);
		} else {
			System.out.println("Unknown server type: " + serverName);
			usage();
		}
	}

	protected static void usage() {
		System.out.println("Usage: java -jar ... <server-name> [server-port]");
		System.out.println("     where server-name is 'registration', "
				+ "'accounts' or 'web' and server-port > 1024");
	}

}
