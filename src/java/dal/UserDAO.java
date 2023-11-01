/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import model.User;

/**
 *
 * @author lenovo
 */
public class UserDAO extends DBContext {

    public String getUserIDByEmail(String email) {
        String sql = "select u.UserID from Users u where u.Email = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, email);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getString("UserID");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "";
    }

    public List<User> getAllUsers() {
        String sql = "SELECT [UserID]\n"
                + "      ,[Name]\n"
                + "      ,[Email]\n"
                + "      ,[Phone]\n"
                + "      ,[Address]\n"
                + "      ,[RegisterDate]\n"
                + "      ,[Role]\n"
                + "  FROM [Library].[dbo].[Users] WHERE Role != 'admin'";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            List<User> list = new ArrayList<>();
            while (rs.next()) {
                User user = new User();
                user.setUserID(UUID.fromString(rs.getString("UserID")));
                user.setName(rs.getString("Name"));
                user.setEmail(rs.getString("Email"));
                user.setAddress(rs.getString("Address"));
                // Define the format of the date string (assuming it's in "yyyy-MM-dd" format)
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                // Parse the string to LocalDate
                String createdDate = rs.getString("RegisterDate").substring(0, 10);
                LocalDate localDate = LocalDate.parse(createdDate, formatter);
                user.setRegisterDate(localDate);
                user.setRole(rs.getString("Role"));
                user.setImage("null");
                list.add(user);              
            }
            return list;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public User getUserByID(String id) {
        String sql = "SELECT [UserID]\n"
                + "      ,[Name]\n"
                + "      ,[Email]\n"
                + "      ,[Phone]\n"
                + "      ,[ImageUrl]\n"
                + "      ,[Address]\n"
                + "      ,[RegisterDate]\n"
                + "      ,[Password]\n"
                + "      ,[Role]\n"
                + "  FROM [Library].[dbo].[Users] WHERE UserID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setUserID(UUID.fromString(rs.getString("UserID")));
                user.setName(rs.getString("Name"));
                user.setEmail(rs.getString("Email"));
                user.setAddress(rs.getString("Address"));
                // Define the format of the date string (assuming it's in "yyyy-MM-dd" format)
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                // Parse the string to LocalDate
                String createdDate = rs.getString("RegisterDate").substring(0, 10);
                LocalDate localDate = LocalDate.parse(createdDate, formatter);
                user.setRegisterDate(localDate);
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("Role"));
                user.setImage("null");
                return user;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
