package interdisciplinar.com.br.interandroid.helper;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import interdisciplinar.com.br.interandroid.Cadastro;
import interdisciplinar.com.br.interandroid.R;

public class MsgDialog {

    public static void msgErro(Context context, String titulo, String msg) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);

        //Configurar o titulo
        dialog.setTitle(titulo);
        //Configura a mensagem
        dialog.setMessage(msg);
        //Nao deixa desaparecer a dialog se clicar fora dela
        dialog.setCancelable(false);
        //Definir icone da dialog
        dialog.setIcon(android.R.drawable.ic_delete);
        //Ação do botão OK da mensagem
        dialog.setPositiveButton(R.string.ok, null);
        dialog.create();
        dialog.show();
    }

    public static void msgErroCadastro(final Context context, String titulo, String msg) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);

        //Configurar o titulo
        dialog.setTitle(titulo);
        //Configura a mensagem
        dialog.setMessage(msg);
        //Nao deixa desaparecer a dialog se clicar fora dela
        dialog.setCancelable(false);
        //Definir icone da dialog
        dialog.setIcon(android.R.drawable.ic_delete);
        //Ação do botão OK da mensagem
        dialog.setPositiveButton(R.string.voltar, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(context, Cadastro.class);
                context.startActivity(intent);
            }
        });
        dialog.create();
        dialog.show();
    }
}
