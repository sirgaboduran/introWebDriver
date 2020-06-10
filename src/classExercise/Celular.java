package classExercise;

public class Celular
{
    private String marcaModelo;
    private String nombre;
    private double precio;
    private int capacidadGB;

    public Celular(String marcaModelo, String nombre, double precio, int capacidadGB)
    {
        this.marcaModelo = marcaModelo;
        this.nombre = nombre;
        this.precio = precio;
        this.capacidadGB = capacidadGB;
    }

    public String getMarcaModelo()
    {
        return marcaModelo;
    }

    public void setMarcaModelo(String marcaModelo)
    {
        this.marcaModelo = marcaModelo;
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public double getPrecio()
    {
        return precio;
    }

    public void setPrecio(double precio)
    {
        this.precio = precio;
    }

    public int getCapacidadGB()
    {
        return capacidadGB;
    }

    public void setCapacidadGB(int capacidadGB)
    {
        this.capacidadGB = capacidadGB;
    }
}
