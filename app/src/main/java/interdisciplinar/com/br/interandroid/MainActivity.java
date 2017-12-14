package interdisciplinar.com.br.interandroid;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import interdisciplinar.com.br.interandroid.config.ConfiguracaoFirebase;
import interdisciplinar.com.br.interandroid.helper.MsgDialog;
import interdisciplinar.com.br.interandroid.model.Empresa;
import interdisciplinar.com.br.interandroid.model.Usuario;

//import com.facebook.stetho.Stetho; //Para visualizar o banco SQLite no Google Chrome

public class MainActivity extends AppCompatActivity {
    private EditText email;
    private EditText senha;
    private Button botaoLogin;
    private Button botaoCadastrar;
    private Toolbar toolbar;
    private Usuario usuario;
    private FirebaseAuth autenticacao;
    private String tituloErro;
    private String msgErro;
    DatabaseReference referenciaFirebase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Para visualizar o banco SQLite no Google Chrome
//        Stetho.initialize(
//                Stetho.newInitializerBuilder(this)
//                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
//                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
//                        .build());
        //***********************************************************************

        verificarUsuarioLogado();

        email = (EditText) findViewById(R.id.txtEmailLogin);
        senha = (EditText) findViewById(R.id.txtSenhaLogin);
        botaoLogin = (Button) findViewById(R.id.btnLogin);
        botaoCadastrar = (Button) findViewById(R.id.btnFazerCadastro);
        toolbar = (Toolbar) findViewById(R.id.toolbarTituloApp);

        toolbar.setTitle(getString(R.string.app_name));
        setSupportActionBar(toolbar);


        botaoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                usuario = new Usuario();
                usuario.setTxtEmailCadastro(email.getText().toString());
                usuario.setTxtSenhaCadastro(senha.getText().toString());

                if (email.getText().toString().isEmpty() || senha.getText().toString().isEmpty()) {

                    tituloErro = getString(R.string.tituloErroLogin);
                    msgErro = getString(R.string.emailOuSenhaNaoPreenchido);
                    MsgDialog.msgErro(MainActivity.this, tituloErro, msgErro);

                } else {
                    validarLogin();
                }
            }
        });


        botaoCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MainActivity.this, Cadastro.class));
                finish();

            }
        });
    }

    private void verificarUsuarioLogado() {
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        if (autenticacao.getCurrentUser() != null) {
            referenciaFirebase = ConfiguracaoFirebase.getFirebase();
            referenciaFirebase.child("empresa").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    boolean  empresa = false;
                    for (DataSnapshot objSnapshot:dataSnapshot.getChildren()){
                        Empresa E = objSnapshot.getValue(Empresa.class);
                        if (autenticacao.getCurrentUser().getEmail().equalsIgnoreCase(E.getTxtEmailCadastro())){
                            empresa = true;
                        }
                    }
                    if (!empresa) {
                        abrirTelaPrincipal();
                    } else {
                        abrirTelaPrincipalEmpresa();
                    }

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
    }

    private void validarLogin() {

        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        autenticacao.signInWithEmailAndPassword(
                usuario.getTxtEmailCadastro(),
                usuario.getTxtSenhaCadastro()
        ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    referenciaFirebase = ConfiguracaoFirebase.getFirebase();
                    referenciaFirebase.child("empresa").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            boolean  empresa = false;
                            for (DataSnapshot objSnapshot:dataSnapshot.getChildren()){
                                Empresa E = objSnapshot.getValue(Empresa.class);
                                if (email.getText().toString().equalsIgnoreCase(E.getTxtEmailCadastro())){
                                    empresa = true;
                                }
                            }
                            if (!empresa) {
                                abrirTelaPrincipal();
                            } else {
                                abrirTelaPrincipalEmpresa();
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
//                    Toast.makeText(MainActivity.this, "Sucesso ao fazer Login", Toast.LENGTH_SHORT).show();
                } else {

                    tituloErro = getString(R.string.tituloErroLogin);

                    try {
                        throw task.getException();
                    } catch (FirebaseAuthInvalidUserException e) {
                        msgErro = getString(R.string.contaInexixtente);
                    } catch (FirebaseAuthInvalidCredentialsException e) {
                        msgErro = getString(R.string.senhaIncorreta);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    MsgDialog.msgErro(MainActivity.this, tituloErro, msgErro);
                }
            }
        });
    }

    private void abrirTelaPrincipal() {
        Intent intent = new Intent(MainActivity.this, HomeCliente.class);
        startActivity(intent);
        finish();
    }
    private void abrirTelaPrincipalEmpresa() {
        Intent intent = new Intent(MainActivity.this, HomeEmpresa.class);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

}