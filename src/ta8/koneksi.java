package ta8;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;


public class koneksi
{
 Connection koneksi=null;
 public static Connection koneksiDb()
 {
     try
     {
         Class.forName("com.mysql.jdbc.Driver");
         Connection koneksi = DriverManager.getConnection("jdbc:mysql://localhost/db_game","root","");
         return koneksi;
     }
     catch(Exception e)
     {
         JOptionPane.showMessageDialog(null, e);
         return null;
     }
 }

    public Connection koneksiDB() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    public Connection connect() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
}
