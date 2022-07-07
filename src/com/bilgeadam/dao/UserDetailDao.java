package com.bilgeadam.dao;

import java.util.List;

import org.hibernate.Session;

import com.bilgeadam.entity.UserDetail;

import jakarta.persistence.TypedQuery;

public class UserDetailDao implements IRepository<UserDetail> {

	@Override
	public void create(UserDetail entity) {

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
			UserDetail deleteUserDetail = find(id);
			if (deleteUserDetail != null) {

				session = databaseConnectionViaHibernate();
				session.getTransaction().begin();
				session.remove(deleteUserDetail);
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
	public void update(long id, UserDetail entity) {

		Session session = null;
		try {
			UserDetail updateUserDetail = find(id);
			if (updateUserDetail != null) {
				updateUserDetail.setBio(entity.getBio());
				updateUserDetail.setFirstName(entity.getFirstName());
				updateUserDetail.setLastName(entity.getLastName());
				updateUserDetail.setBio(entity.getBio());

				session = databaseConnectionViaHibernate();
				session.getTransaction().begin();
				session.merge(updateUserDetail);
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
		TypedQuery<UserDetail> typedQuery = session.createQuery(hql, UserDetail.class);
		List<UserDetail> userDetailList = typedQuery.getResultList();
		for (UserDetail userdetail : userDetailList) {
			System.out.println(userdetail);
		}

	}

	@Override
	public UserDetail find(long id) {

		Session session = databaseConnectionViaHibernate();
		UserDetail userDetail;
		try {
			userDetail = session.find(UserDetail.class, id);

			if (userDetail != null) {
				System.out.println("User found: " + userDetail);
				return userDetail;
			} else {
				System.out.println("User not found! ");
				return userDetail;
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
