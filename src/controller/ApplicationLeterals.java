package controller;

public interface ApplicationLeterals {

	//Menu
	String PARKING_SLOT_SIZE = "create_parking_lot";
	String PARK = "park";
	String LEAVE = "leave";
	String STATUS = "status";
	String RNFCWC = "registration_numbers_for_cars_with_colour";
	String SNFCWC = "slot_numbers_for_cars_with_colour";
	String SNFRN = "slot_number_for_registration_number";
	String EXIT = "exit";
	
	//Numbers
	int ZERO = 0;
	int ONE = 1;
	int TWO = 2;
	
	//Message
	String ERROR_MESSAGE = "Please enter a valid command \n";
	String PARKING_SLOT_NOT_EMPTY = "Some of vehicles parked could't rearrange the size of parking slot now please try again later";
	
	//File Path
	String FILE_PATH = "E:\\file_input.txt";
}
