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
public class ExpressLine extends Vehicle {
    private int extra_tax;
    
    public ExpressLine(int id, Route active_route,int extra_tax ) {
        super(id, active_route);
        this.extra_tax = extra_tax;
    }
    
    public void setTax(int tax){
        extra_tax = tax;
    }
    
    public int getTax(){
        return extra_tax;
    }
    
}
