/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmos;

import amc_practica2a.Punto;
import static amc_practica2a.AMC_Practica2a.MAX;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author usuario
 */
public class Algoritmos {

    ArrayList<Punto> puntos;
    
    
    public ArrayList<Punto>  GenerarPuntosAleatorios(int talla){

        Random rnd = new Random(System.currentTimeMillis());        
        ArrayList<Punto> ArrayDePuntos = new ArrayList();
        Punto[] puntosAleatorios;        
        puntosAleatorios = new Punto[talla];

        System.out.println("Generando Puntos Aleatorios");
        
        for (int i = 0; i < talla; i++) {
            
            int puntox = rnd.nextInt(10000);
            int puntoy = rnd.nextInt(10000);;
            
            puntosAleatorios[i] = new Punto(puntox,puntoy);
            
            ArrayDePuntos.add(puntosAleatorios[i]);            
        }
        
        return ArrayDePuntos;
        
    }
    
    public void OrdenarArrayPunto(ArrayList<Punto> elementos){
        
        int p,j;
        Punto aux;
        for(p=1; p<elementos.size();p++){
            aux = elementos.get(p);
            j = p-1;
            while((j>=0)&&(aux.getX() < elementos.get(j).getX())){
                elementos.set(j+1, elementos.get(j));
                j--;
            }
            elementos.set(j+1,aux);
        }  
    }
    
    public void mostrarArrayList(ArrayList<Punto> puntos){
        for(int i=0; i<puntos.size();i++){
            System.out.println("x: "+puntos.get(i).getX()+" - y: "+puntos.get(i).getY());
        }
    }    
    
    public double DistanciaMinimaDyV(ArrayList<Punto> puntos){
        
        double solucion1, solucion2, solucionfinal;
        int medio;
        
        if(puntos.size()>3)
        {
            medio = (int) Math.round((puntos.get(0).getX()+puntos.get(puntos.size()-1).getX())/2);
            solucion1 = DistanciaMinimaDyV(Particion(puntos,0, medio));
            solucion2 = DistanciaMinimaDyV(Particion(puntos,medio,(int)puntos.get(puntos.size()-1).getX()));
            
            solucionfinal = Math.min(DistanciaMinimaExhaustivo(Particion(puntos, medio-(int)Math.min(solucion1,solucion2), medio+(int)Math.min(solucion1,solucion2))),Math.min(solucion1, solucion2));
        
        }
        else if(puntos.size() == 3){
            return CalcularDistancia3Puntos(puntos.get(0), puntos.get(1), puntos.get(2));
        }
        else{
            return 100000;
        }
        
        return solucionfinal;
        
    }
    
    public ArrayList<Punto> Particion(ArrayList<Punto> elementos, int inicio, int fin){
        
        //Particionamos el arrayList de inicio a fin        
        ArrayList<Punto> aux = new ArrayList<>();
                
        for (int i = 0; i < elementos.size(); i++){
            if(elementos.get(i).getX() < fin && elementos.get(i).getX() >= inicio)
                aux.add(elementos.get(i));
        }

        return aux;
    }
    
    public double CalcularDistancia3Puntos(Punto A, Punto B, Punto C){
         
        double distanciaMinima = 0, distancia1 = 0, distancia2 = 0, distancia3 = 0;
                
        distancia1 = Math.sqrt(Math.pow(A.getX()-B.getX(),2) + Math.pow(A.getY()-B.getY(),2))
                + Math.sqrt(Math.pow(B.getX()-C.getX(),2) + Math.pow(B.getY()-C.getY(),2));
        distancia2 = Math.sqrt(Math.pow(A.getX()-C.getX(),2) + Math.pow(A.getY()-C.getY(),2))
                + Math.sqrt(Math.pow(C.getX()-B.getX(),2) + Math.pow(C.getY()-B.getY(),2));
        distancia3 = Math.sqrt(Math.pow(B.getX()-C.getX(),2)+Math.pow(B.getY()-C.getY(),2))
                + Math.sqrt(Math.pow(C.getX()-A.getX(),2)+Math.pow(C.getY()-A.getY(),2));
                
        distanciaMinima = Math.min(Math.min(distancia1, distancia2), distancia3);
        return distanciaMinima;
    }
    
    public double DistanciaMinimaExhaustivo(ArrayList<Punto> x){
        
        double distanciaminima = 100000;
        double aux;
        
        if(x.size() >= 3){
            for(int i = 0 ; i < x.size(); i++ ){
                for(int j = i+1; j < x.size(); j++){
                    for(int t = j+1; t < x.size(); t++){
                        aux = CalcularDistancia3Puntos(x.get(i),x.get(j),x.get(t));
                        if(aux < distanciaminima){
                            distanciaminima = aux;
                        }
                    }
                }
            }
        }
        return distanciaminima;
    }
}
