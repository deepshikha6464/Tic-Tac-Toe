package com.deeps.tictactoe

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuItem

import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.core.view.isVisible
import nl.dionsegijn.konfetti.KonfettiView
import nl.dionsegijn.konfetti.models.Shape
import nl.dionsegijn.konfetti.models.Size
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    lateinit var winnerTextView: TextView
    lateinit var p1count: TextView
    lateinit var p2count: TextView
    lateinit var btn1 : Button
    lateinit var btn2 : Button
    lateinit var btn3 : Button
    lateinit var btn4 : Button
    lateinit var btn5 : Button
    lateinit var btn6 : Button
    lateinit var btn7 : Button
    lateinit var btn8 : Button
    lateinit var btn9 : Button
    lateinit var viewKonfetti : KonfettiView
    lateinit var comp : MenuItem
    lateinit var man : MenuItem

    var winner = -1
    var p1 = 0
    var p2 = 0
    var gameEnd = false

    var player1 = ArrayList<Int>()
    var player2 = ArrayList<Int>()
    var selectedButton = ArrayList<Int>()
    var activePlayer = 1
    var count = 0
    var playerMode ="multiplayer"     // multiplayer / computer


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        winnerTextView = findViewById(R.id.winningStatus) as TextView
        p1count = findViewById(R.id.playerOneCount) as TextView
        p2count = findViewById(R.id.playerTwoCount) as TextView
        viewKonfetti = findViewById(R.id.viewKonfetti) as KonfettiView

        btn1 =  findViewById(R.id.button1) as Button
        btn2 =  findViewById(R.id.button2) as Button
        btn3 =  findViewById(R.id.button3) as Button
        btn4 =  findViewById(R.id.button4) as Button
        btn5 =  findViewById(R.id.button5) as Button
        btn6 =  findViewById(R.id.button6) as Button
        btn7 =  findViewById(R.id.button7) as Button
        btn8 =  findViewById(R.id.button8) as Button
        btn9 =  findViewById(R.id.button9) as Button
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu, menu)
        comp  = menu.findItem(R.id.comp)
        man  = menu.findItem(R.id.manual)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here.
        val id = item.getItemId()
        if (id == R.id.comp) {
            Toast.makeText(this, "Play with Computer", Toast.LENGTH_LONG).show()
            man.isVisible=true
            comp.isVisible=false
            playerMode = "computer"
            return true
        }
        if (id == R.id.manual) {
            Toast.makeText(this, "Multiplayer Activated", Toast.LENGTH_LONG).show()
            man.isVisible=false
            comp.isVisible=true
            playerMode = "multiplayer"

            return true
        }




        return super.onOptionsItemSelected(item)

    }

    fun BtnClicked(view: android.view.View) {
        val buttonSelected = view as Button
        var cellId = 0
        when(buttonSelected.id){
            R.id.button1-> cellId = 1
            R.id.button2-> cellId = 2
            R.id.button3-> cellId = 3
            R.id.button4-> cellId = 4
            R.id.button5-> cellId = 5
            R.id.button6-> cellId = 6
            R.id.button7-> cellId = 7
            R.id.button8-> cellId = 8
            R.id.button9-> cellId = 9
        }
        playGame(cellId,buttonSelected)
    }

    private fun playGame(cellId: Int, buttonSelected: Button) {
//        player 1 = X
//        player 2 = 0
//        autoplay = 0
        count ++
            if(activePlayer == 1){
                buttonSelected.setText("X")
                buttonSelected.setBackgroundColor(Color.GREEN)
                player1.add(cellId)
                selectedButton.add(cellId)
                activePlayer = 2

                Handler(getMainLooper()).postDelayed({
                    setPlayer()
                }, 1000)
            }else{
                buttonSelected.setText("0")
                buttonSelected.setBackgroundColor(Color.MAGENTA)
                player2.add(cellId)
                selectedButton.add(cellId)
                activePlayer = 1
            }
        buttonSelected.isEnabled = false
        if(gameEnd == false)
            checkWinner()

    }

    private fun checkWinner() {


            if (player1.contains(1) && player1.contains(2) && player1.contains(3)) {
                winner = 1
            }
        if (player2.contains(1) && player2.contains(2) && player2.contains(3)) {
            winner = 2
        }


        // row 2
        if (player1.contains(4) && player1.contains(5) && player1.contains(6)) {
            winner = 1
        }
        if (player2.contains(4) && player2.contains(5) && player2.contains(6)) {
            winner = 2
        }


        // row 3
        if (player1.contains(7) && player1.contains(8) && player1.contains(9)) {
            winner = 1
        }
        if (player2.contains(7) && player2.contains(8) && player2.contains(9)) {
            winner = 2
        }


        // col 1
        if (player1.contains(1) && player1.contains(4) && player1.contains(7)) {
            winner = 1
        }
        if (player2.contains(1) && player2.contains(4) && player2.contains(7)) {
            winner = 2
        }


        // col 2
        if (player1.contains(2) && player1.contains(5) && player1.contains(8)) {
            winner = 1
        }
        if (player2.contains(2) && player2.contains(5) && player2.contains(8)) {
            winner = 2
        }


        // col 3
        if (player1.contains(3) && player1.contains(6) && player1.contains(9)) {
            winner = 1
        }
        if (player2.contains(3) && player2.contains(6) && player2.contains(9)) {
            winner = 2
        }

        //diagonal
        if (player1.contains(1) && player1.contains(5) && player1.contains(9)) {
            winner = 1
        }
        if (player2.contains(1) && player2.contains(5) && player2.contains(9)) {
            winner = 2
        }

        if (player1.contains(3) && player1.contains(5) && player1.contains(7)) {
            winner = 1
        }
        if (player2.contains(3) && player2.contains(5) && player2.contains(7)) {
            winner = 2
        }


        if( winner != -1){
            if (winner==1){
                winnerTextView.setText("Player 1 won!!")
                p1=p1+1
                p1count.setText(p1.toString())
                gameEnd = true
                confettiCall()
                Handler(getMainLooper()).postDelayed({
                    reset()
                }, 1000)
            }else{
                winnerTextView.setText("Player 2 won!!")
                p2=p2+1
                p2count.setText(p2.toString())
                gameEnd = true
                confettiCall()
                Handler(getMainLooper()).postDelayed({
                    reset()
                }, 1000)

            }

        }
        else{
            if(count>=9) {
                winnerTextView.setText("Match Draw")
                gameEnd = true
                winner = -1
            }
        }
    }

    fun reset() {

        winnerTextView.setText("")
          btn1.setBackgroundColor(Color.parseColor("#827370"))
          btn2.setBackgroundColor(Color.parseColor("#827370"))
          btn3.setBackgroundColor(Color.parseColor("#827370"))
          btn4.setBackgroundColor(Color.parseColor("#827370"))
          btn5.setBackgroundColor(Color.parseColor("#827370"))
          btn6.setBackgroundColor(Color.parseColor("#827370"))
          btn7.setBackgroundColor(Color.parseColor("#827370"))
          btn8.setBackgroundColor(Color.parseColor("#827370"))
          btn9.setBackgroundColor(Color.parseColor("#827370"))

        btn1.isEnabled=true
        btn2.isEnabled=true
        btn3.isEnabled=true
        btn4.isEnabled=true
        btn5.isEnabled=true
        btn6.isEnabled=true
        btn7.isEnabled=true
        btn8.isEnabled=true
        btn9.isEnabled=true

        btn1.setText("")
        btn2.setText("")
        btn3.setText("")
        btn4.setText("")
        btn5.setText("")
        btn6.setText("")
        btn7.setText("")
        btn8.setText("")
        btn9.setText("")

        player1.clear()
        player2.clear()

        count = 0
//        p1 = 0
//        p2 = 0
        winner = -1
        gameEnd = false
        viewKonfetti.stopGracefully()

    }
    private fun AutoPlay() {
        val emptyCells=ArrayList<Int>()
        for (cellID in 1..9){
            if(!(player1.contains(cellID) || player2.contains(cellID))){
                emptyCells.add(cellID)
            }
        }

        val r = Random()
        if(!emptyCells.isEmpty()) {
            val randIndex = r.nextInt(emptyCells.size-0)+0
            val cellID = emptyCells[randIndex]
            var buSelected:Button
            when(cellID){
                1->buSelected=btn1
                2-> buSelected=btn2
                3-> buSelected=btn3
                4-> buSelected=btn4
                5-> buSelected=btn5
                6-> buSelected=btn6
                7-> buSelected=btn7
                8-> buSelected=btn8
                9-> buSelected=btn9
                else -> buSelected = btn1

        }

            playGame(cellID,buSelected)


        }


    }

    fun confettiCall(){
    viewKonfetti.build()
        .addColors(Color.YELLOW, Color.GREEN, Color.MAGENTA)
        .setDirection(0.0, 359.0)
        .setSpeed(1f, 5f)
        .setFadeOutEnabled(true)
        .setTimeToLive(1000L)
        .addShapes(Shape.Square, Shape.Circle)
        .addSizes(Size(12))
        .setPosition(-50f, viewKonfetti.width + 50f, -50f, -50f)
        .streamFor(300, 5000L)
}

    fun setPlayer(){
        if(playerMode.equals("multiplayer")){

        }else{
                  AutoPlay()
        }

    }

    fun reset(view: android.view.View) {

        winnerTextView.setText("")
        btn1.setBackgroundColor(Color.parseColor("#827370"))
        btn2.setBackgroundColor(Color.parseColor("#827370"))
        btn3.setBackgroundColor(Color.parseColor("#827370"))
        btn4.setBackgroundColor(Color.parseColor("#827370"))
        btn5.setBackgroundColor(Color.parseColor("#827370"))
        btn6.setBackgroundColor(Color.parseColor("#827370"))
        btn7.setBackgroundColor(Color.parseColor("#827370"))
        btn8.setBackgroundColor(Color.parseColor("#827370"))
        btn9.setBackgroundColor(Color.parseColor("#827370"))

        btn1.isEnabled=true
        btn2.isEnabled=true
        btn3.isEnabled=true
        btn4.isEnabled=true
        btn5.isEnabled=true
        btn6.isEnabled=true
        btn7.isEnabled=true
        btn8.isEnabled=true
        btn9.isEnabled=true

        btn1.setText("")
        btn2.setText("")
        btn3.setText("")
        btn4.setText("")
        btn5.setText("")
        btn6.setText("")
        btn7.setText("")
        btn8.setText("")
        btn9.setText("")

        player1.clear()
        player2.clear()

        count = 0
//        p1 = 0
//        p2 = 0
        winner = -1
        gameEnd = false
        viewKonfetti.stopGracefully()

    }

}