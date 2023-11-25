package com.compair.controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.compair.business.User;
import com.compair.data.UserDB;
import com.compair.util.PasswordUtil;

/**
 *
 * @author rei
 */
@WebServlet(name = "UserServlet", urlPatterns = {
        "/admin/user", "/admin/user/add", "/admin/user/edit"
})
public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        String url = "";

        if (requestURI.endsWith("/user")) {
            request.setAttribute("DBUserList", UserDB.selectAll());
            url = "/admin/user-view-all.jsp";
        } else if (requestURI.endsWith("/add")) {
            url = "/admin/user-add.jsp";
        }

        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        String url = "";

        if (requestURI.endsWith("/user")) {
            request.setAttribute("DBUserList", UserDB.selectAll());
            url = "/admin/user-view-all.jsp";
        } else if (requestURI.endsWith("/add")) {
            url = add(request, response);
        } else if (requestURI.endsWith("/edit")) {
            url = edit(request);
        }

        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }

    private String add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "/admin/user-add.jsp";

        User user = makeValidUser(request);

        if (user == null) {
            return url;
        }

        String email = user.getEmail();

        if (UserDB.hasEmail(email)) {
            user.setPassword("");
            request.setAttribute("DBUser", user);
            request.setAttribute("emailValidationMessage",
                    "Email already in database");
            return url;
        }

        UserDB.insert(user);
        request.setAttribute("message", "User with email <strong>" + email + "</strong> was added.");

        // send email to user
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/email");
        request.setAttribute("recipient", user);
        requestDispatcher.include(request, response);

        return "/admin/user";
    }

    private User makeValidUser(HttpServletRequest request) {
        // get fields from request
        String email = request.getParameter("email");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");

        // make new
        User user = new User();
        user.setEmail(email);
        user.setUsername(username);
        user.setFirstName(firstName);
        user.setLastName(lastName);

        boolean isValid = true;

        // validate
        if (!UserDB.isEmailValid(email)) {
            request.setAttribute("emailValidationMessage",
                    "Enter a valid email.");
            isValid = false;
        }

        if (!UserDB.isNameValid(username)) {
            request.setAttribute("usernameValidationMessage",
                    "User name must not exceed 128 characters.");
            isValid = false;
        }

        if (!UserDB.isNameValid(firstName)) {
            request.setAttribute("firstNameValidationMessage",
                    "First name must not exceed 128 characters.");
            isValid = false;
        }

        if (!UserDB.isNameValid(lastName)) {
            request.setAttribute("lastNameValidationMessage",
                    "Last name must not exceed 128 characters.");
            isValid = false;
        }

        String action = request.getParameter("action");
        boolean passwordIsUpdated = password != null
                && !(password.isBlank() && action != null && action.equals("save"));

        if (passwordIsUpdated && !UserDB.isPasswordValid(password)) {
            request.setAttribute("passwordValidationMessage",
                    "Password must contain at least one upper case letter, one lower case letter, and a number, and a mininum of 8 characters.");
            isValid = false;
        }

        // set in request if not valid
        if (!isValid) {
            request.setAttribute("DBUser", user);
            return null;
        }

        // generate password
        if (passwordIsUpdated) {
            try {
                password = PasswordUtil.generate(password);
                user.setPassword(password);
            } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
                request.setAttribute("passwordValidationMessage",
                        "Could not generate password.");
                isValid = false;
            }
        } else {
            String originalEmail = request.getParameter("originalEmail");
            String originalPassword = UserDB.select(originalEmail).getPassword();
            user.setPassword(originalPassword);
        }

        // otherwise return
        return user;
    }

    private String edit(HttpServletRequest request) {
        String action = request.getParameter("action");

        if (action != null && action.equals("save")) {
            return doEditActionSave(request);
        } else {
            String email = request.getParameter("email");
            User user = UserDB.select(email);

            request.setAttribute("DBUser", user);
            return "/admin/user-edit.jsp";
        }
    }

    private String doEditActionSave(HttpServletRequest request) {
        String url = "/admin/user-edit.jsp";

        User user = makeValidUser(request);

        if (user == null) {
            return url;
        }

        String originalEmail = request.getParameter("originalEmail");
        String email = user.getEmail();

        if (!email.equals(originalEmail) && UserDB.hasEmail(email)) {
            request.setAttribute("DBUser", user);
            request.setAttribute("emailValidationMessage",
                    "Email already in database");
            return url;
        }

        UserDB.update(originalEmail, user);
        request.setAttribute("message", "User with email <strong>" + email + "</strong> was updated.");

        return "/admin/user";
    }

}
