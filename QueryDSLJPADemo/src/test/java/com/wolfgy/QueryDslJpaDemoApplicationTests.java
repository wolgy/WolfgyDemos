package com.wolfgy;

import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.wolfgy.config.AppTestConfig;
import com.wolfgy.domain.FavoriteInfoDomain;
import com.wolfgy.domain.MemberDomain;
import com.wolfgy.domain.QFavoriteInfoDomain;
import com.wolfgy.domain.QMemberDomain;
import com.wolfgy.dto.MemberFavoriteDto;
import com.wolfgy.repository.IFavoriteInfoRepository;
import com.wolfgy.repository.IMemberRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppTestConfig.class })
@Transactional
@Rollback(false)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class QueryDslJpaDemoApplicationTests {

	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	IMemberRepository memberRepo;
	@Autowired
	IFavoriteInfoRepository favoriteInfoRepo;
	@Autowired
	JPAQueryFactory queryFactory;

	@Test
	public void contextLoads() {
	}
	
	@Test
	public void preSave(){
		MemberDomain member = new MemberDomain();
		member.setStatus("0011");
		FavoriteInfoDomain favoriteInfo = new FavoriteInfoDomain();
		favoriteInfo.setFavoriteStoreCode("0721");
		favoriteInfo.setMemberDomain(member);
		favoriteInfoRepo.save(favoriteInfo);
		member = new MemberDomain();
		member.setStatus("0013");
		favoriteInfo = new FavoriteInfoDomain();
		favoriteInfo.setFavoriteStoreCode("0721");
		favoriteInfo.setMemberDomain(member);
		favoriteInfoRepo.save(favoriteInfo);
	}
	
	@Test
	public void queryFactoryUpdate(){
		logger.info("使用queryFactory执行update动作开始。");
		QMemberDomain qm = QMemberDomain.memberDomain;
		long affectedNum = queryFactory.update(qm).set(qm.status, "0012").where(qm.status.eq("0011")).execute();
		logger.info("本次操作影响了"+affectedNum+"行。");
	}

	@Test	
	public void queryFactoryDelete(){
		logger.info("使用queryFactory执行delete动作开始。");
		QMemberDomain qm = QMemberDomain.memberDomain;
		long affectedNum = queryFactory.delete(qm).where(qm.status.eq("0012")).execute();
		logger.info("本次操作影响了"+affectedNum+"行。");
	}
	
	@SuppressWarnings("unused")
	@Test
	public void queyFactorySelect(){
		QMemberDomain qm = QMemberDomain.memberDomain;
		QFavoriteInfoDomain qf= QFavoriteInfoDomain.favoriteInfoDomain;
		//查询字段-select()
		List<String> nameList = queryFactory.select(qm.name).from(qm).fetch();
		//查询实体-selectFrom()
		List<MemberDomain> memberList = queryFactory.selectFrom(qm).fetch();
		//查询并将结果封装至dto中
		List<MemberFavoriteDto> dtoList = queryFactory.select(Projections.constructor(MemberFavoriteDto.class,qm.name,qf.favoriteStoreCode)).from(qm).leftJoin(qm.favoriteInfoDomains,qf).fetch();
		//去重查询-selectDistinct()
		List<String> distinctNameList = queryFactory.selectDistinct(qm.name).from(qm).fetch();
		//获取首个查询结果-fetchFirst()
		MemberDomain firstMember = queryFactory.selectFrom(qm).fetchFirst();
		//获取唯一查询结果-fetchOne()
		//当fetchOne()根据查询条件从数据库中查询到多条匹配数据时，会抛`NonUniqueResultException`。
		MemberDomain anotherFirstMember = queryFactory.selectFrom(qm).fetchOne();
		
		//查询条件示例
		List<MemberDomain> memberConditionList = queryFactory.selectFrom(qm)
				//like示例
				.where(qm.name.like('%'+"Jack"+'%')
						//contain示例
						.and(qm.address.contains("厦门"))
						//equal示例
						.and(qm.status.eq("0013"))
						//between
						.and(qm.age.between(20, 30)))				
				.fetch();
		//复杂条件组合示例
		BooleanBuilder builder = new BooleanBuilder();
		builder.and(qm.address.contains("厦门"));
		BooleanBuilder builder2 = new BooleanBuilder();
		builder2.or(qm.status.eq("0013"));
		builder2.or(qm.status.eq("0014"));
		builder.and(builder2);
		List<MemberDomain> memberComplexConditionList = queryFactory.selectFrom(qm).where(builder).fetch();
		
		//多表查询(left join)
		List<MemberDomain> leftJoinList = queryFactory.selectFrom(qm).leftJoin(qm.favoriteInfoDomains,qf).where(qf.favoriteStoreCode.eq("0721")).fetch();
		
		//聚合函数-avg()
		Double averageAge = queryFactory.select(qm.age.avg()).from(qm).fetchOne();
		//聚合函数-concat()
		String concat = queryFactory.select(qm.name.concat(qm.address)).from(qm).fetchFirst();
		//聚合函数-date_format()
		String date = queryFactory.select(Expressions.stringTemplate("DATE_FORMAT({0},'%Y-%m-%d')", qm.registerDate)).from(qm).fetchFirst();
		
		//子查询
		List<MemberDomain> subList = queryFactory.selectFrom(qm).where(qm.status.in(JPAExpressions.select(qm.status).from(qm))).fetch();
		
		//排序
		List<MemberDomain> orderList = queryFactory.selectFrom(qm).orderBy(qm.name.asc()).fetch();
	}
	
	@SuppressWarnings("unused")
	@Test
	public void springDataSelect(){
		QMemberDomain qm = QMemberDomain.memberDomain;
		
		//简单查询示例
		Iterable<MemberDomain> iterable = memberRepo.findAll(qm.status.eq("0013"));
		
		//使用BooleanBuilder
		BooleanBuilder builder = new BooleanBuilder();
		builder.and(qm.address.contains("厦门"));
		builder.and(qm.status.eq("0013"));
		Iterable<MemberDomain> iterable2 = memberRepo.findAll(builder);
		
		//查询一条数据
		MemberDomain member = memberRepo.findOne(qm.status.eq("0011"));
		
		//查询多条数据带排序
		//实现1
		OrderSpecifier<Integer> order = new OrderSpecifier<>(Order.DESC, qm.age);
		Iterable<MemberDomain> iterable3 = memberRepo.findAll(qm.status.eq("0013"),order);
		//实现2
		Sort sort = new Sort(new Sort.Order(Sort.Direction.ASC, "age"));
		memberRepo.findAll(qm.status.eq("0013"), sort);
		
		
		
	}
	
	@SuppressWarnings("unused")
	@Test
	public void QueryDSL分页的写法(){
		QMemberDomain qm = QMemberDomain.memberDomain;
		//写法一
		JPAQuery<MemberDomain> query = queryFactory.selectFrom(qm).orderBy(qm.age.asc());
		long total = query.fetchCount();//hfetchCount的时候上面的orderBy不会被执行
		List<MemberDomain> list0= query.offset(2).limit(5).fetch();
		//写法二
		QueryResults<MemberDomain> results = queryFactory.selectFrom(qm).orderBy(qm.age.asc()).offset(2).limit(5).fetchResults();
		List<MemberDomain> list = results.getResults();
		logger.debug("total:"+results.getTotal());
		logger.debug("limit:"+results.getLimit());
		logger.debug("offset:"+results.getOffset());
	}
	

}
