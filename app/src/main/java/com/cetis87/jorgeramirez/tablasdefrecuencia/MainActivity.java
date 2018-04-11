package com.cetis87.jorgeramirez.tablasdefrecuencia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button generar, informacion;
    String[] datos;
    EditText et;
    Tabla tabla;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et = (EditText) findViewById(R.id.editText);

        tabla = new Tabla(this, (TableLayout)findViewById(R.id.tabla));
        tabla.agregarCabecera(R.array.cabecera_tabla);
        for(int i = 0; i < 6; i++){
            ArrayList<String> elementos = new ArrayList<>();
            elementos.add(Integer.toString(i));
            elementos.add("Vacio");
            elementos.add("Vacio");
            elementos.add("Vacio");
            elementos.add("Vacio");
            elementos.add("Vacio");
            elementos.add("Vacio");
            elementos.add("Vacio");
            elementos.add("Vacio");
            elementos.add("Vacio");
            elementos.add("Vacio");
            elementos.add("Vacio");
            elementos.add("Vacio");
            elementos.add("Vacio");
            elementos.add("Vacio");
            tabla.agregarFilaTabla(elementos);
        }

        generar = (Button)findViewById(R.id.button);
        generar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datos = (et.getText().toString()).split(",");

                try {
                    double mayor = numeroMasAlto(datos);
                    double menor = numeroMasBajo(datos);
                    double rango = mayor-menor;
                    double iclase = Math.floor((Math.log10(datos.length)*3.33)+1);
                    double anchoc = Math.ceil(rango/iclase);


                    int p_ent = (int) menor;
                    if((menor-p_ent)==0.0){
                        menor = menor-1;
                    }
                    else{
                        double cuenta = iclase*anchoc;
                        double nuevorango = cuenta-rango;
                        menor= menor-nuevorango;
                    }

                    anchoc = Math.ceil(anchoc);

                    /*
                    tabla.removerDatos();
                    String[][] tablas = new String[(int)iclase][14];
                    double datonuevo = menor;



                    for(int i = 0; i < iclase; i++){
                        tablas[0][i] = "["+datonuevo+"-"+datonuevo+anchoc+">";
                    }
                    */

                    double nuevorango = iclase*anchoc;
                    double exceso = nuevorango-rango;
                    if(exceso == 0){

                    }
                    else if(exceso > 0){
                        double nuevoexceso = exceso/2;
                        int dato = (int) nuevoexceso;
                        if(nuevoexceso-dato== 0.0){
                            double rangofinal = 0;
                        }
                        if(nuevoexceso-dato > 0.0 && nuevoexceso-dato < 1){
                            double rangofinal = Math.round(nuevoexceso*Math.pow(10,nuevoexceso-dato))/Math.pow(10,nuevoexceso-dato);
                        }


                    }






                }catch(Exception e){
                    Toast.makeText(getApplicationContext(), "Error al tratar los datos, revisa la informacion proporcionada", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public double numeroMasAlto(String[] lista){
        double[] numero = new double[lista.length];
        for(int i = 0; i < lista.length; i++){
            numero[i] = Double.parseDouble(lista[i]);
        }
        double numeroAlto = numero[0];
        for(int i = 0; i < numero.length; i++){
            if(numeroAlto < numero[i]){
                numeroAlto = numero[i];
            }
        }
        return numeroAlto;
    }

    public double numeroMasBajo(String[] lista){
        double[] numero = new double[lista.length];
        for(int i = 0; i < lista.length; i++){
            numero[i] = Double.parseDouble(lista[i]);
        }
        double numeroBajo = numero[0];
        for(int i = 0; i < numero.length; i++){
            if(numeroBajo > numero[i]){
                numeroBajo = numero[i];
            }
        }
        return numeroBajo;
    }







}
