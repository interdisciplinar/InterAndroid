package interdisciplinar.com.br.interandroid;

import android.content.Intent;
import android.content.SharedPreferences;
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

import interdisciplinar.com.br.interandroid.config.ConfiguracaoFirebase;
import interdisciplinar.com.br.interandroid.model.Usuario;

public class Cadastro extends AppCompatActivity {

    private Toolbar toolbar;
    private RadioButton cliente;
    private RadioButton empresa;
    private EditText email;
    private EditText senha;
    private EditText confirmarSenha;
    private Button botaoCadastrar;
    private Usuario usuario;
    private FirebaseAuth autenticacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
                cadastrarUsuario();


                if (cliente.isChecked()) {

                    startActivity(new Intent(Cadastro.this, CadastroCliente.class));

                }
                if (empresa.isChecked()) {

                    startActivity(new Intent(Cadastro.this, CadastroEmp.class));

                }
                if (!cliente.isChecked() && !empresa.isChecked()) {

                    Toast.makeText(getApplicationContext(), "Nenhum perfil foi selecionado", Toast.LENGTH_SHORT).show();

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

    private void cadastrarUsuario() {

        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        autenticacao.createUserWithEmailAndPassword(usuario.getEmail(), usuario.getSenha())
                .addOnCompleteListener(Cadastro.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            Toast.makeText(Cadastro.this, "Sucesso ao cadastrar usuário", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Cadastro.this, "Erro ao cadastrar usuário", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }
}
