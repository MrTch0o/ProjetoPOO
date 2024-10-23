/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDateTime;
import model.dao.Identifiable;

public class Usuario extends Identifiable{
    private int tipo = -1;
    private String login;
    private String senha;
    private Pessoa pessoa;
    private boolean logado=false;
    private boolean admin=false;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataModificacao;
    
    
  public Usuario(){
      this.dataCriacao = LocalDateTime.now();
  };

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
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

    public boolean isLogado() {
        return logado;
    }

    public void setLogado(boolean logado) {
        this.logado = logado;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
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
        return "USUARIO => | id: " + super.getID() + "| tipo: " + tipo + "| login: " + login + "| senha: " + senha + "idPessoa: " + pessoa.getID() + "| dc: " + dataCriacao + "| dm: " + dataModificacao + " |";
    }
    
}

