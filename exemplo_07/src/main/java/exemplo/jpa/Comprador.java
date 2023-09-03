package exemplo.jpa;

import java.io.Serializable;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "TB_COMPRADOR")
@DiscriminatorValue(value = "C")
@PrimaryKeyJoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO")
public class Comprador extends Usuario implements Serializable {

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, optional = true)
    @JoinColumn(name = "ID_CARTAO_CREDITO", referencedColumnName = "ID_CARTAO_CREDITO")
    private CartaoCredito cartaoCredito;
    @OneToMany(mappedBy = "comprador", fetch = FetchType.LAZY, cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Oferta> ofertas;

    public CartaoCredito getCartaoCredito() {
        return cartaoCredito;
    }

    public void setCartaoCredito(CartaoCredito cartaoCredito) {
        this.cartaoCredito = cartaoCredito;
        this.cartaoCredito.setDono(this);
    }

    public List<Oferta> getOfertas() {
        return ofertas;
    }

    public boolean adicionar(Oferta oferta) {
        return ofertas.add(oferta);
    }

    @Override
    public String toString() {
        return "exemplo.jpa.Comprador[ id=" + id + " ]";
    }

}
