// Import Kafka class from KafkaJS
import { Kafka } from "kafkajs";

// Create a Kafka client instance
const kafka = new Kafka({
  clientId: "real-time-ticketing-system",
  brokers: ["localhost:9092"], // Corrected broker address
});

// Create a Kafka producer
const producer = kafka.producer();

// Create a Kafka consumer
const consumer = kafka.consumer({ groupId: "ticket-group" });

// Function to connect and start the producer
export const connectProducer = async () => {
  await producer.connect();
  console.log("Producer Connected");
};

//Function to send messages using the producer
export const sendMessage = async (topic, message) => {
  await producer.send({
    topic: topic,
    messages: [{ value: message }],
  });
  console.log(`Message sent to topic ${topic}`);
};

// Function to connect and start the consumer
export const connectConsumer = async () => {
  await consumer.connect();
  console.log("Consumer connected");
};

// Function to subscribe the consumer to a topic and handle messages
export const subscribeAndRunConsumer = async (topic) => {
  await consumer.subscribe({ topic, fromBeginning: true });
  await consumer.run({
    eachMessage: async ({ topic, partition, message }) => {
      console.log(`Received message: ${message.value.toString()}`);
    },
  });
};

// Subscribe the consumer to the topic and start consuming
connectConsumer().then(() => {
  subscribeAndRunConsumer("test-topic").catch((error) =>
    console.error("Error in consumer:", error)
  );
});
