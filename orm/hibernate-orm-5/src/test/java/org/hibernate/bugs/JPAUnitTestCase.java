package org.hibernate.bugs;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.bugs.domain.Folder;
import org.hibernate.bugs.domain.Project;
import org.hibernate.bugs.domain.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 * This template demonstrates how to develop a test case for Hibernate ORM, using the Java Persistence API.
 */
public class JPAUnitTestCase {

	private EntityManagerFactory entityManagerFactory;

	@Before
	public void init() {
		entityManagerFactory = Persistence.createEntityManagerFactory( "templatePU" );
	}

	@After
	public void destroy() {
		entityManagerFactory.close();
	}

	// Entities are auto-discovered, so just add them anywhere on class-path
	// Add your tests, using standard JUnit.
	@Test
	public void failingTest() throws Exception {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		// Do stuff...
		User user = new User();
		user.setName("My user");
		entityManager.persist(user);

		Folder folder = new Folder();
		folder.setName("My folder");
		entityManager.persist(folder);

		Project project = new Project();
		project.setName("My project");
		project.setOwner(user);
		project.setFolder(folder);
		entityManager.persist(project);

		project = new Project();
		project.setName("My project");
		project.setOwner(user);
		project.setFolder(folder);
		entityManager.persist(project);

		assertEquals("My user", project.getOwner().getName());
		assertEquals(2, user.getProjects().size());
		assertEquals(2, folder.getProjects().size());

		entityManager.remove(project);
		entityManager.flush();

		assertEquals(1, folder.getProjects().size());
		assertEquals(1, user.getProjects().size());

		entityManager.getTransaction().commit();
		entityManager.close();
	}

	@Test
	public void workingTest() throws Exception {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		// Do stuff...
		User user = new User();
		user.setName("My user");
		entityManager.persist(user);

		Folder folder = new Folder();
		folder.setName("My folder");
		entityManager.persist(folder);

		Project project = new Project();
		project.setName("My project");
		project.setOwner(user);
		project.setFolder(folder);
		entityManager.persist(project);

		project = new Project();
		project.setName("My project");
		project.setOwner(user);
		project.setFolder(folder);
		entityManager.persist(project);

		assertEquals("My user", project.getOwner().getName());
		assertEquals(2, user.getProjects().size());
		assertEquals(2, folder.getProjects().size());

		folder.getProjects().remove(project);
		user.getProjects().remove(project);
		project.setOwner(null);
		project.setFolder(null);
		entityManager.remove(project);
		entityManager.flush();

		assertEquals(1, folder.getProjects().size());
		assertEquals(1, user.getProjects().size());

		entityManager.getTransaction().commit();
		entityManager.close();
	}
}
