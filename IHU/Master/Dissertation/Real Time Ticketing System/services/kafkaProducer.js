import { Kafka } from "kafkajs";

// Initialize Kafka
const kafka = new Kafka({
  clientId: "ticketing-system",
  brokers: ["localhost:9092"],
});

// Set up the producer
const producer = kafka.producer();

// Connect the producer
const connectProducer = async () => {
  await producer.connect();
  console.log("Kafka Producer connected");
};

// Send messages to a specific topic
const sendMessage = async (topic, messages) => {
  try {
    await producer.send({
      topic: topic,
      messages: [{ value: messages }],
    });
    console.log(`Message sent to topic ${topic}`);
  } catch (error) {
    console.error("Failed to send message: ", error);
  }
};

export { connectProducer, sendMessage };
