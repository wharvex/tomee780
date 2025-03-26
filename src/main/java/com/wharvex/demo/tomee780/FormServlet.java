package com.wharvex.demo.tomee780;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "formServlet", value = "/form-servlet")
public class FormServlet extends HttpServlet {
  private Configuration cfg;

  @Override
  public void init() throws ServletException {
    super.init();
    cfg = new Configuration(Configuration.VERSION_2_3_34);
    cfg.setServletContextForTemplateLoading(getServletContext(),
        "/WEB-INF");
    cfg.setDefaultEncoding("UTF-8");
  }

  @Override
  protected void doGet(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException,
      IOException {
    response.setContentType("text/html;charset=UTF-8");
    try (PrintWriter out = response.getWriter()) {
      Template template = cfg.getTemplate("form.ftl");
      Map<String, Object> root =
          new HashMap<>(); // Data model (empty in this case)
      template.process(root, out);
    } catch (TemplateException e) {
      throw new ServletException("Error processing FreeMarker template", e);
    }
  }
}
