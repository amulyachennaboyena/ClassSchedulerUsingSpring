<%@page import="com.journaldev.spring.controller.*"%>
<%@page import="com.journaldev.spring.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List"%>
<%@ page import="java.sql.Date"%>
<%@ page import="java.io.*"%>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

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
<!-- <table border = "2.0"> -->
<!-- <tr><th>date</th> -->
<!-- <th>topics</th></tr> -->
<%java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd"); %>

<%


String date = request.getParameter("date");

Date date1 = Date.valueOf(date);


ScheduleData sd = new ScheduleData();

DBConnection db = new DBConnection();

 

   %>

<% List<ScheduleData> lst = db.topicUnFinished(date1,"topicUnfinished" );

%>
<% 
// Connection conn = null;
// PreparedStatement ps = null;
// ResultSet rs = null;
// String topic = null;
// try{
	
// 	Class.forName("com.mysql.jdbc.Driver");
//     String url="jdbc:mysql://localhost:3306/ASE";
//     String username="amulya";
//     String password="password";
   
//      conn=DriverManager.getConnection(url, username, password);
//     ps =conn.prepareStatement("select cshedule from ASE.scheduler where dateDM=?");
//     ps.setDate(1,date1);
//    rs=ps.executeQuery();
//    topic = rs.getString(2);
//    int i=0;
   
   
	
// }catch(Exception e){
// 	out.print(e);
// }




// int i = 0;
// while(i<25){
%>
<!-- <tr> -->
<%-- <td><% out.println(lst.get(i).getSheduleDate());%></td> --%>
<%-- <td><% out.println(lst.get(i).getTopics());%></td> --%>
<!-- </tr> -->


<%-- <% i++;} --%>
<%-- %> --%>
<!-- </table> -->
<h1>topics unfinfished on <%out.print(date1); %> are updated to next class</h1>

</body>
</html>