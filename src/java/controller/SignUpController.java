/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.AccountDAO;
import dao.SecurityDAO;
import dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.SecurityQuestion;

/**
 *
 * @author 84834
 */
public class SignUpController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AccountDAO accDao = new AccountDAO();
        UserDAO userDao = new UserDAO();
        SecurityDAO securityDao = new SecurityDAO();
        //Lay du lieu tu form nguoi dung nhap        
        String userName = (String) req.getParameter("username");
        String passWord = (String) req.getParameter("password");
        int ques_id = -1;
        try {
            ques_id = Integer.parseInt(req.getParameter("question"));
        } catch (Exception e) {
            System.out.println(e);
        }
        String name = req.getParameter("name");
        String answer = req.getParameter("answer");
        String phone = req.getParameter("phone");
        //Kiem tra xem tai khoan do da duoc tao ra hay chua    
        int succes = accDao.getAccountID(userName);
        // Neu tai khoan da ton tai thi se tra ve voi 1 mess    
        if (succes != 0) {
            ArrayList<SecurityQuestion> list = accDao.getSecurityQuestion();
            req.setAttribute("quest", list);
            req.setAttribute("mess", "This username already exist");
            req.getRequestDispatcher("screens/signUp.jsp").forward(req, resp);
        } //Neu chua duoc tao thi se tao tai khoan tu username va password    
        else {
            succes = accDao.createAccount(userName, passWord);
            // Neu tao that bai thi se quay tro ve trang dang ki cung voi tin nhan loi thuoc ve he thong           
            if (succes == 0) {
                ArrayList<SecurityQuestion> list = accDao.getSecurityQuestion();
                req.setAttribute("quest", list);
                req.setAttribute("mess", "We so sorry, may be website has some problem!");
                req.getRequestDispatcher("screens/signUp.jsp").forward(req, resp);
            } //Neu chay thanh cong thi se tiep tuc tao truong thong tin trong bang user
            else {
                int id = accDao.getAccountID(userName);
                succes = userDao.createUser(id, phone, name);
                // Neu tao that bai thi se quay tro ve trang dang ki cung voi tin nhan loi thuoc ve he thong
                if (succes == 0) {
                    ArrayList<SecurityQuestion> list = accDao.getSecurityQuestion();
                    req.setAttribute("quest", list);
                    req.setAttribute("mess", "We are sorry, may be website has some problem!");
                    req.getRequestDispatcher("screens/signUp.jsp").forward(req, resp);
                } //Neu tao thanh cong se tao them 1 truong trong bang Security
                else {
                    if (ques_id != -1) {
                        succes = securityDao.insertSecurity(ques_id, id, answer);
                        req.setAttribute("mess", ques_id + "  " + id + "  " + answer);
                        if (succes == 1) {
                            resp.sendRedirect("sign-in");
                        }
                        
                    }
                }
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AccountDAO dao = new AccountDAO();
        ArrayList<SecurityQuestion> list = dao.getSecurityQuestion();
        req.setAttribute("quest", list);
        req.getRequestDispatcher("screens/signUp.jsp").forward(req, resp);
    }

}
