package dev.thedevcafe;

import org.springframework.boot.SpringApplication;

public class TestTheDevCafeApplication {

	public static void main(String[] args) {
		SpringApplication.from(TheDevCafeApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
