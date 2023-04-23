package org;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// Programmer Brian Kies

// retrieves ResultSet from covid19table for program methods to view, modify, or delete data
public class GetResultSet
{
    Connection myConn;
    ResultSet rs;
    
    public ResultSet getResultSet ( ) throws SQLException
    {
        GetConnection newConnection = new GetConnection();
        myConn = newConnection.init();
        PreparedStatement stmt = myConn.prepareStatement("SELECT * FROM covid19table", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE );
        rs = stmt.executeQuery();
        
        return rs;
    }   
}
