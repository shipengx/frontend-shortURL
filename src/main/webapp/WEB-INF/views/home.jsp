<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>URL Manager Home</title>
    </head>
    <body>
        <div align="center">
            <h1>URL List</h1>
            <h3><a href="/shortenURL/newURL">New URL</a></h3>
            <table border="1">
            	<tr>
                <th>Id</th>
                <th>LongURL</th>
                <th>shortURL</th>
                </tr> 
                 
                <c:forEach var="url" items="${listURL}" varStatus="status">
                <tr>
                    <td>${url.id}</td>
                    <td>${url.longURL}</td>
                    <td>${url.shortURL}</td>
                    <td>
                        <a href="/shortenURL/editURL?id=${url.id}">Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="/shortenURL/deleteURL?id=${url.id}">Delete</a>
                    </td>  
                </tr>
                </c:forEach>             
            </table>
        </div>
    </body>
</html>