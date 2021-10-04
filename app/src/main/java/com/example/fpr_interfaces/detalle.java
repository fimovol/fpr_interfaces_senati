package com.example.fpr_interfaces;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class detalle extends AppCompatActivity {
    private ImageView imgItemDetalle;
    private TextView txt1,txt2;
    private CantanteModelo itemDetalle;

    Bundle extras;
    String newString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);
        initialViews();
        initialValues();
        extras = getIntent().getExtras();
        if(extras == null) {
            newString= null;
            System.out.println("nada en el titulo :V");
        } else {
            newString= extras.getString("titulo");
            setTitle(newString);
        }
    }
    public void anadirtitulo(){

    }
    public void initialViews(){
        imgItemDetalle= findViewById(R.id.imageView);
        txt1= findViewById(R.id.textView2);
        txt2= findViewById(R.id.textView3);
    }
    public void initialValues(){
        itemDetalle = (CantanteModelo) getIntent().getExtras().getSerializable("contra");
        imgItemDetalle.setImageResource(itemDetalle.getFotocantante());
        txt1.setText(itemDetalle.getCantante());
        txt2.setText(itemDetalle.getNacionalidad());
    }
}