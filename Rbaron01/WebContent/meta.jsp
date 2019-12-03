<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@page import="upem.client.ClientOperation, upem.shared.interfaces.UpemServiceRequestable, upem.shared.interfaces.UpemServiceRequestable.* " %>
<%@page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
out.print(ClientOperation.user());
%>

<h1> Qui inserisco tutte le mie risorse meta</h1>

	<div class="flex-conteiner">
		
		<% ArrayList<SingleRow> ris = ClientOperation.meta().result();
			for(int i=0;i<ris.size();i++){
				out.println("<div class='flex-item'>");
				out.println(ris.get(i));
		%>
		<form action='tryToGetBook'>
			<input type='text' value='<% out.print(ris.get(i).get().get("id")); %>' readonly='readonly'>
			<input type='submit' value='emprunte'>
		</form>
		</div>
		<%	
				out.println("<br>");
			}
		%>

	</div>
</body>
</html>