package cl.tuserver.solemne1.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;

import cl.tuserver.solemne1.R;
import cl.tuserver.solemne1.enums.PosicionBarrasReactor;

public class Control extends AppCompatActivity {

    private ImageView esquema; // Imagen
    private Switch s1, s2, s3; // Botones switch
    private SensorTemperatura sensorTemperatura;
    private Button btnSumario; //Botón que traslada a la actividad de sumario

    public Control(){
        sensorTemperatura = new SensorTemperatura();
        sensorTemperatura.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control);

        // Obtenemos esquema.
        esquema = findViewById(R.id.esquemaPlantaNuclear);
        // -

        // Estado actual desde supuesta base de datos.
        PosicionBarrasReactor.setPosicionActual(PosicionBarrasReactor.SUMERGIDO);
        // -

        // Obtencion de botones desde XML.
        s1 = findViewById(R.id.sSuperficie);
        s2 = findViewById(R.id.sSemiSumergido);
        s3 = findViewById(R.id.sSumergido);
        btnSumario = findViewById(R.id.btnSumario);
        // -

        // Crear eventos para los botones de control
        initSwitchEvents();
        initButtonEvents(); //init de el botón que se pide en el enunciado
        //-
    }

    // Inicia los eventos de los botones switch
    private void initSwitchEvents(){
        s1.setOnClickListener(v ->
            eventoSuperficie()
        );
        s2.setOnClickListener(v ->
            eventoSemiSumergido()
        );
        s3.setOnClickListener(v ->
            eventoSumergido()
        );
        // Hace click en el estado actual
        switch (PosicionBarrasReactor.getPosicionActual()){
            case SUMERGIDO:
                s3.toggle();
                break;
            case SEMI_SUMERGIDO:
                s2.toggle();
                break;
            case SUPERFICIE:
                s1.toggle();
                break;
        }
        // -
    }

    // Enciende superficie y cambia la imagen.
    private void eventoSuperficie(){
        s1.setChecked(true);
        s2.setChecked(false);
        s3.setChecked(false);
        esquema.setImageDrawable(getResources().getDrawable(R.drawable.np3));
    }

    // Enciende semi-sumergido y cambia la imagen.
    private void eventoSemiSumergido(){
        s1.setChecked(false);
        s2.setChecked(true);
        s3.setChecked(false);
        esquema.setImageDrawable(getResources().getDrawable(R.drawable.np2));
    }

    // Enciende sumergido y cambia la imagen.
    private void eventoSumergido(){
        s1.setChecked(false);
        s2.setChecked(false);
        s3.setChecked(true);
        esquema.setImageDrawable(getResources().getDrawable(R.drawable.np1));
    }


    //Inicia el evento del btnSumario
    private void initButtonEvents(){
        btnSumario.setOnClickListener(v ->
                trasladarActivity()
        );
    }

    //Traslada a Sumario enviando la información serializada
    private void trasladarActivity(){
        Intent trasladar = new Intent(this, Sumario.class);
        trasladar.putExtra("infoSerial",sensorTemperatura);
        startActivity(trasladar);
    }





}