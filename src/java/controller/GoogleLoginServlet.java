/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import dal.RegisterDAO;
import dal.UserDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import java.util.UUID;
import model.User;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import util.SD;

/**
 *
 * @author lenovo
 */
public class GoogleLoginServlet extends HttpServlet {

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
        try {
            String code = request.getParameter("code");
            String accessToken = getToken(code);

            User user = getUserInfo(accessToken);
            RegisterDAO registerDAO = new RegisterDAO();
            List<String> list_email = registerDAO.getAllEmail();
            UserDAO userDAO = new UserDAO();
            String user_id = userDAO.getUserIDByEmail(user.getEmail());
            // Chua co ID
            if (user_id.equals("")) {
                UUID gg_userID = UUID.randomUUID(); // Handle ID for Google User
                user.setUserID(gg_userID);
                registerDAO.Register(user);
            }  // Co san ID
            if (!user_id.equals("")){
                user.setUserID(UUID.fromString(user_id));
            }
            user.setRole("user");
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            response.sendRedirect("home");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public static String getToken(String code) throws ClientProtocolException, IOException {
        // call api to get token
        String response = Request.Post(SD.GOOGLE_LINK_GET_TOKEN)
                .bodyForm(Form.form().add("client_id", SD.GOOGLE_CLIENT_ID)
                        .add("client_secret", SD.GOOGLE_CLIENT_SECRET)
                        .add("redirect_uri", SD.GOOGLE_REDIRECT_URI).add("code", code)
                        .add("grant_type", SD.GOOGLE_GRANT_TYPE).build())
                .execute().returnContent().asString();

        JsonObject jobj = new Gson().fromJson(response, JsonObject.class);
        String accessToken = jobj.get("access_token").toString().replaceAll("\"", "");
        return accessToken;
    }

    public static User getUserInfo(final String accessToken) throws ClientProtocolException, IOException {
        String link = SD.GOOGLE_LINK_GET_USER_INFO + accessToken;
        String response = Request.Get(link).execute().returnContent().asString();

        User googlePojo = new Gson().fromJson(response, User.class);

        return googlePojo;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the +
    // sign on the left to edit the code.">
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
        processRequest(request, response);
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

}
