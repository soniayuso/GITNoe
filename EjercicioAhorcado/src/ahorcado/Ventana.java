/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Ventana.java
 *
 * Created on 24-ene-2011, 10:37:55
 */
package ahorcado;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JViewport;

/**
 *
 * @author merche
 */
public class Ventana extends javax.swing.JFrame implements ActionListener {

    /**
     * El objeto de la clase GestoraInformacion con el que trabaja este JFrame
     */
    private final GestoraInformacion gestora = new GestoraInformacion();
    /**
     * La palabra que tiene que adivinar el usuario
     */
    private String palabraSecreta;

    /**
     * El número de letras de la palabra secreta que el usuario lleva acertadas
     */
    private int aciertos = 0;
    /**
     * El número de letras falladas
     */
    int fallos = 0;

    /**
     * este método constructor : invoca asetContentPane para que el panel de fondo de este Jframe sea un 
     * objeto de la clase PanelImagen tal que se visualice como imagen de fondo el archivo de imagen 
     * imagenes/fondo3.jpg, después invoca al método initComponents y por último hace el panel de scroll 
     * en el que se ha incluido el panel de JButton que representa la palabra secreta sea transparente 
     * (ver http://docs.oracle.com/javase/8/docs/api/javax/swing/JScrollPane.html#getViewport())
     *
     */
    public Ventana() {
        this.setContentPane(new PanelImagen("imagenes/fondo3.jpg"));        
        initComponents();
       jScrollPane1.getViewport().setOpaque(false);
    }
 /**
     * Genera los botones JButton de las letras, almacena sus referencias en
     * botonesLetras y los añaden al JPanel jpTeclas Para que los botones se
     * vean en más de una fila el Layout puede ser GridLayout (Diseño de flujo);
     *
     */
    private void generarBotones() {
        this.jpTeclas.removeAll();
        String[] letras = this.gestora.getIdioma().letrasIdioma();
        int longitud=letras.length;
        JButton[] botonesLetras = new JButton[longitud];
        for (int x = 0; x < longitud; x++) {
            botonesLetras[x] = new JButton(letras[x]);
            botonesLetras[x].addActionListener(this);
            this.jpTeclas.add(botonesLetras[x]);
        }
        jpTeclas.revalidate();
        jpTeclas.repaint();
    }
    /**
     * habilita todos los JButton del panel jpTeclas
     */
    private void habilitarBotonesLetra() {
        Component[] botonesLetras = jpTeclas.getComponents();
        for (Component boton : botonesLetras) {
            if (!boton.isEnabled()) {
                boton.setEnabled(true);
            }
        }
    }

    /**
     * Se empezará eliminando todos los componentes de jpLaPalabra, después se
     * crearán y añadirán al panel anterior tantos JButton como letras tenga la
     * palabra secreta de modo que cada botón tenga como texto "_" y esté
     * deshabilitado, por último se hará que los botones definidos se visualicen
     * en el panel, ¡No olvidar que cuando se añaden o eliminan componentes en
     * un contenedor que se está visualizando hay que invocar al método
     * revalidate() para que se visualicen los componentes actualizados! ¡No
     * olvidar que cuando cambia alguna propiedad que afecta al aspecto de un
     * componente incluido en un contenedor que se está visualizando se debe
     * invocar al método repaint() para que se actualice la vista que del mismo
     * ofrece el contenedor!
     */
    private void generarBotonesLetrasPalabraSecreta() {
        jpLaPalabra.removeAll();
        int longitud=palabraSecreta.length();
        JButton[] botonesLetrasPalabraSecreta = new JButton[longitud];
        for (int x = 0; x < longitud; x++) {
            botonesLetrasPalabraSecreta[x] = new JButton("_");
            botonesLetrasPalabraSecreta[x].setEnabled(false);
            jpLaPalabra.add(botonesLetrasPalabraSecreta[x]);
        }
        /**
         * Las siguientes sentencias son necesarias para que se actualice la
         * vista de los componentes del panel después de haber eliminado los
         * correspondientes a la palabra anterior y añadido los de la siguiente
         * palabra
         */
        jpLaPalabra.revalidate();
        jpLaPalabra.repaint();
    }

