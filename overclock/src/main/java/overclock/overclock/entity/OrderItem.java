package overclock.overclock.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "order_item")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item; // 주문 상품

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order; // 주문

    private int orderPrice; // 주문 가격
    private int count; // 주문 수량

    //상품 주문
    public static OrderItem createOrderItem(Item item, int count) {
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setOrderPrice(item.getPrice());
        orderItem.setCount(count);

        item.removeStock(count);
        return orderItem;
    }

    // 주문취소
    public void cancel() {
        getItem().addStock(count);
    }

    // 주문상품 전체 가격조회
    public int getTotalPrice() {
        return getOrderPrice() * getCount();
    }

}