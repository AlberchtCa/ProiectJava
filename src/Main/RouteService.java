/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author Andi
 */
public class RouteService {
    //THE SINGLETONE
    private static final RouteService routeService = new RouteService();
    Scanner scan = new Scanner(System.in);
    IOservice ioService = IOservice.getInstance();
    private RouteService(){
        
    }
    
    public static RouteService getInstance(){
        return routeService;
    } 
            //DECLARING SOME STATIONS
    
    private Station[] stations_i = new Station[]{
        new Station("Eroilor"),
        new Station("Mihai Bravu"),
        new Station("Obor"),
        new Station("13 Septembrie"),
        new Station("Margeanului"),
        new Station("Avrig"),
        new Station("Dristor 1"),
        new Station("Timpuri Noi")
        };
            //CREATING A LIST FOR THEM
    List<Station> stations = new ArrayList<Station>();
    
            //APPENDING THEM TO THE LIST
    
    public void AppendToStationsList(){
            int i;
            for(i=0;i<8;i++){
            stations.add(stations_i[i]);
            }
    }
    int station_no = 8;
        //ADD STATION FUNCTION
    public void addStation(String name){        
        System.out.println("Adding new station: " + name);
        Station new_station = new Station(name);
        stations.add(new_station);
        System.out.println("Does the station have links? 1/0");
        int g = scan.nextInt();
        int k = 1;
        Scanner scan2 = new Scanner(System.in);
        Links new_link = new Links();
        if(g==1)
        {
            System.out.println("Specify the train station links. Press 0 when done or if none.");
            Set<TrainStation> new_train_set = new HashSet<>();
            while(k==1){
                String link = scan2.nextLine();
                if(!link.equals("0")){
                    //trebuie sa adaug string pentru numele statiei de tren. (link)
                    //la setul linksTrain
                    //din links din station.
                    TrainStation new_train = new TrainStation(link);
                    new_train_set.add(new_train);
                }
                else k=0;
            }
            k=1;
            new_link.setLinksTrain(new_train_set);
            System.out.println("Specify the subway station links. Press 0 when done or if none.");
            Set<Subway> new_sub_set = new HashSet<>();;
            while(k==1){
                String link = scan.nextLine();
                if(!link.equals("0")){
                    Subway new_sub = new Subway(link);
                    new_sub_set.add(new_sub);
                }
                else k=0;
            }
            new_link.setLinksSub(new_sub_set);
        }
        stations.get(stations.size()-1).setLinks(new_link);
        System.out.println("Station " + name + " has been added. Press 0 to continue.");
        station_no++;
        
        ioService.WriteToAudit(GetDateTime(),"addStation");
    }
    
    public void showLinks(Station selected_station){
        System.out.println("Showing links for: " + selected_station.getName());
        Links link = selected_station.getLinks();
        System.out.println("Subway links: ");
        if(link.getLinksSub().size()>0){
            Subway[] s = new Subway[link.getLinksSub().size()];
            s = (Subway[]) link.getLinksSub().toArray(new Subway[0]);
            int i;
            for(i=0;i<s.length;i++){
               System.out.println((i+1) + ". " + s[i].getName());
            }
        }
        else System.out.println("None");
        
        System.out.println("Train links: ");
        if(link.getLinksTrain().size()>0){
            TrainStation[] t = new TrainStation[link.getLinksTrain().size()];
            t = (TrainStation[]) link.getLinksTrain().toArray(new TrainStation[0]);
            int i;
            for(i=0;i<t.length;i++){
                System.out.println((i+1) + ". " + t[i].getName());
            }
        }
        else System.out.println("None");
        
        ioService.WriteToAudit(GetDateTime(),"showLinks");
    }
    
    public void changeLinks(int chosen_index){
        Scanner scan2 = new Scanner(System.in);
        int k = 1;
        Links new_link = new Links();
        System.out.println("Specify the train station links. Press 0 when done or if none.");
            Set<TrainStation> new_train_set = new HashSet<>();
            while(k==1){
                String link = scan2.nextLine();
                if(!link.equals("0")){
                    //trebuie sa adaug string pentru numele statiei de tren. (link)
                    //la setul linksTrain
                    //din links din station.
                    TrainStation new_train = new TrainStation(link);
                    new_train_set.add(new_train);
                }
                else k=0;
            }
            k=1;
            new_link.setLinksTrain(new_train_set);
            
            ioService.WriteToAudit(GetDateTime(),"linkAddTrain");
            
            System.out.println("Specify the subway station links. Press 0 when done or if none.");
            Set<Subway> new_sub_set = new HashSet<>();;
            while(k==1){
                String link = scan.nextLine();
                if(!link.equals("0")){
                    Subway new_sub = new Subway(link);
                    new_sub_set.add(new_sub);
                }
                else k=0;
            }
            new_link.setLinksSub(new_sub_set);
        
        stations.get(chosen_index).setLinks(new_link);
        
        ioService.WriteToAudit(GetDateTime(),"linkAddStation");
        
        System.out.println("The links to the station have been modified.");
        
        ioService.WriteToAudit(GetDateTime(),"changeLinks");
    }
    
    
        //SHOW STATIONS FUNCTION
    public void showStations(){
        int i;
        for(i=0;i<station_no;i++){
            System.out.println((i+1) + "." + stations.get(i).getName());
        }
        if(station_no == 0){
            System.out.println("No stations.");
        }
        
        ioService.WriteToAudit(GetDateTime(),"showStations");
    }
        //CREATE A NEW ROUTE BY MAKING A STATION ARRAY
    
