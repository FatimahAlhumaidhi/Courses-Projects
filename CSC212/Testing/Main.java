public class Main {
		public static void main(String[] args) {
			testVHM();
			System.out.println("**********************");
			testLocator();
		}

		private static void testVHM() {
			VehicleHiringManager vhm = new VehicleHiringManager();
			System.out.println(vhm.addVehicle("F", new Location(4, 7))); // true
			System.out.println(vhm.addVehicle("V", new Location(5, 7))); // true
			System.out.println(vhm.addVehicle("K", new Location(6, 1))); // true
			System.out.println(vhm.addVehicle("D", new Location(4, 3))); // true
			System.out.println(vhm.addVehicle("O", new Location(4, 8))); // true
			System.out.println(vhm.addVehicle("U", new Location(8, 4))); // true
			System.out.println(vhm.addVehicle("V", new Location(8, 2))); // false
			System.out.println(vhm.addVehicle("Y", new Location(2, 2))); // true
			System.out.println(vhm.addVehicle("S", new Location(6, 1))); // true
			System.out.println(vhm.addVehicle("B", new Location(6, 3))); // true
			
			System.out.println("----------------------");
			System.out.println(vhm.getVehicleLoc("E")); // null
			System.out.println(vhm.getVehicleLoc("S")); // (6,1)
			System.out.println(vhm.getVehicleLoc("F")); // (4,7)

			System.out.println("----------------------");
			print(vhm.getVehiclesInRange(new Location(5, 5), 2)); //F V D B lower(3,3) upper(7,7) 
			print(vhm.getVehiclesInRange(new Location(3, 3), 1)); //Y and D lower(2,2) upper(4,4) 
			print(vhm.getVehiclesInRange(new Location(0, 0), 10)); //all    lower(-10,-10) upper(10,10) 

			System.out.println("----------------------");
			Map<String, Location> map = vhm.getLocatorMap().getMap();
			System.out.println(map.find("U")); // true, 5
			System.out.println(map.find("K")); // true, 3
			System.out.println(map.find("X")); // false, 3

			System.out.println("----------------------");
			Locator<String> locator = vhm.getLocatorMap().getLocator();
			System.out.println(locator.get(new Location(3, 5)).second); // 2
			System.out.println(locator.get(new Location(2, 6)).second); // 2
			System.out.println(locator.get(new Location(8, 3)).second); // 3
			System.out.println(locator.get(new Location(5, 1)).second); // 2
			System.out.println(locator.get(new Location(4, 2)).second); // 3
			System.out.println(locator.get(new Location(0, 0)).second); // 2
			
			print(locator.get(new Location(3, 5)).first); // empty
			print(locator.get(new Location(2, 2)).first); // Y
			print(locator.get(new Location(8, 4)).first); // U
			print(locator.get(new Location(5, 1)).first); // empty
			print(locator.get(new Location(4, 3)).first); // D
			print(locator.get(new Location(6, 3)).first); // B
			System.out.println(locator.inRange(new Location(2, 2), new Location(8, 8)).second); // 8
			System.out.println(locator.inRange(new Location(4, 4), new Location(6, 6)).second); // 4
			System.out.println(locator.inRange(new Location(0, 0), new Location(2, 3)).second); // 2
			
			print(locator.inRange(new Location(2, 2), new Location(8, 8)).first); // All  Except K and S
			print(locator.inRange(new Location(4, 4), new Location(6, 6)).first); // empty
			print(locator.inRange(new Location(0, 0), new Location(2, 3)).first); // Y
		}

		private static void testLocator() {
			TreeLocator<String> locator = new TreeLocator<String>();
			System.out.println(locator.add("F", new Location(4, 7))); //root     0
			System.out.println(locator.add("V", new Location(5, 7))); //c3       1
			System.out.println(locator.add("K", new Location(6, 1))); //c4       1
			System.out.println(locator.add("V", new Location(5, 7))); //c3       1
			System.out.println(locator.add("D", new Location(4, 3))); //c4 c2    2
			System.out.println(locator.add("O", new Location(4, 8))); //c2       1
			System.out.println(locator.add("Q", new Location(5, 7))); //c3       1
			System.out.println(locator.add("V", new Location(5, 7))); //c3       1
			System.out.println(locator.add("U", new Location(8, 4))); //c4 c3    2
			System.out.println(locator.add("V", new Location(8, 2))); //c4 c3 c4 3
			System.out.println(locator.add("Y", new Location(2, 2))); //c1       1
			System.out.println(locator.add("S", new Location(6, 1))); //c4       1
			System.out.println(locator.add("B", new Location(6, 3))); //c4 c2 c3 3
			
			System.out.println(locator.remove("V", new Location(5, 7)).first);

			System.out.println(locator.get(new Location(3, 5)).second); //2
			System.out.println(locator.get(new Location(2, 6)).second); //2
			System.out.println(locator.get(new Location(8, 3)).second); 
			System.out.println(locator.get(new Location(5, 1)).second); 
			System.out.println(locator.get(new Location(4, 2)).second); 
			System.out.println(locator.get(new Location(0, 0)).second); 
			System.out.println(locator.inRange(new Location(2, 2), new Location(8, 8)).second); //9
			System.out.println(locator.inRange(new Location(4, 4), new Location(6, 6)).second); //4
			System.out.println(locator.inRange(new Location(0, 0), new Location(2, 3)).second); //2
		}


		private static <T> void print(List<T> l) {
			if (l == null) {
				System.out.println("[null]");
				return;
			}
			if (l.empty()) {
				System.out.println("[empty]");
				return;
			}
			l.findFirst();
			while (!l.last()) {
				System.out.print(l.retrieve() + ", ");
				l.findNext();
			}
			System.out.println(l.retrieve());
		}

}