package exemplo.jpa;

import java.io.Serializable;
import java.util.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "TB_CARTAO_CREDITO")
public class CartaoCredito implements Serializable {
    @Id
    @Column(name = "ID_CARTAO_CREDITO")
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
    private Long id;  
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "cartaoCredito", optional = false)
    private Comprador dono;
    @Column(name = "TXT_BANDEIRA", nullable = false, length = 100)
    private String bandeira;
    @Column(name = "TXT_NUMERO", nullable = false, length = 30)
    private String numero;
    @Temporal(TemporalType.DATE)
    @Column(name = "DT_EXPIRACAO", nullable = false)
    private Date dataExpiracao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Comprador getDono() {
        return dono;
    }

    public void setDono(Comprador dono) {
        this.dono = dono;
    }

    public String getBandeira() {
        return bandeira;
    }

    public void setBandeira(String bandeira) {
        this.bandeira = bandeira;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Date getDataExpiracao() {
        return dataExpiracao;
    }

    public void setDataExpiracao(Date dataExpiracao) {
        this.dataExpiracao = dataExpiracao;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Usuario)) {
            return false;
        }

        CartaoCredito other = (CartaoCredito) object;

        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "exemplo.jpa.CartaoCredito[ id=" + id + " ]";
    }

}
