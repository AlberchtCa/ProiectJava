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
public class Vehicle {
    private int id;
    private Route active_route;
    
    public Vehicle(int id, Route active_route){
        this.id = id;
        this.active_route = active_route;
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    public int getId(){
        return id;
    }
    
    public void setRoute(Route r){
        this.active_route = r;
    }
    
    public Route getRoute(){
        return active_route;
    }
    
    
}
