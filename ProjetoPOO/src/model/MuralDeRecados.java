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
public class MuralDeRecados extends Identifiable{
    private Pessoa pessoa;
    private String comentario;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataModificacao;
    
    
    public MuralDeRecados(){
        dataCriacao = LocalDateTime.now();
    }
    
    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
    
    public Pessoa getPessoa() {
        return this.pessoa;
    }
    
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
    
    public String getComentario() {
        return this.comentario;
    }
    
    public LocalDateTime getDataCriacao() {
        return this.dataCriacao;
    }
    
    public LocalDateTime getDataModificacao() {
        return this.dataModificacao;
    }
}
