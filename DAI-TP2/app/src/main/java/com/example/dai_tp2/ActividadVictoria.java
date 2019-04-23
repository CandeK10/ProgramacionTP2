package com.example.dai_tp2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ActividadVictoria extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_victoria);

        //Declaracion de variables
        String Nombre;
        int Cantidad;
        TextView NombreJugador, CantidadJugadas;

        NombreJugador=findViewById(R.id.MensajeVictoria);
        CantidadJugadas=findViewById(R.id.MostrarJugadas);

        //Recinimos los datos del Bundle
        Bundle DatosRecibidos;
        DatosRecibidos=this.getIntent().getExtras();

        Nombre = DatosRecibidos.getString("Nombre");
        Cantidad = DatosRecibidos.getInt("Contador");

        //Seteamos los elementos de la view de Victoria
        NombreJugador.setText("Ganaste, " + Nombre + "!!!");
        CantidadJugadas.setText("Jugadas: " + Cantidad);
    }
}
