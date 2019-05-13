/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Andi
 */
public class Route {
    private Station departure;
    private Station destination;
    private Station via;
    private List<Station> route = new ArrayList<Station>();
    private int route_len;
    
    public Route(List<Station> route,int route_len){
        this.route_len=route_len;
        int i;
        for(i=0;i<route_len;i++){
            this.route.add(route.get(i));
        }
        departure = this.route.get(0);
        destination = this.route.get(route_len-1);
        via = this.route.get(route_len/2);
    }
    
    
    public Station[] getStations(){
        return route.toArray(new Station[0]);
    }
    
    public Station getDeparture(){
        return departure;
    }
    
    public Station getDestination(){
        return destination;
    }
    
    public Station getVia(){
        return via;
    }
    
    public int getRoute_len(){
        return route_len;
    }

    
}