    /**
     * Este método debe empezar estableciendo como icono de jLabel1 la imagen
     * establecida como imagen inicial en la gestora; a continuación se generará
     * una nueva palabra secreta para dar valor al campo palabraSecreta; se
     * habilitarán los JButton de jpLetras, se inicializarán a 0 los valores de
     * los campos aciertos y fallos y se generarán los JButton para representar
     * las letras de la palabra secreta
     */
    public void mNuevaPalabra() {
        this.jLabel1.setIcon(gestora.imagenInicial());
        this.palabraSecreta = gestora.generarNuevaPalabraSecreta();
        if (this.palabraSecreta != null) {
            this.habilitarBotonesLetra();
            aciertos = 0;
            fallos = 0;
            this.generarBotonesLetrasPalabraSecreta();
            this.bGenerar.setEnabled(true);
        } else {
            JOptionPane.showMessageDialog(this, 
                    "No hay palabras para adivinar\ndel idioma " +
                            cbIdioma.getSelectedItem());
        }
    }

    public JButton getBGenerar() {
        return bGenerar;
    }
/**
 *  Espera  un array cuyos valores son las posiciones que ocupa en la palabra secreta,
 *  Este método debe cambiar el texto de los JButton correspondientes a dichas posiciones
 *  por la letra,  incrementar el valor del campo  acierto y comprobar si ya se han adivinado 
 * todas las letras de la palabra secreta, en cuyo caso, se dará un mensaje al usuario y se generará 
 * una nueva  palabra a adivinar 
 * @param posicionesConLetraElegida es un array de números enteros que representa las posiciones de la palabra 
 * secreta donde se encuentra
 * la última  letra elegida por el usuario
 */
    private void gestionarAciertos(Integer[] posicionesConLetraElegida) {
        int primera=posicionesConLetraElegida[0];
        String letra=this.palabraSecreta.substring(primera,primera+1);
        Component[] botonesLetrasPalabraSecreta = jpLaPalabra.
                getComponents();
        for (Integer unaPosicion : posicionesConLetraElegida) {
            ((JButton) botonesLetrasPalabraSecreta[unaPosicion]).setText(letra);
            aciertos++;
        }
        jpLaPalabra.repaint();
        if (aciertos == palabraSecreta.length()) {
            JOptionPane.showMessageDialog(this, "¡Has adivinado la palabra!");
            this.mNuevaPalabra();
        }
    }
/**
 * Se incrementará en 1  el valor del campo fallos, si el nuevo valor de dicho 
 * campo es menor que el máximo 
 * permitido  * se modificará la imagen que se visualiza en jLabel1 por la que
 * corresponda al nuevo valor;  * si el número de fallos es igual al máximo 
 * permitido se avisará al usuario de que ha perdido (indicando
 * cuál era la palabra a adivinar) y se invocará al metodo mNuevaPalabra
 * para empezar de nuevo el juego 
 * con una nueva palabra.
 */
    private void gestionarFallo() {
        fallos++;
        if (fallos <gestora.maximoFallos()) {
            jLabel1.setIcon(gestora.imagenFallos(fallos));
        }
        else  if (fallos == gestora.maximoFallos()) {
                JOptionPane.showMessageDialog(this, 
                        "¡Has perdido!. La palabra era " + palabraSecreta);
                mNuevaPalabra();
            }        
    }

