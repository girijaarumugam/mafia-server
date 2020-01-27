package com.mafia.persistence.mysql.util;

import com.mafia.exceptions.DBException;
import com.mafia.persistence.mysql.MySqlAPI;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * created by girija-4135 on 24/01/20
 */
public class DBOperations {

    public static long addUser(String name,String email) throws Exception
    {
        MySqlAPI mySqlAPI = new MySqlAPI();
        Connection connect = null;
        try {
            connect = mySqlAPI.connect();
        } catch (SQLException | ClassNotFoundException e) {
            throw new DBException("[DBOperations] [addUser()] Exception occurred during Database opening connection...",e);
        }catch (Exception e){
            throw e;
        }

        // for insert a new candidate
        ResultSet rs = null;
        String sql = "INSERT INTO user(name,email) "
                + "VALUES(?,?)";

        try (
                PreparedStatement pstmt = connect.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);) {

            // set parameters for statement
            pstmt.setString(1, name);
            pstmt.setString(2, email);

            int rowAffected = pstmt.executeUpdate();
            if(rowAffected == 1)
            {
                // get candidate id
                rs = pstmt.getGeneratedKeys();
                if(rs.next())
                    return rs.getLong(1);

            }
        } catch (SQLException ex) {
            throw new DBException("[DBOperations] [addUser()] Exception occurred during Database insert user operation...",ex);
        } finally {
            try {
                if(rs != null)  rs.close();
            }catch (SQLException  e) {
                throw new DBException("[DBOperations] [addUser()] Exception occurred during Database connection...",e);
            }catch (Exception e){
                throw e;
            }
        }

        if(connect != null){
            try {
                mySqlAPI.close();
            } catch (SQLException  e) {
                throw new DBException("[DBOperations] [addUser()] Exception occurred during Database closing connection...",e);
            }catch (Exception e){
                throw e;
            }
        }
        return Long.parseLong(null);
    }
}
