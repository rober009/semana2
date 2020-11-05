package com.example.semana2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

public class DetalleContactoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_contacto);
        Intent intent = getIntent();

        String nombreCompleto       = intent.getStringExtra("NOMBRECOMPLETO");
        String fechaNacimiento      = intent.getStringExtra("FECHANACIMIENTO");
        String telefono             = intent.getStringExtra("TELEFONO");
        String correoElectronico    = intent.getStringExtra("CORREOELECTRONICO");
        String descripcion          = intent.getStringExtra("DESCRIPCION");

        MaterialTextView txtNombreCompleto      = (MaterialTextView) findViewById(R.id.txtNombreCompleto);
        MaterialTextView txtfechaNacimiento     = (MaterialTextView) findViewById(R.id.txtFechaNacimiento);
        MaterialTextView txtTelefono            = (MaterialTextView) findViewById(R.id.txtTelefono);
        MaterialTextView txtCorreoElectronico   = (MaterialTextView) findViewById(R.id.txtCorreoElectronico);
        MaterialTextView txtDescripcion         = (MaterialTextView) findViewById(R.id.txtDescripcionContacto);

        txtNombreCompleto.setText(nombreCompleto);
        txtfechaNacimiento.setText(fechaNacimiento);
        txtTelefono.setText(telefono);
        txtCorreoElectronico.setText(correoElectronico);
        txtDescripcion.setText(descripcion);

        MaterialButton btnModificar = (MaterialButton) findViewById(R.id.btnModificar);

        btnModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


}