// kafkaClient.js
import { Kafka } from "kafkajs";
import Name from "./models/name.js"; // Importing the Name model

// Kafka setup
const kafka = new Kafka({
  clientId: "real-time-ticketing-system",
  brokers: ["localhost:9092"],
});

const producer = kafka.producer();
const consumer = kafka.consumer({ groupId: "ticket-group" });

// Connect and run the Kafka producer
export const connectProducer = async () => {
  await producer.connect();
  console.log("Producer connected");
};

// Send message to Kafka
export const sendMessage = async (topic, message) => {
  await producer.send({
    topic,
    messages: [{ value: message }],
  });
  console.log(`Message sent to topic ${topic}`);
};

// Connect and run the Kafka consumer
export const connectConsumer = async () => {
  await consumer.connect();
  console.log("Consumer connected");
};

// Subscribe consumer and process messages
// Consumer code snippet where you save the data
export const subscribeAndRunConsumer = async (topic) => {
  await consumer.subscribe({ topic, fromBeginning: true });
  await consumer.run({
    eachMessage: async ({ topic, partition, message }) => {
      const receivedName = message.value.toString();
      console.log(`Received message: ${receivedName}`);

      // Save the received name to MongoDB using the correct model
      try {
        await Name.create({ name: receivedName });
        console.log("Name saved to MongoDB successfully");
      } catch (error) {
        console.error("Error saving name to MongoDB:", error);
      }
    },
  });
};
