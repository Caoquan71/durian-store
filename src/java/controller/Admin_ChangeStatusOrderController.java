/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.OrderDAO;
import dao.OrderHistoryDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.OrderHistory;

/**
 *
 * @author kienk
 */
public class Admin_ChangeStatusOrderController extends HttpServlet {

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
            out.println("<title>Servlet Admin_ChangeStatusOrderController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Admin_ChangeStatusOrderController at " + request.getContextPath() + "</h1>");
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
        OrderDAO od = new OrderDAO();
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        int statusId = Integer.parseInt(request.getParameter("statusId"));
        OrderHistoryDAO ohd = new OrderHistoryDAO();
        if (statusId == 2) {
            OrderHistory oh = ohd.orderHistoryIsExisted(orderId, "Order placed");
            if (oh == null) {
                od.updateStatus(orderId, statusId);
                ohd.createOrderHistory(orderId, "Order placed");
            }
        } else if (statusId == 3) {
            OrderHistory oh = ohd.orderHistoryIsExisted(orderId, "Order paid");
            if (oh == null) {
                od.updateStatus(orderId, statusId);
                ohd.createOrderHistory(orderId, "Order paid");
            }
        } else if (statusId == 4) {
            OrderHistory oh = ohd.orderHistoryIsExisted(orderId, "Delivery in progress");
            if (oh == null) {
                od.updateStatus(orderId, statusId);
                ohd.createOrderHistory(orderId, "Delivery in progress");
            }
        } else if (statusId == 5) {
            OrderHistory oh = ohd.orderHistoryIsExisted(orderId, "Successful delivery");
            if (oh == null) {
                od.updateStatus(orderId, statusId);
                ohd.createOrderHistory(orderId, "Successful delivery");
            }
        } else if (statusId == 6) {
            OrderHistory check = ohd.orderHistoryIsExisted(orderId, "Successful delivery");
            if (check == null) {
                OrderHistory oh = ohd.orderHistoryIsExisted(orderId, "Order has been canceled");
                if (oh == null) {
                    od.updateStatus(orderId, statusId);
                    ohd.createOrderHistory(orderId, "Order has been canceled");
                }
            }

        } else if (statusId == 7) {
            OrderHistory oh = ohd.orderHistoryIsExisted(orderId, "Order has been refunded");
            if (oh == null) {
                od.updateStatus(orderId, statusId);
                ohd.createOrderHistory(orderId, "Order has been refunded");
            }
        }
        String redirect = request.getParameter("redirect") == null?"admin":"user";
        if (redirect.equals("user")){
            response.sendRedirect("my-cart");
        }else{
            response.sendRedirect("admin-order");
        }
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
        processRequest(request, response);
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
