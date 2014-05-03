package com.joergeschmann.organizer.config;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;

import org.ho.yaml.Yaml;

/**
 * A configuration class for the application
 * 
 */
public class AppConfig {

	private static final String DEFAULT_CONFIG_PATH = "META-INF/AppConfig.yaml";

	private static AppConfig instance = readAppConfig();
	private String persistenceUnitName;
	private String driver;
	private String url;
	private String user;
	private String password;
	private String ddlGeneration;
	private String ddlGenerationMode;

	public AppConfig() {

	}

	public static AppConfig getInstance() {
		return instance;
	}

	public String getPersistenceUnitName() {
		return persistenceUnitName;
	}

	public void setPersistenceUnitName(String persistenceUnitName) {
		this.persistenceUnitName = persistenceUnitName;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDdlGeneration() {
		return ddlGeneration;
	}

	public void setDdlGeneration(String ddlGeneration) {
		this.ddlGeneration = ddlGeneration;
	}

	public String getDdlGenerationMode() {
		return ddlGenerationMode;
	}

	public void setDdlGenerationMode(String ddlGenerationMode) {
		this.ddlGenerationMode = ddlGenerationMode;
	}

	private static AppConfig readAppConfig() {
		try {
			final URL configUrl = AppConfig.class.getClassLoader().getResource(
					DEFAULT_CONFIG_PATH);
			final AppConfig config = Yaml.loadType(
					new File(configUrl.getPath()), AppConfig.class);
			return config;
		} catch (FileNotFoundException e) {
			return new AppConfig();
		}
	}
}
