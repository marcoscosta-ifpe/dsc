package exemplo.jpa;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class TesteJPA {

    private static final EntityManagerFactory emf
            = Persistence.createEntityManagerFactory("exemplo_03");

    public static void main(String[] args) throws IOException {
        persistirUsuario();
        consultarUsuario(1L);
    }

    private static void persistirUsuario() throws IOException {
        Usuario usuario = new Usuario();
        preencherUsuario(usuario);
        EntityManager em = null;
        EntityTransaction et;
        try {
            em = emf.createEntityManager();
            et = em.getTransaction();
            et.begin();
            em.persist(usuario);
            et.commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    private static void preencherUsuario(Usuario usuario) throws IOException {
        usuario.setNome("Fulano da Silva");
        usuario.setEmail("fulano@gmail.com");
        usuario.setLogin("fulano");
        usuario.setSenha("teste");
        usuario.setCpf("534.585.764-45");
        usuario.setTipo(TipoUsuario.ADMIN);
        usuario.addTelefone("(81) 3456-2525");
        usuario.addTelefone("(81) 9122-4528");
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, 1981);
        c.set(Calendar.MONTH, Calendar.FEBRUARY);
        c.set(Calendar.DAY_OF_MONTH, 25);
        usuario.setDataNascimento(c.getTime());
        try {
            BufferedImage img = ImageIO.read(new URL("https://cdn-icons-png.flaticon.com/512/7778/7778185.png"));
            try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
                ImageIO.write(img, "png", baos);
                baos.flush();
                usuario.setFoto(baos.toByteArray());
            }
        } catch (IOException ex) {
            Logger.getGlobal().log(Level.SEVERE, null, ex);
            throw ex;
        }

        preencherEndereco(usuario);
    }

    public static void preencherEndereco(Usuario usuario) {
        Endereco endereco = new Endereco();
        endereco.setLogradouro("Rua Iolanda Rodrigues Sobral");
        endereco.setBairro("Varzea");
        endereco.setCidade("Recife");
        endereco.setEstado("Pernambuco");
        endereco.setCep("50690-220");
        endereco.setNumero(550);
        usuario.setEndereco(endereco);
    }

    private static void consultarUsuario(long l) {
        EntityManager em = emf.createEntityManager();
        
        //A primeira consulta vai recuperar apenas os dados de TB_USUARIO, excetuando IMG_FOTO.
        Usuario usuario = em.find(Usuario.class, l);
        System.out.println(usuario.getNome());
        //usuario.getTelefones().iterator() vai provocar uma consulta à tabela TB_TELEFONE.
        System.out.println(usuario.getTelefones().iterator().next());
        //usuario.getFoto().length vai as informações de TB_USUARIO, incluindo IMG_FOTO.
        System.out.println(usuario.getFoto().length);
        em.close();
    }
}
