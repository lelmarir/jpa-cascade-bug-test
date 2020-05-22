package com.example;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class App {

	private static EntityManagerFactory emf;
	private static EntityManager em;
	private static Integer id;
	
	public static void main(String[] args) throws ClassNotFoundException {

		{
			emf = Persistence.createEntityManagerFactory("example");
			em = emf.createEntityManager();

			runInTransaction(em, () -> {
				Parent p = new Parent();
				
				p.getChilds().add(new Child());
				p.getChilds().add(new Child());
				p.getChilds().add(new Child());
				
				em.persist(p);
				id = p.id;
				System.out.println(id);
			});

			em.close();
			emf.close();
			
			//--------------
			
			emf = Persistence.createEntityManagerFactory("example");
			em = emf.createEntityManager();

			runInTransaction(em, () -> {
				
				Parent p = em.find(Parent.class, id);
				Child firstChild = p.getChilds().get(0);
				
				em.detach(firstChild);
				
				em.flush();
				
				System.out.println(p);
				
			});

			em.close();
			emf.close();
		}
	}

	public static void runInTransaction(EntityManager em, Runnable runnable) {
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {
			runnable.run();
			tx.commit();
		} catch (Exception e) {
			try {
				e.printStackTrace();
				tx.rollback();
			} catch (IllegalStateException e1) {
				e1.printStackTrace();
			}
		}
	}
}
