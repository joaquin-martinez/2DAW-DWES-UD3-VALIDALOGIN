<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
 %>

<jsp:useBean id="usuarioBean" class= "login.BeanUsuario" scope="session"  ></jsp:useBean>


	<form  action="validalogin" method="POST">
	
		<label for="login" >Introduzca usuario: </label>
		<input name="login" type="text" value="<jsp:getProperty property="login" name="usuarioBean"/>">

<br>
	
		<label for="clave" >Introduzca clave: </label>
		<input name="clave" type="password" value="<jsp:getProperty property="clave" name="usuarioBean"/>">

<br>
		
		<input name="control" type="hidden" value="login">
		<input name="enviar" type="submit" value="enviar">
		

	</form>

<br>	
<span><jsp:getProperty property="causaError" name="usuarioBean"/></span>	
	

</body>

</html>