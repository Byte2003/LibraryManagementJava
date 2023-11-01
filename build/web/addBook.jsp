<%-- 
    Document   : addCategory
    Created on : Sep 26, 2023, 2:47:26 PM
    Author     : lenovo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="https://cdn.tiny.cloud/1/ducankyqmyft90amgwny0kxjmlvoq6q04s0s4sgfzxyrc6o5/tinymce/6/tinymce.min.js" referrerpolicy="origin"></script>
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
                    <h2>New Book</h2>
                    <hr>
                    <form action="add_book" method="post" enctype="multipart/form-data">
                        <div class="row g-3">
                            <div class="col-6 mt-5">
                                <label id="my-2 text-primary">Name</label>
                                <input type="text" class="form-control" placeholder="Name" name="book_name" value="<%=request.getParameter("book_name") != null ? request.getParameter("book_name") : "" %>" required>
                        </div>
                        <div class="col-6 mt-5">
                            <label id="my-2 text-primary">Author</label>
                            <input type="text" class="form-control" placeholder="Author" name="book_author" value="<%=request.getParameter("book_author") != null ? request.getParameter("book_author") : "" %>" required>
                        </div>
                        <div class="col-6 mt-5">
                            <label id="my-2 text-primary">Publisher</label>
                            <input type="text" class="form-control" placeholder="Publisher" name="book_publisher" value="<%=request.getParameter("book_publisher") != null ? request.getParameter("book_publisher") : "" %>" required>
                        </div>
                        <div class="col-12 mt-5">
                            <label id="my-2 text-primary">Image</label>
                            <input type="file" class="form-control" placeholder="Image" name="book_image" required>
                        </div>

                        <div class="col-12 mt-5">
                            <label id="my-2 text-primary">Category</label>
                            <select class="form-select" aria-label="Default select example" name="book_cateID">
                                <option disabled selected>--Category--</option>
                                <c:forEach var="i" items="${sessionScope.list_cate}">
                                    <option value="${i.categoryID}">${i.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="my-5 d-flex justify-content-end">
                        <button type="submit" class="btn btn-success mx-2 w-25" value="">Add</button>   
                        <a href="book"  class="btn btn-outline-primary mx-2 " value="">Back to Home</a>
                    </div>

                </form>

            </div>

        </div>
    </body>

</html>
