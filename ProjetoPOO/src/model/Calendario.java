/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import model.dao.Identifiable;
import model.dao.Utils;

/**
 *
 * @author DEVENG
 */
public class Calendario extends Identifiable {
    Utils utils = new Utils();
    private LocalDate dataEvento;
    private String titulo;
    private String descricao;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataModificacao;
    private String dataEventoFormat;
    private Pagamento pagamento;

    public Calendario() {
        this.dataCriacao = LocalDateTime.now();
    }

    public LocalDate getData() {
        return dataEvento;
    }

    public void setData(LocalDate data) {
        this.dataEvento = data;
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

    public LocalDateTime getDataModificacao() {
        return dataModificacao;
    }

    public void setDataModificacao() {
        this.dataModificacao = LocalDateTime.now();
    }

    public String getDataEventoFormat() {
        return dataEventoFormat;
    }

    public void setDataEventoFormat(String dataEventoFormat) {
        this.dataEventoFormat = dataEventoFormat;
    }

    public LocalDate getDataEvento() {
        return dataEvento;
    }

    public void setDataEvento(LocalDate dataEvento) {
        this.dataEvento = dataEvento;
    }

    public Pagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }
    
    

    @Override
    public String toString() {
        return "CALENDARIO => | id: " + super.getID() + " | pagamentoId = " + pagamento.getID() + " | dataEvento: " + utils.formatDateToString(dataEvento) + " | titulo: " + titulo + " | descricao: " + descricao 
                + " | dc: " + dataCriacao + " | dm: " + dataModificacao + " |";
    }

}
