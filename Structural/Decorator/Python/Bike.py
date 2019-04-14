import abc

class Vehicle(metaclass = abc.ABCMeta):
    @abc.abstractmethod
    def build(self):
        return

class Bicycle(Vehicle):
    def build(self):
        print("Bicycle")

class VehicleDecorator(Vehicle):
    def __init__(self, vehicle):
        self.vehicle = vehicle

    def build(self):
        self.vehicle.build()

        
class VehicleWithEngine(VehicleDecorator):
    def __init__(self, vehicle, enginePower):
        super().__init__(vehicle)
        self.enginePower = enginePower

    def build(self):
        super().build()
        print("With {0}hp engine".format(self.enginePower))

bicycle = Bicycle()
moped = VehicleWithEngine(bicycle, 10)
moped.build()
