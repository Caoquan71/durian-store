/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author kienk
 */
public class ProductVariantInfomation {
    private int id;
    private String name;
    private String screen;
    private String camera;
    private String ram;
    private String pin;
    private String chipset;
    private String screenResolution;
    private String url;
    private String color;
    private String storage;
    private int quantity;
    private float variantPrice;
    private int status;
    private float sale;

    public ProductVariantInfomation() {
    }

    public ProductVariantInfomation(int id, String name, String screen, String camera, String ram, String pin, String chipset, String screenResolution, String url, String color, String storage, int quantity, float variantPrice, int status, float sale) {
        this.id = id;
        this.name = name;
        this.screen = screen;
        this.camera = camera;
        this.ram = ram;
        this.pin = pin;
        this.chipset = chipset;
        this.screenResolution = screenResolution;
        this.url = url;
        this.color = color;
        this.storage = storage;
        this.quantity = quantity;
        this.variantPrice = variantPrice;
        this.status = status;
        this.sale = sale;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScreen() {
        return screen;
    }

    public void setScreen(String screen) {
        this.screen = screen;
    }

    public String getCamera() {
        return camera;
    }

    public void setCamera(String camera) {
        this.camera = camera;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getChipset() {
        return chipset;
    }

    public void setChipset(String chipset) {
        this.chipset = chipset;
    }

    public String getScreenResolution() {
        return screenResolution;
    }

    public void setScreenResolution(String screenResolution) {
        this.screenResolution = screenResolution;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getVariantPrice() {
        return variantPrice;
    }

    public void setVariantPrice(float variantPrice) {
        this.variantPrice = variantPrice;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public float getSale() {
        return sale;
    }

    public void setSale(float sale) {
        this.sale = sale;
    }

   
   
}
