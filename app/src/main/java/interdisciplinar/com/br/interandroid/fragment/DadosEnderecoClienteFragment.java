package interdisciplinar.com.br.interandroid.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

import interdisciplinar.com.br.interandroid.CadastroCliente;
import interdisciplinar.com.br.interandroid.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DadosEnderecoClienteFragment extends Fragment {

    private View view;
    private EditText cep;

    public DadosEnderecoClienteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_dados_endereco_cliente, container, false);

//        String s = CadastroCliente.senha;
//        String e = CadastroCliente.email;


        cep = (EditText) view.findViewById(R.id.txtCEPCliente);

        SimpleMaskFormatter simpleMaskCEP = new SimpleMaskFormatter("NNNNN-NNN");
        MaskTextWatcher maskCEP = new MaskTextWatcher(cep, simpleMaskCEP);
        cep.addTextChangedListener(maskCEP);

        return view;
    }

}
