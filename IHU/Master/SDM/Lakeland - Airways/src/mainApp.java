import java.awt.print.Book;

public class mainApp {
    public static void main(String[] args) {

        Ticket_Agent TicketAgent4 = new Ticket_Agent("Ticket Agent", "Bob", 693447772, "Bob@gmail.com", "Helpdesk");
        Ticket_Agent TicketAgent5 = new Ticket_Agent("Ticket Agent", "Martha", 693447772, "Martha@gmail.com", "Ticket Agent Manager");

//      With this method we get how many employees has been instantiated
        Employee.Employees();

//      Issuing tickets using Ticket_Agent's method
        Ticket tk1 = TicketAgent4.issueTicket(31, "Kostas Papas", 20240423, "London", "Athens", "LON0345", 102.4F, 10.2F, "YPA14");
        Ticket tk2 = TicketAgent5.issueTicket(34, "Kiki Louda", 20240421, "Amsterdam", "Thessaloniki", "AMS0233", 140.4F, 10.2F, "YPA14");

        Reservation rs1 = TicketAgent4.makeReservation(2,3,4,5,6);

        Booking bk1 = TicketAgent4.makeBooking(10, 32, 59, 34);

        Ticket tk3 = TicketAgent4.issueTicket(36, "Fotis Migas", 20240411, "Sofia", "Bristol", "SOF01115", 88.4F, 9.3F, "YPA7");

        bk1.getValues();
        TicketAgent4.getValues();
    }
}
