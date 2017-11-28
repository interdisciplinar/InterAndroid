package interdisciplinar.com.br.interandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import interdisciplinar.com.br.interandroid.config.ConfiguracaoFirebase;
import interdisciplinar.com.br.interandroid.model.Empresa;
import interdisciplinar.com.br.interandroid.model.Usuario;

public class BuscaEmpresas extends AppCompatActivity {

    private Toolbar toolbar;
    private FirebaseAuth autenticacao;
    DatabaseReference referenciaFirebase;

    private List<String> nomeEmpresa = new ArrayList<String>();
    private List<Empresa> empresasList = new ArrayList<Empresa>();
    private ArrayAdapter<String> arrayAdapterEmpresa;
    private ListView ListVDados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busca_empresas);

        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();

        //ToolBar
        toolbar = (Toolbar) findViewById(R.id.toolbarTituloApp);
        toolbar.setTitle("Buscar empresas");
        setSupportActionBar(toolbar);

        ListVDados = (ListView) findViewById(R.id.teste);


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
                    empresasList.add(E);
                    nomeEmpresa.add(E.getTxtNomeEmpresa());
                }
                arrayAdapterEmpresa = new ArrayAdapter<String>(BuscaEmpresas.this, R.layout.activity_lista_empersas, nomeEmpresa);
                ListVDados.setAdapter(arrayAdapterEmpresa);
                Log.i("Renato", String.valueOf(empresasList));
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
        Intent intent = new Intent(BuscaEmpresas.this, MainActivity.class);
        startActivity(intent);
        finish();

    }
}
