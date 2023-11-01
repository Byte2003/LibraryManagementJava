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
                <h2>New Category</h2>
                <hr>
                <form action="confirmAddCate.jsp" method="post">
                    <div class="row g-3">
                        <div class="col mt-5">
                            <label id="cate_name my-2 text-primary">Name</label>
                            <input type="text" class="form-control" placeholder="Name" name="name" required>
                        </div>
                        <div class="col-12 mt-5">
                            <label id="cate_desc my-2 text-primary">Description</label>
                            <textarea name="description"> 
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
                        <button type="submit" class="btn btn-success mx-2 w-25" value="">Add</button>   
                        <a href="category"  class="btn btn-outline-primary mx-2 " value="">Back to Home</a>
                    </div>                    
                </form>
<!--                <form action="add_cate" method="post">
                    <div class="row g-3">
                        <div class="col mt-5">
                            <label id="cate_name my-2 text-primary">Name</label>
                            <input type="text" class="form-control" placeholder="Name" name="cate_name" required>
                        </div>
                        <div class="col-12 mt-5">
                            <label id="cate_desc my-2 text-primary">Description</label>
                            <textarea name="cate_desc"> 
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
                        <button type="submit" class="btn btn-success mx-2 w-25" value="">Add</button>   
                        <a href="category"  class="btn btn-outline-primary mx-2 " value="">Back to Home</a>
                    </div>                    
                </form>-->

            </div>

        </div>
    </body>

</html>
