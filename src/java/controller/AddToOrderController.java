/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.OrderDAO;
import dao.OrderDetailDAO;
import dao.ProductDAO;
import dao.ProductDetailDAO;
import dao.ProductVariantDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import model.Account;
import model.Order;
import model.ProductVariant;
import model.User;

/**
 *
 * @author 84834
 */
public class AddToOrderController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductDAO proDao = new ProductDAO();
        OrderDAO orderDao = new OrderDAO();
        OrderDetailDAO detailDao = new OrderDetailDAO();
        ProductDetailDAO productDetailDao = new ProductDetailDAO();
        ProductVariantDAO productVariantDao = new ProductVariantDAO();
        // => Check xem user do da dang nhap hay chua    
        // Lay thog tin account:
        HttpSession session = req.getSession();
        Account acc = (Account) session.getAttribute("account");
        if (acc == null) {
            //todo : messenger login
            resp.sendRedirect("sign-in");
        } else {
            
            int userId = acc.getId();
//Lay thong tin Product Variant
            int productId = Integer.parseInt(req.getParameter("id"));
            String colorName = req.getParameter("colorName");
            int colorId = productDetailDao.getColorIdByName(colorName);
            int storageId = Integer.parseInt(req.getParameter("storageId"));
            ProductVariant productVariant = productVariantDao.productVariantIsExist(productId, colorId, storageId);
// Check xem user nay da co order nao hay chua
            int orderId = orderDao.checkOrder(userId);
// Truong hop user nay khong co order trong trang thai 1 -> tao order moi        
            if (orderId == 0) {
                orderDao.createOrder(userId, 0);
            }
//Truong hop da co        
            Order order = orderDao.getOrderById(userId);
        //check xem san pham do da co trong danh sach hay chua
            int check = detailDao.checkOrderDetail(userId, productVariant.getId());
            int success = 0;
            if(check == 0){
                success = detailDao.insertOrderDetail(order.getId(), productVariant.getId(), 1);
            }
//Todo : add thanh cong tra ve trang checkout  
                resp.sendRedirect("checkout");
        }
    }
}
