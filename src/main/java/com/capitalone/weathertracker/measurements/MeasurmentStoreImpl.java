package com.capitalone.weathertracker.measurements;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class MeasurmentStoreImpl implements MeasurementStore{
	private static List<Measurement> DB = new ArrayList<>(); 
	
	 public void add(Measurement measurement) {
		 // store the Measurement object in a List (in memory DB)
		 System.out.println("A measurement has been stored");
		 DB.add(measurement);
	 };

	  public Measurement fetch(ZonedDateTime timestamp) {
		  System.out.println("Fetching a measurement with the following timestamp: " + timestamp);
		    for (Measurement measurement : DB) {
		    	// Checking if the hour, minute, and day of the year is the same
		        if (timestamp.withFixedOffsetZone().equals(measurement.getTimestamp().withFixedOffsetZone())) {
		            return measurement;
		        }
		    }
		  return null;
		  
	  }
	  /*
	   * This function queries the Measurements in our database and return whatever lies between the from and strictly less than the to
	   * dates passed to the paramater
	   */
	  public List<Measurement> queryDateRange(ZonedDateTime from, ZonedDateTime to) {
		  List<Measurement> result = new ArrayList<>();
		  
		  for (Measurement measurement : DB) {
			  if(measurement.getTimestamp().withFixedOffsetZone().compareTo(from.withFixedOffsetZone()) >= 0 &&
			     measurement.getTimestamp().withFixedOffsetZone().compareTo(to.withFixedOffsetZone()) < 0) {
				  result.add(measurement);
			  }
			  }
		  
		  return result;
		    
	  }

}
