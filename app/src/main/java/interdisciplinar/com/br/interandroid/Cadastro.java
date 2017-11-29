package interdisciplinar.com.br.interandroid;


import android.content.ContentValues;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;

import interdisciplinar.com.br.interandroid.config.ConfiguracaoFirebase;
import interdisciplinar.com.br.interandroid.helper.MsgDialog;
import interdisciplinar.com.br.interandroid.model.Empresa;
import interdisciplinar.com.br.interandroid.model.Usuario;

public class Cadastro extends AppCompatActivity {

    private Toolbar toolbar;

    //Layout emailSenha
    private EditText txtEmailCadastro;
    private EditText txtSenhaCadastro;
    private EditText txtConfSenha;
    private RadioButton rbCliente;
    private RadioButton rbEmpresa;
    private Button btnProximo;
    private String perfil;

    //Layout dadosCliente
    private EditText txtPNomeCliente;
    private EditText txtSNomeCliente;
    private EditText txtCPFCliente;
    private EditText txtTelefoneCliente;
    private EditText txtCelularCliente;
    private RadioGroup radioGrupoSexoCliente;
    private RadioButton rbSexoCliente;
    private RadioButton rbMasculinoCliente;
    private RadioButton rbFemininoCliente;
    private String sexo;
    private CheckBox ckBoxTermos;
    private String termos;
    private Button btnDadosCliente;

    //Layout endereçoCliente
    private EditText txtCEPCliente;
    private EditText txtEndereco;
    private EditText txtNumero;
    private EditText txtComplemento;
    private EditText txtBairro;
    private EditText txtCidade;
    private EditText txtEstado;
    private EditText txtPais;
    private Button btnSaveCliente;

    //Lauout dadosEmpresa
    private EditText txtNomeEmpresa;
    private EditText txtNomeProprietarioEmpresa;
    private EditText txtCNPJ;
    private EditText txtTelefoneEmpresa;
    private EditText txtCelularEmpresa;
    private Button btnDadosEmpresa;

    //Lauout endereçoEmpresa
    private EditText txtCEPEmpresa;
    private EditText txtEnderecoEmpresa;
    private EditText txtNumeroEmpresa;
    private EditText txtComplementoEmpresa;
    private EditText txtBairroEmpresa;
    private EditText txtCidadeEmpresa;
    private EditText txtEstadoEmpresa;
    private EditText txtPaisEmpresa;
    private CheckBox Servico1;
    private CheckBox Servico2;
    private Button btnCadastrarEmpresa;

    private String servico1;
    private String servico2;

    //DAO
    private Usuario usuario;
    private Empresa empresa;

    //Firebase
    private FirebaseAuth autenticacao;

    //Mensagens
    public String tituloErro;
    private String msgErro;

    //Layouts
    private LinearLayout emailSenha;
    private LinearLayout dadosCliente;
    private LinearLayout enderecoCliente;
    private LinearLayout dadosEmpresa;
    private LinearLayout enderecoEmpresa;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        //instancia DAO
        usuario = new Usuario();
        empresa = new Empresa();

        //Inicia Toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbarTituloApp);
        toolbar.setTitle(R.string.app_name);
        setSupportActionBar(toolbar);

        tituloErro = getString(R.string.tituloErroCadastro);

        //Inicia a classe com o Layout emailSenha
        emailSenha = (LinearLayout) findViewById(R.id.emailSenha);
        dadosCliente = (LinearLayout) findViewById(R.id.dadosCliente);
        enderecoCliente = (LinearLayout) findViewById(R.id.enderecoCliente);
        dadosEmpresa = (LinearLayout) findViewById(R.id.dadosEmpresa);
        enderecoEmpresa = (LinearLayout) findViewById(R.id.enderecoEmpresa);

        emailSenha.setVisibility(View.VISIBLE);
        dadosCliente.setVisibility(View.INVISIBLE);
        enderecoCliente.setVisibility(View.INVISIBLE);
        dadosEmpresa.setVisibility(View.INVISIBLE);
        enderecoEmpresa.setVisibility(View.INVISIBLE);

