/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectostablasdemultiplicardos;

/**
 *
 * @author soniaa
 */
public class GestionInformacion {
private int numeroTabla;

//se comprueba que la respuesta dada es la correcta
public boolean comprueba(int respuesta, int posicion){
    boolean devolver=false;
        if(respuesta==devuelveCorrecta(posicion)){
            devolver=true;
            
        }
         return devolver; 
}
//devolver el resultado de multiplicar el número de tabla por la posición en la tabla (x)
public int devuelveCorrecta(int posicion){
    int devolver=(posicion+1)*this.numeroTabla;
        return devolver;
    
}
//cambiar el numero de la tabla con la que trabaja este objeto
public void setTabla(int Tabla){
    this.numeroTabla=Tabla;
}
}
