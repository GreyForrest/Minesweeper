Minesweeper

# Game Summary/Explanation

Minesweeper is a game, where you have a playing field full of covered squares. Each square has either a bomb or a number that says how many bombs are hidden in the eight directly adjacent fields. You can open a field to see what’s beneath it. If it is a bomb you lost. The goal of the game is to mark all bombs with flags. If you open a field with the number zero (aka its empty) every adjacent field is also uncovered. If one of those is also empty, the process repeats. If you marked all bombs or the same number of fields as there are bombs, all the other fields are uncovered, and you can see whether you’ve won.

# My Game

My Minesweeper is played via console. At the beginning you enter your preferred field size (for example 6x7). The number of bombs is calculated automatically, and you will be informed about how many bombs there are. You will then be asked to enter coordinates (like 2,3/6,5) to uncover some squares. Next, the program asks if you would like to mark bombs, again with coordinates. Uncovering squares and marking bombs take turns until you either marked as many squares as there are bombs or only have the same number of unopened fields left (marked or not marked) as the number of bombs there are.

It's definitely not the best answer for a Minesweeper Java application, and I already had to program a second one for an exam which turned out much better, but it is a nice way to waste some time.

# How to “install” my Minesweeper

As it is a console application you will have to download the code (repository code Download Zip-File), unzip it and open it in an IDE suited for Java, I programmed the game in IntelliJ by Jetbrains, which I find is a great IDE. You might have to set up an JDK next, I used JDK 17. Then you will have to build the project and start the “Starter” class. The console should automatically pop up and you can play the game.
