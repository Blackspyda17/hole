package com.motivaimagine.motivaimagine_trial;

/**
 * Created by gpaez on 9/4/2017.
 */

public class implant_info {

    private String type;
    private String fecha;
    private String lado;
    private String num_serie;
    private String referencia;
    private String tipo;
    private String textura;
    private String base;
    private String proyeccion;
    private String volumen;


    public implant_info(String type, String fecha, String lado, String num_serie, String referencia, String tipo, String textura, String base, String proyeccion, String volumen) {
        this.type = type;
        this.setFecha(fecha);
        this.lado = lado;
        this.num_serie = num_serie;
        this.referencia = referencia;
        this.tipo = tipo;
        this.textura = textura;
        this.base = base;
        this.proyeccion = proyeccion;
        this.volumen = volumen;

    }

    public implant_info() {
        this.type = "Seno";
        this.setFecha("10-Aug-2017");
        this.lado = "Izquierdo";
        this.num_serie = "17040532-12";
        this.referencia = "ERSD-300Q";
        this.tipo ="Ergonomix™ Round";
        this.textura = "SILKSURFACE™";
        this.base = "11.5 cm";
        this.proyeccion = "3.9 cm";
        this.volumen = "300 cc";
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLado() {
        return lado;
    }

    public void setLado(String lado) {
        this.lado = lado;
    }

    public String getNum_serie() {
        return num_serie;
    }

    public void setNum_serie(String num_serie) {
        this.num_serie = num_serie;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTextura() {
        return textura;
    }

    public void setTextura(String textura) {
        this.textura = textura;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getProyeccion() {
        return proyeccion;
    }

    public void setProyeccion(String proyeccion) {
        this.proyeccion = proyeccion;
    }

    public String getVolumen() {
        return volumen;
    }

    public void setVolumen(String volumen) {
        this.volumen = volumen;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
