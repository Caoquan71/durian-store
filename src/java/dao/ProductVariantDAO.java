/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.Color;
import model.ProductImage;
import model.ProductVariant;
import model.ProductVariantInfomation;
import model.Range;
import model.Storage;

/**
 *
 * @author kienk
 */
public class ProductVariantDAO extends DBContext {
    
    public List<ProductVariant> getListProductVariant(int offset, int fetch, 
            String orderBy, List<String> filterColor, List<String> filterStorage,
            Range range, List<Integer> search, String filter) {
        List<ProductVariant> product = new ArrayList<>();
        String query = "select * from productVariant"
                + " where 1=1";
        
        int min = range.getMin();
        int max = range.getMax();
        query += "AND (variant_price BETWEEN "+min +" AND "+max+") ";
        if (!filterColor.isEmpty()) {
            query += " and ";
            if (filterColor.size() == 1){
                query+= "color_id =" + filterColor.get(0);
            }
            if (filterColor.size()>1){
                query+= "(";
            
            for (int i = 0; i < filterColor.size(); i++) {
                if (i <=filterColor.size() - 2){
                    query+="color_id = " + filterColor.get(i) + " or ";
                }if (i == filterColor.size() -1){
                    query+="color_id = " + filterColor.get(i) + ") ";
                }
               
            }
            }
        }
        if (!filterStorage.isEmpty()) {
            query += " and ";
            if (filterStorage.size() == 1){
                query+= "storage_id =" + filterStorage.get(0);
            }
            if (filterStorage.size()>1){
                query+= "(";
            
            for (int i = 0; i < filterStorage.size(); i++) {              
                if (i <=filterStorage.size() - 2){
                    query+="storage_id = " + filterStorage.get(i) + " or ";
                }
                if (i == filterStorage.size() -1){
                    query+="storage_id = " + filterStorage.get(i) + ") ";
                }
               
            }
            }
        }
        if (!search.isEmpty()) {
            query += " and ";
            if (search.size() == 1){
                query+= "product_id =" + search.get(0);
            }
            if (search.size()>1){
                query+= "(";
            
            for (int i = 0; i < search.size(); i++) {              
                if (i <=search.size() - 2){
                    query+="product_id = " + search.get(i) + " or ";
                }
                if (i == search.size() -1){
                    query+="product_id = " + search.get(i) + ") ";
                }
               
            }
            }
        }else{
            query += " and product_id=0" ;
        }
        if (filter.equals("2")){
            query+= "and status = 1";
        } else if(filter.equals("3")){
            query+= "and status = 2";
        }else if(filter.equals("4")){
            query+= "and sale_id != 1";
        }
        query += " order by  " + orderBy;
        query += "  offset ? rows fetch next ? rows only";
        int count = 1;
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(count++, (offset - 1) * fetch);
            ps.setInt(count++, fetch);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                product.add(new ProductVariant(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getFloat(6), rs.getInt(7), rs.getInt(8)));
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return product;
    }
    

