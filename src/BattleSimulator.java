
import java.util.Random;
import java.util.Scanner;

public class BattleSimulator {

    // Metodo para crear un Warrior con nombre y atributos aleatorios o personalizados
    public static Warrior createWarrior() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el nombre del Guerrero: ");
        String name = scanner.nextLine();

        System.out.print("¿Desea ingresar atributos personalizados? (s/n): ");
        String choice = scanner.nextLine().toLowerCase();

        if (choice.equals("s")) {
            System.out.print("Ingrese los puntos de vida (100-200): ");
            int hp = scanner.nextInt();

            System.out.print("Ingrese la stamina (10-50): ");
            int stamina = scanner.nextInt();

            System.out.print("Ingrese la fuerza (1-10): ");
            int strength = scanner.nextInt();

            return new Warrior(name, hp, stamina, strength);
        } else {
            return new Warrior(name); // Crea un Warrior con atributos aleatorios
        }
    }

    // Método para crear un Wizard con nombre y atributos aleatorios o personalizados
    public static Wizard createWizard() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el nombre del Mago: ");
        String name = scanner.nextLine();

        System.out.print("¿Desea ingresar atributos personalizados? (s/n): ");
        String choice = scanner.nextLine().toLowerCase();

        if (choice.equals("s")) {
            System.out.print("Ingrese los puntos de vida (50-100): ");
            int hp = scanner.nextInt();

            System.out.print("Ingrese el maná (10-50): ");
            int mana = scanner.nextInt();

            System.out.print("Ingrese la inteligencia (1-50): ");
            int intelligence = scanner.nextInt();

            return new Wizard(name, hp, mana, intelligence);
        } else {
            return new Wizard(name, new Random().nextInt(51) + 50, new Random().nextInt(41) + 10, new Random().nextInt(50) + 1); // Crea un Wizard con atributos aleatorios
        }
    }

    // Método para simular el combate entre dos personajes
    public static void startBattle(Character player1, Character player2) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n--- ¡La batalla ha comenzado! ---");

        // Mientras ambos personajes estén vivos
        while (player1.isAlive() && player2.isAlive()) {
            System.out.println("\nEstado de los personajes:");
            System.out.println(player1.getName() + " - Vida: " + player1.getHp());
            System.out.println(player2.getName() + " - Vida: " + player2.getHp());

            // Turno de jugador 1
            System.out.println("\nEs el turno de " + player1.getName());
            player1.attack(player2);  // Jugador 1 ataca

            if (!player2.isAlive()) {
                System.out.println(player2.getName() + " ha caído. ¡" + player1.getName() + " ha ganado!");
                break;
            }

            // Turno de jugador 2
            System.out.println("\nEs el turno de " + player2.getName());
            player2.attack(player1);  // Jugador 2 ataca

            if (!player1.isAlive()) {
                System.out.println(player1.getName() + " ha caído. ¡" + player2.getName() + " ha ganado!");
                break;
            }

            // Pausa entre turnos
            System.out.println("\nPresione Enter para continuar con el siguiente turno...");
            scanner.nextLine();
        }
    }

    // Método principal que maneja el menú
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("--- Bienvenido al simulador de batalla ---");

        // Crear los personajes
        System.out.println("\nCrea el primer personaje:");
        System.out.println("1. Guerrero");
        System.out.println("2. Mago");
        System.out.print("Seleccione la clase del primer personaje (1/2): ");
        int choice1 = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer

        Character player1;
        if (choice1 == 1) {
            player1 = createWarrior();
        } else {
            player1 = createWizard();
        }

        System.out.println("\nCrea el segundo personaje:");
        System.out.println("1. Guerrero");
        System.out.println("2. Mago");
        System.out.print("Seleccione la clase del segundo personaje (1/2): ");
        int choice2 = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer

        Character player2;
        if (choice2 == 1) {
            player2 = createWarrior();
        } else {
            player2 = createWizard();
        }

        // Comenzar la batalla
        startBattle(player1, player2);
    }
}
