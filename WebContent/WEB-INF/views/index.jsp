<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<html>
<head>
<style type="text/css">
body {
	background-image: url('https://cdn.crunchify.com/bg.png');
}

</style>
</head>
<body>
<p>The time on the server is ${serverTime}.</p>
  
  
  
  <h1 align="center"> <font color="red">UNIVERSITY CLASS SCHEDULER</font></h1>

<br><br><br><br><br>
 
<form method="get" action = "CSchedule">

<table border="2.0" align="center">
<tr><th>
<h2><font color="purple">SELECT SEMESTER  :</font></h2></th><th>
<select >
<option >spring semester</option>
<option >fall semester</option>
<option > summer semester</option>
</select></th>
 <th><input type = "submit" value = "submit" ></th></tr>
</table><br><br>
<table align="center">
<tr>
<th>spring<br>
<span style="padding-left:4em"><img alt="" src="file:///Users/amulyachennaboyena/Downloads/spring-mvc-example/WebContent/resources/spring.jpg" width="100" height ="100">
</span></th>
<th>summer<br>
<span style="padding-left:4em"><img alt="" src="file:///Users/amulyachennaboyena/Downloads/spring-mvc-example/WebContent/resources/summer.jpg" width="100" height ="100">
</span></th>
<th>fall<br>
<span style="padding-left:4em"><img alt="" src="file:///Users/amulyachennaboyena/Downloads/spring-mvc-example/WebContent/resources/fall.jpg" width="100" height ="100">
</span></th>

</tr></table></form>
</body>
</html>