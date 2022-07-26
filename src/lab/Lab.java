package lab;

import java.util.ArrayList;
import java.util.Arrays;

public class Lab {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        casilla [][]Lab = {{new casilla(0, 0), new casilla(0, 1), new casilla(0, 2), new casilla(0, 3), new casilla(0, 4)}, 
                           {new casilla(1, 0), new casilla(1, 1), new casilla(1, 2), new casilla(1, 3), new casilla(1, 4)}, 
                           {new casilla(2, 0), new casilla(2, 1), new casilla(2, 2), new casilla(2, 3), new casilla(2, 4)}, 
                           {new casilla(3, 0), new casilla(3, 1), new casilla(3, 2), new casilla(3, 3), new casilla(3, 4)},
                           {new casilla(4, 0), new casilla(4, 1), new casilla(4, 2), new casilla(4, 3), new casilla(4, 4)},
                           {new casilla(5, 0), new casilla(5, 1), new casilla(5, 2), new casilla(5, 3), new casilla(5, 4)}};
        //Lista de muros
        Lab[0][0].muro = 1;
        Lab[2][1].muro = 1;
        Lab[2][2].muro = 1;
        Lab[2][3].muro = 1;
        Lab[4][3].muro = 1;
        Lab[1][1].muro = 1;
        Lab[0][1].muro = 1;
        
        //Establece el punto de inicio y fin
        casilla inicio = new casilla(3, 1);
        casilla fin = new casilla(1, 2);
        
        
        ArrayList<casilla> Tabla_casillas = new ArrayList<>();
        Tabla_casillas.add(inicio);
        
        heuristica h = new heuristica();
        
        ArrayList<int []> lista_cerrada = h.Algoritmo_Astart(inicio , fin, Lab, Tabla_casillas);
        ArrayList<int []> ruta = h.generar_ruta(lista_cerrada, Tabla_casillas);
        dibujarTabla(ruta, Lab);
    }
    public static void dibujarTabla(ArrayList<int []> lista_cerrada, casilla [][] Lab){
        for(int i=0; i<Lab.length; i++){
            for(int j=0; j<Lab[0].length; j++){
                if(pertenece(Lab[i][j].getCoord(), lista_cerrada)){
                    System.out.print("* ");
                }
                else if(Lab[i][j].muro == 1){
                    System.out.print("M ");
                }
                else{
                    System.out.print(Lab[i][j].c+" ");  
               }
            }
            System.out.println("");
        }
    }
    public static boolean pertenece(int[] V, ArrayList<int []> lista_cerrada){
        for(int[] lc: lista_cerrada){
            if(Arrays.equals(lc, V)){
                return true;
            }
        }
        return false;
    }
}
