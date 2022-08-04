package com.compair.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.compair.business.User;

public class UserDB {

    public static int insert(User user) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query = "INSERT INTO User "
                + "(email, username, password, firstName, lastName) "
                + "VALUES (?, ?, ?, ?, ? )";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getUsername());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getFirstName());
            ps.setString(5, user.getLastName());

            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static int update(String email, User user) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query = "UPDATE User SET "
                + "email = ?, "
                + "username = ?, "
                + "password = ?, "
                + "firstName = ?, "
                + "lastName = ? "
                + "WHERE email = ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getUsername());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getFirstName());
            ps.setString(5, user.getLastName());
            ps.setString(6, email);

            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static User select(String email) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM User WHERE Email = ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, email);
            rs = ps.executeQuery();
            User user = null;
            if (rs.next()) {
                user = new User();
                user.setEmail(rs.getString("email"));
                user.setUsername(rs.getString("username"));;
                user.setPassword(rs.getString("password"));
                user.setFirstName(rs.getString("firstName"));
                user.setLastName(rs.getString("lastName"));
            }
            return user;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);

        }

    }

    public static List<User> selectAll() {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM User";

        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            List<User> userList = new ArrayList<>();
            while (rs.next()) {
                User user = new User();
                user.setEmail(rs.getString("email"));
                user.setUsername(rs.getString("username"));;
                user.setPassword(rs.getString("password"));
                user.setFirstName(rs.getString("firstName"));
                user.setLastName(rs.getString("lastName"));
                userList.add(user);
            }
            return userList;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static boolean hasEmail(String email) {
        return select(email) != null;
    }

    public static boolean isEmailValid(String email) {
        return email != null && !email.isBlank() && email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }

    public static boolean isNameValid(String name) {
        return name != null && !name.isBlank() && name.length() < 128;
    }

    public static boolean isPasswordValid(String password) {
        if (password == null || password.isBlank()) {
            return false;
        }

        String[] requirements = new String[] { "\\S{8,}", "[A-Z]", "[a-z]", "[0-9]" };

        for (String requirement : requirements) {
            // if password does not match requirement regex
            if (!Pattern.compile(requirement).matcher(password).find()) {
                return false; // it is invalid
            }
        }

        return true;
    }
}
