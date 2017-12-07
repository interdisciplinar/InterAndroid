package interdisciplinar.com.br.interandroid;

import android.app.Dialog;
import android.content.Context;
import java.util.Calendar;
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



        }

}
