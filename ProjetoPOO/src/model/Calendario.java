package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import model.dao.Database;
import model.dao.Identifiable;
import model.dao.Utils;

public class Calendario extends Identifiable implements Database.RowMapper {

    Utils utils = new Utils();
    private LocalDate dataEvento;
    private String titulo;
    private String descricao;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataModificacao;
//    private String dataEventoFormat;
    private Pagamento pagamento;

//    public Calendario() {
//        this.dataCriacao = LocalDateTime.now();
//    }

//    public LocalDate getData() {
//        return dataEvento;
//    }
//
//    public void setData(LocalDate data) {
//        this.dataEvento = data;
//    }
    public LocalDate getDataEvento() {
        return dataEvento;
    }

    public void setDataEvento(LocalDate dataEvento) {
        this.dataEvento = dataEvento;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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

//    public void setDataModificacao() {
//        this.dataModificacao = LocalDateTime.now();
//    }

    public void setDataModificacao(LocalDateTime date) {
        this.dataModificacao = date;
    }

//    public String getDataEventoFormat() {
//        return dataEventoFormat;
//    }
//
//    public void setDataEventoFormat(String dataEventoFormat) {
//        this.dataEventoFormat = dataEventoFormat;
//    }


    public Pagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }

    @Override
    public String toString() {
        return "CALENDARIO => | id: " + super.getID() + ((pagamento != null) ? " | pagamentoId = " + String.valueOf(pagamento.getID()) : "") + " | dataEvento: " + utils.formatDateToString(dataEvento) + " | titulo: " + titulo + " | descricao: " + descricao
                + " | dc: " + dataCriacao + " | dm: " + dataModificacao + " |";
    }

    @Override
    public Calendario mapRow(ResultSet rs) throws SQLException {
        Calendario calendario = new Calendario();
        calendario.setID(rs.getInt("id"));
        dataEvento = rs.getDate("dataevento").toLocalDate();
        calendario.setDataEvento(dataEvento);
        calendario.setTitulo(rs.getString("titulo"));
        calendario.setDescricao(rs.getString("descricao"));

        int pagamentoId = rs.getInt("pagamento_id");
        if (pagamentoId > 0) {
            // Busca a pagamento no banco usando o ID
            Database<Pagamento> pagamentoDatabase = new Database<>("pagamento"); // Instância de Database para pagamento
            Pagamento pagamento = pagamentoDatabase.getById(pagamentoId, new Pagamento());

            // Verifica se a pagamento foi encontrada e associa ao fornecedor
            if (pagamento != null) {
                calendario.setPagamento(pagamento);
            }

            // Verifica se os campos não são nulos antes de converter
            java.sql.Timestamp dataCriacao = rs.getTimestamp("datacriacao");
            if (dataCriacao != null) {
                calendario.setDataCriacao(dataCriacao.toLocalDateTime());
            }

            java.sql.Timestamp dataModificacao = rs.getTimestamp("datamodificacao");
            if (dataModificacao != null) {
                calendario.setDataModificacao(dataModificacao.toLocalDateTime());
            }
//        calendario.setDataCriacao(rs.getTimestamp("dataCriacao").toLocalDateTime());
//        calendario.setDataModificacao(rs.getTimestamp("dataModificacao").toLocalDateTime());

        }
        return calendario;
    }

}
