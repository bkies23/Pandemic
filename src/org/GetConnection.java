package org;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

// Programmer Brian Kies

public class GetConnection 
{
    private static Connection myConn;
    static final String DB_URL = "jdbc:mysql://localhost:3306/covid19db?allowedLoadLocalInfile=true";
    static final String DB_USER = "root";
    static final String DB_PW = "Nonnel23";
    
    public static Connection init () throws SQLException
    {
        try 
        {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        } 
        catch (ClassNotFoundException ex) 
        {
            Logger.getLogger(GetConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        catch (InstantiationException | IllegalAccessException e)
        {
            System.err.println ("Cannot connect to database server");
        }
       
        myConn = DriverManager.getConnection(DB_URL, DB_USER, DB_PW);
        
        return myConn;   
    }         
}
