package dev.tpcoder.empfriendly.location.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationStream {

  private String trackingId;
  private Location location;

}
