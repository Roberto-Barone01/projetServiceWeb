<%@page import="upem.client.ClientOperation, upem.shared.interfaces.UpemServiceRequestable, upem.shared.interfaces.UpemServiceRequestable.* " %>
<%@page import="java.util.ArrayList" %>

<div class='header-flex'>
	<div class ='header-item'>
		<% out.println("Welcome " + ClientOperation.user().get("username")); %>
	</div>
</div>