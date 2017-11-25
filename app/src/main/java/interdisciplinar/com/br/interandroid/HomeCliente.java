package interdisciplinar.com.br.interandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class HomeCliente extends AppCompatActivity {
    private ImageButton btnBuscar;
    private ImageButton btnAgendar;
    private ImageButton btnRecentes;
    private ImageButton btnMapa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_cliente);

        btnMapa = (ImageButton) findViewById(R.id.btnMapa);

        btnMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(HomeCliente.this, MapsActivity.class));
            }
        });

    }
}
