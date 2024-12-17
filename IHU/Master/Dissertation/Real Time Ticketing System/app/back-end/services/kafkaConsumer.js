import { Kafka } from "kafkajs";
import Ticket from "../models/ticketModel.js";

// Initialize Kafka
const kafka = new Kafka({
  clientId: "ticketing-system",
  brokers: ["localhost:9092"],
});

// Set up the consumer
const consumer = kafka.consumer({ groupId: "ticketing-consumers" });

// Connect the consumer
const connectConsumer = async () => {
  try {
    await consumer.connect();
    console.log("Kafka Consumer connected");
  } catch (err) {
    console.error("Error connecting Kafka Consumer:", err.message);
  }
};

// Listen to a specific topic and handle incoming messages
const consumeMessages = async (topic) => {
  try {
    await consumer.subscribe({ topic, fromBeginning: true });

    await consumer.run({
      eachMessage: async ({ message }) => {
        const payload = JSON.parse(message.value.toString());
        console.log("Received message:", payload);

        try {
          const { title, description, category, status, createdBy } = payload;
          const newTicket = new Ticket({
            title,
            description,
            category,
            status,
            createdBy,
          });

          await newTicket.save(); // Save ticket to MongoDB
          console.log("Ticket saved successfully");
        } catch (err) {
          console.error("Error saving ticket to DB:", err.message);
        }
      },
    });
  } catch (err) {
    console.error("Error in Kafka consumer:", err.message);
  }
};

export { connectConsumer, consumeMessages };
