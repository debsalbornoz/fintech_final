package br.com.fintech;

import java.sql.*;

public class TransacaoDAO {
    private Connection connection;

    public TransacaoDAO(Connection connection) {
        this.connection = connection;
    }

    public void salvar(Transacao transacao) throws SQLException {
        String sql = "INSERT INTO TRANSACOES (ID, TIPO, VALOR, DATA_TRANSACAO) VALUES (TRANSACAO_SEQ.NEXTVAL, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, transacao.getTipo());
            stmt.setDouble(2, transacao.getValor());
            stmt.setString(3, transacao.getData());
            stmt.executeUpdate();
        }
    }

    public String buscarTipo(int id) throws SQLException {
        String sql = "SELECT TIPO FROM TRANSACOES WHERE ID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("TIPO");
            } else {
                return null;
            }
        }
    }

    public double buscarValor(int id) throws SQLException {
        String sql = "SELECT VALOR FROM TRANSACOES WHERE ID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble("VALOR");
            } else {
                return -1; // ou lançar exceção
            }
        }
    }

    // Buscar data pelo ID
    public String buscarData(int id) throws SQLException {
        String sql = "SELECT DATA_TRANSACAO FROM TRANSACOES WHERE ID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("DATA_TRANSACAO");
            } else {
                return null;
            }
        }
    }

    public Transacao buscarTransacaoPorId(int id) throws SQLException {
        String sql = "SELECT * FROM TRANSACOES WHERE ID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Transacao(
                        rs.getString("TIPO"),
                        rs.getDouble("VALOR"),
                        rs.getString("DATA_TRANSACAO")
                );
            } else {
                return null;
            }
        }
    }

    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM TRANSACOES WHERE ID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
