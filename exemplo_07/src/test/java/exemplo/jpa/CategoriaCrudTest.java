/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exemplo.jpa;

import static exemplo.jpa.GenericTest.logger;
import jakarta.persistence.TypedQuery;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author marcos
 */
public class CategoriaCrudTest extends GenericTest {

    @Test
    public void persistirCategoria() {
        logger.info("Executando persistirCategoria()");
        Categoria instrumentosMusicais = em.find(Categoria.class, Long.valueOf(1));
        Categoria categoria = new Categoria();
        categoria.setNome("Bateria");
        categoria.setMae(instrumentosMusicais);
        em.persist(categoria);
        em.flush();
        assertNotNull(categoria.getId());
    }

    @Test
    public void atualizarCategoria() {
        logger.info("Executando atualizarCategoria()");
        TypedQuery<Categoria> query = em.createNamedQuery("Categoria.PorNome", Categoria.class);
        query.setParameter("nome", "Guitarras");
        Categoria categoria = query.getSingleResult();
        assertNotNull(categoria);
        categoria.setNome("Guitarras Elétricas");
        em.flush();
        assertEquals(0, query.getResultList().size());
        query.setParameter("nome", "Guitarras Elétricas");
        categoria = query.getSingleResult();
        assertNotNull(categoria);
    }

    @Test
    public void atualizarCategoriaMerge() {
        logger.info("Executando atualizarCategoriaMerge()");
        TypedQuery<Categoria> query = em.createNamedQuery("Categoria.PorNome", Categoria.class);
        query.setParameter("nome", "Pedais");
        Categoria categoria = query.getSingleResult();
        assertNotNull(categoria);
        categoria.setNome("Pedais de Guitarra");
        em.clear();       
        em.merge(categoria);
        em.flush();
        assertEquals(0, query.getResultList().size());
    }

    @Test
    public void removerCategoria() {
        logger.info("Executando removerCategoria()");
        TypedQuery<Categoria> query = em.createNamedQuery("Categoria.PorNome", Categoria.class);
        query.setParameter("nome", "Carros");
        Categoria categoria = query.getSingleResult();
        assertNotNull(categoria);
        em.remove(categoria);
        em.flush();
        assertEquals(0, query.getResultList().size());
    }
}
