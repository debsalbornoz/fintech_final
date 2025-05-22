package br.com.fintech;

public class Transacao {
    private String tipo;
    private double valor;
    private String data;

    public Transacao(String tipo, double valor, String data) {
        this.tipo = tipo.toLowerCase();
        this.valor = valor;
        this.data = data;
    }

    public String getTipo() {
        return tipo;
    }

    public double getValor() {
        return valor;
    }

    public String getData() {
        return data;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo.toLowerCase();
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void adicionarValor(double adicional) {
        if (adicional <= 0) {
            System.out.println("Valor para adicionar deve ser positivo.");
            return;
        }
        valor += adicional;
    }

    public void subtrairValor(double desconto) {
        if (desconto <= 0) {
            System.out.println("Valor para subtrair deve ser positivo.");
            return;
        }
        if (valor - desconto < 0) {
            System.out.println("Erro: valor final da transação não pode ser negativo.");
            return;
        }
        valor -= desconto;
    }

    // Valida o tipo
    public boolean tipoValido() {
        return ehReceita() || ehDespesa();
    }

    // Valida o valor
    public boolean valorPositivo() {
        return valor > 0;
    }

    public boolean ehReceita() {
        return tipo.equals("receita") || tipo.equals("deposito");
    }

    public boolean ehDespesa() {
        return tipo.equals("despesa") || tipo.equals("saque");
    }

    public void atualizarRelatorio(Relatorio relatorio) {
        if (!tipoValido() || !valorPositivo()) {
            System.out.println("Transação inválida. Nada foi adicionado ao relatório.");
            return;
        }

        if (ehReceita()) {
            relatorio.adicionarReceita(valor);
        } else if (ehDespesa()) {
            relatorio.adicionarDespesa(valor);
        }
    }

    public void registrarTransacao() {
        System.out.println("Registrando transação: " + tipo + " de R$" + valor + " na data " + data);
    }

    @Override
    public String toString() {
        return "Transacao{" +
                "tipo='" + tipo + '\'' +
                ", valor=R$" + valor +
                ", data='" + data + '\'' +
                '}';
    }
}
