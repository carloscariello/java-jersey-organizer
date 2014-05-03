package com.joergeschmann.organizer.response;

import java.util.HashMap;
import java.util.Map;

/**
 * A class to generalize the response format
 * 
 */
public class ResponseResult {

	private ResponseStatus status;
	private Map<String, String> messages;
	private boolean errorFlag;
	private Map<String, String> errorMessages;

	/**
	 * Possible statuses
	 * 
	 * @author jeschmann
	 * 
	 */
	public enum ResponseStatus {
		NOT_SUCCESSFUL, SUCCESSFUL;
	};

	public ResponseResult() {
		this.messages = new HashMap<String, String>();
		this.errorMessages = new HashMap<String, String>();
		setStatus(ResponseStatus.SUCCESSFUL);
	}

	/**
	 * The status
	 * 
	 * @return {@link ResponseStatus}
	 */
	public String getStatus() {
		return status.name();
	}

	/**
	 * Sets the status
	 * 
	 * @param status
	 */
	public void setStatus(final ResponseStatus status) {
		this.status = status;
	}

	/**
	 * Add info messages
	 * 
	 * @param key
	 * @param message
	 */
	public void addMessage(final String key, final String message) {
		if (key == null || message == null) {
			return;
		}
		this.messages.put(key, message);
	}

	/**
	 * Get info messages
	 * 
	 * @return
	 */
	public Map<String, String> getMessages() {
		return messages;
	}

	/**
	 * Replaces the entire message map
	 * 
	 * @param messages
	 */
	public void setMessage(final Map<String, String> messages) {
		this.messages = messages;
	}

	/**
	 * Returns true if the are some errors messages
	 * 
	 * @return
	 */
	public boolean hasErrorFlag() {
		return errorFlag;
	}

	/**
	 * Activates the error flag and sets the response status to
	 * ResponseStatus.NOT_SUCCESSFUL
	 * 
	 * @param hasErrors
	 */
	public void setErrorFlag(boolean hasErrors) {
		this.errorFlag = hasErrors;
		if (this.errorFlag == true) {
			setStatus(ResponseStatus.NOT_SUCCESSFUL);
		} else {
			setStatus(ResponseStatus.SUCCESSFUL);
		}
	}

	/**
	 * Add error message and set the error flag
	 * 
	 * @param key
	 * @param message
	 */
	public void addErrorMessage(final String key, final String message) {
		if (key == null || message == null) {
			return;
		}
		this.errorMessages.put(key, message);
		setErrorFlag(true);
	}

	/**
	 * Returns the error message map
	 * 
	 * @return
	 */
	public Map<String, String> getErrorMessages() {
		return errorMessages;
	}

	/**
	 * Replaces the entire error message map
	 * 
	 * @param messages
	 */
	public void setErrorMessages(final Map<String, String> messages) {
		this.errorMessages = messages;
	}
}
