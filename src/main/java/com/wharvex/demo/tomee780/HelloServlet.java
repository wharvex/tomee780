package com.wharvex.demo.tomee780;

import java.io.*;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import javax.naming.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
  private String message1 = "Hello World!";
  private String message2;
  private String message3;

  public void buildMessage2() {
    StringBuilder messageBuilder2 = new StringBuilder();
    String jndiName = "java:";
    messageBuilder2.append("<ul>");
    try {
      Context initContext = new InitialContext();
      getNamesRecursive(jndiName, messageBuilder2, initContext);
    } catch (NamingException e) {
      messageBuilder2.append(
          "<li>Encountered NamingException creating InitialContext</li>");
    }
    messageBuilder2.append("</ul>");
    message2 = messageBuilder2.toString();
  }

  public void getNamesRecursive(
      String name,
      StringBuilder messageBuilder, Context context) {
    try {
      NamingEnumeration<NameClassPair> nameChoices =
          context.list(name);
      if (nameChoices == null) {
        messageBuilder.append("<li>context.list returned null</li>");
        return;
      }
      if (!nameChoices.hasMore()) {
        messageBuilder.append("<li>context.list returned no names</li>");
        return;
      }
      while (nameChoices.hasMore()) {
        // Get data.
        NameClassPair nameClassPair = nameChoices.next();
        String newName = nameClassPair.getName();
        String className = "className: " + nameClassPair.getClassName();
        String nameContinuationToken = name.equals("java:") ? "" : "/";

        // Accumulate list.
        messageBuilder.append("<li>").append(newName).append(" (")
            .append(className).append(")").append("</li>");

        // Nest and recurse.
        messageBuilder.append("<ul>");
        getNamesRecursive(name + nameContinuationToken + newName,
            messageBuilder, context);
        messageBuilder.append("</ul>");
      }
    } catch (NamingException e) {
      messageBuilder.append("<li>Encountered NamingException</li>");
    }
  }

  public void init() {
    buildMessage2();
  }

  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    response.setContentType("text/html");

    // Hello
    PrintWriter out = response.getWriter();
    out.println("<html><body>");
    out.println("<h1>" + message1 + "</h1>");
    out.println("<p>" + message2 + "</p>");
    out.println("<h2>" + message3 + "</h2>");
    out.println("</body></html>");
  }

  public void destroy() {
  }
}