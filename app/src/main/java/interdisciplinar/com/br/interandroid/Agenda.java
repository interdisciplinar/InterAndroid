package interdisciplinar.com.br.interandroid;

import android.content.Context;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

public class Agenda extends AppCompatActivity {
        private Context context;

        private Context getContext(){
            return context;
        }
        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        protected void onCreate(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_agenda);
            this.context = getApplicationContext();
            DatePicker datepicker = (DatePicker)findViewById(R.id.Calendar);

            Calendar datainicio = Calendar.getInstance();
            datepicker.init(
                    datainicio.get(Calendar.YEAR),
                    datainicio.get(Calendar.MONTH),
                    datainicio.get(Calendar.DAY_OF_MONTH),
                    new DatePicker.OnDateChangedListener(){
                        @Override
                        public void onDateChanged(DatePicker view,
                                                  int ano, int mesDoAno,int diaDoMes) {
                            Toast.makeText(Agenda.this,
                                    "Ano: " + ano + "\n" +
                                            "Mes: " + mesDoAno + "\n" +
                                            "Dia: " + diaDoMes, Toast.LENGTH_LONG).show();
                        }});

            Button verificarData = (Button)findViewById(R.id.btnProximo);
            verificarData.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DatePicker datepicker = (DatePicker)findViewById(R.id.Calendar);
                    Toast.makeText(Agenda.this, "Data Selecionada " + datepicker.getDayOfMonth() + "/ "+ datepicker.getMonth() + "/"+datepicker.getYear(), Toast.LENGTH_LONG).show();
                    Log.i("teste","teste2");
                }
            });
        }

//TESTE JEAN







}
