package interdisciplinar.com.br.interandroid;

import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
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
