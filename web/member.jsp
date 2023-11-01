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
        <div class="member-container" style="position: fixed;
            top: 75px;
            left: 275px;
            bottom: 0;
            right: 15px;
            background-color: white;
            border-radius: 18px;">
            <div class="container mt-5">
                <h1>Member</h1>
            <hr>
            <table id="user_table" class="table table-striped table-bordered table-hover mt-5 order-column">
                        <thead>
                            <tr>                             
                                <th>UserID</th> 
                                <th>Name</th>  
                                <th>Email</th>  
                                <th>Phone</th> 
                                <th>Address</th>  
                                <th>Role</th>
                            </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${requestScope.list_user}" var="i">
                            <tr> 
                                <td>${i.userID}</td> 
                                <td>${i.name}</td> 
                                <td>${i.email}</td>  
                                <td>${i.phoneNumber}</td>   
                                <td>${i.address}</td>  
                                <td>${i.role} </td>                                
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            
        </div>
    </body>
    <script>
        $(document).ready(function () {
            $('#user_table').DataTable();
        });
    </script>
</html>
