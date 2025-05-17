1. Resumen general:
El código implementa un Árbol AVL, que es un árbol binario de búsqueda auto-balanceado. El objetivo principal del árbol AVL es mantener el balance de la altura entre los subárboles izquierdo y derecho para que las operaciones de inserción, búsqueda y eliminación se realicen en tiempo logarítmico O(log n).

El programa permite insertar números, imprimir el árbol y buscar números dentro del árbol mediante comandos ingresados por consola.

Conceptos Clave

Árbol binario de búsqueda (BST): estructura de datos donde para cada nodo, los valores del subárbol izquierdo son menores y los del derecho son mayores.

Árbol AVL: BST auto-balanceado que mantiene la propiedad de que la diferencia de altura entre subárboles izquierdo y derecho no sea mayor que 1.

Rotaciones: operaciones que reestructuran el árbol para mantenerlo balanceado después de inserciones o eliminaciones.

Recursión: el código usa recursión para insertar y buscar valores en el árbol.

2. Clases y estructura de mi código:
Clase Node
Esta clase representa cada nodo del árbol AVL.

******************************************
static class Node {

        int valor, altura;
        Node izquierda, derecha;

        public Node(int valor) {
            this.valor = valor;
            altura = 1;
        }
    }
******************************************

Atributos:

int valor: almacena el valor numérico del nodo.

int altura: almacena la altura del nodo (número de niveles desde este nodo hasta la hoja más baja).

Node izquierda: referencia al nodo hijo izquierdo.

Node derecha: referencia al nodo hijo derecho.

Constructor:

Inicializa el valor con el dato recibido y la altura en 1 (un nodo nuevo es una hoja inicialmente).

3. Clase AVLTree
Esta clase contiene toda la lógica del árbol AVL, como inserción, balanceo, rotaciones y búsqueda.

Atributo:

Node raiz: nodo raíz del árbol AVL.
Métodos principales de la clase AVLTree
int getAltura(Node nodo)
Devuelve la altura del nodo. Si el nodo es null devuelve 0.

int getFactorBalance(Node nodo)
Calcula y devuelve el factor de balance de un nodo, que es la diferencia entre la altura del subárbol izquierdo y la del derecho.

Factor balance = altura(subárbol izquierdo) - altura(subárbol derecho).

int max(int a, int b)
Devuelve el máximo entre dos valores enteros.

Rotaciones para mantener el balance del árbol AVL:
Son necesarias para asegurar que la diferencia de altura entre subárboles no sea mayor que 1.

Node rotarDerecha(Node y)
Realiza una rotación simple a la derecha, usada cuando el árbol está desbalanceado a la izquierda.

Node rotarIzquierda(Node x)
Realiza una rotación simple a la izquierda, usada cuando el árbol está desbalanceado a la derecha.

Node insertar(Node nodo, int valor)
Inserta un nuevo valor en el árbol de manera recursiva y actualiza el balance del árbol mediante rotaciones si es necesario.
Pasos:

Insertar el valor en la posición correspondiente (como en un árbol binario de búsqueda).

Actualizar la altura del nodo.

Calcular el factor de balance.

Realizar rotaciones si el árbol está desbalanceado (4 casos: Izquierda-Izquierda, Derecha-Derecha, Izquierda-Derecha, Derecha-Izquierda).

void printTree(Node nodo)
Imprime el árbol en consola con una visualización que muestra la estructura jerárquica.

void imprimir(Node nodo, int nivel)
Método auxiliar para printTree, imprime los nodos con indentación según el nivel para simular la estructura del árbol.

boolean buscar(Node nodo, int valor)
Busca recursivamente un valor en el árbol.

Devuelve true si el valor está en el árbol.

Devuelve false si no se encuentra.

******************************************
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
******************************************
4. Método main
Controla la interacción con el usuario desde la consola.

Crea una instancia de AVLTree y un Scanner para entrada de datos.

Instruye al usuario para que pueda:

Insertar números (cualquier entero).

Escribir 0 para buscar un número dentro del árbol.

Escribir exit o -1 para terminar el programa.

En el bucle while(true):

Lee la entrada del usuario.

Si es exit o -1, termina el programa.

Si es 0, solicita un número para buscar y muestra si está o no en el árbol.

Si es un número entero, lo inserta en el árbol y muestra el árbol actualizado.

Si la entrada no es válida, muestra un mensaje de error.

******************************************
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
******************************************