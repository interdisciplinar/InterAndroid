package interdisciplinar.com.br.interandroid;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.EditText;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

import interdisciplinar.com.br.interandroid.adapter.TabAdapterCliente;
import interdisciplinar.com.br.interandroid.adapter.TabAdapterEmpresa;
import interdisciplinar.com.br.interandroid.helper.SlidingTabLayout;

public class CadastroEmp extends AppCompatActivity {

    private Toolbar toolbar;
    private SlidingTabLayout slidingTabLayout;
    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_emp);

        toolbar = (Toolbar) findViewById(R.id.toolbarTituloApp);
        slidingTabLayout = (SlidingTabLayout) findViewById(R.id.tabLayoutEmpresa);
        viewPager = (ViewPager) findViewById(R.id.vpPaginaEmpresa);

        //Configurar adapter
        TabAdapterEmpresa tabAdapterEmpresa = new TabAdapterEmpresa(getSupportFragmentManager());
        viewPager.setAdapter(tabAdapterEmpresa);
        slidingTabLayout.setViewPager(viewPager);

        toolbar.setTitle("Cadastro de Empresas");
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }
}
