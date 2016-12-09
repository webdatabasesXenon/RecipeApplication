/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.session;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Named;

/**
 *
 * @author gsheehan
 */

@Named(value = "UnitsBean")
@Startup
@Singleton
public class UnitsBean {

    private Map<String, Object> units = new LinkedHashMap<String,Object>();
    
    @PostConstruct
    void init() {
        units.put("bag","bag");
        units.put("bags","bags");
        units.put("batch","batch");
        units.put("batches","batches");
        units.put("bottle","bottle");
        units.put("bottles","bottles");
        units.put("box","box");
        units.put("boxes","boxes");
        units.put("bunch","bunch");
        units.put("bunches","bunches");
        units.put("bundle","bundles");
        units.put("can","can");
        units.put("cans","cans");
        units.put("clove","clove");
        units.put("cloves","cloves");
        units.put("cube","cube");
        units.put("cubes","cubes");
        units.put("cup","cup");
        units.put("cups","cups");
        units.put("dash","dash");
        units.put("dashes","dashes");
        units.put("drop","drop");
        units.put("drops","drops");
        units.put("ear","ear");
        units.put("ears","ears");
        units.put("envelope","envelope");
        units.put("envelopes","envelopes");
        units.put("gram","gram");
        units.put("grams","grams");
        units.put("handful","handful");
        units.put("handfuls","handfuls");
        units.put("head","head");
        units.put("heads","heads");
        units.put("heart","heart");
        units.put("hearts","hearts");
        units.put("jar","jar");
        units.put("jars","jars");
        units.put("kilogram","kilogram");
        units.put("kilograms","kilograms");
        units.put("link","link");
        units.put("links","links");
        units.put("liter","liter");
        units.put("liters","liters");
        units.put("loaf","loaf");
        units.put("loaves","loaves");
        units.put("milliliter","milliliter");
        units.put("milliliters","milliliters");
        units.put("ounce","ounce");
        units.put("ounces","ounces");
        units.put("package","package");
        units.put("packages","packages");
        units.put("packet","packet");
        units.put("packets","packets");
        units.put("piece","piece");
        units.put("pieces","pieces");
        units.put("pinch","pinch");
        units.put("pinches","pinches");
        units.put("pint","pint");
        units.put("pints","pints");
        units.put("pound","pound");
        units.put("pounds","pounds");
        units.put("quart","quart");
        units.put("quarts","quarts");
        units.put("rib","rib");
        units.put("ribs","ribs");
        units.put("round","round");
        units.put("rounds","rounds");
        units.put("scoop","scoop");
        units.put("scoops","scoops");
        units.put("sheet","sheet");
        units.put("sheets","sheets");
        units.put("slice","slice");
        units.put("slices","slices");
        units.put("splash","splash");
        units.put("splashes","splashes");
        units.put("sprig","sprig");
        units.put("sprigs","sprigs");
        units.put("stalk","stalk");
        units.put("stalks","stalks");
        units.put("stick","stick");
        units.put("sticks","sticks");
        units.put("strip","strip");
        units.put("strips","strips");
        units.put("tablespoon","tablespoon");
        units.put("tablespoons","tablespoons");
        units.put("teaspoon","teaspoon");
        units.put("teaspoons","teaspoons");
        units.put("wedge","wedge");
        units.put("wedges","wedges");
    }

    public Map<String, Object> getUnits() {
        return units;
    }

    public void setUnits(Map<String, Object> units) {
        this.units = units;
    }
    
    
}
