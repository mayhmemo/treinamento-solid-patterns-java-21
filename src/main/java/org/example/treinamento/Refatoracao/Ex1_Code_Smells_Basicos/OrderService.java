package org.example.treinamento.Refatoracao.Ex1_Code_Smells_Basicos;

import java.util.List;

public class OrderService {

    public void processOrders(List<String[]> rawOrders) {
        double grandTotal = 0.0;
        int processed = 0;
        int failed = 0;

        System.out.println("=== INICIO PROCESSAMENTO DE PEDIDOS ===");

        for (String[] row : rawOrders) {
            if (row == null || row.length < 6) {
                System.out.println("Pedido inválido: linha incompleta");
                failed++;
                continue;
            }

            String orderId = row[0];
            String customer = row[1];
            String amountText = row[2];
            String status = row[3];
            String paymentType = row[4];
            String priority = row[5];

            if (orderId == null || orderId.isBlank()) {
                System.out.println("Pedido sem ID foi descartado");
                failed++;
                continue;
            }

            if (!"NEW".equals(status) && !"PENDING".equals(status) && !"RETRY".equals(status)) {
                System.out.println("Pedido " + orderId + " ignorado por status: " + status);
                continue;
            }

            double amount;
            try {
                amount = Double.parseDouble(amountText);
            } catch (NumberFormatException ex) {
                System.out.println("Pedido " + orderId + " com valor inválido: " + amountText);
                failed++;
                continue;
            }

            if (amount <= 0) {
                System.out.println("Pedido " + orderId + " com valor não positivo");
                failed++;
                continue;
            }

            double discount = 0.0;
            if (amount > 1000) {
                discount = amount * 0.1;
            } else if (amount > 500) {
                discount = amount * 0.05;
            }

            if ("VIP".equalsIgnoreCase(priority)) {
                discount += amount * 0.02;
            }

            double fee = 0.0;
            if ("CARD".equalsIgnoreCase(paymentType)) {
                fee = amount * 0.03;
            } else if ("PIX".equalsIgnoreCase(paymentType)) {
                fee = amount * 0.01;
            } else if ("BOLETO".equalsIgnoreCase(paymentType)) {
                fee = 2.0;
            } else {
                fee = 5.0;
            }

            double finalAmount = amount - discount + fee;
            grandTotal += finalAmount;
            processed++;

            String persistedLine = orderId + ";" + customer + ";" + finalAmount + ";" + paymentType;
            System.out.println("Persistindo: " + persistedLine);

            if ("VIP".equalsIgnoreCase(priority)) {
                System.out.println("Notificação VIP -> cliente=" + customer + ", pedido=" + orderId + ", total=" + finalAmount);
                System.out.println("Resumo: Pedido " + orderId + " aprovado para cliente VIP " + customer + " com total " + finalAmount);
            } else {
                System.out.println("Notificação padrão -> cliente=" + customer + ", pedido=" + orderId + ", total=" + finalAmount);
                System.out.println("Resumo: Pedido " + orderId + " aprovado para cliente " + customer + " com total " + finalAmount);
            }
        }

        System.out.println("=== FIM PROCESSAMENTO DE PEDIDOS ===");
        System.out.println("Processados=" + processed + ", Falhas=" + failed + ", Total final=" + grandTotal);
    }
}
