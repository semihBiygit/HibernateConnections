package com.bilgeadam.dao;

import org.hibernate.Session;

import com.bilgeadam.util.HibernateSession;

public interface IRepository<T> {

	public void create(T entity);

	public void delete(long id);

	public void update(long id, T entity);

	public void listAll();

	public T find(long id);

	default Session databaseConnectionViaHibernate() {
		return HibernateSession.getSessionFactory().openSession();
	}
}