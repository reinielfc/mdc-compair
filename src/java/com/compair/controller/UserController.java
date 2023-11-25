package com.compair.controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.compair.business.User;
import com.compair.data.UserDB;
import com.compair.util.PasswordUtil;

/**
 *
 * @author rei
 */
@WebServlet(name = "UserController", urlPatterns = { "/login", "/welcome" })
public class UserController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("#DEBUG# [UserController] GET: " + request.getContextPath());

        String requestURI = request.getRequestURI();
        String url = "/login.jsp";

        if (requestURI.endsWith("/welcome")) {
            url = welcome(request);
        }

        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }

    private String welcome(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("user") == null) {
            request.setAttribute("message", "Please log in to view your account details.");
            return "/login.jsp";
        }

        return "/welcome.jsp";
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("#DEBUG# [UserController] POST: " + request.getContextPath());

        String url = "/login.jsp";
        String requestURI = request.getRequestURI();

        if (requestURI.endsWith("/login")) {
            url = logIn(request, response);
        } else if (requestURI.endsWith("/welcome")) {
            url = welcome(request);
        }

        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }

    private String logIn(HttpServletRequest request, HttpServletResponse response) {
        String url = "/login.jsp";
        String message = "Incorrect credentials.";

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user = UserDB.select(email);

        try {
            if (user != null && PasswordUtil.verify(password, user.getPassword())) {
                request.getSession().setAttribute("user", user);
                return "/welcome";
            }
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
            message = "Could not authenticate.";
        }
        
        request.setAttribute("email", email);
        request.setAttribute("message", message);

        return url;
    }

}