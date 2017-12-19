/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amc_practica2a;

import manejaFichero.ManejaFichero;
import algoritmos.Algoritmos;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author usuario
 */
public class AMC_Practica2a {

    
    public static final int MAX = 1500;
    
    public static void main(String[] args) {

        int TALLAS [] = new int[4];
        
        TALLAS[0] = 200;
        TALLAS[1] = 500;
        TALLAS[2] = 1500;
        TALLAS[3] = 5000;
        
        int talla = 0;
        
        String nombreFichero = "";
        
        int opcion;
        Algoritmos Algo = new Algoritmos();
        ArrayList<Punto> ArrayDePuntos = new ArrayList<>();
        ManejaFichero MF;
        
        do{
            Scanner scn;
            scn = new Scanner(System.in);
            
            System.out.println("DISTANCIA 3 PUNTOS");
            System.out.println("1. Generar array aleatorio");
            System.out.println("2. Leer fichero");
            System.out.println("3. Calcular 3 puntos por Exhaustivo");
            System.out.println("4. Calcular 3 puntos por Algoritmo DyV");
            System.out.println("0. Salir");
            System.out.println("Introduzca una opcion: ");
            
            System.out.println(opcion = scn.nextInt());

            switch(opcion){
                case 1:
                    System.out.println("Escriba la talla: ");
                    talla = scn.nextInt();
                    ArrayDePuntos = Algo.GenerarPuntosAleatorios(talla);
                break;

                case 2:
                {
                    System.out.println("Escriba el nombre del fichero: ");
                    nombreFichero = scn.next();
                    MF = new ManejaFichero(nombreFichero);

                    try {
                        ArrayDePuntos = MF.Leer();
                    } catch (IOException ex) {
                        Logger.getLogger(AMC_Practica2a.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;

                case 3:
                {    
                    long tiempoini = 0, tiempofin = 0;
                    double distanciaminima = 0;
                    long tiempototal = 0;

                    Algo.OrdenarArrayPunto(ArrayDePuntos);
                    
                    for(int i = 0; i < TALLAS.length; i++){
                     
                        tiempoini = System.nanoTime();
                        distanciaminima = Algo.DistanciaMinimaExhaustivo(ArrayDePuntos);
                        tiempofin = System.nanoTime();
                        tiempototal += tiempofin-tiempoini;
                    }
                    System.out.println("La distancia minima entre 3 puntos es:" + distanciaminima);
                    System.out.println("El tiempo dedicado a la tarea es: "+(tiempototal/10)/1000 + " microsegundos");
                }    
                break;
                
                case 4:
                {
                    
                    long tiempoini = 0, tiempofin = 0, tiempototal = 0;
                    double distanciaminima = 0;
                    Algo.OrdenarArrayPunto(ArrayDePuntos);
                    
                    for (int i = 0 ; i < TALLAS.length ; i++) {
                        tiempoini = System.nanoTime();
                        distanciaminima = Algo.DistanciaMinimaDyV(ArrayDePuntos);
                        tiempofin = System.nanoTime();
                        tiempototal += tiempofin - tiempoini;
                    }
                    
                    System.out.println("La distancia minima entre 3 puntos es:" + distanciaminima);
                    System.out.println("El tiempo dedicado a la tarea es: "+(tiempototal/10)/1000 + " microsegundos");
                }
                break;
            }
        }while(opcion != 0);
    }
}
