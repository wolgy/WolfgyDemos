package com.wolfgy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.wolfgy.domain.FavoriteInfoDomain;

@Repository
public interface IFavoriteInfoRepository extends JpaRepository<FavoriteInfoDomain, String>,QueryDslPredicateExecutor<FavoriteInfoDomain> {

}
