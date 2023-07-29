package model;

import java.util.HashMap;
import java.util.Scanner;

public class Roboter {
    // Instanzvariablen - ersetzen Sie das folgende Beispiel mit Ihren Variablen


    /**
     * Aufzählunstyp Stichwort {NAME, ALTER, HERSTELLER, GESCHLECHT}
     */

    public enum Stichwort {
        NAME, ALTER, HERSTELLER, GESCHLECHT;
    }


    public void spracherkennung() {
        Stichwort stichwort;
        String ende = "ENDE";
        HashMap<String, String> antworten = sinnvolleAntworten();

        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Stellen Sie dem Roboter eine Frage.");
            String eingabe = scanner.nextLine().toUpperCase();              //eingabe wird dem String eingabe zugewiesen

            if (eingabe.equals(ende)) {
                break;                                                  //Schleife wird so bei der Eingabe von dem Wert von ende vorzeitig abgebrochen
            }

            stichwort = Stichwort.valueOf(eingabe);                         //Wert der eingabe wird in den Aufzählunstyp umgewandelt

            switch (stichwort) {

                case NAME:
                    System.out.println(antworten.get("name"));
                    break;

                case ALTER:
                    System.out.println(antworten.get("alter"));
                    break;

                case HERSTELLER:
                    System.out.println(antworten.get("hersteller"));
                    break;

                case GESCHLECHT:
                    System.out.println(antworten.get("geschlecht"));
                    break;

                default:
                    System.out.println("Der Roboter ist in seinen Antwortmöglichkeiten begrenzt. Versuche eine andere Frage.");

            }

        }

    }

    /**
     * Ein Beispiel einer Methode - ersetzen Sie diesen Kommentar mit Ihrem eigenen
     *
     * @return die Summe aus x und y
     */
    private HashMap<String, String> sinnvolleAntworten()                     //eine Methode von Antworten um Struktur zu bewahren
    {
        HashMap<String, String> antworten = new HashMap<String, String>();

        antworten.put("name", "Mein Name ist Rob3141.");
        antworten.put("alter", "Das Alter eines implementierten Objektes zu bestimmen ist unlogisch.");
        antworten.put("hersteller", "Meine Konstrukteuren sind Christopher Stapelmann und Maximilian Mann");
        antworten.put("geschlecht", "Das Geschlecht ist biologisch eineindeutig. Ich hingegen besitze keines.");

        return antworten;
    }


}

