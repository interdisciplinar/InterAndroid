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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button Login = (Button)findViewById(R.id.btnLogin);

        Email = (EditText) findViewById(R.id.txtEmail);
        Senha = (EditText) findViewById(R.id.txtPWDLogin);

//        StringEmail = (String) Email.getText().toString();
//        StringSenha = Senha.getText().toString();

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Email: " + StringEmail, Toast.LENGTH_SHORT).show();

                if (Email.getText().toString().equalsIgnoreCase("Renato")){
                    if (Senha.getText().toString().equals("senha")){
                        Toast.makeText(getApplicationContext(), "Autenticou", Toast.LENGTH_SHORT).show();
                    }
                    else Toast.makeText(getApplicationContext(), "Senha Incorreta", Toast.LENGTH_SHORT).show();
                }
                else Toast.makeText(getApplicationContext(), "Email não encontrado", Toast.LENGTH_SHORT).show();
            }
        });


        Button Cadastrar = (Button)findViewById(R.id.btnCadastrar);
        Cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),Cadastro.class);
                startActivity(i);
                finish();
            }
        });

    }
}