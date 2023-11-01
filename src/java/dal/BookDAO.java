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
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import model.Book;
import model.BookReserve;
import model.BookStock;
import model.Category;
import viewmodel.ReservationPerDay;

/**
 *
 * @author lenovo
 */

public class BookDAO extends DBContext {

    private final CategoryDAO categoryDAO;

    public BookDAO() {
        categoryDAO = new CategoryDAO();
    }

    public List<Book> getAllBooks() {
        String sql = "select * from Books";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            List<Book> list = new ArrayList<>();
            while (rs.next()) {
                Book b = new Book();
                String bookID_raw = rs.getString("BookID");
                b.setBookID(UUID.fromString(bookID_raw));
                b.setName(rs.getString("Name"));
                b.setAuthor(rs.getString("Author"));
                b.setStatus(rs.getString("Status"));
                b.setPublisher(rs.getString("Publisher"));
                b.setImageURL(rs.getString("ImageURL"));
                b.setCateID(rs.getString("CategoryID"));
                Category c = categoryDAO.getCategoryByID(b.getCateID());
                b.setCategory(c);
                list.add(b);
            }
            return list;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public Book getBookByID(String id) {
        String sql = "select * from Books where BookID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Book b = new Book();
                b.setBookID(UUID.fromString(rs.getString("BookID")));
                b.setName(rs.getString("Name"));
                b.setAuthor(rs.getString("Author"));
                b.setStatus(rs.getString("Status"));
                b.setPublisher(rs.getString("Publisher"));
                b.setImageURL(rs.getString("ImageURL"));
                b.setCateID(rs.getString("CategoryID"));
                Category c = categoryDAO.getCategoryByID(b.getCateID());
                b.setCategory(c);
                return b;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public void addBook(Book b) {
        String sql = "INSERT INTO [dbo].[Books]\n"
                + "           ([BookID]\n"
                + "           ,[Name]\n"
                + "           ,[Author]\n"
                + "           ,[Publisher]\n"
                + "           ,[Status]\n"
                + "           ,[CategoryID]\n"
                + "           ,[ImageURL])\n"
                + "     VALUES (?,?,?,?,?,?,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            // Generate a random UUID
            UUID uuid = UUID.randomUUID();

            // Convert UUID to string representation
            String uuidString = uuid.toString();

            st.setString(1, uuidString);
            st.setString(2, b.getName());
            st.setString(3, b.getAuthor());
            st.setString(4, b.getPublisher());
            st.setString(5, "Available"); // Default is available, if rented -> Status = rented
            st.setString(6, b.getCateID() == null ? "Unknown" : b.getCateID());
            st.setString(7, b.getImageURL());
            st.executeUpdate();
            addBookStock(uuidString);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void updateBook(Book b) {
        String sql = "UPDATE [dbo].[Books]\n"
                + "     SET  [Name] =  ?\n"
                + "      ,[Author] = ?\n"
                + "      ,[Publisher] = ?\n"
                + "      ,[Status] = ?\n"
                + "      ,[CategoryID] = ?\n"
                + "      ,[ImageURL] = ?\n"
                + " WHERE BookID = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, b.getName());
            st.setString(2, b.getAuthor());
            st.setString(3, b.getPublisher());
            st.setString(4, b.getStatus());
            st.setString(5, b.getCateID());
            st.setString(6, b.getImageURL());
            st.setString(7, b.getBookID().toString());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void deleteBook(String id) {
        String sql = "DELETE FROM [dbo].[Books] WHERE BookID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, id);
            st.executeUpdate();
            deleteBookStock(id);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    // For Book Stock
    public void decreaseStock(String id) {
        String sql = "UPDATE [dbo].[BookStocks]\n"
                + "   SET [Count] = [Count] - 1 \n"
                + " WHERE BookId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, id);
            st.executeQuery();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void increaseStock(String id) {
        String sql = "UPDATE [dbo].[BookStocks]\n"
                + "   SET [Count] = [Count] + 1\n"
                + " WHERE BookId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, id);
            st.executeQuery();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public int checkCurrentStockOfBook(String id ){
        String sql = "SELECT bs.Count FROM [Library].[dbo].[BookStocks] bs WHERE bs.BookId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                return rs.getInt("Count");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return -1;
    }
    private void addBookStock(String id) {
        String sql = "INSERT INTO [dbo].[BookStocks]\n"
                + "           ([BookId]\n"
                + "           ,[Count])\n"
                + "     VALUES(?,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, id);
            st.setInt(2, 50); // default when adding new book, stock is 50
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void updateBookStatus(String id, String status) {
        String sql = "UPDATE [dbo].[Books]\n"
                + "   SET [Status] = ? \n"
                + " WHERE BookId = ? ";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, status); 
            st.setString(2, id);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void deleteBookStock(String id) {
        String sql = "DELETE FROM [dbo].[BookStocks]\n"
                + "      WHERE BookId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, id);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public BookDAO(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    // For BookStock
    public List<BookStock> getAllBookStock() {
        String sql = "select * from BookStocks";
        try {
            List<BookStock> list = new ArrayList<>();
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                BookStock bs = new BookStock(UUID.fromString(rs.getString("BookId")), rs.getInt("Count"));
                list.add(bs);
            }
            return list;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public List<Book> search(String text) {
        List<Book> list = new ArrayList<>();

        String sql = "SELECT * FROM [Library].[dbo].[Books] b\n"
                + "INNER JOIN Categories c ON b.CategoryID = c.CategoryID\n"
                + "WHERE 1=1 ";

        if (text != null && !text.equals("")) {
            sql += "and b.Name like '%" + text + "%' "
                    + "or c.Name like '%" + text + "%' "
                    + "or b.BookID like '%" + text + "%' "
                    + "or b.Author like '%" + text + "%' ";
        }
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery(); // for only select
            while (rs.next()) {
                Book b = new Book();
                String bookID_raw = rs.getString("BookID");
                b.setBookID(UUID.fromString(bookID_raw));
                b.setName(rs.getString("Name"));
                b.setAuthor(rs.getString("Author"));
                b.setStatus(rs.getString("Status"));
                b.setPublisher(rs.getString("Publisher"));
                b.setImageURL(rs.getString("ImageURL"));
                b.setCateID(rs.getString("CategoryID"));
                Category c = categoryDAO.getCategoryByID(b.getCateID());
                b.setCategory(c);
                list.add(b);
            }
            return list;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;

    }

    // For BookReserve
    public String getUserEmailOfBookReserve(String id) {
        String sql = "SELECT us.Email\n"
                + "  FROM [Library].[dbo].[BookReserves] br\n"
                + "  INNER JOIN Users us on br.UserID = us.UserID WHERE br.Id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getString("Email");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public List<String> getALLISBN() {
        String sql = "SELECT [ISBN] FROM [Library].[dbo].[BookReserves]";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            List<String> list = new ArrayList<>();
            while (rs.next()) {
                list.add(rs.getString("ISBN"));
            }
            return list;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void updateBookReserve(String id, String ISBN, String status) {
        String sql = "UPDATE [dbo].[BookReserves]\n"
                + "   SET [ISBN] = ?\n"
                + "      ,[Status] = ?\n"
                + " WHERE Id= ? ";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, ISBN);
            st.setString(2, status);
            st.setString(3, id);
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public List<BookReserve> getBookReservesByID(String id) {
        String sql = "SELECT [Id]\n"
                + "      ,[UserID]\n"
                + "      ,[BookID]\n"
                + "      ,[ISBN]\n"
                + "      ,[Status]\n"
                + "      ,[CreatedDate]\n"
                + "  FROM [dbo].[BookReserves] WHERE UserID = ?";
        try {
            List<BookReserve> list = new ArrayList<>();
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                BookReserve bookReserve = new BookReserve();
                bookReserve.setId(UUID.fromString(rs.getString("Id")));
                bookReserve.setUserID(UUID.fromString(rs.getString("UserID")));
                bookReserve.setBookID(UUID.fromString(rs.getString("BookID")));
                bookReserve.setStatus(rs.getString("Status"));
                bookReserve.setISBN(rs.getString("ISBN"));
                bookReserve.setBook(getBookByID(rs.getString("BookID")));
                UserDAO userDAO = new UserDAO();
                bookReserve.setUser(userDAO.getUserByID(rs.getString("UserID")));
                // Define the format of the date string (assuming it's in "yyyy-MM-dd" format)
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                // Parse the string to LocalDate
                String createdDate = rs.getString("CreatedDate").substring(0, 10);
                LocalDate localDate = LocalDate.parse(createdDate, formatter);
                bookReserve.setCreatedDate(localDate);
                list.add(bookReserve);
            }
            return list;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public List<BookReserve> getAllBookReserves() {
        String sql = "SELECT [Id]\n"
                + "      ,[UserID]\n"
                + "      ,[BookID]\n"
                + "      ,[ISBN]\n"
                + "      ,[Status]\n"
                + "      ,[CreatedDate]\n"
                + "  FROM [dbo].[BookReserves]";
        try {
            List<BookReserve> list = new ArrayList<>();
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                BookReserve bookReserve = new BookReserve();
                bookReserve.setId(UUID.fromString(rs.getString("Id")));
                bookReserve.setUserID(UUID.fromString(rs.getString("UserID")));
                bookReserve.setBookID(UUID.fromString(rs.getString("BookID")));
                bookReserve.setStatus(rs.getString("Status"));
                bookReserve.setISBN(rs.getString("ISBN"));
                bookReserve.setBook(getBookByID(rs.getString("BookID")));
                UserDAO userDAO = new UserDAO();
                bookReserve.setUser(userDAO.getUserByID(rs.getString("UserID")));
                // Define the format of the date string (assuming it's in "yyyy-MM-dd" format)
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                // Parse the string to LocalDate
                String createdDate = rs.getString("CreatedDate").substring(0, 10);
                LocalDate localDate = LocalDate.parse(createdDate, formatter);
                bookReserve.setCreatedDate(localDate);
                list.add(bookReserve);
            }
            return list;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void createNewBookReserve(BookReserve bookReserve) {
        String sql = "INSERT INTO [dbo].[BookReserves]\n"
                + "           ([Id]\n"
                + "           ,[UserID]\n"
                + "           ,[BookID]\n"
                + "           ,[ISBN]\n"
                + "           ,[Status]\n"
                + "           ,[CreatedDate])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            // Generate a random UUID
            UUID uuid = UUID.randomUUID();
            // Convert UUID to string representation
            String uuidString = uuid.toString();
            st.setString(1, uuidString);
            st.setString(2, bookReserve.getUserID().toString());
            st.setString(3, bookReserve.getBookID().toString());
            st.setString(4, "null");
            st.setString(5, bookReserve.getStatus());
            LocalDate createdDate = LocalDate.now();
            st.setString(6, createdDate.toString());
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public List<ReservationPerDay> getReservationPerDays() {
        String sql = "SELECT count(*) as [Number of Reservations] ,[CreatedDate]\n"
                + "  FROM [Library].[dbo].[BookReserves] br\n"
                + "  GROUP BY br.CreatedDate";
        try {
            List<ReservationPerDay> list = new ArrayList<>();
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                ReservationPerDay reservationPerDay = new ReservationPerDay();
                reservationPerDay.setDay(rs.getString("CreatedDate").substring(0, 10));
                reservationPerDay.setCount(rs.getInt("Number of Reservations"));
                list.add(reservationPerDay);
            }
            return list;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
