package exemplo.jpa;

import java.io.Serializable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/*
 * @Entity informa que a classe é uma entidade JPA.
 * Por padrão, o nome da tabela é o nome da classe.
 * A entidade deve possuir um construtor padrão vazio.
 */
@Entity
public class Usuario implements Serializable {
    /*
     * @Id informa que o atributo representa a chave primária.
     * @GeneratedValue informa como gerar a chave primária.
     * GenerationType.IDENTITY é uma estratégia que pode ser utilizada no Derby.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /*
     * Por padrão, se o nome do atributo é o nome do campo na tabela, nada
     * precisa ser feito em termos de mapeamento.
     */
    private String nome;
    private String email;
    private String login;
    private String senha;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
