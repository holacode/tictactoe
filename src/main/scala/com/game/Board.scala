package com.game

import scala.collection.mutable

/**
  * Created by swapnil on 29/12/16.
  * This class provides the board for tic tac toe.
  * its performs check for board and completion of game
  *
  */
class Board(dimension:Int) {


  private val board = Array.ofDim[String](dimension,dimension)
  val indexMap = new mutable.HashMap[Int,Tuple2[Int,Int]]()
  val usedPositions = new mutable.HashSet[Int]()

  // counter to provide position to each cell
  var index = 0

  // max game need 3 continous sign to occur
  val maxSingInContinueRequired = 3

  //allocate postion to each cell sequentially
  board.indices.foreach{ i =>

    board(i).indices.foreach{ j =>
      index = index + 1

      // handle space for single digits
      board(i)(j) = if(index <=9  ) " "+index.toString else index.toString
      val indexTuple = (i,j)
      indexMap.put(index,indexTuple)
    }

  }

  /**
    * This class prints the tic tac toe board
    *
    */
  def show() {
    board.indices.foreach{ i =>
      println ( board(i).mkString(" | "))

      1 to dimension*5 foreach{ i => print("-")}
      println()
    }

  }

  /**
    * This method validate if user enter postion with in
    * the range
    * @param number to be validated
    * @return Boolean
    */
  def isValidPosition(number: Int): Boolean = {

    if(usedPositions.contains(number) || number > index)
      false
    else
      true

  }

  /**
    * This method update the cell with given sign
    * @param number position to be update as per counter number
    * @param marker sign to be put in the cell
    */
  def updateBoard(number:Int,marker:String): Unit = {

    val indexes  = indexMap.get(number)
    if(indexes.nonEmpty) {

      usedPositions.add(number)
      val i = indexes.get._1
      val j = indexes.get._2
      board(i)(j) = marker
    }

  }

  /**
    * Check if all the cells are filled and end is reached
    * @return Boolean if board is completed or not
    */

  def isBoardCompleted(): Boolean = {

    if(usedPositions.size == indexMap.size)
      true
    else
      false
  }

  /**
    * This method check for winner of game
    *
    * @param p1 player one object
    * @param p2 player two object
    * @return return the Option of winning player , Option None if its draw.
    */

  def checkForWin(p1:Player,p2:Player): Option[Player]  ={

    val rowResult = checkByRowCol(p1,p2,"row")

    val colResult = checkByRowCol(p1,p2,"col")

    val leftDiagonal = checkDiagonal(p1,p2,"left_diagonal")

    val rightDiagonal = checkDiagonal(p1,p2,"right_diagonal")

    if(rowResult.nonEmpty) rowResult
    else if(colResult.nonEmpty) colResult
    else if(leftDiagonal.nonEmpty) leftDiagonal
    else if(rightDiagonal.nonEmpty) rightDiagonal
    else None

  }

  /**
    * This method check for diagonal of board for win condition
    * @param p1 player one object
    * @param p2 player one object
    * @param checkType condition to check left or right diagonal
    * @return give the option of player who won the diagonals of game
    */
  def checkDiagonal(p1:Player,p2:Player,checkType:String): Option[Player] = {


    val boardSize = dimension - 1
    // 0 to N - 1
    val diagonalIndexes = checkType match {
      case "left_diagonal" => 0 to boardSize map (x => (x, x))
      case "right_diagonal" => 0 to boardSize map { x => if (x + boardSize > boardSize) ((boardSize - x), x) else (x + boardSize, x) }
    }
    var isP1Winner, isP2Winner = false
    var p1Sign, p2Sign = 0

    diagonalIndexes.foreach { pair =>
      val i = pair._1
      val j = pair._2
      val sign = board(i)(j)

      if (sign.equals(p1.sign)) {
        p1Sign = p1Sign + 1
        p2Sign = 0
      }
      else if (sign.equals(p2.sign)) {
        p2Sign = p2Sign + 1
        p1Sign = 0
      }else{
        p1Sign = 0
        p2Sign = 0
      }

    }
    if(p1Sign >= maxSingInContinueRequired)
      isP1Winner = true

    if(p2Sign >= maxSingInContinueRequired)
      isP2Winner = true


    if(isP1Winner)
      Some(p1)
    else if(isP2Winner)
      Some(p2)
    else
      None



  }

  /**
    * This method check for row/col of board for win condition
    * @param p1 player one object
    * @param p2 player one object
    * @param checkType condition to check row or col of board
    * @return give the option of player who won the row/col of game
    */

  def checkByRowCol(p1:Player,p2:Player,checkType:String): Option[Player] ={

    var isP1Winner , isP2Winner = false
    val boardSize = dimension - 1

    0 to boardSize foreach { i =>

      var p1Sign,p2Sign = 0

      0 to boardSize foreach { j =>

        val sign = checkType match {
          case  "row" => board(i)(j)
          case  "col" => board(j)(i)
        }

        if(sign.equals(p1.sign)) {
          p1Sign = p1Sign + 1
          p2Sign = 0
        }
        else if(sign.equals(p2.sign)) {
          p2Sign = p2Sign + 1
          p1Sign = 0

        }else{
          p1Sign = 0
          p2Sign = 0
        }

        if(p1Sign >= maxSingInContinueRequired)
          isP1Winner = true

        if(p2Sign >= maxSingInContinueRequired)
          isP2Winner = true

      }


    }

    if(isP1Winner)
      Some(p1)
    else if(isP2Winner)
      Some(p2)
    else
      None
  }

}

/**
  * Companion object for Board class
  */

object Board {

  val maxSize = 100
  val minSize = 3

}