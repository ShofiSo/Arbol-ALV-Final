class Node {
    int valor, altura;
    Node izquierda, derecha;

    public Node(int valor) {
        this.valor = valor;
        altura = 1;
    }
}

class AVLTree {
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
}