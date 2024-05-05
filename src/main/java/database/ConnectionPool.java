package database;

import lombok.Getter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Stack;
public class ConnectionPool {
    private static final int NUM_OF_CONS = 10;

    @Getter
    private static final ConnectionPool instance = new ConnectionPool();

    private Stack<Connection> connections = new Stack<>();


    private ConnectionPool() {
        for (int i = 0; i < NUM_OF_CONS; i++) {
            try {
                connections.push(DriverManager.getConnection(DatabaseManager.URL,DatabaseManager.USERNAME,DatabaseManager.PASSWORD));
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public Connection getConnection()  {
        synchronized (connections){
            if(connections.size() == 0){
                try {
                    connections.wait();
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());;
                }
            }
        }
        return connections.pop();
    }

    public void restoreConnection(Connection conn){
        synchronized (connections){
            connections.push(conn);
            connections.notify();
        }
    }

    public void closeAll() throws SQLException {
        synchronized (connections){
            if(connections.size() != NUM_OF_CONS){
                try {
                    wait();
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        for (Connection conn:connections) {
            conn.close();
        }
    }
}
