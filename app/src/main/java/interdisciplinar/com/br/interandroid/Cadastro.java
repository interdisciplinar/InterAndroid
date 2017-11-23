package interdisciplinar.com.br.interandroid;


import android.content.ContentValues;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;

import interdisciplinar.com.br.interandroid.config.ConfiguracaoFirebase;
import interdisciplinar.com.br.interandroid.fragment.DadosPessoaisClienteFragment;
import interdisciplinar.com.br.interandroid.helper.MsgDialog;
import interdisciplinar.com.br.interandroid.model.Usuario;

public class Cadastro extends AppCompatActivity {

    private Toolbar toolbar;
    private EditText email;
    private EditText senha;
    private EditText confirmarSenha;
    private RadioButton cliente;
    private RadioButton empresa;
    private Button btnProximo;
    private Usuario usuario;
    private FirebaseAuth autenticacao;
    public String tituloErro;
    private String msgErro;
    private LinearLayout Pagina1;
    private LinearLayout Pagina2;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        toolbar = (Toolbar) findViewById(R.id.toolbarTituloApp);
        email = (EditText) findViewById(R.id.txtEmailCadastro);
        senha = (EditText) findViewById(R.id.txtSenhaCadastro);
        confirmarSenha = (EditText) findViewById(R.id.txtConfSenha);
        cliente = (RadioButton) findViewById(R.id.rbCliente);
        empresa = (RadioButton) findViewById(R.id.rbEmpresa);
        btnProximo = (Button) findViewById(R.id.btnProximo);

        toolbar.setTitle(R.string.app_name);
        setSupportActionBar(toolbar);
        tituloErro = getString(R.string.tituloErroCadastro);

        Pagina1 = (LinearLayout) findViewById(R.id.Pagina1);
        Pagina2 = (LinearLayout) findViewById(R.id.Pagina2);

        Pagina1.setVisibility(View.VISIBLE);
        Pagina2.setVisibility(View.INVISIBLE);
        btnProximo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Pagina1.setVisibility(View.INVISIBLE);
                Pagina2.setVisibility(View.VISIBLE);

                usuario = new Usuario();
                usuario.setEmail(email.getText().toString());
                usuario.setSenha(senha.getText().toString());

                //Verificar se todos os campos estão preenchidos
                if (email.getText().toString().isEmpty() || senha.getText().toString().isEmpty()) {

                    msgErro = getString(R.string.emailOuSenhaNaoPreenchido);
                    MsgDialog.msgErro(Cadastro.this, tituloErro, msgErro);

                    //Verifica se as senhas digitadas são iguais
                } else if (!senha.getText().toString().equals(confirmarSenha.getText().toString())) {

                    msgErro = getString(R.string.senhasDiferentes);
                    MsgDialog.msgErro(Cadastro.this, tituloErro, msgErro);

                } else {
                    if (cliente.isChecked()) {

                        cadastrarUsuario(cliente);

                    } else if (empresa.isChecked()) {

                        cadastrarUsuario(empresa);

                    } else {

                        msgErro = getString(R.string.semPerfil);
                        MsgDialog.msgErro(Cadastro.this, tituloErro, msgErro);

                    }
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    private void cadastrarUsuario(final RadioButton perfil) {

        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        autenticacao.createUserWithEmailAndPassword(
                usuario.getEmail(),
                usuario.getSenha()
        ).addOnCompleteListener(Cadastro.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    Bundle bundle = new Bundle();
                    bundle.putString("email", email.getText().toString());
                    bundle.putString("senha", senha.getText().toString());
                    bundle.putString("perfil", perfil.getText().toString());

                    if (perfil.equals(cliente)) {
                        Intent intent = new Intent(Cadastro.this, CadastroCliente.class);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        finish();
                    } else {
                        Intent intent = new Intent(Cadastro.this, CadastroEmp.class);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        finish();
                    }

                    Toast.makeText(Cadastro.this, getString(R.string.sucessoCadastro), Toast.LENGTH_SHORT).show();

                    FirebaseUser usuarioFirebase = task.getResult().getUser();
                    usuario.setId(usuarioFirebase.getUid());
                    usuario.salvar();

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

}
