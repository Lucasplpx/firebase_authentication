package br.com.faculdadedelta.materialdesign.model;

public class Estudante {

    private String  email, senha;


    public Estudante() {
    }


    public Estudante(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "Estudante{" +
                "email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                '}';
    }
}
