package br.com.fintech;

package br.com.fintech;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RelatorioDAO {
    private Connection connection;

    public RelatorioDAO(Connection connection) {
        this.connection = connection;
    }

    public void salvar(Relatorio relatorio) throws SQLException {
        String sql = "INSERT INTO RELATORIOS (MES, TOTAL_RECEITAS, TOTAL_DESPESAS) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, relatorio.getMes());
            stmt.setDouble(2, relatorio.getTotalReceitas());
            stmt.setDouble(3, relatorio.getTotalDespesas());
            stmt.executeUpdate();
        }
    }

    public Relatorio buscarPorMes(String mes) throws SQLException {
        String sql = "SELECT * FROM RELATORIOS WHERE MES = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, mes);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Relatorio(
                        rs.getString("MES"),
                        rs.getDouble("TOTAL_RECEITAS"),
                        rs.getDouble("TOTAL_DESPESAS")
                );
            } else {
                return null;
            }
        }
    }

    public void atualizarReceita(String mes, double novaReceita) throws SQLException {
        String sql = "UPDATE RELATORIOS SET TOTAL_RECEITAS = ? WHERE MES = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDouble(1, novaReceita);
            stmt.setString(2, mes);
            stmt.executeUpdate();
        }
    }

    public void atualizarDespesa(String mes, double novaDespesa) throws SQLException {
        String sql = "UPDATE RELATORIOS SET TOTAL_DESPESAS = ? WHERE MES = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDouble(1, novaDespesa);
            stmt.setString(2, mes);
            stmt.executeUpdate();
        }
    }

    public void deletarPorMes(String mes) throws SQLException {
        String sql = "DELETE FROM RELATORIOS WHERE MES = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, mes);
            stmt.executeUpdate();
        }
    }

    public List<Relatorio> listarTodos() throws SQLException {
        List<Relatorio> lista = new ArrayList<>();
        String sql = "SELECT * FROM RELATORIOS ORDER BY MES";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Relatorio r = new Relatorio(
                        rs.getString("MES"),
                        rs.getDouble("TOTAL_RECEITAS"),
                        rs.getDouble("TOTAL_DESPESAS")
                );
                lista.add(r);
            }
        }
        return lista;
    }
}
