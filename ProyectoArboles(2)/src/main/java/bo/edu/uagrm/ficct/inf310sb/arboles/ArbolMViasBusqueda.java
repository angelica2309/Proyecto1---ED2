package bo.edu.uagrm.ficct.inf310sb.arboles;

import bo.edu.uagrm.ficct.inf310sb.arboles.excepciones.ExcepcioClaveNoExiste;
import bo.edu.uagrm.ficct.inf310sb.arboles.excepciones.ExcepcionOrdenInvalido;

import java.util.*;

public class ArbolMViasBusqueda<K extends Comparable<K>, V>
        implements IArbolBusqueda<K, V> {

    protected NodoMVias<K, V> raiz;
    protected int orden;
    protected int POSICION_INVALIDA = -1;

    public ArbolMViasBusqueda() {
        this.orden = 3;
    }

    public ArbolMViasBusqueda(int orden) throws
            ExcepcionOrdenInvalido {
        if (orden < 3) {
            throw new ExcepcionOrdenInvalido();
        }
        this.orden = orden;
    }

    @Override
    public void vaciar() {
        this.raiz = NodoMVias.nodoVacio();
    }

    @Override
    public boolean esArbolVacio() {
        return NodoMVias.esNodoVacio(this.raiz);
    }

    @Override
    public int size() {
        if (this.esArbolVacio()) {
            return 0;
        }
        int cantidad = 0;
        Queue<NodoMVias<K, V>> colaDeNodos = new LinkedList<>();
        colaDeNodos.offer(this.raiz);
        while (!colaDeNodos.isEmpty()) {
            NodoMVias<K, V> nodoActual = colaDeNodos.poll();
            cantidad++;
            for (int i = 0; i < nodoActual.cantidadDeClavesNoVacias(); i++) {
                if (!nodoActual.esHijoVacio(i)) {
                    colaDeNodos.offer(nodoActual.getHijo(i));
                }
            }
            // Simula hijo derecho
            if (!nodoActual.esHijoVacio(nodoActual.cantidadDeClavesNoVacias())) {
                colaDeNodos.offer(nodoActual.getHijo(nodoActual.cantidadDeClavesNoVacias()));
            }
        }
        return cantidad;
    }

    @Override
    public int altura() {
        return altura(this.raiz);
    }

    protected int altura(NodoMVias<K, V> nodoActual) {
        if (NodoMVias.esNodoVacio(nodoActual)) {
            return 0;
        }
        int alturaMayor = 0;
        for (int i = 0; i < orden; i++) {
            int alturaDeHijos = altura(nodoActual.getHijo(i));
            if (alturaDeHijos > alturaMayor) {
                alturaMayor = alturaDeHijos;
            }
        }

        return alturaMayor + 1;
    }

    @Override
    public int nivel() {
        return nivel(this.raiz);
    }

    protected int nivel(NodoMVias<K, V> nodoActual) {
        if (NodoMVias.esNodoVacio(nodoActual)) {
            return -1;
        }
        int nivelMayor = 0;
        for (int i = 0; i < orden; i++) {
            int nivelDeHijos = nivel(nodoActual.getHijo(i));
            if (nivelDeHijos > nivelMayor) {
                nivelMayor = nivelDeHijos;
            }
        }

        return nivelMayor;
    }

    @Override
    public K minimo() {
        if (this.esArbolVacio()) {
            return null;
        }
        NodoMVias<K, V> nodoActual = this.raiz;
        NodoMVias<K, V> nodoAnterior = (NodoMVias<K, V>) NodoMVias.nodoVacio();
        while (!NodoMVias.esNodoVacio(nodoActual)) {
            nodoAnterior = nodoActual;
            nodoActual = nodoActual.getHijo(0);
        }
        return nodoAnterior.getClave(0);
    }

    @Override
    public K maximo() {
        if (this.esArbolVacio()) {
            return null;
        }
        NodoMVias<K, V> nodoActual = this.raiz;
        NodoMVias<K, V> nodoAnterior = (NodoMVias<K, V>) NodoMVias.nodoVacio();
        while (!NodoMVias.esNodoVacio(nodoActual)) {
            nodoAnterior = nodoActual;
            nodoActual = nodoActual.getHijo(nodoActual.cantidadDeClavesNoVacias());
        }
        return nodoAnterior.getClave(nodoActual.cantidadDeClavesNoVacias());
    }

    @Override
    public void insertar(K claveAInsertar, V valorAInsertar) {
        if (this.esArbolVacio()) {
            this.raiz = new NodoMVias<>(this.orden, claveAInsertar, valorAInsertar);
            return;
        }
        NodoMVias<K, V> nodoActual = this.raiz;
        while (!NodoMVias.esNodoVacio(nodoActual)) {
            int posicionClaveExiste = this.existeClaveEnNodo(nodoActual, claveAInsertar);
            if (posicionClaveExiste != POSICION_INVALIDA) {
                nodoActual.setValor(posicionClaveExiste, valorAInsertar);
                nodoActual = NodoMVias.nodoVacio();
            } else {
                if (nodoActual.esHoja()) {
                    if (nodoActual.estanClavesLlenas()) {
                        int posicionPorDondeBaja = this.porDondeBajar(nodoActual, claveAInsertar);
                        NodoMVias<K, V> nuevoHijo = new NodoMVias<>(this.orden, claveAInsertar, valorAInsertar);
                        nodoActual.setHijo(posicionPorDondeBaja, nuevoHijo);
                    } else {
                        this.insertarDatosOrdenadosEnNodo(nodoActual, claveAInsertar, valorAInsertar);

                    }
                    nodoActual = NodoMVias.nodoVacio();
                } else {
                    int posicionPorDondeBaja = this.porDondeBajar(nodoActual, claveAInsertar);
                    if (nodoActual.esHijoVacio(posicionPorDondeBaja)) {
                        NodoMVias<K, V> nuevoHijo = new NodoMVias<>(this.orden, claveAInsertar, valorAInsertar);
                        nodoActual.setHijo(posicionPorDondeBaja, nuevoHijo);
                        nodoActual = NodoMVias.nodoVacio();
                    } else {
                        nodoActual = nodoActual.getHijo(posicionPorDondeBaja);
                    }
                }
            }
        }

    }

    protected void insertarDatosOrdenadosEnNodo(NodoMVias<K, V> nodoActual, K claveAInsertar, V valorAInsertar) {
        int n = nodoActual.cantidadDeClavesNoVacias();
        int i = n;
        while (i > 0 && nodoActual.getClave(i - 1).compareTo(claveAInsertar) > 0) {
            K claveEnTurno = nodoActual.getClave(i - 1);
            V valorEnTurno = nodoActual.getValor(i - 1);
            nodoActual.setClave(i, claveEnTurno);
            nodoActual.setValor(i, valorEnTurno);
            i--;
        }
        nodoActual.setClave(i, claveAInsertar);
        nodoActual.setValor(i, valorAInsertar);
    }

    protected int porDondeBajar(NodoMVias<K, V> nodoActual, K claveAInsertar) {
        for (int i = 0; i < nodoActual.cantidadDeClavesNoVacias(); i++) {
            K claveEnTurno = nodoActual.getClave(i);
            if (claveEnTurno.compareTo(claveAInsertar) > 0) {
                return i;
            }
        }
        return nodoActual.cantidadDeClavesNoVacias();
    }

    protected int existeClaveEnNodo(NodoMVias<K, V> nodoActual, K claveABuscar) {
        for (int i = 0; i < nodoActual.cantidadDeClavesNoVacias(); i++) {
            K claveActual = nodoActual.getClave(i);
            if (claveActual.compareTo(claveABuscar) == 0) {
                return i;
            }
        }
        return POSICION_INVALIDA;
    }

    @Override
    public V eliminar(K claveAEliminar) {
        if (claveAEliminar == null) {
            throw new IllegalArgumentException("Clave  a eliminar no puede ser nula");
        }

        V valorARetornar = this.buscar(claveAEliminar);
        if (valorARetornar == null) {
            throw new ExcepcioClaveNoExiste("La clave a eliminar no existe en el árbol");
        }
        this.raiz = eliminar(this.raiz, claveAEliminar);
        return valorARetornar;
    }

    private NodoMVias<K, V> eliminar(NodoMVias<K, V> nodoActual, K claveAEliminar) {
        for (int i = 0; i < nodoActual.cantidadDeClavesNoVacias(); i++) {
            K claveActual = nodoActual.getClave(i);
            if (claveAEliminar.compareTo(claveActual) == 0) {
                if (nodoActual.esHoja()) {
                    this.eliminarDatosDeNodo(nodoActual, i);
                    if (nodoActual.cantidadDeClavesNoVacias() == 0) {
                        return NodoMVias.nodoVacio();
                    } else {
                        return nodoActual;
                    }
                }
                // si llego aca la clave esta en un nodo no hoja
                K claveDeReemplazo;
                if (this.hayHijosMasAdelante(nodoActual, i)) {
                    claveDeReemplazo = this.buscarClaveSucesoraInOrden(nodoActual, claveAEliminar);
                } else {
                    claveDeReemplazo = this.buscarClavePredecesoraInOrden(nodoActual, claveAEliminar);
                }
                V valorDeReemplazo = this.buscar(claveDeReemplazo);
                nodoActual = eliminar(nodoActual, claveDeReemplazo);
                nodoActual.setClave(i, claveDeReemplazo);
                nodoActual.setValor(i, valorDeReemplazo);
                return nodoActual;
            }
            if (claveAEliminar.compareTo(claveActual) < 0) {
                NodoMVias<K, V> supuestoNuevoHijo = this.eliminar(nodoActual.getHijo(i), claveAEliminar);
                nodoActual.setHijo(i, supuestoNuevoHijo);
                return nodoActual;
            }
        }
        // hijos derechos
        NodoMVias<K, V> supuestoNuevoHijo = this.eliminar(nodoActual.getHijo(nodoActual.cantidadDeClavesNoVacias()), claveAEliminar);
        nodoActual.setHijo(nodoActual.cantidadDeClavesNoVacias(), supuestoNuevoHijo);
        return nodoActual;
    }

    public K buscarClavePredecesoraInOrden(NodoMVias<K, V> nodoActual, K claveAEliminar) {
        List<K> recInorden = this.recorridoEnInOrden();
        int pos = recInorden.indexOf(claveAEliminar);
        return recInorden.get(pos - 1);
    }

    protected K buscarClaveSucesoraInOrden(NodoMVias<K, V> nodoActual, K claveAEliminar) {
        List<K> recInorden = this.recorridoEnInOrden();
        int pos = recInorden.indexOf(claveAEliminar);
        return recInorden.get(pos + 1);
    }

    protected boolean hayHijosMasAdelante(NodoMVias<K, V> nodoActual, int i) {
        int n = nodoActual.cantidadDeClavesNoVacias();
        for (int k = i; k < n; k++) {
            NodoMVias<K, V> hijo = nodoActual.getHijo(k + 1);
            if (hijo != null) {
                return true;
            }
        }
        return false;
    }

    protected void eliminarDatosDeNodo(NodoMVias<K, V> nodoActual, int i) {
        int n = nodoActual.cantidadDeClavesNoVacias();
        for (int k = i; k < n - 1; k++) {
            K clave = nodoActual.getClave(k + 1);
            V valor = nodoActual.getValor(k + 1);
            nodoActual.setClave(k, clave);
            nodoActual.setValor(k, valor);
        }
        nodoActual.setClave(n - 1, null);
        nodoActual.setValor(n - 1, null);
    }

    @Override
    public boolean contiene(K clave) {
        return this.buscar(clave) != null;
    }

    @Override
    public V buscar(K claveABuscar) {
        NodoMVias<K, V> nodoActual = this.raiz;
        while (!NodoMVias.esNodoVacio(nodoActual)) {
            boolean cambioDeNodo = false;
            for (int i = 0; i < nodoActual.cantidadDeClavesNoVacias() && !cambioDeNodo; i++) {
                K claveActual = nodoActual.getClave(i);
                if (claveABuscar.compareTo(claveActual) == 0) {
                    return nodoActual.getValor(i);
                }
                if (claveABuscar.compareTo(claveActual) < 0) {
                    nodoActual = nodoActual.getHijo(i);
                    cambioDeNodo = true;
                }
            }
            if (!cambioDeNodo) {
                nodoActual = nodoActual.getHijo(nodoActual.cantidadDeClavesNoVacias());
            }
        }
        return (V) NodoMVias.datoVacio();
    }

    @Override
    public List<K> recorridoEnInOrden() {
        List<K> recorrido = new ArrayList<>();
        // se necesita para una implementacion recursiva un
        // metodo amigo que haga el grueso del trabajo
        recorridoEnInOrdenRec(this.raiz, recorrido);
        return recorrido;
    }

    private void recorridoEnInOrdenRec(NodoMVias<K, V> nodoActual, List<K> recorrido) {
        if (NodoMVias.esNodoVacio(nodoActual)) {
            return;
        }

        for (int i = 0; i < nodoActual.cantidadDeClavesNoVacias(); i++) {
            // simula hijo izquierdo
            recorridoEnInOrdenRec(nodoActual.getHijo(i), recorrido);
            recorrido.add(nodoActual.getClave(i));

        }
        //simula el hijo derercho
        recorridoEnInOrdenRec(nodoActual.getHijo(nodoActual.cantidadDeClavesNoVacias()), recorrido);
    }

    @Override
    public List<K> recorridoEnPreOrden() {
        List<K> recorrido = new ArrayList<>();
        // se necesita para una implementacion recursiva un
        // metodo amigo que haga el grueso del trabajo
        recorridoEnPreOrdenRec(this.raiz, recorrido);
        return recorrido;
    }

    private void recorridoEnPreOrdenRec(NodoMVias<K, V> nodoActual, List<K> recorrido) {
        if (NodoMVias.esNodoVacio(nodoActual)) {
            return;
        }

        for (int i = 0; i < nodoActual.cantidadDeClavesNoVacias(); i++) {
            recorrido.add(nodoActual.getClave(i));
            // simula hijo izquierdo
            recorridoEnPreOrdenRec(nodoActual.getHijo(i), recorrido);
        }
        //simula el hijo derercho
        recorridoEnPreOrdenRec(nodoActual.getHijo(nodoActual.cantidadDeClavesNoVacias()), recorrido);
    }

    @Override
    public List<K> recorridoEnPostOrden() {
        List<K> recorrido = new ArrayList<>();
        // se necesita para una implementacion recursiva un
        // metodo amigo que haga el grueso del trabajo
        recorridoEnPostOrdenRec(this.raiz, recorrido);
        return recorrido;
    }

    private void recorridoEnPostOrdenRec(NodoMVias<K, V> nodoActual, List<K> recorrido) {
        if (NodoMVias.esNodoVacio(nodoActual)) {
            return;
        }
        // simula hijo izquierdo
        recorridoEnPostOrdenRec(nodoActual.getHijo(0), recorrido);
        for (int i = 0; i < nodoActual.cantidadDeClavesNoVacias(); i++) {
            //simula el hijo derercho i+1
            recorridoEnPostOrdenRec(nodoActual.getHijo(i + 1), recorrido);
            recorrido.add(nodoActual.getClave(i));
        }

    }

    @Override
    public List<K> recorridoPorNiveles() {
        List<K> recorrido = new ArrayList<>();
        if (this.esArbolVacio()) {
            return recorrido;
        }
        Queue<NodoMVias<K, V>> colaDeNodos = new LinkedList<>();
        colaDeNodos.offer(this.raiz);
        while (!colaDeNodos.isEmpty()) {
            NodoMVias<K, V> nodoActual = colaDeNodos.poll();
            for (int i = 0; i < nodoActual.cantidadDeClavesNoVacias(); i++) {
                // Simula hijo izquierdo
                recorrido.add(nodoActual.getClave(i));
                if (!nodoActual.esHijoVacio(i)) {
                    colaDeNodos.offer(nodoActual.getHijo(i));
                }
            }
            // Simula hijo derecho
            if (!nodoActual.esHijoVacio(nodoActual.cantidadDeClavesNoVacias())) {
                colaDeNodos.offer(nodoActual.getHijo(nodoActual.cantidadDeClavesNoVacias()));
            }
        }
        return recorrido;
    }

    /**
     * Método retorne la cantidad de datos vacios que hay en un arbol m vias
     */
    public int cantidadDatosVacios() {
        return cantidadDatosVacios(this.raiz);
    }

    private int cantidadDatosVacios(NodoMVias<K, V> nodoActual) {
        if (NodoMVias.esNodoVacio(nodoActual)) {
            return 0;
        }
        int cantidad = 0;

        for (int i = 0; i < orden - 1; i++) {
            // supuesto hijo izquierdo
            cantidad += cantidadDatosVacios(nodoActual.getHijo(i));
            if (nodoActual.esClaveVacia(i)) {
                cantidad++;
            }
        }
        // supuesto hijo derecho
        cantidad += cantidadDatosVacios(nodoActual.getHijo(orden - 1));
        return cantidad;
    }

    /**
     * retorna la cantidad de hojas en un arbol MVias
     *
     * @return
     */
    public int cantidadDeHojas() {
        return cantidadDeHojas(this.raiz);
    }

    private int cantidadDeHojas(NodoMVias<K, V> nodoActual) {
        if (NodoMVias.esNodoVacio(nodoActual)) {
            return 0;
        }
        if (nodoActual.esHoja()) {
            return 1;
        }
        int cantidad = 0;
        for (int i = 0; i < orden; i++) {
            cantidad += cantidadDeHojas(nodoActual.getHijo(i));
        }
        return cantidad;
    }

    /**
     * retorna la cantidad de hojas en un arbol MVias a partir del nivel n
     *
     * @return
     */
    public int cantidadDeHojasDesdeNivel(int nivelBase) {
        return cantidadDeHojasDesdeNivel(this.raiz, nivelBase, 0);
    }

    private int cantidadDeHojasDesdeNivel(NodoMVias<K, V> nodoActual, int nivelBase, int nivelActual) {
        if (NodoMVias.esNodoVacio(nodoActual)) {
            return 0;
        }
        if (nivelActual >= nivelBase) {
            if (nodoActual.esHoja()) {
                return 1;
            }
        } else {
            if (nodoActual.esHoja()) {
                return 0;
            }
        }

        int cantidad = 0;
        for (int i = 0; i < orden; i++) {
            cantidad += cantidadDeHojasDesdeNivel(nodoActual.getHijo(i), nivelBase, nivelActual + 1);
        }
        return cantidad;
    }

    /**
     * *
     * mplemente un método recursivo que retorne la cantidad de datos no vacíos
     * que hay en el nivel N de un árbol m-vias de búsqueda
     *
     */
    public int contarDatosNoVaciosEnNivel(int nivel) {
        return contarDatosNoVaciosEnNivel(this.raiz, nivel, 0);
    }

    private int contarDatosNoVaciosEnNivel(NodoMVias<K, V> nodoActual, int nivel, int n) {
        if (NodoMVias.esNodoVacio(nodoActual)) {
            return 0;
        }
        int cantidad = 0;
        if (nivel == n) {
            cantidad += nodoActual.cantidadDeClavesNoVacias();
        }
        for (int i = 0; i < nodoActual.cantidadDeClavesNoVacias(); i++) {
            cantidad += contarDatosNoVaciosEnNivel(nodoActual.getHijo(i), nivel, n + 1);
        }
        return cantidad;
    }

    /**
     * Implemente un método recursivo que retorne el nivel en que se encuentra
     * una clave que se recibe como parámetro. Devolver -1 si la clave no está
     * en el árbol
     */

    public int nivelDeLaClave(K clave) {
        return nivelDeLaClave(clave, this.raiz, 0);
    }

    private int nivelDeLaClave(K clave, NodoMVias<K, V> nodoActual, int nivel) {
        if (NodoMVias.esNodoVacio(nodoActual)) {
            return -1;
        }
        for (int i = 0; i < nodoActual.cantidadDeClavesNoVacias(); i++) {
            K claveEnTurno = nodoActual.getClave(i);
            if (claveEnTurno.compareTo(clave) == 0) {
                return nivel;
            }
            nivel = nivelDeLaClave(clave, nodoActual.getHijo(i), nivel + 1);
        }
        return -1;
    }

    public int nivelQueEstaClave(K claveABuscar) {
        return nivelQueEstaClaveRec(this.raiz, claveABuscar);
    }

    public int nivelQueEstaClaveRec(NodoMVias<K, V> nodoActual, K claveABuscar) {
        if (NodoMVias.esNodoVacio(nodoActual)) {
            return 0;
        }

        for (int i = 0; i < orden; i++) {
            int nivel = nivelQueEstaClaveRec(nodoActual.getHijo(i), claveABuscar) + 1;
            if (nodoActual.getClave(i).compareTo(claveABuscar) == 0) {
                return nivel;
            }

        }
        return -1;
    }

    /**
     * Implemente un método que retorne verdadero si solo hay hojas en el último
     * nivel de un árbol m-vias de búsqueda. Falso en caso contrario.
     */

    public boolean hayHojasEnElUltimoNivel() {
        return hayHojasEnElUltimoNivel(this.raiz, 0);
    }

    private boolean hayHojasEnElUltimoNivel(NodoMVias<K, V> nodoActual, int nivel) {
        if (NodoMVias.esNodoVacio(nodoActual)) {
            return false;
        }

        if (nivel == nivel()) {
            if (nodoActual.esHoja()) {
                return true;
            }
        }
        for (int i = 0; i < orden; i++) {
            return hayHojasEnElUltimoNivel(nodoActual.getHijo(i), nivel + 1);
        }

        return false;
    }

    /**
     * Implemente un método privado que reciba un dato como parámetro y que
     * retorne cual sería el sucesor inorden de dicho dato, sin realizar el
     * recorrido en inOrden
     */
    public K predecesorInOrden(K clave) {
        int pos = porDondeBajar(this.raiz, clave);
        return predecesorInOrdenp(this.raiz, pos);
    }

    protected K predecesorInOrdenp(NodoMVias<K, V> nodoActual, int i) {
        if (nodoActual.esHijoVacio(i)) {
            return nodoActual.getClave(i - 1);
        }
        NodoMVias<K, V> nodoAnterior = NodoMVias.nodoVacio();
        nodoActual = nodoActual.getHijo(i);
        while (!NodoMVias.esNodoVacio(nodoActual)) {
            nodoAnterior = nodoActual;
            nodoActual = nodoActual.getHijo(orden - 1);
        }
        int n = nodoAnterior.cantidadDeClavesNoVacias();
        return nodoAnterior.getClave(n - 1);
    }

    public V valorPredecesorInOrden(K clave) {
        int pos = porDondeBajar(this.raiz, clave);
        return valorPredecesorInOrdenp(this.raiz, pos);
    }

    protected V valorPredecesorInOrdenp(NodoMVias<K, V> nodoActual, int i) {
        if (nodoActual.esHijoVacio(i)) {
            return nodoActual.getValor(i - 1);
        }
        NodoMVias<K, V> nodoAnterior = NodoMVias.nodoVacio();
        nodoActual = nodoActual.getHijo(i);
        while (!NodoMVias.esNodoVacio(nodoActual)) {
            nodoAnterior = nodoActual;
            nodoActual = nodoActual.getHijo(orden - 1);
        }
        int n = nodoAnterior.cantidadDeClavesNoVacias();
        return nodoAnterior.getValor(n - 1);
    }

    private K sucesorInOrdenp(NodoMVias<K, V> nodoActual, int i) {
        if (nodoActual.esHijoVacio(i + 1)) {
            return nodoActual.getClave(i + 1);
        }
        NodoMVias<K, V> nodoAnterior = NodoMVias.nodoVacio();
        nodoActual = nodoActual.getHijo(i + 1);
        while (!NodoMVias.esNodoVacio(nodoActual)) {
            nodoAnterior = nodoActual;
            nodoActual = nodoActual.getHijo(0);
        }
        return nodoAnterior.getClave(0);
    }

    public void mostrar() {
        this.mostrar(raiz, 0);
    }

    protected void mostrar(NodoMVias<K, V> nodoActual, int cont) {
        if (nodoActual == raiz) {
            System.out.println("Arbol forma grafica");
        }
        if (!NodoMVias.esNodoVacio(nodoActual)) {
            int cant = nodoActual.cantidadDeClavesNoVacias();
            for (int i = cant; i >= 0; i--) {
                this.mostrar(nodoActual.getHijo(i), cont + 1);
                if (i > 0) {
                    for (int j = 0; j < cont; j++) {
                        System.out.print("\t\t\t");
                    }
                    System.out.println("[" + i + "]|" + nodoActual.getClave(i - 1));
                }
            }
        }
    }

}
