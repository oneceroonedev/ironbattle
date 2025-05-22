
import java.util.Random;

public class Wizard extends Character implements Attacker {

    // Atributos privados específicos de los magos
    private int mana;
    private int inteligencia;
    private Random random = new Random();

    // Constructor que recibe nombre, puntos de vida, maná e inteligencia
    public Wizard(String nombre, int hp, int mana, int inteligencia) {
        super(nombre, hp);  // Llama al constructor de la clase padre (Character)
        this.mana = mana;
        this.inteligencia = inteligencia;
    }

    // Métodos GET para acceder a los atributos privados
    public int getMana() {
        return mana;
    }

    public int getInteligencia() {
        return inteligencia;
    }

    // Métodos SET para modificar los atributos
    public void setMana(int mana) {
        this.mana = mana;
    }

    public void setInteligencia(int inteligencia) {
        this.inteligencia = inteligencia;
    }

    // Método attack implementado desde la interfaz Attacker
    @Override
    public void attack(Character objetivo) {
        if (mana >= 5 && random.nextBoolean()) {
            lanzarFireball(objetivo);
        } else if (mana >= 1) {
            golpearConBaston(objetivo);
        } else {
            recuperarMana();
        }

        // Mostrar estado después del ataque
        System.out.println(getName() + " ahora tiene " + mana + " de maná.");
        System.out.println(objetivo.getName() + " tiene " + objetivo.getHp() + " de vida.");
        System.out.println("--------------------------------------------------");
    }

    // Lógica para lanzar Fireball
    private void lanzarFireball(Character objetivo) {
        System.out.println(getName() + " lanza una Fireball a " + objetivo.getName()
                + " causando " + inteligencia + " de daño.");
        objetivo.setHp(objetivo.getHp() - inteligencia);
        mana -= 5;
    }

    // Lógica para golpear con el bastón
    private void golpearConBaston(Character objetivo) {
        System.out.println(getName() + " golpea con su bastón a " + objetivo.getName()
                + " causando 2 de daño.");
        objetivo.setHp(objetivo.getHp() - 2);
        mana += 1;  // Recupera 1 de maná
    }

    // Lógica cuando no puede atacar y recupera maná
    private void recuperarMana() {
        System.out.println(getName() + " no tiene maná suficiente y no puede atacar. Recupera 2 de maná.");
        mana += 2;
    }
}
