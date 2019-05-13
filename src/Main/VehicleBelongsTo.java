/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

/**
 *
 * @author Andi
 */
public class VehicleBelongsTo extends Vehicle {
    
    int route_index;
    
    public VehicleBelongsTo(int id, Route active_route, int route_index) {
        super(id, active_route);
        this.route_index = route_index;
    }
    
    public void setRouteIndex(int index){
        route_index = index;
    }
    
    public int getRouteIndex(){
        return route_index;
    }
    
}
