package com.capitalone.weathertracker.measurements;

import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class MeasurementQueryServiceImpl implements MeasurementQueryService {
	  public List<Measurement> queryDateRange(ZonedDateTime from, ZonedDateTime to) {
		  return null;
	  }
}
