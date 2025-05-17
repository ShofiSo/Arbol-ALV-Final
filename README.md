1. Introducción
El presente documento describe la implementación de un Árbol AVL en el lenguaje de programación Java. 
Un árbol AVL es un árbol binario de búsqueda auto-balanceado que mantiene la diferencia de altura entre subárboles en un rango de -1 a 1, permitiendo operaciones eficientes de inserción y búsqueda.

2.Explicación del Código
Clase Principal: ArbolAVL
Esta clase contiene la estructura del programa, donde se definen las clases internas Node y AVLTree, y el método main que interactúa con el usuario.

***************************************
public class ArbolAVL {
***************************************

-Clase Node
Representa un nodo del árbol.

-Valor: el número que contiene el nodo.
-Altura: la altura del nodo (1 si es hoja).
-Izquierda y derecha: apuntan a los nodos hijos.
-Atributos:
-int valor: almacena el valor numérico del nodo.
-int altura: almacena la altura del nodo (número de niveles desde este nodo hasta la hoja más baja).
-Node izquierda: referencia al nodo hijo izquierdo.
-Node derecha: referencia al nodo hijo derecho.
-Constructor: Inicializa el valor con el dato recibido y la altura en 1 (un nodo nuevo es una hoja inicialmente).

***************************************
static class Node {


        int valor, altura;
        Node izquierda, derecha;


        public Node(int valor) {
            this.valor = valor;
            altura = 1;
        }
    }
***************************************

-Clase AVLTree
Contiene la lógica del árbol AVL: inserción, balanceo, rotaciones y búsqueda.
-Método: getAltura
Devuelve la altura de un nodo.
-Método: getFactorBalance
Calcula la diferencia de altura entre subárboles.
-Método: max
Devuelve el mayor de dos enteros.
-Rotaciones
Balancean el árbol cuando se detecta desbalance.
-Inserción con rebalanceo
Inserta un nodo y balancea el árbol si es necesario.

***************************************
static class AVLTree {


        Node raiz;


        int getAltura(Node nodo) {
            return nodo == null ? 0 : nodo.altura;
        }


        int getFactorBalance(Node nodo) {
            return nodo == null ? 0 : getAltura(nodo.izquierda) - getAltura(nodo.derecha);
        }


        int max(int a, int b) {
            return a > b ? a : b;
        }


        Node rotarDerecha(Node y) {
            Node x = y.izquierda;
            Node T2 = x.derecha;


            x.derecha = y;
            y.izquierda = T2;


            y.altura = max(getAltura(y.izquierda), getAltura(y.derecha)) + 1;
            x.altura = max(getAltura(x.izquierda), getAltura(x.derecha)) + 1;


            return x;
        }


        Node rotarIzquierda(Node x) {
            Node y = x.derecha;
            Node T2 = y.izquierda;


            y.izquierda = x;
            x.derecha = T2;


            x.altura = max(getAltura(x.izquierda), getAltura(x.derecha)) + 1;
            y.altura = max(getAltura(y.izquierda), getAltura(y.derecha)) + 1;


            return y;
        }


        Node insertar(Node nodo, int valor) {
            if (nodo == null) {
                return new Node(valor);
            }


            if (valor < nodo.valor) {
                nodo.izquierda = insertar(nodo.izquierda, valor);
            } else if (valor > nodo.valor) {
                nodo.derecha = insertar(nodo.derecha, valor);
            } else {
                return nodo;
            }


            nodo.altura = 1 + max(getAltura(nodo.izquierda), getAltura(nodo.derecha));
            int balance = getFactorBalance(nodo);


            if (balance > 1 && valor < nodo.izquierda.valor) {
                return rotarDerecha(nodo);
            }


            if (balance < -1 && valor > nodo.derecha.valor) {
                return rotarIzquierda(nodo);
            }


            if (balance > 1 && valor > nodo.izquierda.valor) {
                nodo.izquierda = rotarIzquierda(nodo.izquierda);
                return rotarDerecha(nodo);
            }


            if (balance < -1 && valor < nodo.derecha.valor) {
                nodo.derecha = rotarDerecha(nodo.derecha);
                return rotarIzquierda(nodo);
            }


            return nodo;
        }
***************************************

-Impresión del árbol
Visualiza el árbol con indentaciones y la búsqueda del valor a través del método booleano.

***************************************
void printTree(Node nodo) {
            imprimir(nodo, 0);
        }


