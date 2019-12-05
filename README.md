The game begins with the program reading the allowed multiples from a file called “multiples.txt”. 
The file will only contain MANY sets of mutliples. The user can choose which multiple they would like to use when playing the game. 
These are the three numbers which will be randomly provided to the user during the game. The game will operate using TWO arraylist as buffers instead of a grid. 
The maximum number of elements which can be stored in the first arraylist is 5. 
The maximum number of elements which can be stored in the second arraylist is 3. 
The game also allows the user to set the game total up to which the game will be played. 


The game sequence is as follows:
1. The game starts by registering a player to play the game.
2. The player is prompted to enter the game total upto which the game will be played. The game total must be greater than 32 and a multiple of 8.
3. The player is then prompted to select from the possible list of multiple with which to play the game. (These have been read from the file).
4. Both the Arraylist buffers (b1 and b2) are empty.
5. The game then provides a random multiple to the player which is stored in the game total.
