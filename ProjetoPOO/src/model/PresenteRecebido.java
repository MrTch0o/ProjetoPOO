/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDateTime;
import model.dao.Identifiable;

/**
 *
 * @author DEVENG
 */
public class PresenteRecebido extends Identifiable{

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

    public LocalDateTime getDataModificacao() {
        return dataModificacao;
    }

    public void setDataModificacao() {
        this.dataModificacao = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "PRESENTE RECEBIDO => | pessoa: " + pessoa.getNome() + " | presente: " + presente.getNome() + "| qtdCotas: " + qtdCotas + " | dc" + dataCriacao + " | dm: " + dataModificacao + " |";
    }
    
    

}
