/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sınıflar;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author oktay
 */
public class giris_yap {
    ResultSet rs;
    PreparedStatement ps;
    
    public boolean giris(String kadi,String sifre) throws SQLException{
        veritabani_baglanti vb=new veritabani_baglanti();
        vb.baglan();
        
        String sorgu="select sifre from admin where kadi= ?";
        ps=vb.con.prepareStatement(sorgu);
        ps.setString(1,kadi);
        rs=ps.executeQuery();
        
        while(rs.next()){
            String pw=rs.getString("sifre");
            if(pw.equals(sifre)){
                return true;
            }
            else{
                return false;
            }
                
        } 
        return false;
    }
    
}
