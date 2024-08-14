import { EventEmitter } from "events";

const url = "http://mylogger.io/log";

export class Logger extends EventEmitter {
  log = (message) => {
    //Send HTTP request
    console.log(message);

    //Raise an event
    this.emit("messageLogged", {
      id: 1,
      url: "http://",
    });
  };
}
