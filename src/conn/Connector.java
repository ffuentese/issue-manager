/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package conn;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author Francisco
 */
public class Connector {
  public static Connector instance ;

  public static Connection conn = null;

   /** Constructor de DbConnection */
   public Connector() {
      try{
         //obtenemos el driver de para mysql
         Class.forName("com.mysql.jdbc.Driver");
         //obtenemos la conexi�n
          Properties props = new Properties();
          props.load(new FileInputStream("connection.properties"));
    
        String bd = props.getProperty("bd");
        String login = props.getProperty("login");
        String password = props.getProperty("password");
        String url = props.getProperty("url")+bd;
        System.out.println("Connecting to database "+bd+"...");
        conn = DriverManager.getConnection(url,login,password);

         if (conn!=null){
            System.out.println("Connection to database "+bd+" successful!");
         }
      }
      catch(SQLException e){
         System.out.println(e);
      }catch(ClassNotFoundException e){
         System.out.println(e);
      }catch(Exception e){
         System.out.println(e);
      }
   }
   /**Permite retornar la conexi�n*/
   public  synchronized static Connector getConnection(){
       if(instance == null){
		   instance = new Connector();
		 }
		 return instance;
   }
   
    public static Connection getConexion() {
     try {
        return conn;
     }
     catch(Exception ex) {
         System.out.println("No es posible establecer una conexión a la base");
     }
     return conn;
    }

   public void desconectar(){
      instance = null;
   }
}
