<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ORACLE 데이터베이스 연결 테스트</title>
</head>

<body>
	<h3>ORACLE 데이터베이스 연결 테스트</h3>
	<%
	 	String uri = "jdbc:oracle:thin:@localhost:1521:orcl";
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","antman","12345");
		if (conn != null) {
			out.println("ORACLE 데이터베이스로 연결했습니다.<br>");
			conn.close();
			out.println("ORACLE 데이터베이스로의 연결을 끊었습니다.<br>");
		} else {
			out.println("ORACLE 데이터베이스로 연결을 할수 없습니다.<br>");
		}
	%>

</body>
</html>