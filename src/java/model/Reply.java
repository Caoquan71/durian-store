/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author 84834
 */
public class Reply {
    private int Id;
    private int commnetId;
    private String content;

    public Reply() {
    }

    public Reply(int Id, int commnetId, String content) {
        this.Id = Id;
        this.commnetId = commnetId;
        this.content = content;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public int getCommnetId() {
        return commnetId;
    }

    public void setCommnetId(int commnetId) {
        this.commnetId = commnetId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
    
    
}
