/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.ProductDAO;
import dao.ProductVariantDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import model.ProductVariant;

/**
 *
 * @author 84834
 */
public class Admin_DeleteProductController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductVariantDAO variantDao = new ProductVariantDAO();
        ProductDAO productDao = new ProductDAO();

        String id = (String) req.getParameter("id");
        String status = (String) req.getParameter("sta");
        List<ProductVariant> list = variantDao.getListProductVariantByID(id);
        if (!list.isEmpty()) {
            variantDao.updateAllProductVariant(id, status);
        }
        int succes = productDao.updateProduct(id, status);
        if (succes != 0)
            resp.sendRedirect("/Durian_Shop/admin-production-list");
        else //todo: add error page
            resp.sendRedirect("error-page");
        
    }

}