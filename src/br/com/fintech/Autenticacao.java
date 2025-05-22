package br.com.fintech;

package br.com.fintech;

import java.sql.SQLException;

public class AutenticacaoService {

    private UsuarioDAO usuarioDAO;

    public AutenticacaoService(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    public boolean emailExiste(String email) throws SQLException {
        return usuarioDAO.buscarId(email) != -1;
    }

    public boolean autenticar(String email, String senha) throws SQLException {
        Usuario usuario = usuarioDAO.buscarPorEmailESenha(email, senha);
        return usuario != null;
    }

    public int autenticarEObterId(String email, String senha) throws SQLException {
        Usuario usuario = usuarioDAO.buscarPorEmailESenha(email, senha);
        return (usuario != null) ? usuarioDAO.buscarId(email) : -1;
    }

    public boolean emailValido(String email) {
        return email != null && email.matches("^[\\w\\.-]+@[\\w\\.-]+\\.\\w+$");
    }

    public boolean senhaValida(String senha) {
        return senha != null && senha.length() >= 6;
    }
}
