public class Ticket {

    private int ticketId;
    private String passengerName;
    private int flightDate;
    private String arrivalAirportId;
    private String departureAirportId;
    private String flightId;
    private Float ticketCost;
    private Float discount;
    private String ticketBasis;
    private static int id=0;
// +loyaltyPointsAdded

    // public Ticket(int ticketId, String passengerName, int flightDate, String arrivalAirportId, String departureAirportId, String flightId, Float ticketCost, Float discount, String ticketBasis){
    public Ticket(int ticketId, String passengerName, int flightDate, String arrivalAirportId, String departureAirportId, String flightId, Float ticketCost, Float discount, String ticketBasis){

        id=id+1;
        this.ticketId=id;
        this.passengerName=passengerName;
        this.flightDate=flightDate;
        this.arrivalAirportId=arrivalAirportId;
        this.departureAirportId=departureAirportId;
        this.flightId=flightId;
        this.ticketCost=ticketCost;
        this.discount=discount;
        this.ticketBasis=ticketBasis;
    }

    public void getValues(){
        System.out.println("Ticket id is: "+ticketId);
        System.out.println("Passenger name is: "+passengerName);
        System.out.println("Flight date is: "+flightDate);
        System.out.println("Arrival Airport is: "+arrivalAirportId);
        System.out.println("Departure Airport is: "+departureAirportId);
        System.out.println("Flight Id is: "+flightId);
        System.out.println("Ticket Cost is: "+ticketCost);
        System.out.println("discount is: "+discount);
        System.out.println("ticketBasis is: "+ticketBasis);
        System.out.println();
    }
}
