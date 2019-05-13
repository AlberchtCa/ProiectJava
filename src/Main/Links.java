/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Andi
 */
public class Links {
    protected Set<Subway> linksSub;
    protected Set<TrainStation> linksTrain;
    
    public Links(){
        this.linksSub = new HashSet<>();;
        this.linksTrain = new HashSet<>();;
    }
    
    public Links(Links l){
        int i;
   //     this.linksSub = new HashSet<>();
   //     this.linksTrain = new HashSet<>();
   //     Subway[] sub = new Subway[l.getLinksSub().size()];
   //     TrainStation[] train = new TrainStation[l.getLinksTrain().size()];
        this.setLinksSub(l.getLinksSub());
        this.setLinksTrain(l.getLinksTrain());
    }
    
    public Set getLinksSub(){
        return linksSub;
    }
    
    public Set getLinksTrain(){
        return linksTrain;
    }
    
    public void setLinksSub(Set<Subway> sub){
        int i;
        this.linksSub = new HashSet<>();
        Subway[] s = new Subway[sub.size()];
        s = sub.toArray(new Subway[0]);
        for(i=0;i< sub.size(); i++){
            this.linksSub.add(s[i]);
        }
    }
    
    public void setLinksTrain(Set<TrainStation> train){
        int i;
        this.linksTrain = new HashSet<>();
        TrainStation[] s = new TrainStation[train.size()];
        s = train.toArray(new TrainStation[0]);
        for(i=0;i< train.size(); i++){
            this.linksTrain.add(s[i]);
        }
    }
    
    
}
