package bo.edu.uagrm.ficct.inf310sb.arboles;

import bo.edu.uagrm.ficct.inf310sb.arboles.excepciones.ExcepcionOrdenInvalido;

import java.util.Stack;

public class ArbolB<K extends Comparable<K>, V> extends ArbolMViasBusqueda<K, V> {

    private int nroMaximoDeDatos;
    private int nroMinimoDeDatos;
    private int nroMinimoDeHijos;

    public ArbolB() throws ExcepcionOrdenInvalido {
        this(3);

    }

    public ArbolB(int orden) throws
            ExcepcionOrdenInvalido {
        super(orden + 1);
        this.nroMaximoDeDatos = orden - 1;
        this.nroMinimoDeDatos = this.nroMaximoDeDatos / 2;
        this.nroMinimoDeHijos = this.nroMinimoDeDatos + 1;
    }

    @Override
    public void insertar(K claveAInsertar, V valorAInsertar) {
        if (super.esArbolVacio()) {
            //super.raiz = new NodoMVias<>(super.orden + 1, claveAInsertar, valorAInsertar);
            super.raiz = new NodoMVias<>(super.orden, claveAInsertar, valorAInsertar);
            return;
        }
        Stack<NodoMVias<K, V>> pilaDeAncestros = new Stack<>();
        NodoMVias<K, V> nodoActual = this.raiz;
        while (!NodoMVias.esNodoVacio(nodoActual)) {
            if (existeClaveEnNodo(nodoActual, claveAInsertar) != super.POSICION_INVALIDA) {
                return;
            }
            if (nodoActual.esHoja()) {
                this.insertarDatosOrdenadosEnNodo(nodoActual, claveAInsertar, valorAInsertar);
                if (nodoActual.cantidadDeClavesNoVacias() > this.nroMaximoDeDatos) {
                    dividirNodo(nodoActual, pilaDeAncestros);
                }
                nodoActual = NodoMVias.nodoVacio();
            } else {
                int posPorDondeBajar = super.porDondeBajar(nodoActual, claveAInsertar);
                pilaDeAncestros.push(nodoActual);
                nodoActual = nodoActual.getHijo(posPorDondeBajar);
            }
        }//fin del ciclo
        //super.mostrar();
    }

    private NodoMVias<K, V> subNodo(NodoMVias<K, V> nodoActual, int desde, int hasta) {
        NodoMVias<K, V> nuevoNodo = new NodoMVias<>(orden);
        int c = 0;
        for (int i = desde; i < hasta; i++) {
            nuevoNodo.setClave(c, nodoActual.getClave(i));
            nuevoNodo.setValor(c, nodoActual.getValor(i));
            nuevoNodo.setHijo(c, nodoActual.getHijo(i));
            c++;
        }
        nuevoNodo.setHijo(c, nodoActual.getHijo(hasta));
        return nuevoNodo;
    }

    private void dividirNodo(NodoMVias<K, V> nodoActual, Stack<NodoMVias<K, V>> pilaDeAncestros) {
        int posQueSube = this.nroMinimoDeDatos;
        K claveQueSube = nodoActual.getClave(posQueSube);
        V valorQueSube = nodoActual.getValor(posQueSube);
        NodoMVias<K, V> nodoPadre = pilaDeAncestros.isEmpty() ? new NodoMVias<>(orden) : pilaDeAncestros.pop();
        NodoMVias<K, V> nodoHijoIzq = this.subNodo(nodoActual, 0, posQueSube);
        NodoMVias<K, V> nodoHijoDer = this.subNodo(nodoActual, posQueSube + 1, orden - 1);
        int pos = super.porDondeBajar(nodoPadre, claveQueSube);
        super.insertarDatosOrdenadosEnNodo(nodoPadre, claveQueSube, valorQueSube);
        this.recorrerHijosDivision(nodoPadre, pos);
        nodoPadre.setHijo(pos, nodoHijoIzq);
        nodoPadre.setHijo(pos + 1, nodoHijoDer);
        if (pilaDeAncestros.isEmpty()) {
            raiz = nodoPadre;
        }
        if (nodoPadre.cantidadDeClavesNoVacias() > this.nroMaximoDeDatos) {
            this.dividirNodo(nodoPadre, pilaDeAncestros);
        }
    }

