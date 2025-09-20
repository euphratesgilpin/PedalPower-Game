package pedalPower;

public class Navigator {



}

/*
INFORMATION - top section - class info - SCROLL - TO DO LIST

Hello I am here to act as your personal readme
Please use this as an index for methods in gameboard (I'm sorry its a mess in there)

gameBoard class

22 constructor for gameboard
47 initialiseBoard method (sets up the board and its design)
281 getMainInstance (returns main game board reference - the main board has been set up a special way, to allow a mini 
		      board to pop up for choosing cycle lanes to buy)
285 setPlayerPosition (updates player position visually and sends the square values to backend to be stored - note that 
			  front end values are essentially wiped - all values are stored in backend ie squares and player)
319 getTile (im gonna be real idk if this does anything i suspect chatgpt gave me it so its here)
323 startPos (sets start positions for all players.)
345 setVisualStart ( places the numbered images onto the grid and assigns the square values)
363 initPlayers (initialises the player objects depending on the number clicked and the amount playing)
			  i think this one is currently broken im gonna be real
393 move (updates player position, moves image, runs claim square method)
406 claimSquare (if the square is valid to be claimed, maps the values down to ONE value per section to prevent testing
			  for 3 at all times. Doesn't output the "Claim?" question if the square is a docking station or if the
			  square is already part of a claimed section.)
609 setColours (updates the colour when a player claims a section. needs updated to change colour depending on player
			  im thinking just do a wee if and make a colour variable and its the hex code, then stick it into the 
			  hex code slot of each line)
763 takeTurn (outputs "turn" window. action listeners call respective methods and nextTurn if it is a final call)
861 nextTurn (sets to next player and calls nextTurn)
879 buildCyc (SECTION NOT FINISHED - creates a small board. calls method to convert FULLY OWNED sections to buttons.)
913 convertClaimedSquaresToButtons ( is supposed to reverse map the section header values to all their sections and 
			  convert all the respective JLabels to buttons. Once this is done the buttons need the actionListeners 
			  updated to call a method/ code to purchase the cycle lane. Resources need to be edited.)
1318 buildDS (NOT FINISHED - method to build a docking station. need to test whether both sections around it have been
			  fully built first. Deplete resources.)
1324 main ( runs instructions window. starts the game :3 )


startPage class

instructions (runs instructions window)
menu (select player options)


dice class

rollDice (rolls dice - theoretically, of course)
showRes (outputs the result in an annoying little box :3 note frame is invisible but still mandatory to exist)


player class

player constructor ( constructs player object. duhhhh :p )
general setters
setStartSquare (assigns starting squares depending on previous count of how many player objects currently exist)
setImageIcon (assigned using the same format as start squares)
general getters
toString


square class

lots of arrays. im sorry about that. im not sure if we will be using all of them but there is a full and an empty 
instance of each. however, if you need to check any values, they are all manually mapped in these arrays. PLEASE
DO NOT change the data, unless the game is finished and the data was not used (it took a long time) thanks :)

setpsq (adds section squares to array when a player claims a section. note may need a second array (or can we do the 
		opposite? kick the values out once they've been bought and function this array like a stack in that sense? minus
		the whole LIFO thing))
getters
assignStartSquares (you can predict what this one does. Jk. it assigns the start squares)
updateActiveSquares (this is actually where the roll dice method is called. squares are mapped to index value and then the
		index is incremented rather than the value (since the pathing is non linear))


cardLibrary class

currently just where the card objects are created and stored. no images have been added to the system or assigned yet,
and the code to output these has not yet been written


card class

getters and setters. standard, simple object class with no complicated assignments (link held as String currently)


Type enum

enum assignments for card types







TO DO LIST
~~~~~~~~~~

DONE - value added - info panel?
- DONE - info panel - pricing, available rewards, available penalties
- DONE - donate option when landing on someone elses square.

start of game
- instructions need updated
- DONE - need to add input name for players
  
DONE - passing start position
- DONE - when passing your start position, the pop-up will occur and functions correctly
- DONE - resources need updated and image needs added
  
DONE - gameBoard visuals
- DONE - player info visuals are there but dont update
- where the "start game" button appears needs updated to contain continually updating stats of all players. eg Player 1:
  resources, materials, [number of built docking stations?]
- DONE - when cycle lane is built
- DONE - when ds is claimed
- DONE - when action/event occurs
- DONE - when passing "go"

DONE - claiming a section
- DONE - there is currently no cost associated with claiming, it is just free. a resource depletion should be assigned 
  OR a dice roll above 4

DONE - action/event cards
- DONE - do these occur while building? while simply moving? do they only occur if you land precisely on the square or if you
  claim the section they landed in?
- DONE - pop up when own a section and land on event card within that section.
- DONE - a visual output method will need implemented to create a new window that pops up with the information
- DONE - details do not yet update player resources

DONE - build cycle lane
- DONE - reassigning the JLabels to buttons WORKS - but something is off and they don't output always in the correct position
- DONE - note confirmation doesn't work so only outputs if you can't afford the cycle lane - buttons from this have no 
  functionality set to them - need to be made to pull up a confirmation pop-up (or not) and then take away resources 
  based on you building a cycle lane there. Colour values need updated again for the respective square. Thought process
  was "square button in grid -> are you sure? -> depletes resources".
- DONE - need to track bought squares
- DONE - resources now deplete
- DONE - window closes after purchasing a square


DONE - build docking station
- DONE - buttons are available for the user to click if and only if all squares in that section have already been
  purchased
- DONE - actionlisteners need updated to actually receive award from the docking station
- DONE - price needs added/ resources edited - also need images for updating the board - and update board visually
- DONE - fix turn changing after claiming a DS reward
- DONE - remove dockingsquare replaces num with 500+ so need to test end state by seeing if any values exist in dockingSQUARES
  that are not this - (some kind of count to determine the game's end state based on all DS being lit up )

DONE - possible errors
- DONE - negative money/ material - don't allow this to happen. validates when a cycle lane or DS is purchased
- DONE - duplicate owned sections - i believe this should be fine. i should have accounted for this already in the code

DONE - end of game
- DONE - epilogue with scrolling text or pauses
- DONE - triggered once docking station count is 16
- DONE - trigger when negative money from event card
*/