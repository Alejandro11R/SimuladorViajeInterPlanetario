import java.util.Scanner;

public class SimuladorViajeInterPlanetario {

    // Constantes de los planetas y sus distancias desde la Tierra en millones de km
    static String[] PLANETAS = { "Marte", "Júpiter", "Saturno" };
    static double[] DISTANCIAS = { 78.3, 628.7, 1275.0 }; // Distancia en millones de km

    // Constantes de las naves espaciales
    static String[] NAVES = { "Falcon 1", "Explorer X", "Voyager" };
    static double[] VELOCIDADES = { 25_000, 50_000, 75_000 }; // Velocidad en km/h
    static int[] CAPACIDADES = { 5, 10, 20 }; // Capacidad de pasajeros

    // Códigos ANSI para colores y estilos
    public static final String RESET = "\u001B[0m";
    public static final String CYAN = "\u001B[36m";
    public static final String YELLOW = "\u001B[33m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String BLUE = "\u001B[34m";
    public static final String BOLD = "\u001B[1m"; // Negrita

    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        boolean continuar = true;

        int planetaSeleccionado = -1;
        int naveSeleccionada = -1;
        int pasajeros = 0;
        double combustible = 0;
        double oxigeno = 0;

        // Mostrar el logo del simulador en ASCII
        System.out.println(CYAN + BOLD);
        System.out.println("     _.--\"\"--._     ");
        System.out.println("    .'          `.    ");
        System.out.println("    |    JAVA    |    ");
        System.out.println("    |   SPACE   |    ");
        System.out.println("    |  SIMULATOR |    ");
        System.out.println("    `.__________.'    " + RESET);

        while (continuar) {
            // Mostrar el menú principal con colores y decoraciones
            System.out.println(BLUE + "\n╔════════════════════════════════════════╗" + RESET);
            System.out.println(CYAN + "║          🌌 MENÚ PRINCIPAL 🌌          ║" + RESET);
            System.out.println(BLUE + "╠════════════════════════════════════════╣" + RESET);
            System.out.println(YELLOW + "║ 1. Seleccionar Planeta de Destino      ║" + RESET);
            System.out.println(YELLOW + "║ 2. Seleccionar Nave Espacial           ║" + RESET);
            System.out.println(YELLOW + "║ 3. Calcular Distancia y Tiempo de Viaje║" + RESET);
            System.out.println(YELLOW + "║ 4. Gestionar Recursos                  ║" + RESET);
            System.out.println(YELLOW + "║ 5. Iniciar Simulación del Viaje        ║" + RESET);
            System.out.println(YELLOW + "║ 6. Salir                               ║" + RESET);
            System.out.println(BLUE + "╚════════════════════════════════════════╝" + RESET);
            System.out.print(GREEN + "➡️  Seleccione una opción: " + RESET);
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    // Seleccionar el planeta de destino
                    planetaSeleccionado = seleccionarPlaneta(scanner);
                    break;
                case 2:
                    // Seleccionar la nave espacial
                    if (planetaSeleccionado == -1) {
                        System.out.println(RED + "⚠️  Debe seleccionar un planeta antes de elegir una nave." + RESET);
                    } else {
                        naveSeleccionada = seleccionarNave(scanner);
                        System.out.print(YELLOW + "Ingrese el número de pasajeros: " + RESET);
                        pasajeros = scanner.nextInt();
                        if (pasajeros <= 0) {
                            System.out.println(RED + "⚠️  El número de pasajeros debe ser mayor a 0." + RESET);
                            pasajeros = 0;
                        }
                    }
                    break;
                case 3:
                    // Calcular la distancia y el tiempo de viaje
                    if (planetaSeleccionado == -1 || naveSeleccionada == -1) {
                        System.out.println(RED + "⚠️  Debe seleccionar un planeta y una nave primero." + RESET);
                    } else {
                        calcularDistanciaYTiempo(planetaSeleccionado, naveSeleccionada);
                    }
                    break;
                case 4:
                    // Gestionar los recursos
                    if (planetaSeleccionado == -1 || naveSeleccionada == -1 || pasajeros == 0) {
                        System.out.println(
                                RED + "⚠️  Complete los pasos anteriores antes de gestionar recursos." + RESET);
                    } else {
                        System.out.print(YELLOW + "Ingrese la cantidad de combustible (toneladas): " + RESET);
                        combustible = scanner.nextDouble();
                        System.out.print(YELLOW + "Ingrese la cantidad de oxígeno (litros): " + RESET);
                        oxigeno = scanner.nextDouble();
                        if (combustible <= 0 || oxigeno <= 0) {
                            System.out.println(RED + "⚠️  Los recursos deben ser mayores a 0." + RESET);
                            combustible = 0;
                            oxigeno = 0;
                        }
                    }
                    break;
                case 5:
                    // Iniciar la simulación del viaje
                    if (planetaSeleccionado == -1 || naveSeleccionada == -1 || pasajeros == 0 || combustible == 0
                            || oxigeno == 0) {
                        System.out.println(RED + "⚠️  Complete todos los pasos antes de iniciar el viaje." + RESET);
                    } else {
                        iniciarSimulacion(planetaSeleccionado, naveSeleccionada, pasajeros, combustible, oxigeno);
                    }
                    break;
                case 6:
                    // Salir del programa
                    System.out.println(GREEN + "✨ Gracias por usar el simulador. ¡Buen viaje! 🚀" + RESET);
                    continuar = false;
                    break;
                default:
                    System.out.println(RED + "⚠️  Opción no válida. Intente de nuevo." + RESET);
            }
        }
        scanner.close();
    }

    // Método para seleccionar un planeta
    public static int seleccionarPlaneta(Scanner scanner) {
        System.out.println(BLUE + "╔════════════════════════════════════════════╗" + RESET);
        System.out.println(CYAN + "║ 🌎 Selección de Planeta de Destino 🌌      ║" + RESET);
        System.out.println(BLUE + "╠════════════════════════════════════════════╣" + RESET);
        for (int i = 0; i < PLANETAS.length; i++) {
            System.out.printf(YELLOW + "║ %d. %-10s 🌟 Distancia: %-8.1fM km ║%n" + RESET, i + 1, PLANETAS[i], DISTANCIAS[i]);
        }
        System.out.println(BLUE + "╚════════════════════════════════════════════╝" + RESET);
        System.out.print("✨ Ingrese el número del planeta: ");

        while (!scanner.hasNextInt()) {
            System.out.println("⚠️  Por favor, ingrese un número válido.");
            scanner.next();
            System.out.print("✨ Ingrese el número del planeta: ");
        }
        var seleccion = scanner.nextInt();
        if (seleccion >= 1 && seleccion <= PLANETAS.length) {
            System.out.println("\n🚀 🌌 🌟 Planeta seleccionado: " + PLANETAS[seleccion - 1] + " 🌟 🌌 🚀");
            return seleccion - 1;
        } else {
            System.out.println("❌ Selección inválida. Intente de nuevo.");
            return -1;
        }
    }

    // Método para seleccionar una nave espacial
    public static int seleccionarNave(Scanner scanner) {
        System.out.println(BLUE + "\n╔════════════════════════════════════════════╗" + RESET);
        System.out.println(CYAN + "║ 🛸 Selección de Nave Espacial 🚀          ║" + RESET);
        System.out.println(BLUE + "╠════════════════════════════════════════════╣" + RESET);
        for (int i = 0; i < NAVES.length; i++) {
            System.out.printf(YELLOW + "║ %d. %-12s 🚀 Velocidad: %-6.0f km/h, Capacidad: %-2d ║%n" + RESET,
                    i + 1, NAVES[i], VELOCIDADES[i], CAPACIDADES[i]);
        }
        System.out.println(BLUE + "╚════════════════════════════════════════════╝" + RESET);
        System.out.print("✨ Ingrese el número de la nave: ");

        while (!scanner.hasNextInt()) {
            System.out.println("⚠️  Por favor, ingrese un número válido.");
            scanner.next();
            System.out.print("✨ Ingrese el número de la nave: ");
        }

        var  seleccion = scanner.nextInt();
        if (seleccion >= 1 && seleccion <= NAVES.length) {
            System.out.println("\n🌌 🚀 Nave seleccionada: " + NAVES[seleccion - 1] + " 🚀 🌌");
            return seleccion - 1;
        } else {
            System.out.println("❌ Selección inválida. Intente de nuevo.");
            return -1;
        }
    }

    // Método para calcular la distancia y el tiempo de viaje
    public static void calcularDistanciaYTiempo(int planetaIndex, int naveIndex) {
        String planeta = PLANETAS[planetaIndex];
        double distancia = DISTANCIAS[planetaIndex] * 1_000_000;
        double velocidad = VELOCIDADES[naveIndex];
        double duracionHoras = distancia / velocidad;
        double duracionDias = duracionHoras / 24;

        System.out.println(BLUE + "\n🛰️ Cálculo de Distancia y Tiempo 🌌" + RESET);
        System.out.println(BLUE + "══════════════════════════════════" + RESET);
        System.out.printf(YELLOW + "🌍 Destino: %-10s 🌟 Distancia: %.2f km%n" + RESET, planeta, distancia);
        System.out.printf(YELLOW + "🕒 Duración estimada del viaje: %.2f días%n" + RESET, duracionDias);
        System.out.println(BLUE + "══════════════════════════════════" + RESET);
    }

    // Método para iniciar la simulación del viaje interplanetario
    public static void iniciarSimulacion(int planetaIndex, int naveIndex, int pasajeros, double combustible, double oxigeno) {
        String planeta = PLANETAS[planetaIndex];
        double distancia = DISTANCIAS[planetaIndex] * 1_000_000;
        double velocidad = VELOCIDADES[naveIndex];
        double duracionHoras = distancia / velocidad;
        double duracionDias = duracionHoras / 24;

        double combustibleNecesario = distancia / 1000;
        double oxigenoNecesario = duracionDias * pasajeros * 10;

        System.out.println(CYAN + "\n🚀 🌟 Preparando la Simulación del Viaje 🌟 🚀" + RESET);
        if (combustible < combustibleNecesario || oxigeno < oxigenoNecesario) {
            System.out.println(RED + "❌ Recursos insuficientes. No puede iniciar el viaje." + RESET);
            return;
        }

        System.out.println(GREEN + "\n🛸 Iniciando el viaje hacia " + planeta + "..." + RESET);

        // Animación de la nave despegando y viajando
        String[] animacion = {
                "          🚀          ",
                "         / 🚀 \\         ",
                "        /   🚀   \\        ",
                "       /     🚀     \\       ",
                "      /_______🚀_______\\      ",
                "     /_________🚀_________\\     ",
                "    /___________🚀___________\\    ",
                "   /_____________🚀_____________\\   ",
                "  /_______________🚀_______________\\  ",
                " /_________________🚀_________________\\ ",
                "/___________________🚀___________________\\"
        };

        for (String frame : animacion) {
            System.out.println(frame);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                System.out.println(RED + "⚠️  Error en la simulación." + RESET);
                return;
            }
            System.out.print("\033[H\033[2J"); // Limpiar la consola
            System.out.flush();
        }

        for (int i = 0; i <= 100; i += 20) {
            System.out.printf("🌌 Progreso del viaje: %d%% %s%n", i, i == 50 ? "✨ ¡Mitad del camino alcanzada! ✨" : "");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println(RED + "⚠️  Error en la simulación." + RESET);
                return;
            }
        }
        System.out.println(GREEN + "\n🎉 ¡Viaje completado! Bienvenido a " + planeta + ". 🎉" + RESET);
    }
}