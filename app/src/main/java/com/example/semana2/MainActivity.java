package com.example.semana2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewDebug;
import android.widget.DatePicker;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.time.Month;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {

    //Los inputs y sus layouts
    private TextInputLayout edtlNombreCompleto;
    private TextInputEditText edtNombreCompleto;

    private TextInputLayout edtlFechaNacimiento;
    private TextInputEditText edtFechaNacimiento;

    private TextInputLayout edtlTelefono;
    private TextInputEditText edtTelefono;

    private TextInputLayout edtlCorreoElectronico;
    private TextInputEditText edtCorreoElectronico;

    private TextInputLayout edtlDescripcion;
    private TextInputEditText edtDescripcion;

    private MaterialButton btnSiguiente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        guardarReferenciasVistas();
        setearListeners();
    }

    private void guardarReferenciasVistas(){
        //guardamos la referencia al edittext y layout de cada elemento
        edtlNombreCompleto=     (TextInputLayout) findViewById(R.id.nombreCompletoLayout);
        edtNombreCompleto=      (TextInputEditText) findViewById(R.id.nombreCompletoEditText);
        edtlFechaNacimiento=    (TextInputLayout) findViewById(R.id.fechaNacimientoLayout);
        edtFechaNacimiento=     (TextInputEditText) findViewById(R.id.fechaNacimientoEditText);
        edtlTelefono=           (TextInputLayout) findViewById(R.id.telefonoLayout);
        edtTelefono=            (TextInputEditText) findViewById(R.id.telefonoEditText);
        edtlCorreoElectronico=  (TextInputLayout) findViewById(R.id.correoElectronicoLayout);
        edtCorreoElectronico=   (TextInputEditText) findViewById(R.id.correoElectronicoEditText);
        edtlDescripcion=        (TextInputLayout) findViewById(R.id.descripcionContactoLayout);
        edtDescripcion=         (TextInputEditText) findViewById(R.id.descripcionContactoTextfield);

        btnSiguiente=           (MaterialButton) findViewById(R.id.siguienteButton);
    }

    private boolean validarCamposVacios(){
        String nombreCompleto = edtNombreCompleto.getText().toString().trim();
        String fechaNacimiento = edtFechaNacimiento.getText().toString().trim();
        String telefono = edtTelefono.getText().toString().trim();
        String correoElectronico = edtCorreoElectronico.getText().toString().trim();
        String descripcion = edtDescripcion.getText().toString().trim();

        int error=0;

        Log.e("mmm",nombreCompleto);
        if(TextUtils.isEmpty(nombreCompleto)){
            edtlNombreCompleto.setError(getString(R.string.error_validation_empty_field));
            error=1;
        }

        if(TextUtils.isEmpty(fechaNacimiento)){
            edtlFechaNacimiento.setError(getString(R.string.error_validation_empty_field));
            error=1;
        }

        if(TextUtils.isEmpty(telefono)){
            edtlTelefono.setError(getString(R.string.error_validation_empty_field));
            error=1;
        }

        if(TextUtils.isEmpty(correoElectronico)){
            edtlCorreoElectronico.setError(getString(R.string.error_validation_empty_field));
            error=1;
        }

        if(TextUtils.isEmpty(descripcion)){
            edtlDescripcion.setError(getString(R.string.error_validation_empty_field));
            error=1;
        }

        if(error==0){
            return true;
        } else {
            return false;
        }
    }

    private void setearListeners(){
        //CLICK EN CAMPO FECHA
        //Creamos y mostramos el datepicker
        edtFechaNacimiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Sacamos el error en caso de que haya alguno
                edtlFechaNacimiento.setError(null);
                MaterialDatePicker.Builder builder = MaterialDatePicker.Builder.datePicker();
                builder.setTitleText("Fecha nacimiento");

                //Creamos las restricciones de fecha
                CalendarConstraints.Builder restriccionesDefechas = new CalendarConstraints.Builder();
                Calendar calendarStart = Calendar.getInstance();
                Calendar calendarEnd = Calendar.getInstance();
                calendarStart.set(1920, 1, 1);  //Aca es conveniente pasarlo a variables para poder editar los limites mas facilmente
                calendarEnd.set(2015, 1, 1);    //Aca es conveniente pasarlo a variables para poder editar los limites mas facilmente
                long minDate = calendarStart.getTimeInMillis();
                long maxDate = calendarEnd.getTimeInMillis();
                restriccionesDefechas.setStart(minDate);
                restriccionesDefechas.setEnd(maxDate);
                builder.setCalendarConstraints(restriccionesDefechas.build());
                //Fin restricciones de fecha
                MaterialDatePicker picker = builder.build();
                picker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
                    @Override
                    public void onPositiveButtonClick(Object selection) {
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC")); //Seteamos en UTC para que no devuelva la fecha anterior en ciertos casos https://github.com/material-components/material-components-android/issues/653
                        String fecha = simpleDateFormat.format(selection);
                        edtFechaNacimiento.setText(fecha);
                    }
                });
                picker.show(getSupportFragmentManager(), picker.toString());
            }
        });

        // ################# INICIO CHANGE FOCUS EN TODOS LOS INPUT
        edtNombreCompleto.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                edtlNombreCompleto.setError(null);
            }
        });
        edtTelefono.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                edtlTelefono.setError(null);
            }
        });
        edtCorreoElectronico.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                edtlCorreoElectronico.setError(null);
            }
        });
        edtDescripcion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtlDescripcion.setError(null);
            }
        });
        // ################# FIN CHANGE FOCUS
        //CLICK EN BOTON SIGUIENTE
        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validarCamposVacios()){
                    guardarContacto();
                }
            }
        });
    }

    private void guardarContacto(){
        String nombreCompleto = edtNombreCompleto.getText().toString().trim();
        String fechaNacimiento = edtFechaNacimiento.getText().toString().trim();
        String telefono = edtTelefono.getText().toString().trim();
        String correoElectronico = edtCorreoElectronico.getText().toString().trim();
        String descripcion = edtDescripcion.getText().toString().trim();

        Intent intent = new Intent(this, DetalleContactoActivity.class);
        intent.putExtra("NOMBRECOMPLETO", nombreCompleto);
        intent.putExtra("FECHANACIMIENTO", fechaNacimiento);
        intent.putExtra("TELEFONO", telefono);
        intent.putExtra("CORREOELECTRONICO", correoElectronico);
        intent.putExtra("DESCRIPCION", descripcion);

        startActivity(intent);
    }
}