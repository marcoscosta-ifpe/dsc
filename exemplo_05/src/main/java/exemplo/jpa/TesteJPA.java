package exemplo.jpa;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

public class TesteJPA {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("exemplo_05");
    private static final Logger logger = Logger.getGlobal();

    static {
        logger.setLevel(Level.INFO);
    }
    
    public static void main(String[] args) {
        try {
            criarCategorias();
            criarItens();
        } finally {
            emf.close();
        }
    }
    
    public static void criarCategorias() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        Categoria categoria1 = new Categoria();
        categoria1.setNome("Informática");
        Categoria categoria2 = new Categoria();
        categoria2.setNome("Hardware");
        Categoria categoria3 = new Categoria();
        categoria3.setNome("Software");
        Categoria categoria4 = new Categoria();
        categoria4.setNome("Equipamentos Musicais");
        
        try {
            et.begin();
            em.persist(categoria1);
            em.persist(categoria2);
            em.persist(categoria3);
            em.persist(categoria4);
            et.commit();
        } catch (Exception ex) {
            if (et != null && et.isActive()) {
                Logger.getGlobal().log(Level.SEVERE,
                        "Cancelando transação com erro. Mensagem: {0}", ex.getMessage());
                et.rollback();
                Logger.getGlobal().info("Transação cancelada.");
            }
        } finally {
            em.close();
        }
    }

    private static void criarItens() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        Item item = new Item();
        Oferta oferta1 = new Oferta();
        Oferta oferta2 = new Oferta();
        
        try {
            et.begin();                        
            item.setTitulo("POD Studio");
            item.setDescricao("Interface para gravação de instrumentos musicais");
            //Selecionando todas as categorias cadastradas
            Query query = em.createQuery("SELECT c FROM Categoria c");
            List<Categoria> categorias = query.getResultList();
            categorias.forEach(categoria -> {
                item.adicionar(categoria);
            });
            
            oferta1.setValor(250.00);
            oferta2.setValor(275.00);
            item.adicionar(oferta1);
            item.adicionar(oferta2);
            
            em.persist(item);
            em.flush(); //força as alterações a irem para o banco
            item.remover(oferta2); //Observe que a oferta "órfã" será deletada.
            et.commit();
        } catch (Exception ex) {
            if (et != null && et.isActive()) {
                Logger.getGlobal().log(Level.SEVERE,
                        "Cancelando transação com erro. Mensagem: {0}", ex.getMessage());
                et.rollback();
                Logger.getGlobal().info("Transação cancelada.");
            }
        } finally {
            em.close();
        }        
    }
}
