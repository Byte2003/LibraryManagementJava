<%-- 
    Document   : home.jsp
    Created on : Oct 10, 2023, 11:21:32 PM
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
            <div class="member-container py-3" style="position: absolute;
                 top: 75px;
                 left: 275px;
                 bottom: 0;
                 right: 15px;
                 background-color: white;
                 border-radius: 18px;
                 overflow-y: scroll;">
                <div class="container mt-5">

                    <div id="carouselExampleInterval" class="carousel slide" data-bs-ride="carousel">
                        <div class="carousel-inner">
                            <div class="carousel-item active" data-bs-interval="3000">
                                <div class="col-12 m-auto">
                                    <section class="jumbotron text-center">
                                        <p class="fs-2">"A mind needs books as a sword needs a whetstone if it is to keep its edge."</p>
                                        <p class="text-end fs-5">George R.R. Martin, A Game of Thrones</p>
                                    </section>
                                </div>
                            </div>
                            <div class="carousel-item" data-bs-interval="3000">
                                <div class="col-12 m-auto"> 
                                    <section class="jumbotron text-center">
                                        <p class="fs-2">“Books must be read as deliberately and reservedly as they were written.”</p>
                                        <p class="text-end fs-5">Henry David Thoreau, Walden</p>
                                    </section> 
                                </div>
                            </div>
                            <div class="carousel-item"  data-bs-interval="3000" >
                                <div class="col-12 m-auto">
                                    <section class="jumbotron text-center">
                                        <p class="fs-2">“Books are mirrors: you only see in them what you already have inside you.”</p>
                                        <p class="text-end fs-5">Carlos Ruiz Zafon, The Shadow of the Wind</p>
                                    </section>
                                </div>
                            </div>
                        </div>
                        <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleInterval" data-bs-slide="prev">
                            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                            <span class="visually-hidden">Previous</span>
                        </button>
                        <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleInterval" data-bs-slide="next">
                            <span class="carousel-control-next-icon" aria-hidden="true"></span>
                            <span class="visually-hidden">Next</span>
                        </button>
                    </div>
                    <hr>                     
                    <div class="row mt-5">
                    <c:if test="${requestScope.book_list != null}">
                        <c:forEach var="i" items="${requestScope.book_list}" begin="${(requestScope.currentPage - 1) * requestScope.pageSize}" end="${(requestScope.currentPage - 1) * requestScope.pageSize + requestScope.pageSize - 1}">
                            <div class="col-md-4 col-sm-12 col-xs-4 d-flex justify-content-center">
                                <div class="card p-2 mb-3" style="width: 540px; border-radius: 0.5rem;justify-content: center;">
                                    <div class="row g-0">
                                        <div class="col-md-4 border d-flex justify-content-center aligns-item-center">
                                            <img src="${i.imageURL}" class="img-fluid rounded-start" alt="..." >
                                        </div>
                                        <div class="col-md-8" style="">
                                            <div class="card-body">
                                                <h5 class="card-title">${i.name}</h5>
                                                <!--<p class="card-text ">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>-->        
                                                <p class="card-text text-secondary">Author: ${i.author}</p> 
                                                <p class="card-text text-secondary">Category: ${i.category.name}</p>
                                                <p class="card-text ">Status:
                                                    <c:if test="${i.status.equals('Available')}">
                                                        <span class="text-primary" style="font-weight: 600;">${i.status}</span>
                                                    </c:if>
                                                    <c:if test="${i.status.equals('Stockout')}">
                                                        <span class="text-danger"  style="font-weight: 600;">Out of stock</span>
                                                    </c:if>
                                                </p>
                                                <div class="d-flex justify-content-end">
                                                    <a class="btn btn-primary mx-2" href="book_detail?id=${i.bookID}">
                                                        <i class="bi bi-eye"></i>
                                                        View
                                                    </a>
                                                    <a onclick="ConfirmReserve('${i.bookID}')" class="btn btn-success">
                                                        <i class="bi bi-box"></i>
                                                        Reserve
                                                    </a>
                                                </div>

                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach> 
                    </c:if>
                    <c:if test="${requestScope.not_found != null}">
                        <h2 class="text-danger">${requestScope.not_found}</h2>
                    </c:if>
                    <div class="d-flex justify-content-between aligns-item-center">
                        <p class="">Page: ${requestScope.currentPage} / ${requestScope.totalPage} </p>
                        <nav aria-label="Page navigation example">
                            <ul class="pagination justify-content-end">
                                <c:if test="${requestScope.currentPage == 1}">
                                    <li class="page-item disabled">
                                        <a class="page-link" href="#" tabindex="-1" disabled="true">Previous</a>
                                    </li>  
                                </c:if>
                                <c:if test="${requestScope.currentPage != 1}">
                                    <li class="page-item">
                                        <a class="page-link" href="home?currentPage=${requestScope.currentPage - 1}">Previous</a>
                                    </li>  
                                </c:if> 
                                <c:forEach var="i" begin="${requestScope.currentPage - 1}" end="${requestScope.currentPage + 2}">
                                    <c:if test="${i <= requestScope.totalPage && i != 0}">
                                        <li class="page-item page-item-${i}"><a class="page-link" href="home?currentPage=${i}">${i}</a></li>
                                        </c:if>
                                    </c:forEach>
                                    <c:if test="${requestScope.currentPage == totalPage}">
                                    <li class="page-item disabled">
                                        <a class="page-link" href="#">Next</a>
                                    </li>
                                </c:if>
                                <c:if test="${requestScope.currentPage < totalPage}">
                                    <li class="page-item">
                                        <a class="page-link" href="home?currentPage=${requestScope.currentPage + 1}">Next</a>
                                    </li>
                                </c:if>

                            </ul>
                        </nav>
                    </div>
                </div>

            </div>

    </body>
    <script>
        function ConfirmReserve(id) {
            Swal.fire({
                title: 'Are you sure',
                text: "Do you want to take reservation for this?",
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Yes, I want it!'
            }).then((result) => {
                if (result.isConfirmed) {
                    window.location = "book_reserve?book_id=" + id;
                }

            })
        }

        const url = new URL(window.location.href);
        const currentPage = url.searchParams.get("currentPage");
        if (currentPage !== null) {
            console.log("Current Page:", currentPage);
            var paginate_button_active = document.querySelector(`.page-item-${currentPage}`);
            paginate_button_active.classList.add("active");
        } else {
            var paginate_button_active = document.querySelector(`.page-item-1`);
            paginate_button_active.classList.add("active");
        }
    </script>
</html>
