<%-- 
    Document   : login.jsp
    Created on : Sep 24, 2023, 4:15:46 PM
    Author     : lenovo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <link rel="stylesheet" href="assets/css/style.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css"
              integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA=="
              crossorigin="anonymous" referrerpolicy="no-referrer" />
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.0/font/bootstrap-icons.css">
    </head>

    <body style="background-color: #f7f7fa;">
        <div class="login-wrapper">
            <div class="container login-container">
                <div class="card mb-3">
                    <div class="row g-0">
                        <div class="col-md-6">
                            <div class="card-body mx-4" style="margin-top: 16%;">
                                <h3 class="card-title">Welcome to FPTLMS </h3>

                                <h4>Register</h4>
                                <form class="row g-3" action="register" method="post">
                                    <div class="col-md-6">
                                        <label for="inputEmail4" class="form-label">Name</label>
                                        <input type="text" class="form-control" id="inputEmail4" name="name" value="<%=request.getParameter("name") != null ? request.getParameter("name") : "" %>" required>
                                    </div>
                                    <div class="col-md-6">
                                        <label for="inputAddress" class="form-label">Phone</label>
                                        <input type="text" class="form-control" id="inputAddress" name="phone" value="<%=request.getParameter("phone") != null ? request.getParameter("phone") : ""%>" required>
                                    </div>
                                    <div class="col-md-6">
                                        <label for="inputEmail4" class="form-label">Email</label>
                                        <input type="email" class="form-control" id="inputEmail4" name="email" value="<%=request.getParameter("address") != null ? request.getParameter("address") : ""%>" required>
                                        <c:if test="${requestScope.email_existed != null}">
                                            <span class="text-danger">${requestScope.email_existed}</span>
                                        </c:if> 
                                    </div>
                                    <div class="col-md-6">
                                        <label for="inputPassword4" class="form-label">Password</label>
                                        <input type="password" class="form-control" id="password" name="password" required>
                                    </div>
                                    <div class="col-md-12">                                                                         
                                        <label for="inputPassword4" class="form-label">Confirm Password</label>
                                        <input type="password" class="form-control" id="confirm_password" name="confirm_password" required>
                                        <c:if test="${requestScope.error_confirm != null}">
                                            <span class="text-danger">${requestScope.error_confirm}</span>
                                        </c:if>     
                                    </div>
                                    <div class="col-12">
                                        <label for="inputAddress" class="form-label">Address</label>
                                        <input type="text" class="form-control" id="inputAddress" placeholder="1234 Main St" name="address" required>
                                    </div>  

                                    <div class="col-12 mt-5">
                                        <button type="submit" class="btn btn-primary w-50 btn-register" >Register</button>
                                    </div>
                                    <div class="mt-3 d-flex w-50 justify-content-center">
                                        <a href="https://accounts.google.com/o/oauth2/auth?scope=email%20profile%20openid&redirect_uri=http://localhost:9999/library/google_login&response_type=code
                                           &client_id=516326276029-jf90bgmvvd1v24k9inmdiiif7pv07d7l.apps.googleusercontent.com&approval_prompt=force" class="btn btn-danger w-100"> <i class="bi bi-google"></i>Login with Google</a>
                                    </div> 
                                </form>
                                <div class="mt-5" style="display: flex;">
                                    <p class="text-secondary">Already have an account? </p>
                                    <a href="login" class="mx-2" style="text-decoration: none;">Sign in</a>
                                </div>

                            </div>
                        </div>
                        <div class="col-md-6">
                            <img src="assets/Image/login.png" class="img-fluid rounded-start rounded" alt="..." style="background-color: dodgerblue;">
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </body>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
    crossorigin="anonymous"></script>
</html>
