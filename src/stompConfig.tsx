
import { Client } from "@stomp/stompjs";

const connectWebSocket = (onMessage: (message: string) => void) => {
    const client = new Client({
        brokerURL : ("ws://localhost:8080/socket-endpoint"), // WebSocket URL
        connectHeaders: {},
        debug: (msg) => console.log(msg), // Logs connection events
        reconnectDelay: 5000, // Retry connection after 5 seconds
        onConnect: () => {
            console.log("STOMP connected");
            client.subscribe("/topic/notification", (message) => {
                console.log("Recieved notification: ",message.body)
                onMessage(message.body); // Use the callback to handle incoming messages
            });
        },
        onStompError: (frame) => {
            console.error("STOMP error", frame);
        },
    });

    client.activate();
};

export default connectWebSocket