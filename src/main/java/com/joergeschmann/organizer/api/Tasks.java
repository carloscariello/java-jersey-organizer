package com.joergeschmann.organizer.api;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.joergeschmann.organizer.controller.TaskController;
import com.joergeschmann.organizer.helper.RequestHelper;
import com.joergeschmann.organizer.model.Task;
import com.joergeschmann.organizer.response.ResponseResult;

/**
 * API for Tasks
 * 
 */
@Path("/tasks")
public class Tasks {

	/**
	 * Gets all tasks for the user
	 * 
	 * @param requestContext
	 * @return A list of tasks
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String index(@Context ContainerRequestContext requestContext) {
		final Gson gson = new Gson();

		long ownerId = RequestHelper.getUserIdFromContext(requestContext);
		final List<Task> taskList = TaskController.getInstance()
				.findTasksForUser(ownerId);

		return gson.toJson(taskList);
	}

	/**
	 * Adds a new task to the db
	 * 
	 * @param task
	 * @param requestContext
	 * @return
	 */
	@POST
	@Path("create")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String create(Task task,
			@Context ContainerRequestContext requestContext) {

		final Gson gson = new Gson();
		final ResponseResult responseResult = new ResponseResult();

		if (task.getOwner() == 0) {
			long ownerId = RequestHelper.getUserIdFromContext(requestContext);
			task.setOwner(ownerId);
		}

		TaskController.getInstance().saveTask(task);

		if (task.getId() == 0) {
			responseResult.addErrorMessage("project",
					"The task could not be saved.");
			return gson.toJson(responseResult);
		}

		return gson.toJson(task);
	}

	/**
	 * Updates a task
	 * 
	 * @param task
	 * @param requestContext
	 * @return the updated task
	 */
	@POST
	@Path("update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String update(Task task,
			@Context ContainerRequestContext requestContext) {

		final Gson gson = new Gson();

		if (task.getOwner() == 0) {
			long ownerId = RequestHelper.getUserIdFromContext(requestContext);
			task.setOwner(ownerId);
		}

		TaskController.getInstance().updateTask(task);

		return gson.toJson(task);
	}

	/**
	 * Deletes a task
	 * 
	 * @param task
	 * @param requestContext
	 * @return {@link ResponseResult}
	 */
	@POST
	@Path("delete")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteById(Task task,
			@Context ContainerRequestContext requestContext) {
		final Gson gson = new Gson();
		final ResponseResult responseResult = new ResponseResult();

		long ownerId = RequestHelper.getUserIdFromContext(requestContext);
		TaskController.getInstance().deleteTaskById(task.getId(), ownerId);

		return gson.toJson(responseResult);
	}
}
