/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.ProductDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import model.Product;

/**
 *
 * @author 84834
 */
public class Admin_CreateProductController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("screens/Admin_CreateProduct.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int succes = 0;
        Product pro = new Product(0, req.getParameter("name"), Float.parseFloat(req.getParameter("price"))
                , req.getParameter("description"), req.getParameter("screen"), req.getParameter("camera")
                , req.getParameter("ram"), req.getParameter("pin"), req.getParameter("chipset"),
                req.getParameter("resolution"), req.getParameter("img"), 1);
        ProductDAO proDao = new ProductDAO();
        succes = proDao.createProduct(pro);
        if(succes != 0){
            List<Product> list = proDao.getProductList();
            req.setAttribute("list", list);
            resp.sendRedirect("admin-production-list");
        }
        else{
            req.getRequestDispatcher("error-page").forward(req, resp);
        }
    }

}
