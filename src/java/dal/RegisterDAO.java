/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import model.User;

/**
 *
 * @author lenovo
 */


public class RegisterDAO extends DBContext {
    public List<String> getAllEmail() {
        String sql = "select u.Email from Users u";
        try {
            List<String> list = new ArrayList<>();
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(rs.getString("Email"));
            }
            return list;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void Register(User user) {
        String sql = "INSERT INTO [dbo].[Users]\n"
                + "           ([UserID]\n"
                + "           ,[Name]\n"
                + "           ,[Email]\n"
                + "           ,[Phone]\n"
                + "           ,[ImageUrl]\n"
                + "           ,[Address]\n"
                + "           ,[RegisterDate]\n"
                + "           ,[Password]\n"
                + "           ,[Role])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            UUID uuid = UUID.randomUUID();
            // Convert UUID to string representation
            String uuidString = uuid.toString();
            st.setString(1, uuidString);
            st.setString(2, user.getName());
            st.setString(3, user.getEmail());
            st.setString(4, user.getPhoneNumber() == null ? "unknown" : user.getPhoneNumber());
            st.setString(5, user.getImage() == null ? "" : user.getImage());
            st.setString(6, user.getAddress() == null ? "" : user.getAddress());            
            LocalDate registerDate = LocalDate.now();
            st.setString(7, registerDate.toString());
            st.setString(8,user.getPassword() == null ? "" : user.getPassword());
            st.setString(9, "user");
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
