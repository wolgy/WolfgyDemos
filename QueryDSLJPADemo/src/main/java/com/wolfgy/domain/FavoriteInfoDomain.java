package com.wolfgy.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class FavoriteInfoDomain {

	@Id
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	private String id;
	
	private String favoriteStoreId;
	private String favoriteStoreCode;
	
	@ManyToOne(cascade=CascadeType.PERSIST,fetch=FetchType.LAZY,optional=false)
	private MemberDomain memberDomain;
}
