package com.mafia.persistence.mysql;

import com.mafia.constants.MafiaDBProperty;
import com.mafia.util.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * created by girija-4135 on 24/01/20
 */
public class MySqlAPI {

    private static final Logger LOGGER = Logger.getLogger(MySqlAPI.class.getName());

    private Connection connection;

    public Connection connect() throws SQLException,ClassNotFoundException
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");

            // Establishing Connection
            String url = Configuration.getValue(MafiaDBProperty.DBURL).replace(MafiaDBProperty.HOSTNAME,Configuration.getValue(MafiaDBProperty.HOSTNAME))
                    .replace(MafiaDBProperty.PORT,Configuration.getValue(MafiaDBProperty.PORT)).replace(MafiaDBProperty.DBNAME,Configuration.getValue(MafiaDBProperty.DBNAME));

            connection = DriverManager.getConnection(url, Configuration.getValue(MafiaDBProperty.USERNAME), Configuration.getValue(MafiaDBProperty.PASSWORD));
            if(connection != null){
                LOGGER.log(Level.INFO,"MYSQL Connection established!!!");
            }
            return connection;

        }catch (SQLException | ClassNotFoundException e){
            throw e;
        }
    }

    public void close() throws SQLException {
        if(connection != null)
        {
            connection.close();
            LOGGER.log(Level.INFO,"MYSQL Connection closed!!!");
        }
    }
}
