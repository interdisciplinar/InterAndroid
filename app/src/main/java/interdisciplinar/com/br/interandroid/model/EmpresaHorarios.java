package interdisciplinar.com.br.interandroid.model;

import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;

import interdisciplinar.com.br.interandroid.config.ConfiguracaoFirebase;

/**
 * Created by JoseRenato on 11/12/17.
 */

public class EmpresaHorarios {

    private String id;
    private String HoraInicial;
    private String HoraFinal;
    private String TempoAtendimento;

    public EmpresaHorarios(){
    }

    public void salvar() {
        DatabaseReference referenciaFirebase = ConfiguracaoFirebase.getFirebase();
        Log.i("Renato",getID());
        referenciaFirebase.child("empresaHorarios").child(getID()).setValue(this);
    }

    public String getID() {
        return id;
    }
    public void setID(String ID) {
        this.id = ID;
    }

    public String getHoraInicial() {
        return HoraInicial;
    }

    public void setHoraInicial(String horaInicial) {
        HoraInicial = horaInicial;
    }

    public String getHoraFinal() {
        return HoraFinal;
    }

    public void setHoraFinal(String horaFinal) {
        HoraFinal = horaFinal;
    }

    public String getTempoAtendimento() {
        return TempoAtendimento;
    }

    public void setTempoAtendimento(String tempoAtendimento) {
        TempoAtendimento = tempoAtendimento;
    }
}

