<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page
	import="upem.client.ClientOperation, upem.shared.interfaces.UpemServiceRequestable, upem.shared.interfaces.UpemServiceRequestable.* "%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<link rel='Stylesheet' href='css/header.css'>
<link rel='Stylesheet' href='css/books.css'>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<%
	if(request.getParameter("meta") != null){
		
		int meta = Integer.parseInt(request.getParameter("meta"));
		if(meta == 0){
			int id_book = Integer.parseInt(request.getParameter("id_book"));
			ClientOperation.returnResource(false, id_book);
		}else{
			int id_meta = Integer.parseInt(request.getParameter("id_meta"));
			ClientOperation.returnResource(true, id_meta);
		}
			
		
	}
%>


<body>
	<jsp:include page='/header.jsp'></jsp:include>
	<h1 align='center'>Les objects que tu as emprunté:</h1>

	<div class="flex-conteiner">

		<%
			ArrayList<SingleRow> ris = ClientOperation.objectsBorrowed(false).result();
			for (int i = 0; i < ris.size(); i++) {
				out.println("<div class='flex-item'>");
				out.println("<h1>book</h1>");
				out.println("<div class='flex-sub-item'>Title: " + ris.get(i).get().get("title") + "</div>");
				out.println("<div class='flex-sub-item'>Edition: " + ris.get(i).get().get("edition") + "</div>");
				out.println("<div class='flex-sub-item'>Date: " + ris.get(i).get().get("date") + "</div>");
				//out.println(ris);
		%>
		<form action='borrowed.jsp'>
			<input type='submit' value='retourne'> <input type='hidden'
				align='center' value='<%out.print(ris.get(i).get().get("id"));%>'
				readonly='readonly' name='id_book'>
			<input type='hidden' align='center' value='0' readonly='readonly' name='meta'>
		</form>
	</div>
	<%
		out.println("<br>");
		}
	%>

	<%
		ris = ClientOperation.objectsBorrowed(true).result();
		for (int i = 0; i < ris.size(); i++) {

			out.println("<div class='flex-item'>");
			out.println("<h1>Meta</h1>");
			out.println("<div class='flex-sub-item'>Name: " + ris.get(i).get().get("meta_name") + "</div>");
			out.println("<div class='flex-sub-item'>Type: " + ris.get(i).get().get("meta_type") + "</div>");
			out.println("<div class='flex-sub-item'>Date: " + ris.get(i).get().get("date") + "</div>");
	%>
	<form action='borrowed.jsp'>
		<input type='submit' value='retourne'> <input type='hidden'
			align='center' value='<%out.print(ris.get(i).get().get("id"));%>'
			readonly='readonly' name='id_meta'> <input type='hidden'
			align='center' value='1' readonly='readonly' name='meta'>
	</form>
	</div>
	<%
		out.println("<br>");
		}
	%>
	</div>
</body>
</html>