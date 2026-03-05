package org.example.treinamento.Refatoracao.Ex2_Priorizacao;

import java.util.List;

public class BillingClosureService {

    public void closeMonth(List<String[]> rows, boolean sendEmail, boolean persistData, boolean retryFailed) {
        double total = 0.0;
        int success = 0;
        int failed = 0;

        System.out.println("=== FECHAMENTO MENSAL ===");

        for (String[] row : rows) {
            if (row == null || row.length < 5) {
                System.out.println("Linha inválida. Ignorando.");
                failed++;
                continue;
            }

            String accountId = row[0];
            String plan = row[1];
            String usageText = row[2];
            String status = row[3];
            String region = row[4];

            if (accountId == null || accountId.isBlank()) {
                System.out.println("Conta sem id, falha de fechamento");
                failed++;
                continue;
            }

            if (!"ACTIVE".equals(status) && !"GRACE".equals(status) && !"RETRY".equals(status)) {
                System.out.println("Conta " + accountId + " ignorada por status=" + status);
                continue;
            }

            int usage;
            try {
                usage = Integer.parseInt(usageText);
            } catch (NumberFormatException ex) {
                System.out.println("Uso inválido para conta " + accountId + ": " + usageText);
                failed++;
                if (retryFailed) {
                    System.out.println("Retry habilitado para conta " + accountId + " (parse)");
                }
                continue;
            }

            double basePrice;
            if ("BASIC".equals(plan)) {
                basePrice = 49.9;
            } else if ("PRO".equals(plan)) {
                basePrice = 99.9;
            } else if ("ENTERPRISE".equals(plan)) {
                basePrice = 199.9;
            } else {
                basePrice = 59.9;
            }

            double variable = usage * 0.15;
            double surcharge = 0.0;
            if (usage > 1000) {
                surcharge = usage * 0.03;
            }
            if ("LATAM".equals(region)) {
                surcharge += 8.0;
            }

            double amount = basePrice + variable + surcharge;

            if ("GRACE".equals(status)) {
                amount = amount * 0.5;
            }
            if ("RETRY".equals(status)) {
                amount = amount + 12.0;
            }

            total += amount;
            success++;

            if (persistData) {
                System.out.println("Persistindo fechamento: " + accountId + ";" + amount + ";" + plan + ";" + region);
            }

            if (sendEmail) {
                if ("ENTERPRISE".equals(plan)) {
                    System.out.println("Email premium: conta=" + accountId + " valor=" + amount);
                } else {
                    System.out.println("Email padrão: conta=" + accountId + " valor=" + amount);
                }
            }

            System.out.println("Fechado: conta=" + accountId + " plano=" + plan + " valor=" + amount);
        }

        System.out.println("Resumo fechamento: sucesso=" + success + ", falhas=" + failed + ", total=" + total);
    }
}
