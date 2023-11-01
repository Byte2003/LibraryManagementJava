/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import model.Category;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 *
 * @author lenovo
 */

public class CategoryDAO extends DBContext {

    public List<Category> getAllCategories() {
        String sql = "select * from Categories";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            List<Category> list = new ArrayList<>();
            while (rs.next()) {
                Category c = new Category();
                String cateID_raw = rs.getString("CategoryID");
                c.setCategoryID(UUID.fromString(cateID_raw));
                c.setName(rs.getString("Name"));
                c.setDescription(rs.getString("Description"));
                list.add(c);
            }
            return list;
        } catch (Exception e) {
        }
        return null;
    }

    public Category getCategoryByID(String id) {
        String sql = "select * from Categories where CategoryID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Category c = new Category();
                c.setCategoryID(UUID.fromString(rs.getString("CategoryID")));
                c.setName(rs.getString("Name"));
                String description_raw = rs.getString("Description");
                Document doc = Jsoup.parse(description_raw);
                String description = doc.text();
                c.setDescription(description);
                return c;
            }
        } catch (Exception e) {
        }
        return null;
    }

    public void addCategory(Category c) {
        String sql = "INSERT INTO [dbo].[Categories]\n"
                + "           ([CategoryID]\n"
                + "           ,[Name],"
                + "           [Description])\n"
                + "     VALUES (?,?,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            // Generate a random UUID
            UUID uuid = UUID.randomUUID();

            // Convert UUID to string representation
            String uuidString = uuid.toString();

            st.setString(1, uuidString);
            st.setString(2, c.getName());

            // Convert HTML to text
            String description_raw = c.getDescription();
            Document doc = Jsoup.parse(description_raw);
            String description = doc.text();

            st.setString(3, description);
            st.executeUpdate();
        } catch (Exception e) {

        }
    }

    public void updateCategory(Category c) {
        String sql = "UPDATE [dbo].[Categories]\n"
                + "   SET [Name] = ?\n"
                + "      ,[Description] = ?\n"
                + " WHERE CategoryID = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, c.getName());

            // Convert HTML to text
            String descriptionRaw = c.getDescription();
            Document doc = Jsoup.parse(descriptionRaw);
            String description = doc.text();
            st.setString(2, description);
            // Assuming you have the CategoryID of the category you want to update
            st.setString(3, c.getCategoryID().toString());

            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteCategory(String id) {
        String sql = "DELETE FROM [dbo].[Categories] WHERE CategoryID = ?" ;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, id);
            st.executeUpdate();
        } catch (Exception e) {
        }
    }
}
