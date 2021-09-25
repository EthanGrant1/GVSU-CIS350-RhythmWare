Team name:
- RhythmWare

Team members:
* Roman Brancato
* Brian Turmbo
* Ethan Grant
* David Geisel

# Introduction

Our project is going to consist of a rhythm game. The main structure of the game is going to be as follows:

The player is in a play area of some kind. "Notes" (what are considered to be the main object and core objective of the game) will make their way towards the player. The player's goal is to interact with the notes in some way (whether that be through mouse, keyboard or some other control method) such that the notes don't hit the player or leave the player area. The player will be scored based on number of notes hit, and whether or not the player character hasn't "died" (i.e., the player lets too many notes leave the play area and thus has lost the game due to a lack of health or power meter of some kind). 

The "type" (or subgenre) or rhythm game that we are going to create is currently up for debate, as there are many different types which vary in scope, control, mechanical development, etc. The current types that are up for consideration are as follows:

- Scrolling rhythm game: A game in which the notes are on a "track" which is directed towards the player. The player is stationary and the notes fly towards the player, where they must interact with them once they reach a specific region in front of the player character. A possible statistic that could be tracked is the "accuracy level" of the actions that the player performs. There is a specific region in front of the player character in which a note is registered as hit, and is therefore not active after the action. The timing in which this action occurs (i.e., the action occurs too early and the note is in front of the immediate action region, the action occurs too late and the note is behind the immediate action region, or the action occurs perfectly timed and the is perfectly inside the action region). Players could be assigned a specific score value based on how well the notes were timed (i.e., 50 for poor timing, 100 for close to perfect timing, and 300 for perfect timing).

- Radial rhythm game: A game in which the play area is circular, and notes "radiate" outwards towards the edge of the circular play area. The player contols a "basket" (a small region outside of the cicle that is a portion of the sector taken from a specific angle from the center of the play area) and tries to catch the notes in the basket. Accuracy in this game could be tracked based on how close to the center of the basket a player caught a note.. 

# Anticipated Technologies

We are anticipating that this project will be built in Java. We anticipate that it will require imports for GUI, mathematical calculations (specifically for angle calculation), timers (for BPM, note, and accuracy timing, as well as triggers for game events).

# Method/Approach

Our method and approach involves creating a basic version of the specific type of rhythm game that the team most agrees on. We will begin with simplistic representation of objects (simple shapes and graphics) and create the minimum of what is required to create that specific game. If time allows, we may introduce other mechanics that will further increase the complexities of the game, iterating on ideas and see what works and what doesn't work. (See "Estimated Timeline" for a more in depth explanation of the mechanic roadmap).

# Estimated Timeline

Important Information: Times are unknown currently, but will be later refined. Lists are primarly displayed in order of most important to least important. Subject to change and reworking.

* Refinement of implementable features (throughout next week)

* Primary Feature Implementation:
  * Assess imports and dependencies for feature implementation.
  * Basic feature prototyping (the game is in a -- perhaps barely -- playable state). Get notes moving / player character interaction / implementation of timers / etc.
    * Notes will move derterministically based on a static beats per minute value that will be set for testing purposes (they will appear at a constant rate).
    * Notes will also appear at random locations (if the "scrolling" game style is chosen there will be 3 "lanes" in which the notes can appear. One for up, one for forward, and one for down. If the "radial" game style is chosen, notes will move at a random angle outwards from the play area.)
    * Scores will not be tabulated during this period, nor will accuracy scores.
  * Implement GUI (will be iterated on and features will be slowly introduced as time goes on, but here's a potential roadmap for it)
    * Add primary buttons for starting the game.
    * Create a simple database for storing high scores. 
    * Settings menu for changing difficulty / sound, music volume / etc. (will most likely include a text field in which the player can define the BPM, buttons for difficulty, sliders for volume, etc.)

* Refined Feature Implementation:
  * Add a dynamic scoring system in which the player will be judged based on how well the timing is to the beat of the song (i.e., 50 for poor, 100 for good, 300 for perfect) (timing values will be determined exactly during testing, such that there is a specific timing "window" in milliseconds in which the player will be able to hit the button to receive a score).
  * Add hazards / health.
    * Hazards: Add some kind of hazard in which the player must either avoid (by pressing another button), or simply not hit when the hazard comes in contact with the "hit zone" (area of effect in which the player's actions are enacted upon a object).
    * Health: Correlates directly with hazards in that the player will lose health if hit with a hazard, and lose if all health is lost.
  * Add custom charting.
    * Notes will no longer appear at / move at a constant rate. The frequency / speed of the notes will be determined by a custom charting system, which allows for dynamic note placemnt (i.e. on 4th beats, 8th beats, 16th beats, 32nd beats, etc.), as well as dynamic note frequency (BPM can change on the fly). This will allow a song to "sync up" with a song.
    * We will most likely import a song as an audio file to start, and create a chart for it. If time allows, we will create more charts.
    * Add "hold notes" in which a player will hold a button rather than simply pressing it. Visually appears as a long, stretched out note.
  * Refine the graphics (will most likely be iterated on throughout the project instead of all at the end, due to feature tweaks and new implementations)
    * Asset design (block design / radial area design, player character, UI, play areas, different screens, etc.)
    * Creation of simple animations for the player character's interactions (action of swinging a sword, breaking the blocks, idle animations, etc.) 
    * Title screen logo
    * Backgrounds and other visual elements
    * Visual eye candy (screen transitions, dynamic menu animations, refinement of asset design, dynamic particle effects, etc.)

# Anticipated Problems
* Timing issues
  * Note interaction with "hit zone" / note frequency, appearance rate
  * Tweaking number of milliseconds for timing variables
  * Button press latency / delay

* Error checking / test cases
  * Requires extensive play testing in order to gauge issues in gameplay
  * If and when unforseen problems arise, finding exact cases in which problems can be reproduced is difficult (because gameplay is dynamic, it may be hard to even determine a cause).

* TODO: Please add more to this list after touching base during meeting times.
