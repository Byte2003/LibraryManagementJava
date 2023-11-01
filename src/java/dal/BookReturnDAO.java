package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import model.BookReturn;
import viewmodel.UserStatistic;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author lenovo
 */
class Test {

    public static void main(String[] args) {
        BookReturnDAO bookReturnDAO = new BookReturnDAO();
        List<BookReturn> list = bookReturnDAO.getAllBookReturns();
        System.out.println(list.size());
    }

}

public class BookReturnDAO extends DBContext {

    public List<BookReturn> getAllBookReturns() {
        String sql = "SELECT [BookReturnID]\n"
                + "      ,[Status]\n"
                + "      ,[UserID]\n"
                + "      ,[BookID]\n"
                + "      ,[FineID]\n"
                + "      ,[ISBN]\n"
                + "      ,[BeginDate]\n"
                + "      ,[EndDate]\n"
                + "  FROM [dbo].[BookReturns]";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            List<BookReturn> list = new ArrayList<>();
            while (rs.next()) {
                BookReturn bookReturn = new BookReturn();
                bookReturn.setBookReturnID(UUID.fromString(rs.getString("BookReturnID")));
                bookReturn.setBookID(UUID.fromString(rs.getString("BookID")));
                bookReturn.setUserID(UUID.fromString(rs.getString("UserID")));
                bookReturn.setStatus(rs.getString("Status"));
                if (rs.getString("FineID") != null) {
                    bookReturn.setFineID(UUID.fromString(rs.getString("FineID")));
                }
                bookReturn.setISBN(rs.getString("ISBN"));
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                String beginDate_raw = rs.getString("BeginDate").substring(0, 10);
                LocalDate beginDate = LocalDate.parse(beginDate_raw, formatter);
                bookReturn.setBeginDate(beginDate);
                if (rs.getString("EndDate") != null) {
                    String endDate_raw = rs.getString("EndDate").substring(0, 10);
                    LocalDate endDate = LocalDate.parse(endDate_raw, formatter);
                    bookReturn.setEndDate(endDate);
                }
                list.add(bookReturn);
            }
            return list;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void createBookReturn(BookReturn bookReturn) {
        String sql = "INSERT INTO [dbo].[BookReturns]\n"
                + "           ([BookReturnID]\n"
                + "           ,[Status]\n"
                + "           ,[UserID]\n"
                + "           ,[BookID]\n"
                + "           ,[ISBN]\n"
                + "           ,[BeginDate])\n"
                + "     VALUES (?,?,?,?,?,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, bookReturn.getBookReturnID().toString());
            st.setString(2, bookReturn.getStatus());
            st.setString(3, bookReturn.getUserID().toString());
            st.setString(4, bookReturn.getBookID().toString());
            st.setString(5, bookReturn.getISBN());
            st.setString(6, bookReturn.getBeginDate().toString());
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void updateStatus(String id, String status) {
        String sql = "UPDATE [dbo].[BookReturns]\n"
                + "   SET [Status] = ? \n"
                + " WHERE BookReturnID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, status);
            st.setString(2, id);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateEndDate(String id) {
        String sql = "UPDATE [dbo].[BookReturns]\n"
                + "   SET [EndDate] = ? \n"
                + " WHERE BookReturnID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);

            st.setString(1, LocalDate.now().toString()); // Can plus 10 days for testing Book return late
            st.setString(2, id);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // Handle late days
        int lateDays = calculateLateDays(id);
        if (lateDays - 7 > 0) { // Return book within 7 days          
            updateStatus(id, "LATE");
            String fineID = UUID.randomUUID().toString();
            addFineForLate(fineID, lateDays);
            updateFineID(id, fineID);
        }
    }

    public void updateFineID(String id, String fineID) {
        // Update the FineID, then create new record for Fine table
        String sql = "UPDATE [dbo].[BookReturns]\n"
                + "   SET [FineID] = ? \n"
                + " WHERE BookReturnID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, fineID);
            st.setString(2, id);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // EndDate - Begin Date
    public int calculateLateDays(String id) {
        String sql = " SELECT DATEDIFF(DAY,br.BeginDate, br.EndDate) as lateDays from [Library].[dbo].[BookReturns] br WHERE br.BookReturnID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getInt("lateDays");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return 1;
    }

    public void addFineForLate(String id, int lateDays) {
        String sql = "INSERT INTO [dbo].[Fines]\n"
                + "           ([FineID]\n"
                + "           ,[NumberOfLateDays]\n"
                + "           ,[Price])\n"
                + "     VALUES\n"
                + "           (?,?,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, id);
            st.setInt(2, lateDays - 7);
            st.setDouble(3, (lateDays - 7) * 5000);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public List<UserStatistic> getAllUserStatistic() {
        String sql = "SELECT TOP (1000) [UserID]\n"
                + ", br.[Status], COUNT(*) AS Total\n"
                + "  FROM [Library].[dbo].[BookReturns] br\n"
                + "  GROUP BY UserID, br.[Status]";
        try {
            List<UserStatistic> list = new ArrayList<>();
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                UserStatistic userStatistic = new UserStatistic();
                userStatistic.setUserID(UUID.fromString(rs.getString("UserID")));
                userStatistic.setStatus(rs.getString("Status"));
                userStatistic.setTotal(rs.getInt("Total"));
                list.add(userStatistic);
            }
            return list;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

}
