<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Resumen del login. </title>
</head>
<body>

<jsp:useBean id="usuarioBean" class= "login.BeanUsuario" scope="session"  ></jsp:useBean>

<p>
<jsp:getProperty property="nombre" name="usuarioBean"/> 
</p>


<p>
<jsp:getProperty property="login" name="usuarioBean"/> 
</p>

<p>
<jsp:getProperty property="clave" name="usuarioBean"/> 
</p>


</body>
</html>