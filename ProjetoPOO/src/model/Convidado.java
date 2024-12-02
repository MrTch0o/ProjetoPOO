package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import model.dao.Database;
import model.dao.Identifiable;

public class Convidado extends Identifiable implements Database.RowMapper<Convidado> {

    private Pessoa pessoa;
    private Familia familia;
    private String parentesco;
    private boolean confirmado = false;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataModificacao;

//    public Convidado() {
//        this.dataCriacao = LocalDateTime.now();
//    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Pessoa getPessoa() {
        return this.pessoa;
    }

    public void setFamilia(Familia familia) {
        this.familia = familia;
    }

    public Familia getFamilia() {
        return this.familia;
    }

    public void setParentesco(String parentesco) {
        this.parentesco = parentesco;
    }

    public String getParentesco() {
        return this.parentesco;
    }

    public void setConfirmado(boolean confirmado) {
        this.confirmado = confirmado;
    }

    public boolean isConfirmado() {
        return this.confirmado;
    }

//    public void setDataCriacao() {
//        this.dataCriacao = LocalDateTime.now();
//    }

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

    @Override
    public String toString() {
        return "CONVIDADO => | id: " + super.getID() + " | pessoaId: " + pessoa.getID() + " | codigoAcesso: " + familia.getAcesso() + " | familiaId: " + familia.getID() + " | parentesco: " + parentesco + " | confirmado: " + confirmado
                + " | dc: " + dataCriacao + " | dm: " + dataModificacao + " |";
    }

    @Override
    public Convidado mapRow(ResultSet rs) throws SQLException {
        Convidado convidado = new Convidado();
        convidado.setID(rs.getInt("id"));
        convidado.setParentesco(rs.getString("parentesco"));
        convidado.setConfirmado(rs.getInt("confirmado") == 1 ? true : false);
        // Verifica se os campos não são nulos antes de converter
        java.sql.Timestamp dataCriacao = rs.getTimestamp("datacriacao");
        if (dataCriacao != null) {
            convidado.setDataCriacao(dataCriacao.toLocalDateTime());
        }

        java.sql.Timestamp dataModificacao = rs.getTimestamp("datamodificacao");
        if (dataModificacao != null) {
            convidado.setDataModificacao(dataModificacao.toLocalDateTime());
        }
        // Obtém o ID do forncedor associado ao convidado
        int pessoaID = rs.getInt("pessoa_id");
        if (pessoaID > 0) {
            // Busca fornecedor no banco usando o ID
            Database<Pessoa> pessoaDatabase = new Database<>("pessoa"); // Instância de Database para pessoa
            Pessoa pessoa = pessoaDatabase.getById(pessoaID, new Pessoa());

            // Verifica se a forncedor foi encontrada e associa ao convidado
            if (pessoa != null) {
                convidado.setPessoa(pessoa);
            }
        }

        int familiaID = rs.getInt("familia_id");
        if (familiaID > 0) {
            // Busca fornecedor no banco usando o ID
            Database<Familia> familiaDatabase = new Database<>("familia"); // Instância de Database para familia
            Familia familia = familiaDatabase.getById(familiaID, new Familia());

            // Verifica se a forncedor foi encontrada e associa ao convidado
            if (familia != null) {
                convidado.setFamilia(familia);
            }
        }

        return convidado;

    }

}
