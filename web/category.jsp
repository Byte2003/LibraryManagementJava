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
                    <h2>Library resource: Category</h2>
                    <hr>
                    <div class="d-flex justify-content-end my-5">
                        <a href="add_cate">

                            <button class="btn btn-success mx-2">
                                <i class="bi bi-plus-circle-dotted"></i>  Add new category</button>
                        </a>

                    </div>


                    <table id="cate_table" class="table table-striped table-bordered table-hover mt-5 order-column">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Name</th>     
                                <th>Description</th> 
                                <th>Manage</th>         
                            </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${requestScope.Cate_list}" var="i">
                            <tr>
                                <td>${i.categoryID}</td>
                                <td>${i.name}</td>  
                                <td>${i.description}</td>    
                                <td>
                                    <div class="d-flex justify-content-center">
                                        <a href="update_cate?id=${i.categoryID}" class="btn btn-primary mx-2"><i class="bi bi-pencil"></i>  Update</a>
                                        <a class="btn btn-danger mx-2" onclick="ConfirmDelete('${i.categoryID}')"><i class="bi bi-trash"></i>  Delete</a>
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
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
                    window.location = "delete_cate?id=" + id;
                }

            })
        }
        <c:if test="${requestScope.delete_cate_success != null}">
        Swal.fire(
                'Deleted!',
                'Your category has been deleted.',
                'success'
                )
        </c:if>
    </script>
    <script>
        $(document).ready(function () {
            $('#cate_table').DataTable(); 
        });
    </script>
</html>
