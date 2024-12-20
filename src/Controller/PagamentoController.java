package controller;


import java.util.HashMap;
import java.util.Map;
import pagamentos.Pagamento;
import pagamentos.PagamentoCartao;
import pagamentos.PagamentoDinheiro;

public class PagamentoController {
    private Map<String, Pagamento> pagamentos; // Mapeia os métodos de pagamento pelo nome

    public PagamentoController() {
        this.pagamentos = new HashMap<>();
        inicializarMetodosPagamento();
    }

    // Inicializar métodos de pagamento disponíveis
    private void inicializarMetodosPagamento() {
        adicionarMetodoPagamento("Cartão", new PagamentoCartao(20000 , "123456", "João Pedro", "12/4", "123"));
        adicionarMetodoPagamento("Dinheiro", new PagamentoDinheiro(3333, 1));
    }

    public void adicionarMetodoPagamento(String nome, Pagamento metodo) {
        pagamentos.put(nome, metodo);
        System.out.println("Método de pagamento adicionado: " + nome);
    }

    public boolean realizarPagamento(String metodo, double valor) {
        Pagamento pagamento = pagamentos.get(metodo);
    
        if (pagamento == null) {
            System.out.println("Método de pagamento inválido: " + metodo);
            return false; // Pagamento falhou porque o método é inválido
        }
    
        boolean sucesso = pagamento.processarPagamento(valor);
    
        if (sucesso) {
            System.out.println("Pagamento realizado com sucesso usando " + metodo + ". Valor: R$" + valor);
            return true; // Pagamento bem-sucedido
        } else {
            System.out.println("Falha no pagamento usando " + metodo + ". Tente novamente.");
            return false; // Pagamento falhou
        }
    }
    

    public void listarMetodosPagamento() {
        System.out.println("\nMétodos de pagamento disponíveis:");
        for (String metodo : pagamentos.keySet()) {
            System.out.println("- " + metodo);
        }
    }
}
