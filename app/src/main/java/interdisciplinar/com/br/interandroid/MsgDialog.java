package interdisciplinar.com.br.interandroid;

import android.content.Context;
import android.support.v7.app.AlertDialog;

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
        dialog.setPositiveButton("OK", null);
        dialog.create();
        dialog.show();
    }
}
