package interdisciplinar.com.br.interandroid.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import interdisciplinar.com.br.interandroid.fragment.DadosEnderecoClienteFragment;
import interdisciplinar.com.br.interandroid.fragment.DadosEnderecoEmpresaFragment;
import interdisciplinar.com.br.interandroid.fragment.DadosPessoaisClienteFragment;
import interdisciplinar.com.br.interandroid.fragment.DadosPessoaisEmpresaFragment;

public class TabAdapterEmpresa extends FragmentPagerAdapter {

    private String[] tituloAbas = {"DADOS EMPRESA", "DADOS ENDEREÇO"};

    public TabAdapterEmpresa(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;

        switch (position) {
            case 0:
                fragment = new DadosPessoaisEmpresaFragment();
                break;
            case 1:
                fragment = new DadosEnderecoEmpresaFragment();
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
