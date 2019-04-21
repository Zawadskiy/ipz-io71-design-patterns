import random

class Restaurant:
    def __init__(self):
        self.dishes = {
            1:"Burger",
            2:"Carbonara",
            3:"Caesar salad"
        }
    
    def receiveOrder(self, menuItemIds):
        return [self.dishes[item] for item in menuItemIds]
        
class Room:
    def __init__(self, number, hotel):
        self.hotel = hotel
        self.number = number
        self.laundry = None
        self.cleanliness = 1
        self.occupied = False
        
    def receiveOrder(self, order):
        print("Order {0} received by the guest in room {1}".format(order, self.number))
        
    def giveLaundry(self):
        laundry = self.laundry
        self.laundry = None
        return laundry
        
    def clean(self):
        self.cleanliness = 1
        print("Room {0} is cleaned.".format(self.number))
        
    def occupy(self):
        self.occupied = True
        self.cleanliness = random.random()
        self.laundry = ["Dirty shoes", "Dirty raincoat"]
        print("Room {0} is occupied.".format(self.number))
        
    def free(self):
        self.occupied = False
        print("Room {0} is freed.".format(self.number))
        self.hotel.orderRoomCleaning(self.number)
        
class Delivery:
    def __init__(self, hotel):
        self.hotel = hotel
        
    def deliverToRoom(self, order, room):
        self.hotel.rooms[room].receiveOrder(order)
        
    def deliverLaundry(self, room):
        return self.hotel.rooms[room].giveLaundry()
            
class Janitor:
    def __init__(self, hotel):
        self.hotel = hotel
        
    def cleanRoom(self, room):
        self.hotel.rooms[room].clean()
        
class LaundryService:
    def wash(self, laundry):
        for i in range(len(laundry)):
            laundry[i] = laundry[i].replace("Dirty", "Clean")

class Hotel:
    def __init__(self, roomsCount):
        self.rooms = {i+1:Room(i+1, self) for i in range(roomsCount)}
        self.restaurant = Restaurant()
        self.delivery = Delivery(self)
        self.janitor = Janitor(self)
        self.laundryService = LaundryService()
    
    def occupyRoom(self):
        for room in self.rooms.values():
            if not room.occupied:
                room.occupy()
                return room

    def orderFood(self, roomNumber, menuItemIds):
        order = self.restaurant.receiveOrder(menuItemIds)
        self.delivery.deliverToRoom(order, roomNumber)
        
    def orderLaundry(self, roomNumber):
        laundry = self.delivery.deliverLaundry(roomNumber)
        if (laundry is not None):
            self.laundryService.wash(laundry)
            self.delivery.deliverToRoom(laundry, roomNumber)
            
    def orderRoomCleaning(self, roomNumber):
        self.janitor.cleanRoom(roomNumber)

    