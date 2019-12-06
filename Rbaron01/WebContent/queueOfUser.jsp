<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="upem.client.ClientOperation, upem.shared.interfaces.UpemServiceRequestable, upem.shared.interfaces.UpemServiceRequestable.* " %>
<%@page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel='Stylesheet' href='css/header.css'>
<link rel='Stylesheet' href='css/books.css'>
</head>
<body>
<jsp:include page='/header.jsp'></jsp:include>

<div class='flex-conteiner'>


<%
	if(request.getParameter("meta") != null){
		int meta = Integer.parseInt(request.getParameter("meta"));
		
		if(meta == 0){
			int id_book = Integer.parseInt(request.getParameter("id_book"));
			ClientOperation.deleteMyQueue(false, id_book);
		}else{
			int id_meta = Integer.parseInt(request.getParameter("id_meta"));
			ClientOperation.deleteMyQueue(true, id_meta);
		}
	}
		

%>

<% ArrayList<SingleRow> ris = ClientOperation.queueUser(false).result();
			for(int i=0;i<ris.size();i++){
				out.println("<div class='flex-item'>");
				out.println("<h1>book</h1>");
				out.println("<div class='flex-sub-item'>Title: "+ris.get(i).get().get("title")+"</div>" );
				out.println("<div class='flex-sub-item'>Edition: "+ris.get(i).get().get("edition")+"</div>" );
				out.println("<div class='flex-sub-item'>Date: "+ris.get(i).get().get("date")+"</div>" ); 
				//out.println(ris);
		%>
		<form action='queueOfUser.jsp'>
			<input type='submit' value='supprime'>
			<input  type='hidden' align='center' value='<% out.print(ris.get(i).get().get("id")); %>' readonly='readonly' name='id_book'>
			<input  type='hidden' align='center' value='0' readonly='readonly' name='meta'>
		</form>
		</div>
		<%	
				out.println("<br>");
			}
		%>
		
		<%  ris = ClientOperation.queueUser(true).result();
			for(int i=0;i<ris.size();i++){
				
				out.println("<div class='flex-item'>");
				out.println("<h1>Meta</h1>");
				out.println("<div class='flex-sub-item'>Name: "+ris.get(i).get().get("meta_name")+"</div>" );
				out.println("<div class='flex-sub-item'>Type: "+ris.get(i).get().get("meta_type")+"</div>" );
				out.println("<div class='flex-sub-item'>Date: "+ris.get(i).get().get("date")+"</div>" );
		%>
		<form action='queueOfUser.jsp'>
			<input type='submit' value='supprime'>
			<input  type='hidden' align='center' value='<% out.print(ris.get(i).get().get("id")); %>' readonly='readonly' name='id_meta'>
			<input  type='hidden' align='center' value='1' readonly='readonly' name='meta'>
		</form>
		</div>
		<%	
				out.println("<br>");
			}
		%>
</div>
</body>
</html>