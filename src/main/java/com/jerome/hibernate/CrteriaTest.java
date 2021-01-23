package com.jerome.hibernate;
//
//import java.util.List;
//
//import javassist.bytecode.Descriptor.Iterator;
//
//import org.hibernate.Criteria;
//import org.hibernate.Query;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.criterion.Criterion;
//import org.hibernate.criterion.Order;
//import org.hibernate.criterion.Projection;
//import org.hibernate.criterion.Projections;
//import org.hibernate.criterion.Restrictions;
//import org.hibernate.transform.AliasToBeanResultTransformer;
//import org.hibernate.transform.Transformers;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.health.business.domain.organization.Company;
//import com.health.business.domain.policy.InsurancePolicy;
//import com.health.business.domain.product.component.InsuranceCompany;
//import com.health.business.domain.security.EmpUser;
//import com.health.business.domain.security.EmpUserImpl;
//import com.sun.javafx.collections.MappingChange.Map;
//
///**
// * Hibernate - Crteria学习
// * @author jerome
// *
// */
//@RunWith(SpringJUnit4ClassRunner.class)  
//@ContextConfiguration(locations = { "classpath:*spring/applicationContext*.xml" })  
//@Transactional
//@SuppressWarnings({ "unchecked", "unused" })
public class CrteriaTest {
//	
//	@Autowired
//	protected SessionFactory sessionFactory;
//	
//	Session session = null;
//	@Before
//	public void setSession(){
//		session = sessionFactory.getCurrentSession();
//	}
//	
//	// Hibernate拥有完全的ORM(对象关系映射)理念，我们在操作数据库时，可以通过面向对象的方式就可以完成数据库的CRUD
//	// 在使用Hibernate主要基于HQL（Hibernate 面向对象的查询语言，语法类似SQL）、 Criteria（面向对象的条件查询对象）、SQL（原生态SQL语句）
//	
//	// 当session.createCriteria(实体类.class) 就会产生一条select * from 表的SQL语句，
//	// 查询实体类对应数据表的所有记录，然后我们就可以在这个Criteria对象上进行条件查询、分页查询、多表关联查询、投影查询、子查询等一系列操作
//    @Test  
//    public void testFindAll() throws Exception {  
//    	Criteria criteria = session.createCriteria(Company.class);
//		List<Company> companyList = criteria.list();
////        select 
////        	...
////        from
////        HM_COMPANY this_
//    }  
//    
//    
//    //org.hibernate.criterion.Criterion是Hibernate提供的一个面向对象查询条件接口，
//    //一个单独的查询就是Criterion接口的一个实例，用于限制Criteria对象的查询，
//    //在Hibernate中Criterion对象的创建通常是通过Restrictions 工厂类完成的。
//    @Test  
//    public void testFindByRestriction() throws Exception {  
//    	Criteria criteria = session.createCriteria(Company.class);
//    	criteria.add(Restrictions.like("name", "%发%")); // gt lt ..
//    	List<Company> companyList = criteria.list();
////        select 
////        	...
////        from
////        	HM_COMPANY this_
////        where
////       	this_.name like ?
//    }  
//
////    Restrictions的方法有
////    Restrictions.eq = 
////    Restrictions.gt >
////    Restrictions.ge >=
////    Restrictions.lt <
////    Restrictions.le <=
////    Restrictions.between
////    Restrictions.like
////    Restrictions.in
////    Restrictions.and
////    Restrictions.or
////    ...
//    
//    //多个查询条件
//    @Test  
//    public void testFindByRestrictions() throws Exception {  
//    	Criteria criteria = session.createCriteria(Company.class);
//    	Criterion criterion1 = Restrictions.like("contactTel", "15%");
//    	Criterion criterion2 = Restrictions.le("oid", 14974979l);
//    	criteria.add(Restrictions.and(criterion1, criterion2)); 
//    	List<Company> companyList = criteria.list();
//    	
//    	for (Company company : companyList) {
//			System.out.println(company.getName());
//		}
////        select 
////        	...
////        from
////        	HM_COMPANY this_
////        where
////        (
////            this_.contactTel like ? 
////            and this_.oid<=?
////        )
//    }  
//    
//    //分页操作 排序操作 等
//    @Test  
//    public void testFindByPage() throws Exception {  
//    	Criteria criteria = session.createCriteria(Company.class);
//    	
//    	criteria.setFirstResult(0); // 从第0条开始读取
//    	criteria.setMaxResults(3); // 读取3条
//    	
//    	criteria.addOrder(Order.asc("oid")); // 排序
//    	
//    	List<Company> companyList = criteria.list();
//    	
//    	for (Company company : companyList) {
//			System.out.println(company.getName());
//		}
////        select 
////        	...
////        from
////       	 HM_COMPANY this_ 
////   	  order by
////       	 this_.oid asc limit ?
//    }  
//    
//    //重点：多表关联操作 createAlias和createCriteria 
//    
//    @Test  
//    public void testFindByCreateCriteria() throws Exception {  
////    	Criteria criteria = session.createCriteria(EmpUser.class);
////    	Criteria criteria2 = criteria.createCriteria("company");
////    	criteria2.add(Restrictions.like("name","建发%"));
////    	List<EmpUser> empUserList = criteria2.list();
//    	
//		List<EmpUser> empUserList = session.createCriteria(EmpUser.class)
//				.createCriteria("company").add(Restrictions.like("name", "建%"))
//				.list();
//    	
//    	for (EmpUser empUser : empUserList) {
//			System.out.println(empUser.getName());
//		}
////        select 
////        	...
////        from
////       	 HM_COMPANY this_ 
////   	  ...
////        inner join
////       	 HM_COMPANY companyimp1_ 
////            on this_.companyId=companyimp1_.oid 
////    	   where
////        	companyimp1_.name like ?
//    }  
//    
//    @Test  
//    public void testFindByCreateAlias() throws Exception {  
//    	Criteria criteria = session.createCriteria(EmpUser.class);
//    	criteria.createAlias("company", "c");
//    	criteria.add(Restrictions.like("c.name","建%"));
//    	List<EmpUser> empUserList = criteria.list();
//    	
//    	for (EmpUser empUser : empUserList) {
//    		System.out.println(empUser.getName());
//    	}	
////        select 
////        	...
////        from
////       	 HM_COMPANY this_ 
////   	  ...
////        inner join
////       	 HM_COMPANY c1_ 
////            on this_.companyId=c1_.oid 
////   	  where
////      	  c1_.name like ?
//    }  
//    
//    
//    //3.5.投影(查询指定属性)、分组查询 Projection 
//    // org.hibernate.criterion.Projections工厂类用于返回Projection投影查询对象。
//    @Test  
//    public void testFindByProjections() throws Exception {  
//    	Criteria criteria = session.createCriteria(Company.class);
//		criteria.setProjection(Projections.projectionList()
//				.add(Projections.property("name"))
//				.add(Projections.property("oid")));
//    	List<Company> companyList = criteria.list();
//    	
////    select
////        this_.name as y0_,
////        this_.oid as y1_ 
////    from
////        HM_COMPANY this_
//    }  
//    
////     Projections提供了分组函数的查询方法： 
////	   rowCount() 查询记录总数量；
////	   count(String propertyName) 统计某列数量；
////	   countDistinct(String propertyName) 统计某列数量（排除重复）；
////	   avg(String propertyName) 统计某列平均值； 
////	   sum(String propertyName) 对某列值求和；
////	   max(String propertyName) 求某列最大值； 
////	   min(String propertyName) 求某列最小值。
//    
//    // 查询公司的总数量
//    @Test  
//    public void testCountCompanys() throws Exception {  
//    	Criteria criteria = session.createCriteria(Company.class);
//		criteria.setProjection(Projections.rowCount());
//		Long totalCount = (Long) criteria.uniqueResult();
//		
//		System.out.println(totalCount);
////	    select
////        count(*) as y0_ 
////    	from
////        HM_COMPANY this_
//    }  
//    
//    // groupProperty(String propertyNam-e)用于执行分组操作
//    // 查询每个公司的员工数量
//    @Test  
//    public void testGrooup() throws Exception {  
//    	Criteria criteria = session.createCriteria(EmpUserImpl.class);
//		criteria.setProjection(Projections.projectionList()
//				.add(Projections.groupProperty("company"))
//				.add(Projections.count("oid")));
//		List<Object[]> list = criteria.list();
//		
//		for (Object[] object : list) {
//			System.out.println(object[1]);
//		}
//		
////	  select
////        this_.companyId as y0_,
////        count(this_.oid) as y1_ 
////    from
////        HM_EMPUSER this_ 
////    inner join
////        HM_PERSON this_1_ 
////            on this_.oid=this_1_.oid 
////    inner join
////        HM_SE_USER this_2_ 
////            on this_.oid=this_2_.oid 
////    group by
////        this_.companyId
//    	
//    }
//    
//    @Test  
//	public void testResultTransformer() {
//		//下面两个的功能是一样的，但是第一个返回的是List<Object> 
//    	//第二个返回的是List<EmpUserImpl> why?
//    	Criteria criteria = session.createCriteria(EmpUserImpl.class);
//		criteria.setProjection(Projections.projectionList()
//				.add(Projections.groupProperty("company"))
//				.add(Projections.count("oid")));
//		List<Object> list = criteria.list();
//		
////		String hql = "from "+EmpUserImpl.class.getName() +" group by company";
////		Query queryObject = session.createQuery(hql);
////		List<EmpUserImpl> list2 = queryObject.list();
//		
//		
//		//刚刚说了投影操作的使用，其实在Hibernate内部投影查询是会影响到结果集的封装策略的
////		session.createCriteria(EmpUserImpl.class).list();  返回 List <EmpUserImpl>
////		session.createCriteria(EmpUserImpl.class).setProjection(
////		Projections.projectionList()
////		.add(Property.forName(”name”))
////		.add(Property.forName(”age”))); 返回 List
//
////		投影之后，返回的结果将不再被封装到EmpUserImpl对象中
////		ResultTransformer结果集转换策略接口，在Criteria的父接口中CriteriaSpecification定义了几个ResultTransformer的常用实现。 
////
////		ALIAS_TO_ENTITY_MAP 将结果集封装到Map对象； 
////		ROOT_ENTITY 将结果集封装到根实体对象；
////		DISTINCT_ROOT_ENTITY 将结果集封装到不重复的根实体对象；
////		PROJECTION 根据投影的结果类型自动封装； 
////
////		当进行投影查询时，结果的封装策略由ROOT_ENTITY 变为了PROJECTION， 所以是根据查询数据进行封装，而不是封装到根对象。
////		了解上面原理后，即使只查询name和age属性，也可以封装成List集合。
////		使用AliasToBeanResultTransformer 修改结果封装策略，AliasToBeanResultTransformer 会根据返回列自动匹配类中属性名，完成封装。
//    	/*Criteria criteria = session.createCriteria(EmpUserImpl.class);
//		criteria.setProjection(Projections.projectionList()
//				.add(Projections.groupProperty("company").as("company"))
//				.add(Projections.property("name").as("name"))
//				.add(Projections.count("oid")));
//		criteria.setResultTransformer(Transformers.aliasToBean(EmpUserImpl.class));
//		List<EmpUserImpl> list = criteria.list();
//		for (EmpUserImpl empUserImpl : list) {
//			System.out.println(empUserImpl.getName());
//		}*/
//		
//	}  
//
//    // html_single/index.html  第 17 章 条件查询（Criteria Queries）
}
