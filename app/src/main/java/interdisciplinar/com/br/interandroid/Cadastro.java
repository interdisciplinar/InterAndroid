package interdisciplinar.com.br.interandroid;


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
import interdisciplinar.com.br.interandroid.model.Usuario;

public class Cadastro extends AppCompatActivity {

    private Toolbar toolbar;
    private EditText email;
    private EditText senha;
    private EditText confirmarSenha;
    private RadioButton cliente;
    private RadioButton empresa;
    private Button botaoCadastrar;
    private Usuario usuario;
    private FirebaseAuth autenticacao;
    private String tituloErro = "Erro ao efetuar cadastro";
    private String msgErro;

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
        botaoCadastrar = (Button) findViewById(R.id.btnCadastrar);

        toolbar.setTitle("Título do Aplicativo");
        setSupportActionBar(toolbar);


        botaoCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                usuario = new Usuario();
                usuario.setEmail(email.getText().toString());
                usuario.setSenha(senha.getText().toString());

                //Verificar se todos os campos estão preenchidos
                if (email.getText().toString().isEmpty() || senha.getText().toString().isEmpty()) {

                    msgErro = "Campo E-mail ou Senha não foi preenchido";
                    MsgDialog.msgErro(Cadastro.this, tituloErro, msgErro);

                    //Verifica se as senhas digitadas são iguais
                } else if (!senha.getText().toString().equals(confirmarSenha.getText().toString())) {

                    msgErro = "Senhas digitadas não são iguais";
                    MsgDialog.msgErro(Cadastro.this, tituloErro, msgErro);

                } else {
                    if (cliente.isChecked()) {
                        cadastrarUsuario(cliente);

//                        Intent intent = new Intent(Cadastro.this, CadastroCliente.class);
//                        String Email = email.getText().toString();
//                        String Senha = senha.getText().toString();
//                        Bundle bundle = new Bundle();
//                        bundle.putString("Email", Email);
//                        bundle.putString("Senha", Senha);
//                        intent.putExtras(bundle);
//                        startActivity(intent);

                    } else if (empresa.isChecked()) {
                        cadastrarUsuario(empresa);

//                        Intent intent = new Intent(Cadastro.this, CadastroEmp.class);
//                        String Email = email.getText().toString();
//                        String Senha = senha.getText().toString();
//                        Bundle bundle = new Bundle();
//                        bundle.putString("Email", Email);
//                        bundle.putString("Senha", Senha);
//                        intent.putExtras(bundle);
//                        startActivity(intent);

                    } else {

                        msgErro = "Nenhum perfil foi selecionado";
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
                    if (perfil.equals(cliente)) {
                        Intent intent = new Intent(Cadastro.this, CadastroCliente.class);
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(Cadastro.this, CadastroEmp.class);
                        startActivity(intent);
                    }

                    Toast.makeText(Cadastro.this, "Sucesso ao cadastrar usuário", Toast.LENGTH_SHORT).show();

                    FirebaseUser usuarioFirebase = task.getResult().getUser();
                    usuario.setId(usuarioFirebase.getUid());
                    usuario.salvar();

                } else {

                    try {
                        throw task.getException();
                    } catch (FirebaseAuthWeakPasswordException e) {
                        msgErro = "Digite uma senha mais forte";
                    } catch (FirebaseAuthInvalidCredentialsException e) {
                        msgErro = "E-mail digitado inválido.";
                    } catch (FirebaseAuthUserCollisionException e) {
                        msgErro = "Usuário já existente";
                    } catch (Exception e) {
                        msgErro = "Ao efetuar cadastro";
                        e.printStackTrace();
                    }
                    MsgDialog.msgErro(Cadastro.this, tituloErro, msgErro);

                }
            }
        });
    }

}
