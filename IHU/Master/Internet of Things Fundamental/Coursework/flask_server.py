#Import libraries
from luma.core.render import canvas
from flask import Flask, request
from luma.core.interface.serial import i2c
from luma.oled.device import sh1106
import threading

#Create flask application instance
app = Flask(__name__)

# Initialize the OLED display using I2C communication
serial = i2c(port=1, address=0x3C)
oled = sh1106(serial)

# Function to update OLED display with the status and message
def update_display(status, message):
    with canvas(oled) as draw:
        # Display status in the top-right corner
        draw.text((oled.width - 50, 0), status, fill="white")

        # Display message starting from the left in the middle
        draw.text((10, 20), message, fill="white")

#Endpoint to handle POST requests for display messages
@app.route('/display', methods=['POST'])
def display_message():
    data = request.json
    status = data.get('status', 'Unknown')
    message = data.get('message', '')

    #Starting a new thread to update the display
    threading.Thread(target=update_display, args=(status, message)).start()
    return "Message received", 200

#Running the Flask app on host 0.0.0.0, port 5000
if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5000)
