# Smart Home Simulation
**Authors**: [Darina Mingazova](@mingadar), [Mikita A Citarovič](@citarmik)
## Description
The main challenge for us was to realise an interesting smart home simulation using suitable design patterns. <br/>
Of the features, you can specify the simulation acceleration factor. Also, as we are using real time, it will not be difficult to visualise it. The entire logic of the simulation of inhabitants, devices and so on is time-dependent. <br/>
Full description - [ProjektSmartHome](docs/ProjektSmartHome.pdf)
## UML class diagram
![Use case diagram](/docs/classDiagram.jpg)
## Use case diagram
![Use case diagram](/docs/useCase.png)
## Functional requirements
### From the assignment
"+" means that paragraph has been successfully completed. <br/>
"*" means that we have some notes or explanation to this paragraph.

- **F1 +** <br/>
All entities are located in the entity package.
- **F2 \*** <br/>
Our devices have no content. (didn't have time and considered it not basic :( )
- **F3 +** <br/>
- **F4 +** <br/>
- **F5 +** <br/>
- **F6 +** <br/>
- **F7 +** <br/>
- **F8 +** <br/>
- **F9 +** <br/>
- **F10 +** <br/>

### Own
- **F11 +** <br/>
Real-time simulation with acceleration capability.
- **F12 +** <br/>
The possibility of postponing tasks if no one was able to solve the problem at the time (e.g. feeding the cat).
- **F13 +** <br/>
The devices have a limited lifespan and can break down not only because of someone's use, but also because of the passage of time.

## Design patterns
- **Singleton** <br/>
Used to store the basic system state and control of the system (start and end of the simulation, links to the main parts of the system).
- **Observer** <br/>
In our system, entities whose logic depends on time are subscribed to "time". 
- **State machine** <br/>
Each device and inhabitant have different states in which their behaviour may differ.
- **Event Bus** <br/>
Any entity can both subscribe to events and generate events. This allows for a fairly flexible expansion of the system and the addition of new logic.
- **Chain of Responsibility** <br/>
This pattern works in conjunction with the Event Bus, which allows for more flexible event handling.
- **Builder (nested builder + factory)** <br/>
This pattern is used to create the configuration of our house.
- **Factory method** <br/>
Used in conjunction with the builder to create items, devices, entrances and inhabitants.
- **An abstract factory** <br/>
Used in conjunction with the builder to create different types of sensors (internal and external).
- **Proxy** <br/>
In our case, the SHSystem class has a "start" method which, after some logic, triggers the "start" method of the Simulation class. 

## Config
The configuration is defined by means of a json file, which is then parsed.