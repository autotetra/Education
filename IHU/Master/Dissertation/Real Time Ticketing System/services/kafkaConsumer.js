import { Kafka } from "kafkajs";

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
      // e.g store a ticket to MongoDB
    },
  });
};

export { connectConsumer, consumeMessages };
