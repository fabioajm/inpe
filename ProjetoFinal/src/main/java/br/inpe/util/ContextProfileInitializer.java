package br.inpe.util;

import java.io.IOException;
import java.util.Properties;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.web.context.ConfigurableWebApplicationContext;

public class ContextProfileInitializer implements
		ApplicationContextInitializer<ConfigurableWebApplicationContext> {
	public void initialize(ConfigurableWebApplicationContext ctx) {
		ConfigurableEnvironment environment = ctx.getEnvironment();
		// logic to decide active profiles
		Properties proper = new Properties();
		try {
			proper.load(getClass().getResourceAsStream("/conf/environment"));
			
			String profiles = proper.getProperty("profile").trim();
			environment.setActiveProfiles(profiles);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}