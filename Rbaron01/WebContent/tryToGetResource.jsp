<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="upem.client.ClientOperation, upem.shared.interfaces.UpemServiceRequestable, upem.shared.interfaces.UpemServiceRequestable.* " %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel='Stylesheet' href='css/header.css'>
</head>
<body>
<jsp:include page='/header.jsp'></jsp:include>
<% 
	
	String meta = request.getParameter("meta");
	if(meta != null){
		
		int isMeta = Integer.parseInt(meta);
		if(isMeta == 0){
			String value = request.getParameter("id_book");
			out.println("Code Message "+ClientOperation.tryToGetBook(Integer.parseInt(value)).code());
		}else{
			String value = request.getParameter("id_meta");
			out.println("Code Message "+ClientOperation.tryToGetMeta(Integer.parseInt(value)).code());
		}
		
	}
	else
		out.println("nothig");
%>

</body>
</html>