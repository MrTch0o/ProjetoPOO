package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import model.dao.Database;
import model.dao.Identifiable;
import model.dao.Utils;

public class Pessoa extends Identifiable implements Database.RowMapper<Pessoa> {

    Utils utils = new Utils();
    private String nome;
    private LocalDate nascimento;
    private String telefone;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataModificacao;

//    public Pessoa() {
//        this.dataCriacao = LocalDateTime.now();
//    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
    }

    public LocalDate getNascimento() {
        return this.nascimento;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getTelefone() {
        return this.telefone;
    }

    public void setDataCriacao(LocalDateTime date) {
        this.dataCriacao = date;
    }

    public LocalDateTime getDataCriacao() {
        return this.dataCriacao;
    }

//    public void setDataModificacao() {
//        this.dataModificacao = LocalDateTime.now();
//    }
    public void setDataModificacao(LocalDateTime date) {
        this.dataModificacao = date;
    }

    public LocalDateTime getDataModificacao() {
        return this.dataModificacao;
    }

//    @Override
//    public String toString() {
//        return "PESSOA => | id: " + super.getID() + " | nome: " + nome + " | nascimento: " + nascimento != null? nascimento.toString() : "" + " | telefone: " + telefone != null ? telefone : "" + " | dc: " + dataCriacao
//                + " | dm: " + dataModificacao + " |";
//    }
    @Override
    public String toString() {
        return "PESSOA => "
                + "| id: " + super.getID()
                + " | nome: " + (nome != null && !nome.trim().isEmpty() ? nome : "N/A")
                + " | nascimento: " + (nascimento != null ? utils.formatDateToString(nascimento) : "N/A")
                + " | telefone: " + (telefone != null && !telefone.trim().isEmpty() ? telefone : "N/A")
                + " | data de criação: " + (dataCriacao != null ? dataCriacao : "N/A")
                + " | data de modificação: " + (dataModificacao != null ? dataModificacao : "N/A")
                + " |";
    }

    @Override
    public Pessoa mapRow(ResultSet rs) throws SQLException {
        Pessoa pessoa = new Pessoa();
        pessoa.setID(rs.getInt("id"));
        pessoa.setNome(rs.getString("nome"));
        java.sql.Date date = rs.getDate("datanascimento");
        if (date != null) {
            pessoa.setNascimento(date.toLocalDate());
        }
        String telefone = rs.getString("telefone");
        if (telefone != null && !telefone.trim().isEmpty()) {
            pessoa.setTelefone(telefone);
        }
        // Verifica se os campos não são nulos antes de converter
        java.sql.Timestamp dataCriacao = rs.getTimestamp("datacriacao");
        if (dataCriacao != null) {
            pessoa.setDataCriacao(dataCriacao.toLocalDateTime());
        }

        java.sql.Timestamp dataModificacao = rs.getTimestamp("datamodificacao");
        if (dataModificacao != null) {
            pessoa.setDataModificacao(dataModificacao.toLocalDateTime());
        }
//        pessoa.setDataCriacao(rs.getTimestamp("dataCriacao").toLocalDateTime());
//        pessoa.setDataModificacao(rs.getTimestamp("dataModificacao").toLocalDateTime());
        return pessoa;
    }

}
//3953751
