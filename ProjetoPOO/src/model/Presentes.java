/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDateTime;
import model.dao.Identifiable;

/**
 *
 * @author Gabriel
 */
public class Presentes extends Identifiable {
    private String nome;
    private String tipo;
    private Pessoa pessoa;
    private double valor;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataModificacao;
    
    public Presentes(){
        this.dataCriacao = LocalDateTime.now();
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getNome() {
        return this.nome;
    }
    
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public String getTipo() {
        return this.tipo;
    }
    
    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
    
    public Pessoa getPessoa() {
        return this.pessoa;
    }
    
    public void setValor(double valor) {
        this.valor = valor;
    }
    
    public double getValor() {
        return this.valor;
    }

    public LocalDateTime getDataCriacao() {
        return this.dataCriacao;
    }
    
    public void setDataModificacao() {
        this.dataModificacao = LocalDateTime.now();
    }
    
    public LocalDateTime getDataModificacao() {
        return this.dataModificacao;
    }

    @Override
    public String toString() {
        return "Presentes{"+ "id="+ getID() + ", nome=" + nome + ", tipo=" + tipo + ", pessoa=" + pessoa + ", valor=" + valor + ", dataCriacao=" + dataCriacao + ", dataModificacao=" + dataModificacao + '}';
    }
    
    
}
