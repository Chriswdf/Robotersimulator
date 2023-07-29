package model;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Zeichenflaeche extends JPanel                      //Klasse Zeichenflaeche ist Subklasse von Jpanel
{
    private ArrayList<Rechteck> anzuzeigendeHindernisse;

    /**
     * Konstrutkor der Klasse Zeichenflaeche ruft Konstruktor der Klasse JPanel auf um dessen Parameter zu beziehen
     */

    public Zeichenflaeche() {
        super();
    }


    /**
     * Das uebergebene Figur-Objekt zeichnen
     * <p>
     * Diese Methode zeichnet momentan noch nichts. Wenn hier gueltige Figuren uebergeben werden, speichern Sie die Figuren in Ihrer Klasse Zeichenflaeche
     * und rufen repaint() auf. Die Methode repaint() in der Klasse JPanel ruft paintComponent() auf und zeichnet so die geaenderten Figuren neu.
     * Deshalb brauchen Sie in Ihrem Programm paintComponent() nicht noch einmal explizit aufrufen.
     */

    public void setFiguren(Object figuren) {
        if (figuren instanceof ArrayList<?>) {
            ArrayList<?> figurenListe = (ArrayList<?>) figuren;

            if (!figurenListe.isEmpty() && figurenListe.get(0) instanceof Rechteck) {
                anzuzeigendeHindernisse = (ArrayList<Rechteck>) figurenListe;
                repaint();
            } else {
                throw new IllegalArgumentException("Ungültiger Parameter. Erwartet wurde eine ArrayList von Rechteck-Objekten.");
            }
        } else {
            throw new IllegalArgumentException("Ungültiger Parameter. Erwartet wurde eine ArrayList von Rechteck-Objekten.");
        }
    }


    /**
     * Die Methode paintComponent der Klasse JPanel mit einer eigenen Methode ueberschreiben
     * <p>
     * Hier wird ein weisser Hintergrund mit einem roten Rechteck gezeichnet. Aendern Sie die Methode so, dass die Liste Ihrer Figuren gezeichnet wird.
     */

    @Override
    public void paintComponent(Graphics graphic)                //wird von repaint() aufgerufen
    {
        super.paintComponent(graphic);

        Dimension size = getSize();                             //ein weißer Hintergrund wird gezeichnet
        graphic.setColor(Color.white);
        graphic.drawRect(0, 0, size.width, size.height);
        graphic.fillRect(0, 0, size.width, size.height);


        for (Rechteck zZR : anzuzeigendeHindernisse)             //jedes Element der hindernisliste <=> anzuzeigendeHindernisse wird anhand seiner bestimmten Parameter gezeichnet
        {                                                        //zZR entspricht zuZeichnendes Rechteck
            graphic.setColor(zZR.getFarbe());
            graphic.drawRect(zZR.getPosition().getX(), zZR.getPosition().getY(), zZR.getBreite(), zZR.getLaenge());
            graphic.fillRect(zZR.getPosition().getX(), zZR.getPosition().getY(), zZR.getBreite(), zZR.getLaenge());
        }

    }

    public void repaintFiguren(ArrayList<Rechteck> figuren) {
        anzuzeigendeHindernisse = figuren;                          //Die ArrayList der anzuzeigendenHindernisse wird durch die Eingabe einer ArrayList mit dieser aktualisiert
        repaint();                                                  //die Zeichenflaeche wird neu gezeichnet
    }


}