<%-- 
    Document   : bookView
    Created on : Oct 26, 2023, 1:17:13 PM
    Author     : lenovo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                 overflow-y: scroll;">
                <h3 class="mt-3 mx-3">Details</h3>
                <hr>
                <div class="container login-container">
                    <div class="card my-3">
                        <div class="row g-0">
                            <div class="col-md-6">
                                <div class="card-body mx-4" style="margin-top: 16%;">
                                    <h3 class="card-title">${requestScope.book.name}</h3>
                                <form class="row g-3" action="register" method="post"> 
                                    <div class="col-12 text-secondary">
                                        What is Lorem Ipsum?
                                        Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book.
                                    </div>
                                    <div class="col-md-6">
                                        <label for="inputAddress" class="form-label">Category</label>
                                        <input type="text" class="form-control" id="inputAddress" name="name" value="${requestScope.book.category.name}" readonly="">
                                    </div> 
                                    <div class="col-md-6">
                                        <label for="inputAddress" class="form-label">Author</label>
                                        <input type="text" class="form-control" id="inputAddress" name="author" value="${requestScope.book.author}" readonly="">
                                    </div>
                                    <div class="col-md-6">
                                        <label for="inputEmail4" class="form-label">Publisher</label>
                                        <input type="email" class="form-control" id="inputEmail4" name="publisher" value="${requestScope.book.publisher}" readonly="">
                                    </div>
                                    <div class="col-md-6">
                                        <label for="inputEmail4" class="form-label">Status</label>
                                        <input type="email" class="form-control" id="inputEmail4" name="status" value="${requestScope.book.status}" readonly="">
                                    </div>  
                                    <div class="d-flex justify-content-end mt-5">                                       
                                        <c:if test="${requestScope.book.status.equals('Stockout') == false}">
                                            <a href="book_reserve?book_id=${requestScope.book.bookID}" class="btn btn-success w-25" >Reserve</a>
                                        </c:if>
                                        <a href="home" class="btn btn-secondary w-25 mx-4" >Back to home</a>
                                    </div>

                                </form>
                            </div>
                        </div>
                        <div class="col-md-6" style="display: flex;
                             justify-content: center;
                             align-items: center;">
                            <img src="${requestScope.book.imageURL}" class="img-fluid rounded-start rounded" alt="..." style="max-height:600px">
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
