package interdisciplinar.com.br.interandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;

import interdisciplinar.com.br.interandroid.config.ConfiguracaoFirebase;

public class HomeCliente extends AppCompatActivity {
    private ImageButton btnBuscar;
    private ImageButton btnAgendar;
    private ImageButton btnRecentes;
    private ImageButton btnMapa;

    private Toolbar toolbar;
    private FirebaseAuth usuarioAutenticacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_cliente);

        usuarioAutenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();

        toolbar = (Toolbar) findViewById(R.id.toolbarTituloApp);

        toolbar.setTitle("Título do Aplicativo");
        setSupportActionBar(toolbar);

        btnMapa = (ImageButton) findViewById(R.id.btnMapa);
        btnMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(HomeCliente.this, MapsActivity.class));
            }
        });

        btnBuscar = (ImageButton) findViewById(R.id.btnBuscar);
        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeCliente.this, BuscaEmpresas.class));
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
        usuarioAutenticacao.signOut();
        Intent intent = new Intent(HomeCliente.this, MainActivity.class);
        startActivity(intent);
        finish();

    }
}
