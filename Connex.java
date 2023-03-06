
package connect;

import java.sql.*;
public class Connex
{
    public Connection IConnex(){
    Connection conn = null;
    try {
        //étape 1: charger la classe de driver
        Class.forName("oracle.jdbc.driver.OracleDriver");
        //étape 2: créer l'objet de connexion
        conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott","tiger");       
    } catch (Exception e) { e.printStackTrace(); } 
      return conn;
    }
}



