package interdisciplinar.com.br.interandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

public class Cadastro extends AppCompatActivity {
    private RadioButton Cliente;
    private RadioButton Empresa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        Cliente = (RadioButton)findViewById(R.id.rbCliente);
        Empresa = (RadioButton)findViewById(R.id.rbEmpresa);

        Button Proximo = (Button)findViewById(R.id.btnProximo);
        Proximo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    if (Cliente.isChecked()) {
                        Intent i = new Intent(getApplicationContext(), CadastroCliente.class);
                        startActivity(i);
                    }
                    if (Empresa.isChecked()) {
                        Intent i = new Intent(getApplicationContext(), CadastroEmp.class);
                        startActivity(i);
                    }
                    if (!Cliente.isChecked() && !Empresa.isChecked()) {
                        Toast.makeText(getApplicationContext(), "Nenhum perfil foi selecionado", Toast.LENGTH_SHORT).show();

                    }

            }
        });
    }
}
