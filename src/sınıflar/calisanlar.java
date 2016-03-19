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

/**
 *
 * @author oktay
 */
public class calisanlar {

   
    private String calisan_id;
    private String tc_no;
    private String sicil_no;
    private String ad;
    private String soyad;
    private String birim;
    private String cinsiyet;
    private String dogum_tarihi;
    private String sehir;
    
    PreparedStatement ps;
    ResultSet rs;

    

 
    
    
     public String dateFormat(Date d){
        DateFormat df= new SimpleDateFormat("yyyy-MM-dd");
        String date= df.format(d);
        return date;
    }
     
    
    public boolean calisanEkle(calisanlar cEkle){
        try {
            veritabani_baglanti vb=new veritabani_baglanti();
            vb.baglan();
            String sorgu="insert into calisanlar "
                    +"(tc_no,sicil_no,calisan_ad,calisan_soyad,birim,cinsiyet,dogum_tarihi,sehir)"
                    +"values(?,?,?,?,?,?,?,?)";
            
            ps=vb.con.prepareStatement(sorgu);
            ps.setString(1, cEkle.getTc_no());
            ps.setString(2, cEkle.getSicil_no());
            ps.setString(3, cEkle.getAd());
            ps.setString(4, cEkle.getSoyad());
            ps.setString(5, cEkle.getBirim());
            ps.setString(6, cEkle.getCinsiyet());
            ps.setString(7, cEkle.getDogum_tarihi());
            ps.setString(8, cEkle.getSehir());
            ps.execute();
            

        } catch (Exception ex) {
            return false;
        }
        
        return true;
       
        
    }
        
    public boolean calisanGuncelle(calisanlar cGuncelle){
    try {
        veritabani_baglanti vb= new veritabani_baglanti();
      vb.baglan();
      String sorgu="update calisanlar set calisan_ad=?, calisan_soyad=? where calisan_id=?";
      ps=vb.con.prepareStatement(sorgu);
      ps.setString(1, cGuncelle.getAd());
      ps.setString(2, cGuncelle.getSoyad());
      ps.setString(3, cGuncelle.getCalisan_id());
      ps.execute();
    } catch (Exception e) {
        return false;
    }
    return true;
}    
    
    public ArrayList<calisanlar> calisanAra(String ad,String soyad,String sicil_no) throws SQLException{
        veritabani_baglanti vb= new veritabani_baglanti();
        vb.baglan();
        ArrayList<calisanlar> list=new ArrayList<>();
        String sorgu="select * from calisanlar where "
                + "calisan_ad like '%"+ad+"%' and calisan_soyad like '%"+soyad+"%' and sicil_no like '%"+sicil_no+"%'";
            ps=vb.con.prepareStatement(sorgu);
            rs=ps.executeQuery();
            while(rs.next()){
                calisanlar cs=new calisanlar();
                cs.setCalisan_id(rs.getString(1));
                cs.setTc_no(rs.getString(2));
                cs.setSicil_no(rs.getString(3));
                cs.setAd(rs.getString(4));
                cs.setSoyad(rs.getString(5));
                cs.setBirim(rs.getString(6));
                cs.setCinsiyet(rs.getString(7));
                cs.setDogum_tarihi(rs.getString(8));
                cs.setSehir(rs.getString(9));
                list.add(cs);
            }
        ps.close();
        rs.close();
        return list;
    }
    
    
    public ArrayList<calisanlar> cListele() throws SQLException{
    veritabani_baglanti vb = new veritabani_baglanti();
    vb.baglan();
    ArrayList<calisanlar> list=new ArrayList<>();
    String sorgu ="select * from calisanlar";
    ps=vb.con.prepareStatement(sorgu);
    rs=ps.executeQuery();
    while(rs.next()){
        calisanlar cr=new calisanlar();
        cr.setCalisan_id(rs.getString(1));
        cr.setTc_no(rs.getString(2));
        cr.setSicil_no(rs.getString(3));
        cr.setAd(rs.getString(4));
        cr.setSoyad(rs.getString(5));
        cr.setBirim(rs.getString(6));
        cr.setCinsiyet(rs.getString(7));
        cr.setDogum_tarihi(rs.getString(8));
        cr.setSehir(rs.getString(9));
        list.add(cr);
    }
    return list;
}
   
    
    public String getCalisan_id() {
        return calisan_id;
    }

    public void setCalisan_id(String calisan_id) {
        this.calisan_id = calisan_id;
    }

    public String getTc_no() {
        return tc_no;
    }

    public void setTc_no(String tc_no) {
        this.tc_no = tc_no;
    }

    public String getSicil_no() {
        return sicil_no;
    }

    public void setSicil_no(String sicil_no) {
        this.sicil_no = sicil_no;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getSoyad() {
        return soyad;
    }

    public void setSoyad(String soyad) {
        this.soyad = soyad;
    }

    public String getBirim() {
        return birim;
    }

    public void setBirim(String birim) {
        this.birim = birim;
    }

    public String getCinsiyet() {
        return cinsiyet;
    }

    public void setCinsiyet(String cinsiyet) {
        this.cinsiyet = cinsiyet;
    }

    public String getDogum_tarihi() {
        return dogum_tarihi;
    }

    public void setDogum_tarihi(String dogum_tarihi) {
        this.dogum_tarihi = dogum_tarihi;
    }

    public String getSehir() {
        return sehir;
    }

    public void setSehir(String sehir) {
        this.sehir = sehir;
    }

    
    
    
    
      
}

     
    
    
    

