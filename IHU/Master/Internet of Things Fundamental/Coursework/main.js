//Importing Modules
const record = require("node-record-lpcm16");
const speech = require("@google-cloud/speech");
const axios = require("axios");
const dotenv = require("dotenv").config();
const { Client } = require("@notionhq/client");
const Gpio = require("onoff").Gpio;

// Initialize Google Speech Recognition and Notion Client, get Notion database ID from .env
const client = new speech.SpeechClient();
const notion = new Client({ auth: process.env.NOTION_API_TOKEN });
const notionDatabaseId = process.env.NOTION_DATABASE_ID;

//Setup GPIO pins for LEDs
const whiteLED = new Gpio(18, "out");
const greenLED = new Gpio(12, "out");
const redLED = new Gpio(25, "out");

// Control LED behaviour based on the Notion API status
function handleLED(success) {
  whiteLED.writeSync(0); // Turn off white
  if (success) {
    greenLED.writeSync(1); // Turn on green
    setTimeout(() => greenLED.writeSync(0), 2000); // Turn off green after 2 seconds
  } else {
    redLED.writeSync(1); // Turn on red
    setTimeout(() => redLED.writeSync(0), 2000); // Turn off red after 2 seconds
  }
  setTimeout(() => whiteLED.writeSync(1), 2000); // Turn on white after 2 seconds
}

// Add transcribed data to Notion Task Tracker
async function addItemToDatabase(transcription) {
  let displayMessage = {
    status: "",
    message: transcription,
  };

  try {
    const response = await notion.pages.create({
      parent: { database_id: notionDatabaseId },
      properties: {
        Name: {
          title: [
            {
              text: {
                content: transcription,
              },
            },
          ],
        },
      },
    });
    handleLED(true);
    console.log("Success! Entry added:", response);
    displayMessage.status = "Success";
  } catch (error) {
    handleLED(false);
    console.error("Error adding entry to Notion:", error.body);
    displayMessage.status = "Error";
    displayMessage.message = error.message;
  }

  sendToDisplay(displayMessage);
}

//POST the transcribed voice command to display (Flask Server ip)
function sendToDisplay(displayMessage) {
  axios
    .post("http://192.168.1.15:5000/display", displayMessage)
    .then((response) => console.log("Message sent to display:", response.data))
    .catch((error) => console.error("Error sending message:", error));
}

// Configure the request for speech recognition
const request = {
  config: {
    encoding: "LINEAR16",
    sampleRateHertz: 16000,
    languageCode: "en-US",
  },
  interimResults: false,
};

// Create a stream for recognizing speech in real-time
const recognizeStream = client
  .streamingRecognize(request)
  .on("error", (err) => console.error("Error:", err))
  .on("data", (data) => {
    if (data.results[0] && data.results[0].alternatives[0]) {
      const transcription = data.results[0].alternatives
        .map((alternative) => alternative.transcript)
        .join(", ");
      console.log("Transcription:", transcription);

      addItemToDatabase(transcription);
    } else {
      console.log("No transcription result or empty response");
    }
  });

// Turn on the white LED when the application starts
whiteLED.writeSync(1);

//Configure audio recording and pipe it to the speech regognition stream
record
  .record({
    sampleRateHertz: 16000,
    threshold: 0,
    verbose: false,
    recordProgram: "arecord",
    recorder: "arecord",
    device: "default",
  })
  .stream()
  .on("error", (err) => console.error("Recording error:", err))
  .pipe(recognizeStream);

// Handle application exit, turn off and unexport all LEDs
process.on("SIGINT", () => {
  // Turn off all LEDs
  whiteLED.writeSync(0);
  whiteLED.unexport();
  greenLED.unexport();
  redLED.unexport();
  process.exit();
});

console.log("Listening, press Ctrl+C to stop.");
