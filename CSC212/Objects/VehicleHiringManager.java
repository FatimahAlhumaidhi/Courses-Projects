public class VehicleHiringManager {
	LocatorMap<String> locatormap;
	
	public VehicleHiringManager() {
		locatormap = new TreeLocatorMap<String>();
	}

	// Returns the locator map.
	public LocatorMap<String> getLocatorMap() {
		return locatormap;
	}

	// Sets the locator map.
	public void setLocatorMap(LocatorMap<String> locatorMap) {
		locatormap = locatorMap;
	}

	// Inserts the vehicle id at location loc if it does not exist and returns true.
	// If id already exists, the method returns false.
	public boolean addVehicle(String id, Location loc) {
		return locatormap.add(id, loc).first;
	}

	// Moves the vehicle id to location loc if id exists and returns true. If id not
	// exist, the method returns false.
	public boolean moveVehicle(String id, Location loc) {
		return locatormap.move(id, loc).first;
	}

	// Removes the vehicle id if it exists and returns true. If id does not exist,
	// the method returns false.
	public boolean removeVehicle(String id) {
		return locatormap.remove(id).first;
	}

	// Returns the location of vehicle id if it exists, null otherwise.
	public Location getVehicleLoc(String id) {
		return locatormap.getLoc(id).first;
	}

	// Returns all vehicles located within a square of side 2*r centered at loc
	// (inclusive of the boundaries).
	public List<String> getVehiclesInRange(Location loc, int r) {
		int x1 = (loc.x)-r, x2 = (loc.x)+r;
		int y1 = (loc.y)-r, y2 = (loc.y)+r;
		Location lowerleft = new Location(x1, y1);
		Location upperright = new Location(x2, y2);
		return locatormap.getInRange(lowerleft, upperright).first;
	}
}
