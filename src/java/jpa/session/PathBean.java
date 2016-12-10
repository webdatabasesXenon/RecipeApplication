/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.session;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Named;

/**
 *
 * @author Greg
 */
@Named(value = "PathBean")
@Startup
@Singleton
public class PathBean {
    
    private String picPath;

    @PostConstruct
    void init() {
        picPath = "resources/Pictures/";
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }
    
    
    
}
