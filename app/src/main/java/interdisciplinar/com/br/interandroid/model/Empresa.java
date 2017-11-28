package interdisciplinar.com.br.interandroid.model;

import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;

import interdisciplinar.com.br.interandroid.config.ConfiguracaoFirebase;

/**
 * Created by JoseRenato on 23/11/17.
 */

public class Empresa {

    private String id;
    //Layout emailSenha
    private String txtEmailCadastro;
    private String txtSenhaCadastro;
    private String perfil;

    //Lauout dadosEmpresa
    private String txtNomeEmpresa;
    private String txtNomeProprietarioEmpresa;
    private String txtCNPJ;
    private String txtTelefoneEmpresa;
    private String txtCelularEmpresa;

    //Lauout endereçoEmpresa
    private String txtCEPEmpresa;
    private String txtEnderecoEmpresa;
    private String txtNumeroEmpresa;
    private String txtComplementoEmpresa;
    private String txtBairroEmpresa;
    private String txtCidadeEmpresa;
    private String txtEstadoEmpresa;
    private String txtPaisEmpresa;
    private String Servico1;
    private String Servico2;


    public Empresa() {
    }
    public void salvar() {
        DatabaseReference referenciaFirebase = ConfiguracaoFirebase.getFirebase();
        referenciaFirebase.child("empresa").child(getId()).setValue(this);
    }
    @Exclude
    public String getId() {
        return id;
    }
    @Exclude
    public void setId(String id) {
        this.id = id;
    }

    public String getTxtEmailCadastro() {
        return txtEmailCadastro;

    }

    public void setTxtEmailCadastro(String txtEmailCadastro) {
        this.txtEmailCadastro = txtEmailCadastro;
    }
    @Exclude
    public String getTxtSenhaCadastro() {
        return txtSenhaCadastro;
    }
    @Exclude
    public void setTxtSenhaCadastro(String txtSenhaCadastro) {
        this.txtSenhaCadastro = txtSenhaCadastro;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public String getTxtNomeEmpresa() {
        return txtNomeEmpresa;
    }

    public void setTxtNomeEmpresa(String txtNomeEmpresa) {
        this.txtNomeEmpresa = txtNomeEmpresa;
    }

    public String getTxtNomeProprietarioEmpresa() {
        return txtNomeProprietarioEmpresa;
    }

    public void setTxtNomeProprietarioEmpresa(String txtNomeProprietarioEmpresa) {
        this.txtNomeProprietarioEmpresa = txtNomeProprietarioEmpresa;
    }

    public String getTxtCNPJ() {
        return txtCNPJ;
    }

    public void setTxtCNPJ(String txtCNPJ) {
        this.txtCNPJ = txtCNPJ;
    }

    public String getTxtTelefoneEmpresa() {
        return txtTelefoneEmpresa;
    }

    public void setTxtTelefoneEmpresa(String txtTelefoneEmpresa) {
        this.txtTelefoneEmpresa = txtTelefoneEmpresa;
    }

    public String getTxtCelularEmpresa() {
        return txtCelularEmpresa;
    }

    public void setTxtCelularEmpresa(String txtCelularEmpresa) {
        this.txtCelularEmpresa = txtCelularEmpresa;
    }

    public String getTxtCEPEmpresa() {
        return txtCEPEmpresa;
    }

    public void setTxtCEPEmpresa(String txtCEPEmpresa) {
        this.txtCEPEmpresa = txtCEPEmpresa;
    }

    public String getTxtEnderecoEmpresa() {
        return txtEnderecoEmpresa;
    }

    public void setTxtEnderecoEmpresa(String txtEnderecoEmpresa) {
        this.txtEnderecoEmpresa = txtEnderecoEmpresa;
    }

    public String getTxtNumeroEmpresa() {
        return txtNumeroEmpresa;
    }

    public void setTxtNumeroEmpresa(String txtNumeroEmpresa) {
        this.txtNumeroEmpresa = txtNumeroEmpresa;
    }

    public String getTxtComplementoEmpresa() {
        return txtComplementoEmpresa;
    }

    public void setTxtComplementoEmpresa(String txtComplementoEmpresa) {
        this.txtComplementoEmpresa = txtComplementoEmpresa;
    }

    public String getTxtBairroEmpresa() {
        return txtBairroEmpresa;
    }

    public void setTxtBairroEmpresa(String txtBairroEmpresa) {
        this.txtBairroEmpresa = txtBairroEmpresa;
    }

    public String getTxtCidadeEmpresa() {
        return txtCidadeEmpresa;
    }

    public void setTxtCidadeEmpresa(String txtCidadeEmpresa) {
        this.txtCidadeEmpresa = txtCidadeEmpresa;
    }

    public String getTxtEstadoEmpresa() {
        return txtEstadoEmpresa;
    }

    public void setTxtEstadoEmpresa(String txtEstadoEmpresa) {
        this.txtEstadoEmpresa = txtEstadoEmpresa;
    }

    public String getTxtPaisEmpresa() {
        return txtPaisEmpresa;
    }

    public void setTxtPaisEmpresa(String txtPaisEmpresa) {
        this.txtPaisEmpresa = txtPaisEmpresa;
    }

    public String getServico1() {
        return Servico1;
    }

    public void setServico1(String servico1) {
        Servico1 = servico1;
    }

    public String getServico2() {
        return Servico2;
    }

    public void setServico2(String servico2) {
        Servico2 = servico2;
    }
}
