package model;

import java.time.LocalDateTime;
import model.dao.Identifiable;

public class Fornecedor extends Identifiable {

    private String razaoSocial;
    private String cpfCnpj;
    private String telefone;
    private Pessoa pessoa;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataModificacao;

    public Fornecedor() {
        this.dataCriacao = LocalDateTime.now();
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getRazaoSocial() {
        return this.razaoSocial;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public String getcpfCnpj() {
        return this.cpfCnpj;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getTelefone() {
        return this.telefone;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
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
        return "FORNECEDOR => | id: " + super.getID() + " | razaoSocial: " + razaoSocial + " | cpfCnpj: " + cpfCnpj + " | telefone: " + telefone + " | pessoaId: " + pessoa.getID()
                + " | dc: " + dataCriacao + " | dm: " + dataModificacao +  " |";
    }
    
    
}
