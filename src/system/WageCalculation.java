package system;

@FunctionalInterface
public interface WageCalculation {
    double calculateWage(double hours, double overTime);
}
