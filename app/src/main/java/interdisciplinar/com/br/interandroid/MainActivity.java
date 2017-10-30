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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import interdisciplinar.com.br.interandroid.config.ConfiguracaoFirebase;
import interdisciplinar.com.br.interandroid.model.Usuario;

public class MainActivity extends AppCompatActivity {
    private EditText email;
    private EditText senha;
    private String StringEmail = "";
    private String StringSenha = "";
    private Button botaoLogin;
    private Button botaoCadastrar;
    private Toolbar toolbar;
    private Usuario usuario;
    private FirebaseAuth autenticacao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = (EditText) findViewById(R.id.txtEmailLogin);
        senha = (EditText) findViewById(R.id.txtSenhaLogin);
        botaoLogin = (Button) findViewById(R.id.btnLogin);
        botaoCadastrar = (Button) findViewById(R.id.btnFazerCadastro);
        toolbar = (Toolbar) findViewById(R.id.toolbarTituloApp);

        toolbar.setTitle("Título do Aplicativo");
        setSupportActionBar(toolbar);

//        StringEmail = (String) Email.getText().toString();
//        StringSenha = Senha.getText().toString();

        botaoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                usuario = new Usuario();
                usuario.setEmail(email.getText().toString());
                usuario.setSenha(senha.getText().toString());
                validarLogin();


                Toast.makeText(getApplicationContext(), "Email: " + StringEmail, Toast.LENGTH_SHORT).show();

                if (email.getText().toString().equalsIgnoreCase("Renato")) {
                    if (senha.getText().toString().equals("senha")) {
                        Toast.makeText(getApplicationContext(), "Autenticou", Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(getApplicationContext(), "Senha Incorreta", Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(getApplicationContext(), "Email não encontrado", Toast.LENGTH_SHORT).show();
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

    private void validarLogin() {
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        autenticacao.signInWithEmailAndPassword(
                usuario.getEmail(),
                usuario.getSenha()
        ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    Toast.makeText(MainActivity.this, "Sucesso ao fazer Login", Toast.LENGTH_SHORT).show();

                } else {
                    //Necessário fazer a tratativa das exceções de erro de Login
                    Toast.makeText(MainActivity.this, "Erro ao fazer Login", Toast.LENGTH_SHORT).show();
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
}