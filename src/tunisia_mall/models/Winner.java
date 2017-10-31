/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunisia_mall.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author ahmed
 */
public class Winner {
    int id_tab_winner;
    String Date;
    int id_userwinner;

    public Winner(int id_tab_winner, String Date, int id_userwinner) {
        this.id_tab_winner = id_tab_winner;
        this.Date = Date;
        this.id_userwinner = id_userwinner;
    }

    public Winner(String Date, int id_userwinner) {
        this.Date = Date;
        this.id_userwinner = id_userwinner;
    }

    public Winner() {
    }

    public int getId_tab_winner() {
        return id_tab_winner;
    }

    public void setId_tab_winner(int id_tab_winner) {
        this.id_tab_winner = id_tab_winner;
    }

   

  
    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public int getId_userwinner() {
        return id_userwinner;
    }

    public void setId_userwinner(int id_userwinner) {
        this.id_userwinner = id_userwinner;
    }

    @Override
    public String toString() {
        return "Winner{" + "id_tab_winner=" + id_tab_winner + ", Date=" + Date + ", id_userwinner=" + id_userwinner + '}';
    }

 

    public java.sql.Date convert(String date) throws ParseException {

        SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date date1 = sdf1.parse(date);
        java.sql.Date sqlDate = new java.sql.Date(date1.getTime());

        return sqlDate;
    }

    public static String convert(java.sql.Date d) {
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String text = df.format(d);
        return text;
    }

}
