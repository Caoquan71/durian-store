/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.ProductDAO;
import dao.ProductDetailDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import model.Account;
import model.Product;

/**
 *
 * @author 84834
 */
public class Admin_UpdateProductController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Product pro = new Product();
        ProductDAO dao = new ProductDAO();
        String id = (String) req.getParameter("id");

        int product_id = 0;
        try {
            product_id = Integer.parseInt(id);
        } catch (Exception e) {
            resp.sendRedirect("/home");
        }
        pro = dao.getProductByID(product_id);
        req.setAttribute("product", pro);
        req.getRequestDispatcher("screens/Admin_UpdateProduct.jsp").forward(req, resp);
    }



@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = (String) req.getParameter("id");
        int product_id = Integer.parseInt(id);
        String product_name = (String) req.getParameter("name");
        String product_price = (String) req.getParameter("price");
        float price = Float.parseFloat(product_price);
        String product_description = (String) req.getParameter("description");
        String product_screen = (String) req.getParameter("screen");
        String product_camera = (String) req.getParameter("camera");
        String product_ram = (String) req.getParameter("ram");
        String product_pin = (String) req.getParameter("pin");
        String product_Chipset = (String) req.getParameter("chipset");
        String Screen_resolution = (String) req.getParameter("resolution");
        String product_image = req.getParameter("img");
        if (req.getParameter("img") == null || req.getParameter("img").length() == 0){
            product_image = req.getParameter("current-image");
        }
       Product product = new Product(product_id, product_name, price, product_description, product_screen,
               product_camera, product_ram, product_pin, product_Chipset, Screen_resolution, product_image, 1);
       
       ProductDAO proDAO = new ProductDAO();
       int succes = proDAO.updateProduct(product_id, product);
       if(succes == 0){
           //Todo: add 404 page to this
           resp.sendRedirect("error-page");
       }
       else{
           List<Product> list = proDAO.getProductList();
           req.setAttribute("list", list);
           resp.sendRedirect("admin-production-list");
       }
    }
}
