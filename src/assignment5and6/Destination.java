/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment5and6;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Scanner;

/**
 *
 * @author bener
 */
public class Destination implements Serializable{
    
    private String destination;
    private int duration;
    private String wentwith;
    private int year;
    private String comments;
    
    Scanner reader = null;
    PrintWriter writer = null;
    String inFile;
    String outFile;
    
    int count = 0;
    
    public Destination(){
    }
    
    public Destination(String destination, int duration, String wentwith, int year, String comments){
        this.destination = destination;
        this.setDuration(duration);
        this.setWentwith(wentwith);
        this.setYear(year);
        this.setComments(comments);
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setWentwith(String wentwith) {
        this.wentwith = wentwith;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getDestination() {
        return destination;
    }

    public int getDuration() {
        return duration;
    }

    public String getWentwith() {
        return wentwith;
    }

    public int getYear() {
        return year;
    }

    public String getComments() {
        return comments;
    }

    @Override
    public String toString() {
        count++;
        return "List " + count + "\n"
                +"Destination : " + destination + "\n"
                +"Days of Stay : " + duration + "\n"
                +"Went with : " + wentwith + "\n"
                +"Year : " + year + "\n"
                +"Comments : " + comments;
    }
    
}
