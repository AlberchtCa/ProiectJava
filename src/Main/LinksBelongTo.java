/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.util.HashSet;

/**
 *
 * @author Andi
 */
public class LinksBelongTo extends Links{
    private int station_index;
    
    public LinksBelongTo(){
        this.linksSub = new HashSet<>();
        this.linksTrain = new HashSet<>();
        station_index = -1;
    }
    
    public LinksBelongTo(LinksBelongTo link){
        this.setLinksSub(link.getLinksSub());
        this.setLinksTrain(link.getLinksTrain());
        this.station_index = link.station_index;
    }
    
    public int getStationIndex(){
        return station_index;
    }
    
    public void setStation_index(int index){
        station_index = index;
    }
}
