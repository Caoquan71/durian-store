/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.AccountDAO;
import dao.ColorDAO;
import dao.CommentDAO;
import dao.OrderDAO;
import dao.OrderDetailDAO;
import dao.ProductDAO;
import dao.ProductVariantDAO;
import dao.SaleDAO;
import dao.StorageDAO;
import dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import model.Comment;
import model.Order;
import model.ProductVariant;

/**
 *
 * @author 84834
 */
public class DashboardController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        OrderDAO orderDao = new OrderDAO();
        OrderDetailDAO detailDao = new OrderDetailDAO();
        ProductVariantDAO variantDao = new ProductVariantDAO();
        StorageDAO stoDao = new StorageDAO();
        ColorDAO colorDao = new ColorDAO();
        ProductDAO productDao = new ProductDAO();
        CommentDAO commentDao = new CommentDAO();
        UserDAO userDao = new UserDAO();
        

        LocalDate date = LocalDate.now();
        Month month = date.getMonth();
        int year = date.getYear();
        float totalMonth = orderDao.TotalPriceMonth(year, month);
        float totalYear = orderDao.TotalPriceYear(year);

        HashMap<Integer, String> storageMap = (HashMap<Integer, String>) stoDao.getHashMapStorage();
        HashMap<Integer, String> colorMap = (HashMap<Integer, String>) colorDao.getHashMapColor();
        HashMap<Integer, String> productMap = (HashMap<Integer, String>) productDao.getHashMapProduct();
        
        //phan report comment can lay ra content va user.
        HashMap<Integer, String> userMap = (HashMap<Integer, String>) userDao.getUserName();
        ArrayList<Comment> listComment = commentDao.getReportedComment();
        
        int hot_id = detailDao.getHotProductID();
        ProductVariant hot_product = variantDao.getProductVariantByID(hot_id);
        List<String> hot = new ArrayList<>();
        if (hot_id != 0) {
            hot.add(productMap.get(hot_product.getProductId()));
            hot.add(colorMap.get(hot_product.getColorId()));
            hot.add(storageMap.get(hot_product.getStorageId()));
            hot.add(String.valueOf(hot_product.getVariantPrice()));
            hot.add(String.valueOf(hot_product.getQuantity()));
        } else {
            hot.add(" ");
            hot.add(" ");
            hot.add(" ");
            hot.add(" ");
            hot.add(" ");
        }

        ProductVariant cold_product = variantDao.getColdProductVariant();
        List<String> cold = new ArrayList<>();
        if (cold_product != null) {
            cold.add(productMap.get(cold_product.getProductId()));
            cold.add(colorMap.get(cold_product.getColorId()));
            cold.add(storageMap.get(cold_product.getStorageId()));
            cold.add(String.valueOf(cold_product.getVariantPrice()));
            cold.add(String.valueOf(cold_product.getQuantity()));
        } else {
            cold.add(" ");
            cold.add(" ");
            cold.add(" ");
            cold.add(" ");
            cold.add(" ");
        }
        AccountDAO acd = new AccountDAO();
        OrderDAO ord = new OrderDAO();
        SaleDAO sd = new SaleDAO();
        int totalUser = acd.getTotalUser();
        int totalProduct = variantDao.getTotalProductVariant();
        int totalOrder = ord.getTotalOrder();
        int totalSale = sd.getTotalSale();
        List<Order> recentOr = ord.getRecentOrder();
        req.setAttribute("totalUser", totalUser);
        req.setAttribute("totalProduct", totalProduct);
        req.setAttribute("totalOrder", totalOrder);
        req.setAttribute("totalSale", totalSale);
        req.setAttribute("listComment", listComment);
        req.setAttribute("listUser", userMap);
        req.setAttribute("recentOr", recentOr);
        req.setAttribute("listProduct", productMap);
        req.getRequestDispatcher("/screens/Admin_Dashboard.jsp").forward(req, resp);
    }

}