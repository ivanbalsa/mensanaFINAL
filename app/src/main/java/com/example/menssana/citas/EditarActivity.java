package com.example.menssana.citas;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.menssana.R;
import com.example.menssana.db.Citas;
import com.example.menssana.db.DbCitas;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class EditarActivity extends AppCompatActivity {

    EditText txtTitulo, txtFecha, txtNota;
    Button btnGuarda, btnEdita;

    FloatingActionButton btnBorrar;

    boolean correcto = false;

    Citas cita;
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver);

        txtTitulo = findViewById(R.id.editTitulo);
        txtFecha = findViewById(R.id.editFecha);
        txtNota = findViewById(R.id.editNota);
        btnGuarda = findViewById(R.id.btnGuarda);
        btnEdita = findViewById(R.id.btnEdita);
        btnEdita.setVisibility(View.INVISIBLE);


        if(savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                id = Integer.parseInt(null);
            } else {
                id = extras.getInt("ID");
            }
        }  else {
            id = (int) savedInstanceState.getSerializable("ID");
        }

        DbCitas dbCitas = new DbCitas(EditarActivity.this);
        cita = dbCitas.verCita(id);

        if (cita != null){
            txtTitulo.setText(cita.getTitulo());
            txtFecha.setText(cita.getFecha());
            txtNota.setText(cita.getNota());

         }
       btnGuarda.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if(!txtTitulo.getText().toString().equals("") && !txtFecha.getText().toString().equals("")){
                  correcto = dbCitas.editarCita(id, txtTitulo.getText().toString(), txtFecha.getText().toString(), txtNota.getText().toString());
               if(correcto) {
                   Toast.makeText(EditarActivity.this, "REGISTRO MODIFICADO", Toast.LENGTH_LONG).show();
                   verRegistro();
               }else {
                   Toast.makeText(EditarActivity.this, "ERROR AL MODIFICAR EL REGISTRO", Toast.LENGTH_LONG).show();
               }
               } else {
                   Toast.makeText(EditarActivity.this, "DEBE LLENAR LOS CAMPOS OBLIGATORIOS", Toast.LENGTH_LONG).show();
               }

               }
           });
       }
       private void verRegistro(){
        Intent intent = new Intent (this, VerActivity.class);
        intent.putExtra("ID", id);
        startActivity(intent);
       }
    }

