package lab;

public class casilla {
    public char c;
    public int []coord = new int[2];
    public double fn;
    public int muro;
    public casilla padre;
    public casilla(int x, int y){
        this.c = '-';
        this.coord[0] = x;
        this.coord[1] = y;
        this.muro = 0;
    }
    public int[] getCoord(){
        return this.coord;
    }
    public int getX(){
        return this.coord[0];
    }
    public int getY(){
        return this.coord[1];
    }
}
