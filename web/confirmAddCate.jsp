<%-- 
    Document   : confirmAddCate
    Created on : Oct 27, 2023, 5:21:05 PM
    Author     : lenovo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Category"%>
<jsp:useBean id="category" class="model.Category" scope="session" />
<jsp:setProperty name="category" property="*"/>
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
                    <h3>Confirmation</h3>
                    <hr>              
                    <form class="row g-3" action="add_cate" method="post">
                        <div class="col-md-6">
                            <label for="name" class="form-label">Name:</label>
                            <p class="text-primary">${category.name}</p>
                    </div>
                    <div class="col-md-6">
                        <label id="my-2 text-primary">Description</label>
                        <p class="text-primary">${category.description}</p>
                    </div>       
                    <div class="col-12">
                        <button type="submit" class="btn btn-primary">Confirm</button> 
                        <a href="home" class="btn btn-secondary">Back To Home</a>
                    </div>
                </form>
            </div>

        </div>
    </body>
</html>
