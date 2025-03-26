package com.wharvex.demo.tomee780;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/processForm")
public class ProcessFormServlet extends HttpServlet {

  @Override
  protected void doPost(HttpServletRequest request,
                        HttpServletResponse response)
      throws ServletException, IOException {
    String name = request.getParameter("name");
    String email = request.getParameter("email");

    response.setContentType("text/html;charset=UTF-8");
    try (PrintWriter out = response.getWriter()) {
      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("<title>Form Submission Result</title>");
      out.println("</head>");
      out.println("<body>");
      out.println("<h1>Form Data Received</h1>");
      out.println("<p>Name: " + name + "</p>");
      out.println("<p>Email: " + email + "</p>");
      // Process the data further (e.g., save to a database)
      out.println("</body>");
      out.println("</html>");
    }
  }
}