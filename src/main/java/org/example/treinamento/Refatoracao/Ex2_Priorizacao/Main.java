package org.example.treinamento.Refatoracao.Ex2_Priorizacao;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String[]> rows = new ArrayList<>();
        rows.add(new String[]{"ACC-1", "BASIC", "120", "ACTIVE", "LATAM"});
        rows.add(new String[]{"ACC-2", "PRO", "980", "GRACE", "US"});
        rows.add(new String[]{"ACC-3", "ENTERPRISE", "1500", "ACTIVE", "LATAM"});
        rows.add(new String[]{"ACC-4", "PRO", "not-number", "RETRY", "US"});
        rows.add(new String[]{"ACC-5", "BASIC", "200", "BLOCKED", "LATAM"});

        BillingClosureService service = new BillingClosureService();
        service.closeMonth(rows, true, true, true);
    }
}
