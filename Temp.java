package composant;
        import java.time.format.*;
        import java.time.LocalTime;
        import static java.time.temporal.ChronoUnit.MINUTES;
public class Temp{
    LocalTime avant;
    LocalTime apres;

    public Temp(LocalTime avant,LocalTime apres)
    {
        this.avant=avant;
        this.apres=apres;
    }
    public Temp()
    {
    }

    public void setavant(LocalTime p){this.avant=p;}
	public LocalTime getavant(){return this.avant;}

        public void setapres(LocalTime p){this.apres=p;}
	public LocalTime getapres(){return this.apres;}
}