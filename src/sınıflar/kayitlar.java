/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sınıflar;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import arayuz.form2;

/**
 *
 * @author oktay
 */
public class kayitlar {
    
    private String kayit_id;
    private String calisan_id;
    private String izin_tur;
    private String baslangıc;
    private String bitis;
    private String sure;
    
    
    PreparedStatement ps;
    ResultSet rs;

    
    
    
    
    public boolean kayitEkle(kayitlar cEkle){
            try {
            veritabani_baglanti vb=new veritabani_baglanti();
            vb.baglan();
            String sorgu="insert into kayitlar "
                    +"(calisan_id,izin_tür,izin_baslangic,izin_bitis,izin_süresi)"
                    +"values(?,?,?,?,?)";
            
            ps=vb.con.prepareStatement(sorgu);
            ps.setString(1, cEkle.getCalisan_id());
            ps.setString(2, cEkle.getIzin_tur());
            ps.setString(3, cEkle.getBaslangıc());
            ps.setString(4, cEkle.getBitis());
            ps.setString(5, cEkle.getSure());
            ps.execute();
            

        } catch (Exception ex) {
            return false;
        }
        
        return true;
       
        
    }
    public boolean kayitSil(String kSil){
    try {
        veritabani_baglanti vb = new veritabani_baglanti();
        vb.baglan();
        String sorgu="delete from kayitlar where kayit_id=?";
        ps=vb.con.prepareStatement(sorgu);
        ps.setString(1, kSil);
        ps.execute();
    } catch (Exception e) {
        System.out.println(e);
        return false;
    }
        return true;
    }
    
    public ObservableList<ArrayList> calisan_idGetir() throws SQLException{
        veritabani_baglanti vb = new veritabani_baglanti();
        vb.baglan();
        ObservableList<ArrayList> list=FXCollections.observableArrayList();
        String sorgu ="select calisan_id from calisanlar";
        ps=vb.con.prepareStatement(sorgu);
        rs=ps.executeQuery();
        while(rs.next()){
        ArrayList<String> liste = new ArrayList<>();
        liste.add(rs.getString("calisan_id"));
        list.add(liste);
    }
    return list;  
       }

    
    public String getKayit_id() {
        return kayit_id;
    }

    public void setKayit_id(String kayit_id) {
        this.kayit_id = kayit_id;
    }

    public String getCalisan_id() {
        return calisan_id;
    }

    public void setCalisan_id(String calisan_id) {
        this.calisan_id = calisan_id;
    }

    public String getIzin_tur() {
        return izin_tur;
    }

    public void setIzin_tur(String izin_tur) {
        this.izin_tur = izin_tur;
    }

    public String getBaslangıc() {
        return baslangıc;
    }

    public void setBaslangıc(String baslangıc) {
        this.baslangıc = baslangıc;
    }

    public String getBitis() {
        return bitis;
    }

    public void setBitis(String bitis) {
        this.bitis = bitis;
    }

    public String getSure() {
        return sure;
    }

    public void setSure(String sure) {
        this.sure = sure;
    }
    
    
    
}
