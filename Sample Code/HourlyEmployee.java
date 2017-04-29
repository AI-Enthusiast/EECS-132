/* An employee paid by the hour */
public class HourlyEmployee extends Employee {
  
  /* hourly wage of the employee */
  private double hourlyWage;
  
  /* amount of hours worked */
  private double hoursWorked;
  
  /* the constructor for hourly employee */
  public HourlyEmployee(String name) {
    super(name);
  }
  
  /* change the hourly wage */
  public void setHourlyWage(double hourlyWage) {
    this.hourlyWage = hourlyWage;
  }
  
  /* retrieve the hourly wage */
  public double getHourlyWage() {
    return hourlyWage;
  }
  
  /* change the hours worked */
  public void setHoursWorked(double hoursWorked) {
    this.hoursWorked = hoursWorked;
  }
  
  /* retrieve the hours worked */
  public double getHoursWorked() {
    return hoursWorked;
  }
  
  /* change the behavior of the getSalary method.
   * For an HourlyEmployee, the "salary" is the hourly wage * hours worked */
  @Override
  public double getSalary() {
    return getHourlyWage() * getHoursWorked();
  }
  
}