package interdisciplinar.com.br.interandroid;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;

import interdisciplinar.com.br.interandroid.adapter.TabAdapterCliente;
import interdisciplinar.com.br.interandroid.helper.SlidingTabLayout;


public class CadastroCliente extends AppCompatActivity {

    private Toolbar toolbar;
    private SlidingTabLayout slidingTabLayout;
    private ViewPager viewPager;
 //   private EditText telefone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_cliente);

        toolbar = (Toolbar) findViewById(R.id.toolbarTituloApp);
        slidingTabLayout = (SlidingTabLayout) findViewById(R.id.tabLayoutCliente);
        viewPager = (ViewPager) findViewById(R.id.vpPaginaCliente);
//        telefone = (EditText) findViewById(R.id.txtTelefone);

        //Configurar adapter
        TabAdapterCliente tabAdapterCliente = new TabAdapterCliente(getSupportFragmentManager());
        viewPager.setAdapter(tabAdapterCliente);
        slidingTabLayout.setViewPager(viewPager);

        toolbar.setTitle("Cadastro de Clientes");
        setSupportActionBar(toolbar);

//        SimpleMaskFormatter simpleMaskTelefone = new SimpleMaskFormatter("NNNNN-NNNN");
//        MaskTextWatcher maskTelefone = new MaskTextWatcher(telefone, simpleMaskTelefone);
//        telefone.addTextChangedListener(maskTelefone);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }
}
