package com.example.menssana.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.menssana.db.Citas;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DbCitas extends Dbhelper {

    Context context;

    public DbCitas(@Nullable Context context) {
        super(context);
        this.context =context;

    }
    public long insertarCita(String Titulo, String fecha, String nota) {

        long id = 0;
        try {
            Dbhelper dbHelper = new Dbhelper(context);

            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("Titulo", Titulo);
            values.put("fecha", fecha);
            values.put("nota", nota);

            id = db.insert(TABLA_CITAS, null, values);
            db.close();
        } catch (Exception ex) {
            ex.toString();
        }
        return id;

    }
    public ArrayList<Citas> mostrarCitas (){


        final Dbhelper Dbhelper= new Dbhelper(context);
        SQLiteDatabase db= Dbhelper.getWritableDatabase();

        ArrayList<Citas> listaCitas = new ArrayList<Citas>();
        Citas cita = null;
        Cursor cursorCitas= null;

        cursorCitas = db.rawQuery("SELECT * FROM " + TABLA_CITAS, null);

        if(cursorCitas.moveToFirst()){
            do{
                cita = new Citas();
                cita.setId(cursorCitas.getInt(0));
                cita.setTitulo(cursorCitas.getString(1));
                cita.setFecha(cursorCitas.getString(2));
                cita.setNota(cursorCitas.getString(3));
                listaCitas.add(cita);
            } while (cursorCitas.moveToNext());
        }

        cursorCitas.close();

        return listaCitas;


    }
    public Citas verCita (int id){


        final Dbhelper Dbhelper= new Dbhelper(context);
        SQLiteDatabase db= Dbhelper.getWritableDatabase();


        Citas cita = null;
        Cursor cursorCitas=null;



        cursorCitas = db.rawQuery("SELECT * FROM " + TABLA_CITAS + " WHERE id = " + id + " LIMIT 1", null);

        if(cursorCitas.moveToFirst()){

                cita = new Citas();
                cita.setId(cursorCitas.getInt(0));
                cita.setTitulo(cursorCitas.getString(1));
                cita.setFecha(cursorCitas.getString(2));
                cita.setNota(cursorCitas.getString(3));


        }

        cursorCitas.close();

        return cita;


    }
    public boolean editarCita(int id, String Titulo, String fecha, String nota) {

        boolean correcto = false;

        Dbhelper dbHelper = new Dbhelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("UPDATE " + TABLA_CITAS + " SET Titulo = '" + Titulo + "', fecha = '" + fecha + "', nota = '" + nota +"' WHERE id= '" + id + "' ");
            //db.close();
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }
        return correcto;

    }
    public boolean borrarCita(int id) {

        boolean correcto = false;

        Dbhelper dbHelper = new Dbhelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("DELETE FROM " + TABLA_CITAS + " WHERE id = '" + id + "'");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }
        return correcto;

    }
}
