package model;

import javax.swing.*;
import java.awt.*;

public class Leinwand {

    private JFrame fenster;
    private Zeichenflaeche zeichenflaeche;

    /**
     * Passen Sie den Konstruktor f�r Ihr Projekt an.
     */

    public Leinwand() {
        fenster = new JFrame();
        zeichenflaeche = new Zeichenflaeche();
        fenster.setContentPane(zeichenflaeche);                             //Zeichenflaeche wird zum Hauptinhalt des Fensters gemacht
        fenster.setTitle("Robotersimulation");
        zeichenflaeche.setPreferredSize(new Dimension(850, 850));               //vorgegeben waren (850, 850) --> aktualisierter Wert: (1000, 1000)
        fenster.pack();                                                     //Größe des Fensters wird automatisch an darin enthaltenen Inhalt angepasst
        fenster.setVisible(true);

    }


    /**
     * Das uebergebene Figur-Objekt auf die Leinwand zeichnen
     * <p>
     * Diese Methode zeichnet momentan noch nichts. Passen Sie den Parameter so an, dass die Figuren
     * aus Ihrem Projekt gezeichnet werden.
     */
    public void zeichnen(Object figuren) {                                  //Superklasse Object enthält Subtyp: ArrayList<Rechteck> figuren
        zeichenflaeche.setFiguren(figuren);
    }

    /**
     * Die angegebenen Millisekunden warten
     * <p>
     * Verwenden Sie diese Methode z.B., wenn sich der Roboter zu schnell bewegt.
     */
    public void warten(int millisekunden) {
        try {
            Thread.sleep(millisekunden);
        } catch (Exception e) {
            // Exception ignorieren
        }
    }
}