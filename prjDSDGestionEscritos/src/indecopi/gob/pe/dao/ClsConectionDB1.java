package indecopi.gob.pe.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import java.sql.Connection;
import java.sql.SQLException;

import java.util.Properties;

import javax.naming.InitialContext;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

public class ClsConectionDB1 {
    private Connection conn = null;
    private String jdbc_conn;

    public ClsConectionDB1() throws ClassNotFoundException, SQLException {

        Properties props = new Properties();
        String dbPropsFile = "connection.properties";
        this.jdbc_conn = "";
        try {
            try {
                InputStream inputStream =
                    this.getClass().getResourceAsStream(dbPropsFile);
                if (inputStream == null) {
                    throw new FileNotFoundException("property file '" +
                                                    dbPropsFile +
                                                    "' not found in the classpath");
                }
                props.load(inputStream);
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
            jdbc_conn = props.getProperty("jdbc_conn1");
            InitialContext initialContext = new InitialContext();
            DataSource ds = (DataSource)initialContext.lookup(this.jdbc_conn);
            this.conn = ds.getConnection();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection f_getConn() {
        return conn;
    }

    public void f_endConn() {
        try {
            if (this.conn != null)
                this.conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    }

