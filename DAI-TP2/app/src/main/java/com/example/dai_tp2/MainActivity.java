package com.example.dai_tp2;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.*;

public class MainActivity extends AppCompatActivity {
    //Array de botones, declarado globalmente
    ImageButton[] ArrayBotones;
    //Inicializamos un contador de jugadas
    int ContJugadas;

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
        //Inicializamos la variable para el Nombre ingresado en pantalla.
        EditText Nombre = findViewById(R.id.Nombre);
        String NombreObtenido = Nombre.getText().toString();
        //Verificamos que no venga nulo.
        if(NombreObtenido.length()!=0)
        {
            //En caso que no lo sea, recorremos el vector de botones para que se puedan clickear.
            for(int i=0; i<9; i++)
            {
                ImageButton Boton = ArrayBotones[i];
                Boton.setVisibility(View.VISIBLE);
            }
            //Se reinicia el contador de Jugadas
            ContJugadas=0;
        }
    }

    public void Jugada(View VistaRecibida) {
        //Se añade una jugada al contador
        ContJugadas++;

        //Declaramos la variable para el Boton Presionado
        ImageButton BotonPresionado;
        BotonPresionado = (ImageButton) VistaRecibida;

        //Variable para el tag de ese boton, lo obtenemos y lo convertimos en tipo int.
        String TagBotonPresionado;
        TagBotonPresionado = BotonPresionado.getTag().toString();
        int NumeroBotonPresionado;
        NumeroBotonPresionado = Integer.parseInt(TagBotonPresionado);

        //Llamamos a la funcion encargada de traer todos los botones que deben cambiar de color
        List<Integer> BotonesACambiar = new ArrayList<Integer>();
        BotonesACambiar=BotonesACambiar(NumeroBotonPresionado);
        
        //Llamamos a la funcion encargada de invertir la imagen segun el numero del boton por la cantidad de numeros que haya en nuestra lista
        for (int numero:BotonesACambiar)
        {
            InvertirImageButton(numero);
        }

        //Verificamos si ganó o no con una funcion, devuelve un boolean
        boolean Ganado = Ganar();
        if (Ganado==true)
        {
            //Ganamos!!! Nos vamos a otra activity

            //Enviamos los datos correspondientes
            EditText Nombre=findViewById(R.id.Nombre);

            Bundle PaqueteDeDatos;
            PaqueteDeDatos = new Bundle();

            PaqueteDeDatos.putString("Nombre", Nombre.getText().toString());
            PaqueteDeDatos.putInt("Contador", ContJugadas);

            Intent ActividadDestino;
            ActividadDestino=new Intent(MainActivity.this, ActividadVictoria.class);
            ActividadDestino.putExtras(PaqueteDeDatos);

            startActivity(ActividadDestino);
        }
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

    public void InvertirImageButton(int NumeroBoton)
    {
        //Obtenemos el codigo de la imagen que tiene el botoncito
        Drawable.ConstantState CodigoImagenBoton;
        CodigoImagenBoton = ArrayBotones[NumeroBoton].getDrawable().getConstantState();

        //Obtengo el codigo de una de las imagenes
        Drawable.ConstantState CodigoImagen1;
        CodigoImagen1= ContextCompat.getDrawable(this, R.drawable.cuadceleste).getConstantState();

        //Si la imagen que tiene el boton es la celeste, le asigno la azul, en caso contrario, le asigno la celeste
        if(CodigoImagen1==CodigoImagenBoton){
            ArrayBotones[NumeroBoton].setImageResource(R.drawable.cuadazul);
        }
        else{
            ArrayBotones[NumeroBoton].setImageResource(R.drawable.cuadceleste);
        }
    }

    public List<Integer> BotonesACambiar (Integer NumeroBoton) {
        //Inicializamos un ArrayList
        List<Integer> ListaBotones = new ArrayList<Integer>();

        //Segun el boton presionado, el ArrayList se llena con diferentes numeros de los respectivos botones a cambiar
        if(NumeroBoton==0){
            ListaBotones.add(0);
            ListaBotones.add(1);
            ListaBotones.add(3);
            ListaBotones.add(4);
        }
        if(NumeroBoton==1){
            ListaBotones.add(0);
            ListaBotones.add(1);
            ListaBotones.add(2);
            ListaBotones.add(4);
        }
        if(NumeroBoton==2){
            ListaBotones.add(1);
            ListaBotones.add(2);
            ListaBotones.add(4);
            ListaBotones.add(5);
        }
        if(NumeroBoton==3){
            ListaBotones.add(0);
            ListaBotones.add(3);
            ListaBotones.add(4);
            ListaBotones.add(6);
        }
        if(NumeroBoton==4){
            ListaBotones.add(1);
            ListaBotones.add(3);
            ListaBotones.add(4);
            ListaBotones.add(5);
            ListaBotones.add(7);
        }
        if(NumeroBoton==5){
            ListaBotones.add(2);
            ListaBotones.add(4);
            ListaBotones.add(5);
            ListaBotones.add(8);
        }
        if(NumeroBoton==6){
            ListaBotones.add(3);
            ListaBotones.add(4);
            ListaBotones.add(6);
            ListaBotones.add(7);
        }
        if(NumeroBoton==7){
            ListaBotones.add(4);
            ListaBotones.add(6);
            ListaBotones.add(7);
            ListaBotones.add(8);
        }
        if(NumeroBoton==8){
            ListaBotones.add(4);
            ListaBotones.add(5);
            ListaBotones.add(7);
            ListaBotones.add(8);
        }
        return ListaBotones;
    }

    public boolean Ganar() {
        //Inicializamos variable boolean
        boolean ganar=false;

        //Obtenemos los codigos de las imagenes
        Drawable.ConstantState CodigoImagen1;
        CodigoImagen1= ContextCompat.getDrawable(this, R.drawable.cuadceleste).getConstantState();

        Drawable.ConstantState CodigoImagenBoton;

        int Cont = 0;

        for (int i = 0; i < 9; i++)
        {
            //Por cada boton verificamos si coinciden o no las imagenes
            CodigoImagenBoton = ArrayBotones[i].getDrawable().getConstantState();
            if (CodigoImagenBoton == CodigoImagen1)
            {
                Cont++;
            }
        }
        if (Cont==0||Cont==9){
            //Segun los resultados del contador, se verifica que todas las imagenes sean iguales
            ganar = true;
        }
        return ganar;
    }
}
