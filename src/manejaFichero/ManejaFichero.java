/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejaFichero;

import amc_practica2a.Punto;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;

/**
 *
 * @author usuario
 */
public class ManejaFichero {
    
    String inputFile;
    String outputFile;
    ArrayList<Punto> aux;
    int dimmension;
    String nombreFichero;
    private int solution;
    
    public ManejaFichero(String Fichero){       

        this.inputFile = Fichero;       
        
    }
    
    public ArrayList<Punto> Leer() throws FileNotFoundException, IOException{
        
        File F = new File(inputFile);
        
        Scanner scr = new Scanner(F);
        Punto punt;
        String cadena = "";
        double coordX, coordY;
        
        cadena = scr.nextLine();
        nombreFichero = cadena.replace("NAME: ","");
        scr.nextLine();
        scr.nextLine();
        scr.next();
        dimmension = scr.nextInt();
        while(!scr.hasNextInt()){
            cadena = scr.next();
        }
        
        aux = new ArrayList<>(dimmension);
                
        for (int i = 0 ; i < dimmension ; i++) {
            
            scr.nextInt();
            coordX = scr.nextFloat();
            coordY = scr.nextFloat();
            
            punt = new Punto(coordX, coordY);
            
            aux.add(punt);
        }
        
        scr.close();
        
        return this.aux;        
    }
    
    public void Escribir(ArrayList<Punto>  solucion) throws IOException{
        
        outputFile = inputFile + ".opt.tour";
        
        FileWriter f = new FileWriter(outputFile);
        try (BufferedWriter BFR = new BufferedWriter(f)) {
            BFR.write("NAME : "+nombreFichero);
            BFR.newLine();
            BFR.write("TYPE : TOUR");
            BFR.newLine();
            BFR.write("DIMMENSION : "+dimmension);
            BFR.newLine();
            BFR.write("SOLUTION : "+solution);
            BFR.newLine();
            BFR.write("TOUR_SECTION");
            for(int i = 0; i < solucion.size() ; i++){
                BFR.write(solucion.get(i).getX()+" "+solucion.get(i).getY());
                BFR.newLine();
            }
            BFR.write("-1");
            BFR.newLine();
            BFR.write("EOF");
            BFR.newLine();
        }
    }

    public void setNombreFichero(String NombreFichero) {
        this.nombreFichero = NombreFichero;
    }    
}
