/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.ProductVariantDAO;
import dao.SaleDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import model.ProductVariantInfomation;
import model.Sale;

/**
 *
 * @author kienk
 */
public class SaleController extends HttpServlet {

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
            out.println("<title>Servlet SaleController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SaleController at " + request.getContextPath() + "</h1>");
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
        SaleDAO sale = new SaleDAO();
        List<Sale> saleList = sale.getListSale();
        String percentList = "";
        for (Sale sale1 : saleList) {
            percentList+=sale1.getPercent() + ",";
        }
        percentList = percentList.substring(0, percentList.lastIndexOf(","));
        String id = request.getParameter("id");
        String[] arrId = id.split(",");
        List<Integer> listId = new ArrayList<>();
        for (String string : arrId) {
            listId.add(Integer.parseInt(string));
        }
        ProductVariantDAO pvd = new ProductVariantDAO();
        List<ProductVariantInfomation> pvi = pvd.getListNameProductVariantById(listId);
        request.setAttribute("percentList", percentList);
        request.setAttribute("id", id);
        request.setAttribute("name", pvi);
        request.setAttribute("saleList", saleList);
        request.getRequestDispatcher("screens/SaleController.jsp").forward(request, response);
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
         String id = request.getParameter("id");
         String[] arrId = id.split(",");
         String selectSale = request.getParameter("sale");
         String newSale = request.getParameter("newSale");
         SaleDAO sd = new SaleDAO();
         ProductVariantDAO pv = new ProductVariantDAO();
         if (newSale != null && !newSale.equals("")){
             float percent = Float.parseFloat(newSale);
             Sale percentExist = sd.isPercentExist(percent);
             if (percentExist == null){
                 sd.createNewSale(percent);
                 Sale newSa = sd.getNewSale();
                 for (String string : arrId) {
                     pv.updateProductSale(string, newSa.getId() + "");
                 }
                 response.sendRedirect("admin-variant-list");
             }
         }else{
             for (String string : arrId) {
                     pv.updateProductSale(string, selectSale);
                 }
             response.sendRedirect("admin-variant-list");
         }        
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
