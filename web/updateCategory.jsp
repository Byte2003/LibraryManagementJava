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
        <div class="member-container" style="position: fixed;
             top: 75px;
             left: 275px;
             bottom: 0;
             right: 15px;
             background-color: white;
             border-radius: 18px;">
            <div class="container mt-5">
                <h2>Update Category</h2>
                <hr>
            <c:set var="c" value="${requestScope.cate}"></c:set>
                <form action="update_cate" method="post">
                    <div class="row g-3">
                        <input type="text" name="id" hidden value="${c.categoryID}">
                        <div class="col mt-5">
                            <label id="cate_name my-2 text-primary">Name</label>
                            <input type="text" class="form-control" placeholder="Name" name="cate_name" value="${c.name}" required>
                        </div>
                        <div class="col-12 mt-5">
                            <label id="cate_desc my-2 text-primary">Description</label>
                            <textarea name="cate_desc"> 
                                <c:out value="${c.description}" />
                            </textarea>
                            <script>
                                tinymce.init({
                                    selector: 'textarea',
                                    plugins: 'anchor autolink charmap codesample emoticons image link lists media searchreplace table visualblocks wordcount',
                                    toolbar: 'undo redo | blocks fontfamily fontsize | bold italic underline strikethrough | link image media table | align lineheight | numlist bullist indent outdent | emoticons charmap | removeformat',
                                });
                            </script>
                        </div>
                    </div>
                    <div class="my-5 d-flex justify-content-end">
                        <button type="submit" class="btn btn-success mx-2 w-25" value="">Update</button>   
                        <a href="category"  class="btn btn-outline-primary mx-2 " value="">Back to Home</a>

                    </div>
                    
                </form>

            </div>

        </div>
    </body>

</html>
