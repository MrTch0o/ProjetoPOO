package model;

import java.time.LocalDateTime;
import model.dao.Identifiable;

public class MuralRecado extends Identifiable {

    private String nome;
    private String recado;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataModificacao;

    public MuralRecado() {
        this.dataCriacao = LocalDateTime.now();
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRecado() {
        return recado;
    }

    public void setRecado(String recado) {
        this.recado = recado;
    }

    public LocalDateTime getDataModificacao() {
        return dataModificacao;
    }

    public void setDataModificacao(LocalDateTime dataModificacao) {
        this.dataModificacao = dataModificacao;
    }

    @Override
    public String toString() {
        return "RECADO => | " + "NOME: " + nome + " | RECADO: " + recado + " | DC: " + dataCriacao + " | DM: " + dataModificacao + " |";
    }
}
