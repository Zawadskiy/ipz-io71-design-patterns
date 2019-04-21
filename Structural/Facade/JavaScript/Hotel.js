class Restaurant
{
    constructor()
    {
        this.dishes = {
            1:"Burger",
            2:"Carbonara",
            3:"Caesar salad"
        }
    }
    
    receiveOrder(menuItemIds)
    {
        var order = [];
        for (var i = 0; i < menuItemIds.length; i++)
            order.push(this.dishes[menuItemIds[i]]);
        return order;
    }
}

class Room
{
    constructor(number, hotel)
    {
        this.hotel = hotel;
        this.number = number;
        this.laundry = null;
        this.cleanliness = 1.0;
        this.occupied = false;
    }
    
    receiveOrder(order)
    {
        console.log("Order ".concat(order.toString()).concat(" received by the guest in room ").concat(this.number.toString()));
    }
    
    giveLaundry()
    {
        var laundry = this.laundry;
        this.laundry = null;
        return laundry;
    }
    
    clean()
    {
        this.cleanliness = 1.0;
        console.log("Room ".concat(this.number.toString()).concat(" is cleaned."));
    }
    
    occupy()
    {
        this.occupied = true;
        this.cleanliness = Math.random();
        this.laundry = ["Dirty shoes", "Dirty raincoat"];
        console.log("Room ".concat(this.number.toString()).concat(" is occupied."));
    }
    
    free()
    {
        this.occupied = false;
        console.log("Room ".concat(this.number.toString()).concat(" is freed."));
        this.hotel.orderRoomCleaning(this.number);
    }
}

class Delivery
{
    constructor(hotel)
    {
        this.hotel = hotel;
    }
    
    deliverToRoom(order, roomNumber)
    {
        this.hotel.rooms[roomNumber].receiveOrder(order);
    }
    
    deliverLaundry(roomNumber)
    {
        return this.hotel.rooms[roomNumber].giveLaundry();
    }
}

class Janitor
{
    constructor(hotel)
    {
        this.hotel = hotel;
    }
    
    cleanRoom(roomNumber)
    {
        this.hotel.rooms[roomNumber].clean();
    }
}

class LaundryService
{
    wash(laundry)
    {
        for (var i = 0; i < laundry.length; i++)
        {
            laundry[i] = laundry[i].replace("Dirty", "Clean");
        }
    }
}

class Hotel
{
    constructor(roomsCount)
    {
        this.rooms = {};
        for (var i = 0; i < roomsCount; i++)
            this.rooms[i+1] = new Room(i+1, this);
        this.restaurant = new Restaurant()
        this.delivery = new Delivery(this)
        this.janitor = new Janitor(this)
        this.laundryService = new LaundryService()
    }
    
    occupyRoom()
    {
        for (var number in this.rooms)
        {
            if (!this.rooms[number].occupied)
            {
                this.rooms[number].occupy();
                return this.rooms[number];
            }
        }
    }
    
    orderFood(roomNumber, menuItemIds)
    {
        var order = this.restaurant.receiveOrder(menuItemIds);
        this.delivery.deliverToRoom(order, roomNumber);
    }
    
    orderLaundry(roomNumber)
    {
        var laundry = this.delivery.deliverLaundry(roomNumber)
        if (laundry != null)
        {
            this.laundryService.wash(laundry);
            this.delivery.deliverToRoom(laundry, roomNumber);
        }
    }
    
    orderRoomCleaning(roomNumber)
    {
        this.janitor.cleanRoom(roomNumber);
    }
}

// Test

var hotel = new Hotel(10);

var myRoom = hotel.occupyRoom();
hotel.orderFood(myRoom.number, [1,2,3]);
hotel.orderLaundry(myRoom.number);
myRoom.free();