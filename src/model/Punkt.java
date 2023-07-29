package model;

public class Punkt {

    private int x;
    private int y;


    /**
     * "überladener" Konstruktor für Objekte der Klasse Punkt
     */

    public Punkt() {
        int x = 0;
        int y = 0;

        // Instanzvariable initialisieren
        // Konstruktor Punkt ohne Parameter
    }

    public Punkt(int x, int y) {
        this.x = x;
        this.y = y;
    }


    /**
     * Ein Beispiel einer Methode - ersetzen Sie diesen Kommentar mit Ihrem eigenen
     *
     * @return die Summe aus x und y
     */

    public int getX() {
        return x;
        // Methode: lesen von x
    }

    ;

    public int getY() {
        return y;
        // Methode: lesen von y
    }

    ;

    public void setX(int xPos) {
        x = xPos;
        // Methode: setzen von x
    }

    ;

    public void setY(int yPos) {
        y = yPos;
        // Methode: setzen von y
    }

    public void ausgabeAttribute() {
        System.out.print('\u000C');
        //Methode: Konsole reseten
        System.out.println("x=" + x + ";" + "y=" + y);
        //Methode: Ausgabe der Position
    }

    public void bewegePunktUm(int dx, int dy) {
        x = x + dx;
        //x wird um dx verschoben
        y = y + dy;
        //y wird um y verschoben
    }

    public double gibAbstand(Punkt andererPunkt) {
        int dx = this.x - andererPunkt.getX();
        // dx ist x-wertiger Abstand von Punkt des Objektes zu ausgewähltem Punkt)
        int dy = this.y - andererPunkt.getY();
        // dy ist y-wertiger Abstand von Punkt des Objektes zu ausgewähltem Punkt)

        return Math.hypot(dx, dy);
        //Hypothenuse der Abstandspunkte wird ermittelt (Abstand=[((dx)^2)+(dy)^2]^(1/2)


    }


    // http://www.codeadventurer.de/?p=2631

}
