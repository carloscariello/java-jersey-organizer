package com.joergeschmann.organizer.dal;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.joergeschmann.organizer.model.Task;
import com.joergeschmann.organizer.model.UserProfile;

/**
 * An entity manager for tasks
 * 
 */
public class TaskEntityManager {

	private final EntityManager entityManager;

	public TaskEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	/**
	 * Save a task to db
	 * 
	 * @param task
	 *            {@link Task}
	 */
	public void create(Task task) {
		this.entityManager.getTransaction().begin();
		this.entityManager.persist(task);
		this.entityManager.getTransaction().commit();
	}

	/**
	 * Update a task in the db
	 * 
	 * @param task
	 *            {@link Task}
	 * @return {@link Task} the updated task
	 */
	public Task edit(Task task) {
		this.entityManager.getTransaction().begin();
		final Task updatedTask = this.entityManager.merge(task);
		this.entityManager.getTransaction().commit();
		return updatedTask;
	}

	/**
	 * Deletes a task in the db
	 * 
	 * @param task
	 *            {@link Task}
	 */
	public void remove(Task task) {
		this.entityManager.getTransaction().begin();
		this.entityManager.remove(task);
		this.entityManager.getTransaction().commit();
	}

	/**
	 * Deletes all tasks of an owner
	 * 
	 * @param owner
	 *            the {@link UserProfile} id of the owner
	 * @return the number of deleted tasks
	 */
	public int deleteByOwner(long owner) {
		final Query query = this.entityManager.createQuery(
				"delete from Task o where o.owner = :owner").setParameter(
				"owner", owner);
		this.entityManager.getTransaction().begin();
		int result = query.executeUpdate();
		this.entityManager.getTransaction().commit();
		return result;
	}

	/**
	 * Deletes the task with the specified id and owner
	 * 
	 * @param id
	 * @param owner
	 * @return the number of deleted tasks
	 */
	public int deleteByIdAndOwner(long id, long owner) {
		final Query query = this.entityManager
				.createQuery(
						"delete from Task o where o.id = :id and o.owner = :owner")
				.setParameter("id", id).setParameter("owner", owner);
		this.entityManager.getTransaction().begin();
		int result = query.executeUpdate();
		this.entityManager.getTransaction().commit();
		return result;
	}

	/**
	 * Finds a task by id
	 * 
	 * @param id
	 * @return {@link Task} the task found or null
	 */
	@SuppressWarnings("unchecked")
	public Task findById(long id) {
		final Query query = this.entityManager.createQuery(
				"select o from Task o where o.id = :id").setParameter("id", id);

		final List<Task> resultList = query.getResultList();

		if (resultList.isEmpty()) {
			return null;
		}

		return resultList.get(0);
	}

	/**
	 * Finds tasks with the specified owner
	 * 
	 * @param owner
	 * @return {@link List<Task>}
	 */
	@SuppressWarnings("unchecked")
	public List<Task> findByOwner(long owner) {
		final Query query = this.entityManager.createQuery(
				"select o from Task o where o.owner = :owner").setParameter(
				"owner", owner);

		final List<Task> resultList = query.getResultList();

		return resultList;
	}
}
