/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ahorcado;

import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;

/**
 *
 * @author merche
 */
public class GestoraInformacion {
 /**
     * el idioma actual
     */
    private Idioma idioma;
    /**
     * Una palabra aleatoria del idioma actual
     */
    private String palabraSecreta;
 
   

    public String getPalabraSecreta() {
        return palabraSecreta;
    }

    public Idioma getIdioma() {
        return idioma;
    }

  

    /**
     * Da al campo idioma el valor i 
     * @param i un idioma
     */
    public void setIdioma(Idioma i) {
        this.idioma = i;
    }

    /**
     * Selecciona una posición aleatoria de un array que contiene
     * todas las palabras de idioma para dar valor a palabraSecreta
     *
     * @return devuelve palabraSecreta
     */
    public String generarNuevaPalabraSecreta() {
        String[] palabras = this.idioma.palabrasIdioma();
        int aleatorio=(int) (Math.random()*palabras.length);  
        //int aleatorio=new Random().nextInt(palabras.length);
        this.palabraSecreta = palabras[aleatorio];   
        return this.palabraSecreta;
    }    
    /**
     * Crea un ArrayList donde se van almacenando las posiciones
     * donde se encuentra c en la palabra secreta
     * A partir del ArrayList anterior se define un array de Integer que
     * representa la misma información
     *
     * @param c la letra
     * @return el array de posiciones donde se encuentra c en la
     * palabra secreta o null si la letra c no aparece en  la palabra
     * secreta
     */
    public Integer[] posicionesLetra(char c) {
        Integer[] devolver=null;
        if (this.palabraSecreta.indexOf(c) != -1) {
            ArrayList<Integer> intermedio = new ArrayList<Integer>();
            for (int x = 0; x < palabraSecreta.length(); x++) {
                if (palabraSecreta.charAt(x) == c) {
                    intermedio.add(x);
                }
            }
           devolver=intermedio.toArray(new Integer[intermedio.size()]);
        } 
        return devolver;
    }

    /**
     *Crea un objeto ImageIcon que representa la imagen almacenada en el archivo imagenes/ahorcado.GIF
     * @return un objeto ImageIcon que representa la imagen almacenada en el archivo imagenes/ahorcado.GIF
     */
    public ImageIcon imagenInicial() {
        URL recurso=getClass().getResource("imagenes/ahorcado.GIF");
        ImageIcon imagen=new ImageIcon(recurso);
        return imagen;
    }

    /**
     *Crea un objeto ImageIcon que representa la imagen que corresponde
     * al número de fallos que espera el método como parámetro 
     * (a.GIF para 1 fallo, ah.GIF, para 2 fallos,...)
     * @param fallos
     * @return un objeto ImageIcon que representa la imagen que corresponde al número de fallos que espera
     * el método como parámetro
     */
    public ImageIcon imagenFallos(int fallos) { 
        URL recurso=getClass().getResource(
                "imagenes/" + "ahorcado".substring(0, fallos) + ".GIF");
        ImageIcon imagen=new ImageIcon(recurso);
        return imagen;
    }

    /**
     *
     * @return número máximo de fallos
     * (el nº de letras de la palabra ahorcado)
     */
    public int maximoFallos() {
        return "ahorcado".length();
    }
}
