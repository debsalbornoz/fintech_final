package br.com.fintech;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    private final Connection connection;

    public UsuarioDAO(Connection connection) {
        this.connection = connection;
    }

    public void inserirUsuario(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO USUARIOS (ID, NOME, EMAIL, SENHA) VALUES (USUARIO_SEQ.NEXTVAL, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenha());
            stmt.executeUpdate();
        }
    }
    // Retorna o email do usuário pelo id
    public String buscarEmail(int id) throws SQLException {
        String sql = "SELECT EMAIL FROM USUARIOS WHERE ID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("EMAIL");
            } else {
                return null; // ou lançar exceção se preferir
            }
        }
    }

    public int buscarId(String email) throws SQLException {
        String sql = "SELECT ID FROM USUARIOS WHERE EMAIL = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("ID");
            } else {
                return -1; // valor inválido para indicar que não encontrou
            }
        }
    }

    public String buscarNome(int id) throws SQLException {
        String sql = "SELECT NOME FROM USUARIOS WHERE ID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("NOME");
            } else {
                return null;
            }
        }
    }

    public int buscarIdPorNome(String nome) throws SQLException {
        String sql = "SELECT ID FROM USUARIOS WHERE NOME = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("ID");
            } else {
                return -1;
            }
        }
    }

    public List<Usuario> listarTodos() throws SQLException {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM USUARIOS";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Usuario u = new Usuario(
                        rs.getString("NOME"),
                        rs.getString("EMAIL"),
                        rs.getString("SENHA")
                );
                lista.add(u);
            }
        }
        return lista;
    }

    public void atualizarNome(int id, String novoNome) throws SQLException {
        String sql = "UPDATE USUARIOS SET NOME = ? WHERE ID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, novoNome);
            stmt.setInt(2, id);
            stmt.executeUpdate();
        }
    }

    public void atualizarEmail(int id, String novoEmail) throws SQLException {
        String sql = "UPDATE USUARIOS SET EMAIL = ? WHERE ID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, novoEmail);
            stmt.setInt(2, id);
            stmt.executeUpdate();
        }
    }

    public void atualizarSenha(int id, String novaSenha) throws SQLException {
        String sql = "UPDATE USUARIOS SET SENHA = ? WHERE ID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, novaSenha);
            stmt.setInt(2, id);
            stmt.executeUpdate();
        }
    }

    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM USUARIOS WHERE ID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
