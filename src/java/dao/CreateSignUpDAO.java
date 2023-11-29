/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.ArrayList;
import model.SecurityQuestion;

/**
 *
 * @author 84834
 */
public class CreateSignUpDAO {
    public static void main(String[] args) {
        test("aq", "1", "1", "", "1", "1");
    }
    
    public static void test(String fusername, String fpassword, String fquestion, String fname, String fanswer, String fphone) {
        AccountDAO accDao = new AccountDAO();
        UserDAO userDao = new UserDAO();
        SecurityDAO securityDao = new SecurityDAO();
        //Lay du lieu tu form nguoi dung nhap        
        String userName = fusername;
        String passWord = fpassword;
        int ques_id = -1;
        try {
            ques_id = Integer.parseInt(fquestion);
        } catch (Exception e) {
            System.out.println(e);
        }
        String name = fname;
        String answer = fanswer;
        String phone = fphone;
        //Kiem tra xem tai khoan do da duoc tao ra hay chua    
        int succes = accDao.getAccountID(userName);
        // Neu tai khoan da ton tai thi se tra ve voi 1 mess    
        if (succes != 0) {
            System.out.println("da ton tai");
        } //Neu chua duoc tao thi se tao tai khoan tu username va password    
        else {
            succes = accDao.createAccount(userName, passWord);
            // Neu tao that bai thi se quay tro ve trang dang ki cung voi tin nhan loi thuoc ve he thong           
            if (succes == 0) {
                System.out.println("Loi tao acc");
            } //Neu chay thanh cong thi se tiep tuc tao truong thong tin trong bang user
            else {
                int id = accDao.getAccountID(userName);
                succes = userDao.createUser(id, phone, name);
                // Neu tao that bai thi se quay tro ve trang dang ki cung voi tin nhan loi thuoc ve he thong
                if (succes == 0) {
                    System.out.println("Loi tao user");
                } //Neu tao thanh cong se tao them 1 truong trong bang Security
                else {
                    if (ques_id != -1) {
                        succes = securityDao.insertSecurity(ques_id, id, answer);
                        if (succes == 1) {
                            System.out.println("Thanh Cong");
                        }

                    }
                }
            }
        }
    }
}
