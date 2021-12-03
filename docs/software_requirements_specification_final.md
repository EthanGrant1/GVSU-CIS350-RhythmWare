# Overview

The purpose of this document is to provide a software requirements specification for our rhythm game. Both functional and non-functional requirements will be showcased. Functional requirements will showcase the more in-depth, specific specifications regarding feature implementation (what the software is going to do). Non-functional requirements will showcase higher level ideas that will be related to how the software behaves (example: "The game shall have a pleasing interface" is a high level idea without specifics about how it will be implemented). This document also showcases the artifacts that were produced over the course of producing the game.

# Software Requirements

Requirements for the game will be showcased in this section. These are guidelines that were followed when producing the game to ensure that the scope is manageable, and what needs to be in the game overall. Requirements are split into 2 categories. Functional requirements are requirements that are more concrete and related to design principles (how something is going to be implemented). Non-functional requirements are requirements that are considered more "high-level" and non-specific in terms of actual implementation. 

## Functional Requirements

### Score Keeping
| Requirement ID | Requirement Description 															|
| :------------: | :---------------------: 															|
| FR1 | The user's score shall be displayed on the screen during gameplay via the graphics module.								|
| FR2 | The user's note "judgements" shall be displayed during gameplay (i.e. "Number of Perfects / Excellents / etc:")						|
| FR3 | The user's high score shall be calculated as a percentage of a total (i.e. "(Number of notes * Judgement score / Perfect score) * 100")			|
| FR4 | The game shall calculate the high score utilizing the accuracy of a user's input (Perfect judgements are worth 300 points, Excellent 250, etc.).	|
| FR5 | The user's score and accuracy judgements shall be visible immediately upon completion via an end screen.						|

### User Input and Gameplay
| Requirement ID | Requirement Description															|
| :------------: | :---------------------:															|
| FR6  | The game shall provide feedback to the user that a button has been pressed (via a graphic on screen that displays whenever a user presses a button).	|
| FR7  | The game's internal system that handles key strokes shall not have any responsiveness issues via timing and key press modules. Specifically:		|
| FR8  | 	- A user's inputs for each key shall be calculated independently (separate each key modularly rather than use a if-else structure).		|
| FR9  |	- A user's inputs shall be calculated the frame after the input was registered.									|
| FR10 | The gameplay loop shall begin upon pressing the "Play Game" button on the main screen.									|
| FR11 | The system shall close the game window when the "Quit" button is pressed.										|

## Song Menu and Audio
| Requirement ID | Requirement Description															|
| :------------: | :---------------------:															|
| FR12 | A library or module shall be used in order to play audio on the song select menu and during gameplay (i.e. jlayer).					|
| FR13 | The song select menu shall have an audio preview of the song play before the game starts.								|
| FR14 | The notes shall be timed with the audio (Timed by frame intervals. "What frame does this note play on?" etc.)						|
| FR15 | The timing of the notes shall be offset to take into account the distance to travel down the screen (Pixels / Frame over a certain distance).		|
| FR16 | The audio module shall be relegated to a separate thread in order to not conflict with gameplay and user interaction.					|
| FR17 | The song menu shall display some info and graphics about the song (Song title, Artist, a banner image, etc.)						|

## Non-Functional Requirements

### Graphics and User Interface Design
| Requirement ID | Requirement Description 															|
| :------------: | :---------------------: 															|
| NFR1 | The design of the interface shall be user-friendly as to not cause confusion about functionality.							|
| NFR2 | The user interface shall respond quickly and provide feedback to the user.										|
| NFR3 | Assets shall not be visually distracting or cluttered.													|
| NFR4 | Notes shall be consistent with audio, and be responsive to user input.											|
| NFR5 | All parts of the interface shall be consistent and easily navigable.											|
| NFR6 | The game screens shall load quickly upon switching.													|

### High Level Gameplay Loop Implementations
| Requirement ID | Requirement Description 															|
| :------------: | :---------------------: 															|
| NFR7  | The high score display shall update quickly and be easy to read.											|
| NFR8  | The difficulty of the game shall be challenging, but easily understandable by a user.									|
| NFR9  | The user shall receive reponsive visual cues to indicate accuracy of a button press.									|
| NFR10 | The user's keystrokes shall be read effectively and quickly.												|
| NFR11 | Notes shall be arranged appropriately to accurately represent the song structure.									| 

### Assets and Graphics Design
| Requirement ID | Requirement Description 															|
| :------------: | :---------------------: 															|
| NFR12  | The game's graphics shall not include any graphics that are considered displeasing, ineffective, or otherwise hard to read. Specifically:		|
| NFR13  | 	- Assets shall not include colors that are highly satuarated (bright, displeasing color schemes)						|
| NFR14  |	- Images, colors, and text, shall not pose a challenge when a user is trying to read them, or parse visual information.				|
| NFR15  |	- Colors and lights shall not flash rapidly as to not cause eye strain or epileptic reactions.							|
| NFR16  | The note colors shall be differentiable so that it is not confusing to the user.									| 

# Software Artifacts

The purpose of this section is to showcase various artifacts that were produced during the production of the game. These can include prototype art, mockups, charts, etc.

* [RhythmWareC1.pdf](https://github.com/EthanGrant1/GVSU-CIS350-RhythmWare/tree/master/artifacts/Concepts/RhythmWareC1.pdf)
* [RhythmWareC2.pdf](https://github.com/EthanGrant1/GVSU-CIS350-RhythmWare/tree/master/artifacts/Concepts/RhythmWareC2.pdf)
* [RhythmWareC3.pdf](https://github.com/EthanGrant1/GVSU-CIS350-RhythmWare/tree/master/artifacts/Concepts/RhythmWareC3.pdf)
* [Gameplay-Use-Case-Diagram.png](https://github.com/EthanGrant1/GVSU-CIS350-RhythmWare/tree/master/artifacts/use_case_diagrams/Gameplay-Use-Case-Diagram.png)
* [UseCase-OptionsMenu.pdf](https://github.com/EthanGrant1/GVSU-CIS350-RhythmWare/tree/master/artifacts/use_case_diagrams/UseCase-OptionsMenu.pdf)
* [use_case_song_select.png](https://github.com/EthanGrant1/GVSU-CIS350-RhythmWare/blob/master/artifacts/use_case_digrams/use_case_song_select.png)
