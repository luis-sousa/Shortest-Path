/*
 * IPP-INSTITUTO POLITECNICO DO PORTO
 * ESTGF-ESCOLA SUPERIOR TENCOLOGIAS E GESTAO DE FELGUEIRAS
 * ED-ESTRUTURA DE DADOS
 * 
 */
package Graph;

/* ESCOLA SUPERIOR DE TECNOLOGIA E GESTÃO DE FELGUEIRAS 
 * LEI - 2012/2013
 *
 * Authors: Luís Sousa [8090228] & Rui Vieira [8100451]
 * 
 */
/**
 * Esta classe representa o peso de uma aresta
 */
public class Weight {

    private double distance;
    private int quality;
    private int peakHour;
    private int normalHour;
    private double result;

    public Weight() {
        distance = 0;
        quality = 0;
        normalHour = 0;
        peakHour = 0;
        result = 0;
    }

    /**
     * Método Construtor para um Peso
     * @param distance     distancia entre dois vertices
     * @param quality      qualidade entre dois vertices
     * @param peakHour     hora de ponta entre dois vertices
     * @param normalHour   hora normal dois vertices
     * @param result       resultado que vai sendo actualizado conforme os critérios
     */
    public Weight(double distance, int quality, int peakHour, int normalHour, double result) {
        this.distance = distance;
        this.quality = quality;
        this.peakHour = peakHour;
        this.normalHour = normalHour;
        this.result = result;
    }

    /**
     * Retorna a distancia entre dois vertices
     * 
     * @return the distancia
     */
    public double getDistance() {
        return distance;
    }

    /**
     * Altera a distancia entre dois vertices
     * 
     * @param distancia the distancia to set
     */
    public void setDistance(double distance) {
        this.distance = distance;
    }

    /**
     * 
     * Retorna a qualidade entre dois vertices
     * 
     * @return the qualidade
     */
    public int getQuality() {
        return quality;
    }

    /**
     * Altera a qualidade entre dois vertices
     * 
     * @param qualidade the qualidade to set
     */
    public void setQuality(int quality) {
        this.quality = quality;
    }

    /**
     * Retorna o tempo em minutos que um individuo 
     * demora a pecorrer uma certa distancia em hora normal
     * 
     * @return the tempoHoraNormal
     */
    public int getNormalHour() {
        return normalHour;
    }

    /**
     * 
     * Altera o tempo em minutos que um indiviudo 
     * demora a pecorrer uma certa distancia em hora normal
     * 
     * @param tempoHoraNormal the tempoHoraNormal to set
     */
    public void setNormalHour(int normalHour) {
        this.normalHour = normalHour;
    }

    /**
     * Retorna o tempo em minutos que um individuo 
     * demora a pecorrer uma certa distancia em hora ponta
     * @return the tempoHoraPonta
     */
    public int getPeakHour() {
        return peakHour;
    }

    /**
     * Altera o tempo em minutos que um indiviudo 
     * demora a pecorrer uma certa distancia em hora ponta
     * @param tempoHoraPonta the tempoHoraPonta to set
     */
    public void setPeakHour(int peakHour) {
        this.peakHour = peakHour;
    }

    /**
     * Retorna o peso dependendo dos criterios
     * @return result
     */
    public double getResult() {
        return result;
    }

    /**
     * Altera o peso dependendo dos criterios
     * @param result 
     */
    public void setResult(double result) {
        this.result = result;
    }
}
