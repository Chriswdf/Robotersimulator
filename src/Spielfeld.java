import model.Leinwand;
import model.Punkt;
import model.Rechteck;
import model.Roboter;

import java.util.ArrayList;
import java.util.Scanner;

public class Spielfeld {
    private int breite = 1000;
    private int laenge = 1000;
    private Punkt punkt;
    private Leinwand leinwand;

    /**
     * Konstruktor der Klasse Spielfeld, erzeugt ein Roboter-Objekt der Klasse Roboter
     */

    public Spielfeld() {
        leinwand = new Leinwand();
        //Spielfeld spielfeld = new Spielfeld();                        //evtl nicht notwendig
        //this.breite = breite;                                         //irrelevant
        //this.laenge = laenge;                                         //irrelevant
    }

    public static void main(String[] args) {
        Spielfeld spielfeld = new Spielfeld();                                          //damit poiAbfahren() aufgerufen werden kann
        Roboter roboter = new Roboter();                                                //um spracherkennung() aufrufen zu können
        Scanner scanner = new Scanner(System.in);                                       //Vorarbeit: Eingabekonsole
        String eingabe;                                                                 //setzen der eingabe-Variable da sowohl in do als auch while Rumpf von Bedeutung
        String ende = "ENDE";

        do {
            System.out.println("Hauptmenü:\n Gültige Eingabe mittels der Ziffer der Aufgabe ('1','2','3')");         //ggf "Hauptmenü"\n""Aufgaben"\n"Verlassen mit Eingabe von 'Ende'"
            System.out.println("Welche Aufgabe soll der Roboter bewältigen?");
            System.out.println("1. Points-of-Interest über den möglichst kürzesten Weg abfahren");
            System.out.println("2. Hindernisse umfahren");
            System.out.println("3. Stichwört erkennen und antworten");
            System.out.println("Zum Verlassen des Spiels 'ende' eingeben");

            eingabe = scanner.nextLine().toUpperCase();

            switch (eingabe) {                                                  //Auswahl der Aufgabe nach dem Switch-Case verfahren

                case "1":                                                       //1. Aufgabe: poi abfahren
                    System.out.println("Starte: Points-of-Interest abfahren...");
                    spielfeld.poiAbfahren();                                    //aus der Spielfeld-Objekt der Klasse Spielfeld
                    break;

                case "2":                                                       //2. Aufgabe: Hindernisse umfahren
                    System.out.println("Starte: Hindernisse umfahren...");
                    spielfeld.hindernisseUmfahren();                                      //wird später implementiert
                    break;

                case "3":                                                       //3. Aufgabe: Spracherkennung
                    System.out.println("Starte: Spracherkennung...");
                    roboter.spracherkennung();
                    break;

                case "ENDE":
                    break;

                default:                                                         //Antwort auf jegliche andere Eingaben
                    System.out.println("Ungültige Eingabe. Wählen Sie eine der Aufgaben oder verlassen Sie das Spiel.");
                    System.out.println("Gültige Eingaben: '1', '2', '3', 'ende'");
                    break;
            }

        }

        while (!eingabe.equals(ende));                                                 //Abbruchbedingung ist ende
    }

