package com.game

/**
  * Created by swapnil on 29/12/16.
  * Object to keep all the message used in game
  */
object Msg {

  // update with space for proper alignment
  val X = " X"
  val O = " 0"

  val numberFormatException = "Entered invalid number, please enter valid number "

  val inputPosition = "Please enter position which"
  val validInput = "Please enter valid position this already marked"
  val welcome = "Welcome to Game Please Enter size of Board :: "
  val cursor = ">> "
  val validNumber = "Please Enter Valid board size as Number :: "
  val player1 = "Player 1"
  val player2 = "Player 2"
  val validBoardSize = s"Valid board size should be less than ${Board.maxSize} and greater than equal to ${Board.minSize} :: "

  def playerWelcome(name:String) = s" Enter Name for ${name} :: "

  def inputMsg(player:Player) = s" ${player.name} choose a box to place an '${player.sign}' into: "

  def congrats(player: Player) = s"Congratulations ${player.name} ! You have won."

  def draw = s"Its a draw."

}
