/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package osiristape.hotelbooking;

import java.sql.Date;
import javafx.scene.image.Image;

/**
 *
 * @author shift
 */
public class HotelInfos {
    
    private Integer id;
    private String number;
    private String type;
    private String duration;
    private String image;
    private Date date;
    private String current;
    
    public HotelInfos(Integer Id, String roomNumber, String roomType, String duration, String image, Date date, String current){
        
        this.id = id;
        this.number = roomNumber;
        this.type = roomType;
        this.duration = duration;
        this.image = image;
        this.date = date;
        this.current = current;
        
    }
    
    public Integer getId(){
        return id;
    }
    public String getNumber(){
        return number;
    }
    public String getType(){
        return type;
    }
    public String getDuration(){
        return duration;
    }
    public String getImage(){
        return image;
    }
    public Date getDate(){
        return date;
    }
    public String getCurrent(){
        return current;
    }

    Image getImage(Image image) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}

