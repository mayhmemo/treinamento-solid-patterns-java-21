# Exercício 2 - Priorização (Impacto vs Risco)

## Contexto
Uma rotina de faturamento mensal ficou cheia de exceções e regras de produto. O time quer melhorar, mas sem “big rewrite” porque o fechamento do mês depende desse fluxo.

## Objetivo do exercício
Treinar **priorização de refatoração**: escolher primeiro mudanças de alto impacto e baixo risco, antes de mexer em partes perigosas.

## Code Smells presentes
- Long Method (`closeMonth` faz parse, regras, persistência, notificação e retry).
- Condicionais complexas por tipo de plano/status.
- Primitive Obsession (`String[]`, flags booleanas em cascata, status por texto livre).
- Duplicação de política de retry e logging.
- Dependências rígidas em saída de console simulando repositório/notificação.
- Acoplamento temporal (a ordem das ações é crítica e implícita).

## Tarefas sugeridas (ordem incremental de segurança)
1. Rodar o `Main` e anotar comportamento baseline (totais, falhas e mensagens principais).
2. Extrair etapas sem regra de negócio (ex.: logging padronizado, parse seguro) para reduzir ruído.
3. Nomear decisões críticas com métodos (`isProcessable`, `computeBasePrice`, etc.).
4. Introduzir um tipo para entrada (`record SubscriptionRow(...)`) mantendo compatibilidade.
5. Só depois avaliar separar cálculo de cobrança da camada de efeitos colaterais.

## Definition of Done
- Fechamento continua produzindo o mesmo resumo final para o dataset atual.
- Fluxo principal está legível com métodos nomeados por intenção.
- Mudanças perigosas (ordem de retry/persistência) ficaram explícitas e documentadas.
- Decisões de prioridade estão justificadas (impacto vs risco) no próprio README ou notas.

## Dicas
- Nem todo smell deve ser atacado no mesmo ciclo.
- Priorize remover ambiguidade antes de dividir classes.
- Smell principal aqui: **priorização ruim causada por acoplamento temporal**.
- Perguntas úteis: "se eu quebrar isso, qual dano de negócio?" e "qual extração é semanticamente neutra?"
