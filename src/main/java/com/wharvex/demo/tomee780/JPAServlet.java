package com.wharvex.demo.tomee780;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "jpaServlet", value = "/jpa-servlet")
public class JPAServlet extends HttpServlet {
  private String message0 = "JPA Servlet";
  private String message1;

  public void init() {
    StringBuilder messageBuilder = new StringBuilder();
    try (EntityManagerFactory emf = Persistence.createEntityManagerFactory(
        "MyOracleDBPU");
         EntityManager em = emf.createEntityManager()
    ) {
      messageBuilder.append("EntityManagerFactory created: ").append(emf)
          .append("<br>");
      messageBuilder.append("EntityManager created: ").append(em)
          .append("<br>");
      em.getTransaction().begin();
      messageBuilder.append("Transaction started").append("<br>");
      Employee employee = new Employee();
      employee.setId("1");
      employee.setFirstName("Clare");
      employee.setLastName("Churcher");
      em.persist(employee);
      em.getTransaction().commit();
      messageBuilder.append("Transaction committed").append("<br>");
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
