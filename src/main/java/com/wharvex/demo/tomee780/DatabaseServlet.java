package com.wharvex.demo.tomee780;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

@WebServlet(name = "databaseServlet", value = "/database-servlet")
public class DatabaseServlet extends HttpServlet {
  private String message0 = "Database Servlet";
  private String message1;

  public void init() {
    StringBuilder messageBuilder = new StringBuilder();
    try {
      Context initContext = new InitialContext();
      Context envContext = (Context) initContext.lookup("java:/comp/env");
      DataSource ds = (DataSource) envContext.lookup("MyOracleDB");
      try (Connection connection = ds.getConnection()) {
        messageBuilder.append("Connection established: ").append(connection);
      }
    } catch (Exception e) {
      messageBuilder.append(e);
    }
    message1 = messageBuilder.toString();
  }

  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    out.println("<html><body>");
    out.println("<h1>" + message0 + "</h1>");
    out.println("<p>" + message1 + "</p>");
    out.println("</body></html>");
  }

  public void destroy() {
  }
}
