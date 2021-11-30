# Overview

The purpose of this document is to provide a software requirements specification for our rhythm game. Both functional and non-functional requirements will be showcased. Functional requirements will showcase the more in-depth, specific specifications regarding feature implementation (what the software is going to do). Non-functional requirements will showcase higher level ideas that will be related to how the software behaves (example: "The game shall have a pleasing interface" is a high level idea without specifics about how it will be implemented). This document also showcases the artifacts that were produced over the course of producing the game.

# Software Requirements

Requirements for the game will be showcased in this section. These are guidelines that were followed when producing the game to ensure that the scope is manageable, and what needs to be in the game overall. Requirements are split into 2 categories. Functional requirements are requirements that are more concrete and related to design principles (how something is going to be implemented). Non-functional requirements are requirements that are considered more "high-level" and non-specific in terms of actual implementation. 

## Functional Requirements

### Score Keeping
| Requirement ID | Requirement Description 															|
| :------------: | :---------------------: 															|
| FR1 | The user's high scores shall be recorded and retrievable via a simple database.										|
| FR2 | The high score database shall be easily accessible from the song selection screen.									|
| FR3 | The user's high score shall be recorded for each difficulty.												|
| FR4 | The game shall calculate the high score utilizing the accuracy of a user's input.									|
| FR5 | The high score for the song played shall be visible immediately upon completion.									|

### User Input and Gameplay
| Requirement ID | Requirement Description															|
| :------------: | :---------------------:															|
| FR6  | The speed of a block moving across the screen shall be user defineable within an options menu.								|
| FR7  | The game's internal system that handles key strokes shall not have any responiveness issues.								|
| FR7A | 	- A user's inputs for each key shall be calculated seperately, as to avoid overlap.								|
| FR7B |	- A user's inputs shall be calculated the frame after the input was registered, as to not cause an input to go undetected.			|
| FR8  | The gameplay loop shall begin upon pressing the "Play Game" button on the main screen.									|
| FR9  | The system shall close the game window when the "Quit" button is pressed.										|

## Non-Functional Requirements

### Graphics and User Interface Design

| Requirement ID | Requirement Description 															|
| :------------: | :---------------------: 															|
| NFR1 | The design of the interface shall be user-friendly as to not cause confusion about functionality.							|
| NFR2 | The user interface shall respond quickly to changes in volume and scroll speed.									|
| NFR3 | Assets shall not be visually distracting or cluttered.													|
| NFR4 | Notes shall be consistent with audio, and be responsive to user input.											|
| NFR5 | All parts of the interface shall be consistent and easily navigable.											|
| NFR6 | The game screens shall load quickly upon switching.													|

### High Level Gameplay Loop Implementations
| Requirement ID | Requirement Description 															|
| :------------: | :---------------------: 															|
| NFR7  | High score database shall be responsive and update quickly.												|
| NFR8  | The difficulties of each song shall be consistent and easily understood by the user.									|
| NFR9  | The user shall recieve reponsive visual and audio cues to indicate accuracy of a button press.							|
| NFR10 |The user's keystrokes shall be read effectively and quickly.												|

### Assets and Graphics Design
| Requirement ID | Requirement Description 															|
| :------------: | :---------------------: 															|
| NFR11  | The game's graphics shall not include any graphics that are in accordance with the following:						 	|
| NFR11A | 	- Assets shall not include colors that are of high saturation, such that the contrast causes the color scheme to be displeasing.		|
| NFR11B |	- Images, colors, and text, shall not pose a challenge when a user is trying to read them, or parse visual information.				|
| NFR11C |	- Colors and lights shall not flash rapidly (16 frames per second or faster) as to not cause eye strain or epileptic reactions..		|

# Software Artifacts

The purpose of this section is to showcase various artifacts that were produced during the production of the game. These can include prototype art, mockups, charts, etc.

* [RhythmWareC1.pdf](https://github.com/EthanGrant1/GVSU-CIS350-RhythmWare/tree/master/artifacts/Concepts/RhythmWareC1.pdf)
* [RhythmWareC2.pdf](https://github.com/EthanGrant1/GVSU-CIS350-RhythmWare/tree/master/artifacts/Concepts/RhythmWareC2.pdf)
* [RhythmWareC3.pdf](https://github.com/EthanGrant1/GVSU-CIS350-RhythmWare/tree/master/artifacts/Concepts/RhythmWareC3.pdf)
* [Gameplay-Use-Case-Diagram.png](https://github.com/EthanGrant1/GVSU-CIS350-RhythmWare/tree/master/artifacts/use_case_diagrams/Gameplay-Use-Case-Diagram.png)
* [UseCase-OptionsMenu.pdf](https://github.com/EthanGrant1/GVSU-CIS350-RhythmWare/tree/master/artifacts/use_case_diagrams/UseCase-OptionsMenu.pdf)
* [use case song select.png](https://github.com/EthanGrant1/GVSU-CIS350-RhythmWare/tree/master/artifacts/use case song select.png)
