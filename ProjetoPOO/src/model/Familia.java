package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import model.dao.Database;
import model.dao.Identifiable;

public class Familia extends Identifiable implements Database.RowMapper {

    private String nomeFamilia;
    private String acesso;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataModificacao;

    public Familia() {
        this.dataCriacao = LocalDateTime.now();
    }

    public void setNomeFamilia(String nomeFamilia) {
        this.nomeFamilia = nomeFamilia;
    }

    public String getNomeFamilia() {
        return this.nomeFamilia;
    }

    public void setAcesso(String acesso) {
        this.acesso = acesso;
    }

    public String getAcesso() {
        return this.acesso;
    }

    public void setDataCriacao(LocalDateTime date) {
        this.dataCriacao = date;
    }

    public LocalDateTime getDataCriacao() {
        return this.dataCriacao;
    }

    public LocalDateTime getDataModificacao() {
        return this.dataModificacao;
    }

    public void setDataModificacao() {
        this.dataModificacao = LocalDateTime.now();
    }

    public void setDataModificacao(LocalDateTime date) {
        this.dataModificacao = date;
    }

    @Override
    public String toString() {
        return "FAMILIA => | id: " + super.getID() + "nomeFamilia: " + nomeFamilia + " | acesso: " + acesso + " | dc: " + dataCriacao + " | dm: " + dataModificacao + " |";
    }

    @Override
    public Familia mapRow(ResultSet rs) throws SQLException {
        Familia familia = new Familia();
        familia.setID(rs.getInt("id"));
        familia.setNomeFamilia(rs.getString("nome"));
        familia.setAcesso(rs.getString("acesso"));
        // Verifica se os campos não são nulos antes de converter
        java.sql.Timestamp dataCriacao = rs.getTimestamp("datacriacao");
        if (dataCriacao != null) {
            familia.setDataCriacao(dataCriacao.toLocalDateTime());
        }
        java.sql.Timestamp dataModificacao = rs.getTimestamp("datamodificacao");
        if (dataModificacao != null) {
            familia.setDataModificacao(dataModificacao.toLocalDateTime());
        }
        return familia;
    }
}
