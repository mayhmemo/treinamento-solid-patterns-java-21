package org.example.treinamento.Refatoracao.Ex1_Code_Smells_Basicos;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String[]> orders = new ArrayList<>();
        orders.add(new String[]{"ORD-100", "Ana", "1200", "NEW", "CARD", "VIP"});
        orders.add(new String[]{"ORD-101", "Bruno", "450", "PENDING", "PIX", "NORMAL"});
        orders.add(new String[]{"ORD-102", "Carla", "abc", "NEW", "BOLETO", "NORMAL"});
        orders.add(new String[]{"ORD-103", "Diego", "700", "CANCELLED", "CARD", "VIP"});
        orders.add(new String[]{"ORD-104", "Eva", "300", "RETRY", "CASH", "NORMAL"});

        OrderService service = new OrderService();
        service.processOrders(orders);
    }
}
