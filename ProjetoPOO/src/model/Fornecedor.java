package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import model.dao.Database;
import model.dao.Identifiable;

public class Fornecedor extends Identifiable implements Database.RowMapper<Fornecedor> {

    private String razaoSocial;
    private String cpfCnpj;
    private String telefone;
    private Pessoa pessoa;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataModificacao;

//    public Fornecedor() {
//        this.dataCriacao = LocalDateTime.now();
//    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getRazaoSocial() {
        return this.razaoSocial;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public String getcpfCnpj() {
        return this.cpfCnpj;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getTelefone() {
        return this.telefone;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
    
     public void setDataCriacao (LocalDateTime date) {
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
        return "FORNECEDOR => | id: " + super.getID() + " | razaoSocial: " + razaoSocial + " | cpfCnpj: " + cpfCnpj + " | telefone: " + telefone + " | pessoaId: " + pessoa.getID()
                + " | dc: " + dataCriacao + " | dm: " + dataModificacao + " |";
    }

    @Override
    public Fornecedor mapRow(ResultSet rs) throws SQLException {
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setID(rs.getInt("id"));
        fornecedor.setRazaoSocial(rs.getString("razaosocial"));
        fornecedor.setCpfCnpj(rs.getString("cpfcnpj"));
        fornecedor.setTelefone(rs.getString("telefone"));
        // Verifica se os campos não são nulos antes de converter
        java.sql.Timestamp dataCriacao = rs.getTimestamp("datacriacao");
        if (dataCriacao != null) {
            fornecedor.setDataCriacao(dataCriacao.toLocalDateTime());
        }

        java.sql.Timestamp dataModificacao = rs.getTimestamp("datamodificacao");
        if (dataModificacao != null) {
            fornecedor.setDataModificacao(dataModificacao.toLocalDateTime());
        }
        // Obtém o ID da pessoa associado ao fornecedor
        int pessoaId = rs.getInt("pessoa_id");
        if (pessoaId > 0) {
            // Busca a Pessoa no banco usando o ID
            Database<Pessoa> pessoaDatabase = new Database<>("pessoa"); // Instância de Database para pessoa
            Pessoa pessoa = pessoaDatabase.getById(pessoaId, new Pessoa());

            // Verifica se a Pessoa foi encontrada e associa ao fornecedor
            if (pessoa != null) {
                fornecedor.setPessoa(pessoa);
            }
        }

        return fornecedor;
    }

}
