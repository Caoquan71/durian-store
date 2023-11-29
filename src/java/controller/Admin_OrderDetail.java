/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.ColorDAO;
import dao.OrderDAO;
import dao.OrderDetailDAO;
import dao.ProductVariantDAO;
import dao.ProductVariantInformationDAO;
import dao.StorageDAO;
import dao.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import model.Account;
import model.Order;
import model.OrderDetail;
import model.ProductVariantInfomation;
import model.User;

/**
 *
 * @author kienk
 */
public class Admin_OrderDetail extends HttpServlet {

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
            out.println("<title>Servlet Admin_OrderDetail</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Admin_OrderDetail at " + request.getContextPath() + "</h1>");
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
         OrderDAO orderDao = new OrderDAO();
        OrderDetailDAO detailDao = new OrderDetailDAO();
        UserDAO userDao = new UserDAO();
        ColorDAO colorDao = new ColorDAO();
        StorageDAO stoDao = new StorageDAO();
        ProductVariantDAO variantDao = new ProductVariantDAO();
        ProductVariantInformationDAO informationDao = new ProductVariantInformationDAO();
        //Hashmap chua thong tin ve id orderDetail va noi dung cuar orderDetail do    
        HashMap<Integer, ProductVariantInfomation> InformationMap = new HashMap<>();
        //HashMap chua so luong con lai trong kho voi key la variantId
        HashMap<Integer, Integer> remain = new HashMap<>();
        //HashMap de lay ten cua Color 
        Map<Integer, String> color = colorDao.getHashMapColor();
        //HashMap de lay ten cua Storage
        Map<Integer, String> storage = stoDao.getHashMapStorage();
        //HashMap chhua gia cua tung variant voi ket laf variantId
        HashMap<Integer, Double> price = new HashMap<>();
        
        int userId = Integer.parseInt( request.getParameter("userId"));
        User user = userDao.getUserById(userId);
        int orderId = Integer.parseInt( request.getParameter("orderId"));
        ArrayList<OrderDetail> list = new ArrayList<>();
            list = detailDao.getAllOrderDetail(orderId);
        for (OrderDetail o : list) {
            ProductVariantInfomation information = informationDao.getDetailInformation(o.getProductId());
            int number = variantDao.getRemainById(o.getProductId());
            InformationMap.put(o.getId(), information);
            remain.put(o.getProductId(), number);
            price.put(o.getProductId(), variantDao.getPriceById(o.getProductId()));
        }
        request.setAttribute("userId", userId);
        request.setAttribute("orderId", orderId);
        request.setAttribute("color", color);
        request.setAttribute("storage", storage);
        request.setAttribute("size", InformationMap.size());
        request.setAttribute("map", InformationMap);
        request.setAttribute("list", list);
        request.setAttribute("remain", remain);
        request.setAttribute("remain", remain);
        request.getRequestDispatcher("screens/Admin_OrderDetail.jsp").forward(request, response);
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
