package jotto
import scala.util.control.Breaks._
import scala.collection.mutable.ListBuffer

import scala.io.Source
import scala.util.Random

object jotto {

  val stream = Source.fromFile("jottoWordList.txt") //reads in file list 
  val all = stream.getLines.toList //takes stream and puts as a list of strings
  val alphabet = "abcdefghijklmnopqrstuvwxyz" //create alphabet
  def hasProperForm(i: String): Boolean =
    (i.forall(character => alphabet.contains(character))) && (i.distinct == i) && (i.distinct.length() == 5)

  val wordList = all.filter(word => hasProperForm(word)) //filters our file into our wordlist by only selecting words with proper form

  def isLegalWord(word: String): Boolean = { //tests if is legal word by seeing if the word is in the wordlist, which has alread filtered by proper form
    (wordList.contains(word))
  }

  def countMatchingLetters(word1: String, word2: String): Int = {
    val intersChars = (word1 intersect word2) //first check intersection between two Strings
    intersChars.length() //then count length of intersection, intentionally will count repeated letters as incremental lengths for example tanya and anna both have 2 a's, but only one has 1 n, so length is 3
  }

  def provenLetters(secretWord: String, words: List[String]): Set[Char] = { //checks proven lets 
    var longWord = words.mkString("") //first make the list of words a single long string
    val intersStrings = (secretWord intersect longWord) //find intersections of two 
    intersStrings.toSet[Char] //return as [Char]
  }

  def disprovenLetters(secretWord: String, words: List[String]): Set[Char] = { //disproven letters
    var longWord = words.mkString("") //same methodology as proven letters 
    val intersStrings = (secretWord intersect longWord)
    val disjunct = longWord.toSet[Char] &~ intersStrings.toSet[Char] //find dusjunction by subtracting the intersection from the entire long string, hence disproven
    disjunct.toSet[Char] //return as [Char]
  }

  def randomJotto(i: List[String]): String = { 
    Random.shuffle(wordList).toList(0)  //shuffles the entire word list and then takes the first element of that list as our secret word
  }

  var gameOver = false //initialize game as not over 
  def start(): Unit = {
    val listToPrint = new ListBuffer[String]() //create list of words to print (the words we've guessed)
    val listString = new ListBuffer[String]() //create list of strings of words we've guessed, used for our proven/disproven methods above
    var gameWord = randomJotto(wordList) //create new gameword or secret word by calling randomJotto
    //println(gameWord)
    println("Welcome to Jotto! Please guess a 5 letter word, or enter 'Give up' to give up! ")
    var guessCounter = 0 //set our "guesses" to 0
    while (!gameOver) { //while loop based on while game is not over
      val input = Console.readLine().toLowerCase() //read in input from console and convert to all lower case (case insensitivy)
      if (input == "give up") { //if the user inputs give up then we just print the word and end the game
        println("You gave up! The word is: " + gameWord)
        gameOver = true
      } else if (!isLegalWord(input)) { //else if the user does not enter a legal word, we reject the word since we reject non legal guesses
        println("Your input is not ok! You must guess a legal word!") //and tell them to guess again (we do not count non-legal guesses as guesses per instructinos)
      } else { 
        if (input != gameWord) { //else if the input is not the game word then 
          var numMatchingLetters = countMatchingLetters(input, gameWord) //set a var numMatchingLetters as the number of matching letters between the guessed word and the secret word  
          listToPrint += input + " " + numMatchingLetters //print the input and the number of matching letters it had
          guessCounter = guessCounter + 1 //increment the guessCounter since we guessed 
          println("You have made " + guessCounter + " legal guess(es) so far") //print how many legal guesses they made so far
          listString += input //add the input to the list of strings
          println("The list of words you've guessed so far are: " + listString.toList) //print the list of words guessed so far, stored as listString
          println(listToPrint.mkString("\n")) 
          println("Proven letters: " + provenLetters(gameWord, listString.toList)) //print all proven letters by calling provenLetters on the secret word and the list of words guessed so far
          println("Disproven letters: " + disprovenLetters(gameWord, listString.toList)) //print all disproven letters by calling disprovenLetters on the secret word and the list of words guessed so far
          print("NonProven letters: ")
          println(alphabet.toSet[Char] &~ disprovenLetters(gameWord, listString.toList) &~ provenLetters(gameWord, listString.toList)) //print nonproven letters by subtracting all the proven and disproven letters from the alphabet of letters 
        } else if (input == gameWord) { //else if the user guesses the gameWord
          guessCounter = guessCounter + 1 //increment counter, since guessing the legal gameWord is a legal guess
          println("Yay! You win the game!") //and print congratulatory message
          gameOver = true //set gameOver to true to break out of while loop
        }

      }
    }
    println("Total legal guesses: " + guessCounter) //print out the final guessCounter
    println("Game is over!") //print game is over

  }

  def main(args: Array[String]) { //our main method simply calls the start method
    start() //runit
  }

}







