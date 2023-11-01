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
                <div class="container mt-5">
                    <h2>Book reservation</h2>
                    <hr>  
                <c:if test="${requestScope.list_reserve != null}">
                    <table id="reserve_table" class="table table-striped table-bordered table-hover mt-5 order-column">
                        <thead>
                            <tr>                             
                                <th>ID</th> 
                                <th>UserName</th>  
                                <th>BookName</th>  
                                <th>ISBN</th> 
                                <th>Status</th>  
                                <th>CreatedDate</th>
                                    <c:if test="${sessionScope.user.role.equals('admin')}">
                                    <th>Manage</th>
                                    </c:if>

                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${requestScope.list_reserve}" var="i">
                                <tr> 
                                    <td>${i.id}</td> 
                                    <td>${i.user.name}</td> 
                                    <td>${i.book.name}</td>  
                                    <td>${i.ISBN}</td>
                                    <c:choose>
                                        <c:when test="${i.status.equals('Deny')}">
                                            <td class="text-danger">${i.status}</td>  
                                        </c:when>
                                        <c:when test="${i.status.equals('Pending')}">
                                            <td class="text-secondary">${i.status}</td>  
                                        </c:when>
                                        <c:when test="${i.status.equals('Approve')}">
                                            <td class="text-primary">${i.status}</td>  
                                        </c:when>
                                        <c:when test="${i.status.equals('Received')}">
                                            <td class="text-information">${i.status}</td>  
                                        </c:when>
                                        <c:when test="${i.status.equals('Returned')}">
                                            <td class="text-success">${i.status}</td>  
                                        </c:when>
                                    </c:choose> 
                                    <td>${i.createdDate} </td>
                                    <c:if test="${sessionScope.user.role.equals('admin')}">
                                        <td>
                                            <div class="d-flex justify-content-center">
                                                <a href="update_reserve?id=${i.id}&book_id=${i.bookID}&book_name=${i.book.name}&user_id=${i.userID}&user_name=${i.user.name}&status=${i.status}&ISBN=${i.ISBN}" class="btn btn-primary mx-2"><i class="bi bi-pencil"></i>  Update</a>                                              
                                            </div>
                                        </td>
                                    </c:if>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:if>
            </div>
        </div>
    </body>
    <script>
        function ConfirmDelete(id) {
            Swal.fire({
                title: 'Are you sure?',
                text: "You won't be able to revert this!",
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Yes, delete it!'
            }).then((result) => {
                if (result.isConfirmed) {
                    window.location = "delete_book?id=" + id;
                }

            })
        }
        <c:if test="${requestScope.delete_book_success != null}">
        Swal.fire(
                'Deleted!',
                'Your book has been deleted.',
                'success'
                )
        </c:if>
    </script>
    <script>
        $(document).ready(function () {
            $('#reserve_table').DataTable();
        });
    </script>
    <script>
        <c:if test="${requestScope.reserve_success != null}">
        toastr.success('Reserve successfully!', 'FPTU LMS')
        </c:if>
        <c:if test="${requestScope.status_reserve_success!= null}">
        toastr.success('Update status successfully!', 'FPTU LMS')
        </c:if>

    </script>
</html>
