package com.java.dto;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

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
@ToString
@EqualsAndHashCode
@Entity
@Cache(usage=CacheConcurrencyStrategy.READ_ONLY)
@Cacheable
public class Phone {
	@Id
	private long number;
	@Enumerated(EnumType.STRING)
	private Type type;
	@OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
		private Student student;
}
