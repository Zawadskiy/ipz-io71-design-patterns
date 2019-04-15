interface Vehicle
{
    void build();
}

class Bicycle implements Vehicle
{
    @Override
    public void build()
    {
        System.out.println("Bicycle");
    }
}

class VehicleDecorator implements Vehicle
{
	protected Vehicle vehicle;
	
    public VehicleDecorator(Vehicle vehicle)
    {
    	this.vehicle = vehicle;
    }
    
    @Override
    public void build()
    {
        vehicle.build();
    }
}

class VehicleWithEngine extends VehicleDecorator
{
	protected float enginePower;
	
	public VehicleWithEngine(Vehicle vehicle, float enginePower)
	{
		super(vehicle);
		this.enginePower = enginePower;
	}
	
	@Override
	public void build()
	{
		super.build();
		System.out.println(String.format("With %.2fhp engine", enginePower));
	}
}

public class BikeTest {

	public static void main(String[] args)
	{
		Bicycle bicycle = new Bicycle();
		VehicleWithEngine moped = new VehicleWithEngine(bicycle, 10);
		moped.build();
	}

}
