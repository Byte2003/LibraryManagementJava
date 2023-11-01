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
import java.util.ArrayList;
import java.util.List;
import model.Book;

/**
 *
 * @author lenovo
 */
public class HomeServlet extends HttpServlet {

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
            out.println("<title>Servlet HomeServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet HomeServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    private List<Book> handleSearching(String text, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BookDAO bookDAO = new BookDAO();
        List<Book> list = bookDAO.search(text);
        return list;
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
              
        BookDAO bookDAO = new BookDAO();
        List<Book> book_list = new ArrayList<>();

        String text = request.getParameter("text");
        if (text != null && !text.equals("")) {
            book_list = handleSearching(text, request, response);
            request.setAttribute("search_text", text);
            if (!book_list.isEmpty()) {
                request.setAttribute("book_list", book_list);
            } else {
                request.setAttribute("not_found", "No results for " + text);
            }
        } else {
            book_list = bookDAO.getAllBooks();
            request.setAttribute("book_list", book_list);
        }
        
        // Handle pagination
        String currentPage_raw = request.getParameter("currentPage") == null ? "" : request.getParameter("currentPage") ;
        int currentPage;
        if (currentPage_raw.equals("")){
            currentPage = 1;
        } else {
            currentPage = Integer.parseInt(currentPage_raw);
        }
        final int pageSize = 6;
        int totalPage =  (book_list.size() % pageSize == 0 ) ? book_list.size() / pageSize  : book_list.size() / pageSize + 1;
        request.setAttribute("totalPage", totalPage);      
        request.setAttribute("currentPage", currentPage);  
        request.setAttribute("pageSize", pageSize);
        request.getRequestDispatcher("home.jsp").forward(request, response);

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
        request.getRequestDispatcher("layout.jsp").forward(request, response);
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
