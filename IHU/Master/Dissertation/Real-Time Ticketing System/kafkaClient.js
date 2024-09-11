// Import Kafka class from KafkaJS
import { Kafka } from "kafkajs";

// Create a Kafka client instance
const kafka = new Kafka({
  cliendId: "real-time-ticketing-system",
  brokers: ["localhost: 9092"], //Replace with my Kafka broker address
});

// Create a Kafka producer
const producer = kafka.producer();
// Create a Kafka consumer
const consumer = kafka.consumer({ groupId: "ticket-group" });

//Function to send messages using the producer
