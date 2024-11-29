package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import model.dao.Database;
import model.dao.Identifiable;

public class Usuario extends Identifiable implements Database.RowMapper<Usuario> {

    private int tipo = -1;
    private String login;
    private String senha;
    private Pessoa pessoa;
    private boolean logado = false;
    private boolean admin = false;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataModificacao;

    public Usuario() {
        this.dataCriacao = LocalDateTime.now();
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public boolean isLogado() {
        return logado;
    }

    public void setLogado(boolean logado) {
        this.logado = logado;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime date) {
        this.dataModificacao = date;
    }

    public LocalDateTime getDataModificacao() {
        return dataModificacao;
    }

    public void setDataModificacao() {
        this.dataModificacao = LocalDateTime.now();
    }

    public void setDataModificacao(LocalDateTime date) {
        this.dataModificacao = date;
    }

    @Override
    public String toString() {
        return "USUARIO => | id: " + super.getID() + " | tipo: " + tipo + " | login: " + login + " | senha: " + senha + " | idPessoa: " + pessoa.getID() + " | dc: " + dataCriacao + " | dm: " + dataModificacao + " |";
    }

    @Override
    public Usuario mapRow(ResultSet rs) throws SQLException {
        Usuario usuario = new Usuario();
        usuario.setID(rs.getInt("id"));
        usuario.setLogin(rs.getString("login"));
        usuario.setSenha(rs.getString("senha"));
        usuario.setTipo(rs.getInt("tipo"));
        usuario.setLogado(rs.getInt("logado") == 1? true : false);
        usuario.setAdmin(rs.getInt("admin") == 1? true : false);
        usuario.setDataCriacao(rs.getTimestamp("datacriacao").toLocalDateTime());
        usuario.setDataModificacao(rs.getTimestamp("datamodificacao").toLocalDateTime());

        // Obtém o ID da pessoa associado ao usuário
        int pessoaId = rs.getInt("pessoa_id");
        if (pessoaId > 0) {
            // Busca a Pessoa no banco usando o ID
            Database<Pessoa> pessoaDatabase = new Database<>("pessoa"); // Instância de Database para pessoa
            Pessoa pessoa = pessoaDatabase.getById(pessoaId, new Pessoa());

            // Verifica se a Pessoa foi encontrada e associa ao Usuario
            if (pessoa != null) {
                usuario.setPessoa(pessoa);
            }
        }

        return usuario;
    }

}
