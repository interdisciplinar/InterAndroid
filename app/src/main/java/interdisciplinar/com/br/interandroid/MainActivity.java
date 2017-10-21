package interdisciplinar.com.br.interandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText Email;
    private EditText Senha;
    private String StringEmail = "";
    private String StringSenha = "";
    private Button botaoLogin;
    private Button botaoCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Email = (EditText) findViewById(R.id.txtEmailLogin);
        Senha = (EditText) findViewById(R.id.txtPWDLogin);
        botaoLogin = (Button) findViewById(R.id.btnLogin);
        botaoCadastrar = (Button) findViewById(R.id.btnCadastrar);

//        StringEmail = (String) Email.getText().toString();
//        StringSenha = Senha.getText().toString();

        botaoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Email: " + StringEmail, Toast.LENGTH_SHORT).show();

                if (Email.getText().toString().equalsIgnoreCase("Renato")) {
                    if (Senha.getText().toString().equals("senha")) {
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
}