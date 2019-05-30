package Main;

import java.util.Scanner;
import java.util.Date;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

////////////######   DESCRIEREA PROIECTULUI ESTE LA BAZA SURSEI ACESTEA. #######################///////////////////
////////////######   ################################################### #######################///////////////////
public class Main {
    
    
    static Scanner scan = new Scanner(System.in);
    public static void main(String[] args){
        
        //APELEZ SERVICEUL DE PROCESARE
        RouteService routeService = RouteService.getInstance();
        DataManager dataManager = DataManager.getInstance();
        //ADAUG STATIILE INITIALE DIN CODUL SURSA DIN SERVICE (RouteService, line : 280)
//        routeService.AppendToStationsList();
        int g=1;
        int i;
        
        //ADAUG IN BAZA DE DATE:
//        routeService.addDataToDB();
        routeService.getDataFromDB();
        
        //ADAUG DATELE DIN FISIERE
        routeService.getDataFromFiles();
        


        //MENIUL PRINCIPAL
        while(g==1){
            showOptions();
            i=scan.nextInt();
            switch(i){
                case 1:
                    clearScreen();
                    showRoutes(routeService);
                    break;
                case 2:
                    showStations(routeService);
                    break;
                case 3:
                    showVehicles(routeService);
                    break;
                case 4:
                    addRoute(routeService);
                    break;
                case 5:
                    addStation(routeService);
                    break;
                case 6:
                    addVehicle(routeService);
                    break;
                case 7:
                    showLinks(routeService);
                    break;
                case 8:
                    changeLinks(routeService);
                    break;
                case 9:
                    deleteStation(routeService);
                    break;
                case 0:
                    g=0;
                    break;
            }
        }
        
        routeService.updateDB();
    }
    
    public static void showOptions(){
        System.out.println("1. Show Routes");
        System.out.println("2. Show Stations");
        System.out.println("3. Show Vehicles");
        System.out.println("4. Add Route");
        System.out.println("5. Add Station");
        System.out.println("6. Add Vehicle");
        System.out.println("7. Show the links of a station.");
        System.out.println("8. Change the links of an existing station.");
        System.out.println("9. Remove a route.");
        System.out.println("0. Exit");
    }
    
    public static void showRoutes(RouteService routeService){
        System.out.println("The available routes are:");
        routeService.showRoutes();
        returnValue(0);
    }
    
    public static void showStations(RouteService routeService){
        System.out.println("The available stations are:");
        routeService.showStations();
        returnValue(0);     
    }
    
    public static void showVehicles(RouteService routeService){
        System.out.println("The available vehicles are:");
        routeService.showVehicles();
        returnValue(0);
    }
    
    public static void addRoute(RouteService routeService){
        routeService.initializeList();
        System.out.println("Choose the stations you want for the route:");
        System.out.println("Press 0 when done.");
        routeService.createRouteStations();
    }
    
    public static void addStation(RouteService routeService){
        System.out.print("Name the station you want to add: ");
        Scanner scan = new Scanner(System.in);
        String s = scan.nextLine();
        routeService.addStation(s);
        returnValue(0);
    }
    
    public static void addVehicle(RouteService routeService){
        System.out.println("Select the route you desire for the vehicle");
        routeService.showRoutes();
        routeService.addVehicle();
        returnValue(0);
    }
    
    public static void showLinks(RouteService routeService){
        System.out.println("Select the station whose links you want to see:");
        routeService.showStations();
        int chosen_index = scan.nextInt();
        routeService.showLinks(routeService.stations.get(chosen_index-1));
        returnValue(0);
    }
    
    public static void changeLinks(RouteService routeService){
        System.out.println("Select the station whose links you want to change:");
        routeService.showStations();
        int chosen_index = scan.nextInt();
        routeService.changeLinks(chosen_index-1);
        returnValue(0);
    }
    
    public static void deleteStation(RouteService routeService){
        System.out.println("Choose which station you want to remove:");
        routeService.showStations();
        int chosen_index = scan.nextInt();
        routeService.deleteStation(chosen_index-1);
        returnValue(0);
    }
    
    public static void clearScreen() {  
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }
    
    public static void returnValue(int g){
        while(g==0){
            System.out.println("Press 0 to go back.");
            int i = scan.nextInt();
            switch(i){
                case 0:
                    g=1;
                    clearScreen();
                break;
                default:
                    System.out.println("Not 0.");
            }
        }
    }
    
    
}



/*
PROIECT CONSTANDACHE ANDI - 244.
RETEA DE TRANSPORT.

OBIECTE EXISTENTE:
1. Station - the main unit of transport.
2. Route - a collection of routes.
3. Subway - an external link.
4. TrainStation - another external link.
5. Links - a collection of links that a station might have.
6. Vehicle - an object that travels on a given route.
7. Lightrail - an object that extends a standard vehicle, having extra discomfort.
8. ExpressLine - an object that extends a standard vehicle. It has an extra tax. 

ACTIUNI POSIBILE:
THE 8 DESCRIBED IN THE OPTION MENU + 1 FOR SUBWAY LINK ADD AND 1 FOR TRAIN STATION LINK ADD.

FOLOSESC LIST SI SET CA SI COLECTII.

in fisiere:
    Stations - de forma 
                Nume s
    Routes   - de forma
                Len n,station1,station2,..,stationn
    Links    - de forma
                Station_they_belong_to_index,nsub,sub1,sub2,..subn,ntrain,train1,train2,..trainn
    Vehicles - de forma
             -  vehicle_name, assesed_route

*/