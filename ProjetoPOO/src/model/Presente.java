package model;

import java.time.LocalDateTime;
import model.dao.Identifiable;

public class Presente extends Identifiable {

    private String nome;
    private int cotas;
    private double valor;
    private double valorCota;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataModificacao;

    public Presente() {
        this.dataCriacao = LocalDateTime.now();
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return this.nome;
    }

    public int getCotas() {
        return cotas;
    }

    public void setCotas(int cotas) {
        this.cotas = cotas;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public double getValor() {
        return this.valor;
    }

    public double getValorCota() {
        return valorCota;
    }

    public void setValorCota(double valorCota) {
        this.valorCota = valorCota;
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
    
    
    public String toStringConvidado(){
        
        return  (super.getID()
                +"."
                +nome
                +" - Valor estimado: R$"
                +String.format("%.2f", valor)
                +" - Valor da cota: R$"
                +String.format("%.2f", valorCota)
                +" - Cotas restantes: "
                +cotas);
    }

    
    
    @Override
    public String toString() {
        return "PRESENTE => | id: " + super.getID() + " | nome: " + nome + " | valor: " + String.format("%.2f", valor) + " | cotas: " + cotas + "| valorCota: " + String.format("%.2f", valorCota) + "| dc: " + dataCriacao + " | dm: " + dataModificacao + " |";
    }
    
}
