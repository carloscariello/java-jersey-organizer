package com.joergeschmann.organizer.dal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.joergeschmann.organizer.model.Task;

public class TaskEntityManagerTests {

	private static TaskEntityManager entityManager;
	private static long defaultOwnerId = 1;

	@BeforeClass
	public static void beforeClass() {
		entityManager = EntityManagerBuilder.getInstance().createEntityManager(
				TaskEntityManager.class);
	}

	@Before
	public void beforeTest() {
		entityManager.deleteByOwner(defaultOwnerId);
	}

	@Test
	public void testShouldInsertAndFindATask() {

		Task newTask = new Task();
		newTask.setName("Test task");
		newTask.setDescription("Some Description");
		newTask.setOwner(defaultOwnerId);

		entityManager.create(newTask);

		List<Task> foundTasks = entityManager.findByOwner(defaultOwnerId);
		assertTrue(foundTasks.size() == 1);
		assertEquals(newTask.getId(), foundTasks.get(0).getId());

	}

	@Test
	public void testShouldDeleteAndNotFindATask() {

		Task newTask = new Task();
		newTask.setName("Test task");
		newTask.setDescription("Some Description");
		newTask.setOwner(defaultOwnerId);

		entityManager.create(newTask);

		List<Task> foundTasks = entityManager.findByOwner(1);
		assertTrue(foundTasks.size() == 1);
		assertEquals(newTask.getId(), foundTasks.get(0).getId());

		entityManager.deleteByIdAndOwner(newTask.getId(), defaultOwnerId);

		assertTrue(entityManager.findByOwner(defaultOwnerId).size() == 0);
	}

}
