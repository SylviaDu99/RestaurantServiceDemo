package org.example.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryRequest {
    private Integer orderId;
    private String customerName;
    private String customerPhone;
    private String deliveryAddress;
}
