/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.BookDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.util.UUID;
import model.Book;

/**
 *
 * @author lenovo
 */
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10, // 10 MB
        maxRequestSize = 1024 * 1024 * 100 // 100 MB
)
public class UpdateBookServlet extends HttpServlet {

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
            out.println("<title>Servlet UpdateBookServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateBookServlet at " + request.getContextPath() + "</h1>");
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
        try {
            String id = request.getParameter("id");
            BookDAO bookDAO = new BookDAO();
            Book book = bookDAO.getBookByID(id);
            request.setAttribute("updated_book", book);
            request.getRequestDispatcher("updateBook.jsp").forward(request, response);
        } catch (Exception e) {
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
        try {
            BookDAO bookDAO = new BookDAO();

            String book_id = request.getParameter("book_id");
            Book updatedBook = bookDAO.getBookByID(book_id);

            updatedBook.setBookID(UUID.fromString(book_id));

            String book_name = request.getParameter("book_name");
            updatedBook.setName(book_name);

            String book_author = request.getParameter("book_author");
            updatedBook.setAuthor(book_author);

            String book_status = request.getParameter("book_status");
            // If status is not selected, preserve the old one.
            if (book_status != null) {
                updatedBook.setStatus(book_status);
            }

            String book_publisher = request.getParameter("book_publisher");
            updatedBook.setPublisher(book_publisher);

            String categoryID = request.getParameter("book_cateID");
            // If category is not selected, preserve the old one.
            if (categoryID != null) {
                updatedBook.setCateID(categoryID);
            }

            final String UPLOAD_DIRECTORY = "assets\\Image\\BookImages";
            Part filePart = request.getPart("book_image");
            String fileName = filePart.getSubmittedFileName();
            
            if (!fileName.equals("")) {
                String uploadPath = getServletContext().getRealPath("") + UPLOAD_DIRECTORY;
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) {
                    uploadDir.mkdir();
                }
                //Delete the old image
                String oldImage = updatedBook.getImageURL();
                if (oldImage != null && !oldImage.isEmpty()) {
                    File oldImageFile = new File(getServletContext().getRealPath("") + File.separator + oldImage);
                    if (oldImageFile.exists()) {
                        oldImageFile.delete();
                    }
                }
                // Upload the image
                filePart.write(uploadPath + File.separator + fileName);
                updatedBook.setImageURL("assets/Image/BookImages/" + fileName);
            }
            bookDAO.updateBook(updatedBook);

            response.sendRedirect("book");
        } catch (Exception e) {
            System.out.println(e);
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