    private void recorrerHijosDivision(NodoMVias<K, V> nodoActual, int pos) {
        for (int i = nodoActual.cantidadDeClavesNoVacias(); i > pos; i--) {
            nodoActual.setHijo(i, nodoActual.getHijo(i - 1));
        }
    }

    private boolean claveEnNodo(NodoMVias<K, V> nodoActual, K clave) {
        for (int i = 0; i < nodoActual.cantidadDeClavesNoVacias(); i++) {
            if (clave.compareTo(nodoActual.getClave(i)) == 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public V eliminar(K claveAEliminar) {
        Stack<NodoMVias<K, V>> pilaDeAncestros = new Stack<>();
        NodoMVias<K, V> nodoActual = raiz;
        if (NodoMVias.esNodoVacio(nodoActual)) {
            return null;
        }
        if (!super.contiene(claveAEliminar)) {
            return null;
        }
        while (!NodoMVias.esNodoVacio(nodoActual)) {
            int posicionDelDato = super.porDondeBajar(nodoActual, claveAEliminar) - 1;
            if (nodoActual.esHoja()) {
                super.eliminarDatosDeNodo(nodoActual, posicionDelDato);
                if (nodoActual.cantidadDeClavesNoVacias() < this.nroMinimoDeDatos) {
                    if (pilaDeAncestros.isEmpty()) {
                        if (nodoActual.cantidadDeClavesNoVacias() == 0) { //nodoActual es la raiz
                            super.vaciar();
                        }
                    } else {
                        this.prestarseOFusionarse(nodoActual, pilaDeAncestros);
                    }
                }
                return null;
            } else {
                if (this.claveEnNodo(nodoActual, claveAEliminar)) {
                    K clavePredecesor = super.predecesorInOrden(claveAEliminar);
                    V datoPredecesor = super.valorPredecesorInOrden(claveAEliminar);
                    NodoMVias<K, V> nodoDelPredecesor = buscarNodoDelPredecesor(nodoActual,
                            clavePredecesor, pilaDeAncestros);
                    int posDelPredecesor = nodoDelPredecesor.cantidadDeClavesNoVacias() - 1;
                    nodoActual.setClave(posicionDelDato, clavePredecesor);
                    nodoActual.setValor(posicionDelDato, datoPredecesor);
                    super.eliminarDatosDeNodo(nodoDelPredecesor, posDelPredecesor);
                    if (nodoDelPredecesor.cantidadDeClavesNoVacias() < this.nroMinimoDeDatos) {
                        this.prestarseOFusionarse(nodoDelPredecesor, pilaDeAncestros);
                    }
                    nodoActual = NodoMVias.nodoVacio();
                } else {
                    int pos = super.porDondeBajar(nodoActual, claveAEliminar);
                    pilaDeAncestros.push(nodoActual);
                    nodoActual = nodoActual.getHijo(pos);
                }
            }
        }
        return null;
    }

    private int posicionHijoNulo(NodoMVias<K, V> nodoActual, int cant) {
        if (NodoMVias.esNodoVacio(nodoActual)) {
            return -1;
        }
        for (int i = 0; i <= cant; i++) {
            NodoMVias<K, V> nodoHijo = nodoActual.getHijo(i);
            if (nodoHijo.cantidadDeClavesNoVacias() < this.nroMinimoDeDatos) {
                return i;
            }
        }
        return -1;
    }

    private int indiceDeFusion(NodoMVias<K, V> nodoPadre, int posNodoHijo) {
        int cant = nodoPadre.cantidadDeClavesNoVacias();
        NodoMVias<K, V> nodoHermanoDer = posNodoHijo != cant ? nodoPadre.getHijo(posNodoHijo + 1)
                : NodoMVias.nodoVacio();
        return !NodoMVias.esNodoVacio(nodoHermanoDer) ? 1 : -1;
    }

    private int indiceDePrestamo(NodoMVias<K, V> nodoPadre, int posNodoHijo) {
        int cant = nodoPadre.cantidadDeClavesNoVacias();
        NodoMVias<K, V> nodoHermanoDer = posNodoHijo != cant ? nodoPadre.getHijo(posNodoHijo + 1)
                : NodoMVias.nodoVacio();
        NodoMVias<K, V> nodoHermanoIzq = posNodoHijo > 0 ? nodoPadre.getHijo(posNodoHijo - 1)
                : NodoMVias.nodoVacio();
        if (!NodoMVias.esNodoVacio(nodoHermanoDer)
                && nodoHermanoDer.cantidadDeClavesNoVacias() > this.nroMinimoDeDatos) {
            return 1;
        } else if (!NodoMVias.esNodoVacio(nodoHermanoIzq)
                && nodoHermanoIzq.cantidadDeClavesNoVacias() > this.nroMinimoDeDatos) {
            return -1;
        }
        return 0;
    }

    private NodoMVias<K, V> buscarNodoDelPredecesor(NodoMVias<K, V> nodoActual, K clave,
            Stack<NodoMVias<K, V>> pilaDeAncestros) {
        if (NodoMVias.esNodoVacio(nodoActual)) {
            return pilaDeAncestros.pop();
        }
        int pos = super.porDondeBajar(nodoActual, clave);
        pilaDeAncestros.push(nodoActual);
        return this.buscarNodoDelPredecesor(nodoActual.getHijo(pos), clave, pilaDeAncestros);
    }

    private void prestarseOFusionarse(NodoMVias<K, V> nodoActual, Stack<NodoMVias<K, V>> pilaDeAncestros) {
        NodoMVias<K, V> nodoPadre = pilaDeAncestros.peek();
        int posNodoEliminado = this.posicionHijoNulo(nodoPadre, nodoPadre.cantidadDeClavesNoVacias());
        int indicePrestamo = this.indiceDePrestamo(nodoPadre, posNodoEliminado);
        if (indicePrestamo != 0 && nodoActual.esHoja()) {
            this.prestamoDato(nodoActual, indicePrestamo, pilaDeAncestros);
        } else {
            int indFusion = this.indiceDeFusion(nodoPadre, posNodoEliminado);
            if (indFusion == 1) {
                this.fusionPorDerecha(nodoActual, pilaDeAncestros);
            } else {
                this.fusionPorIzquierda(nodoActual, pilaDeAncestros);
            }
        }
    }

    private void prestamoDato(NodoMVias<K, V> nodoActual, int indicePrestamo,
            Stack<NodoMVias<K, V>> pilaDeAncestros) {
        if (indicePrestamo == 1) {
            System.out.println("Prestamo por der");
        } else {
            System.out.println("Prestamo por izq");
        }
        NodoMVias<K, V> nodoPadre = pilaDeAncestros.pop();
        int posNodoEliminado = this.posicionHijoNulo(nodoPadre, nodoPadre.cantidadDeClavesNoVacias());
        //int cant = nodoPadre.cantidadDeDatosNoVacios();
        NodoMVias<K, V> nodoHermano = nodoPadre.getHijo(posNodoEliminado + indicePrestamo);
        int posDatoPadre = indicePrestamo > 0 ? posNodoEliminado
                : posNodoEliminado - 1;
        K clavePadre = nodoPadre.getClave(posDatoPadre);
        V datoPadre = nodoPadre.getValor(posDatoPadre);
        int posDatoHermano = indicePrestamo > 0 ? 0 : nodoHermano.cantidadDeClavesNoVacias() - 1;
        K claveHermano = nodoHermano.getClave(posDatoHermano);
        V datoHermano = nodoHermano.getValor(posDatoHermano);
        super.eliminarDatosDeNodo(nodoPadre, posDatoPadre);
        super.eliminarDatosDeNodo(nodoHermano, posDatoHermano);
        super.insertarDatosOrdenadosEnNodo(nodoActual, clavePadre, datoPadre);
        super.insertarDatosOrdenadosEnNodo(nodoActual, claveHermano, datoHermano);
    }

    private void eliminarHijo(NodoMVias<K, V> nodoPadre, int posicion, int cantHijos) {
        for (int i = posicion; i < cantHijos; i++) {
            nodoPadre.setHijo(i, nodoPadre.getHijo(i + 1));
        }
    }

    private void insertarHijosFusion(NodoMVias<K, V> nodoAInsertar, NodoMVias<K, V> nodoDeLosHijos,
            int posAInsertar, int posPrimerHijo) {
        int cant2 = nodoDeLosHijos.cantidadDeHijosNoVacios();
        int cont = posAInsertar;
        for (int i = posPrimerHijo; i < cant2; i++) {
            nodoAInsertar.setHijo(cont, nodoDeLosHijos.getHijo(i));
            cont++;
        }
    }

    private void insertarDatosFusion(NodoMVias<K, V> nodoActual, NodoMVias<K, V> nodoHermano) {
        for (int i = 0; i < nodoHermano.cantidadDeClavesNoVacias(); i++) {
            super.insertarDatosOrdenadosEnNodo(nodoActual, nodoHermano.getClave(i), nodoHermano.getValor(i));
        }
    }

    private void fusionPorDerecha(NodoMVias<K, V> nodoActual, Stack<NodoMVias<K, V>> pilaDeAncestros) {
        NodoMVias<K, V> nodoPadre = pilaDeAncestros.pop();
        int cantDatosPadre = nodoPadre.cantidadDeClavesNoVacias();
        int cantHijosPadre = cantDatosPadre + 1;
        int posNodoNulo = this.posicionHijoNulo(nodoPadre, cantDatosPadre);
        K clavePadre = nodoPadre.getClave(posNodoNulo);
        V datoPadre = nodoPadre.getValor(posNodoNulo);
        NodoMVias<K, V> nodoHermano = nodoPadre.getHijo(posNodoNulo + 1);
        K claveHermano = nodoHermano.getClave(0);
        V datoHermano = nodoHermano.getValor(0);
        NodoMVias<K, V> nodoPredecesorPadre = nodoHermano.getHijo(0);
        super.insertarDatosOrdenadosEnNodo(nodoActual, clavePadre, datoPadre);
        super.insertarDatosOrdenadosEnNodo(nodoActual, claveHermano, datoHermano);
        super.eliminarDatosDeNodo(nodoPadre, posNodoNulo);
        super.eliminarDatosDeNodo(nodoHermano, 0);
        if (nodoActual.esHoja()) {
            this.eliminarHijo(nodoPadre, posNodoNulo + 1, cantHijosPadre);
        } else {
            nodoActual.setHijo(1, nodoPredecesorPadre);
            this.insertarDatosFusion(nodoActual, nodoHermano);
            this.insertarHijosFusion(nodoActual, nodoHermano, 2, 1);
            this.eliminarHijo(nodoPadre, posNodoNulo + 1, cantHijosPadre);
        }
        if (nodoPadre.cantidadDeClavesNoVacias() == 0) {
            if (nodoPadre == raiz) {
                raiz = nodoActual;
                nodoPadre = nodoActual;
            }
        }
        if (nodoPadre.cantidadDeClavesNoVacias() < this.nroMinimoDeDatos) {
            this.prestarseOFusionarse(nodoPadre, pilaDeAncestros);
        } else if (nodoActual.cantidadDeClavesNoVacias() > this.nroMaximoDeDatos) {
            pilaDeAncestros.push(nodoPadre);
            this.dividirNodo(nodoActual, pilaDeAncestros);
        }

    }

    private void fusionPorIzquierda(NodoMVias<K, V> nodoActual, Stack<NodoMVias<K, V>> pilaDeAncestros) {
        NodoMVias<K, V> nodoPadre = pilaDeAncestros.pop();
        int cantDatosPadre = nodoPadre.cantidadDeClavesNoVacias();
        int cantHijosPadre = cantDatosPadre + 1;
        int posNodoNulo = this.posicionHijoNulo(nodoPadre, cantDatosPadre);
        K clavePadre = nodoPadre.getClave(cantDatosPadre - 1);
        V datoPadre = nodoPadre.getValor(cantDatosPadre - 1);
        NodoMVias<K, V> nodoHermano = nodoPadre.getHijo(posNodoNulo - 1);
        super.insertarDatosOrdenadosEnNodo(nodoActual, clavePadre, datoPadre);
        super.eliminarDatosDeNodo(nodoPadre, cantDatosPadre - 1);
        if (nodoActual.esHoja()) {
            this.eliminarHijo(nodoPadre, posNodoNulo, cantHijosPadre);
        } else {
            this.insertarHijosFusion(nodoHermano, nodoActual, nodoHermano.cantidadDeClavesNoVacias(),
                    nodoActual.cantidadDeClavesNoVacias());
        }
        if (nodoPadre.cantidadDeClavesNoVacias() == 0) {
            if (nodoPadre == raiz) {
                raiz = nodoHermano;
                nodoPadre = nodoHermano;
            }
        }
        if (nodoPadre.cantidadDeClavesNoVacias() < this.nroMinimoDeDatos) {
            this.prestarseOFusionarse(nodoPadre, pilaDeAncestros);
        }
    }

}
