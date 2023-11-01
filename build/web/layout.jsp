<%-- 
    Document   : Home
    Created on : Sep 24, 2023, 1:09:20 PM
    Author     : lenovo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Libary Management System</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <link rel="stylesheet" href="assets/css/style.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css"
              integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA=="
              crossorigin="anonymous" referrerpolicy="no-referrer" />
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.0/font/bootstrap-icons.css">
        <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.11.5/css/jquery.dataTables.min.css">
        <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css"/>
        <style>
            .category.animate,
            .book.animate {
                max-height: 100px; /* Adjust the desired height for the animation */
                transition: max-height 0.3s ease-in-out; /* Adjust the animation duration as needed */
            }

            .category,
            .book {
                overflow: hidden;
                max-height: 0;
                transition: max-height 0.3s ease-in-out;
            }
        </style>
    </head>

    <body class="bg-light">
        <nav class="navbar navbar-expand-lg navbar-light mx-2" style="height: 60px; padding: 0px; background-color: #ededed">
            <div class="container-fluid">
                <div class="header-left">
                    <a class="navbar-brand" href="#">
                        <img src="assets/Image/logo.png" alt="" width="156" height="40"
                             class="d-inline-block align-text-top">
                    </a>
                    <a class="navbar-brand" href="#" style="margin-left:65px">
                        <img src="assets/Image/fpt-short.png" alt="" class="d-inline-block align-text-top">
                    </a>
                    <form action="home" method="get" class="d-flex" style="margin-left: 68px;">
                        <input class="form-control me-2 input-search" type="text" name="text" value="${requestScope.search_text}" placeholder="Name, Category, Author,..."
                               aria-label="Search">
                        <button class="btn btn-primary" type="submit">Search</button>
                    </form>
                </div>
            </div>
            <ul class="navbar-nav me-auto mb-2 mb-lg-0" style="display: flex;
                justify-content: center;
                align-items: center;">
                <li class="nav-item notification bg-light mx-3">
                    <a href="" class="icon">
                        <i class="bi bi-bell-fill"></i>
                    </a>               
                </li>
                <c:if test="${sessionScope.user.email != null}">
                    <li class="nav-item mx-4">
                        <span style="cursor:pointer; text-decoration: none; font-weight: 600;"  class="text-secondary">${sessionScope.user.name}</span> 
                        <span style="cursor:pointer; text-decoration: none; font-weight: 600;"  class="text-danger text-uppercase">${sessionScope.user.role}</span> 
                    </li>
                    <li class="nav-item mx-2">
                        <a href="logout" style="text-decoration: none; font-weight: 600;">
                            <span class="text-secondary" style="cursor:pointer;">Logout</span>
                        </a>                       
                    </li>
                </c:if>
                <c:if test="${sessionScope.user.email == null}">
                    <li class="nav-item mx-4">
                        <a href="login.jsp" style=" text-decoration: none; ">
                            <span style="cursor:pointer;font-weight: 600;"  class="text-secondary">Login</span>
                        </a>
                    </li>
                    <li class="nav-item mx-2">
                        <a href="register" style=" text-decoration: none; ">
                            <span style="cursor:pointer;font-weight: 600;"  class="text-secondary">Register</span>
                        </a>
                    </li>
                </c:if>
            </ul>
        </nav>
        <div class="sidebar nav nav-pills" >
            <ul class=" my-4">
                <li class="sidebar-item px-2">
                    <a href="">
                        <p class="text-secondary" style="font-weight: 600;"> Library Management System</p>
                        <hr>
                    </a>
                </li>
                <li class="sidebar-item px-2 py-3">
                    <a href="home">
                        <i class="bi bi-house"></i>
                        <span class="text-secondary mx-4 ">Home</span>
                    </a>
                </li>
                <li class="sidebar-item px-2 py-3">
                    <a href="dashboard">
                        <i class="fa-solid fa-chart-line"></i>
                        <span class="text-secondary mx-4 ">Dashboard</span>
                    </a>
                </li>
                <li class="sidebar-item px-2 py-3">
                    <a href="member">
                        <i class="bi bi-person-circle"></i>
                        <span class="text-secondary mx-4">Members</span>
                    </a>
                </li>
                <li class="sidebar-item px-2 py-3">
                    <a href="book_reserve">
                        <i class="bi bi-book-half"></i>
                        <span class="text-secondary mx-4">Reservation</span>
                    </a>
                </li>
                <li class="sidebar-item px-2 py-3">
                    <a href="book_return">
                        <i class="bi bi-arrow-return-right"></i>
                        <span class="text-secondary mx-4">Return</span>
                    </a>
                </li>
                <li class="sidebar-item px-2 py-3" id="manageResource">
                    <div>
                        <a href="">
                            <i class="bi bi-book"></i>
                            <span class="text-secondary mx-4">Manage resource</span>
                            <i class="fa-solid fa-angle-right"></i>
                        </a>
                    </div>
                </li>
                <li class="sidebar-item px-2 py-3 category" style="margin-left:52px;display:none;">
                    <div>
                        <a href="category">
                            <i class="bi bi-bookmarks"></i>
                            <span class="text-secondary mx-4">Category</span>
                        </a>
                    </div>
                </li>
                <li class="sidebar-item px-2 py-3 book" style="margin-left:52px;display:none;">
                    <div>
                        <a href="book">
                            <i class="bi bi-book"></i>
                            <span class="text-secondary mx-4">Book</span>
                        </a>
                    </div>
                </li>
                <li class="sidebar-item px-2 py-3">
                    <a href="fine.jsp  ">
                        <i class="bi bi-wallet2"></i>
                        <span class="text-secondary mx-4">Fine</span>
                    </a>
                </li>
            </ul>
        </div>
    </body>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
    crossorigin="anonymous"></script>
    <script>
        document.addEventListener("DOMContentLoaded", function () {
            const manageResourceLink = document.getElementById("manageResource");
            const categoryItem = document.querySelector(".category");
            const bookItem = document.querySelector(".book");

            manageResourceLink.addEventListener("click", function (event) {
                event.preventDefault();

                if (categoryItem.style.display === "none" || categoryItem.style.display === "") {
                    categoryItem.style.display = "block";
                    bookItem.style.display = "block";
                } else {
                    categoryItem.style.display = "none";
                    bookItem.style.display = "none";
                }

                // Add animation here (e.g., using CSS transitions)
                categoryItem.classList.toggle("animate");
                bookItem.classList.toggle("animate");
            });
        });
    </script>
    <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.11.5/js/jquery.dataTables.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js"></script>
</html>