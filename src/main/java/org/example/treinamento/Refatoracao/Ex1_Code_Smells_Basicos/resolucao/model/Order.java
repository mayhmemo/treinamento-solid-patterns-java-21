package org.example.treinamento.Refatoracao.Ex1_Code_Smells_Basicos.resolucao.model;

import lombok.Getter;

@Getter
public class Order {
    private final String id;
    private final String customer;
    private final Double amount;
    private final OrderStatus status;
    private final PaymentType paymentType;
    private final OrderPriority orderPriority;
    private Double discount = 0.0;
    private Double fee;

    public Order(
            String id,
            String customer,
            Double amount,
            OrderStatus status,
            PaymentType paymentType,
            OrderPriority orderPriority
    ) {
        this.id = id;
        this.customer = customer;
        this.amount = amount;
        this.status = status;
        this.paymentType = paymentType;
        this.orderPriority = orderPriority;

        this.calculateDiscount();
        this.calculateFee();
    }

    public void calculateDiscount() {
        if (amount > 1000) {
            this.discount = amount * 0.1;
        } else if (amount > 500) {
            this.discount = amount * 0.05;
        }

        if (orderPriority.equals(OrderPriority.VIP)) {
            this.discount += amount * 0.02;
        }
    }

    public void calculateFee() {
        switch (paymentType) {
            case CARD:
                this.fee = amount * 0.03;
                break;
            case PIX:
                this.fee = amount * 0.01;
                break;
            case BOLETO:
                this.fee = 2.0;
                break;
            default:
                this.fee = 5.0;
                break;
        }
    }
}
