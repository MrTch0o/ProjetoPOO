/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import model.dao.Identifiable;

/**
 *
 * @author Gabriel
 */
public class Pagamento extends Identifiable {

    private LocalDate data;
    private Fornecedor fornecedor;
    private double valor;
    private int tipo;
    private int parcela;
    private String descricao;
    private Calendario calendario;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataModificacao;

    public Pagamento() {
        this.dataCriacao = LocalDateTime.now();
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

    public void setDataModificacao() {
        this.dataModificacao = LocalDateTime.now();
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
    
    

    @Override
    public String toString() {
        return "Pagamento{" + "data=" + data + "\n"
                + fornecedor + "\n"
                + "valor=" + valor + ", tipo=" + tipo + ", parcela=" + parcela + ", descricao=" + descricao + ", dataCriacao=" + dataCriacao + ", dataModificacao=" + dataModificacao + '}';
    }

}
