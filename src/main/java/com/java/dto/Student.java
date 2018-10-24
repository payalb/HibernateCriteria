package com.java.dto;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude="phone")
@EqualsAndHashCode
@Entity(name="st")
@Table(name="student_details")
@Cacheable
@Cache(usage=CacheConcurrencyStrategy.READ_ONLY)
public class Student {
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	private int id;
	@Column(name="stName",unique=true)
	private String name;
	public Student(String name) {
		this.name= name;
	}
	@Version
	long version;
}
/*student_phone*/