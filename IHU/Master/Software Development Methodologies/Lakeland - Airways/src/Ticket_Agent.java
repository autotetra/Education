class Ticket_Agent extends Employee {

    int Ticket_Agent_id;
    String Ticket_AgentType;

    public Ticket_Agent (String role, String name, int telephoneNumber, String email, String Ticket_AgentType) {
        super(role, name, telephoneNumber, email);
        this.Ticket_AgentType = Ticket_AgentType;
    }


    public Ticket issueTicket(int ticketId, String passengerName, int flightDate, String arrivalAirportId, String departureAirportId, String flightId, Float ticketCost, Float discount, String ticketBasis){
        Work = Work + "Ticket " + ticketId + ", ";
        return new Ticket(ticketId, passengerName, flightDate, arrivalAirportId, departureAirportId, flightId, ticketCost, discount, ticketBasis);
    }

    public Reservation makeReservation(int reservationId, int reservationDate, int customerId, int flightId, int availUntilDate){
        Work = Work + "Reservation " +  reservationId + ",  ";
        return new Reservation(reservationId, reservationDate, customerId, flightId, availUntilDate);
    }

    public Booking makeBooking(int bookingId, int ticketId, int customerId, int flightId){
        Work = Work + "Booking " +  bookingId + ", ";
        return new Booking(bookingId, ticketId, customerId, flightId);
    }

    public void getValues(){
        super.getValues();
        System.out.println("Ticket agent has the role of: "+Ticket_AgentType);
        System.out.println();
    }
}
