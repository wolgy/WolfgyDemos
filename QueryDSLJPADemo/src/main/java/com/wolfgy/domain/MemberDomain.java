package com.wolfgy.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class MemberDomain {

	@Id
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	private String id;
	
	private String status;
	private String name;
	private int age;
	private String address;
	private Date registerDate;
	
	@OneToMany(cascade=CascadeType.PERSIST,mappedBy="memberDomain",fetch=FetchType.LAZY)
	private Set<FavoriteInfoDomain> favoriteInfoDomains = new HashSet<>();
	
}
