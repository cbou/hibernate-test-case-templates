package org.hibernate.bugs;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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
	public void hhh123Test() throws Exception {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		// Do stuff...
		User user = new User();
		user.setName("My user");
		entityManager.persist(user);

		Project project = new Project();
		project.setName("My project");
		project.setOwner(user);
		entityManager.persist(project);

		project = new Project();
		project.setName("My project");
		project.setOwner(user);
		entityManager.persist(project);

		assertEquals("My user", project.getOwner().getName());
		assertEquals(2, user.getProjects().size());

		entityManager.remove(project);
		entityManager.flush();

		assertEquals("My user", project.getOwner().getName());
		assertEquals(1, user.getProjects().size());

		entityManager.getTransaction().commit();
		entityManager.close();
	}
}
