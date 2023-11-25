package com.compair.controller;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.compair.business.User;
import com.compair.util.EmailUtil;

@WebServlet(name = "EmailController", urlPatterns = { "/email" })
public class EmailServlet extends HttpServlet {

    private static String smtpHost;
    private static String smtpPort;
    private static String smtpUsername;
    private static String smtpPassword;

    public void init() {
        // SMTP server from web.xml file
        ServletContext context = getServletContext();
        smtpHost = context.getInitParameter("SMTP_HOST");
        smtpPort = context.getInitParameter("SMTP_PORT");
        smtpUsername = context.getInitParameter("SMTP_USERNAME");
        smtpPassword = context.getInitParameter("SMTP_PASSWORD");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // get user stored in session
        User recipient = (User) request.getAttribute("recipient");

        // prepare email
        String toAddress = recipient.getEmail();
        String subject = "CompAir: Welcome " + recipient.getFirstName() + "!";
        String body = buildCodeEmailBody(recipient.getFirstName());

        // send email
        try {
            EmailUtil.send(
                    smtpHost, smtpPort,
                    smtpUsername, smtpPassword,
                    toAddress, subject, body);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private String buildCodeEmailBody(String recipientFirstName) {
        return new StringBuilder()
                .append("<h1><strong>CompAir:</strong> Account Created ").append(recipientFirstName).append("!</h1>")
                .append("<p>An account was created in your name by an administrator!</p>")
                .toString();
    }

}
