<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<a href="echarts.jsp">echarts图表demo</a><br/><br/>
<!-- <a href="exportExcel">导入excel demo</a><br/> -->
<form action="importExcel" method="post" enctype="multipart/form-data">
	选择文件:<input type="file" name="file"><br> 
	<input type="submit" value="提交">
</form><br/><br/>
<a href="exportExcel">导出excel demo</a><br/><br/>
</body>
</html>