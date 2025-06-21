import implementacion.ArbolPrecipitaciones;
import tdas.ABBPrecipitacionesTDA;
import algoritmos.Algoritmos;
import tdas.ColaPrioridadTDA;
import tdas.ColaStringTDA;

public class Main {
    public static void main(String[] args) {
        // Inicializar el árbol de precipitaciones
        ABBPrecipitacionesTDA arbol = new ArbolPrecipitaciones();
        arbol.inicializar();

        // Agregar campos
        arbol.agregar("CampoA");
        arbol.agregar("CampoB");

        // Crear instancia de algoritmos
        Algoritmos alg = new Algoritmos(arbol);

        // Cargar mediciones de ejemplo
        alg.agregarMedicion("CampoA", 2025, 7, 10, 20);
        alg.agregarMedicion("CampoA", 2025, 7, 11, 15);
        alg.agregarMedicion("CampoA", 2025, 8,  1,  5);
        alg.agregarMedicion("CampoB", 2025, 7, 10, 30);
        alg.agregarMedicion("CampoB", 2025, 7, 12, 25);
        alg.agregarMedicion("CampoC", 2025, 7, 12, 2000);
        // Probar mediciones promedio diario de julio 2025 (todos los campos)
        System.out.println("=== Mediciones promedio diario de julio 2025 (todos los campos) ===");
        ColaPrioridadTDA colaMes = alg.medicionesMes(2025, 7);
        while (!colaMes.colaVacia()) {
            int dia = colaMes.primero();
            int prom = colaMes.prioridad();
            System.out.printf("Día %d: %d mm\n", dia, prom);
            colaMes.desacolar();
        }

        // Probar mediciones de un campo específico en julio 2025
        System.out.println("\n=== Mediciones de CampoA en julio 2025 ===");
        ColaPrioridadTDA colaCampo = alg.medicionesCampoMes("CampoA", 2025, 7);
        while (!colaCampo.colaVacia()) {
            int dia = colaCampo.primero();
            int mm = colaCampo.prioridad();
            System.out.printf("Día %d: %d mm\n", dia, mm);
            colaCampo.desacolar();
        }

        // Mes más lluvioso en la historia
        int mesMas = alg.mesMasLluvioso();
        System.out.println("\nMes más lluvioso (todos los campos y años): " + mesMas);

        // Promedio de lluvia en un día específico
        float promDia = alg.promedioLluviaEnUnDia(2025, 7, 10);
        System.out.printf("Promedio de lluvia en 10/07/2025: %.2f mm\n", promDia);

        // Campo que recibió más lluvia en la historia
        String campoMax = alg.campoMasLLuvisoHistoria();
        System.out.println("Campo más lluvioso de la historia: " + campoMax);

        // Campos con lluvia mayor al promedio en julio 2025
        System.out.println("\n=== Campos con precipitaciones por encima del promedio en julio 2025 ===");
        ColaStringTDA camposAlto = alg.camposConLLuviaMayorPromedio(2025, 7);
        while (!camposAlto.colaVacia()) {
            System.out.println(camposAlto.primero());
            camposAlto.desacolar();
        }

        // Ejemplo de eliminación de una medición y un campo
        alg.eliminarMedicion("CampoA", 2025, 7, 11);
        System.out.println("\n-- Después de eliminar la medición del 11/07/2025 en CampoA --");
        ColaPrioridadTDA colaPostElim = alg.medicionesCampoMes("CampoA", 2025, 7);
        while (!colaPostElim.colaVacia()) {
            System.out.printf("Día %d: %d mm\n", colaPostElim.primero(), colaPostElim.prioridad());
            colaPostElim.desacolar();
        }

        alg.eliminarCampo("CampoB");
        System.out.println("\n-- Después de eliminar CampoB --");
        // Volver a calcular mes más lluvioso
        System.out.println("Mes más lluvioso ahora: " + alg.mesMasLluvioso());
    }
}
