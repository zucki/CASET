package model.services.calculation;

public class FunctionPointResults extends CalculationResults {

	private double ratedPoints;
	private double unratedPoints;

	public double getRatedPoints() {
		return ratedPoints;
	}

	public void setRatedPoints(double ratedPoints) {
		this.ratedPoints = ratedPoints;
	}

	public double getUnratedPoints() {
		return unratedPoints;
	}

	public void setUnratedPoints(double unratedPoints) {
		this.unratedPoints = unratedPoints;
	}
	
	
}
