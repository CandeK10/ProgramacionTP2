package com.example.dai_tp2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import java.lang.reflect.Array;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    //Array de botones, declarado globalmente
    ImageButton[] ArrayBotones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Inicializamos el array de botones al comenzar la aplicación.
        ArrayBotones = new ImageButton[9];
        //Cada elemento del array tiene un boton asignado por id.
        ArrayBotones[0] = (ImageButton) findViewById(R.id.Boton1);
        ArrayBotones[1] = (ImageButton) findViewById(R.id.Boton2);
        ArrayBotones[2] = (ImageButton) findViewById(R.id.Boton3);
        ArrayBotones[3] = (ImageButton) findViewById(R.id.Boton4);
        ArrayBotones[4] = (ImageButton) findViewById(R.id.Boton5);
        ArrayBotones[5] = (ImageButton) findViewById(R.id.Boton6);
        ArrayBotones[6] = (ImageButton) findViewById(R.id.Boton7);
        ArrayBotones[7] = (ImageButton) findViewById(R.id.Boton8);
        ArrayBotones[8] = (ImageButton) findViewById(R.id.Boton9);

        //declaro contador para validar que no sean iguales
        int contNumerosRandom =0;
        while (contNumerosRandom == 0 || contNumerosRandom == 9){
            contNumerosRandom=0;
            //recorro el vector y le asigno a cada ImageButton una imagen
            for (int i = 0; i < 9; i++) {
                int NumeroEntre0y1 = RandomEntre0y1();
                contNumerosRandom += NumeroEntre0y1;
                AsignarImagen(ArrayBotones[i],NumeroEntre0y1);
            }
        }
    }

    public void Jugar(View VistaRecibida){
        //Recorremos el vector de botones para que se puedan clickear.
        for(int i = 0; i < 9; i++){
            ArrayBotones[i].setClickable(true);
        }
    }

    public void Jugada(View VistaRecibida){
        //Declaramos la variable para el Boton Presionado
        ImageButton BotonPresionado;
        BotonPresionado=(ImageButton)VistaRecibida;

        //Variable para el tag de ese boton, lo obtenemos y lo convertimos en tipo int.
        String TagBotonPresionado;
        TagBotonPresionado = BotonPresionado.getTag().toString();
        int NumeroBotonPresionado;
        NumeroBotonPresionado=Integer.parseInt(TagBotonPresionado);

        //LLamamos a la funcion encargada de invertir el color del boton, enviandole el respectivo numero

    }

    public int RandomEntre0y1()
    {
        //Declaro una variable de tipo random
        Random NumeroRandom;
        NumeroRandom= new Random();

        //le asigno un numero random entre 0 y 1
        int NumeroEntre0y1;
        NumeroEntre0y1=NumeroRandom.nextInt(2);

        //returneo
        return NumeroEntre0y1;
    }

    public void AsignarImagen(ImageButton Boton, int NumeroEntre0y1){
        //Dependiendo del numero que venga, le asigno una imagen al boton
        if(NumeroEntre0y1==0){
            Boton.setImageResource(R.drawable.cuadazul);
        }
        else{
            Boton.setImageResource(R.drawable.cuadceleste);
        }
    }

    public void InvertirImageButton(ImageButton Boton)
    {



    }
}
