package interdisciplinar.com.br.interandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

public class Cadastro extends AppCompatActivity {

    private RadioButton cliente;
    private RadioButton empresa;
    private Button botaoCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        cliente = (RadioButton) findViewById(R.id.rbCliente);
        empresa = (RadioButton) findViewById(R.id.rbEmpresa);
        botaoCadastrar = (Button) findViewById(R.id.btnProximo);

        botaoCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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
}
