public class Reservation {
  int reservationId;
  int reservationDate;
  int customerId;
  int flightId;
  int availUntilDate;

  public Reservation(int reservationId, int reservationDate, int customerId, int flightId, int availUntilDate){
      this.reservationId=reservationId;
      this.reservationDate=reservationDate;
      this.customerId=customerId;
      this.flightId=flightId;
      this.availUntilDate=availUntilDate;
  }

  public void getValues(){
      System.out.println("Reservation ID is: "+reservationId);
      System.out.println("Reservation Date is: "+reservationDate);
      System.out.println("Customer ID is: "+customerId);
      System.out.println("Flight ID is: "+flightId);
      System.out.println("Availability Until Date is: "+availUntilDate);
      System.out.println();

  }

}
