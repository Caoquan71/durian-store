/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.ColorDAO;
import dao.OrderDAO;
import dao.OrderDetailDAO;
import dao.OrderHistoryDAO;
import dao.ProductVariantDAO;
import dao.ProductVariantInformationDAO;
import dao.SaleDAO;
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
 * @author 84834
 */
public class CheckoutController extends HttpServlet {

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
//        response.setContentType("text/html;charset=UTF-8");
//        try ( PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet CheckoutController</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet CheckoutController at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        OrderDetailDAO detailDao = new OrderDetailDAO();
        OrderDAO orderDao = new OrderDAO();
        ProductVariantDAO variantDao = new ProductVariantDAO();
        OrderHistoryDAO ohd = new OrderHistoryDAO();

        double totalPrice = 0;
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        int status = Integer.parseInt(request.getParameter("paymentMethod"));
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        ArrayList<OrderDetail> list = detailDao.getAllOrderDetail(orderId);
        // Lay quantity va update && lay price cua tung san pham trong Detail
        for (OrderDetail detail : list) {
            int index = list.indexOf(detail);
            int quantity = Integer.parseInt(request.getParameter("quantity[" + index + "]"));
            float salePercent = Float.parseFloat(request.getParameter("salePercent[" + index + "]"));
            int success = detailDao.updateQuantity(detail.getId(), quantity);
            if (success == 0) {
                response.sendRedirect("screens/Errorpage.jsp");
            }
            // Tinh total Price    
            totalPrice += (variantDao.getPriceById(detail.getProductId()) * quantity) * (1 - salePercent / 100);
            // Update quantity
            success = variantDao.minusQuantity(detail.getProductId(), quantity);
            if (success == 0) {
                response.sendRedirect("screens/Errorpage.jsp");
            }
        }
        try {
            // Update TotalPrice
            int check = orderDao.updateTotalPrice(orderId, totalPrice);
            // Update OrderDate  
            check = orderDao.updateDate(orderId);
            //Update Status 
            check = orderDao.updateStatus(orderId, status);
            if (status == 2){
                ohd.createOrderHistory(orderId, "Order placed");
            }else if (status == 3){
                ohd.createOrderHistory(orderId, "Order placed");
                ohd.createOrderHistory(orderId, "Order paid");
            }
            //update Information
            check = orderDao.updateInformation(orderId, address, name, phone);
        } catch (Exception e) {
            response.sendRedirect("screens/Errorpage.jsp");
        }
        //Todo : Page trang thai don hang
        response.sendRedirect("home");
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

        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("account");
        if(acc == null){
             request.getRequestDispatcher("sign-in").forward(request, response);
        }
            Order order = orderDao.getOrderById(acc.getId());
            int orderId = order.getId();
            ArrayList<OrderDetail> list = new ArrayList<>();
            if (orderDao.checkStatus(orderId) == 1) {
                list = detailDao.getAllOrderDetail(orderId);
            }
            for (OrderDetail o : list) {
                ProductVariantInfomation information = informationDao.getDetailInformation(o.getProductId());
                int number = variantDao.getRemainById(o.getProductId());
                InformationMap.put(o.getId(), information);
                remain.put(o.getProductId(), number);
                price.put(o.getProductId(), variantDao.getPriceById(o.getProductId()));
            }
            request.setAttribute("orderId", orderId);
            request.setAttribute("color", color);
            request.setAttribute("storage", storage);
            request.setAttribute("size", InformationMap.size());
            request.setAttribute("map", InformationMap);
            request.setAttribute("list", list);
            request.setAttribute("remain", remain);
            request.getRequestDispatcher("screens/Test.jsp").forward(request, response);
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
