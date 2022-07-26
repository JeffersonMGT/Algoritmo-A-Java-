package lab;

import java.util.*;

public class heuristica {
    public heuristica(){
        
    }
    public ArrayList<int []> Algoritmo_Astart(casilla inicio , casilla fin, casilla Laberinto[][] , ArrayList<casilla> Tabla_casillas){
        ArrayList<casilla> lista_abierta = new ArrayList<>();
        lista_abierta.add(inicio);
        ArrayList<int []> lista_cerrada = new ArrayList<>();
        while(true){
            casilla nodo = lista_abierta.get(0);
            lista_abierta.remove(0);
     
            lista_cerrada.add(nodo.getCoord());
            if(Arrays.equals(nodo.getCoord(), fin.getCoord())){
                break;
            }
                
            ArrayList<casilla> sucesores = getVecinos(nodo, Laberinto);
            
            for(int i=0; i<sucesores.size(); i++){
                casilla s = sucesores.get(i);
                
                if(!this.pertenece(s.getCoord(), posiciones_EnTabla(Tabla_casillas))){
                    s.padre = nodo;
                    this.OrdenarInsertar(s, lista_abierta, inicio, fin, Tabla_casillas);
                }    
            }        
        }
        return lista_cerrada;
    }
    private ArrayList<casilla> getVecinos(casilla nodo, casilla Lab[][]){
        int x = nodo.getX();
        int y = nodo.getY();

        int vecinos[][] = {{x, y-1}, {x-1, y-1}, {x-1, y}, {x-1, y+1},
                           {x, y+1}, {x+1, y+1}, {x+1, y}, {x+1, y-1}};
        ArrayList<casilla> vecinos_validos = new ArrayList<>();
        
        for(int[] v: vecinos){
            int nx = v[0];
            int ny = v[1];
            if (( (nx >= 0 && nx < Lab.length) && (ny >= 0 && ny < Lab[0].length) ) && (Lab[nx][ny].muro != 1) ){
                vecinos_validos.add(Lab[nx][ny]);
            }     
        } 
        return vecinos_validos;
    }
    
    private ArrayList<int[]> posiciones_EnTabla(ArrayList<casilla> Tabla_casillas){
        ArrayList<int[]> Posiciones = new ArrayList<>();
        for(casilla tc: Tabla_casillas){
            Posiciones.add(tc.getCoord());
        }
        return Posiciones;
    }
    private boolean pertenece(int [] casilla, ArrayList<int[]> Tabla_casillas){
        for(int [] c: Tabla_casillas){
            if(Arrays.equals(casilla, c)){
                return true;
            }
        }
        return false;
    }
    private void OrdenarInsertar(casilla n, ArrayList<casilla> lista_abierta, casilla inicio, casilla fin, ArrayList<casilla> Tabla_casillas){
        //g(n)
        double gn = 0;
        casilla hijo = n;
        while(true){
            gn += this.distancia_euclidiana(hijo.getCoord(), hijo.padre.getCoord());
            hijo = hijo.padre;
            if(hijo == null || hijo == inicio){
                break;
            } 
        }

        //h(n)
        double hn = this.distancia_euclidiana(fin.getCoord(), n.getCoord());

        //f(n)
        n.fn = gn+hn;

        //Agregar y ordenar
        lista_abierta.add(n);
        this.ordenar_lista(lista_abierta);
        
        //Agregando el elemento a la tabla de casillas
        Tabla_casillas.add(n);
    }
    
    public void ordenar_lista(ArrayList<casilla> lista_abierta){
        casilla temp;
        for(int i=1; i<lista_abierta.size(); i++){
            for(int j=0; j<lista_abierta.size()-1; j++){
                if(lista_abierta.get(j).fn > lista_abierta.get(j+1).fn){
                    temp = lista_abierta.get(j);
                    lista_abierta.set(j, lista_abierta.get(j+1));
                    lista_abierta.set(j+1, temp);
                }
            }
        }
    }
    
    public double distancia_euclidiana(int[] P1, int []P2){
        double d = Math.pow(P1[0]-P2[0], 2) + Math.pow(P1[1]-P2[1], 2); 
        return Math.sqrt(d);
    }
    
    public int[] retornar_padre(int [] casilla, ArrayList<casilla> Tabla_casillas){
        for(casilla c: Tabla_casillas){
            if(Arrays.equals(c.getCoord(), casilla)){
                return c.padre.getCoord();
            }
        }
        return null;
    }


    public ArrayList<int[]>  generar_ruta(ArrayList<int []>  lista_cerrada, ArrayList<casilla> Tabla_casillas){
        Collections.reverse(lista_cerrada);
        ArrayList<int[]> ruta = new ArrayList<>();
        ruta.add(lista_cerrada.get(0));
        int i = 0;
        while(i < lista_cerrada.size()-1){
            int[] padre = this.retornar_padre(lista_cerrada.get(i), Tabla_casillas);
            ruta.add(padre);
            i = lista_cerrada.indexOf(padre);
        }
        return ruta;
    }
}
