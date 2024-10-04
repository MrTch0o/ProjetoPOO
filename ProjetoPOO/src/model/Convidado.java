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
public class Convidado extends Identifiable{
    private Pessoa pessoa;
    private Familia familia;
    private String parentesco;
    private boolean confirmado;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataModificacao;
    
    public Convidado(){
        this.dataCriacao = LocalDateTime.now();
    }
    
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
    
    public void setDataCriacao() {
        this.dataCriacao = LocalDateTime.now();
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
        return "\nConvidado{" + pessoa.toString() + "\n"
                + "parentesco=" + parentesco + ", confirmado=" + confirmado + ", dataCriacao=" + dataCriacao + ", dataModificacao=" + dataModificacao + "\n"
                + familia.toString() + '}';
    }
    
    
}


