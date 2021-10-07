# Overview

The purpose of this document is to provide a software requirements specification for our rhythm game. Both functional and non-functional requirements will be showcased. Functional requirements will showcase the more in-depth, specific specifications regarding feature implementation (what the software is going to do). Non-functional requirements will showcase higher level ideas that will be related to how the software behaves (example: "The game shall have a pleasing interface" is a high level idea without specifics about how it will be implemented).

# Functional Requirements

1. Score Keeping
	1. The user's high scores shall be recorded and retrievable via a simple database.
	2. The high score database shall be easily accessible from the song selection screen.
	3. The user's high score shall be recorded for each difficulty.
	4. The game shall calculate the high score utilizing the accuracy of a user's input.
	5. The high score for the song played shall be visible immediately upon completion.

2. User Input and Gameplay
	1. The speed of a block moving across the screen shall be user defineable within an options menu.
	2. The game's internal system that handles key strokes shall not have any responiveness issues.
		- A user's inputs for each key shall be calculated seperately, as to avoid overlap.
		- A user's inputs shall be calculated the frame after the input was registered, as to not cause an input to go undetected.
	3. The gameplay loop shall begin upon pressing the "Play Game" button on the main screen.
	4. The system shall close the game window when the "Quit" button is pressed.

3. Assets and Graphics Design
	1. The game's graphics shall not include any graphics that are in accordance with the following:
		- Assets shall not include colors that are of high saturation, such that the contrast causes the color scheme to be displeasing.
		- Images, colors, and text, shall not pose a challenge when a user is trying to read them, or parse visual information.
		- Colors and lights shall not flash rapidly (16 frames per second or faster) as to not cause eye strain or epileptic reactions..
	

# Non-Functional Requirements

3. Graphics and User Interface Design
	1. The design of the interface shall be user-friendly as to not cause confusion about functionality.
	2. The user interface shall respond quickly to changes in volume and scroll speed.
	3. Assets shall not be visually distracting or cluttered.
	4. Notes shall be consistent with audio, and be responsive to user input.
	5. All parts of the interface shall be consistent and easily navigable.
	6. The game screens shall load quickly upon switching.

4. High Level Gameplay Loop Implementations
	1. High score database shall be responsive and update quickly.
	2. The difficulties of each song shall be consistent and easily understood by the user.
	3. The user shall recieve reponsive visual and audio cues to indicate accuracy of a button press.
	4. The user's keystrokes shall be read effectively and quickly
