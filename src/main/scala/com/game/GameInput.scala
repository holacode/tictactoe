package com.game

import scala.io.{StdIn => Input}

/**
  * Created by swapnil on 29/12/16.
  * Class to take input from users
  */
object GameInput {

  /**
    * This method take input for size of board and retry for input if invalid input is given
    * @return give back size of board
    */
  def inputBoardSize(): Int = {
    var boardSize = 3
    try {
      println(Msg.welcome)
      print(Msg.cursor)
      boardSize = Input.readInt()

      if( boardSize >  Board.maxSize || boardSize < Board.minSize){
        println(Msg.validBoardSize)
        boardSize = inputBoardSize()
      }

    }catch {
      case _ =>  {
        println(Msg.validNumber)
        boardSize = inputBoardSize()
      }

    }
    boardSize
  }

  /**
    * This method take input of position for X/0 from user and retry for input if invalid input postion is given is given
    * @param board game board as input
    * @param player player who is update the postion
    */

  def inputMarker(player:Player,board:Board): Unit ={


    println(Msg.inputMsg(player))
    print(Msg.cursor)

    try{

      val position  = Input.readInt()
      if(board.isValidPosition(position)) {
        board.updateBoard(position, player.sign)
        board.show()
      } else{

        println(Msg.numberFormatException)
        inputMarker(player,board)
      }

    }catch {
      case(ne:NumberFormatException) => {
        println(Msg.numberFormatException)
        inputMarker(player,board)
      }
      case _  => {
        println(Msg.validNumber)
        inputMarker(player,board)

      }
    }

  }

}
