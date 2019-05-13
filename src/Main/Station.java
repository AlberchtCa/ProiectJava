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
public class Station {
    private String name;
    private Links links;
    
    public Station(String name){
        this.name=name;
        this.links= new Links();
    }
    
    public String getName(){
        return name;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public void setLinks(Links link){
        this.links.setLinksSub(link.getLinksSub());
        this.links.setLinksTrain(link.getLinksTrain());
    }
    
    public Links getLinks(){
        return this.links;
    }
    
}
