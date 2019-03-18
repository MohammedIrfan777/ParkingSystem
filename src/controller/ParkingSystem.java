package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

public class ParkingSystem implements ApplicationLeterals{
	private ParkingSystemHelper parkingSystemHelper = new ParkingSystemHelper();

	public static void main(String[] args) {
		ParkingSystem parkingSystem = new ParkingSystem();
		parkingSystem.run();
	}

	/**
	 * Method to read the data from text file or command prompt.
	 */
	public void run() {
		while(true) {
			try {
				File file = new File(FILE_PATH);
				if(file.isFile()) {
					BufferedReader br = new BufferedReader(new FileReader(file));
					String line;
					while((line = br.readLine()) != null){
						buildCarPool(line);
					}
					break;
				}else {
					if(buildCarPool(null))
						break;
				}
			} catch (Exception e) {
				System.out.println(ERROR_MESSAGE);
				e.printStackTrace();
			}
		}
	}

	/**
	 * Method BuildCarPool
	 */
	boolean buildCarPool(String data) {
		String selectedCommond;
		String selectedCommondArray[] = null;
		Scanner menuChoice = null;
		if(data == null) {
			parkingSystemHelper.showMenu();
			menuChoice = new Scanner(System.in);
			selectedCommond = menuChoice.hasNext() ? menuChoice.next() : null;
		} else {
			selectedCommondArray = data.split(" ");
			selectedCommond = selectedCommondArray[ZERO];
		}
		if (PARKING_SLOT_SIZE.equals(selectedCommond)) {
			String parkingSlotSize = data == null ? menuChoice.hasNext() ? menuChoice.next() : null : selectedCommondArray[ONE];
			parkingSystemHelper.createParkingLot(Integer.parseInt(parkingSlotSize));
		} else if (PARK.equals(selectedCommond)) {
			String registerNumber = data == null ? menuChoice.hasNext() ? menuChoice.next() : null : selectedCommondArray[ONE];
			String colour = data == null ? menuChoice.hasNext() ? menuChoice.next() : null : selectedCommondArray[ONE];
			parkingSystemHelper.parkVehicle(registerNumber, colour);
		} else if (LEAVE.equals(selectedCommond)) {
			Integer slotNumber = data == null ?  menuChoice.hasNext() ? menuChoice.nextInt() : null : Integer.parseInt(selectedCommondArray[ONE]);
			parkingSystemHelper.carExit(slotNumber);
		} else if (STATUS.equals(selectedCommond)) {
			parkingSystemHelper.status();
		} else if (RNFCWC.equals(selectedCommond)) {
			String colour = data == null ? menuChoice.hasNext() ? menuChoice.next() : null : selectedCommondArray[ONE];
			parkingSystemHelper.findCarbyColour(colour, false);
		} else if (SNFCWC.equals(selectedCommond)) {
			String colour = data == null ? menuChoice.hasNext() ? menuChoice.next() : null : selectedCommondArray[ONE];
			parkingSystemHelper.findCarbyColour(colour, true);
		} else if (SNFRN.equals(selectedCommond)) {
			String registerNumber = data == null ? menuChoice.hasNext() ? menuChoice.next() : null : selectedCommondArray[ONE];
			parkingSystemHelper.findSlotNumber(registerNumber);
		} else if (EXIT.equals(selectedCommond)) {
			return true;
		} else {
			System.out.println(ERROR_MESSAGE);
		}
		return false;
	}
}

