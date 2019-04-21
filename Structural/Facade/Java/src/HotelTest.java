import java.util.*;

interface IConsierge
{
	void orderFood(Integer roomNumber, Integer[] menuItemIds);
	void orderLaundry(Integer roomNumber);
	void orderRoomCleaning(Integer roomNumber);
	Room getRoom(Integer roomNumber);
}

class Restaurant
{
	private Map<Integer, String> dishes;
	
	public Restaurant()
	{
		dishes = new HashMap<Integer,String>();
		dishes.put(1, "Burger");
		dishes.put(2, "Carbonara");
		dishes.put(3, "Caesar salad");
	}
	
	public String[] receiveOrder(Integer[] menuItemIds)
	{
		String[] order = new String[menuItemIds.length];
		for (int i = 0; i < menuItemIds.length; i++)
		{
			order[i] = dishes.get(menuItemIds[i]);
		}
		return order;
	}
}

class Room
{
	private IConsierge hotel;
	private Integer number;
	private String[] laundry;
	private float cleanliness;
	private boolean occupied;
	
	public Room(Integer number, IConsierge hotel)
	{
		this.number = number;
		this.hotel = hotel;
		this.laundry = null;
		this.cleanliness = 1f;
		this.occupied = false;
	}
	
	public Integer Number()
	{
		return number;
	}
	
	public boolean isOccupied()
	{
		return occupied;
	}
	
	public void receiveOrder(String[] order)
	{
		System.out.println(String.format("Guest in room %x received:", number));
		for (int i = 0; i < order.length; i++)
		{
			System.out.println(order[i]);
		}
	}
	
	public String[] giveLaundry()
	{
		String[] l = laundry;
		laundry = null;
		return l;
	}
	
	public void clean()
	{
		cleanliness = 1f;
		System.out.println(String.format("Room %x is cleaned.", number));
	}
	
	public void occupy()
	{
		occupied = true;
		cleanliness = new Random().nextFloat();
		laundry = new String[] {"Dirty shoes", "Dirty raincoat"};
		System.out.println(String.format("Room %x is occupied.", number));
	}
	
	public void free()
	{
		occupied = false;
		System.out.println(String.format("Room %x is freed.", number));
		hotel.orderRoomCleaning(number);
	}
}

class Delivery
{
	private IConsierge hotel;
	
	public Delivery(IConsierge hotel)
	{
		this.hotel = hotel;
	}
	
	public void deliverToRoom(String[] order, Integer roomNumber)
	{
		hotel.getRoom(roomNumber).receiveOrder(order);
	}
	
	public String[] deliverLaundry(Integer roomNumber)
	{
		return hotel.getRoom(roomNumber).giveLaundry();
	}
}

class Janitor
{
	private IConsierge hotel;
	public Janitor(IConsierge hotel)
	{
		this.hotel = hotel;
	}
	
	public void cleanRoom(Integer roomNumber)
	{
		hotel.getRoom(roomNumber).clean();
	}
}

class LaundryService
{
	public void wash(String[] laundry)
	{
		for (int i = 0; i < laundry.length; i++)
		{
			laundry[i] = laundry[i].replace("Dirty", "Clean");
		}
	}
}

class Hotel implements IConsierge
{
	private Map<Integer, Room> rooms;
	private Restaurant restaurant;
	private Delivery delivery;
	private Janitor janitor;
	private LaundryService laundryService;
	
	public Hotel(int roomsCount)
	{
		rooms = new HashMap<Integer, Room>();
		for (int i = 0; i < roomsCount; i++)
			rooms.put(i+1, new Room(i+1, this));
		restaurant = new Restaurant();
		delivery = new Delivery(this);
		janitor = new Janitor(this);
		laundryService = new LaundryService();
	}
	
	public Room occupyRoom()
	{
		for (Map.Entry<Integer, Room> entry : rooms.entrySet())
		{
			Room room = entry.getValue();
			if (!room.isOccupied())
			{
				room.occupy();
				return room;
			}
		}
		return null;
	}
	
	public void orderFood(Integer roomNumber, Integer[] menuItemIds)
	{
		String[] order = restaurant.receiveOrder(menuItemIds);
		delivery.deliverToRoom(order, roomNumber);
	}
	
	public void orderLaundry(Integer roomNumber)
	{
		String[] laundry = delivery.deliverLaundry(roomNumber);
		if (laundry != null)
		{
			laundryService.wash(laundry);
			delivery.deliverToRoom(laundry, roomNumber);
		}
	}
	
	public void orderRoomCleaning(Integer roomNumber)
	{
		janitor.cleanRoom(roomNumber);
	}
	
	public Room getRoom(Integer roomNumber)
	{
		return rooms.get(roomNumber);
	}
}

public class HotelTest {

	public static void main(String[] args)
	{
		Hotel hotel = new Hotel(10);
		Room myRoom = hotel.occupyRoom();
		hotel.orderFood(myRoom.Number(), new Integer[] {1,2,3});
		hotel.orderLaundry(myRoom.Number());
		myRoom.free();
	}

}
