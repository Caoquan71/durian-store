/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author kienk
 */
public class Storage {
    private int id;
    private String storageSize;
    private float priceBonus;
    private int status;

    public Storage() {
    }

    public Storage(int id, String storageSize, float priceBonus, int status) {
        this.id = id;
        this.storageSize = storageSize;
        this.priceBonus = priceBonus;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStorageSize() {
        return storageSize;
    }

    public void setStorageSize(String storageSize) {
        this.storageSize = storageSize;
    }

    public float getPriceBonus() {
        return priceBonus;
    }

    public void setPriceBonus(float priceBonus) {
        this.priceBonus = priceBonus;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
    
}
