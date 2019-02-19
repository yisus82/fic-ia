public class Posicion
{
  public int x, y;
  
  public Posicion (int x, int y)
  {
    this.x = x;
    this.y = y;
  }
  
  public Posicion clonar()
  {
    return (new Posicion(x,y));
  }
  
  public String toString()
  {
    return ("("+x+","+y+")");
  }
}
