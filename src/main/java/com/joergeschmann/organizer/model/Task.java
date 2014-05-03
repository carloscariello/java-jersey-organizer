package com.joergeschmann.organizer.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;

/**
 * A class for task entities
 * 
 */
@Entity
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotNull
	@FormParam("name")
	private String name;

	@FormParam("description")
	private String description;

	@FormParam("content")
	private String content;

	@DefaultValue("0")
	@FormParam("owner")
	private long owner;

	public Task() {

	}

	/**
	 * 
	 * @return
	 */
	public long getId() {
		return id;
	}

	/**
	 * 
	 * @param value
	 */
	public void setId(long value) {
		this.id = value;
	}

	/**
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @param value
	 */
	public void setName(String value) {
		this.name = value;
	}

	public String getDescription() {
		return description;
	}

	/**
	 * 
	 * @param value
	 */
	public void setDescription(String value) {
		this.description = value;
	}

	/**
	 * 
	 * @return
	 */
	public String getContent() {
		return this.content;
	}

	/**
	 * 
	 * @param value
	 */
	public void setContent(final String value) {
		this.content = value;
	}

	/**
	 * 
	 * @return
	 */
	public long getOwner() {
		return this.owner;
	}

	/**
	 * 
	 * @param value
	 */
	public void setOwner(long value) {
		this.owner = value;
	}
}