    /**
     * deshabilita el Jbutton boton; comprueba si la letra que se visualiza
     * sobre el JButton boton se corresponde con alguna de las letras de
     * palabraSecreta; si la  letra del JButton que recibe el método está en la 
     * palabra secreta se 
     * invocará al método gestionarAciertos ; si la letra del JButton que recibe 
     * el método no se 
     * corresponde con ninguna de las letras de palabraSecreta se invocará 
     * al método gestionarFallo() 
     * @param boton
     */
    void mComprobar(JButton boton) {
        boton.setEnabled(false);
        char letraElegida = boton.getText().charAt(0);
        Integer[] posicionesConLetraElegida = gestora.
                posicionesLetra(letraElegida);
        if (posicionesConLetraElegida != null) {
            gestionarAciertos(posicionesConLetraElegida);
        } else {
            gestionarFallo();
        }
    }

   

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jpTeclas = new javax.swing.JPanel();
        bGenerar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jpLaPalabra = new javax.swing.JPanel();
        cbIdioma = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("AHORCADO");
        setIconImage(new ImageIcon(this.getClass().getResource("imagenes/ahorcado.GIF")).getImage());
        setResizable(false);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ahorcado/imagenes/ahorcado.GIF"))); // NOI18N
        jLabel1.setText("jLabel1");

        jpTeclas.setOpaque(false);
        jpTeclas.setLayout(new java.awt.GridLayout(3, 0));

        bGenerar.setBackground(new java.awt.Color(255, 255, 204));
        bGenerar.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        bGenerar.setText("Generar nueva palabra");
        bGenerar.setEnabled(false);
        bGenerar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bGenerarActionPerformed(evt);
            }
        });

        jScrollPane1.setBorder(null);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane1.setOpaque(false);

        jpLaPalabra.setOpaque(false);
        jScrollPane1.setViewportView(jpLaPalabra);

        cbIdioma.setBackground(new java.awt.Color(255, 255, 204));
        cbIdioma.setModel(new DefaultComboBoxModel(Idioma.nombresIdiomas()));
        cbIdioma.setSelectedItem(null);
        cbIdioma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbIdiomaActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel2.setText("IDIOMA");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(bGenerar)
                        .addContainerGap(458, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(108, 108, 108)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbIdioma, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jpTeclas, javax.swing.GroupLayout.PREFERRED_SIZE, 428, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 428, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbIdioma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(41, 41, 41)
                        .addComponent(jpTeclas, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(bGenerar)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
 /**
     * Si el usuario ha seleccionado un idioma, cambia el idioma con el que
     * trabaja la gestora, genera y visualiza en jpTeclas los JButton
     * correspondientes a las letras del idioma seleccionado, botones c -cada
     * valor adecuado a letras -invoca a generarBotones() para generar los
     * Jbutton -define a esta ventana como el objeto escuchador de eventos de
     * acción para los botones generados y también para el JButton bGenerar
     * -invoca a mNuevaPlabra() para generar la plabra a adivinar
     */

    private void cbIdiomaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbIdiomaActionPerformed
        String seleccionado = (String) cbIdioma.getSelectedItem();
        if (seleccionado != null) {
            Idioma idioma=Idioma.devuelveIdioma(seleccionado);
            this.gestora.setIdioma(idioma);
            this.generarBotones();            
            this.mNuevaPalabra();
        }
    }//GEN-LAST:event_cbIdiomaActionPerformed
/**
 * invoca al método mNuevaPalabra para comenzar el juego con una nueva palabra
 * @param evt 
 */
    private void bGenerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bGenerarActionPerformed
        this.mNuevaPalabra();        
    }//GEN-LAST:event_bGenerarActionPerformed
    /**
     * - hace que el idioma seleccionado sea Idioma.ES - pone en etiIdioma el
     * nombre del idioma - deshabilita bEspanol y habilita bIngles
     *
     * @param evt
     */
    /**
     * - hace que el idioma seleccionado sea Idioma.IN - pone en etiIdioma el
     * nombre del idioma - deshabilita bIngles y habilita bEspanol
     *
     * @param evt
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bGenerar;
    private javax.swing.JComboBox cbIdioma;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel jpLaPalabra;
    private javax.swing.JPanel jpTeclas;
    // End of variables declaration//GEN-END:variables

    /**
     * se  invoca al método mComprobar(JButton) para comprobar
     * si la letra que aparece sobre el JButton que ha provocado el evento de
     * acción está en la palabra
     *
     * @param e
     */
    public void actionPerformed(ActionEvent e) {
        Object fuente = e.getSource();
        this.mComprobar((JButton) fuente);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ventana().setVisible(true);
            }
        });
    }

}
