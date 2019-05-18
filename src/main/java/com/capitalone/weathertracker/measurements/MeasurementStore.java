package com.capitalone.weathertracker.measurements;

import java.time.ZonedDateTime;
import java.util.List;

public interface MeasurementStore {
  void add(Measurement measurement);

  Measurement fetch(ZonedDateTime timestamp);
  
  List<Measurement> queryDateRange(ZonedDateTime from, ZonedDateTime to);
}
