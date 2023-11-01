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
        <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css"/>    
    </head>
    <body style="background-color: #f7f7fa;">
        <div class="login-wrapper">
            <div class="container login-container">
                <div class="card mb-3">
                    <div class="row g-0">
                        <div class="col-md-6">
                            <img src="assets/Image/login.png" class="img-fluid rounded-start rounded" alt="..." style="background-color: dodgerblue;">
                        </div>
                        <div class="col-md-6">
                            <div class="card-body mx-4" style="margin-top: 16%;">
                                <h3 class="card-title">Welcome to FPT LMS </h3>
                                <div style="display: flex;">
                                    <p class="text-secondary">Need an account? </p>
                                    <a href="register" class="mx-2" style="text-decoration: none;">Sign up</a>
                                </div>
                                <h4>Sign in</h2>
                                    <form class="w-75" action="login" method="post">
                                        <div class="my-3">
                                            <c:if test="${requestScope.ErrorLogin != null}">
                                                <span class="text-danger">${requestScope.ErrorLogin}</span>
                                                <br>
                                            </c:if>
                                            <label for="exampleInputEmail1" class="form-label text-secondary">Email</label>
                                            <input type="email" name="email" class="form-control" id="exampleInputEmail1"
                                                   aria-describedby="emailHelp" required>
                                            <div id="emailHelp" class="form-text">We'll never share your email with anyone else.
                                            </div>
                                        </div>
                                        <div class="my-3">
                                            <label for="exampleInputPassword1" class="form-label text-secondary">Password</label>
                                            <input type="password" name="password" class="form-control" id="exampleInputPassword1" required>
                                        </div>
                                        <div class="forgot-container my-2 d-flex justify-content-between">
                                            <div class="remember-me">
                                                <input type="checkbox" name="radio">
                                                <label class="custom_check mr-2 mb-0 d-inline-flex remember-me text-secondary"> Remember me
                                            </div>
                                            <a href="" style="text-decoration: none;">Forgot password?</a>
                                        </div>
                                        <button type="submit" class="btn btn-primary w-100">Login</button>
                                        <div class="mt-3 d-flex w-100 justify-content-center">
                                            <a href="https://accounts.google.com/o/oauth2/auth?scope=email%20profile%20openid&redirect_uri=http://localhost:9999/library/google_login&response_type=code
                                               &client_id=516326276029-jf90bgmvvd1v24k9inmdiiif7pv07d7l.apps.googleusercontent.com&approval_prompt=force" class="btn btn-danger w-100"> <i class="bi bi-google"></i>Login with Google</a>
                                        </div>                                      
                                    </form>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </body>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
    crossorigin="anonymous"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js"></script>
    <script>
        <c:if test="${requestScope.email_existed != null}">
            toastr.error('${requestScope.email_existed}', 'FPTU LMS');
        </c:if>
    </script>


</html>
