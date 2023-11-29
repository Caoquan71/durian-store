/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.BlogDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Account;
import model.Blog;
import model.BlogCategory;

/**
 *
 * @author kienk
 */
public class AddNewBlogController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AddNewBlogController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddNewBlogController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        BlogDAO bd = new BlogDAO();
        List<BlogCategory> listBC = bd.getListBlogCategory();
        String cateDefault = request.getParameter("cateid");
        request.setAttribute("listCate", listBC);
        request.setAttribute("cateDefault", cateDefault);
        request.getRequestDispatcher("screens/AddNewBlog.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       BlogDAO bd = new BlogDAO();
       int cateId = Integer.parseInt(request.getParameter("cate"));
       String title = request.getParameter("title");
       String cover = request.getParameter("cover");
       int quantitySub = Integer.parseInt(request.getParameter("quantitySub"));
       HttpSession session = request.getSession();
       Account acc = (Account) session.getAttribute("account");
       bd.createBlog(title, cateId, acc.getId(), cover);
       if (quantitySub > 0){
           Blog blog = bd.getLastBlog();
           for (int i = 1; i<=quantitySub; i++){
               String header = request.getParameter("header"+i);
               String content = request.getParameter("content"+i);
               String img = request.getParameter("img"+i);
               bd.createSubBlog(title, content, img, blog.getId());
           }
       }
       response.sendRedirect("admin-blog-list?id="+cateId);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
