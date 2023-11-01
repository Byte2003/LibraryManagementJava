/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.BookDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import model.BookReserve;
import model.User;

/**
 *
 * @author lenovo
 */
public class BookReserveServlet extends HttpServlet {

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
            out.println("<title>Servlet BookReserveServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BookReserveServlet at " + request.getContextPath() + "</h1>");
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
        List<BookReserve> list = new ArrayList<>();
        try {
            BookDAO bookDAO = new BookDAO();
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            UUID user_id = user.getUserID();
            switch (user.getRole().toLowerCase()) {
                case "user":
                    String book_id = request.getParameter("book_id");
                    if (book_id != null) { // Click on reserve button
                        BookReserve bookReserve = new BookReserve();
                        bookReserve.setId(UUID.randomUUID());
                        bookReserve.setBookID(UUID.fromString(book_id));
                        bookReserve.setUserID(user_id);
                        bookReserve.setStatus("Pending");
                        bookDAO.createNewBookReserve(bookReserve);
                        request.setAttribute("reserve_success", "Reservation successully");
                    }
                    list = bookDAO.getBookReservesByID(user_id.toString());
                    break;
                case "admin":
                    list = bookDAO.getAllBookReserves();
                    break;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            request.setAttribute("list_reserve", list);
            request.getRequestDispatcher("bookReserve.jsp").forward(request, response);
        }

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
        processRequest(request, response);
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
