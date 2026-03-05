package org.example.treinamento.Refatoracao.Ex1_Code_Smells_Basicos;

import org.example.treinamento.Refatoracao.Ex1_Code_Smells_Basicos.resolucao.model.Order;
import org.example.treinamento.Refatoracao.Ex1_Code_Smells_Basicos.resolucao.model.OrderPriority;
import org.example.treinamento.Refatoracao.Ex1_Code_Smells_Basicos.resolucao.model.OrderStatus;

import java.util.Arrays;
import java.util.List;

public class OrderService {
    private static final List<OrderStatus> PERMITED_STATUS = Arrays.asList(OrderStatus.NEW, OrderStatus.PENDING, OrderStatus.RETRY);

    public void processOrders(List<Order> rawOrders) {
        double grandTotal = 0.0;
        int processed = 0;
        int failed = 0;

        System.out.println("=== INICIO PROCESSAMENTO DE PEDIDOS ===");

        for (Order order : rawOrders) {
            if (order.getId() == null || order.getId().isBlank()) {
                System.out.println("Pedido sem ID foi descartado");
                failed++;
                continue;
            }

            if (!PERMITED_STATUS.contains(order.getStatus())) {
                System.out.println("Pedido " + order.getId() + " ignorado por status: " + order.getStatus());
                continue;
            }

            if (order.getAmount() <= 0) {
                System.out.println("Pedido " + order.getId() + " com valor não positivo");
                failed++;
                continue;
            }

            double finalAmount = order.getAmount() - order.getDiscount() + order.getFee();
            grandTotal += finalAmount;
            processed++;

            String persistedLine = order.getId() + ";" + order.getCustomer() + ";" + finalAmount + ";" + order.getPaymentType();
            System.out.println("Persistindo: " + persistedLine);

            if (order.getOrderPriority().equals(OrderPriority.VIP)) {
                System.out.println("Notificação VIP -> cliente=" + order.getCustomer() + ", pedido=" + order.getId() + ", total=" + finalAmount);
                System.out.println("Resumo: Pedido " + order.getId() + " aprovado para cliente VIP " + order.getCustomer() + " com total " + finalAmount);
            } else {
                System.out.println("Notificação padrão -> cliente=" + order.getCustomer() + ", pedido=" + order.getId() + ", total=" + finalAmount);
                System.out.println("Resumo: Pedido " + order.getId() + " aprovado para cliente " + order.getCustomer() + " com total " + finalAmount);
            }
        }

        System.out.println("=== FIM PROCESSAMENTO DE PEDIDOS ===");
        System.out.println("Processados=" + processed + ", Falhas=" + failed + ", Total final=" + grandTotal);
    }
}
