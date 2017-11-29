package interdisciplinar.com.br.interandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;

import interdisciplinar.com.br.interandroid.config.ConfiguracaoFirebase;

public class Capa_Empresa extends AppCompatActivity {
    private Toolbar toolbar;
    private FirebaseAuth usuarioAutenticacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capa_empresa);

        usuarioAutenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();

        toolbar = (Toolbar) findViewById(R.id.toolbarTituloApp);

        toolbar.setTitle("Título do Aplicativo");
        setSupportActionBar(toolbar);
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
        usuarioAutenticacao.signOut();
        Intent intent = new Intent(Capa_Empresa.this, MainActivity.class);
        startActivity(intent);
        finish();

    }



    //RenatoTestePorra
}
