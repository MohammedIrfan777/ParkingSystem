package controller;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import model.Car;
import model.ParkingPlace;
import model.ParkingSlot;

public class ParkingSystemHelper implements ApplicationLeterals{

	private static ParkingPlace parkingPlace = new ParkingPlace();
	
	public static void showMenu() {
		StringBuffer menu = new StringBuffer();
		menu.append("**** PARKING SYSTEM MENU **** \n" )
			.append("create_parking_lot \n")
			.append("park \n")
			.append("leave \n")
			.append("status \n")
			.append("registration_numbers_for_cars_with_colour \n")
			.append("slot_numbers_for_cars_with_colour \n")
			.append("slot_number_for_registration_number \n")
			.append("exit \n")
			.append("Please enter suitable command");
		System.out.println(menu);
	}
	
	public void createParkingLot(Integer parkingSlotSize) {
		if (!isParkingSlotCreated() || parkingPlace.getParkingSlots().size() == ZERO) {
			parkingPlace.setParkingSlotSize(parkingSlotSize);
			System.out.println("Created a parking lot with " + parkingSlotSize + " slots.");
		} else {
			System.out.println(PARKING_SLOT_NOT_EMPTY);
		}
	}
	
	public void parkVehicle(String registerNumber, String colour) {
		if (isParkingSlotCreated()) {
			if(isParkingSlotFull()) {
				System.out.println("Sorry, parking lot is full");
			} else {
				System.out.println(carParking(registerNumber, colour));
			}
		} else {
			System.out.println("Please create the parking slots.");
		}
	}
	
	public void carExit(Integer slotNumber) {
		parkingPlace.getParkingSlots().remove(slotNumber - ONE);
		System.out.println("Slot number " + slotNumber + " is free");
	}
	
	public void status() {
		if (isParkingSlotEmpty()) {
			System.out.println("Parking slots empty.");
		} else {
			StringBuffer status = new StringBuffer();
			status.append("Slot No.    Registration No    Colour \n" );
			for (ParkingSlot parkingSlot : parkingPlace.getParkingSlots()) {
				status.append(parkingSlot.getSlotNumber() + 1)
					  .append("           ")
					  .append(parkingSlot.getCar().getRegisterNumber())
					  .append("      ")
					  .append(parkingSlot.getCar().getColour())
					  .append(" \n");
			}
			System.out.println(status);
		}
	}
	
	public void findCarbyColour(String colour, boolean printSlotNumber) {
		if (isParkingSlotEmpty()) {
			System.out.println("Parking slots empty.");
		} else {
			List<ParkingSlot> parkingSots = parkingPlace.getParkingSlots().stream().filter(parkingSlot -> parkingSlot.getCar().getColour().equals(colour)).collect(Collectors.toList());
			if (parkingSots != null && parkingSots.size() > ZERO) {
				StringBuffer registerNumbers = new StringBuffer();
				for (ParkingSlot parkingSlot : parkingSots) {
					if (printSlotNumber) {
						registerNumbers.append(parkingSlot.getSlotNumber());
					} else {
						registerNumbers.append(parkingSlot.getCar().getRegisterNumber());
					}
					registerNumbers.append(", ");
				}
				System.out.println(registerNumbers.substring(ZERO, registerNumbers.lastIndexOf(",")));
			} else {
				System.out.println("Not found");
			}
		}
	}
	
	public void findSlotNumber (String registerNumber) {
		if (isParkingSlotEmpty()) {
			System.out.println("Parking slots empty.");
		} else {
			String message = "Not Found";
			for (ParkingSlot parkingSlot : parkingPlace.getParkingSlots()) {
				if (parkingSlot.getCar().getRegisterNumber().equals(registerNumber)) {
					message = parkingSlot.getSlotNumber().toString();
				}
			}
			System.out.println(message);
		}
	}
	
	private boolean isParkingSlotCreated() {
		return parkingPlace.getParkingSlotSize() == null ? false :true;
	}
	
	private boolean isParkingSlotFull() {
		return parkingPlace.getParkingSlots().size() >= parkingPlace.getParkingSlotSize() ? true : false; 
	}
	
	private boolean isParkingSlotEmpty() {
		return parkingPlace.getParkingSlots().size() == ZERO ? true : false;
	}
	
	private String carParking(String registerNumber, String colour) {
		Car car = new Car();
		car.setRegisterNumber(registerNumber);
		car.setColour(colour);
		ParkingSlot parkingSlot = new ParkingSlot();
		parkingSlot.setSlotNumber(getSlotNumber());
		parkingSlot.setCar(car);
		parkingPlace.getParkingSlots().add(parkingSlot);
		return "Allocated slot number:" + parkingSlot.getSlotNumber();
	}
	
	private Integer getSlotNumber() {
		Integer slotNumber = 0;
		List<Integer> allocatedSlotNumber = parkingPlace.getParkingSlots().stream().map(ParkingSlot :: getSlotNumber).collect(Collectors.toList());
		for (slotNumber = 1; slotNumber <= parkingPlace.getParkingSlotSize(); slotNumber ++) {
			if(! allocatedSlotNumber.contains(slotNumber))
				return slotNumber;
		}
		return slotNumber;
	}
	
}
