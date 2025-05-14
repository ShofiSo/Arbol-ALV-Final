
import java.util.Scanner;

public class ArbolAVL {

    static class Node {

        int valor, altura;
        Node izquierda, derecha;

        public Node(int valor) {
            this.valor = valor;
            altura = 1;
        }
    }

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
            }else if (valor > nodo.valor) {
                nodo.derecha = insertar(nodo.derecha, valor); 
            }else {
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
    }

    public static void main(String[] args) {
        AVLTree arbol = new AVLTree();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese numeros para insertar en el arbol AVL. Escribe 'exit' para salir del programa...");

        while (true) {
            System.out.print("Número: ");
            String entrada = scanner.nextLine();

            if (entrada.equalsIgnoreCase("exit") || entrada.equals("-1")) {
                break;
            }

            try {
                int numero = Integer.parseInt(entrada);
                arbol.raiz = arbol.insertar(arbol.raiz, numero);
                System.out.println("Árbol AVL actualizado:");
                arbol.printTree(arbol.raiz);
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida. Intente de nuevo.");
            }
        }

        scanner.close();
    }
}
