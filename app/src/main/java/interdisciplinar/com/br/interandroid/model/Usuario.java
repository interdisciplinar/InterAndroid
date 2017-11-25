package interdisciplinar.com.br.interandroid.model;

import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;

import interdisciplinar.com.br.interandroid.config.ConfiguracaoFirebase;

public class Usuario {

    private String id;

    //Layout emailSenha
    private String txtEmailCadastro;
    private String txtSenhaCadastro;
    private String perfil;

    //Layout dadosCliente
    private String txtPNomeCliente;
    private String txtSNomeCliente;
    private String txtCPFCliente;
    private String txtTelefoneCliente;
    private String txtCelularCliente;
    private String sexo;
    private String termos;

    //Layout endereçoCliente
    private String txtCEPCliente;
    private String txtEndereco;
    private String txtNumero;
    private String txtComplemento;
    private String txtBairro;
    private String txtCidade;
    private String txtEstado;
    private String txtPais;

    public Usuario() {
    }

    public void salvar() {
        DatabaseReference referenciaFirebase = ConfiguracaoFirebase.getFirebase();
        referenciaFirebase.child("usuarios").child(getId()).setValue(this);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTxtEmailCadastro() {
        return txtEmailCadastro;
    }

    public void setTxtEmailCadastro(String txtEmailCadastro) {
        this.txtEmailCadastro = txtEmailCadastro;
    }

    public String getTxtSenhaCadastro() {
        return txtSenhaCadastro;
    }

    public void setTxtSenhaCadastro(String txtSenhaCadastro) {
        this.txtSenhaCadastro = txtSenhaCadastro;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public String getTxtPNomeCliente() {
        return txtPNomeCliente;
    }

    public void setTxtPNomeCliente(String txtPNomeCliente) {
        this.txtPNomeCliente = txtPNomeCliente;
    }

    public String getTxtSNomeCliente() {
        return txtSNomeCliente;
    }

    public void setTxtSNomeCliente(String txtSNomeCliente) {
        this.txtSNomeCliente = txtSNomeCliente;
    }

    public String getTxtCPFCliente() {
        return txtCPFCliente;
    }

    public void setTxtCPFCliente(String txtCPFCliente) {
        this.txtCPFCliente = txtCPFCliente;
    }

    public String getTxtTelefoneCliente() {
        return txtTelefoneCliente;
    }

    public void setTxtTelefoneCliente(String txtTelefoneCliente) {
        this.txtTelefoneCliente = txtTelefoneCliente;
    }

    public String getTxtCelularCliente() {
        return txtCelularCliente;
    }

    public void setTxtCelularCliente(String txtCelularCliente) {
        this.txtCelularCliente = txtCelularCliente;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getTermos() {
        return termos;
    }

    public void setTermos(String termos) {
        this.termos = termos;
    }

    public String getTxtCEPCliente() {
        return txtCEPCliente;
    }

    public void setTxtCEPCliente(String txtCEPCliente) {
        this.txtCEPCliente = txtCEPCliente;
    }

    public String getTxtEndereco() {
        return txtEndereco;
    }

    public void setTxtEndereco(String txtEndereco) {
        this.txtEndereco = txtEndereco;
    }

    public String getTxtNumero() {
        return txtNumero;
    }

    public void setTxtNumero(String txtNumero) {
        this.txtNumero = txtNumero;
    }

    public String getTxtComplemento() {
        return txtComplemento;
    }

    public void setTxtComplemento(String txtComplemento) {
        this.txtComplemento = txtComplemento;
    }

    public String getTxtBairro() {
        return txtBairro;
    }

    public void setTxtBairro(String txtBairro) {
        this.txtBairro = txtBairro;
    }

    public String getTxtCidade() {
        return txtCidade;
    }

    public void setTxtCidade(String txtCidade) {
        this.txtCidade = txtCidade;
    }

    public String getTxtEstado() {
        return txtEstado;
    }

    public void setTxtEstado(String txtEstado) {
        this.txtEstado = txtEstado;
    }

    public String getTxtPais() {
        return txtPais;
    }

    public void setTxtPais(String txtPais) {
        this.txtPais = txtPais;
    }

    //    //usuario
//    private String id;
//    private String email;
//    private String senha;
//    private String nome;
//    private String sobrenome;
//    private String sexo;
//
//    //endereco
//    private String endereco;
//    private String complemento;
//    private String bairro;
//    private String cidade;
//    private String estado;
//    private String pais;
//    private String perfil;
//
//    private int cpf; // usuario
//
//    //contaato
//    private int telefone;
//    private int celular;
//
//    //endereco
//    private int cep; // Endereco
//    private int numero;
//
//    @Exclude //Usado para não salvar este campo no banco de dados do usuario
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
////    @Exclude //Usado para não salvar este campo no banco de dados do usuario
//    public String getSenha() {
//        return senha;
//    }
//
//    public void setSenha(String senha) {
//        this.senha = senha;
//    }
//
//    public String getNome() {
//        return nome;
//    }
//
//    public void setNome(String nome) {
//        this.nome = nome;
//    }
//
//    public String getSobrenome() {
//        return sobrenome;
//    }
//
//    public void setSobrenome(String sobrenome) {
//        this.sobrenome = sobrenome;
//    }
//
//    public String getSexo() {
//        return sexo;
//    }
//
//    public void setSexo(String sexo) {
//        this.sexo = sexo;
//    }
//
//    public String getEndereco() {
//        return endereco;
//    }
//
//    public void setEndereco(String endereco) {
//        this.endereco = endereco;
//    }
//
//    public String getComplemento() {
//        return complemento;
//    }
//
//    public void setComplemento(String complemento) {
//        this.complemento = complemento;
//    }
//
//    public String getBairro() {
//        return bairro;
//    }
//
//    public void setBairro(String bairro) {
//        this.bairro = bairro;
//    }
//
//    public String getCidade() {
//        return cidade;
//    }
//
//    public void setCidade(String cidade) {
//        this.cidade = cidade;
//    }
//
//    public String getEstado() {
//        return estado;
//    }
//
//    public void setEstado(String estado) {
//        this.estado = estado;
//    }
//
//    public String getPais() {
//        return pais;
//    }
//
//    public void setPais(String pais) {
//        this.pais = pais;
//    }
//
//    public int getCpf() {
//        return cpf;
//    }
//
//    public void setCpf(int cpf) {
//        this.cpf = cpf;
//    }
//
//    public int getTelefone() {
//        return telefone;
//    }
//
//    public void setTelefone(int telefone) {
//        this.telefone = telefone;
//    }
//
//    public int getCelular() {
//        return celular;
//    }
//
//    public void setCelular(int celular) {
//        this.celular = celular;
//    }
//
//    public int getCep() {
//        return cep;
//    }
//
//    public void setCep(int cep) {
//        this.cep = cep;
//    }
//
//    public int getNumero() {
//        return numero;
//    }
//
//    public void setNumero(int numero) {
//        this.numero = numero;
//    }
//
//    public String getPerfil() {
//        return perfil;
//    }
//
//    public void setPerfil(String perfil) {
//        this.perfil = perfil;
//    }
}


