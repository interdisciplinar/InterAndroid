package interdisciplinar.com.br.interandroid;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import interdisciplinar.com.br.interandroid.config.ConfiguracaoFirebase;
import interdisciplinar.com.br.interandroid.model.Empresa;

public class Capa_Empresa extends AppCompatActivity {
    private Toolbar toolbar;
    private FirebaseAuth autenticacao;
    DatabaseReference referenciaFirebase;
    private String empresaNome;
    private List<Empresa> empresasList = new ArrayList<Empresa>();
    private ArrayAdapter<String> arrayAdapterEmpresa;
    private List<String> nomeEmpresa = new ArrayList<String>();

    private TextView NomeEmpresa;
    private TextView ProprietarioEmpresa;
    private TextView TelefoneEmpresa;
    private TextView EnderecoEmpresa;
    private Button btnAgenda;
    private String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capa_empresa);

        NomeEmpresa = (TextView) findViewById(R.id.NomeEmpresa);
        ProprietarioEmpresa = (TextView) findViewById(R.id.ProprietarioEmpresa);
        TelefoneEmpresa = (TextView) findViewById(R.id.TelefoneEmpresa);
        EnderecoEmpresa = (TextView) findViewById(R.id.EnderecoEmpresa);


        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();

        toolbar = (Toolbar) findViewById(R.id.toolbarTituloApp);

        toolbar.setTitle("Título do Aplicativo");
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        empresaNome = intent.getStringExtra("nomeEmpresa");

        eventoDataBase();

        btnAgenda  = (Button) findViewById(R.id.btnAgenda);
        btnAgenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Capa_Empresa.this, Agenda.class);
                //startActivity(intent);

                final Dialog dialog = new Dialog(Capa_Empresa.this);
                dialog.setContentView(R.layout.activity_agenda);
                dialog.setTitle("Calendario");

                DatePicker datePicker = (DatePicker) dialog.findViewById(R.id.Calendar);


                Calendar datainicio = Calendar.getInstance();
                datePicker.init(
                        datainicio.get(Calendar.YEAR),
                        datainicio.get(Calendar.MONTH),
                        datainicio.get(Calendar.DAY_OF_MONTH),
                        new DatePicker.OnDateChangedListener(){
                            @Override
                            public void onDateChanged(DatePicker view,
                                                      int ano, int mesDoAno,int diaDoMes) {
//                                Toast.makeText(Capa_Empresa.this,
//                                        "Ano: " + ano + "\n" +
//                                                "Mes: " + mesDoAno + "\n" +
//                                                "Dia: " + diaDoMes, Toast.LENGTH_LONG).show();
                                data = diaDoMes + "/"+ mesDoAno +"/"+ ano;
                                dialog.dismiss();
                            }});
                dialog.show();

            }
        });
    }

    private void eventoDataBase() {
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        referenciaFirebase = ConfiguracaoFirebase.getFirebase();
        referenciaFirebase.child("empresa").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                empresasList.clear();
                for (DataSnapshot objSnapshot:dataSnapshot.getChildren()){
                    Empresa E = objSnapshot.getValue(Empresa.class);

                    if (E.getTxtNomeEmpresa().equalsIgnoreCase(empresaNome)){

                        NomeEmpresa.setText(E.getTxtNomeEmpresa());
                        ProprietarioEmpresa.setText(getString(R.string.Proprietario)+E.getTxtNomeProprietarioEmpresa());
                        TelefoneEmpresa.setText(E.getTxtTelefoneEmpresa());
                        EnderecoEmpresa.setText(E.getTxtEnderecoEmpresa()+" , "+E.getTxtNumeroEmpresa()+" , "
                                +E.getTxtBairroEmpresa()+" , "+E.getTxtCidadeEmpresa()+"/"+E.getTxtEstadoEmpresa());
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.item_sair:
                deslogarUsuario();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void deslogarUsuario(){
        autenticacao.signOut();
        Intent intent = new Intent(Capa_Empresa.this, MainActivity.class);
        startActivity(intent);
        finish();

    }

}
