
import java.util.Random;

public class Warrior extends Character implements Attacker {

    private int stamina;
    private int strength;
    private Random rand = new Random();

    // Getters
    public int getStamina() {
        return stamina;
    }

    public int getStrength() {
        return strength;
    }

    // Setters
    public void setStamina(int stamina) {
        this.stamina = stamina;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    // Constructor 1
    public Warrior(String name, int hp, int stamina, int strength) {
        super(name, hp);
        if (stamina < 10 || stamina > 50) {
            throw new IllegalArgumentException("La stamina debe estar entre 10 y 50");
        }
        if (strength < 1 || strength > 10) {
            throw new IllegalArgumentException("La fuerza debe estar entre 1 y 10.");
        }
        this.stamina = stamina;
        this.strength = strength;
    }

    // Constructor 2: Genera automáticamente valores aleatorios
    public Warrior(String name) {
        super(name, new Random().nextInt(101) + 100); // HP (Puntos de vida) entre 100–200
        this.stamina = rand.nextInt(41) + 10; // Stamina entre 10–50
        this.strength = rand.nextInt(10) + 1; // Fuerza between 1–10
    }

    @Override
    public void attack(Character character) {
        // Comprueba si el personaje sigue vivo
        if (!this.isAlive()) {
            System.out.println(this.getName() + " está muerto y no puede atacar.");
            return;
        }

        // Decide aleatoriamente si intentar un ataque fuerte o débil
        boolean heavyAttack = rand.nextBoolean();
        int damage = 0;
        String attackType = "";

        // Tipo de ataque
        if (heavyAttack && stamina >= 5) {
            damage = strength;
            stamina -= 5;
            attackType = "Ataque fuerte";
        } else if (stamina >= 1) {
            damage = strength / 2;
            stamina += 1;
            attackType = "Ataque débil";
        } else {
            damage = 0;
            stamina += 2;
            attackType = "No posee suficiente energía - Sin ataque";
        }

        // Aplicación del daño
        character.setHp(character.getHp() - damage);

        // Resultado
        System.out.println(this.getName() + " usa " + attackType + " contra " + character.getName()
                + " causando " + damage + " de daño. (Puntos de vida de " + character.getName() + ": " + character.getHp() + ")");

    }
}
