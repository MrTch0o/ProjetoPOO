
package model;

import java.time.LocalDateTime;
import model.dao.Identifiable;

public class Familia extends Identifiable {

    private String nomeFamilia;
    private String acesso;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataModificacao;

    public Familia() {
        this.dataCriacao = LocalDateTime.now();
    }

    public void setNomeFamilia(String nomeFamilia) {
        this.nomeFamilia = nomeFamilia;
    }

    public String getNomeFamilia() {
        return this.nomeFamilia;
    }

    public void setAcesso(String acesso) {
        this.acesso = acesso;
    }

    public String getAcesso() {
        return this.acesso;
    }

    public LocalDateTime getDataCriacao() {
        return this.dataCriacao;
    }

    public LocalDateTime getDataModificacao() {
        return this.dataModificacao;
    }

    @Override
    public String toString() {
        return "FAMILIA => | nomeFamilia = " + nomeFamilia + " | acesso = " + acesso + " | dataCriacao = " + dataCriacao + " | dataModificacao = " + dataModificacao + " |";
    }
}
