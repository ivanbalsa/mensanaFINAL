package com.example.menssana.citas;




import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import androidx.appcompat.app.AppCompatActivity;


import com.example.menssana.R;
import com.example.menssana.db.DbCitas;

public class AgregarCita extends AppCompatActivity {

    EditText editTitulo, editFecha, editNota;

    Button btnAgregar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_citas);

        editTitulo= (EditText)findViewById(R.id.editTitulo);
        editFecha= (EditText)findViewById(R.id.editFecha);
        editNota= (EditText)findViewById(R.id.editNota);
        btnAgregar= (Button) findViewById(R.id.btnAgregar);



        btnAgregar.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view){

                DbCitas dbcitas = new DbCitas (AgregarCita.this);
                dbcitas.insertarCita(editTitulo.getText().toString(), editFecha.getText().toString(), editNota.getText().toString());



                limpiar();

               /* final Dbhelper dbhelp= new Dbhelper(AgregarCita.this);

                SQLiteDatabase db = dbhelp.getWritableDatabase();

                if(db!=null){
                    Toast.makeText(AgregarCita.this, "BASE DE DATOS CREADA", Toast.LENGTH_LONG).show();
                } else{
                    Toast.makeText(AgregarCita.this, "ERROR", Toast.LENGTH_LONG).show();
                }
                DbCitas.insertarCita(editTitulo.getText().toString(), editFecha.getText().toString());
                Toast.makeText(getApplicationContext(), "Se agrego correctamente", Toast.LENGTH_SHORT).show();*/

            }
        });






    }

    private void limpiar(){
        editTitulo.setText("");
        editFecha.setText((""));
        editNota.setText((""));

    }
}
