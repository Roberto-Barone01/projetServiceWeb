<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="upem.client.ClientOperation, upem.shared.interfaces.UpemServiceRequestable, upem.shared.interfaces.UpemServiceRequestable.* " %>
<%@page import="java.util.ArrayList" %>

<!DOCTYPE html>
<html>
<head>
<link rel='Stylesheet' href='css/header.css'>
<link rel='Stylesheet' href='css/add.css'>
<meta charset="UTF-8">
<title>Add book</title>
</head>
<body>


<jsp:include page='/header.jsp'></jsp:include>

	<%
		if( request.getParameter("title") != null && request.getParameter("isbn") != null){
			String title = request.getParameter("title");
			String publisher = request.getParameter("publisher");
			String edition = request.getParameter("edition");
			String isbn = request.getParameter("isbn");
			String pages = request.getParameter("pages");
			String year = request.getParameter("year");
			String comment = request.getParameter("comment");
			String state = request.getParameter("state");
			out.println("<h1 align='center'>ADDED</h1>");
			ClientOperation.addBook(title, publisher, edition, isbn, pages, year, comment, state);
		}
	
	%>

	<div class='flex-conteiner'>
	<form action='addBook.jsp'>
		<div class='flex-item'>
		Title: <input type='text' name='title'>
		</div>
		<div class='flex-item'>
		Publisher: <input type='text' name='publisher'>
		</div>
		<div class='flex-item'>
		Edition:<input type='text' name='edition'>
		</div>
		<div class='flex-item'>
		ISBN:<input type='text' name='isbn'>
		</div>
		<div class='flex-item'>
		Pages:<input type='number'  min='1' max= '10000' name='pages'>
		</div>
		<div class='flex-item'>
		Year:<input type='text' name='year'>
		</div>
		<div class='flex-item'>
		Comment:<input type='text' name='comment'>
		</div>
		<div class='flex-item'>
		State:
			<input type="radio" name="state" value="new">new </> 
			<input type="radio" name="state" value="used">used</>
		</div>
		<input class="submit" type='submit' align='center' value='envoye'>
	</form>
	</div>

</body>
</html>