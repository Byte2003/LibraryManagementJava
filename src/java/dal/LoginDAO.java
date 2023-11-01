/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import model.User;

/**
 *
 * @author lenovo
 */
public class LoginDAO extends DBContext {
    public User login(String email, String password) throws SQLException{
        String sql = "select * from Users where Email = ? and Password = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, email);
            st.setString(2, password);
            ResultSet rs = st.executeQuery(); 
            if (rs.next()){
                User user =  new User();
                String userID_raw = rs.getString("UserID");
                user.setUserID(UUID.fromString(userID_raw));
                user.setPassword(rs.getString("password"));
                user.setName(rs.getString("Name")); 
                user.setAddress(rs.getString("Address"));
                user.setImage(rs.getString("ImageUrl"));
                user.setPhoneNumber(rs.getString("Phone"));
                user.setEmail(rs.getString("email"));
                // Convert string to LocalDate
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                String dateString = rs.getDate("RegisterDate").toString();
                LocalDate localDate = LocalDate.parse(dateString,formatter);
                user.setRegisterDate(localDate);
                user.setRole(rs.getString("Role"));

                return user;
            } 
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
    
}
