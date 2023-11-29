/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Blog;
import model.BlogCategory;
import model.SubBlog;

/**
 *
 * @author kienk
 */
public class BlogDAO extends DBContext {
    public List<BlogCategory> getListBlogCategory(){
        List<BlogCategory> list = new ArrayList<>();
        String query = "Select * from [DURIAN_SHOP].[dbo].[BlogCategory]";
        try{
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                list.add(new BlogCategory(rs.getInt(1), rs.getNString(2), rs.getInt(3)));
            }
            ps.close();
            rs.close();
        }catch(SQLException e){
            System.out.println(e);
        }
        return list;
    }
    public List<BlogCategory> getListBlogCategory(int status){
        List<BlogCategory> list = new ArrayList<>();
        String query = "Select * from [DURIAN_SHOP].[dbo].[BlogCategory] where [status]= " + status;
        try{
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                list.add(new BlogCategory(rs.getInt(1), rs.getNString(2), rs.getInt(3)));
            }
            ps.close();
            rs.close();
        }catch(SQLException e){
            System.out.println(e);
        }
        return list;
    }
    
    public List<Blog> getListBlogbyCategoryId(int id){
        List<Blog> list = new ArrayList<>();
        String query = "Select * from [DURIAN_SHOP].[dbo].[Blog] where categoryId = "+ id + " order by id desc";
        try{
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                list.add(new Blog(rs.getInt(1), rs.getNString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getString(6), rs.getDate(7)));
            }
            ps.close();
            rs.close();
        }catch(SQLException e){
            System.out.println(e);
        }
        return list;
    }
    
    public Blog getListBlogById(int id){
        String query = "Select * from [DURIAN_SHOP].[dbo].[Blog] where id = "+ id + " order by id desc";
        try{
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
               return new Blog(rs.getInt(1), rs.getNString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getString(6), rs.getDate(7));
            }
            ps.close();
            rs.close();
        }catch(SQLException e){
            System.out.println(e);
        }
        return null;
    }
    
    public List<Blog> getListBlogbyCategoryId(int id, int status){
        List<Blog> list = new ArrayList<>();
        String query = "Select * from [DURIAN_SHOP].[dbo].[Blog] where categoryId = "+ id + " and [status]=" + status + 
                " order by id desc";
        try{
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                list.add(new Blog(rs.getInt(1), rs.getNString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getString(6), rs.getDate(7)));
            }
            ps.close();
            rs.close();
        }catch(SQLException e){
            System.out.println(e);
        }
        return list;
    }
    
    public List<SubBlog> getListSubBlogbyBlogId(int id){
        List<SubBlog> list = new ArrayList<>();
        String query = "Select * from [DURIAN_SHOP].[dbo].[SubBlog] where blogId = " + id;
        try{
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                list.add(new SubBlog(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5)));
            }
            ps.close();
            rs.close();
        }catch(SQLException e){
            System.out.println(e);
        }
        return list;
    }
    
    public BlogCategory getBlogCategoryById(int id){
        String query = "Select * from [DURIAN_SHOP].[dbo].[BlogCategory] where id = " + id;
        try{
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                return new BlogCategory(rs.getInt(1), rs.getNString(2), rs.getInt(3));
            }
            ps.close();
            rs.close();
        }catch(SQLException e){
            System.out.println(e);
        }
        return null;
    }
    
    public int createBlogCategory(String name){
        int isSuccess = 0;
        String query = "Insert into [DURIAN_SHOP].[dbo].[BlogCategory](categoryName, status) values (?, 1)";
        try{
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, name);
            isSuccess = ps.executeUpdate();
        }catch(SQLException e){
            System.out.println(e);
        }
        return isSuccess;
    }
    
    public int updateBlogCategory(int id, String name, int status){
        int success = 0;
        String query = "UPDATE [BlogCategory] SET " +
                                       "status = " + status;
        if (!name.equals("")){
            query+= ", categoryName = '" + name + "' ";
        }
        query += " where id = " + id;
          try{
            PreparedStatement ps = connection.prepareStatement(query);
            success = ps.executeUpdate();
            ps.close();
        }catch(SQLException e){
            System.out.println(e);
        }
          return success;
    }
    
    public int updateBlogByCateId(int id, String title, int status, int catgoryId){
        int success = 0;
        String query = "UPDATE [Blog] SET " +
                                       "status = " + status;
        if (!title.equals("")){
            query+= ", title = '" + title + "' ";
        }
        if (catgoryId != 0){
            query+= ", categoryId = " + catgoryId;
        }
        query += " where categoryId = " + id;
          try{
            PreparedStatement ps = connection.prepareStatement(query);
            success = ps.executeUpdate();
            ps.close();
        }catch(SQLException e){
            System.out.println(e);
        }
          return success;
    }
    
     public int updateBlogById(int id, String title, int status, int catgoryId, String coverImg){
        int success = 0;
        String query = "UPDATE [Blog] SET " +
                                       "status = " + status;
        if (!title.equals("")){
            query+= ", title = '" + title + "' ";
        }
        if (catgoryId != 0){
            query+= ", categoryId = " + catgoryId;
        }
        if (!coverImg.equals("")){
            query+= ", coverImg = '" + coverImg + "' ";
        }
        query += " where Id = " + id;
          try{
            PreparedStatement ps = connection.prepareStatement(query);
            success = ps.executeUpdate();
            ps.close();
        }catch(SQLException e){
            System.out.println(e);
        }
          return success;
    }
    
    public int createBlog(String title, int cateId, int userId, String cover){
        int isSuccess = 0;
        String query = "Insert into [DURIAN_SHOP].[dbo].[Blog](title, categoryId, userId, status, coverImg) values (?, ?, ?, 1, ?)";
        try{
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, title);
            ps.setInt(2, cateId);
            ps.setInt(3, userId);
            ps.setString(4, cover);
            isSuccess = ps.executeUpdate();
        }catch(SQLException e){
            System.out.println(e);
        }
        return isSuccess;
    }
    
    public int createSubBlog(String title, String content, String img, int blogId){
        int isSuccess = 0;
        String query = "Insert into [DURIAN_SHOP].[dbo].[SubBlog](title, content, img, blogId) values (?, ?, ?, ?)";
        try{
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, title);
            ps.setString(2, content);
            ps.setString(3, img);
            ps.setInt(4, blogId);
            isSuccess = ps.executeUpdate();
        }catch(SQLException e){
            System.out.println(e);
        }
        return isSuccess;
    }
    
    public Blog getLastBlog(){
        String query = "Select * FROM [DURIAN_SHOP].[dbo].[Blog] "
                + "WHERE id = (select MAX(id) from [DURIAN_SHOP].[dbo].[Blog]) ";
        try{
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                return new Blog(rs.getInt(1), rs.getNString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getString(6), rs.getDate(7));
            }
            ps.close();
            rs.close();
        }catch(SQLException e){
            System.out.println(e);
        }
        return null;
    }
    
    public static void main(String[] args) {
        BlogDAO bd = new BlogDAO();
        List<BlogCategory> b = bd.getListBlogCategory(1);
        for (BlogCategory blogCategory : b) {
            System.out.println(blogCategory.getName());
        }
    }
   
}
