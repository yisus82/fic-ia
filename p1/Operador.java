public abstract class Operador
{
  public static int NORTE = 0;
  public static int SUR = 1;
  public static int ESTE = 2;
  public static int OESTE = 3;
  
  protected Nodo nodo;

  public abstract boolean esRelevante();  
  public abstract Nodo aplicar();
}
