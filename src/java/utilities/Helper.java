/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilities;

/**
 *
 * @author kienk
 */
public class Helper {
    public static String[] splitText(String text, String regex){
        String[] arr = text.split(regex);
        for (int i = 0; i<arr.length; i++){
            if (!arr[i].trim().endsWith(".")){
                arr[i]+= ".";
            }
        }
        return arr;
    }
    
    public static int getEndPage(int total, int amount){
        return (int) Math.ceil((double) total / amount);
        
    }
    
}
