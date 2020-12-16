

public class TeamChoice {
	/* EARTH'S RADIUS IN MILES */
	static final double EARTH_RADIUS_MILES = 3959;
	
	/*
	 * Class: Box
	 * 
	 * Creates a box to search through longitudes and latitudes when querying. 
	 * This is supposed to minimize runtime.
	 */
	public class Box {
		private double rightSide = 0;
		private double leftSide = 0;
		private double topSide = 0;
		private double bottomSide = 0;
		
		public Box (double sourceLat, double sourceLon) {
			double margin = 0.05;
			rightSide = sourceLon + margin;
			leftSide = sourceLon - margin;
			topSide = sourceLat + margin;
			bottomSide = sourceLat + margin;
		}
	}
	
	/*
	 * Method: isWithinRadius
	 * Returns: boolean
	 * 
	 * Method determines if one location is within a radius of another by using
	 * geographical points (latitudes and longitudes). THIS IS AN APPROXIMATION!!
	 * Assumes Earth is a perfect sphere which is not the case.
	 * 
	 */
	static boolean isWithinRadius (double radiusMiles, double sourceLat, double sourceLon, double targetLat, double targetLon) {
		
		double latDiffRad = Math.toRadians(sourceLat - targetLat);
		double lonDiffRad = Math.toRadians(sourceLon - targetLon);
		
		double a = Math.sin(latDiffRad / 2) * Math.sin(latDiffRad / 2) + 
				Math.cos(Math.toRadians(sourceLat)) * Math.cos(Math.toRadians(targetLat)) * 
				Math.sin(lonDiffRad / 2) * Math.sin(lonDiffRad / 2);
		
		double distance = 2.0 * EARTH_RADIUS_MILES * Math.asin(Math.sqrt(a));
		
		System.out.println(distance);
		
		return (distance <= radiusMiles) ? true : false;
	}
	
	
}