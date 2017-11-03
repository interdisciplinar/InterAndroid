package interdisciplinar.com.br.interandroid.model;


import android.widget.RadioButton;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;

import interdisciplinar.com.br.interandroid.config.ConfiguracaoFirebase;

public class Usuario {

    private String id;
    private String email;
    private String senha;

    public Usuario() {
    }

    public void salvar() {
        DatabaseReference referenciaFirebase = ConfiguracaoFirebase.getFirebase();
        referenciaFirebase.child("usuarios").child(getId()).setValue(this);
    }

    @Exclude //Usado para não salvar este campo no banco de dados do usuario
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Exclude //Usado para não salvar este campo no banco de dados do usuario
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

}