    Station s;
    List<Station> aux = new ArrayList<Station>();
    int a_len = 0;
    
    public void initializeList(){
        aux.clear();
        a_len = 0;
    }
    
    public void createRouteStations(){
        showStations();
        Scanner scan = new Scanner(System.in);
        int index_picked = 1;
        while(index_picked != 0){
            index_picked = scan.nextInt();
            if(index_picked == 0){
                addRoute(aux,a_len);
            }
            else{
                if(index_picked > stations.size() || index_picked < 0){
                    System.out.println("Wrong station index.");
                }
                else{
                    aux.add(stations.get(index_picked-1));
                    a_len++;
                }
            }
        }
    }
    
    int route_no=0;
    List<Route> routes = new ArrayList<Route>();
        //ADD ROUTE FUNCTION
    public void addRoute(List<Station> stations,int len){
        Route new_route = new Route(stations,len);
        routes.add(new_route);
        route_no++;
        
        ioService.WriteToAudit(GetDateTime(),"addRoute");
    }
        //SHOW ROUTES FUNCTION
    public void showRoutes(){
        int i;
        for(i=0;i<route_no;i++){
            System.out.println((i+1) + "." 
                                + " From " +routes.get(i).getDeparture().getName() 
                                + " to " + routes.get(i).getDestination().getName() 
                                + " via " + routes.get(i).getVia().getName());
        }
        if(route_no==0){
            System.out.println("No routes.");
        }
        
        
        ioService.WriteToAudit(GetDateTime(),"showRoutes");
    }
    
    int vehicle_no;
    List<Vehicle> vehicles = new ArrayList<Vehicle>();
        //SHOW VEHICLES FUNCTION
    public void showVehicles(){
        int i;
        for(i=0;i<vehicle_no;i++){
            System.out.println((i+1) + ". Vehicle with ID: " + vehicles.get(i).getId());
        }
        
        if(i==0){
            System.out.println("No vehicles.");
        }
        
        ioService.WriteToAudit(GetDateTime(),"showVehicles");
    }
    
        //ADD VEHICLE FUNCTION
    public void addVehicle(){
        int route_index = scan.nextInt() -1;
        Vehicle new_vehicle = new Vehicle(vehicle_no,routes.get(route_index));
        vehicles.add(new_vehicle);
        System.out.println("Vehicle added with id: " + route_index);
        vehicle_no++;
        
        ioService.WriteToAudit(GetDateTime(),"addVehicle");
    }
    
    public Date GetDateTime()
    {
            DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
            Date date = new Date();
            return date;
    }
    
    public void getDataFromFiles(){
        //STATIONS
        List<Station> stations_from_file = new ArrayList<>();
        stations_from_file = ioService.GetStationsFromFile();
        int i;
        for(i=0;i<stations_from_file.size();i++){
            stations.add(stations_from_file.get(i));
            station_no++;
        }
        
        //LINKS
        List<LinksBelongTo> links_from_file = new ArrayList<>();
        links_from_file = ioService.GetLinksFromFile();
        
        for(i=0;i<links_from_file.size();i++){
            Station selected_station = stations.get(links_from_file.get(i).getStationIndex());
            Links new_link = new Links();
            new_link.setLinksSub(links_from_file.get(i).getLinksSub());
            new_link.setLinksTrain(links_from_file.get(i).getLinksTrain());
            selected_station.setLinks(new_link);
        }
        
        //ROUTES
        List<Route> routes_from_file = new ArrayList<>();
        routes_from_file = ioService.GetRoutesFromFile();
        
        for(i=0;i<routes_from_file.size();i++){
            routes.add(routes_from_file.get(i));
            route_no++;
        }
        
        //VEHICLES
        List<VehicleBelongsTo> vehicles_from_file = new ArrayList<>();
        vehicles_from_file = ioService.GetVehiclesFromFile();
        
        for(i=0;i<vehicles_from_file.size();i++){
            Route selected_route = routes.get(vehicles_from_file.get(i).getRouteIndex());
            Vehicle new_vehicle = new Vehicle(vehicles_from_file.get(i).getId(),selected_route);
            
            vehicles.add(new_vehicle);
            vehicle_no++;
        }
        
    }
}