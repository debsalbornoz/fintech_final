package br.com.fintech;

package br.com.fintech;

public class Relatorio {
    private String mes;
    private double totalReceitas;
    private double totalDespesas;

    public Relatorio(String mes, double totalReceitas, double totalDespesas) {
        this.mes = mes;
        this.totalReceitas = totalReceitas;
        this.totalDespesas = totalDespesas;
    }

    public String getMes() {
        return mes;
    }

    public double getTotalReceitas() {
        return totalReceitas;
    }

    public double getTotalDespesas() {
        return totalDespesas;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public void setTotalReceitas(double totalReceitas) {
        this.totalReceitas = totalReceitas;
    }

    public void setTotalDespesas(double totalDespesas) {
        this.totalDespesas = totalDespesas;
    }

    public void adicionarReceita(double valor) {
        totalReceitas += valor;
    }

    public void adicionarDespesa(double valor) {
        totalDespesas += valor;
    }

    public double getSaldoFinal() {
        return totalReceitas - totalDespesas;
    }

    public boolean estaPositivo() {
        return getSaldoFinal() >= 0;
    }

    public void resetarRelatorio() {
        totalReceitas = 0;
        totalDespesas = 0;
    }

    public void gerarRelatorio() {
        System.out.println("\n=== Relatório Financeiro - " + mes + " ===");
        System.out.printf("Total de Receitas: R$%.2f%n", totalReceitas);
        System.out.printf("Total de Despesas: R$%.2f%n", totalDespesas);
        System.out.printf("Saldo Final: R$%.2f%n", getSaldoFinal());
    }

    @Override
    public String toString() {
        return "Relatorio{" +
                "mes='" + mes + '\'' +
                ", totalReceitas=" + totalReceitas +
                ", totalDespesas=" + totalDespesas +
                ", saldoFinal=" + getSaldoFinal() +
                '}';
    }
}
