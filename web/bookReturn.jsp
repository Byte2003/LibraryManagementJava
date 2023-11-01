<%-- 
    Document   : member.jsp
    Created on : Sep 24, 2023, 9:01:10 PM
    Author     : lenovo
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="layout.jsp"></jsp:include>
            <div class="member-container" style="position: absolute;
                 top: 75px;
                 left: 275px;
                 bottom: 0;
                 right: 15px;
                 background-color: white;
                 border-radius: 18px;
                 overflow-y: scroll">
                <div class="container my-5">
                    <h2>Return</h2>
                    <hr>                  
                <table id="return" class="table table-striped table-bordered table-hover mt-5 order-column">
                    <thead>
                        <tr>                             
                            <th>ID</th> 
                            <th>UserID</th>  
                            <th>BookID</th>  
                            <th>Status</th>  
                            <th>FineID</th> 
                            <th>ISBN</th>  
                            <th>BeginDate</th>
                            <th>EndDate</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${requestScope.list_return}" var="i">
                            <tr> 
                                <td>${i.bookReturnID}</td> 
                                <td>${i.userID}</td>    
                                <td>${i.bookID}</td> 
                                <td>${i.status}</td>  
                                <td style="width:10%">${i.fineID}</td>   
                                <td>${i.ISBN}</td>  
                                <td>${i.beginDate} </td>   
                                <td>${i.endDate} </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </body>
    <script>
        $(document).ready(function () {
            $('#return').DataTable();
        });
    </script>
</html>
