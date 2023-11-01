/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.BookDAO;
import dal.BookReturnDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import model.BookReturn;
import util.Email;

/**
 *
 * @author lenovo
 */
class GetMethodOverrideWrapper extends HttpServletRequestWrapper {

    private String method;

    public GetMethodOverrideWrapper(HttpServletRequest request, String method) {
        super(request);
        this.method = method;
    }

    @Override
    public String getMethod() {
        return method;
    }
}

public class UpdateBookReserveServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UpdateBookReserveServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateBookReserveServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        String book_name = request.getParameter("book_name");
        String user_name = request.getParameter("user_name");
        String book_id = request.getParameter("book_id");
        String user_id = request.getParameter("user_id");
        String status = request.getParameter("status");
        String ISBN = request.getParameter("ISBN");
        request.setAttribute("id", id);
        request.setAttribute("status", status);
        request.setAttribute("user_name", user_name);
        request.setAttribute("book_name", book_name);
        request.setAttribute("book_id", book_id);
        request.setAttribute("user_id", user_id);
        request.setAttribute("ISBN", ISBN);
        request.getRequestDispatcher("updateReservation.jsp").forward(request, response);

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        BookReturnDAO bookReturnDAO = new BookReturnDAO();
        String id = request.getParameter("id");
        String book_name = request.getParameter("book_name");
        String user_name = request.getParameter("user_name");
        String status = request.getParameter("status");
        String book_id = request.getParameter("book_id");
        String user_id = request.getParameter("user_id");
        String ISBN = request.getParameter("ISBN");
        request.setAttribute("id", id);
        request.setAttribute("user_name", user_name);
        request.setAttribute("book_name", book_name);
        request.setAttribute("ISBN", ISBN);

        // Prevent duplicate ISBN
        BookDAO bookDAO = new BookDAO();
        List<String> list_isbn = bookDAO.getALLISBN();
        if (list_isbn.contains(ISBN) && !status.equals("Deny") && !status.equals("Received") && !status.equals("Returned")) {
            request.setAttribute("duplicate_isbn", "Dupplicate ISBN: " + ISBN);
            request.setAttribute("status", "Pending");
            request.getRequestDispatcher("updateReservation.jsp").forward(request, response);
        } else {

            // Sending an email
            String userEmail = bookDAO.getUserEmailOfBookReserve(id);
            switch (status.toLowerCase()) {
                case "approve":
                    Email.sendEmail(userEmail, "Thanks for reservation!", "Your reservation has been placed! \n ID: " + id + "; BookName : " + book_name + "; ISBN: " + ISBN);
                    break;
                case "deny":
                    Email.sendEmail(userEmail, "Denial Reservation", "Sorry, Your reservation has been denied! \n ID: " + id);
                    break;
                case "received":
                    bookDAO.decreaseStock(book_id);
                    int stock = bookDAO.checkCurrentStockOfBook(book_id);
                    if (stock == 0) {
                        bookDAO.updateBookStatus(book_id, "Stockout");
                    }
                    // Create Book Return
                    BookReturn bookReturn = new BookReturn();
                    bookReturn.setBookReturnID(UUID.fromString(id)); // Using the same id of Book reserve 
                    bookReturn.setUserID(UUID.fromString(user_id));
                    bookReturn.setBookID(UUID.fromString(book_id));
                    bookReturn.setStatus("OK");
                    bookReturn.setBeginDate(LocalDate.now());
                    bookReturn.setISBN(ISBN);
                    bookReturnDAO.createBookReturn(bookReturn);
                    break;
                case "returned":
                    bookDAO.increaseStock(book_id);
                    bookReturnDAO.updateEndDate(id);
                    break;
            }
            bookDAO.updateBookReserve(id, ISBN, status);
            request.setAttribute("status_reserve_success", "Update reservation's status successfully!");
            // Change the HTTP method to GET
            GetMethodOverrideWrapper getMethodRequest = new GetMethodOverrideWrapper(request, "GET");
            request.getRequestDispatcher("book_reserve").forward(getMethodRequest, response);
        }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
