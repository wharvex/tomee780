package com.wharvex.demo.tomee780;

import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
  private String message;

  public void buildMessage1() {
    StringBuilder messageBuilder = new StringBuilder();
    messageBuilder.append("Hello World!").append("<br>");
    try {
      Context initContext = new InitialContext();
      Context envContext = (Context) initContext.lookup("java:/comp/env");
      DataSource ds = (DataSource) envContext.lookup("jdbc/myoracle");
      Connection conn = ds.getConnection();
      if (conn != null) {
        messageBuilder.append("Connected to the database!").append("<br>");
        System.out.println("Connected to the database!");
        Statement statement = conn.createStatement();
        ResultSet resultSet =
            statement.executeQuery("select * from testtable");
        messageBuilder.append("Select * from testtable").append("<br>");
        while (resultSet.next()) {
          String testColVal = resultSet.getString("testcol");
          messageBuilder.append("testcol: ").append(testColVal)
              .append("<br>");
        }
        resultSet.close();
        statement.close();
        conn.close();
      } else {
        messageBuilder.append("Failed to connect to the database!")
            .append("<br>");
        System.out.println("Failed to make connection!");
      }
    } catch (NamingException | SQLException e) {
      throw new RuntimeException(e);
    }
    message = messageBuilder.toString();
  }

  public void init() {
    message = "Hello World!";
  }

  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    response.setContentType("text/html");

    // Hello
    PrintWriter out = response.getWriter();
    out.println("<html><body>");
    out.println("<h1>" + message + "</h1>");
    out.println("</body></html>");
  }

  public void destroy() {
  }
}