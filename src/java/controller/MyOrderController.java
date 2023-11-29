/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.OrderDAO;
import dao.OrderDetailDAO;
import dao.ProductVariantDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import model.Account;
import model.Order;
import model.OrderDetail;
import model.ProductVariant;
import model.Status;

/**
 *
 * @author kienk
 */
public class MyOrderController extends HttpServlet {

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
            out.println("<title>Servlet MyCartController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MyCartController at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        if (account == null) {
            request.getRequestDispatcher("sign-in").forward(request, response);
        } else {
            OrderDAO ord = new OrderDAO();
            String status = request.getParameter("filter") == null ? "1" : request.getParameter("filter");
            String searchString = request.getParameter("sr") == null ? "iphone" : request.getParameter("sr");
            ProductVariantDAO pvd = new ProductVariantDAO();
            List<Integer> listProductId = pvd.getListIdProductByName(searchString);
            List<Order> orderList = ord.getOrderList(account.getId(), status);
            OrderDetailDAO odd = new OrderDetailDAO();
            List<Integer> display = new ArrayList<>();
            for (Order order : orderList) {
                List<OrderDetail> od = odd.getAllOrderDetail(order.getId());
                for (OrderDetail orderDetail : od) {
                    ProductVariant pv = pvd.getProductVariantByID(orderDetail.getProductId());
                    if (listProductId.contains(pv.getProductId())){
                        display.add(order.getId());
                    }
                }
            }
            List<Status> statusList = ord.getAllStatus();
            request.setAttribute("orderList", orderList);
            request.setAttribute("statusList", statusList);
            request.setAttribute("productId", display);
            request.getRequestDispatcher("screens/User_MyOrder.jsp").forward(request, response);
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
