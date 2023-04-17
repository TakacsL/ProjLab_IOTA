public class Fountain extends Area{
    public Pipe PickupPipe() {
        System.out.println("+New Pipe Created");
        return new Pipe();
    }

    @Override
    void Connect(Area a){
        System.out.println("->Fountain.Connect["+a.toString()+"]");
        connectedAreas.add(a);
        System.out.println("<-Fountain.Connect["+a.toString()+"]");
    }

    @Override
    public String toString() {return "[Fountain]";}
}
