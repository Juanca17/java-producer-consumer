# Productor Consumidor con hilos en Java
Programa Productor/Consumidor. Ejecución en diferentes hilos + GUI para entradas/salidas.

## Restricciones
### Entradas
- Número productores (1 - 10).
- Número consumidores (1 - 10).
- Tiempo de espera (0 ms a 10,000 ms) para productor/consumidor antes de su siguiente ciclo.
- Tamaño del buffer (1 - 100).
- Rango de valores para las operaciones en scheme: [0, 9].
- Operadores para las operaciones en scheme: `+, -, *, /`.

## Salidas
- Lista de tareas por hacer (operación e identificador del productor).
- Número de tareas por hacer (cantidad, porcentaje, barra de progreso, etc.)
- Lista de tareas realizadas (operación, resultado e identificador del consumidor).
- Número de tareas realizadas (cantidad).

## Actuadores
- Botón de inicio (limpia las SALIDAS, valida las ENTRADAS, crea los hilos (productores/consumidores) e inicia la ejecución).
- Botón de paro (detiene/mata los hilos).
