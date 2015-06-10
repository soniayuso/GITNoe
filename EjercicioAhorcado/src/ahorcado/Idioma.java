/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ahorcado;

import java.lang.reflect.Array;



/**
 *
 * @author merche
 */
public enum Idioma {
    IDIOMA1("Español", 
            "A,B,C,D,E,F,G,H,I,J,K,L,M,N,Ñ,O,P,Q,R,S,T,U,V,W,X,Y,Z",
    "PATATA,REMOLACHA,MOLINILLO,HELICOPTERO,MESA,LOCOMOTORA"),
    IDIOMA2("Inglés", "A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z",
    "ONE,FRIEND,COMPUTER,UNDERSTAND,BOOK"),
    IDIOMA3("Raro", "|,@,#,$,%,&,/,),=,?,¿,Ç,^,*,¨,!,<,Ç",
    "|@#,%|@"),
     IDIOMA4("Italiano", "A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z",
    "CIAO,BAMBINI,GIALLO,MARE");
    private final String nombre;
    private final String letras;
    private final String palabras;
    private Idioma(String nombre, String letras,String palabras) {
        this.nombre = nombre;
        this.letras = letras;
        this.palabras=palabras;
    }
    public static String[] nombresIdiomas() {
        Idioma[] idiomas=Idioma.values();
        String[] devolver = new String[idiomas.length];
        for (int x = 0; x < devolver.length; x++) {
            devolver[x] = idiomas[x].nombre;
        }
//        int x=0;
//        for (Idioma unIdioma:idiomas) {
//            Array.set(devolver, 0, unIdioma.nombre);
//            x++;
//        }
        return devolver;
    }

    public String getNombre() {
        return nombre;
    }

    public  String[] letrasIdioma() {
        //Consulta el API para aprender  qué devuelve el método split de la clase String
        return this.letras.split(",");
    }
  public  String[] palabrasIdioma() {
        //Consulta el API para aprender  qué devuelve el método split de la clase String
        return this.palabras.split(",");
    }
    public static Idioma devuelveIdioma(String nombre) {
        Idioma devolver = null;
        Idioma[] losIdiomas=Idioma.values();
        for (Idioma uno : losIdiomas) {
            if (uno.nombre.equals(nombre)) {
                devolver = uno;
                break;
            }
        }
        return devolver;
    }
}
