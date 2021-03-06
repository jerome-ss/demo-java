package com.jerome.common.mongo;

import com.jerome.common.util.MD5Utils;
import org.bson.Document;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
public class TestMongo {


	@Before
	public void setUp() throws Exception {
		//启动初始化MongoDB
		MongoUtilFactory.getMongoUtil1().init();
		MongoUtilFactory.getMongoUtil2().init();
	}

	@After
	public void destory() throws Exception{
		//关闭MongoDB资源
		MongoUtilFactory.getMongoUtil1().close();
		MongoUtilFactory.getMongoUtil2().close();
	}

	@Test
	public void test_UpdateOrInsertOne() {
		Document document = new Document();
		document.put("login_name", "jerome");
		document.put("phone_number", "15059890666");
		document.put("age", 26);
		document.put("sex", "男1");
		document.put("email", "xxx@139.com");
		Document where = new Document();
		where.put("login_name", "jerome");
		MongoUtilFactory.getMongoUtil1().updateOrInsertOne("sys_user", where, document);
//		MongoUtilFactory.getMongoUtil2().updateOrInsertOne("sys_user", where, document);
	}

	@Test
	public void test_UpdateOrInsertMany(){
		Document document = new Document();
		document.put("login_name", "jerome");
		document.put("is_open", 1);
		Document where = new Document();
		where.put("login_name", "jerome");
		// MongoUtil.updateOrInsertMany("wlw_open_door_log", where, document);
		MongoUtilFactory.getMongoUtil1().updateOrInsertMany("sys_user", where, document);
		MongoUtilFactory.getMongoUtil2().updateOrInsertMany("sys_user", where, document);
	}

	@Test
	public void test_Insert() {
		Document document = new Document();
		document.put("login_name", "jerome");
		document.put("age", 27);
		// MongoUtil.insert("sys_user", document);
		MongoUtilFactory.getMongoUtil1().insert("sys_user", document);
		MongoUtilFactory.getMongoUtil2().insert("sys_user", document);
	}

	@Test
	public void test_find() {
		Document where = new Document();
		where.put("serial_number", "cf004fdc76fa1a4f62e0eb5261ca3");
		Document sort = new Document();
		sort.put("$natural", -1);
		List<Document> docList = MongoUtilFactory.getMongoUtil1().find("wlw_eq_hblog", where, sort, 10, 0);
		for(Document doc : docList){
			System.out.println(doc);
		}
	}

	@Test
	public void test_drop() {
		String collectionName = "wlw_test_001";
		MongoUtilFactory.getMongoUtil1().dropCollection(collectionName );
	}

	/**
	 * 测试固定集合
	 */
	@Test
	public void test_cappedCollection() {
		String collectionName = "wlw_eq_hblog";
		List<Document> documentList = new ArrayList<>();
		Random random = new Random();
		for (int i = 0; i < 100; i++) {
			Document document = new Document();
			document.put("serial_number", MD5Utils.getStringMD5(String.valueOf(random.nextInt(1000))));
			document.put("eq_ip", "10.91.80.21");
			document.put("eq_port", 58047);
			document.put("is_success", 1);
			document.put("switch_ip", "10.91.80.21");
			document.put("switch_port", 5010);
			document.put("log_time", new Date());
			documentList.add(document);
			if(i % 10000 == 0){
				MongoUtilFactory.getMongoUtil1().insertMany(collectionName, documentList);
				documentList.clear();
				System.out.println("已经插入 " + i + " 条");
			}
		}
	}

	/**
	 * 测试聚合函数（aggregate）据统计分析
	 */
	@Test
	public void test_aggregate() {
		// https://docs.mongodb.org/getting-started/java/aggregation/
		/*db.wlw_card_open_door_log.aggregate({
			$match : {
				type : 1, // 卡开门日志
				occur_time : {
					$lte : ISODate('2016-03-09'),
					$gt : ISODate('2016-02-04')
				}
			}
		}, {
			$group : {
				// 分组字段
				_id : {
					community_id : "$community_id",
					city_id : "$city_id",
					property_id : "$property_id",
					community_id : "$community_id",
					community : "$community"
				},

				count : {
					$sum : 1
				},
				// sum is_success
				sum_is_success : {
					$sum : "$is_success"
				}
			}
		}, {
			$sort : {
				// 倒序排序
				count : -1
			}
		}, {
			$skip : 2
		}, {
			$limit : 10
		});*/

		// 查询条件
		List<Document> docs = new ArrayList<>();

		// matchMap
		Map<String, Object> eqMatchMap = new HashMap<>();
		eqMatchMap.put("$lt", "2016-04-26 14:44:55");
		eqMatchMap.put("$gte", "2015-04-26 14:44:52");
		Document matchGroup = new Document("$match",new Document("type", 1).append("occur_time", eqMatchMap));

		// groupIdsMap
		Map<String, Object> groupIdsMap = new HashMap<>();
		groupIdsMap.put("community_id", "$community_id");
		groupIdsMap.put("city_id", "$city_id");
		groupIdsMap.put("property_id", "$property_id");
		groupIdsMap.put("community", "$community");
		Document docGroup = new Document("$group",
				new Document("_id", new Document(groupIdsMap)).append("count", new Document("$sum", 1))
						.append("sum_is_success", new Document("$sum", "$is_success")));

		// project
		Document docProject = new Document("$project",
				new Document("count",1));
		Document sortGroup = new Document("$sort", new Document("count", -1));
		Document skipGroup = new Document("$skip", 2);
		Document limitGroup = new Document("$limit", 10);

		docs.add(matchGroup);
		docs.add(docGroup);
		docs.add(sortGroup);
		docs.add(skipGroup);
		docs.add(limitGroup);
		docs.add(docProject);
		List<Document> findByAggregate = MongoUtilFactory.getMongoUtil1().findByAggregate("wlw_card_open_door_log", docs);
		for (Document document : findByAggregate) {
			System.out.println(document);
//			long test = Long.parseLong(document.get("count").toString());
//			long count = (long) document.get("count");
//			long sum_is_success = (long) document.get("sum_is_success");
		}
	}
	
	/**
	 * Hadoop
	 */
	@Test
	public void test_mongoHadoop() {
		
	}
	
}
