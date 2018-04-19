<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@page import="com.journaldev.spring.controller.*"%>
<%@page import="com.journaldev.spring.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List"%>
<%@ page import="java.sql.Date"%>
<%@ page import="java.io.*"%>
<html>
<head>
<style type="text/css">
body {
	background-image: url('https://cdn.crunchify.com/bg.png');
}
table {
    width:100%;
}
table, th, td {
    border: 1px solid black;
    border-collapse: collapse;
}
th, td {
    padding: 15px;
    text-align: left;
}
table#t01 tr:nth-child(even) {
    background-color: #eee;
}
table#t01 tr:nth-child(odd) {
   background-color: #fff;
}
table#t01 th {
    background-color: black;
    color: white;
}
</style>
</head>
<body>

${date}
<form method="get" action = "CancelClass">
          <input type = 'text' name ="date" > 
          <input type = "submit" value ="cancel">
</form>
          
<form method="get" action = "IncompleteTopics">
          <input type = 'text' name ="date" > 
          <input type = "submit" value ="incomplete">




<table id="t01" border="2.0">
   <tr>
        <th>user ID</th>
        <th>topics</th>
       
       
   </tr>
   <%
   try
   {
       Class.forName("com.mysql.jdbc.Driver");
       String url="jdbc:mysql://localhost:3306/ASE";
       String username="amulya";
       String password="password";
       String query="select * from ASE.scheduler";
       Connection conn=DriverManager.getConnection(url, username, password);
       Statement stmt=conn.createStatement();
       ResultSet rs=stmt.executeQuery(query);
       while(rs.next())
       {
   %>
           <tr>
           
           <td><%out.println(rs.getDate(1)); %></td>
          <td><%out.println(rs.getString(2)); %></td>
        
          
          </tr>
          
          
   <%
       }
       %>

</table>
  
   <%
        rs.close();
        stmt.close();
        conn.close();
   }
   catch(Exception e)
   {
        e.printStackTrace();
   }
   %>
</form>
</body>
</html>