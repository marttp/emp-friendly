package dev.tpcoder.empfriendly.shipment;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.tpcoder.empfriendly.shipment.model.OrderShipmentEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

@Service
public class ShipmentSubscriber implements MessageListener {

  private final ObjectMapper objectMapper = new ObjectMapper();
  private final Logger logger = LoggerFactory.getLogger(ShipmentSubscriber.class);

  /*
  * Example
  {
    "employeeId": "ddf5757a-ca21-41f4-b668-836d7755d70d",
    "orderId": "test",
    "address": {
        "houseNumber": "2222222",
        "city": "Port",
        "state": "New York",
        "postalCode": "13906-2123",
        "country": "United States"
    }
  }
  * */
  @Override
  public void onMessage(Message message, byte[] pattern) {
    // logger.debug(message.toString());
    String txtMessage = message.toString();
    OrderShipmentEvent orderShipmentEvent;
    try {
      orderShipmentEvent = objectMapper.readValue(txtMessage, OrderShipmentEvent.class);
      logger.debug("orderShipmentEvent: {}", orderShipmentEvent);
    } catch (JsonProcessingException e) {
      // throw new RuntimeException(e);
      logger.error("Can't deserialize to OrderShipmentEvent", e);
    }
  }
}
