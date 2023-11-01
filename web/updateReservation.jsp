
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
                    <h2>Update Reservation</h2>
                    <hr>
                    <form action="update_reserve" method="post">
                    <c:if test="${requestScope.duplicate_isbn != null}">
                        <span class="text-danger">${requestScope.duplicate_isbn}</span>
                    </c:if>
                    <div class="row g-3 ">
                        <input type="text" name="id" hidden value="${requestScope.id}"> 
                        <input type="text" name="book_id" hidden value="${requestScope.book_id}"> 
                        <input type="text" name="user_id" hidden value="${requestScope.user_id}">
                        <div class="col-6 mt-5">
                            <label id="my-2 text-primary">Name</label>
                            <input type="text" class="form-control" placeholder="Name" name="book_name" value="${requestScope.book_name}" readonly="">
                        </div>
                        <div class="col-6 mt-5">
                            <label id="my-2 text-primary">Author</label>
                            <input type="text" class="form-control" name="user_name" value="${requestScope.user_name}" readonly="">
                        </div>

                        <div class="col-6 mt-5">
                            <label id="my-2 text-primary">Status</label>
                            <select onclick="showISBN()" class="form-select status" aria-label="Default select example" name="status" required="">
                                <option disabled selected>--Status--</option>
                                <c:choose>
                                    <c:when test="${requestScope.status.equals('Pending')}">
                                        <option value="Approve">Approve</option>  
                                        <option value="Deny">Deny</option>
                                    </c:when>
                                    <c:when test="${requestScope.status.equals('Deny')}">
                                        <option value="Approve">Approve</option>  
                                    </c:when>
                                    <c:when test="${requestScope.status.equals('Approve')}">
                                        <option value="Received">Received</option>
                                    </c:when>
                                    <c:when test="${requestScope.status.equals('Received')}">
                                        <option value="Returned">Returned</option>
                                    </c:when>
                                    <c:when test="${requestScope.status.equals('Returned')}">                                       
                                    </c:when>   
                                </c:choose>
                            </select>
                        </div>
                        <div class="col-6 mt-5 isbn" style="display: none;">
                            <label id="my-2 text-primary">ISBN</label>
                            <input type="text" class="form-control isbn_input" name="ISBN" value="${requestScope.ISBN}" placeholder="Enter ISBN for this reservation">                               
                        </div>
                    </div>
                    <div class="my-5 d-flex justify-content-start">
                        <button type="submit" class="btn btn-success mx-2">Update</button>   
                        <a href="book_reserve"  class="btn btn-outline-primary mx-2 " value="">Back</a>
                    </div>
                </form>

            </div>

        </div>
    </body>
    <script>
        var ISBN_input = document.querySelector(".isbn_input"); // Move this line outside the function

        function showISBN() {
            var status = document.querySelector(".status");
            console.log(status);

            if (status.value === 'Approve') {
                var ISBN = document.querySelector(".isbn");
                ISBN.style.display = "block";
                ISBN_input.required = true;
            }
            if (status.value === 'Deny') {
                var ISBN = document.querySelector(".isbn");
                ISBN.style.display = "none";
                ISBN_input.required = false;
                ISBN_input.value = "";
            }
            if (status.value === 'Received') {
                var ISBN = document.querySelector(".isbn");
                ISBN.style.display = "block";
                ISBN_input.readonly = true;
            }
            if (status.value === 'Returned') {
                var ISBN = document.querySelector(".isbn");
                ISBN.style.display = "none";
                ISBN_input.required = false;
                ISBN_input.value = "";
            }
        }
    </script>
</html>

