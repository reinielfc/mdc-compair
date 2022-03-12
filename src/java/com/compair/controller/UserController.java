/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.compair.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.compair.business.User;
import com.compair.data.UserIO;

/**
 *
 * @author rei
 */
@WebServlet(name = "UserController", urlPatterns = { "/login" })
public class UserController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = logIn(request, response);

        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }

    private String logIn(HttpServletRequest request, HttpServletResponse response) {
        String url = "/login.jsp";
        String message = "";
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username == null || password == null) {
            return url;
        }

        if (username.isBlank() || password.isBlank()) {
            message = "* Missing credentials.";
        } else {
            String filePath = getServletContext().getRealPath("/WEB-INF/login_credentials.txt");
            User user = UserIO.getUser(filePath, username, password);
            if (user == null) {
                message = "* Incorrect credentials.";
            } else {
                request.getSession().setAttribute("user", user);
                url = "/welcome.jsp";
            }
        }

        request.setAttribute("message", message);

        return url;
    }

}