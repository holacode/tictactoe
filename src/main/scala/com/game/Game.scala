package com.game
import scala.io.{StdIn => Input}
/**
  * Created by swapnil on 29/12/16.
  * Class for Execution of Game.
  * This class has Board and Player object
  */
object Game extends App{


  val boardSize = GameInput.inputBoardSize()

  val board = new Board(boardSize)
  board.show()

  // init player one
  println(Msg.playerWelcome(Msg.player1))
  val playerOneName  = Input.readLine()
  val playerOne  = Player(playerOneName,Msg.X)

  // init player two
  println(Msg.playerWelcome(Msg.player2))
  val playerTwoName  = Input.readLine()
  val playerTwo  = Player(playerTwoName,Msg.O)

  // intial condition for start of game
  var isGameCompleted = false

  /**
    *This method checks for winner after every mode
    *
    * @param board game board as input
    * @param player1 player one object
    * @param player2 player two object
    */
  def checkWinner(board: Board,player1: Player,player2:Player) ={

    val result = board.checkForWin(player1,player2)
    if(result.isEmpty && board.isBoardCompleted()){
      isGameCompleted = true
      println(Msg.draw)
      System.exit(0)
    }else if(result.nonEmpty && !board.isBoardCompleted()){
    val winner = result.get
    isGameCompleted = true
      println(Msg.congrats(winner))
      System.exit(0)
    }
  }

  // continuation of game until win/draw
  while(!isGameCompleted){
    GameInput.inputMarker(playerOne,board)
    checkWinner(board,playerOne,playerTwo)
    GameInput.inputMarker(playerTwo,board)
    checkWinner(board,playerOne,playerTwo)
  }

}
