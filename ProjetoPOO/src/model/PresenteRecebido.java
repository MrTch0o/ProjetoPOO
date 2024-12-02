package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import model.dao.Database;
import model.dao.Identifiable;

public class PresenteRecebido extends Identifiable implements Database.RowMapper{

    private Presente presente;
    private Pessoa pessoa;
    private int qtdCotas;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataModificacao;

    public PresenteRecebido() {
        this.dataCriacao = LocalDateTime.now();
    }

    public Presente getPresente() {
        return presente;
    }

    public void setPresente(Presente presente) {
        this.presente = presente;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public int getQtdCotas() {
        return qtdCotas;
    }

    public void setQtdCotas(int qtdCotas) {
        this.qtdCotas = qtdCotas;
    }

    public LocalDateTime getDataCriacao() {
        return this.dataCriacao;
    }
    public void setDataCriacao(LocalDateTime date) {
        this.dataCriacao = date;
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
        return "PRESENTE RECEBIDO => | pessoa: " + pessoa.getNome() + " | presente: " + presente.getNome() + "| qtdCotas: " + qtdCotas + " | dc" + dataCriacao + " | dm: " + dataModificacao + " |";
    }

    @Override
     public PresenteRecebido mapRow(ResultSet rs) throws SQLException {
        PresenteRecebido presenteRecebido = new PresenteRecebido();
        presenteRecebido.setID(rs.getInt("id"));
        presenteRecebido.setQtdCotas(rs.getInt("qtdcotas"));
        // Verifica se os campos não são nulos antes de converter
        java.sql.Timestamp dataCriacao = rs.getTimestamp("datacriacao");
        if (dataCriacao != null) {
            presenteRecebido.setDataCriacao(dataCriacao.toLocalDateTime());
        }

        java.sql.Timestamp dataModificacao = rs.getTimestamp("datamodificacao");
        if (dataModificacao != null) {
            presenteRecebido.setDataModificacao(dataModificacao.toLocalDateTime());
        }
        // Obtém o ID do forncedor associado ao presenteRecebido
        int pessoaID = rs.getInt("pessoa_id");
        if (pessoaID > 0) {
            // Busca fornecedor no banco usando o ID
            Database<Pessoa> pessoaDatabase = new Database<>("pessoa"); // Instância de Database para pessoa
            Pessoa pessoa = pessoaDatabase.getById(pessoaID, new Pessoa());

            // Verifica se a forncedor foi encontrada e associa ao presenteRecebido
            if (pessoa != null) {
                presenteRecebido.setPessoa(pessoa);
            }
        }

        int presenteID = rs.getInt("presente_id");
        if (presenteID > 0) {
            // Busca fornecedor no banco usando o ID
            Database<Presente> presenteDatabase = new Database<>("presente"); // Instância de Database para presente
            Presente presente = presenteDatabase.getById(presenteID, new Presente());

            // Verifica se a forncedor foi encontrada e associa ao presenteRecebido
            if (presente != null) {
                presenteRecebido.setPresente(presente);
            }
        }

        return presenteRecebido;

    }
    

}
