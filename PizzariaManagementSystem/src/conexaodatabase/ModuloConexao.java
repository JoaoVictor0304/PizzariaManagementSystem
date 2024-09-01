/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexaodatabase;
import java.sql.*;
import javax.swing.JOptionPane;
/**
 *
 * @author lucas
 */
public class ModuloConexao {
    public static Connection connector(){
        Connection conexao = null;
        String Driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/pizzariadatabase";
        String user = "root";
        String password = "root";
        
        try {
            Class.forName(Driver);
            conexao = DriverManager.getConnection(url,user,password);
            return conexao;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
 
    }
    
    
}
