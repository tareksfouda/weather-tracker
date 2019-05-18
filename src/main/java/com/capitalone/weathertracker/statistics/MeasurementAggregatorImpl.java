package com.capitalone.weathertracker.statistics;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.capitalone.weathertracker.measurements.Measurement;

@Service
public class MeasurementAggregatorImpl implements MeasurementAggregator{
	
	@Override
	public List<AggregateResult> analyze(List<Measurement> measurements, List<String> metrics, List<Statistic> stats) {
		List<AggregateResult> result = new ArrayList<>(); 
		
		  for (String metric : metrics) {
			  
			  for (Statistic stat : stats) {
				  
				  if (stat.toString() == "MIN") {
					  double minValue = this.getMinimumValue(metric, measurements);
					  if (minValue != 1000.0) {
						  System.out.println("The minimum value for all of the measurements is " + minValue + "for this metric: "+ metric);
						  AggregateResult aggResult = new AggregateResult(metric, stat, minValue);
						  result.add(aggResult);
					  }
				  }
				  if (stat.toString() == "MAX") {
					  double maxValue = this.getMaximumValue(metric, measurements);
					  if (maxValue != -1.0) {
						  AggregateResult aggResult = new AggregateResult(metric, stat, maxValue);
						  result.add(aggResult);
						  System.out.println("The minimum value for all of the measurements is " + maxValue + "for this metric: "+ metric);
					  }
				  }
				  
				  if (stat.toString() == "AVERAGE") {
					  double minValue = this.getMinimumValue(metric, measurements);
					  double maxValue = this.getMaximumValue(metric, measurements);
					  double average = (minValue + maxValue)/2;;
					  if (maxValue != -1.0 || minValue != 1000.0) {
						  System.out.println("The min and max " + minValue +  " "+  maxValue);
						  System.out.println("The average value for all of the measurements is " + average + "for this metric: "+ metric);
						  AggregateResult aggResult = new AggregateResult(metric, stat, average);
						  result.add(aggResult);  
					  }
				  }
			  }
			  
		  }
		return result;
	}

	private double getMinimumValue(String metric, List<Measurement> measurements) {
		double min = 1000.0;
		
		for (Measurement measurement : measurements) {
			if (measurement.getMetrics().containsKey(metric)) {
				if (measurement.getMetric(metric) < min) {
					min = measurement.getMetric(metric);
				}
			}
		}
		
		return min;
	}
	
	private double getMaximumValue(String metric, List<Measurement> measurements) {
		double max = -1.0;
		
		for (Measurement measurement : measurements) {
			if (measurement.getMetrics().containsKey(metric)) {
				if (measurement.getMetric(metric) > max) {
					max = measurement.getMetric(metric);
				}
			}
		}
		
		return max;
	}
}
