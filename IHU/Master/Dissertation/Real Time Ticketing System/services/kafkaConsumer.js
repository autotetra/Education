import { Kafka } from "kafkajs";
import Ticket from "../models/ticket.js";

// Initialize Kafka
const kafka = new Kafka({
  clientId: "ticketing-system",
  brokers: ["localhost: 9092"],
});

// Set up the consumer
const consumer = kafka.consumer({ groupId: "ticketing-consumers" });

// Connect the consumer
const connectConsumer = async () => {
  await consumer.connect();
  console.log("Kafka Consumer connected");
};

// Listen to a specific topic and handle incoming messages
const consumeMessages = async (topic) => {
  await consumer.subscribe({ topic, fromBeginning: true });
  await consumer.run({
    eachMessage: async ({ message }) => {
      console.log(`Received message: ${message.value.toString()}`);
      //Message processing logic here:
      const ticketData = JSON.parse(message.value.toString());

      // Create a new ticket document based on the message data
      const newTicket = new Ticket({
        title: ticketData.title,
        description: ticketData.description,
        status: ticketData.status,
      });

      // Save ticket to MongoDB
      await newTicket.save();
      console.log("Ticket saved to MongoDB");
    },
  });
};
export { connectConsumer, consumeMessages };
