
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
    <%@page import="java.net.URLEncoder"%>
    <%@page import="myutil.MultiPart" %>
    <%@page import="java.net.URLEncoder.*" %>
    
    
<% 

MultiPart multiPart = new MultiPart(request);

String title = multiPart.getParameter("TITLE");
String description = multiPart.getParameter("DESCRIPTION");

String fileName = multiPart.getFileName("UPLOAD_FILE");
String newPart = application.getRealPath("/files/" + fileName);
multiPart.saveFile("UPLOAD_FILE", newPart);

String uri = String.format("UploadResult.jsp?TITLE=%s&DESCRIPTION=%s&FILE_NAME=%s",
URLEncoder.encode(title,"utf-8"),
URLEncoder.encode(description,"utf-8"),
URLEncoder.encode(fileName,"utf-8"));

response.sendRedirect(uri);
%>
