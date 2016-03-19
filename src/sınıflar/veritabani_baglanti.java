/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sınıflar;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author oktay
 */
public class veritabani_baglanti {
    
    public Connection con;
    
    private String url="jdbc:mysql://localhost:3306/";
    private String driver="com.mysql.jdbc.Driver";
    private String dbname="izin_otomasyon";
    private String username="root";
    private String password="190323";
    
    public void baglan(){
        try {
            Class.forName(driver).newInstance();
            con=DriverManager.getConnection(url+dbname,username,password);
            
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,"baglanti hatasi");
        }
    }
    
    
}
