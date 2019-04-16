class Vehicle
{
    build()
    {
        throw new Error("Abstract method!");
    }
}

class Bicycle extends Vehicle
{
    build()
    {
        console.log("Bicycle");
    };
}

class VehicleDecorator extends Vehicle
{
    constructor(vehicle)
    {
        super();
        this.vehicle = vehicle;
    }
    
    build()
    {
        this.vehicle.build();
    }
}

class VehicleWithEngine extends VehicleDecorator
{
    constructor (vehicle, enginePower)
    {
        super(vehicle);
        this.enginePower = enginePower;
    }
   
    build()
    {
        super.build();
        console.log("With ".concat(this.enginePower.toString()).concat("hp engine"));
    }
}

// Test

var bicycle = new Bicycle();
var moped = new VehicleWithEngine(bicycle, 10);
moped.build();