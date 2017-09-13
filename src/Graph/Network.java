/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Graph;

import NonLinearStructs.Heap;
import Exceptions.EmptyCollectionException;
import LinearStructs.ArrayUnorderedList;
import LinearStructs.LinkedQueue;
import LinearStructs.LinkedStack;
import java.util.Iterator;

/* ESCOLA SUPERIOR DE TECNOLOGIA E GESTÃO DE FELGUEIRAS 
 * LEI - 2012/2013
 *
 * Authors: Luís Sousa [8090228] & Rui Vieira [8100451]
 * 
 */
/**
 * Network represents an adjacency matrix implementation of a network.
 */
public class Network<T> extends Graph<T> implements NetworkADT<T> {

    private Weight[][] adjMatrix;

    /**
     * Cria uma network vazia.
     */
    public Network() {
        numVertices = 0;
        this.adjMatrix = new Weight[DEFAULT_CAPACITY][DEFAULT_CAPACITY];
        this.vertices = (T[]) (new Object[DEFAULT_CAPACITY]);
    }

    /**
     * Devolve a representação da matriz de adjacâncias da Network, na forma de
     * uma string.
     *
     * @return a representação da matriz de adjacâncias da Network, na forma de
     * uma string
     */
    @Override
    public String toString() {
        if (numVertices == 0) {
            return "Network vazia!";
        }

        String result = "";

        result += "\n\nValores do Vértice";
        result += "\n------------------\n";
        result += "índice\tvalor\n\n";

        for (int i = 0; i < numVertices; i++) {
            result += "" + i + "\t";
            result += vertices[i].toString() + "\n";
        }

        result += "\n\n   Matriz de Adjacências\n";
        result += "---------------------------\n";
        result += "índice";

        for (int i = 0; i < numVertices; i++) {
            result += "\t\t" + vertices[i];

        }
        result += "\n\n";

        for (int i = 0; i < numVertices; i++) {
            result += vertices[i] + "\t";

            for (int j = 0; j < numVertices; j++) {
                if (getAdjMatrix()[i][j] != null) {
                    result += "(" + getAdjMatrix()[i][j].getDistance() + "," + getAdjMatrix()[i][j].getQuality() + "," + getAdjMatrix()[i][j].getNormalHour() + "," + getAdjMatrix()[i][j].getPeakHour() + "," + getAdjMatrix()[i][j].getResult() + ")\t";
                } else {
                    result += "0" + "        ";
                }
            }
            result += "\n";
        }

        result += "\n\nPesos das Arestas";
        result += "\n-----------------\n";
        result += "aresta\tpeso\n\n";

        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                if (getAdjMatrix()[i][j] != null && getAdjMatrix()[i][j].getResult() != 0) {
                    result += " -->" + "(" + vertices[i].toString() + "," + vertices[j].toString() + ")-";
                    result += " -->" + getAdjMatrix()[i][j].getResult();
                }
            }
            result += "\n";
        }


        return result;
    }

    /**
     * Insere uma aresta pesada entre os dois vértices especificados.
     *
     * @param vertex1 o primeiro vértice
     * @param vertex2 o segundo vértice
     * @param weight o peso da aresta
     */
    protected void addEdge(int index1, int index2, Weight weight) {
        if (indexIsValid(index1) && indexIsValid(index2)) {
            getAdjMatrix()[index1][index2] = weight;
        }
    }

    /**
     * Remove a aresta entre os índices especificados.
     *
     * @param index1 o primeiro índice
     * @param index2 o segundo índice
     */
    @Override
    public void removeEdge(int index1, int index2) {
        if (indexIsValid(index1) && indexIsValid(index2)) {
            getAdjMatrix()[index1][index2] = new Weight();
        }
    }

    /**
     * Adiciona uma aresta entre os vértices especificados, com o peso indicado.
     *
     * @param vertex1 o primeiro vértice
     * @param vertex2 o segundo vértice
     * @param weight o peso da aresta
     */
    @Override
    public void addEdge(T vertex1, T vertex2, Weight weight) {
        addEdge(getIndex(vertex1), getIndex(vertex2), weight);
    }

    /**
     * Adiciona uma aresta não pesada entre os vértices especificados.
     *
     * @param vertex1 o primeiro vértice
     * @param vertex2 o segundo vértice
     */
    @Override
    public void addEdge(T vertex1, T vertex2) {
        addEdge(getIndex(vertex1), getIndex(vertex2), new Weight());
    }

    /**
     * Remove a aresta entre os vértices especificados.
     * Lança uma throws Excepcao se a Network estiver vazia.
     *
     * @param vertex1 o primeiro vértice
     * @param vertex2 o segundo vértice
     * @throws Execao se a Network estiver vazia
     */
    @Override
    public void removeEdge(T vertex1, T vertex2) {


        removeEdge(getIndex(vertex1), getIndex(vertex2));
    }

    /**
     * Adiciona o vértice especificado à Network.
     *
     * @param vertex o vértice a adicionar à Network
     */
    @Override
    public void addVertex(T vertex) {
        if (containsVertex(vertices, vertex) == false) {
            if (numVertices == vertices.length) {
                expandCapacity();
            }

            vertices[numVertices] = vertex;
            for (int i = 0; i <= numVertices; i++) {
                getAdjMatrix()[numVertices][i] = new Weight();
                getAdjMatrix()[i][numVertices] = new Weight();
            }
            numVertices++;
        }
    }

    protected boolean containsVertex(T[] vertices, T vertex) {
        boolean result = false;
        int i;

        for (i = 0; i < vertices.length; i++) {
            if (vertices[i] == (vertex)) {
                result = true;
            }
        }
        return result;
    }

    /**
     * Remove o vértice no índice especificado.
     *
     * @param index índice do vértice a remover
     */
    @Override
    public void removeVertex(int index) {
        if (indexIsValid(index)) {
            numVertices--;

            for (int i = index; i < numVertices; i++) {
                vertices[i] = vertices[i + 1];
            }

            for (int i = index; i < numVertices; i++) {
                for (int j = 0; j <= numVertices; j++) {
                    getAdjMatrix()[i][j] = getAdjMatrix()[i + 1][j];
                }
            }

            for (int i = index; i < numVertices; i++) {
                for (int j = 0; j < numVertices; j++) {
                    getAdjMatrix()[j][i] = getAdjMatrix()[j][i + 1];
                }
            }
        }
    }

    /**
     * Remove o vértice especificado.
     *  Lança uma Execao se a Network estiver vazia.
     *
     * @param vertex o vértice a remover
     * @throws Excepcao se a Network estiver vazia
     */
    @Override
    public void removeVertex(T vertex) {
        for (int i = 0; i < numVertices; i++) {
            if (vertex.equals(vertices[i])) {
                removeVertex(i);
                return;
            }
        }
    }

    /**
     * Devolve um iterador que irá realizar a travessia em profundidade
     * (depth-first traversal), iniciando-a no vértice especificado.
     * Lança uma Excecao se a Network estiver vazia.
     *
     * @param startVertex o vértice onde irá começar a travessia
     * @return um iterador de travessia em profundidade, iniciado no vértice
     * especificado
     * @throws Excepcao se a Network estiver vazia
     */
    @Override
    public Iterator<T> iteratorDFS(T startVertex) throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("Network vazia!");
        }

        return iteratorDFS(getIndex(startVertex));
    }

    /**
     * Devolve um iterador que irá realizar a travessia em profundidade
     * (depth-first traversal), iniciando-a no indice especificado.
     *
     * @param startIndex o índice onde irá começar a travessia
     * @return um iterador de travessia em profundidade, iniciado no índice
     * especificado
     */
    @Override
    public Iterator<T> iteratorDFS(int startIndex) throws EmptyCollectionException {
        Integer x;
        boolean found;
        LinkedStack<Integer> traversalStack = new LinkedStack<Integer>();
        ArrayUnorderedList<T> resultList = new ArrayUnorderedList<T>();
        boolean[] visited = new boolean[numVertices];

        if (!indexIsValid(startIndex)) {
            return resultList.iterator();
        }

        for (int i = 0; i < numVertices; i++) {
            visited[i] = false;
        }

        traversalStack.push(new Integer(startIndex));
        resultList.addToRear(vertices[startIndex]);
        visited[startIndex] = true;

        while (!traversalStack.isEmpty()) {
            x = traversalStack.peek();
            found = false;

            for (int i = 0; (i < numVertices) && !found; i++) {
                if ((getAdjMatrix()[x.intValue()][i] != null) && !visited[i]) {
                    traversalStack.push(new Integer(i));
                    resultList.addToRear(vertices[i]);
                    visited[i] = true;
                    found = true;
                }
            }
            if (!found && !traversalStack.isEmpty()) {
                traversalStack.pop();
            }
        }
        return resultList.iterator();
    }

    /**
     * Devolve um iterador que irá realizar a travessia em largura
     * (breadth-first traversal), iniciando-a no vértice
     * especificado. Lança uma Execao se a Network estiver
     * vazia.
     *
     * @param startVertex o vértice onde irá começar a travessia
     * @return um iterador de travessia em largura, iniciado no vértice
     * especificado
     * @throws Excecpao se a Network estiver vazia
     */
    @Override
    public Iterator<T> iteratorBFS(T startVertex) throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("Network vazia!");
        }

        return iteratorBFS(getIndex(startVertex));
    }

    /**
     * Devolve um iterador que irá realizar a travessia em largura
     * (breadth-first traversal), iniciando-a no vértice especificado.
     *
     * @param startIndex o índice onde irá começar a travessia
     * @return um iterador de travessia em profundidade, iniciado no índice
     * especificado
     */
    @Override
    public Iterator<T> iteratorBFS(int startIndex) throws EmptyCollectionException {
        Integer x;
        LinkedQueue<Integer> traversalQueue = new LinkedQueue<Integer>();
        ArrayUnorderedList<T> resultList = new ArrayUnorderedList<T>();

        if (!indexIsValid(startIndex)) {
            return resultList.iterator();
        }

        boolean[] visited = new boolean[numVertices];
        for (int i = 0; i < numVertices; i++) {
            visited[i] = false;
        }


        traversalQueue.enqueue(new Integer(startIndex));
        visited[startIndex] = true;

        while (!traversalQueue.isEmpty()) {
            x = traversalQueue.dequeue();
            resultList.addToRear(vertices[x.intValue()]);

            /**
             * Encontra todos os vertices adjacentes a x que nao foram visitados
             * 
             */
            for (int i = 0; i < numVertices; i++) {
                if ((getAdjMatrix()[x.intValue()][i] != null)
                        && !visited[i]) {
                    traversalQueue.enqueue(new Integer(i));
                    visited[i] = true;
                }
            }
        }
        return resultList.iterator();
    }

    /**
     * Devolve um iterador com os índices dos vértices que estão no caminho mais
     * curto entre os dois vértices especificados, tendo em conta o comprimento 
     * entre dois vertices.
     * Lança uma Execao se a Network estiver vazia.
     *
     * @param startIndex o índice inicial
     * @param targetIndex o índice alvo
     * @return um iterador com os índices dos vértices que estão no caminho mais
     * curto entre os dois vértices especificados
     * @throws Execoes se a Network estiver vazia
     */
    @Override
    protected Iterator<Integer> iteratorShortestPathIndices(int startIndex, int targetIndex) throws EmptyCollectionException {

        int index;
        double weight;
        int[] predecessor = new int[numVertices];
        Heap<Double> traversalMinHeap = new Heap<Double>();
        ArrayUnorderedList<Integer> resultList =
                new ArrayUnorderedList<Integer>();
        LinkedStack<Integer> stack = new LinkedStack<Integer>();

        int[] pathIndex = new int[numVertices];
        double[] pathWeight = new double[numVertices];
        for (int i = 0; i < numVertices; i++) {
            pathWeight[i] = Double.POSITIVE_INFINITY;
        }

        boolean[] visited = new boolean[numVertices];
        for (int i = 0; i < numVertices; i++) {
            visited[i] = false;
        }

        if (!indexIsValid(startIndex) || !indexIsValid(targetIndex)
                || (startIndex == targetIndex) || isEmpty()) {
            return resultList.iterator();
        }

        pathWeight[startIndex] = 0;
        predecessor[startIndex] = -1;
        visited[startIndex] = true;
        weight = 0;

        for (int i = 0; i < numVertices; i++) {
            if (!visited[i] && getAdjMatrix()[startIndex][i].getResult() != 0) {
                pathWeight[i] = pathWeight[startIndex]
                        + getAdjMatrix()[startIndex][i].getResult();
                predecessor[i] = startIndex;
                traversalMinHeap.addElement(new Double(pathWeight[i]));
            }
        }

        do {
            weight = (traversalMinHeap.removeMin()).doubleValue();
            traversalMinHeap.removeAllElements();
            if (weight == Double.POSITIVE_INFINITY) // CAMINHO IMPOSSIVEL
            {
                return resultList.iterator();
            } else {
                index = getIndexOfAdjVertexWithWeightOf(visited, pathWeight,
                        weight);
                visited[index] = true;
            }

            for (int i = 0; i < numVertices; i++) {
                if (!visited[i]) {
                    if ((getAdjMatrix()[index][i].getResult() != 0)
                            && (pathWeight[index] + getAdjMatrix()[index][i].getResult()) < pathWeight[i]) {
                        pathWeight[i] = pathWeight[index] + getAdjMatrix()[index][i].getResult();
                        predecessor[i] = index;
                    }
                    traversalMinHeap.addElement(new Double(pathWeight[i]));
                }
            }
        } while (!traversalMinHeap.isEmpty() && !visited[targetIndex]);

        index = targetIndex;
        stack.push(new Integer(index));
        do {
            index = predecessor[index];
            stack.push(new Integer(index));
        } while (index != startIndex);

        while (!stack.isEmpty()) {
            resultList.addToRear((stack.pop()));
        }

        return resultList.iterator();
    }

    /**
     * Devolve o índice do vértice adjacente ao vértice com o índice
     * especificado.
     *
     * @param visited
     * @param pathWeight
     * @param weight
     * @return o índice do vértice adjacente ao vértice com o índice
     * especificado
     */
    protected int getIndexOfAdjVertexWithWeightOf(boolean[] visited,
            double[] pathWeight, double weight) {
        for (int i = 0; i < numVertices; i++) {
            if ((pathWeight[i] == weight) && !visited[i]) {
                for (int j = 0; j < numVertices; j++) {
                    if ((getAdjMatrix()[i][j] != null)) {
                        return i;
                    }
                }
            }
        }

        return 0;
    }

    /**
     * Devolve um iterador que contém o caminho mais curto entre os dois
     * vértices especificados.
     * Lança uma Execoes se a Network estiver vazia.
     *
     * @param startVertex o vértice inicial
     * @param targetVertex o vértice final
     * @return o iterador que contém o caminho mais curto entre dois vértices
     * @throws Excepcao se a Network estiver vazia
     */
    @Override
    public Iterator<T> iteratorShortestPath(T startVertex, T targetVertex)
            throws EmptyCollectionException {
        if (isEmpty()) {
            throw new EmptyCollectionException("Network vazia!");
        }


        return iteratorShortestPath(getIndex(startVertex), getIndex(targetVertex));
    }

    /**
     * Devolve um iterador que contém o caminho mais curto entre os dois
     * vértices especificados.
     *
     * @param startVertex o vértice inicial
     * @param targetVertex o vértice final
     * @param tipo especfica os atributos
     * @return o iterador que contém o caminho mais curto entre dois vértices
     * @throws Execoes se a Network estiver vazia
     */
    @Override
    public Iterator<T> iteratorShortestPath(int startIndex, int targetIndex) throws EmptyCollectionException {
        ArrayUnorderedList templist = new ArrayUnorderedList();
        if (!indexIsValid(startIndex) || !indexIsValid(targetIndex)) {
            return templist.iterator();
        }
        Iterator<Integer> iter = iteratorShortestPathIndices(startIndex, targetIndex);
        while (iter.hasNext()) {
            templist.addToRear(vertices[(iter.next()).intValue()]);
        }
        return templist.iterator();
    }

    /**
     * Devolve a aresta com o peso indicado.
     *
     * @param weight o peso da aresta
     * @param visited
     * @return a aresta com o peso indicado
     */
    protected int[] getEdgeWithWeightOf(double weight, boolean[] visited) {
        int[] edge = new int[2];
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                if ((getAdjMatrix()[i][j].getResult() == weight) && (visited[i] ^ visited[j])) {
                    edge[0] = i;
                    edge[1] = j;
                    return edge;
                }
            }
        }

        edge[0] = -1;
        edge[1] = -1;
        return edge;
    }

    /**
     * Cria novos arrays para guardar o conteúdo da Network com o dobro da
     * capacidade.
     */
    @Override
    protected void expandCapacity() {
        T[] largerVertices = (T[]) (new Object[vertices.length * 2]);
        Weight[][] largerAdjMatrix =
                new Weight[vertices.length * 2][vertices.length * 2];

        for (int i = 0; i < numVertices; i++) {
            System.arraycopy(getAdjMatrix()[i], 0, largerAdjMatrix[i], 0, numVertices);
            largerVertices[i] = vertices[i];
        }

        vertices = largerVertices;
        setAdjMatrix(largerAdjMatrix);
    }

    /**
     * @return the adjMatrix
     */
    public Weight[][] getAdjMatrix() {
        return adjMatrix;
    }

    /**
     * @param adjMatrix the adjMatrix to set
     */
    public void setAdjMatrix(Weight[][] adjMatrix) {
        this.adjMatrix = adjMatrix;
    }

    /******************************************************************
    Returns the weight of the least weight path in the network.  
    Returns positive infinity if no path is found.
     ******************************************************************/
    public double shortestPathWeight(int startIndex, int targetIndex) throws EmptyCollectionException {
        double result = 0;
        if (!indexIsValid(startIndex) || !indexIsValid(targetIndex)) {
            return 0;
        }

        int index1, index2;
        Iterator<Integer> it = iteratorShortestPathIndices(startIndex,
                targetIndex);

        if (it.hasNext()) {
            index1 = ((Integer) it.next()).intValue();
        } else {
            return 0;
        }

        while (it.hasNext()) {
            index2 = (it.next()).intValue();
            result += adjMatrix[index1][index2].getResult();
            index1 = index2;
        }

        return result;
    }

    /******************************************************************
    Returns the weight of the least weight path in the network.  
    Returns positive infinity if no path is found.
     ******************************************************************/
    @Override
    public double shortestPathWeight(T startVertex, T targetVertex) throws EmptyCollectionException {
        return shortestPathWeight(getIndex(startVertex),
                getIndex(targetVertex));
    }

    /*
     * Metodo que actualiza o peso (result) para dar o Caminho mais curto
     */
    protected void shortestDistance() {
        int i, j;
        for (i = 0; i < adjMatrix.length; i++) {
            for (j = 0; j < adjMatrix[0].length; j++) {
                if (adjMatrix[i][j] != null && adjMatrix[i][j].getResult() != 0) {
                    adjMatrix[i][j].setResult(adjMatrix[i][j].getDistance());
                }
            }

        }
    }

    /*
     * Metodo que actualiza o peso (result) para dar o Caminho mais curto com 
     * uma determinada qualidade
     */
    protected void shortestDistanceQuality(int quality) {
        int i, j;
        for (i = 0; i < adjMatrix.length; i++) {
            for (j = 0; j < adjMatrix[0].length; j++) {
                if (adjMatrix[i][j] != null && adjMatrix[i][j].getResult() != 0) {
                    if (adjMatrix[i][j].getQuality() >= quality) {
                        adjMatrix[i][j].setResult(adjMatrix[i][j].getDistance());
                    } else { // altera as matriz onde a qualidade e menor para que o result seja infinito
                        adjMatrix[i][j].setResult(Double.POSITIVE_INFINITY);
                    }
                }
            }

        }
    }

    /*
     * Metodo que actualiza o peso (result) para dar o Caminho Mais Rapido
     * em hora de ponta
     */
    protected void peakHourFastPath() {
        int i, j;
        for (i = 0; i < adjMatrix.length; i++) {
            for (j = 0; j < adjMatrix[0].length; j++) {
                if (adjMatrix[i][j] != null && adjMatrix[i][j].getResult() != 0) {
                    adjMatrix[i][j].setResult(adjMatrix[i][j].getPeakHour());
                }
            }

        }
    }

    /*
     * Metodo que actualiza o peso (result) para dar o Caminho Mais Rapido
     * em hora normal
     */
    protected void normalHourFastPath() {
        int i, j;
        for (i = 0; i < adjMatrix.length; i++) {
            for (j = 0; j < adjMatrix[0].length; j++) {
                if (adjMatrix[i][j] != null && adjMatrix[i][j].getResult() != 0) {
                    adjMatrix[i][j].setResult(adjMatrix[i][j].getNormalHour());
                }
            }

        }
    }

    /*
     * Metodo que actualiza o peso (result) para dar o Caminho Mais Rapido
     * em Hora Ponta com uma determinada qualidade
     */
    protected void peakHourFastQualityPath(int quality) {
        int i, j;
        for (i = 0; i < adjMatrix.length; i++) {
            for (j = 0; j < adjMatrix[0].length; j++) {
                if (adjMatrix[i][j] != null && adjMatrix[i][j].getResult() != 0) {
                    if (adjMatrix[i][j].getQuality() >= quality) {
                        adjMatrix[i][j].setResult(adjMatrix[i][j].getPeakHour());
                    } else { // altera as matriz onde a qualidade e menor para que o result seja infinito
                        adjMatrix[i][j].setResult(Double.POSITIVE_INFINITY);
                    }
                }
            }

        }
    }

    /*
     * Metodo que actualiza o peso (result) para dar o Caminho Mais Rapido
     * em Hora Normal com uma determinada qualidade
     */
    protected void normalHourFastQualityPath(int quality) {
        int i, j;
        for (i = 0; i < adjMatrix.length; i++) {
            for (j = 0; j < adjMatrix[0].length; j++) {
                if (adjMatrix[i][j] != null && adjMatrix[i][j].getResult() != 0) {
                    if (adjMatrix[i][j].getQuality() >= quality) {
                        adjMatrix[i][j].setResult(adjMatrix[i][j].getNormalHour());
                    } else { // altera as matriz onde a qualidade e menor para que o result seja infinito
                        adjMatrix[i][j].setResult(Double.POSITIVE_INFINITY);
                    }
                }
            }
        }
    }

    /*
     * Metodo que actualiza o peso (result) para dar o Caminho Mais Curto e Rapido
     * em Hora Ponta
     */
    protected void peakHourShortestFastPath() {
        int i, j;
        for (i = 0; i < adjMatrix.length; i++) {
            for (j = 0; j < adjMatrix[0].length; j++) {
                if (adjMatrix[i][j] != null && adjMatrix[i][j].getResult() != 0) {
                    double calc = adjMatrix[i][j].getDistance() * (adjMatrix[i][j].getPeakHour() * 0.01);
                    adjMatrix[i][j].setResult(calc);

                }
            }
        }
    }

    /*
     * Metodo que actualiza o peso (result) para dar o Caminho Mais Curto e Rapido
     * em Hora Normal
     */
    protected void normalHourShortestFastPath() {
        int i, j;
        for (i = 0; i < adjMatrix.length; i++) {
            for (j = 0; j < adjMatrix[0].length; j++) {
                if (adjMatrix[i][j] != null && adjMatrix[i][j].getResult() != 0) {
                    double calc = adjMatrix[i][j].getDistance() * (adjMatrix[i][j].getNormalHour() * 0.01);
                    adjMatrix[i][j].setResult(calc);

                }
            }
        }
    }

    /*
     * Metodo que actualiza o peso (result) para dar o Caminho Mais Curto e Rapido
     * em Hora Ponta com uma determinada Qualidade
     */
    protected void peakHourShortestFastQualityPath(int quality) {
        int i, j;
        for (i = 0; i < adjMatrix.length; i++) {
            for (j = 0; j < adjMatrix[0].length; j++) {
                if (adjMatrix[i][j] != null && adjMatrix[i][j].getResult() != 0) {
                    if (adjMatrix[i][j].getQuality() >= quality) {
                        double calc = adjMatrix[i][j].getDistance() * (adjMatrix[i][j].getPeakHour() * 0.01);
                        adjMatrix[i][j].setResult(calc);
                    } else {
                        adjMatrix[i][j].setResult(Double.POSITIVE_INFINITY);
                    }
                }
            }
        }
    }

    /*
     * Metodo que actualiza o peso (result) para dar o Caminho Mais Curto e Rapido
     * em Hora Normal com uma determinada Qualidade
     */
    protected void normalHourShortestFastQualityPath(int quality) {
        int i, j;
        for (i = 0; i < adjMatrix.length; i++) {
            for (j = 0; j < adjMatrix[0].length; j++) {
                if (adjMatrix[i][j] != null && adjMatrix[i][j].getResult() != 0) {
                    if (adjMatrix[i][j].getQuality() >= quality) {
                        double calc = adjMatrix[i][j].getDistance() * (adjMatrix[i][j].getNormalHour() * 0.01);
                        adjMatrix[i][j].setResult(calc);
                    } else {
                        adjMatrix[i][j].setResult(Double.POSITIVE_INFINITY);
                    }
                }
            }
        }
    }
}
