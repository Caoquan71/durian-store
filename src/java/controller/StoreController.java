/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.ProductDAO;
import dao.ProductVariantDAO;
import dao.SaleDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
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
 * @author kienk
 */
@WebServlet(name = "StoreController", urlPatterns = {"/store"})
public class StoreController extends HttpServlet {

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
            out.println("<title>Servlet StoreController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet StoreController at " + request.getContextPath() + "</h1>");
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
        ProductDAO pDao = new ProductDAO();
        ProductVariantDAO productVarDao = new ProductVariantDAO();
        SaleDAO sale = new SaleDAO();
        String offsetRaw = request.getParameter("o");
        String fetchRaw = request.getParameter("f");
        String tagRaw = request.getParameter("tag");
        String orderRaw = request.getParameter("order");
        String storageFRaw = request.getParameter("sto");
        String colorFRaw = request.getParameter("col"); 
        String minRaw = request.getParameter("min");
        String maxRaw = request.getParameter("max");
        String sr = request.getParameter("sr");
        String search = sr==null?"iphone":sr;
        List<Integer> listId = productVarDao.getListIdProductByName(search);
        int min = minRaw==null?0:Integer.parseInt(request.getParameter("min"));
        int max = maxRaw==null?3000:Integer.parseInt(request.getParameter("max"));
        Range range = new Range(min, max);
        List<String> colorF = new ArrayList<>();
        List<String> storageF = new ArrayList<>();
        
        
        if (storageFRaw != null && !storageFRaw.equals("")) {
                String[] sArr = storageFRaw.split(",");
                storageF.addAll(Arrays.asList(sArr));
        }
        if (colorFRaw != null&& !colorFRaw.equals("")) {
                String[] cArr = colorFRaw.split(",");
                colorF.addAll(Arrays.asList(cArr));
        }
        
        
        String tag = tagRaw == null ? "1" : tagRaw;
        String order = orderRaw == null ? "id desc" : orderRaw;
        int totalProduct;
        int endPage;
        int offset = 1;
        int fetch = 6;
        List<ProductVariant> productVar;
        if (offsetRaw != null && fetchRaw != null) {
            offset = Integer.parseInt(offsetRaw);
            fetch = Integer.parseInt(fetchRaw);
            totalProduct = productVarDao.getTotalProductVariant(offset, fetch, order, colorF, storageF, range, listId, "2");
            endPage = Helper.getEndPage(totalProduct, fetch);
            productVar = productVarDao.getListProductVariant(offset, fetch, order, colorF, storageF, range, listId, "2");
            
        } else {
            totalProduct = productVarDao.getTotalProductVariant(1, 6, order, colorF, storageF, range, listId, "2");
            productVar = productVarDao.getListProductVariant(1, 6, order, colorF, storageF, range, listId, "2");
            endPage = Helper.getEndPage(totalProduct, 6);
        }
        List<ProductVariantInfomation> productVarInfo = new ArrayList<>();
        Product pr;
        ProductImage pi;
        for (ProductVariant p : productVar) {
            pr = pDao.getProductbyId(p.getProductId() + "");
            pi = productVarDao.getOneProductVariantImage(p.getProductId(), p.getColorId() + "");
            productVarInfo.add(new ProductVariantInfomation(p.getId(), pr.getName(),
                    pr.getScreen(), pr.getCamera(), pr.getRam(), pr.getScreen(), pr.getChipset(),
                    pr.getScreenResolution(), pi.getUrl(), productVarDao.getColorNameById(p.getColorId()),
                    productVarDao.getStorageSizeById(p.getStorageId()), p.getQuantity(), p.getVariantPrice(), p.getStatus(), sale.getSaleById(p.getSaleId()).getPercent()));
        }
        //send Color
        List<Color> colorList = productVarDao.getListColor();
        List<Storage> storageList = productVarDao.getListStorage();
        request.setAttribute("sr", search);
        request.setAttribute("min", min);
        request.setAttribute("max", max);
        request.setAttribute("colorList", colorList);
        request.setAttribute("storageList", storageList);
        request.setAttribute("order", order);
        request.setAttribute("totalProduct", totalProduct);
        request.setAttribute("o", offset);
        request.setAttribute("sto", storageFRaw);
        request.setAttribute("col", colorFRaw);
        request.setAttribute("f", fetch);
        request.setAttribute("tag", tag);
        request.setAttribute("totalPage", endPage);
        request.setAttribute("product", productVarInfo);
        request.getRequestDispatcher("/screens/store.jsp").forward(request, response);
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
