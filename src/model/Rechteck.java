package model;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Rechteck {
    private Punkt position;                         //entspricht linkerer oberer Ecke des Rechtecks
    private int breite;                             //Breite in x-Richtung   Anzahl der Pixel
    private int laenge;                             //Länge in y-Richtung    Anzahl der Pixel
    private String bezeichnung;                     //Bezeichnung als Objekt der Klasse String
    private Color farbe;                            //Farbe als Objekt der Klasse Color
    private static Random zufallsgenerator;         //zufallsgenerator als Klassenvariable der Klasse Random

    /**
     * Konstruktor für Objekte der Klasse Rechteck
     */

    public Rechteck()               //einfacher Konstruktor
    {
        this.position = new Punkt(0, 0);               //mäßig sinnvolle Parameter eines einfach konstruierten Rechtecks
    }

    public Rechteck(Punkt position, int breite, int laenge, String bezeichnung, Color farbe)       //Konstruktor mit Attributen als formaler Parameter
    {
        this.position = position;
        this.breite = breite;
        this.laenge = laenge;
        this.bezeichnung = bezeichnung;
        this.farbe = farbe;
    }

    /**
     * Getter der Rechteck.Klassenattribute
     */

    public Punkt getPosition() {
        return position;
    }

    public int getBreite() {
        return breite;
    }

    public int getLaenge() {
        return laenge;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public Color getFarbe() {
        return farbe;
    }

    /**
     * Setter der Rechteck.Klassenattribute
     */

    public void setPosition(Punkt position) {
        this.position = position;
    }

    public void setBreite(int breite) {
        this.breite = breite;
    }

    public void setLaenge(int laenge) {
        this.laenge = laenge;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public void setFarbe(Color farbe) {
        if (farbe.equals(Color.white)) {
            System.out.println("Fehlermeldung: Weiß ist nicht sichbar bei einem weißen Hintergrund, somit nicht zulässig.");
        } else {
            this.farbe = farbe;
        }
    }


    public void bewegeUm(int dx, int dy) {
        position.setX(position.getX() + dx);
        position.setY(position.getY() + dy);
    }

    public void bewegeUm(Punkt verschiebevektor) {
        position.setX(position.getX() + verschiebevektor.getX());
        position.setY(position.getY() + verschiebevektor.getY());
    }

    public void ausgabeAttribute() {
        System.out.println("Position:" + position.getX() + "," + position.getY() + ")");
        System.out.println("Breite:" + breite);
        System.out.println("Länge:" + laenge);
        System.out.println("Bezeichnung:" + bezeichnung);
        System.out.println("Farbe:" + farbe);
    }

    public boolean ueberlappt(Rechteck r) {
        int x1 = this.position.getX();      //linke obere Ecke des aktuellen Rechtecks
        int y1 = this.position.getY();
        int x2 = x1 + this.breite;          //rechte obere Ecke des aktuellen Rechtecks
        int y2 = y1 + this.laenge;          //linke untere Ecke des aktuellen Rechtecks

        int x3 = r.getPosition().getX();    //linke obere Ecke des übergebenen Rechtecks
        int y3 = r.getPosition().getY();
        int x4 = x3 + r.getBreite();        //rechte obere Ecke des übergebenen Rechtecks
        int y4 = y3 + r.getLaenge();        //linke untere Ecke des übergebenen Rechtecks

        if (x1 >= x4 || x2 <= x3 || y2 <= y3 || y1 >= y4) //linksseitige Kontrolle || rechtsseitige Kontrolle || unterseitige Kontrolle || oberseitige Kontrolle
        {
            return false;                   //keine Überlappung
        } else {
            return true;                    //Überlappung
        }
    }

    public ArrayList<Rechteck> hindernislisteErzeugen() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Wieviele Hindernisse sollen sich auf dem Spielfeld befinden?");
        int a = scanner.nextInt();
        ArrayList<Rechteck> hindernisliste = new ArrayList<Rechteck>();
        int abbruch = 0;

        for (int i = 1; i < (a + 1) && (abbruch < 50); i++) {
            String bezeichnung = "Rechteck " + i;
            int spezbreite = zufallszahl(1, 100);            //spezifische breite eines Rechtecks
            int spezlaenge = zufallszahl(1, 100);            //spezifische Laenge eines Rechtecks
            Rechteck hindernis = new Rechteck(new Punkt(zufallszahl(1, 1000 - spezbreite), zufallszahl(1, 1000 - spezlaenge)), spezbreite, spezlaenge, bezeichnung, zufallsfarbe());                    //a-viele Rechtecke werden in einer Schleife insgesamt erzeugt

            hindernisliste.add(hindernis);                          //die hindernisse werden nacheinander der hindernisliste hinzugefügt

            for (Rechteck hindernisInListe : hindernisliste) {
                if (ueberlappt(hindernisInListe))           //wenn aktuelles hindernis mit einem hindernisInListe überlappt wird die Abbruchbedingung inkrementiert
                {
                    hindernisliste.remove(hindernis);
                    abbruch++;
                } else {
                    abbruch = 0;                                        //Abbruchbedingung kann nur 50 erreichen, wenn 50 Hindernisse hintereinander sich überlappen
                }
            }

        }

        return hindernisliste;                                      //die ArrayList hindernisliste wird der Methode zurückgegeben
    }

    private int zufallszahl(int von, int bis) {
        zufallsgenerator = new Random();                                    //zufallsgenerator-Objekt der Klasse Random wird erzeugt
        int value = zufallsgenerator.nextInt(von, bis);
        return value;                                                       //gibt erzeugten Bereich der zu generierenden Zufallszahl der Methode zurück
    }

    private Color zufallsfarbe() {
        zufallsgenerator = new Random();
        int r = zufallsgenerator.nextInt(256);                              //zufällige Werte für den Anteil von rot
        int g = zufallsgenerator.nextInt(256);                              //zufällige Werte für den Anteil von gelb
        int b = zufallsgenerator.nextInt(256);                              //zufällige Werte für den Anteil von blau

        Color randomColor = new Color(r, g, b, 1);                          //neue Farbe wird im RGBa-Modell erzeugt
        return randomColor;                                                 //generierte Farbe wird der Methode zurückgegeben
    }

    /**
     * Ein Beispiel einer Methode - ersetzen Sie diesen Kommentar mit Ihrem eigenen
     *
     * @param  y    ein Beispielparameter für eine Methode
     * @return die Summe aus x und y
     */


}

