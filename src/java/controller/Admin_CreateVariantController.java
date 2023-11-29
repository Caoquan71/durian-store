/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.ColorDAO;
import dao.ProductDAO;
import dao.ProductDetailDAO;
import dao.ProductImageDAO;
import dao.ProductVariantDAO;
import dao.StorageDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import model.Color;
import model.Product;
import model.ProductImage;
import model.ProductVariant;
import model.Storage;

/**
 *
 * @author 84834
 */
public class Admin_CreateVariantController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductDAO proDao = new ProductDAO();
        StorageDAO stoDao = new StorageDAO();
        ColorDAO colorDao = new ColorDAO();

        List<Product> list = proDao.getProductList();
        List<Storage> list_Storage = stoDao.getAllStorage();
        List<Color> list_Color = colorDao.getAllColor();

        req.setAttribute("list", list);
        req.setAttribute("list_Storage", list_Storage);
        req.setAttribute("list_Color", list_Color);

        req.getRequestDispatcher("screens/Admin_CreateVariant.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pro_id = Integer.parseInt(req.getParameter("name"));
        int color_id = Integer.parseInt(req.getParameter("color"));
        int storage_id = Integer.parseInt(req.getParameter("storage"));
        int quantity = Integer.parseInt(req.getParameter("quantity"));
        String img = req.getParameter("imgList");
        String[] imgList = img.split(",");
        ProductDAO pDao = new ProductDAO();
        Product product = pDao.getProductByID(pro_id);
        ProductDetailDAO pdDao = new ProductDetailDAO();
        Color color = pdDao.getColorbyId(color_id + "");
        Storage sto = pdDao.getStoragebyId(storage_id + "");
        float price = product.getPrice() + color.getPriceBonus() + sto.getPriceBonus();
        ProductVariantDAO varDao = new ProductVariantDAO();
        ProductVariant isExisted = varDao.productVariantIsExist(pro_id, color_id, storage_id);
        if (isExisted == null) {
            ProductVariant var = new ProductVariant(0, pro_id, color_id, storage_id, quantity, price, 1, 1);
            ProductImageDAO prid = new ProductImageDAO();
            for (String string : imgList) {
                ProductImage pimg = new ProductImage(0, string, pro_id, color_id);
                prid.createImage(pimg);
            }

            int success = varDao.createVariant(var);

            if (success == 1) {
                resp.sendRedirect("/Durian_Shop/admin-variant-list");
            } else {
                resp.sendRedirect("error-page");
            }
        } else {
            resp.sendRedirect("error-page");
        }
    }

}
