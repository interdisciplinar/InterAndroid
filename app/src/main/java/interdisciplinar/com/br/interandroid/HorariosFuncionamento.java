package interdisciplinar.com.br.interandroid;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;

import interdisciplinar.com.br.interandroid.config.ConfiguracaoFirebase;
import interdisciplinar.com.br.interandroid.helper.MsgDialog;
import interdisciplinar.com.br.interandroid.model.Empresa;
import interdisciplinar.com.br.interandroid.model.EmpresaHorarios;

public class HorariosFuncionamento extends AppCompatActivity {

    private Toolbar toolbar;

    private EditText HoraInicial;
    private EditText HoraFinal;
    private EditText TempoAtendimento;
    private Button btnSalvar;
    //Firebase
    private FirebaseAuth autenticacao;

    //DAO
    private EmpresaHorarios empresaHorarios;
    private Empresa empresa;

    //Mensagens
    public String tituloErro;
    private String msgErro;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horarios_funcionamento);

        tituloErro = getString(R.string.tituloErroCadastro);

        //Inicia Toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbarTituloApp);
        toolbar.setTitle(R.string.app_name);
        setSupportActionBar(toolbar);

        empresaHorarios = new EmpresaHorarios();
        empresa = new Empresa();

        btnSalvar = (Button) findViewById(R.id.btnSalvar);
        HoraInicial = (EditText) findViewById(R.id.txtHoraInicial);
        HoraFinal = (EditText) findViewById(R.id.txtHoraFinal);
        TempoAtendimento = (EditText) findViewById(R.id.txtTempoAtendimento);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                empresaHorarios.setHoraFinal(HoraFinal.getText().toString());
                empresaHorarios.setHoraInicial(HoraInicial.getText().toString());
                empresaHorarios.setTempoAtendimento(TempoAtendimento.getText().toString());

//                empresaHorarios.salvar();
                cadastrarEmpresaHorarios();
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }


    private void cadastrarEmpresaHorarios() {
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        Log.i("Renato", String.valueOf(autenticacao.getCurrentUser().getUid()));
        empresaHorarios.setID(autenticacao.getCurrentUser().getUid());

                    empresaHorarios.salvar();



    }
}
