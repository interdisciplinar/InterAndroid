package interdisciplinar.com.br.interandroid;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.Toast;

import interdisciplinar.com.br.interandroid.adapter.TabAdapterCliente;
import interdisciplinar.com.br.interandroid.fragment.DadosEnderecoClienteFragment;
import interdisciplinar.com.br.interandroid.helper.SlidingTabLayout;


public class CadastroCliente extends AppCompatActivity {

    private Toolbar toolbar;
    private SlidingTabLayout slidingTabLayout;
    private ViewPager viewPager;
    public static String email;
    public static String senha;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_cliente);

        toolbar = (Toolbar) findViewById(R.id.toolbarTituloApp);
        slidingTabLayout = (SlidingTabLayout) findViewById(R.id.tabLayoutCliente);
        viewPager = (ViewPager) findViewById(R.id.vpPaginaCliente);

        Intent intent = getIntent();
        email = intent.getStringExtra("email");
        senha = intent.getStringExtra("senha");
        Log.i("teste",email);
        Log.i("teste",senha);


        //Configurar adapter
        TabAdapterCliente tabAdapterCliente = new TabAdapterCliente(getSupportFragmentManager());
        viewPager.setAdapter(tabAdapterCliente);
        slidingTabLayout.setViewPager(viewPager);

        toolbar.setTitle(getString(R.string.telaCadastro));
        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }
}
