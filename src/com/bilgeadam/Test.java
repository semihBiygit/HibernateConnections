package com.bilgeadam;

import com.bilgeadam.util.HibernateSession;

public class Test {

	public static void main(String[] args) {
		
		HibernateSession.getSessionFactory().openSession();
		
	}

}
