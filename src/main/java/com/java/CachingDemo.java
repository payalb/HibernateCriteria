package com.java;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.java.dto.Phone;
import com.java.dto.Student;
import com.java.dto.Type;

public class CachingDemo {
	static SessionFactory sf;
	static {
		Configuration cfg = new Configuration().addPackage("com.java.dto");
		cfg.configure("hibernate.cfg.xml");
		sf = cfg.buildSessionFactory();
	}

	public static void main(String args[]) {
		
		try {
			//insertRecords();
			secondLevelCacheDemo();
			updateData();
		} finally {
			sf.close();
		}
	}

	private static void firstLevelCacheDemo() {
		Session s=sf.openSession();
		Student s1=s.get(Student.class, 1);//student & phone 
		Student s2=s.get(Student.class, 1);
		System.out.println(s1);System.out.println(s2);
		s.close();
		//1 query fired
	}
	public static void updateData() {
		Session s = sf.openSession();
		Query<Student> query=s.createQuery("update st set name= 'ritu343' where id = 1 ");
		Transaction t=s.beginTransaction();
		query.executeUpdate();
		t.commit();
		s.close();
	}
	private static void secondLevelCacheDemo() {
		//2 queries fired
		Session s=sf.openSession();
		Student s1=s.get(Student.class, 1);
		System.out.println(s1);
		s.close();
		s= sf.openSession();
		Student s2=s.get(Student.class, 1);
		System.out.println(s2);
		s.close();
	}
	
	public static void insertRecords() {
		Student st1 = new Student("payal");
		Student st2 = new Student("ritu");
		Phone p1 = new Phone(76_372l, Type.LANDLINE, st1);
		Phone p2 = new Phone(7_38_47_476l, Type.MOBILE, st2);
		Session s = sf.openSession();
		s.beginTransaction();
		s.persist(p1);
		s.persist(p2);
		s.getTransaction().commit();
		s.close();
	}

}
