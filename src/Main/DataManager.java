/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.*;
import java.util.*;
/**
 *
 * @author Andi
 */
public class DataManager {
    private static DataManager dataManager = new DataManager();
    
    private DataManager(){
    
    }
    
    public static DataManager getInstance(){
        return dataManager;
    }
    
    public void addStation(Station station){
        try{
            Connection con = ConnectionManager.getInstance().getConnection();
            Statement statement = con.createStatement();
            String stationName = station.getName();
            String sql = "insert into stations "
                    + "(name) values"
                    + "('" + stationName + "')";
         statement.executeUpdate(sql);
        
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public void clearDB(){
        try{
            Connection con = ConnectionManager.getInstance().getConnection();
            Statement statement = con.createStatement();
            String sql = "delete from stations";
            statement.executeUpdate(sql);
            sql = "alter table stations auto_increment = 1";
            statement.executeUpdate(sql);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public List<Station> getStationsFromDB(){
        List<Station> stationList = new ArrayList<Station>(); 
        try{
            Connection con = ConnectionManager.getInstance().getConnection();
            Statement statement = con.createStatement();
            
            ResultSet result = statement.executeQuery("select * from stations");
            
            while(result.next()){
               String stationName = result.getString("name");
               Station newStation = new Station(stationName);
               stationList.add(newStation);
            }
        
        }catch(SQLException e){
            e.printStackTrace();
        }
        return stationList;
    }
    
    public void getRoutes(RouteService routeService){
        
    }
    
}
