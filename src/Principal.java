public class Principal {
    /* ma classe Principale pour tester mes fonctions */
    public static void main(String[] args) {

        Rationnel r1 = Rationnel.getInstance(4, 2);
        Rationnel r2 = Rationnel.getInstance(4, 2);
        Rationnel r3 = r1.division(r2);
        Rationnel r4 = r3.inverse().addInt(2);

        System.out.println(r4);
        System.out.println("Nombre d'objets créés : " + Rationnel.getCompteur());

    }
}