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
import sınıflar.calisanlar;
import sınıflar.kayitlar;
/**
 *
 * @author oktay
 */
public class listeleme {
    ResultSet rs;
    PreparedStatement ps;
    
   public String dateFormat(Date d){
        DateFormat df= new SimpleDateFormat("yyyy-MM-dd");
        String date= df.format(d);
        return date;
    }
   
   
   
    public ObservableList<ArrayList> sicilGetir() throws SQLException{
    veritabani_baglanti vb = new veritabani_baglanti();
    vb.baglan();
    ObservableList<ArrayList> list=FXCollections.observableArrayList();
     String sorgu ="select sicil_no from calisanlar";
    ps=vb.con.prepareStatement(sorgu);
    rs=ps.executeQuery();
    while(rs.next()){
        ArrayList<String> liste = new ArrayList<>();
        liste.add(rs.getString("sicil_no"));
        list.add(liste);
    }
    return list;  
}
    
    public ObservableList<kayitlar> listele(String calisan_id) throws SQLException{
    veritabani_baglanti vb = new veritabani_baglanti();
    vb.baglan();
    ObservableList<kayitlar> list=FXCollections.observableArrayList();
    String sorgu ="select * from kayitlar where calisan_id = ?";
    ps=vb.con.prepareStatement(sorgu);
    ps.setString(1, calisan_id);
    rs=ps.executeQuery();
    while(rs.next()){
        kayitlar kz = new kayitlar();
        kz.setKayit_id(rs.getString(1));
        kz.setCalisan_id(rs.getString(2));
        kz.setIzin_tur(rs.getString(3));
        kz.setBaslangıc(rs.getString(4));
        kz.setBitis(rs.getString(5));
        kz.setSure(rs.getString(6));
        list.add(kz);
    }
    return list;
}
    
    /**
     *
     */
    public ObservableList<ArrayList> tizinListele() throws SQLException{
    veritabani_baglanti vb = new veritabani_baglanti();
    vb.baglan();
    ObservableList<ArrayList> list=FXCollections.observableArrayList();
    String sorgu ="select c.calisan_ad,c.calisan_soyad,sum(k.izin_süresi) from kayitlar k,calisanlar c "
            + "where c.calisan_id = k.calisan_id group by c.calisan_id";
    ps=vb.con.prepareStatement(sorgu);
    rs=ps.executeQuery();
    while(rs.next()){
        ArrayList<String> liste= new ArrayList<>();
        liste.add(rs.getString("c.calisan_ad"));
        liste.add(rs.getString("c.calisan_soyad"));
        liste.add(rs.getString("sum(k.izin_süresi)"));
        
        list.add(liste);
    }
    return list;
}
    public ObservableList<ArrayList> tarihVarMi(String tarih) throws SQLException{
    veritabani_baglanti vb = new veritabani_baglanti();
    vb.baglan();
    ObservableList<ArrayList> list=FXCollections.observableArrayList();
    String sorgu ="select c.calisan_ad,c.calisan_soyad,k.izin_baslangic,k.izin_bitis from kayitlar k,calisanlar c "
            + "where c.calisan_id = k.calisan_id "
            + "and date_format(?,'%y-%m-%d') between k.izin_baslangic and k.izin_bitis";
    ps=vb.con.prepareStatement(sorgu);
    ps.setString(1,tarih);
    rs=ps.executeQuery();
    while(rs.next()){
        ArrayList<String> liste= new ArrayList<>();
        liste.add(rs.getString("c.calisan_ad"));
        liste.add(rs.getString("c.calisan_soyad"));
        liste.add(rs.getString("k.izin_baslangic"));
        liste.add(rs.getString("k.izin_bitis"));
        list.add(liste);
    }
    return list;
}
    
}
