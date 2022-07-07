package com.bilgeadam.dao;

import java.util.List;

import org.hibernate.Session;

import com.bilgeadam.entity.User;

import jakarta.persistence.TypedQuery;

public class UserDao implements IRepository<User> {

	@Override
	public void create(User entity) {

		Session session = null;
		try {
			session = databaseConnectionViaHibernate();
			session.getTransaction().begin();
			session.persist(entity);
			session.getTransaction().commit();

			System.out.println("Successfully created");
		} catch (Exception e) {
			System.out.println("Some problems has occured during CREATE operations");
			e.printStackTrace();
		} finally {
			session.close();
		}

	}

	@Override
	public void delete(long id) {

		Session session = null;
		try {
			User deleteUser = find(id);
			if (deleteUser != null) {

				session = databaseConnectionViaHibernate();
				session.getTransaction().begin();
				session.remove(deleteUser);
				session.getTransaction().commit();
			}

			System.out.println("Successfully deleted");
		} catch (Exception e) {
			System.out.println("Some problems has occured during DELETE operations");
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	@Override
	public void update(long id, User entity) {

		Session session = null;
		try {
			User updateUser = find(id);
			if (updateUser != null) {
				updateUser.setEmail(entity.getEmail());
				updateUser.setPassword(entity.getPassword());
				updateUser.setUserDetail(entity.getUserDetail());
				updateUser.setRole(entity.getRole());

				session = databaseConnectionViaHibernate();
				session.getTransaction().begin();
				session.merge(updateUser);
				session.getTransaction().commit();
			}

			System.out.println("Successfully updated.");
		} catch (Exception e) {
			System.out.println("Some problems has occured during UPDATE operations");
			e.printStackTrace();
		} finally {
			session.close();
		}

	}

	@Override
	public void listAll() {
		Session session = databaseConnectionViaHibernate();
		String hql = "select usr from User as usr";
		TypedQuery<User> typedQuery = session.createQuery(hql, User.class);
		List<User> userList = typedQuery.getResultList();
		for (User user : userList) {
			System.out.println(user);
		}

	}

	@Override
	public User find(long id) {

		Session session = databaseConnectionViaHibernate();
		User user;
		try {
			user = session.find(User.class, id);

			if (user != null) {
				System.out.println("User found: " + user);
				return user;
			} else {
				System.out.println("User not found! ");
				return user;
			}
		} catch (Exception e) {
			System.out.println("Some problems has occured during FIND operations");
			e.printStackTrace();
		} finally {
			session.close();
		}

		return null;
	}

}
