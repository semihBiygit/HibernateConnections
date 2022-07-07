package com.bilgeadam.dao;

import java.util.List;

import org.hibernate.Session;

import com.bilgeadam.entity.Rule;

import jakarta.persistence.TypedQuery;

public class RuleDao implements IRepository<Rule> {

	@Override
	public void create(Rule entity) {

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
			Rule deleteRule = find(id);
			if (deleteRule != null) {

				session = databaseConnectionViaHibernate();
				session.getTransaction().begin();
				session.remove(deleteRule);
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
	public void update(long id, Rule entity) {

		Session session = null;
		try {
			Rule updateRule = find(id);
			if (updateRule != null) {

				session = databaseConnectionViaHibernate();
				session.getTransaction().begin();
				session.merge(updateRule);
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
		TypedQuery<Rule> typedQuery = session.createQuery(hql, Rule.class);
		List<Rule> ruleList = typedQuery.getResultList();
		for (Rule rule : ruleList) {
			System.out.println(rule);
		}

	}

	@Override
	public Rule find(long id) {

		Session session = databaseConnectionViaHibernate();
		Rule rule;
		try {
			rule = session.find(Rule.class, id);

			if (rule != null) {
				System.out.println("Rule found: " + rule);
				return rule;
			} else {
				System.out.println("Rule not found! ");
				return rule;
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
