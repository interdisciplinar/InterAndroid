package interdisciplinar.com.br.interandroid.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import interdisciplinar.com.br.interandroid.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DadosEnderecoEmpresaFragment extends Fragment {


    public DadosEnderecoEmpresaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dados_endereco_empresa, container, false);
    }

}
