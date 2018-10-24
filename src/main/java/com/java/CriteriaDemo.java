package com.java;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.java.dto.Phone;
import com.java.dto.Phone_;
import com.java.dto.Student;
import com.java.dto.Student_;
import com.java.dto.Type;

public class CriteriaDemo {
	static SessionFactory sf;
	static {
		Configuration cfg = new Configuration().addPackage("com.java.dto");
		cfg.configure("hibernate.cfg.xml");
		sf = cfg.buildSessionFactory();
	}

	public static void main(String args[]) {
		
		try {
			//insertRecords();
			getStudents();
			getStudentById();
			getStudentByPhoneId();
		} finally {
			sf.close();
		}
	}

	
	public static void getStudents() {
		Session s = sf.openSession();
		CriteriaBuilder cb=s.getCriteriaBuilder();
		//To specify result type
		CriteriaQuery<Student> cq=cb.createQuery(Student.class);
		Root<Student> root=cq.from(Student.class);
		cq.select(root);
		List<Student> list=s.createQuery(cq).getResultList();
		System.out.println(list);
		s.close();
	}
	
	public static void getStudentById() {
		Session s = sf.openSession();
		CriteriaBuilder cb=s.getCriteriaBuilder();
		//To specify result type
		CriteriaQuery<Student> cq=cb.createQuery(Student.class);
		Root<Student> root=cq.from(Student.class);
		cq.select(root);
		cq.where(cb.equal(root.get(Student_.id), 1));
		List<Student> list=s.createQuery(cq).getResultList();
		System.out.println(list);
		s.close();
	}
	
	public static void getStudentByPhoneId() {
		Session s = sf.openSession();
		CriteriaBuilder cb=s.getCriteriaBuilder();
		//To specify result type
		CriteriaQuery<Student> cq=cb.createQuery(Student.class);
		Root<Phone> root=cq.from(Phone.class);
		cq.select(root.get(Phone_.STUDENT));
		cq.where(cb.equal(root.get(Phone_.NUMBER), 76_372l));
		Student st=s.createQuery(cq).uniqueResult();
		System.out.println(st);
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