    public ProductImage getOneProductVariantImage(int id, String colorId) {
        String query = "Select top(1) * from ProductImage "
                + " Where productImage.product_id = ? ";
        if (!colorId.equals("")) {
            query += " AND color_id =  " + colorId;
        }
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return (new ProductImage(rs.getInt(1), rs.getString(2),
                        rs.getInt(3), rs.getInt(4)));
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
    
    public int getTotalProductVariant() {
        String query = "select count(*) from productVariant";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
    }

    
    public int getTotalProductVariant(int offset, int fetch, String orderBy, 
            List<String> filterColor, List<String> filterStorage, Range range, 
            List<Integer> search, String filter) {
        String query = "select count(*) from productVariant"
                + " where 1=1";
        
        int min = range.getMin();
        int max = range.getMax();
        query += "AND (variant_price BETWEEN "+min +" AND "+max+") ";
        if (!filterColor.isEmpty()) {
            query += " and ";
            if (filterColor.size() == 1){
                query+= "color_id =" + filterColor.get(0);
            }
            if (filterColor.size()>1){
                query+= "(";
            
            for (int i = 0; i < filterColor.size(); i++) {
                if (i <=filterColor.size() - 2){
                    query+="color_id = " + filterColor.get(i) + " or ";
                }if (i == filterColor.size() -1){
                    query+="color_id = " + filterColor.get(i) + ") ";
                }
               
            }
            }
        }
        if (!filterStorage.isEmpty()) {
            query += " and ";
            if (filterStorage.size() == 1){
                query+= "storage_id =" + filterStorage.get(0);
            }
            if (filterStorage.size()>1){
                query+= "(";
            
            for (int i = 0; i < filterStorage.size(); i++) {              
                if (i <=filterStorage.size() - 2){
                    query+="storage_id = " + filterStorage.get(i) + " or ";
                }
                if (i == filterStorage.size() -1){
                    query+="storage_id = " + filterStorage.get(i) + ") ";
                }
               
            }
            }
        }
        if (!search.isEmpty()) {
            query += " and ";
            if (search.size() == 1){
                query+= "product_id =" + search.get(0);
            }
            if (search.size()>1){
                query+= "(";
            
            for (int i = 0; i < search.size(); i++) {              
                if (i <=search.size() - 2){
                    query+="product_id = " + search.get(i) + " or ";
                }
                if (i == search.size() -1){
                    query+="product_id = " + search.get(i) + ") ";
                }
               
            }
            }
        }else{
            query += " and product_id=0" ;
        }
        if (filter.equals("2")){
            query+= "and status = 1";
        } else if(filter.equals("3")){
            query+= "and status = 2";
        }else if(filter.equals("4")){
            query+= "and sale_id != 1";
        }
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
    }

    public String getColorNameById(int id) {
        String query = "Select color from color "
                + "Where id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getString(1);
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public String getStorageSizeById(int id) {
        String query = "Select [Storage_Size] from [DURIAN_SHOP].[dbo].[Storage] "
                + "Where id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getString(1);
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public List<Color> getListColor() {
        List<Color> color = new ArrayList<>();
        String query = "SELECT * FROM color";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                color.add(new Color(rs.getInt(1), rs.getString(2), rs.getFloat(3), rs.getString(4), rs.getInt(5)));
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return color;
    }

    public List<Storage> getListStorage() {
        List<Storage> storage = new ArrayList<>();
        String query = "SELECT * FROM storage";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                storage.add(new Storage(rs.getInt(1), rs.getString(2), rs.getFloat(3), rs.getInt(4)));
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return storage;
    }
    
    public List<Integer> getListIdProductByName(String search){
        List<Integer> list = new ArrayList<>();
        String query = "Select id from product "
                + "where name like '%"+ search+ "%'";
         try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getInt(1));
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
    
    public ProductVariant getProductVariantByID(int id){
        ProductVariant product = new ProductVariant();
        String sql = "SELECT * FROM [ProductVariant] WHERE id LIKE '" + id + "'";
         try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                return new ProductVariant(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getFloat(6), rs.getInt(7), rs.getInt(8));
            }
            ps.close();
            rs.close();
        }catch(SQLException e){
            System.out.println(e);
        }
         return null;
    } 
    
    public ProductVariant getProductVariant(String colorId, String productId, String storageId){
        ProductVariant product = new ProductVariant();
        String sql = "SELECT * FROM [ProductVariant] WHERE product_id = " + productId + " and color_Id = " +colorId+ " and storage_id = " + storageId;
         try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                return new ProductVariant(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getFloat(6), rs.getInt(7), rs.getInt(8));
            }
            ps.close();
            rs.close();
        }catch(SQLException e){
            System.out.println(e);
        }
         return null;
    } 
    
    public ProductVariant getColdProductVariant(){
        ProductVariant cold_product = new ProductVariant();
        String sql = "select * from [ProductVariant] where Quantity = (select MAX(Quantity) from [ProductVariant]) ";
         try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                return new ProductVariant(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getFloat(6), rs.getInt(7), rs.getInt(8));
            }
            ps.close();
            rs.close();
        }catch(SQLException e){
            System.out.println(e);
        }
         return null;
    } 
    
     public List<ProductVariant> getListProductVariantByID(String id){
        List<ProductVariant> variant_list = new ArrayList<>();
        String sql = "SELECT * FROM [ProductVariant] WHERE Product_Id LIKE  '" + id + "'";
         try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                variant_list.add(new ProductVariant(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getFloat(6), rs.getInt(7), rs.getInt(8)));
            }
            ps.close();
            rs.close();
        }catch(SQLException e){
            System.out.println(e);
        }
         return variant_list;
    } 
     
     public void deleteAllProductVariant(String product_id){
         String sql = "Delete FROM [ProductVariant] WHERE Product_Id LIKE  '" + product_id + "'";
         try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.executeUpdate();
            ps.close();
        }catch(SQLException e){
            System.out.println(e);
        }
    }
     
      public void updateAllProductVariant(String product_id, String status){
         String sql = "Update [ProductVariant] SET status ="+ status + " WHERE Product_Id LIKE  '" + product_id + "'";
         try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.executeUpdate();
            ps.close();
        }catch(SQLException e){
            System.out.println(e);
        }
    }
      
      public void updateAllProductVariantByColor(String color_id, String status){
         String sql = "Update [ProductVariant] SET status ="+ status + " WHERE color_Id LIKE  '" + color_id+ "'";
         try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.executeUpdate();
            ps.close();
        }catch(SQLException e){
            System.out.println(e);
        }
    }
      
      public void updateAllProductVariantByStorage(String storage_id, String status){
         String sql = "Update [ProductVariant] SET status ="+ status + " WHERE storage_Id LIKE  '" + storage_id+ "'";
         try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.executeUpdate();
            ps.close();
        }catch(SQLException e){
            System.out.println(e);
        }
    }
    
    public int deleteProductVariant(String id){
        int succes = 0;
         String sql = "Delete FROM [ProductVariant] WHERE Id LIKE  '" + id + "'";
         try{
            PreparedStatement ps = connection.prepareStatement(sql);
            succes = ps.executeUpdate();
            ps.close();
        }catch(SQLException e){
            System.out.println(e);
        }
         return succes;
    }
    
    public int updateProduct( ProductVariant pro_variant){
        int succes = 0;
        String query = "UPDATE [ProductVariant] SET Color_Id = " + pro_variant.getColorId() + 
                                       ", Storage_Id = " + pro_variant.getStorageId() +
                                       ", Quantity = " + pro_variant.getQuantity() +
                                       ", Variant_Price = " + pro_variant.getVariantPrice() +
                                       " where Id = " + pro_variant.getId();
          try{
            PreparedStatement ps = connection.prepareStatement(query);
            succes = ps.executeUpdate();
            ps.close();
        }catch(SQLException e){
            System.out.println(e);
        }
          return succes;
    }
    
      public int updateProduct(String id, String status){
        int succes = 0;
        String query = "UPDATE [ProductVariant] SET [Status] = " + status + 
                                       
                                       " where Id = " + id;
          try{
            PreparedStatement ps = connection.prepareStatement(query);
            succes = ps.executeUpdate();
            ps.close();
        }catch(SQLException e){
            System.out.println(e);
        }
          return succes;
    }
      public int updateProductSale(String id, String sale){
        int succes = 0;
        String query = "UPDATE [ProductVariant] SET [sale_id] = " + sale + 
                                       
                                       " where Id = " + id;
          try{
            PreparedStatement ps = connection.prepareStatement(query);
            succes = ps.executeUpdate();
            ps.close();
        }catch(SQLException e){
            System.out.println(e);
        }
          return succes;
    }
    
    public int createVariant(ProductVariant pro_variant){
        int succes = 0;
        System.out.println(pro_variant);
        String query = "INSERT INTO [ProductVariant]([Product_Id],[Color_Id],[Storage_Id],[Quantity],[Variant_Price])"
                + "values ('" + pro_variant.getProductId() + "', '" + pro_variant.getColorId() + "' ,'" + pro_variant.getStorageId()
                + "' ,'" + pro_variant.getQuantity() + "', '" + pro_variant.getVariantPrice() + "')";
          try{
            PreparedStatement ps = connection.prepareStatement(query);
            succes = ps.executeUpdate();
            ps.close();
        }catch(SQLException e){
            System.out.println(e);
        }
          return succes;
    }
    
     public ProductVariant productVariantIsExist(int productId, int colorId, int storageId){
        String sql = "SELECT * FROM [ProductVariant] WHERE product_Id = ? and color_id = ? and storage_id = ?";
         try{
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, productId);
            ps.setInt(2, colorId);
            ps.setInt(3, storageId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                return new ProductVariant(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getFloat(6), rs.getInt(7), rs.getInt(8));
            }
            ps.close();
            rs.close();
        }catch(SQLException e){
            System.out.println(e);
        }
         return null;
    } 
     
     public List<ProductVariantInfomation> getListNameProductVariantById(List<Integer> id){
         List<ProductVariantInfomation> product = new ArrayList<>();
         String query = "Select pv.id AS productVariant_id, p.name AS product_name,c.color AS color_name,s.storage_Size AS storage_name, pv.sale_id"
                 + " From ProductVariant pv JOIN product p ON pv.product_id = p.id JOIN color c ON pv.color_id = c.id JOIN storage s ON pv.storage_id = s.id"
                 + " where";
         
         if (!id.isEmpty()) {
            if (id.size() == 1){
                query+= " pv.id =" + id.get(0);
            }
            if (id.size()>1){
                query+= "(";
            
            for (int i = 0; i < id.size(); i++) {              
                if (i <=id.size() - 2){
                    query+="pv.id = " + id.get(i) + " or ";
                }
                if (i == id.size() -1){
                    query+="pv.id = " + id.get(i) + ") ";
                }
               
            }
            }
        }
         try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                product.add(new ProductVariantInfomation(rs.getInt(1), rs.getString(2), "", "", "", "", "", "", "", rs.getString(3), rs.getString(4), 0, 0, 0, rs.getInt(5)));
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return product;
     }
     
     public int getRemainById(int variantId) {
        int remain = 0;
        String sql = "select [Quantity] from ProductVariant where [Id] = " + variantId;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                remain = rs.getInt(1);
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return remain;
    }

    public double getPriceById(int variantId) {
        double price = 0;
        String sql = "select [Variant_Price] from ProductVariant where [Id] = " + variantId;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                price = rs.getDouble(1);
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return price;
    }

    public int minusQuantity(int id, int quantity) {
        int succes = 0;
        String sql = "update [ProductVariant] set [Quantity] = [Quantity] - ? where Id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, quantity);
            ps.setInt(2, id);
            succes = ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return succes;
    }

}
