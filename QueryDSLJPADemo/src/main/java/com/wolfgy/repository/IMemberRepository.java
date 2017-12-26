package com.wolfgy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.wolfgy.domain.MemberDomain;

@Repository
public interface IMemberRepository extends JpaRepository<MemberDomain, String>,QueryDslPredicateExecutor<MemberDomain> {

}
