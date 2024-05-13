import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Rationnel {

    private int num;

    private int denom;
    static int compteur;
    /* mon cache qui contiendra tous mes rationnels dont le denominateur est 2 */
    private static Map<Integer, Rationnel> cache = new HashMap<>();

    /* constructeur prive qui creé les rationnels */
    private Rationnel(int a, int b) {

        if (b == 0) {
            throw new IllegalArgumentException("denominateur nul");
        }
        euclide(a, b);
        compteur++;
    }

    /*
     * methode static qui permet de representer par le meme objet les rationnels
     * avec comme denominateur 2
     * appel le constructeur pour creer les rationnels
     */
    public static Rationnel getInstance(int a, int b) {
        if (b != 2) {
            return new Rationnel(a, b);
        }

        int cle = a / b;
        Rationnel rationnel = cache.get(cle);
        if (rationnel == null) {
            rationnel = new Rationnel(a, b);
            cache.put(cle, rationnel);

        }
        return rationnel;

    }

    /* retourn le nombre d'objets rationnels créés */
    static int getCompteur() {
        return compteur;
    }
    /* retourn le numerateur */

    public int getNum() {
        return this.num;
    }

    /* retourne le donominateur */
    public int getDenom() {
        return this.denom;
    }

    /* methode qui cree l'oppose d'un rationnel */
    public Rationnel oppose() {
        return new Rationnel(-1 * this.num, this.denom);
    }

    /* methode qui retourne l'inverse d'un rationnel */
    public Rationnel inverse() {

        if (this.num == 0) {
            throw new IllegalStateException("on inverse pas numerateur nul");
        }
        return new Rationnel(this.denom, this.num);
    }

    /* methode qui retourne la somme de deux rationnels */
    public Rationnel addition(Rationnel r) {

        int num1 = (this.num * r.getDenom()) + (this.denom * r.getNum());
        int denom1 = (this.denom * r.getDenom());
        return new Rationnel(num1, denom1);

    }

    /* methode qui retourne la multiplication de deux rationnels */
    public Rationnel multiplication(Rationnel r) {

        int num1 = (this.num) * r.getNum();
        int denom1 = (this.denom * r.getDenom());
        return new Rationnel(num1, denom1);

    }

    /* ma methode toString pour afficher les rationnels */
    public String toString() {
        if (this.denom == 1 || this.num == 0) {

            return "Rationnel irreductible : " + this.num + " / " + this.denom;
        }

        return "Rationnel irreductible : " + this.num + " / " + this.denom;
    }

    /* compare deux objets rationnels */
    public boolean equals(Object o) {

        if (o instanceof Rationnel) {

            Rationnel r = (Rationnel) o;

            return this.num == r.getNum() && this.denom == r.getDenom();

        }
        return false;
    }

    /* retourne la division de deux rationnels */
    public Rationnel division(Rationnel r) {
        Rationnel rInv = r.inverse();
        return this.multiplication(rInv);
    }

    /* retourne la soustraction de deux rationnels */
    public Rationnel soustraction(Rationnel r) {
        Rationnel rOPP = r.oppose();
        return this.addition(rOPP);

    }

    /* retourne la valeur de hashcode de mon objet */
    public int hashCode() {
        return Objects.hashCode(this);

    }

    /* ajoute un entier a un rationnel */
    public Rationnel addInt(int c) {

        return new Rationnel((this.num + this.denom * c), this.denom);
    }

    /* multiplie un entier a un rationnel */
    public Rationnel multInt(int c) {

        return new Rationnel(this.num * c, this.denom);
    }

    /*
     * methode qui rend irreductible mon rationnel : divise le numerateur et le
     * denom
     * par le pgcd
     */
    public void euclide(int a, int b) {
        int t;
        this.num = a;
        this.denom = b;
        while (b != 0) {
            t = b;
            b = a % b;
            a = t;

        }
        this.num = this.num / a;
        this.denom = this.denom / a;

    }

}