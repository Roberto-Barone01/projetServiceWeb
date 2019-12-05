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
<title>Insert title here</title>
</head>
<body>


<jsp:include page='/header.jsp'></jsp:include>

	<%
		if( request.getParameter("name") != null && request.getParameter("type") != null){
			String name = request.getParameter("name");
			String type = request.getParameter("type");
			String comment = request.getParameter("comment");
			String state = request.getParameter("state");
			out.println("<h1 align='center'>ADDED</h1>");
			ClientOperation.addMeta(name, type, comment, state);
		}
			
	%>

	<div class='flex-conteiner'>
	<form action='addMeta.jsp'>
		<div class='flex-item'>
		Name: <input type='text' name='name'>
		</div>
		<div class='flex-item'>
		Type: <input type='text' name='type'>
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
</body>
</html>