        void imprimir(Node nodo, int nivel) {
            if (nodo == null) {
                return;
            }


            imprimir(nodo.derecha, nivel + 1);


            for (int i = 0; i < nivel; i++) {
                System.out.print("    ");
            }
            System.out.println(nodo.valor);


            imprimir(nodo.izquierda, nivel + 1);
        }


        boolean buscar(Node nodo, int valor) {
            if (nodo == null) {
                return false;
            }
            if (valor == nodo.valor) {
                return true;
            } else if (valor < nodo.valor) {
                return buscar(nodo.izquierda, valor);
            } else {
                return buscar(nodo.derecha, valor);
            }
        }
    }
***************************************
-Método main
Permite al usuario insertar, buscar y salir mediante comandos por consola.
-Crea una instancia de AVLTree y un Scanner para entrada de datos.
-Instruye al usuario para que pueda:
-Insertar números (cualquier entero).
-Escribir 0 para buscar un número dentro del árbol.
-Escribir exit o -1 para terminar el programa.
-En el bucle while(true):
-Lee la entrada del usuario.
-Si es exit o -1, termina el programa.
-Si es 0, solicita un número para buscar y muestra si está o no en el árbol.
-Si es un número entero, lo inserta en el árbol y muestra el árbol actualizado.
-Si la entrada no es válida, muestra un mensaje de error.

***************************************
public static void main(String[] args) {
        AVLTree arbol = new AVLTree();
        Scanner scanner = new Scanner(System.in);


        System.out.println("Ingrese numeros para insertar en el arbol AVL.");
        System.out.println("Escribe '0' para buscar un numero, 'exit' o '-1' para salir.");


        while (true) {
            System.out.print("Comando o numero: ");
            String entrada = scanner.nextLine();


            if (entrada.equalsIgnoreCase("exit") || entrada.equals("-1")) {
                break;
            }


            if (entrada.equals("0")) {
                System.out.print("Numero a buscar: ");
                String valorBuscar = scanner.nextLine();
                try {
                    int numBuscar = Integer.parseInt(valorBuscar);
                    boolean encontrado = arbol.buscar(arbol.raiz, numBuscar);
                    if (encontrado) {
                        System.out.println("Si esta el numero en el arbol");
                    } else {
                        System.out.println("No esta el numero en el arbol");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Entrada no valida para busqueda.");
                }
                continue;
            }


            try {
                int numero = Integer.parseInt(entrada);
                arbol.raiz = arbol.insertar(arbol.raiz, numero);
                System.out.println("Arbol AVL ACTUALIZADO:");
                arbol.printTree(arbol.raiz);
            } catch (NumberFormatException e) {
                System.out.println("Entrada no valida. Intente de nuevo.");
            }
        }


        scanner.close();
    }
}
***************************************

3. Ejecución del Código
-Cuando ingresamos al programa nos debe mostrar las indicaciones que configuramos, los comandos para buscar, insertar números en el árbol o salir del programa.
-Ingresamos los números según se requiera, y nos debe ir mostrando el árbol actualizado.
-Para buscar o verificar que tal número se encuentra dentro del árbol, ingresamos el número “0”, el cuál nos debe mostrar en consola el mensaje: “Si está el número en el árbol” o “No está el número en el árbol”, según sea el caso.
-Podemos seguir ingresando números según necesitamos y nos debe mostrar el árbol actualizado.
-Por último, podemos escribir -1 o “exit” para salir del programa y terminar la ejecución del código.

4. Conclusión
Este código es una implementación funcional de un árbol AVL que permite:

-Insertar valores manteniendo el balance del árbol.
-Buscar valores de forma eficiente.
-Mostrar la estructura del árbol de forma visual en consola.
-Interactuar con el usuario mediante consola con opciones claras para insertar, buscar y salir.

5. Enlace Repositorio GitHub
-https://github.com/ShofiSo/Arbol-ALV-Final

6. Enlace Video Youtube:
-https://youtu.be/IGlYXKm4opM

