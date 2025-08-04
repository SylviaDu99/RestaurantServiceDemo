package org.example.domain;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity
@Table(name = "order_items")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {
    @EmbeddedId
    private OrderItemId orderItemId;

    @ManyToOne
    @MapsId("foodId")
    @JoinColumn(name = "menu_item_id")
    private MenuItem menuItem;

    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "order_id")
    private Bill bill;

    @Min(1)
    private int quantity;
}
