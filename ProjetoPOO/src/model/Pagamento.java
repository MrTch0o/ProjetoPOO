package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import model.dao.Database;
import model.dao.Identifiable;
import model.dao.Utils;

public class Pagamento extends Identifiable implements Database.RowMapper<Pagamento> {
    Utils utils = new Utils();
    private LocalDate data;
    private Fornecedor fornecedor;
    private double valor;
    private int tipo;
    private int parcela;
    private String descricao;
    private Calendario calendario;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataModificacao;

//    public Pagamento() {
//        this.dataCriacao = LocalDateTime.now();
//    }
    
    public void setDataCriacao(LocalDateTime date){
        this.dataCriacao = date;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalDate getData() {
        return this.data;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public Fornecedor getFornecedor() {
        return this.fornecedor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public double getValor() {
        return this.valor;
    }

    public void setParcela(int parcela) {
        this.parcela = parcela;
    }

    public int getParcela() {
        return this.parcela;
    }

    public LocalDateTime getDataCriacao() {
        return this.dataCriacao;
    }

    public LocalDateTime getDataModificacao() {
        return this.dataModificacao;
    }

//    public void setDataModificacao() {
//        this.dataModificacao = LocalDateTime.now();
//    }
    public void setDataModificacao(LocalDateTime date) {
        this.dataModificacao = date;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
    
    

    @Override
    public String toString() {
        return "PAGAMENTO => | id: " + super.getID() + " | data: " + utils.formatDateToString(data) + "| fornecedor: " + fornecedor.getID() + " | valor: "
                + valor + " | tipo: " + tipo + " | parcela: " + parcela + " | descricao: " + descricao + " | dc: " + dataCriacao + " | dm: " + dataModificacao + " |";
    }

    @Override
    public Pagamento mapRow(ResultSet rs) throws SQLException {
        Pagamento pagamento = new Pagamento();
        pagamento.setID(rs.getInt("id"));
        pagamento.setData(rs.getTimestamp("data").toLocalDateTime().toLocalDate());
        pagamento.setValor(rs.getDouble("valor"));
        pagamento.setTipo(rs.getInt("tipo"));
        pagamento.setParcela(rs.getInt("parcelas"));
        pagamento.setDescricao(rs.getString("descricao"));
        // Verifica se os campos não são nulos antes de converter
        java.sql.Timestamp dataCriacao = rs.getTimestamp("datacriacao");
        if (dataCriacao != null) {
            pagamento.setDataCriacao(dataCriacao.toLocalDateTime());
        }

        java.sql.Timestamp dataModificacao = rs.getTimestamp("datamodificacao");
        if (dataModificacao != null) {
            pagamento.setDataModificacao(dataModificacao.toLocalDateTime());
        }
        // Obtém o ID do forncedor associado ao pagamento
        int fornecedorId = rs.getInt("fornecedor_id");
        if (fornecedorId > 0) {
            // Busca fornecedor no banco usando o ID
            Database<Fornecedor> fornecedorDatabase = new Database<>("fornecedor"); // Instância de Database para pessoa
            Fornecedor fornecedor = fornecedorDatabase.getById(fornecedorId, new Fornecedor());

            // Verifica se a forncedor foi encontrada e associa ao pagamento
            if (fornecedor != null) {
                pagamento.setFornecedor(fornecedor);
            }
        }

        return pagamento;

    }

}
