# Exercício 1 - Code Smells Básicos

## Contexto
Você recebeu um serviço legado que processa pedidos em lote para uma loja pequena. O código funciona, mas cada mudança simples cria medo de quebrar regra de negócio.

## Objetivo do exercício
Identificar smells fundamentais e praticar refatorações pequenas, mantendo comportamento.

## Code Smells presentes
- Long Method (`processOrders` concentra validação, cálculo, persistência e notificação).
- Primitive Obsession (uso de `String[]` para representar pedido, status e tipo de pagamento).
- Duplicação (impressão de resumo e mensagens de notificação repetidas).
- Responsabilidade misturada (regras de desconto, I/O e relatório na mesma classe).
- Números mágicos (`0.05`, `0.1`, `0.2`, taxas fixas).

## Tarefas sugeridas (ordem incremental de segurança)
1. Cobrir o comportamento atual observando a saída do `Main` e registrando entradas/saídas esperadas.
2. Extrair métodos privados pequenos para validação e cálculo (sem mudar assinatura pública).
3. Introduzir um tipo simples para pedido (ex.: `record Order(...)`) e mapear do `String[]`.
4. Eliminar duplicações de impressão/notificação com métodos reutilizáveis.
5. Separar responsabilidades em classes focadas (ex.: cálculo vs. relatório), mantendo testes manuais no `Main`.

## Definition of Done
- O `Main` continua executando sem erro.
- Resultado financeiro (totais e mensagens-chave) permanece equivalente.
- `processOrders` ficou menor e com menos ramificações diretas.
- Não há mais acesso direto a índices mágicos do array em múltiplos pontos.

## Dicas
- Primeiro proteja comportamento, depois mude estrutura.
- Se uma extração altera lógica e estrutura ao mesmo tempo, está grande demais.
- Smell principal aqui: **Long Method**. Os demais são sintomas acoplados.
- Pergunta guia: "qual mudança mínima reduz risco sem alterar regra?"
