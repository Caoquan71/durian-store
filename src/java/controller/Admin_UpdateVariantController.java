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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import model.Color;
import model.Product;
import model.ProductImage;
import model.ProductVariant;
import model.ProductVariantInfomation;
import model.Range;
import model.Storage;
import utilities.Helper;

/**
 *
 * @author 84834
 */
public class Admin_UpdateVariantController extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductVariant variant = new ProductVariant();
        ProductDAO proDao = new ProductDAO();
        ProductVariantDAO variantDao = new ProductVariantDAO();
        ProductDetailDAO pdDao = new ProductDetailDAO();
        StorageDAO stoDao = new StorageDAO();
        ColorDAO colorDao = new ColorDAO();
        String id = (String) req.getParameter("id");

        int product_id = 0;
        try {
            product_id = Integer.parseInt(id);
        } catch (Exception e) {
            resp.sendRedirect("/home");
        }
        List<Product> list = proDao.getProductList();
        List<Storage> list_Storage = stoDao.getAllStorage();
        List<Color> list_Color = colorDao.getAllColor();
        req.setAttribute("list_Storage", list_Storage);
        req.setAttribute("list_Color", list_Color);
        req.setAttribute("list", list);

        variant = variantDao.getProductVariantByID(product_id);
        List<ProductImage> listImg = pdDao.getProductImage(variant.getProductId(), variant.getColorId() + "");
        String imgs = "";
        for (ProductImage productImage : listImg) {
            imgs += productImage.getUrl() + "|" + productImage.getId() + ",";
        }
        imgs = imgs.substring(0, imgs.lastIndexOf(","));
        req.setAttribute("id", id);
        req.setAttribute("variant", variant);
        req.setAttribute("listImg", imgs);
        req.getRequestDispatcher("screens/Admin_UpdateVariant.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("varid"));
        int pro_id = Integer.parseInt(request.getParameter("name"));
        int color_id = Integer.parseInt(request.getParameter("color"));
        int storage_id = Integer.parseInt(request.getParameter("storage"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String img = request.getParameter("imgList");
//        response.getWriter().println("add" + img);
        String[] imgList = new String[0];
        if (!img.isEmpty()){
            imgList = img.split(",");
        }
        String removeImg = request.getParameter("removeImgList");
//        response.getWriter().println("remove" + removeImg);
        String[] removeImgList = new String[0];
        if (!removeImg.isEmpty()){
            removeImgList = removeImg.split(",");
        }
        ProductDAO pDao = new ProductDAO();
        Product product = pDao.getProductByID(pro_id);
        ProductDetailDAO pdDao = new ProductDetailDAO();
        Color color = pdDao.getColorbyId(color_id + "");
        Storage sto = pdDao.getStoragebyId(storage_id + "");
        float price = product.getPrice() + color.getPriceBonus() + sto.getPriceBonus();

        ProductVariant var = new ProductVariant(id, pro_id, color_id, storage_id, quantity, price, 1, 1);
        ProductVariantDAO varDao = new ProductVariantDAO();
        ProductImageDAO prid = new ProductImageDAO();
        if (imgList.length > 0) {
            for (String string : imgList) {
                ProductImage pimg = new ProductImage(0, string, pro_id, color_id);
                prid.createImage(pimg);
            }
        }
        if (removeImgList.length > 0) {
            for (String index : removeImgList) {
                prid.deleteImg(index);
            }
        }
        int success = varDao.updateProduct(var);

        if (success == 1) {
            response.sendRedirect("/Durian_Shop/admin-variant-list");
        } else {
            response.sendRedirect("error-page");
        }
    }
}
