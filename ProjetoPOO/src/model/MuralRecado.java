package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import model.dao.Database;
import model.dao.Identifiable;

public class MuralRecado extends Identifiable implements Database.RowMapper<MuralRecado>{

    private String nome;
    private String recado;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataModificacao;

    public MuralRecado() {
        this.dataCriacao = LocalDateTime.now();
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }
    
    public void setDataCriacao(LocalDateTime date) {
        this.dataCriacao = date;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRecado() {
        return recado;
    }

    public void setRecado(String recado) {
        this.recado = recado;
    }

    public LocalDateTime getDataModificacao() {
        return dataModificacao;
    }

    public void setDataModificacao(LocalDateTime dataModificacao) {
        this.dataModificacao = dataModificacao;
    }

    @Override
    public String toString() {
        return "RECADO => | " + "NOME: " + nome + " | RECADO: " + recado + " | DC: " + dataCriacao + " | DM: " + dataModificacao + " |";
    }

    @Override
    public MuralRecado mapRow(ResultSet rs) throws SQLException {
        MuralRecado mr = new MuralRecado();
        mr.setID(rs.getInt("id"));
        mr.setNome(rs.getString("nome"));
        mr.setRecado(rs.getString("recado"));
        
        // Verifica se os campos não são nulos antes de converter
        java.sql.Timestamp dataCriacao = rs.getTimestamp("datacriacao");
        if (dataCriacao != null) {
            mr.setDataCriacao(dataCriacao.toLocalDateTime());
        }

        java.sql.Timestamp dataModificacao = rs.getTimestamp("datamodificacao");
        if (dataModificacao != null) {
            mr.setDataModificacao(dataModificacao.toLocalDateTime());
        }
//        mr.setDataCriacao(rs.getTimestamp("dataCriacao").toLocalDateTime());
//        mr.setDataModificacao(rs.getTimestamp("dataModificacao").toLocalDateTime());
        return mr;
    }
}
