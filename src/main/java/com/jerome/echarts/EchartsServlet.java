package com.jerome.echarts;

import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @ClassName: EchartsServlet
 * @Description: echarts后台交互实例
 * @author jerome
 * @date 2015年9月30日 下午3:45:44
 * @see 参考 http://echarts.baidu.com/doc/start.html 
 * 需要jquery-1.7.2.js / json-lib-2.4-jdk15.jar
 * echarts.jsp
 * pom
	<dependency>
	  <groupId>net.sf.json-lib</groupId>
	  <artifactId>json-lib</artifactId>
	  <version>2.4</version>
	  <classifier>jdk15</classifier>
	</dependency>
 */
@WebServlet("/echartsServlet")
public class EchartsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EchartsServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 组装数据
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("legend", Arrays.asList(new String[] { "销售认购金额","销售认购金额2"}));
		map.put("category", Arrays.asList(new String[] { "年度认购金额", "年度认购指数", "全案认购金额", "全案认购指数" }));
		map.put("series", Arrays.asList(
				new ArrayList<Integer>(Arrays.asList(21,23,28,26)),
				new ArrayList<Integer>(Arrays.asList(25,22,21,21))
				));
		JSONObject json = JSONObject.fromObject(map);
		System.out.println(json.toString());

		// 数据返回前端显示
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer = response.getWriter();
		writer.print(json);
		writer.flush();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
