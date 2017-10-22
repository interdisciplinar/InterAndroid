package interdisciplinar.com.br.interandroid.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import interdisciplinar.com.br.interandroid.fragment.DadosEnderecoClienteFragment;
import interdisciplinar.com.br.interandroid.fragment.DadosEnderecoEmpresaFragment;
import interdisciplinar.com.br.interandroid.fragment.DadosPessoaisClienteFragment;
import interdisciplinar.com.br.interandroid.fragment.DadosPessoaisEmpresaFragment;

public class TabAdapterCliente extends FragmentPagerAdapter {

    private String[] tituloAbas = {"DADOS PESSOAIS", "DADOS DE ENDEREÇO"};

    public TabAdapterCliente(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;

        switch (position) {
            case 0:
                fragment = new DadosPessoaisClienteFragment();
                break;
            case 1:
                fragment = new DadosEnderecoClienteFragment();
                break;
        }

        return fragment;
    }

    @Override
    public int getCount() {

        return tituloAbas.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tituloAbas[position];
    }
}
