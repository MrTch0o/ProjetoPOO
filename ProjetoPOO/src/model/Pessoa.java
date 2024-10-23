package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import model.dao.Identifiable;
import model.dao.Utils;

public class Pessoa extends Identifiable {
    Utils utils = new Utils();
    private String nome;
    private LocalDate nascimento;
    private String telefone;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataModificacao;

    public Pessoa() {
        this.dataCriacao = LocalDateTime.now();
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
    }

    public LocalDate getNascimento() {
        return this.nascimento;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getTelefone() {
        return this.telefone;
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
        return "PESSOA => | id: " + super.getID() + " | nome: " + nome + " | nascimento: " + utils.formatDateToString(nascimento) + " | telefone: " + telefone + " | dc: " + dataCriacao 
                + " | dm: " + dataModificacao + " |";
    }

}
