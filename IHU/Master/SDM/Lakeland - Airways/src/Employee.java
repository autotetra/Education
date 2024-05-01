class Employee {

    // Fields
    private String role;
    private String name;
    private int telephoneNumber;
    private String email;
    private int Employee_Id;
//    This field "Work" is a field that to store the things that an employee has done. (This field does not appear in the class diagram)
    String Work;
    private static int id = 0;

    // Constructor
    public Employee(String role, String name, int telephoneNumber, String email){
        id = id+1;
        Work = " ";
        this.role = role;
        this.name = name;
        this.telephoneNumber = telephoneNumber;
        this.email = email;
        this.Employee_Id=id;
    }

    // Methods
    public void getValues(){
        System.out.println("Role: "+role);
        System.out.println("name: "+name);
        System.out.println("Telephone Number: "+telephoneNumber);
        System.out.println("Email: "+email);
        System.out.println("Employee id: "+Employee_Id);
        System.out.println("This Employee has done the following: "+Work);
    }

    // A static method, this is for the whole class
    public static void Employees(){
        System.err.println("Employees are: "+id);
    }
}
