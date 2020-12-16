import java.sql.*;
import java.awt.*;
import javax.swing.*;
import java.util.LinkedList;
import java.util.Set;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;



public class Q3 {
    HashMap<String, ArrayList<location>> franchiseLocations;

    public class location {
        public String businessID;
        public String franchiseName;
        public double rating;

        //longitude and latitude
        public double longitude;
        public double latitude;

        public location(String ID, String name, double rating, double longitude, double latitude) {
            businessID = ID;
            franchiseName = name;
            this.rating = rating;
            this.longitude = longitude;
            this.latitude = latitude;
        }
        
        public String getFranchiseName() {
        	return franchiseName;
        }
        public double getRating() {
        	return rating;
        }
    }

    public Q3(String state) {
        ArrayList<location> businesses = new ArrayList<location>();
        franchiseLocations = new HashMap<String, ArrayList<location>>();
        //Set the franchises
        String sqlStatement = "SELECT business.business_id, business.name, business.stars, business.longitude, business.latitude "
        		+ "FROM business WHERE business.state = \'" + state + "\'";

        ResultSet results = jdbcpostgreSQLGUI.queryDatabaseTable(sqlStatement);
        try {
            while(results.next()) {
            	businesses.add(new location(results.getString(1), results.getString(2), 
            			results.getDouble(3), results.getDouble(4), results.getDouble(5)));
            }
        } catch(Exception e) {} 
        
        //set its locations in the same order
        for(int i = 0; i < businesses.size(); i++) {

        	String key = businesses.get(i).getFranchiseName();
            if (!franchiseLocations.containsKey(key)) { // this is a new franchise
            	ArrayList<location> value = new ArrayList<location>();
            	value.add(businesses.get(i));
            	franchiseLocations.put(key, value);
            }
            else { // the franchise already exists, append the location to the location ArrayList
            	ArrayList<location> newvalue = franchiseLocations.get(key);
            	newvalue.add(businesses.get(i));
            	franchiseLocations.replace(key, newvalue);
            }
            
        }
    }

    public Set<String> getFranchises() {
        return franchiseLocations.keySet();
    }


    public double avgFranchiseRating(String name) {
    	ArrayList<location> locations = franchiseLocations.get(name);
    	double sum = 0;
    	for (int f = 0; f < locations.size(); f++) {
    		sum += locations.get(f).getRating();
    	}
    	double avg = sum / locations.size();
    	if (sum != 0)
    		return avg;
    	else return -1; //return -1 if you could not find a match
    }

    
    public void filterByRating(double lower, double upper) {
    	HashMap<String, ArrayList<location>> newFranLoc = new HashMap<String, ArrayList<location>>();
    	franchiseLocations.forEach((fname, locations) -> {
            double avg = avgFranchiseRating(fname);
            //System.out.println(franchises.get(i) + ": " + avg);
            if(!(avg < lower || avg > upper)) {
                //System.out.println(franchises.get(i) + ": " + avg);
                newFranLoc.put(fname, locations);
            }
    	});
    	franchiseLocations = newFranLoc;
    }
    
    
    public double standDist(String name) {
        double sumLong = 0;
        double sumLat = 0;
            
        double avgLong;
        double avgLat;

        //Variance
        double varLong = 0;
        double varLat = 0;

        int numLoc = franchiseLocations.get(name).size();
        for(int i = 0; i < numLoc; i++) {
            sumLong += franchiseLocations.get(name).get(i).longitude;
            sumLat += franchiseLocations.get(name).get(i).latitude;
        }
        avgLong = sumLong / numLoc;
        avgLat = sumLat / numLoc;
        for(int i = 0; i < numLoc; i++) {
            varLong += Math.pow((franchiseLocations.get(name).get(i).longitude - avgLong), 2);
            varLat += Math.pow((franchiseLocations.get(name).get(i).latitude - avgLat), 2);
        }
        varLong /= numLoc;
        varLat /= numLoc;

        double standDist = Math.sqrt(varLong + varLat);
        return standDist;
    } 

    //franchises with lower spread of locations get cut
    public void filterLocSpread(int targetNum) { 
        //used just to sort things neatly
        class Tuple implements Comparable<Tuple> {
            public final double standDist;
            public final String franchiseName;

            public Tuple(double standDist, String name) {
                this.standDist = standDist;
                this.franchiseName = name;
            }
            public int compareTo(Tuple o) {
                return Double.compare(this.standDist, o.standDist);
            }
        }

        int numFranchises = franchiseLocations.size();
        ArrayList<Tuple> franchiseStandDist = new ArrayList<Tuple>(numFranchises);
        HashMap<String, ArrayList<location>> newFranLoc = new HashMap<String, ArrayList<location>>();
        
	    franchiseLocations.forEach((fname, locations) -> {
	    	franchiseStandDist.add(new Tuple(this.standDist(fname), fname));
	    });
	    
        Collections.sort(franchiseStandDist);
        for(int i = numFranchises - targetNum; i < numFranchises; i++) {
        	String name = franchiseStandDist.get(i).franchiseName;
            newFranLoc.put(name, franchiseLocations.get(name));
        }
        franchiseLocations = newFranLoc;

    }
}