package cl.tuserver.solemne1.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import cl.tuserver.solemne1.R;
import cl.tuserver.solemne1.enums.PosicionBarrasReactor;

public class Sumario extends AppCompatActivity {

    public Button btnAct; //Botón para actualizar
    public TextView tvTemperatura,tvNombre,tvEstado; //TextView (t°, nombre planta y estado según t°)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sumario);

        //Obtenemos la información
        Bundle datos = this.getIntent().getExtras();
        SensorTemperatura sensorTemp = (SensorTemperatura)datos.getSerializable("infoSerial");

        //Inicializamos el botón
        btnAct = findViewById(R.id.btnActualizar);
        initButtonEvents();

        //Text views
        tvTemperatura = findViewById(R.id.temperatura);
        tvNombre = findViewById(R.id.titulo);
        tvEstado = findViewById(R.id.tvEsTemp);

        //Obtener nombre planta desde strings.xml para text view
        tvNombre.setText(R.string.nombrePlanta);

        //Obtener t° de la información obtenida previamente
        tvTemperatura.setText(Double.toString(sensorTemp.getTemperaturaActual()));



        switch(PosicionBarrasReactor.getPosicionActual()) {
            case SUMERGIDO :
                if(sensorTemp.getTemperaturaActual()<35){
                    tvEstado.setText("BAJA_TEMPERATURA");
                    tvEstado.setTextColor(getResources().getColor(R.color.colorAmarillo));
                }

                if(sensorTemp.getTemperaturaActual()>=35 && sensorTemp.getTemperaturaActual()<=55){
                    tvEstado.setText("NORMAL");
                    tvEstado.setTextColor(getResources().getColor(R.color.colorVerde));
                }

                if(sensorTemp.getTemperaturaActual()>55){
                    tvEstado.setText("ALTA_TEMPERATURA");
                    tvEstado.setTextColor(getResources().getColor(R.color.colorRojo));
                }
                break;

            case SEMI_SUMERGIDO:
                if(sensorTemp.getTemperaturaActual()<55){
                    tvEstado.setText("BAJA_TEMPERATURA");
                    tvEstado.setTextColor(getResources().getColor(R.color.colorAmarillo));
                }

                if(sensorTemp.getTemperaturaActual()>=55 && sensorTemp.getTemperaturaActual()<=85){
                    tvEstado.setText("NORMAL");
                    tvEstado.setTextColor(getResources().getColor(R.color.colorVerde));
                }

                if(sensorTemp.getTemperaturaActual()>85){
                    tvEstado.setText("ALTA_TEMPERATURA");
                    tvEstado.setTextColor(getResources().getColor(R.color.colorRojo));
                }
                break;

            case SUPERFICIE:
                if(sensorTemp.getTemperaturaActual()<85){
                    tvEstado.setText("BAJA_TEMPERATURA");
                    tvEstado.setTextColor(getResources().getColor(R.color.colorAmarillo));
                }

                if(sensorTemp.getTemperaturaActual()>85 && sensorTemp.getTemperaturaActual()<=99){
                    tvEstado.setText("NORMAL");
                    tvEstado.setTextColor(getResources().getColor(R.color.colorVerde));
                }

                if(sensorTemp.getTemperaturaActual()>99){
                    tvEstado.setText("ALTA_TEMPERATURA");
                    tvEstado.setTextColor(getResources().getColor(R.color.colorRojo));
                    Toast.makeText(getApplicationContext(),
                            "¡PELIGRO! LA TEMPERATURA ACTUAL ES 100º O SUPERIOR",Toast.LENGTH_LONG);
                }
                break;

            default:
                System.out.println("Error al leer temperatura.");
        }

    }
        // Métodos para btnAct
        private void actualizarInfo(){
            finish();
            startActivity(getIntent());
        }

        private void initButtonEvents(){
            btnAct.setOnClickListener(v ->
                    actualizarInfo()
            );
        }
}
