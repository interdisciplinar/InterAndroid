package interdisciplinar.com.br.interandroid;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.database.DatabaseReference;

import interdisciplinar.com.br.interandroid.config.ConfiguracaoFirebase;
import interdisciplinar.com.br.interandroid.model.Usuario;

public class MainActivity extends AppCompatActivity {
    private EditText email;
    private EditText senha;
    private Button botaoLogin;
    private Button botaoCadastrar;
    private Toolbar toolbar;
    private Usuario usuario;
    private FirebaseAuth autenticacao;
    private AlertDialog.Builder dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        verificarUsuarioLogado();

        email = (EditText) findViewById(R.id.txtEmailLogin);
        senha = (EditText) findViewById(R.id.txtSenhaLogin);
        botaoLogin = (Button) findViewById(R.id.btnLogin);
        botaoCadastrar = (Button) findViewById(R.id.btnFazerCadastro);
        toolbar = (Toolbar) findViewById(R.id.toolbarTituloApp);

        toolbar.setTitle("Título do Aplicativo");
        setSupportActionBar(toolbar);


        botaoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                usuario = new Usuario();
                usuario.setEmail(email.getText().toString());
                usuario.setSenha(senha.getText().toString());

                if (email.getText().toString().isEmpty() || senha.getText().toString().isEmpty()) {

                    String msgErro = "Campo E-mail ou Senha não foi preenchido";
                    msgLogin(msgErro);

                } else {

                    validarLogin();

                }
            }
        });


        botaoCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MainActivity.this, Cadastro.class));
                finish();
            }
        });
    }

    private void verificarUsuarioLogado() {
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        if (autenticacao.getCurrentUser() != null) {

            abrirTelaPrincipal();
        }
    }

    private void validarLogin() {
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        autenticacao.signInWithEmailAndPassword(
                usuario.getEmail(),
                usuario.getSenha()
        ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    abrirTelaPrincipal();
                    Toast.makeText(MainActivity.this, "Sucesso ao fazer Login", Toast.LENGTH_SHORT).show();

                } else {

                    String erroExcecao = "";
                    try {
                        throw task.getException();
                    } catch (FirebaseAuthInvalidUserException e) {
                        erroExcecao = "Conta inexistente ou desativada";
                    } catch (FirebaseAuthInvalidCredentialsException e) {
                        erroExcecao = "Senha incorreta";
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    msgLogin(erroExcecao);
//                    Toast.makeText(MainActivity.this, "Erro ao fazer Login: " + erroExcecao, Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void abrirTelaPrincipal() {
        Intent intent = new Intent(MainActivity.this, Capa_Empresa.class);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    private void msgLogin(String msg) {
        //Criar alert dialog
        dialog = new AlertDialog.Builder(MainActivity.this);
        //Configurar o titulo
        dialog.setTitle("Erro ao fazer Login");
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