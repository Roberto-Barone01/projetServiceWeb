<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@page import="upem.client.ClientOperation, upem.shared.interfaces.UpemServiceRequestable, upem.shared.interfaces.UpemServiceRequestable.* " %>
<%@page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<link rel='Stylesheet' href='css/header.css'>
<link rel='Stylesheet' href='css/books.css'>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:include page='/header.jsp'></jsp:include>

	<div class="flex-conteiner">
		
		<% ArrayList<SingleRow> ris = ClientOperation.books().result();
			for(int i=0;i<ris.size();i++){
				out.println("<div class='flex-item'>");
				out.println("<div class='flex-sub-item'>Title: "+ris.get(i).get().get("title")+"</div>" );
				out.println("<div class='flex-sub-item'>Publisher: "+ris.get(i).get().get("publisher")+"</div>" );
				out.println("<div class='flex-sub-item'>Edition: "+ris.get(i).get().get("edition")+"</div>" );
				out.println("<div class='flex-sub-item'>ISBN: "+ris.get(i).get().get("isbn")+"</div>" );
				out.println("<div class='flex-sub-item'>Pages: "+ris.get(i).get().get("pages")+"</div>" );
				out.println("<div class='flex-sub-item'>Year: "+ris.get(i).get().get("year")+"</div>" );
				out.println("<div class='flex-sub-item'>Comment: "+ris.get(i).get().get("comment")+"</div>" );
				out.println("<div class='flex-sub-item'>State: "+ris.get(i).get().get("state")+"</div>" );
		%>
		<form action='tryToGetResource.jsp'>
			<input type='submit' value='emprunte'>
			<input  type='hidden' align='center' value='<% out.print(ris.get(i).get().get("id")); %>' readonly='readonly' name='id_book'>
			<input  type='hidden' align='center' value='0' readonly='readonly' name='meta'>
		</form>
		</div>
		<%	
				out.println("<br>");
			}
		%>

	</div>
</body>
</html>