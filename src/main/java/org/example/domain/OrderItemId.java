package org.example.domain;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemId implements Serializable {
    private Integer foodId;
    private Integer orderId;

    @Override
    public String toString() {
        return orderId + "-" + foodId;
    }

    public static OrderItemId fromString(String str) {
        String[] parts = str.split("-");
        if (parts.length != 2) throw new IllegalArgumentException("Invalid ID format: " + str);
        return new OrderItemId(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
    }
}
