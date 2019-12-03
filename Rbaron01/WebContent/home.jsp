<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel='Stylesheet' href='css/header.css'>
<link rel='Stylesheet' href='css/home.css'>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://fonts.googleapis.com/css?family=Open+Sans&display=swap" rel="stylesheet">
</head>
<body>
<jsp:include page='/header.jsp'></jsp:include>
<h1 align="center"> Mes opérations</h1>
<div class="flex-container">
	<div class="flex-list">
		<div class="flex-item"><a  href="books.jsp"> show all books</a> </div>
		<div class="flex-item"> show all meta resource</div>
		<div class="flex-item"> show my resources</div>
		<div class="flex-item"> show my queue</div>
		<div class="flex-item"> Objets empruntés</div>
		<div class="flex-item"> Add book</div>
		<div class="flex-item"> Add meta</div>
	</div>
</div>
</body>
</html>