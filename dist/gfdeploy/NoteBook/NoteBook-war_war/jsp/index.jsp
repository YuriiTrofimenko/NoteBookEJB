<%-- 
    Document   : index
    Created on : 12.12.2017, 10:18:35
    Author     : student
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import = "java.io.*,java.util.*,java.sql.*"%>
<%@ page import = "javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix = "sql"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>States table</h1>
        <sql:setDataSource var = "snapshot" driver = "com.mysql.jdbc.Driver"
         url = "jdbc:mysql://localhost:3306/NoteBook"
         user = "root"  password = "root"/>
 
        <sql:query dataSource = "${snapshot}" var = "result">
           SELECT * FROM State;
        </sql:query>
 
      <table border = "1" width = "100%">
         <tr>
            <th>ID</th>
            <th>Name</th>
         </tr>
         
         <c:forEach var = "row" items = "${result.rows}">
            <tr>
               <td><c:out value = "${row.id}"/></td>
               <td><c:out value = "${row.name}"/></td>
            </tr>
         </c:forEach>
      </table>
    </body>
</html>
