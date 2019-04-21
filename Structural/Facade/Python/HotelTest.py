import Hotel

hotel = Hotel.Hotel(10)

myRoom = hotel.occupyRoom()
hotel.orderFood(myRoom.number, [1,2,3])
hotel.orderLaundry(myRoom.number)
myRoom.free()