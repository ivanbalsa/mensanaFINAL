package com.example.menssana.citas;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.menssana.R;
import com.example.menssana.db.Citas;
import com.example.menssana.db.DbCitas;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class VerActivity extends AppCompatActivity {

    EditText txtTitulo, txtFecha, txtNota;

    Button btnGuarda, btnEdita;

    FloatingActionButton btnBorrar;


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
        btnBorrar  = findViewById(R.id.btnBorrar);


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

            DbCitas dbCitas = new DbCitas(VerActivity.this);
            cita = dbCitas.verCita(id);

            if (cita != null){
                txtTitulo.setText(cita.getTitulo());
                txtFecha.setText(cita.getFecha());
                txtNota.setText(cita.getNota());
                btnGuarda.setVisibility(View.INVISIBLE);
                txtTitulo.setInputType(InputType.TYPE_NULL);
                //txtNota.setInputType(InputType.TYPE_NULL);

        }
            btnEdita.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(VerActivity.this, EditarActivity.class);
                    intent.putExtra("ID", id);
                    startActivity(intent);
                }
            });

             btnBorrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(VerActivity.this);
                    builder.setMessage("¿Desea eliminar este contacto?")
                            .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int i) {
                                    if (dbCitas.borrarCita(id)){
                                        lista();
                                    }
                                }
                            })
                            .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            }).show();
                }
            });
    }
    private void lista(){
        Intent intent = new Intent (this, TusCitas.class);
        startActivity(intent);
    }

}
