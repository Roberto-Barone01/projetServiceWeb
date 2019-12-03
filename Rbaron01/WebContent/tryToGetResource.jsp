<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1> Qui provo ad assegnare una risorsa ad un user</h1>

<% 
	String value = request.getParameter("id_book");
	if( value != null )
		out.println(value);
	else
		out.println("nothig");
%>

</body>
</html>