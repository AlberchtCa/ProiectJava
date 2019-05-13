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
public class Lightrail extends Vehicle{
    private int discomfort;
    
    public Lightrail(int id, Route active_route, int discomf) {
        super(id, active_route);
        discomfort = discomf;
    }
    
    public void setDiscomfort(int discomf){
        discomfort = discomf;
    }
    
    public int getDiscomfort(){
        return discomfort;
    }
    
}
