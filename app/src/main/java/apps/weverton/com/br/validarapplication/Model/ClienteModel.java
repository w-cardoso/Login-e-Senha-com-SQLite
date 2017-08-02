package apps.weverton.com.br.validarapplication.Model;

import java.io.Serializable;

/**
 * Created by re034850 on 28/07/2017.
 */

public class ClienteModel implements Serializable {
    private Long id;
    private String usuario;
    private String email;
    private String senha;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
