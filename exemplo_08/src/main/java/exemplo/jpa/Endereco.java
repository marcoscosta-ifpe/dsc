package exemplo.jpa;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/*
 * Todos os campos de Endereco serão armazenados na mesma tabela 
 * que armazena os dados de Usuario.
 */
@Embeddable
public class Endereco implements Serializable {
    @NotBlank
    @Size(max = 150)
    @Column(name = "END_TXT_LOGRADOURO")
    private String logradouro;
    @NotBlank
    @Size(max = 150)
    @Column(name = "END_TXT_BAIRRO")
    private String bairro;
    @NotNull
    @Min(1)
    @Max(99999)
    @Column(name = "END_NUMERO")
    private Integer numero;
    @Size(max = 30)
    @Column(name = "END_TXT_COMPLEMENTO")
    private String complemento;
    @NotNull
    @Pattern(regexp = "[0-90]{2}.[0-9]{3}-[0-9]{3}", message = "{exemplo.jpa.Endereco.cep}")
    @Column(name = "END_TXT_CEP")
    private String cep;
    @NotBlank
    @Size(max = 50)
    @Column(name = "END_TXT_CIDADE")
    private String cidade;
    @NotBlank
    @ValidaEstado
    @Size(min = 2, max = 2)
    @Column(name = "END_TXT_ESTADO")
    private String estado;

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }
    
    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
