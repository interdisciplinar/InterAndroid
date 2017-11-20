package interdisciplinar.com.br.interandroid.fragment;

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
    private EditText nome;
    private EditText sobrenome;
    private EditText cpf;
    private EditText telefone;
    private EditText celular;
    private RadioButton masculino;
    private RadioButton feminino;
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

        nome = (EditText) view.findViewById(R.id.txtPNomeCliente);
        sobrenome = (EditText) view.findViewById(R.id.txtSNomeCliente);
        cpf = (EditText) view.findViewById(R.id.txtCPFCliente);
        telefone = (EditText) view.findViewById(R.id.txtTelefoneCliente);
        celular = (EditText) view.findViewById(R.id.txtCelularCliente);
        masculino = (RadioButton) view.findViewById(R.id.rbMasculinoCliente);
        feminino = (RadioButton) view.findViewById(R.id.rbFemininoCliente);

        SimpleMaskFormatter simpleMaskTelefone = new SimpleMaskFormatter("(NN) NNNN-NNNN");
        MaskTextWatcher maskTelefone = new MaskTextWatcher(telefone, simpleMaskTelefone);
        telefone.addTextChangedListener(maskTelefone);

        SimpleMaskFormatter simpleMaskCelular = new SimpleMaskFormatter("(NN) NNNNN-NNNN");
        MaskTextWatcher maskCelular = new MaskTextWatcher(celular, simpleMaskCelular);
        celular.addTextChangedListener(maskCelular);

        SimpleMaskFormatter simpleMaskCPF = new SimpleMaskFormatter("NNN.NNN.NNN-NN");
        MaskTextWatcher maskCPF = new MaskTextWatcher(cpf, simpleMaskCPF);
        cpf.addTextChangedListener(maskCPF);


        return view;
    }

}
