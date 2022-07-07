package com.bilgeadam.dao;

import java.util.List;

import org.hibernate.Session;

import com.bilgeadam.entity.Role;

import jakarta.persistence.TypedQuery;

public class RoleDao implements IRepository<Role> {

	@Override
	public void create(Role entity) {

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
			Role deleteRole = find(id);
			if (deleteRole != null) {

				session = databaseConnectionViaHibernate();
				session.getTransaction().begin();
				session.remove(deleteRole);
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
	public void update(long id, Role entity) {

		Session session = null;
		try {
			Role updateRole = find(id);
			if (updateRole != null) {
				updateRole.setRole(entity.getRole());
				updateRole.setDescription(entity.getDescription());

				session = databaseConnectionViaHibernate();
				session.getTransaction().begin();
				session.merge(updateRole);
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
		TypedQuery<Role> typedQuery = session.createQuery(hql, Role.class);
		List<Role> userList = typedQuery.getResultList();
		for (Role role : userList) {
			System.out.println(role);
		}

	}

	@Override
	public Role find(long id) {

		Session session = databaseConnectionViaHibernate();
		Role role;
		try {
			role = session.find(Role.class, id);

			if (role != null) {
				System.out.println("Role found: " + role);
				return role;
			} else {
				System.out.println("Role not found! ");
				return role;
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
