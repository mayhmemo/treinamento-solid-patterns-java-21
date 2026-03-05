package org.example.treinamento.Refatoracao.Ex1_Code_Smells_Basicos;

import org.example.treinamento.Refatoracao.Ex1_Code_Smells_Basicos.resolucao.model.Order;
import org.example.treinamento.Refatoracao.Ex1_Code_Smells_Basicos.resolucao.model.OrderPriority;
import org.example.treinamento.Refatoracao.Ex1_Code_Smells_Basicos.resolucao.model.OrderStatus;
import org.example.treinamento.Refatoracao.Ex1_Code_Smells_Basicos.resolucao.model.PaymentType;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Order> orders = new ArrayList<>();
        orders.add(new Order("ORD-100", "Ana", 1200.00, OrderStatus.NEW, PaymentType.CARD, OrderPriority.VIP));
        orders.add(new Order("ORD-101", "Bruno", 450.00, OrderStatus.PENDING, PaymentType.PIX, OrderPriority.NORMAL));
        orders.add(new Order("ORD-102", "Carla", 100.00, OrderStatus.NEW, PaymentType.BOLETO, OrderPriority.NORMAL));
        orders.add(new Order("ORD-103", "Diego", 700.00, OrderStatus.CANCELLED, PaymentType.CARD, OrderPriority.VIP));
        orders.add(new Order("ORD-104", "Eva", 300.00, OrderStatus.RETRY, PaymentType.CASH, OrderPriority.NORMAL));

        OrderService service = new OrderService();
        service.processOrders(orders);
    }
}
