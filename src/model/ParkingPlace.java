package model;

import java.util.ArrayList;
import java.util.List;

public class ParkingPlace {

	private List<ParkingSlot> parkingSlots = new ArrayList<ParkingSlot>();
	private Integer parkingSlotSize;
	public List<ParkingSlot> getParkingSlots() {
		return parkingSlots;
	}
	public void setParkingSlots(List<ParkingSlot> parkingSlots) {
		this.parkingSlots = parkingSlots;
	}
	public Integer getParkingSlotSize() {
		return parkingSlotSize;
	}
	public void setParkingSlotSize(Integer parkingSlotSize) {
		this.parkingSlotSize = parkingSlotSize;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((parkingSlots == null) ? 0 : parkingSlots.hashCode());
		result = prime * result + ((parkingSlotSize == null) ? 0 : parkingSlotSize.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ParkingPlace other = (ParkingPlace) obj;
		if (parkingSlots == null) {
			if (other.parkingSlots != null)
				return false;
		} else if (!parkingSlots.equals(other.parkingSlots))
			return false;
		if (parkingSlotSize == null) {
			if (other.parkingSlotSize != null)
				return false;
		} else if (!parkingSlotSize.equals(other.parkingSlotSize))
			return false;
		return true;
	}

	
	
	
}
