package interdisciplinar.com.br.interandroid.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

import java.net.URISyntaxException;

import interdisciplinar.com.br.interandroid.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DadosPessoaisClienteFragment extends Fragment {

    private View view;
    private EditText txtPNomeCliente;
    private EditText txtSNomeCliente;
    private EditText txtCPF;
    private EditText txtTelefoneCliente;
    private EditText txtCelularCliente;
    private RadioButton rbMasculinoCliente;
    private RadioButton rbFemininoCliente;
    private EditText telefone;
    private EditText celular;
    private String Email;
    private String Senha;
    private Button btnProximo;



    public DadosPessoaisClienteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Email = getArguments().getString("Email");

        view = inflater.inflate(R.layout.fragment_dados_pessoais_cliente, container, false);

        telefone = (EditText) view.findViewById(R.id.txtTelefoneCliente);
        celular = (EditText) view.findViewById(R.id.txtCelularCliente);

        SimpleMaskFormatter simpleMaskTelefone = new SimpleMaskFormatter("(NN) NNNN-NNNN");
        MaskTextWatcher maskTelefone = new MaskTextWatcher(telefone, simpleMaskTelefone);
        telefone.addTextChangedListener(maskTelefone);

        SimpleMaskFormatter simpleMaskCelular = new SimpleMaskFormatter("(NN) NNNNN-NNNN");
        MaskTextWatcher maskCelular = new MaskTextWatcher(celular, simpleMaskCelular);
        celular.addTextChangedListener(maskCelular);

        return view;



    }

}
