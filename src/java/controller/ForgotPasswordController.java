/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.AccountDAO;
import dao.SecurityDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import model.Security;
import model.SecurityQuestion;

/**
 *
 * @author kienk
 */
public class ForgotPasswordController extends HttpServlet {

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
            out.println("<title>Servlet forgotPasswordController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet forgotPasswordController at " + request.getContextPath() + "</h1>");
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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        AccountDAO dao = new AccountDAO();
        ArrayList<SecurityQuestion> list = dao.getSecurityQuestion();
        req.setAttribute("quest", list);
        req.getRequestDispatcher("screens/forgotPassword.jsp").forward(req, resp);
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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        AccountDAO accDao = new AccountDAO();
        SecurityDAO securityDao = new SecurityDAO();
        //Lay du lieu thu form cua nguoi dung    
        String username = req.getParameter("username");
        int id_question = Integer.parseInt(req.getParameter("question"));
        String anwser = req.getParameter("answer");
        String password = req.getParameter("password");
        //Check xem user do co ton tai hay khong
        int accountID = accDao.getAccountID(username);
        if (accountID == 0) {
            AccountDAO dao = new AccountDAO();
            ArrayList<SecurityQuestion> list = dao.getSecurityQuestion();
            req.setAttribute("quest", list);
            req.setAttribute("mess", "This username does not exist!");
            req.getRequestDispatcher("screens/forgotPassword.jsp");
        } else {
            Security infor = securityDao.getQuestAnswer(accountID);
            if (infor.getQuestion_id() != id_question
                    || !anwser.equalsIgnoreCase(infor.getAnswer())) {
                AccountDAO dao = new AccountDAO();
                ArrayList<SecurityQuestion> list = dao.getSecurityQuestion();
                req.setAttribute("quest", list);
                req.setAttribute("mess", "Your answer is incorrect!");
                req.getRequestDispatcher("screens/forgotPassword.jsp").forward(req, resp);
            } else {
                int success = accDao.updatePassword(accountID, password);
                if (success == 0) {
                    AccountDAO dao = new AccountDAO();
                    ArrayList<SecurityQuestion> list = dao.getSecurityQuestion();
                    req.setAttribute("mess", "Update password success!");
                    req.getRequestDispatcher("screens/forgotPassword.jsp").forward(req, resp);

                } else {
                    resp.sendRedirect("/Durian_Shop/sign-in");
                }

            }
        }
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