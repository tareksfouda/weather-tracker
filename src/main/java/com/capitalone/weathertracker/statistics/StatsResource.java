package com.capitalone.weathertracker.statistics;

import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;

import com.capitalone.weathertracker.measurements.Measurement;
import com.capitalone.weathertracker.measurements.MeasurementQueryService;
import com.capitalone.weathertracker.measurements.MeasurementStore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stats")
public class StatsResource {
//  private final MeasurementQueryService queryService;
//  private final MeasurementAggregator aggregator;

	@Autowired
	  private MeasurementStore service;
	
	@Autowired
	  private MeasurementAggregator aggregator;
//  public StatsResource(MeasurementQueryService queryService, MeasurementAggregator aggregator) {
//    this.queryService = queryService;
//    this.aggregator = aggregator;
//  }

  @GetMapping
  public List<AggregateResult> getStats(
    @RequestParam("metric") List<String> metrics,
    @RequestParam("stat") List<Statistic> stats,
    @RequestParam("fromDateTime") ZonedDateTime from,
    @RequestParam("toDateTime") ZonedDateTime to
    ) {
      List<Measurement> measurements = service.queryDateRange(from, to);
      // If there are no results within the time range, return an empty collection.
      if (measurements.isEmpty()) {
    	  return Collections.emptyList();
      }
      return aggregator.analyze(measurements, metrics, stats);
  }
  
  
//  @GetMapping
//  public List<Measurement> getStats(
//    @RequestParam("fromDateTime") ZonedDateTime from,
//    @RequestParam("toDateTime") ZonedDateTime to
//    ) {
//      List<Measurement> measurements = service.queryDateRange(from, to);
//      return measurements;
//  }
}
