/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.ColorDAO;
import dao.ProductDAO;
import dao.ProductVariantDAO;
import dao.SaleDAO;
import dao.StorageDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import model.Color;
import model.Product;
import model.ProductImage;
import model.ProductVariant;
import model.ProductVariantInfomation;
import model.Range;
import model.Sale;
import model.Storage;
import utilities.Helper;

/**
 *
 * @author 84834
 */
public class Admin_ProductVariantListController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      ProductDAO pDao = new ProductDAO();
        ProductVariantDAO productVarDao = new ProductVariantDAO();
        SaleDAO sale = new SaleDAO();
        String offsetRaw = request.getParameter("o");
        String fetchRaw = request.getParameter("f");
        String tagRaw = request.getParameter("tag");
        String sr = request.getParameter("sr");
        String search = sr==null?"iphone":sr;
        List<Integer> listId = productVarDao.getListIdProductByName(search);
        List<String> colorF = new ArrayList<>();
        List<String> storageF = new ArrayList<>();          
        String tag = tagRaw == null ? "1" : tagRaw;
        String filterRaw = request.getParameter("filter");
        String filter = filterRaw==null?"1":filterRaw;
        Range range = new Range(0, 3000);
        int totalProduct;
        int endPage;
        int offset = 1;
        int fetch = 6;
        List<ProductVariant> productVar;
        if (offsetRaw != null && fetchRaw != null) {
            offset = Integer.parseInt(offsetRaw);
            fetch = Integer.parseInt(fetchRaw);
            totalProduct = productVarDao.getTotalProductVariant(offset, fetch, "id", colorF, storageF, range, listId, filter);
            endPage = Helper.getEndPage(totalProduct, fetch);
            productVar = productVarDao.getListProductVariant(offset, fetch, "id desc", colorF, storageF, range, listId, filter);
            
        } else {
            totalProduct = productVarDao.getTotalProductVariant(1, 6, "id", colorF, storageF, range, listId, filter);
            productVar = productVarDao.getListProductVariant(1, 6, "id desc", colorF, storageF, range, listId, filter);
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
        List<Sale> saleList = sale.getListSale();
        request.setAttribute("filter", filter);
        request.setAttribute("saleList", saleList);
        request.setAttribute("sr", search);
        request.setAttribute("totalProduct", totalProduct);
        request.setAttribute("o", offset);
        request.setAttribute("f", fetch);
        request.setAttribute("tag", tag);
        request.setAttribute("totalPage", endPage);
        request.setAttribute("product", productVarInfo);
        request.getRequestDispatcher("screens/Admin_ProductionVariantList.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductDAO proDao = new ProductDAO();
        ProductVariantDAO variantDao = new ProductVariantDAO();
        StorageDAO stoDao = new StorageDAO();
        ColorDAO colorDao = new ColorDAO();

        String id = (String) req.getParameter("pro");
        List<Product> list = proDao.getProductList();
        List<ProductVariant> list_variant = variantDao.getListProductVariantByID("%%");
        if(id != "%%")
            list_variant = variantDao.getListProductVariantByID(id);
        
        HashMap<Integer, String> storageMap = (HashMap<Integer, String>) stoDao.getHashMapStorage();
        HashMap<Integer, String> colorMap = (HashMap<Integer, String>) colorDao.getHashMapColor();
        HashMap<Integer, String> productMap = (HashMap<Integer, String>) proDao.getHashMapProduct();
        
        req.setAttribute("storageMap", storageMap);
        req.setAttribute("colorMap", colorMap);
        req.setAttribute("productMap", productMap);

        req.setAttribute("id", id);
        req.setAttribute("variant", list_variant);
        req.setAttribute("list", list);
        req.getRequestDispatcher("screens/Admin_ProductionVariantList.jsp").forward(req, resp);
    }

}