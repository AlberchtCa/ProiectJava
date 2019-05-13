/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.io.*;
import java.security.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 *
 * @author Andi
 */
public class IOservice {
    
    private static final IOservice ioService = new IOservice();
    
    
    private IOservice(){
 
    }
    
    public static IOservice getInstance(){
        return ioService;
    }
    
    File actions = new File("Audit.csv");
    
    public void WriteToAudit(Date date, String action_done){
         DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        try{
            FileWriter fileWriter = new FileWriter(actions,true);
            PrintWriter output = new PrintWriter(fileWriter);
            output.print(action_done + ", " + dateFormat.format(date) + '\n');
            output.close();
        } catch (IOException ex){
            System.out.printf("ERROR: %s\n", ex);
        }
    }
    
    public List<Station> GetStationsFromFile(){
        File stationsFile = new File("Stations.csv");
        List<Station> stations = new ArrayList<>();
        String line = null;
        try{
            FileReader filein = new FileReader(stationsFile);
            BufferedReader input = new BufferedReader(filein);
            
           while((line = input.readLine())!= null){
               //AICI INTRA CODUL
            //   String[] parts = line.split(", ");
            Station s = new Station(line);
            stations.add(s);
           }
            
            input.close();
        }catch(IOException ex){
            System.out.printf("ERROR: %s\n", ex);
        }
        
        return stations;
    }
    
    public List<LinksBelongTo> GetLinksFromFile(){
        File linksFile = new File("Links.csv");
        List<LinksBelongTo> link = new ArrayList<>();
        String line = null;
        
        try{
            FileReader filein = new FileReader(linksFile);
            BufferedReader input = new BufferedReader(filein);
            
           while((line = input.readLine())!= null){
            String[] parts = line.split(", ");
            
            int station_index,sub_no, train_no, i;
            
            Set<Subway> sub = new HashSet<>();
            Set<TrainStation> train = new HashSet<>();
            
            station_index = Integer.parseInt(parts[0]);
            sub_no = Integer.parseInt(parts[1]);
            
            for(i=0; i<sub_no; i++){
                String subway_name = parts[2+i];
                Subway subway = new Subway(subway_name);
                sub.add(subway);
            }
            
            train_no = Integer.parseInt(parts[2+i]);
            int j;
            for(j=0; j<train_no; j++){
                String train_name = parts[2+i+j+1];
                TrainStation tr = new TrainStation(train_name);
                train.add(tr);
            }
            
            LinksBelongTo s = new LinksBelongTo();
            s.setStation_index(station_index);
            s.setLinksSub(sub);
            s.setLinksTrain(train);
            
            link.add(s);
           }
            
            input.close();
        }catch(IOException ex){
            System.out.printf("ERROR: %s\n", ex);
        }
        
        return link;
    }
    
    public List<Route> GetRoutesFromFile(){
        File inputFile = new File("Routes.csv");
        List<Route> routes = new ArrayList<>();
        String line = null;
        try{
            FileReader filein = new FileReader(inputFile);
            BufferedReader input = new BufferedReader(filein);
            
           while((line = input.readLine())!= null){
               //AICI INTRA CODUL
               String[] parts = line.split(", ");
            int route_len = Integer.parseInt(parts[0]);
            List<Station> stations = new ArrayList<>();
            int i;
            
            for(i=0; i<route_len; i++){                
                stations.add(new Station(parts[i+1]));
            }
            
            Route new_route = new Route(stations,route_len);
            
            routes.add(new_route);
           }
            
            input.close();
        }catch(IOException ex){
            System.out.printf("ERROR: %s\n", ex);
        }
        
        return routes;
    }
    
    public List<VehicleBelongsTo> GetVehiclesFromFile(){
        File inputFile = new File("Vehicles.csv");
        List<VehicleBelongsTo> vehicles = new ArrayList<>();
        String line = null;
        
        try{
            FileReader filein = new FileReader(inputFile);
            BufferedReader input = new BufferedReader(filein);
            
           while((line = input.readLine())!= null){
               String[] parts = line.split(", ");
               List<Station> s = new ArrayList<>();
               //CREEZ GARBAGE STATIONS
               Station[] b= new Station[]{
                   new Station("Statia1"),
                   new Station("Statia2"),
                   new Station("Statia3")
               };
               s.add(b[0]); s.add(b[1]); s.add(b[2]);
               Route r = new Route(s,3);
               
               int route_index, vehicle_id;
               route_index = Integer.parseInt(parts[1]);
               vehicle_id = Integer.parseInt(parts[0]);
               
               VehicleBelongsTo new_vehicle = new VehicleBelongsTo(vehicle_id, r, route_index);
               
               vehicles.add(new_vehicle);
               
               
           }
           
           input.close();
           }catch(IOException ex){
            System.out.printf("ERROR: %s\n", ex);
        }
                
        return vehicles;
    }
    
    
}
