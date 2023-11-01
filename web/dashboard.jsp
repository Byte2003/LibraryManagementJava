
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dashboard</title>
    </head>
    <body>
        <%-- Include your layout or header if needed --%>
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
                    <h2>Dashboard</h2>
                    <hr>
                    <div class="row">
                        <div class="col-md-6">
                            <h3 class="text-secondary text-center">STOCK</h3>
                            <canvas id="bookStock" width="400" height="200"></canvas>
                        </div>
                        <div class="col-md-6">
                            <h3 class="text-secondary text-center">Reservation</h3>
                            <canvas id="bookReserve" width="400" height="200"></canvas>
                        </div>
                    </div>
                    <hr>
                    <div class="my-5">
                        <h3 class="text-center">Return status</h3>
                        <table id="user_statistic" class="table table-striped table-bordered table-hover mt-5 order-column">
                            <thead>
                                <tr>                             
                                    <th>UserID</th>  
                                    <th>Status</th>
                                    <th>Total</th>
                                </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${requestScope.user_statistic}" var="i">
                                <tr> 
                                    <td>${i.userID}</td> 
                                    <td>${i.status}</td> 
                                    <td>${i.total}</td>  
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>        
    </body>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>  
    <script type="text/javascript">
        const ctx = document.getElementById('bookStock');

        var bookNames = [];
        var bookQuantities = [];
        <c:forEach items="${requestScope.bookStocks}" var="i">
            <c:forEach items="${requestScope.books}" var="j">
        var bookID = "${j.bookID}";
        if (bookID === "${i.bookId}") {
            bookNames.push("${j.name}");
        }
            </c:forEach>
        bookQuantities.push(${i.count});
        </c:forEach>
        new Chart(ctx, {
            type: 'bar',
            data: {
                labels: bookNames,
                datasets: [{
                        label: 'Number of Books',
                        data: bookQuantities,
                        backgroundColor: '#9966ff',
                        borderWidth: 1
                    }]
            },
            options: {
                scales: {
                    y: {
                        beginAtZero: true
                    }
                }
            }
        });
    </script>
    <script type="text/javascript">
        const ctx_book_reserve = document.getElementById("bookReserve").getContext("2d");
        // Sample data for the x-axis (dates) and y-axis (number of books created)
        const dates = [];
        const bookCounts = [];
        <c:forEach items="${requestScope.bookReserves}" var="i">
        dates.push("${i.day}");
        bookCounts.push(${i.count});
        </c:forEach>
        new Chart(ctx_book_reserve, {
            type: "line",
            data: {
                labels: dates,
                datasets: [{
                        label: "Number of Book Reservations",
                        data: bookCounts,
                        borderColor: "rgba(75, 192, 192, 1)",
                        borderWidth: 2,
                        fill: false
                    }]
            },
        });
    </script>
    <script>
        $(document).ready(function () {
            $('#user_statistic').DataTable();
        });
    </script>
</html>
