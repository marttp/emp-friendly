package dev.tpcoder.empfriendly.location.service;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.geo.Point;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Utility {

  public static Point randomGeoPoint() {
    double latitude = (Math.random() * 180.0) - 90.0;
    double longitude = (Math.random() * 360.0) - 180.0;
    return new Point(longitude, latitude);
  }
}
