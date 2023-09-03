package exemplo.jpa;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "TB_OFERTA")
public class Oferta implements Serializable, Comparable<Oferta> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_ITEM", referencedColumnName = "ID")
    private Item item;
    @DecimalMin("0.1")
    @NotNull
    @Column(name = "NUM_VALOR")
    private BigDecimal valor;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_OFERTA")
    private Date data;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_COMPRADOR", referencedColumnName = "ID_USUARIO")
    private Comprador comprador;
    @Column(name = "FLAG_VENCEDORA")
    private boolean vencedora;    
    
    public Oferta() {
        this.vencedora = false;
    }

    @PrePersist
    public void set() {
        this.setData(new Date());
    }
    
    public Comprador getComprador() {
        return comprador;
    }

    public void setComprador(Comprador comprador) {
        this.comprador = comprador;
    }    

    public boolean isVencedora() {
        return vencedora;
    }

    public void setVencedora(boolean vencedora) {
        this.vencedora = vencedora;
    }
    
    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Oferta)) {
            return false;
        }
        Oferta other = (Oferta) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "exemplo.jpa.Oferta[ id=" + id + " ]";
    }

    @Override
    public int compareTo(Oferta oferta) {
        return this.valor.compareTo(oferta.valor);
    }
    
}
