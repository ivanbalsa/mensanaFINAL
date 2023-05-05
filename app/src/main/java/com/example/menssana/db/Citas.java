package com.example.menssana.db;

public class Citas {

    private int id;
    private String Titulo;
    private String fecha;

    private String nota;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String Titulo) {
        this.Titulo = Titulo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha=fecha;
    }

    public String getNota() { return nota; }

    public void setNota(String nota) { this.nota = nota; }
}
