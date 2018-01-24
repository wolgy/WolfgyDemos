package com.wolfgy.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.wolfgy.domain.MemberDomain;

@Repository
public interface IMemberRepository extends MongoRepository<MemberDomain, String >,QueryDslPredicateExecutor<MemberDomain> {

}
