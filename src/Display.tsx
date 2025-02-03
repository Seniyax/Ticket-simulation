import axios from "axios";
import React, { useEffect, useState } from "react";
import connectWebSocket from "./stompConfig";


function Display(): React.JSX.Element{
    const [config, setConfig] = useState({
        totalTickets:0,
        ticketReleaseRate:0,
        customerRetrievalRate:0,
        maxTicketCapacity:0,
    });

    const [logs,setlogs] = useState<string[]>([]);

    const handlechange = (e:React.ChangeEvent<HTMLInputElement>):void => {
        const {name, value} = e.target;
        setConfig({...config, [name]: parseInt(value)});
    }

    const handlesubmit = async (e:React.MouseEvent<HTMLButtonElement>)=> {
        e.preventDefault();
        try {
            await axios.post("http://localhost:8080/api/config",config);
            alert("Config updated successfully");
        }catch (error){
            console.error("error saving config", error);
        }
    };

    const handleStart= async () =>{
        try {
            await axios.post("http://localhost:8080/api/sys/start");
            alert("Threads started");
        }catch (error){
            console.error("error saving start", error);
        }


    };
     const handleStop = async () =>{
         try {
             await axios.post("http://localhost:8080/api/sys/stop");
         }catch (error){
             console.error("error saving stop", error);
         }
     };


     const handleLogs = (message: string)=>{
             setlogs(prevlogs => [...prevlogs, message]);

         };


    useEffect(() => {
        connectWebSocket(handleLogs);
    }, []);













    return <>
            
    <div className="simulator">
      
            <h1>REAL-TIME TICKET SIMULATOR</h1>
        
        <div className="Buttons">
            <button className="startbtn" onClick={handleStart}>START</button>
            <button className="stopbuttn" onClick={handleStop}>STOP</button>
        </div>
                

            <div className="Container">
                <form >
                    <label htmlFor="TotalTickets">TotalTickets:</label>
                    <input type={"number"} id={"TotalTickets"} name="totalTickets" value={config.totalTickets} onChange={handlechange} required/><br/>
                    <label htmlFor="TicketReleaseRate">TicketReleaseRate:</label>
                    <input type={"number"} id={"TicketReleaseRate"} name="ticketReleaseRate" value={config.ticketReleaseRate} onChange={handlechange} required/><br/>
                    <label htmlFor="CustomerRetrivelRate">CustomerRetrievalRate:</label>
                    <input type={"number"} id={"CustomerRetrieveRate "} name="customerRetrievalRate" value={config.customerRetrievalRate} onChange={handlechange} required/><br/>
                    <label htmlFor="MaxTickets">Max Ticket:</label>
                    <input type={"number"} id={"MaxTickets"} name="maxTicketCapacity" value={config.maxTicketCapacity} onChange={handlechange} required/><br/>
                    <button type={"submit"} onClick={handlesubmit}>SUBMIT</button>
                </form>
                <div className= "Logs">
                <h2>Logs</h2>
                <ul >
                    {logs.map((log,index)=>
                        <li key={index}>{log}</li>)}

                </ul>
                </div>

            </div>
    </div>
        </>
};


export default Display;