        //Inicia dados emailSenha
        txtEmailCadastro = (EditText) findViewById(R.id.txtEmailCadastro);
        txtSenhaCadastro = (EditText) findViewById(R.id.txtSenhaCadastro);
        txtConfSenha = (EditText) findViewById(R.id.txtConfSenha);
        rbCliente = (RadioButton) findViewById(R.id.rbCliente);
        rbEmpresa = (RadioButton) findViewById(R.id.rbEmpresa);
        btnProximo = (Button) findViewById(R.id.btnProximo);

        btnProximo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Verificar se todos os campos estão preenchidos
                if (txtEmailCadastro.getText().toString().isEmpty() || txtSenhaCadastro.getText().toString().isEmpty()) {

                    msgErro = getString(R.string.emailOuSenhaNaoPreenchido);
                    MsgDialog.msgErro(Cadastro.this, tituloErro, msgErro);

                    //Verifica se as senhas digitadas são iguais
                } else if (!txtSenhaCadastro.getText().toString().equals(txtConfSenha.getText().toString())) {

                    msgErro = getString(R.string.senhasDiferentes);
                    MsgDialog.msgErro(Cadastro.this, tituloErro, msgErro);

                } else {
                    if (rbCliente.isChecked()) {
                        perfil = rbCliente.getText().toString();
                        emailSenha.setVisibility(View.INVISIBLE);
                        dadosCliente.setVisibility(View.VISIBLE);

                    } else if (rbEmpresa.isChecked()) {
                        perfil = rbEmpresa.getText().toString();
                        emailSenha.setVisibility(View.INVISIBLE);
                        dadosEmpresa.setVisibility(View.VISIBLE);

                    } else {
                        msgErro = getString(R.string.semPerfil);
                        MsgDialog.msgErro(Cadastro.this, tituloErro, msgErro);
                    }
                }
            }
        });

        //Inicia dados dadosCliente
        txtPNomeCliente = (EditText) findViewById(R.id.txtPNomeCliente);
        txtSNomeCliente = (EditText) findViewById(R.id.txtSNomeCliente);
        txtCPFCliente = (EditText) findViewById(R.id.txtCPFCliente);
        txtTelefoneCliente = (EditText) findViewById(R.id.txtTelefoneCliente);
        txtCelularCliente = (EditText) findViewById(R.id.txtCelularCliente);
        radioGrupoSexoCliente = (RadioGroup) findViewById(R.id.radioGrupoSexoCliente);
        rbMasculinoCliente = (RadioButton) findViewById(R.id.rbMasculinoCliente);
        rbFemininoCliente = (RadioButton) findViewById(R.id.rbFemininoCliente);
        ckBoxTermos = (CheckBox) findViewById(R.id.ckBoxTermos);
        btnDadosCliente = (Button) findViewById(R.id.btnDadosCliente);

        //Máscaras de telefone, celular e CPF
        SimpleMaskFormatter simpleMaskTelefone = new SimpleMaskFormatter("(NN) NNNN-NNNN");
        MaskTextWatcher maskTelefone = new MaskTextWatcher(txtTelefoneCliente, simpleMaskTelefone);
        txtTelefoneCliente.addTextChangedListener(maskTelefone);

        SimpleMaskFormatter simpleMaskCelular = new SimpleMaskFormatter("(NN) NNNNN-NNNN");
        MaskTextWatcher maskCelular = new MaskTextWatcher(txtCelularCliente, simpleMaskCelular);
        txtCelularCliente.addTextChangedListener(maskCelular);

        SimpleMaskFormatter simpleMaskCPF = new SimpleMaskFormatter("NNN.NNN.NNN-NN");
        MaskTextWatcher maskCPF = new MaskTextWatcher(txtCPFCliente, simpleMaskCPF);
        txtCPFCliente.addTextChangedListener(maskCPF);

        btnDadosCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int sexoCliente = radioGrupoSexoCliente.getCheckedRadioButtonId();

                if (txtPNomeCliente.getText().toString().isEmpty() ||
                        txtSNomeCliente.getText().toString().isEmpty() ||
                        txtCPFCliente.getText().toString().isEmpty() ||
//                        txtTelefoneCliente.getText().toString().isEmpty() ||
                        txtCelularCliente.getText().toString().isEmpty()) {

                    msgErro = getString(R.string.campoNaoPreenchido);
                    MsgDialog.msgErro(Cadastro.this, tituloErro, msgErro);

                } else if (sexoCliente < 0) {
                    msgErro = getString(R.string.semSexo);
                    MsgDialog.msgErro(Cadastro.this, tituloErro, msgErro);

                } else if (!ckBoxTermos.isChecked()) {
                    msgErro = getString(R.string.AceiteTermos);
                    MsgDialog.msgErro(Cadastro.this, tituloErro, msgErro);

                } else {
                    if (rbMasculinoCliente.isChecked()) {
                        sexo = rbMasculinoCliente.getText().toString();

                    } else {
                        sexo = rbFemininoCliente.getText().toString();
                    }
                    termos = "Aceito";
                    dadosCliente.setVisibility(View.INVISIBLE);
                    enderecoCliente.setVisibility(View.VISIBLE);
//                    Log.i("inter", sexo);
//                    Log.i("inter", termos);
//                    Log.i("inter", txtPNomeCliente.getText().toString());
//                    Log.i("inter", txtSNomeCliente.getText().toString());
//                    Log.i("inter", txtCPFCliente.getText().toString());
//                    Log.i("inter", txtTelefoneCliente.getText().toString());
//                    Log.i("inter", txtCelularCliente.getText().toString());

                }
            }
        });

        //Inicia dados enderecoCliente
        txtCEPCliente = (EditText) findViewById(R.id.txtCEPCliente);
        txtEndereco = (EditText) findViewById(R.id.txtEndereco);
        txtNumero = (EditText) findViewById(R.id.txtNumero);
        txtComplemento = (EditText) findViewById(R.id.txtComplemento);
        txtBairro = (EditText) findViewById(R.id.txtBairro);
        txtCidade = (EditText) findViewById(R.id.txtCidade);
        txtEstado = (EditText) findViewById(R.id.txtEstado);
        txtPais = (EditText) findViewById(R.id.txtPais);
        btnSaveCliente = (Button) findViewById(R.id.btnSaveCliente);

        //Máscara de CEP
        SimpleMaskFormatter simpleMaskCEP = new SimpleMaskFormatter("NNNNN-NNN");
        MaskTextWatcher maskCEP = new MaskTextWatcher(txtCEPCliente, simpleMaskCEP);
        txtCEPCliente.addTextChangedListener(maskCEP);

        btnSaveCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (txtCEPCliente.getText().toString().isEmpty() ||
                        txtEndereco.getText().toString().isEmpty() ||
                        txtNumero.getText().toString().isEmpty() ||
                        txtComplemento.getText().toString().isEmpty() ||
                        txtBairro.getText().toString().isEmpty() ||
                        txtCidade.getText().toString().isEmpty() ||
                        txtEstado.getText().toString().isEmpty() ||
                        txtPais.getText().toString().isEmpty()) {
                    msgErro = getString(R.string.campoNaoPreenchido);
                    MsgDialog.msgErro(Cadastro.this, tituloErro, msgErro);
                } else {
                    usuario.setTxtEmailCadastro(txtEmailCadastro.getText().toString());
                    usuario.setTxtSenhaCadastro(txtSenhaCadastro.getText().toString());
                    usuario.setPerfil(perfil);

                    usuario.setTxtPNomeCliente(txtPNomeCliente.getText().toString());
                    usuario.setTxtPNomeCliente(txtSNomeCliente.getText().toString());
                    usuario.setTxtCPFCliente(txtCPFCliente.getText().toString());
                    usuario.setTxtTelefoneCliente(txtTelefoneCliente.getText().toString());
                    usuario.setTxtCelularCliente(txtCelularCliente.getText().toString());
                    usuario.setSexo(sexo);
                    usuario.setTermos(termos);

                    usuario.setTxtCEPCliente(txtCEPCliente.getText().toString());
                    usuario.setTxtEndereco(txtEndereco.getText().toString());
                    usuario.setTxtNumero(txtNumero.getText().toString());
                    usuario.setTxtComplemento(txtComplemento.getText().toString());
                    usuario.setTxtBairro(txtBairro.getText().toString());
                    usuario.setTxtCidade(txtCidade.getText().toString());
                    usuario.setTxtEstado(txtEstado.getText().toString());
                    usuario.setTxtPais(txtPais.getText().toString());
                    usuario.setSexo(sexo);

                    cadastrarUsuario();
                }
            }
        });

        //Inicia dados dadosEmpresa
        txtNomeEmpresa = (EditText) findViewById(R.id.txtNomeEmpresa);
        txtNomeProprietarioEmpresa = (EditText) findViewById(R.id.txtNomeProprietarioEmpresa);
        txtCNPJ = (EditText) findViewById(R.id.txtCNPJ);
        txtTelefoneEmpresa = (EditText) findViewById(R.id.txtTelefoneEmpresa);
        txtCelularEmpresa = (EditText) findViewById(R.id.txtCelularEmpresa);
        btnDadosEmpresa = (Button) findViewById(R.id.btnDadosEmpresa);

        btnDadosEmpresa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dadosEmpresa.setVisibility(View.INVISIBLE);
                enderecoEmpresa.setVisibility(View.VISIBLE);
            }
        });

        //Inicia dados endereçoEmpresa
        txtCEPEmpresa = (EditText) findViewById(R.id.txtCEPEmpresa);
        txtEnderecoEmpresa = (EditText) findViewById(R.id.txtEnderecoEmpresa);
        txtNumeroEmpresa = (EditText) findViewById(R.id.txtNumeroEmpresa);
        txtComplementoEmpresa = (EditText) findViewById(R.id.txtComplementoEmpresa);
        txtBairroEmpresa = (EditText) findViewById(R.id.txtBairroEmpresa);
        txtCidadeEmpresa = (EditText) findViewById(R.id.txtCidadeEmpresa);
        txtEstadoEmpresa = (EditText) findViewById(R.id.txtEstadoEmpresa);
        txtPaisEmpresa = (EditText) findViewById(R.id.txtPaisEmpresa);
        Servico1 = (CheckBox) findViewById(R.id.Servico1);
        Servico2 = (CheckBox) findViewById(R.id.Servico2);
        btnCadastrarEmpresa = (Button) findViewById(R.id.btnCadastrarEmpresa);

        btnCadastrarEmpresa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                empresa.setTxtNomeEmpresa(txtNomeEmpresa.getText().toString());
                empresa.setTxtNomeProprietarioEmpresa(txtNomeProprietarioEmpresa.getText().toString());
                empresa.setTxtCNPJ(txtCNPJ.getText().toString());
                empresa.setTxtTelefoneEmpresa(txtTelefoneEmpresa.getText().toString());
                empresa.setTxtCelularEmpresa(txtCelularEmpresa.getText().toString());
                empresa.setTxtCEPEmpresa(txtCEPEmpresa.getText().toString());
                empresa.setTxtEnderecoEmpresa(txtEnderecoEmpresa.getText().toString());
                empresa.setTxtNumeroEmpresa(txtNumeroEmpresa.getText().toString());
                empresa.setTxtComplementoEmpresa(txtComplementoEmpresa.getText().toString());
                empresa.setTxtBairroEmpresa(txtBairroEmpresa.getText().toString());
                empresa.setTxtCidadeEmpresa(txtCidadeEmpresa.getText().toString());
                empresa.setTxtEstadoEmpresa(txtEstadoEmpresa.getText().toString());
                empresa.setTxtPaisEmpresa(txtPaisEmpresa.getText().toString());
                empresa.setTxtEmailCadastro(txtEmailCadastro.getText().toString());
                empresa.setTxtSenhaCadastro(txtSenhaCadastro.getText().toString());
                empresa.setPerfil(perfil);

                if (Servico1.isChecked()) {
                    servico1 = "Sim";
                } else servico1 = "Não";
                if (Servico2.isChecked()) {
                    servico2 = "Sim";
                } else servico2 = "Não";
                empresa.setServico1(servico1);

                empresa.setServico2(servico2);

                cadastrarEmpresa();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    private void cadastrarUsuario() {
        Log.i("Inter", "email: " + usuario.getTxtEmailCadastro());
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        autenticacao.createUserWithEmailAndPassword(
                usuario.getTxtEmailCadastro(),
                usuario.getTxtSenhaCadastro()
        ).addOnCompleteListener(Cadastro.this, new OnCompleteListener<AuthResult>() {


            @Override

            public void onComplete(@NonNull Task<AuthResult> task) {
                Log.i("Inter", "entra complet: ");
                if (task.isSuccessful()) {
                    Toast.makeText(Cadastro.this, getString(R.string.sucessoCadastro), Toast.LENGTH_SHORT).show();

                    FirebaseUser usuarioFirebase = task.getResult().getUser();
                    usuario.setId(usuarioFirebase.getUid());
                    usuario.salvar();
                    abrirTelaPrincipalCliente();
                } else {

                    try {
                        throw task.getException();
                    } catch (FirebaseAuthWeakPasswordException e) {
                        msgErro = getString(R.string.senhaFraca);
                    } catch (FirebaseAuthInvalidCredentialsException e) {
                        msgErro = getString(R.string.emailInvalido);
                    } catch (FirebaseAuthUserCollisionException e) {
                        msgErro = getString(R.string.usuarioExixtente);
                    } catch (Exception e) {
                        msgErro = getString(R.string.erroEfetuarCadastro);
                        e.printStackTrace();
                    }
                    MsgDialog.msgErro(Cadastro.this, tituloErro, msgErro);

                }
                Log.i("Inter", "complet: " + usuario.getTxtSenhaCadastro());

            }
        });
        Log.i("Inter", "fim: " + usuario.getTxtSenhaCadastro());
    }


    private void cadastrarEmpresa() {
        Log.i("Inter", "email: " + usuario.getTxtEmailCadastro());
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        autenticacao.createUserWithEmailAndPassword(
                empresa.getTxtEmailCadastro(),
                empresa.getTxtSenhaCadastro()
        ).addOnCompleteListener(Cadastro.this, new OnCompleteListener<AuthResult>() {


            @Override

            public void onComplete(@NonNull Task<AuthResult> task) {
                Log.i("Inter", "entra complet: ");
                if (task.isSuccessful()) {
                    Toast.makeText(Cadastro.this, getString(R.string.sucessoCadastro), Toast.LENGTH_SHORT).show();

                    FirebaseUser usuarioFirebase = task.getResult().getUser();
                    empresa.setId(usuarioFirebase.getUid());
                    empresa.salvar();
                    abrirTelaPrincipalEmpresa();
                } else {

                    try {
                        throw task.getException();
                    } catch (FirebaseAuthWeakPasswordException e) {
                        msgErro = getString(R.string.senhaFraca);
                    } catch (FirebaseAuthInvalidCredentialsException e) {
                        msgErro = getString(R.string.emailInvalido);
                    } catch (FirebaseAuthUserCollisionException e) {
                        msgErro = getString(R.string.usuarioExixtente);
                    } catch (Exception e) {
                        msgErro = getString(R.string.erroEfetuarCadastro);
                        e.printStackTrace();
                    }
                    MsgDialog.msgErro(Cadastro.this, tituloErro, msgErro);

                }

            }
        });
    }

    private void abrirTelaPrincipalCliente() {
        Intent intent = new Intent(Cadastro.this, HomeCliente.class);
        startActivity(intent);
        finish();
    }

    private void abrirTelaPrincipalEmpresa() {
        Intent intent = new Intent(Cadastro.this, HomeEmpresa.class);
        startActivity(intent);
        finish();
    }
}
