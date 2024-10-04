/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDateTime;
import model.dao.Identifiable;

public class Usuario extends Identifiable{
    private String tipo;
    private String login;
    private String senha;
    private Pessoa pessoa;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataModificacao;
    
    
  public Usuario(){
      this.dataCriacao = LocalDateTime.now();
  };

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public LocalDateTime getDataModificacao() {
        return dataModificacao;
    }

    public void setDataModificacao() {
        this.dataModificacao = LocalDateTime.now();
    }


    @Override
    public String toString() {
        return "Usuario{" + "tipo=" + tipo + ", login=" + login + ", senha=" + senha + ", dataCriacao=" + dataCriacao + ", dataModificacao=" + dataModificacao + "\n" +  pessoa.toString() + '}';
    }
    
}

