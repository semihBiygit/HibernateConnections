package com.bilgeadam.entity;

import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "rule")
public class Rule {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String rule;
	private String description;

	@Column(name = "created_on")
	@Temporal(value = TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date createdOn;

	@ManyToMany(mappedBy = "rules")
	private List<Role> roles;
}