    /**
     * Methode die ein Array bestimmter im Spielfeld liegenden Punkte anlegt
     */
    public Punkt[] punkteEingeben() {
        Scanner scanner0 = new Scanner(System.in);                  //Eingabe der Punktanzahl
        System.out.println("Geben Sie die Anzahl der zu bestimmenden Punkte ein.");

        int anzahlPunkte = scanner0.nextInt();
        Punkt[] punkteliste = new Punkt[anzahlPunkte + 1];          //Array über Anzahl der Punkte wird erstellt
        punkteliste[0] = new Punkt(0, 0);                         //1. Eintrag des Arrays ist der Ursprung (0, 0)

        for (int i = 1; i < punkteliste.length; i++) {
            Scanner scanner = new Scanner(System.in);                           //Null Pointer exception vermeidbar?
            System.out.println("Geben Sie die x-Koordinate des " + i + ".Punktes ein:");
            int x = scanner.nextInt();
            System.out.println("Geben Sie die y-Koordinate des " + i + ".Punktes ein:");
            int y = scanner.nextInt();

            if ((x >= breite || y >= laenge) && (x == 0 || y == 0))             // Koordinaten des Punktes müssen innerhalb des Feldes liegen
            {
                System.out.println("Die Koordinaten des Punktes befinden sich außerhalb des Spielfeldes!");
                System.out.println("x = (0..." + breite + "); y = (0..." + laenge + ")");
                System.out.println("Geben Sie die Koordinaten des " + i + ".Punktes erneut ein.");
                i--;
            }

            punkteliste[i] = new Punkt(x, y);                  //Eintrag an iter Stelle wird durch Punkt-Objekt gefüllt                                           //i wird inkrementiert
        }


        punkteliste = poiSortieren(punkteliste);                //poiSortieren bereits hier, da eine Liste von ungeordneten Punkten keinen Sinn erfüllt

        return punkteliste;
    }


    public Punkt[] poiSortieren(Punkt[] poi) {
        for (int i = 0; i <= poi.length - 1; i++) {

            for (int j = 1 + i; j < poi.length - i - 1; j++)                       //j entspricht zu untersuchende Einträge; "1" aufgrund der Lesbarkeit
            {
                int k = j + i + 1;

                do {

                    if (poi[i].gibAbstand(poi[j]) > poi[i].gibAbstand(poi[k]))        //Bubble-Sort-Algorithmus
                    {
                        Punkt temp = poi[j];
                        poi[j] = poi[k];
                        poi[k] = temp;
                    }

                    k++;
                }
                while (k < poi.length - i);

            }

        }

        System.out.println("Das Array ist sortiert");

        for (int i = 1; i > poi.length; i++)                            //(Punkt punkt : poi)
        {
            System.out.println("Punkt (" + punkt.getX() + "," + punkt.getY() + ") - Abstand: " + punkt.gibAbstand(poi[i]));         //Abstände von Punkt zu Punkt müssen berechnet werden
        }

        return poi;
    }


    public double poiAbfahren() {
        int i = 1;
        double sum = 0;                                      //Summe der Teilroutenabstände
        Punkt[] poi = punkteEingeben();                      //Array der Punkte (Objektreferenz)
        for (Punkt punkt : poi)                              //für alle Punkte wird Abstand zum nächsten Punkt gemessen
        {
            double abstand = punkt.gibAbstand(poi[i]);
            sum += abstand;
            i++;
            if (i == poi.length)                              //verhindert i > pl.length --> exception
            {
                i--;
            }
        }

        System.out.println("Der kürzeste Weg beträgt: " + sum);
        return sum;
    }


    /**
     * Methode um die Dimension des Spielfeldes als Methode eine Objektes übergeben zu können.
     */
    /**
     * wahrscheinlich nutzlos
     public Dimension getSize()
     {
     Dimension size = new Dimension (breite, laenge);
     return size;
     }
     */

    /**
     *
     */

    public void zeichnen(ArrayList<Rechteck> hindernisse)          //evtl auch durch Klassenhierarchie möglich
    {
        /**
         * Fehlerhaft
         */
        Leinwand leinwand = new Leinwand();                         //Objekt der Klasse Spielfeld wird erzeugt und erzeugt simultan ein Objekt der Klasse Leinwand
        leinwand.zeichnen(hindernisse);                           //Methode zeichnen des Leinwand-Objektes wird aufgerufen und die Eingabe einer ArrayList hindernisse wird als Parameter übergeben)
    }


    public void hindernisseUmfahren() {
        Rechteck rechteck = new Rechteck();
        leinwand.zeichnen(rechteck.hindernislisteErzeugen());                //Alle Hindernisse der ArrayList<Rechteck> (als Referenz übergeben) werden gezeichnet
    }
}