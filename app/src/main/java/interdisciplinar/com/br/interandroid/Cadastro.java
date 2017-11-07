package interdisciplinar.com.br.interandroid;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthActionCodeException;
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
    private AlertDialog.Builder dialog;
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
                    msgCadastro(msgErro);

                    //Verifica se as senhas digitadas são iguais
                } else if (!senha.getText().toString().equals(confirmarSenha.getText().toString())) {

                    msgErro = "Senhas digitadas não são iguais";
                    msgCadastro(msgErro);

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
                        msgCadastro(msgErro);

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
                    String erroExcecao = "";

                    try {
                        throw task.getException();
                    } catch (FirebaseAuthWeakPasswordException e) {
                        erroExcecao = "Digite uma senha mais forte";
                    } catch (FirebaseAuthInvalidCredentialsException e) {
                        erroExcecao = "E-mail digitado inválido.";
                    } catch (FirebaseAuthUserCollisionException e) {
                        erroExcecao = "Usuário já existente";
                    } catch (Exception e) {
                        erroExcecao = "Ao efetuar cadastro";
                        e.printStackTrace();
                    }
                    msgCadastro(erroExcecao);
//                    Toast.makeText(Cadastro.this, "Erro: " + erroExcecao, Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void msgCadastro(String msg) {
        //Criar alert dialog
        dialog = new AlertDialog.Builder(Cadastro.this);
        //Configurar o titulo
        dialog.setTitle("Erro ao efetuar cadastro");
        //Configura a mensagem
        dialog.setMessage(msg);
        //Nao deixa desaparecer a dialog se clicar fora dela
        dialog.setCancelable(false);
        //Definir icone da dialog
        dialog.setIcon(android.R.drawable.ic_delete);
        //Ação do botão OK da mensagem
        dialog.setNeutralButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
//                Toast.makeText(MainActivity.this, "Campo Email ou Senha não foi preenchido", Toast.LENGTH_SHORT).show();

            }
        });
        dialog.create();
        dialog.show();
    }

}
