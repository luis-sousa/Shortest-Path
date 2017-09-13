/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Graph;

import Exceptions.EmptyCollectionException;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

/* ESCOLA SUPERIOR DE TECNOLOGIA E GESTÃO DE FELGUEIRAS 
 * LEI - 2012/2013
 *
 * Authors: Luís Sousa [8090228] & Rui Vieira [8100451]
 * 
 */
public class Demo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws EmptyCollectionException, IOException, BiffException {
        Network<String> mygraph = new Network<String>();


        //Create a workbook object from the file at specified location.
        //Change the path of the file as per the location on your computer.
        Workbook wrk1 = Workbook.getWorkbook(new File("Mapas/mapa1.xls"));

        //Obtain the reference to the first sheet in the workbook
        Sheet sheet1 = wrk1.getSheet(0);

        //ler do excel e adicionar vertices 
        for (int i = 0; i < sheet1.getRows(); i++) {
            for (int j = 0; j < 2; j++) {
                mygraph.addVertex(sheet1.getCell(j, i).getContents());
            }
        }

        String str = "";

        //ler do excel adicionar arestas
        for (int i = 0; i < sheet1.getRows(); i++) {
            for (int j = 0; j < 6; j++) {
                mygraph.addEdge(sheet1.getCell(j, i).getContents(), sheet1.getCell(j = j + 1, i).getContents(), new Weight(Double.valueOf(sheet1.getCell(j = j + 1, i).getContents()), Integer.parseInt(sheet1.getCell(j = j + 1, i).getContents()), Integer.parseInt(sheet1.getCell(j = j + 1, i).getContents()), Integer.parseInt(sheet1.getCell(j = j + 1, i).getContents()), Integer.parseInt(sheet1.getCell(2, i).getContents())));
            }
        }

        //System.out.println(mygraph.toString());

        mygraph.shortestDistance();

        Iterator<String> it = null;
        it = mygraph.iteratorShortestPath("n1", "n13");

        while (it.hasNext()) {
            if (it.hasNext()) {
                str = str + it.next() + " - ";
            }
        }
        str = str + mygraph.shortestPathWeight("n1", "n13");
        System.out.println(mygraph.toString());
        System.out.println(str);

        System.out.println("-------------------------------------------------");
        mygraph.shortestDistanceQuality(3);
        String str2 = "";
        Iterator<String> it2 = null;
        it2 = mygraph.iteratorShortestPath("n1", "n13");

        while (it2.hasNext()) {
            if (it2.hasNext()) {
                str2 = str2 + it2.next() + " - ";
            }
        }
        str2 = str2 + mygraph.shortestPathWeight("n1", "n13");
        System.out.println(mygraph.toString());
        System.out.println(str2);

        System.out.println("-------------------------------------------------");

        mygraph.shortestDistance();
        String str3 = "";
        Iterator<String> it3 = null;
        it3 = mygraph.iteratorShortestPath("n1", "n13");

        while (it3.hasNext()) {
            if (it3.hasNext()) {
                str3 = str3 + it3.next() + " - ";
            }
        }
        str3 = str3 + mygraph.shortestPathWeight("n1", "n13");
        System.out.println(mygraph.toString());
        System.out.println(str3);

        System.out.println("-------------------------------------------------");

        mygraph.normalHourShortestFastQualityPath(3);
        String str4 = "";
        Iterator<String> it4 = null;
        it4 = mygraph.iteratorShortestPath("n2", "n9");

        while (it4.hasNext()) {
            if (it4.hasNext()) {
                str4 = str4 + it4.next() + " - ";
            }
        }
        str4 = str4 + mygraph.shortestPathWeight("n2", "n9");
        System.out.println(mygraph.toString());
        System.out.println(str4);

        System.out.println("-------------------------------------------------");
        mygraph.normalHourFastQualityPath(5);
        String str5 = "";
        Iterator<String> it5 = null;
        it5 = mygraph.iteratorShortestPath("n1", "n13");

        while (it5.hasNext()) {
            if (it5.hasNext()) {
                str5 = str5 + it5.next() + " - ";
            }
        }
        str5 = str5 + mygraph.shortestPathWeight("n1", "n13");
        System.out.println(mygraph.toString());
        System.out.println(str5);

    }
}
