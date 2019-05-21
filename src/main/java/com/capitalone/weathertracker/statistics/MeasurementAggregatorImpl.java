package com.capitalone.weathertracker.statistics;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.capitalone.weathertracker.measurements.Measurement;

@Service
public class MeasurementAggregatorImpl implements MeasurementAggregator{
	
	@Override
	public List<AggregateResult> analyze(List<Measurement> measurements, List<String> metrics, List<Statistic> stats) {
	    List<AggregateResult> result = new ArrayList<>();
		
		for (String metric : metrics) {
		    for (Statistic stat : stats) {
		        Map<String,Double> minAndMax = new HashMap<String, Double>();
		        minAndMax = this.getMinimumAndMaximumValues(metric, measurements);
				  if (stat.toString() == "MIN") {
					  if (minAndMax.get("min") != 1000.0) {
						  AggregateResult aggResult = new AggregateResult(metric, stat, minAndMax.get("min"));
						  result.add(aggResult);
					  }
				  }
				  
				  if (stat.toString() == "MAX") {
					  if (minAndMax.get("max") != -1.0) {
						  AggregateResult aggResult = new AggregateResult(metric, stat, minAndMax.get("max"));
						  result.add(aggResult);
					  }
				  }
				  
				  if (stat.toString() == "AVERAGE") {
					  double average = (minAndMax.get("min") + minAndMax.get("max"))/2;
					  if (minAndMax.get("max") != -1.0 || minAndMax.get("min") != 1000.0) {
						  AggregateResult aggResult = new AggregateResult(metric, stat, average);
						  result.add(aggResult);
					  }
				  }
			  }
			  
		  }
		return result;
	}
	
	/*
     * A helper function that gets the maximum value and the minimum values for a specific metric
     * in a list of measurements in pairs.
     * avoided implementation two differenct functions fin min and max to avout unnecessary loops.
     * returns -1.0 for max and 1000.0 for min if there is fault in recording the metric.
     *
     */
	private Map<String,Double> getMinimumAndMaximumValues(String metric, List<Measurement> measurements) {
	    // Initialize max variable to a very small negative signed value so we could override it in the following for loop with the actual maximum in the List.
		double max = -1.0;
	    // Initialize min variable to a very large number so we could override it in the following for loop with the actual minimum in the List.
		double min = 1000.0;
		
		for (Measurement measurement : measurements) {
			if (measurement.getMetrics().containsKey(metric)) {
				if (measurement.getMetric(metric) > max) {
					max = measurement.getMetric(metric);
				}
				if (measurement.getMetric(metric) < min) {
					min = measurement.getMetric(metric);
				}
			}
		}
		
		Map<String, Double> result = new HashMap<String, Double>();
		result.put("max", max);
        result.put("min", min);
        System.out.println(result.get("max"));
		return result;
	}
}
