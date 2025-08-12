package org.example.event;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocationEvent {
    private Integer deliveryPersonId;
    private Integer orderId;
    private double latitude;
    private double longitude;
    private String timestamp;
}
