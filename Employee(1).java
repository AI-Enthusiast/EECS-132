/* a class the represents employee's */
public class Employee {
  /* the name of the employee */
  private String name;
  
  /* the salary of the employee */
  private double salary;
  
  /* the rank of the employee */
  private String rank;
  
  /* the employee number - will not change value once assigned */
  private final int number;
  
  /* the last employee number used  - a class field */
  private static int lastEmployeeNumber = 0;
   
  /* a constructor to create an employee */
  public Employee(String name) {
    this.name = name;
    number = (lastEmployeeNumber = lastEmployeeNumber + 1);
  }
  
  /* retrieve the employee name */
  public String getName() {
    return name;
  }
   
  /* change the employee name */
  public void setName(String name) {
    this.name = name;
  }
  
  /* retrieve the employee salary */
  public double getSalary() {
    return salary;
  }
  
  /* change the employee salary */
  public void setSalary(double salary) {
    this.salary = salary;
  }
  
  /* retrieve the employee number */
  public int getNumber() {
    return number;
  }
  
  /* return true if this employee earns more than the input employee */
  public boolean earnsMoreThan(Employee e) {
    return this.getSalary() > e.getSalary();
  }
  
}