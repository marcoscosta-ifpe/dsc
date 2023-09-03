package exemplo.jpa;

import java.io.Serializable;
import java.util.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "TB_OFERTA")
public class Oferta implements Serializable {

    @Id
    @Column(name = "ID_OFERTA")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_ITEM", referencedColumnName = "ID_ITEM")
    private Item item;
    @Column(name = "NUM_VALOR", nullable = false)
    private Double valor;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DT_OFERTA", nullable = false)
    private Date data;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_COMPRADOR", referencedColumnName = "ID_USUARIO", nullable = false)
    private Comprador comprador;
    @Column(name = "FLAG_VENCEDORA", nullable = false)
    private boolean vencedora;

    public Oferta() {
        this.data = new Date();
        this.vencedora = false;
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

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
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

}
