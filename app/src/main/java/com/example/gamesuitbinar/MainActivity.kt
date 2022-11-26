package com.example.gamesuitbinar

import android.os.Bundle
import android.util.Log
import androidx.annotation.ColorRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import com.example.gamesuitbinar.databinding.ActivityMainBinding
import com.example.gamesuitbinar.model.MatchResult
import com.example.gamesuitbinar.model.Player

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var player: Player
    private lateinit var com: Player

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setUpGame()
        setUpAction()
    }

    private fun setUpGame() {
        player = Player()
        com = Player()
    }

    private fun setUpAction() {
        binding.apply {
            btnRockPlayer.setOnClickListener {
                onPlayerSelect(ROCK)
            }
            btnPaperPlayer.setOnClickListener {
                onPlayerSelect(PAPER)
            }
            btnScissorPlayer.setOnClickListener {
                onPlayerSelect(SCISSOR)
            }
            btnRefresh.setOnClickListener {
                if (player.choice != null) {
                    reset()
                }
            }
        }
    }

    private fun onPlayerSelect(choice: String) {
        // Set player selection
        player.choice = choice
        playerSelectedButton(choice)

        // Get Random choice from com & set selected button based on choice
        com.choice = listChoice.random()
        comSelectedButton(com.choice!!)

        // Show log player and com choices
        Log.d("PLAYER", player.choice!!)
        Log.d("COM", com.choice!!)

        getWinner()
    }

    private fun getWinner() {
        when (player.choice) {
            ROCK -> {
                when (com.choice) {
                    ROCK -> setMatchResult(MatchResult.DRAW)
                    PAPER -> setMatchResult(MatchResult.COM_WINS)
                    SCISSOR -> setMatchResult(MatchResult.PLAYER_WINS)
                }
            }
            PAPER -> {
                when (com.choice) {
                    ROCK -> setMatchResult(MatchResult.PLAYER_WINS)
                    PAPER -> setMatchResult(MatchResult.DRAW)
                    SCISSOR -> setMatchResult(MatchResult.COM_WINS)
                }
            }
            SCISSOR -> {
                when (com.choice) {
                    ROCK -> setMatchResult(MatchResult.COM_WINS)
                    PAPER -> setMatchResult(MatchResult.PLAYER_WINS)
                    SCISSOR -> setMatchResult(MatchResult.DRAW)
                }
            }
        }
        com.choice
    }

    private fun setMatchResult(result: MatchResult) {
        // Change Announcements words
        when (result) {
            MatchResult.PLAYER_WINS -> {
                setWinnerAnnouncement("Player wins!")
            }
            MatchResult.COM_WINS -> {
                setWinnerAnnouncement("Com wins!")
            }
            MatchResult.DRAW -> {
                setWinnerAnnouncement("Draw!", R.color.blue)
            }
            else -> {}
        }


    }

    private fun setWinnerAnnouncement(message: String, @ColorRes resId:Int = R.color.green) {
        binding.tvAnnouncement.apply {
            text = message
            textSize = 18.0f
            setTextColor(AppCompatResources.getColorStateList(this@MainActivity, R.color.white))
            backgroundTintList =
                AppCompatResources.getColorStateList(this@MainActivity, resId)
        }
    }

    private fun reset() {
        resetButton()
        resetChoice()
        resetAnnouncment()
    }

    private fun resetAnnouncment() {
        binding.tvAnnouncement.apply {
            text = "VS"
            textSize = 48.0f
            setTextColor(AppCompatResources.getColorStateList(this@MainActivity, R.color.red))
            backgroundTintList =
                AppCompatResources.getColorStateList(this@MainActivity, R.color.yellow)
        }
    }

    private fun resetChoice() {
        player.choice = null
        com.choice = null
    }

    private fun resetButton() {
        binding.apply {
            // Reset player choice buttons
            btnRockPlayer.apply {
                backgroundTintList =
                    AppCompatResources.getColorStateList(this@MainActivity, R.color.white)
                isClickable = true
            }
            btnPaperPlayer.apply {
                backgroundTintList =
                    AppCompatResources.getColorStateList(this@MainActivity, R.color.white)
                isClickable = true
            }
            btnScissorPlayer.apply {
                backgroundTintList =
                    AppCompatResources.getColorStateList(this@MainActivity, R.color.white)
                isClickable = true
            }

            // Reset com choice buttons
            btnRockCom.apply {
                backgroundTintList =
                    AppCompatResources.getColorStateList(this@MainActivity, R.color.white)
            }
            btnPaperCom.apply {
                backgroundTintList =
                    AppCompatResources.getColorStateList(this@MainActivity, R.color.white)
            }
            btnScissorCom.apply {
                backgroundTintList =
                    AppCompatResources.getColorStateList(this@MainActivity, R.color.white)
            }

        }
    }

    private fun playerSelectedButton(choice: String) {
        binding.apply {
            when (choice) {
                ROCK -> {
                    btnRockPlayer.backgroundTintList =
                        AppCompatResources.getColorStateList(
                            this@MainActivity,
                            androidx.appcompat.R.color.material_grey_300
                        )
                    btnPaperPlayer.backgroundTintList =
                        AppCompatResources.getColorStateList(this@MainActivity, R.color.white)
                    btnScissorPlayer.backgroundTintList =
                        AppCompatResources.getColorStateList(this@MainActivity, R.color.white)
                }

                PAPER -> {
                    btnRockPlayer.backgroundTintList =
                        AppCompatResources.getColorStateList(this@MainActivity, R.color.white)
                    btnPaperPlayer.backgroundTintList =
                        AppCompatResources.getColorStateList(
                            this@MainActivity,
                            androidx.appcompat.R.color.material_grey_300
                        )
                    btnScissorPlayer.backgroundTintList =
                        AppCompatResources.getColorStateList(this@MainActivity, R.color.white)
                }

                SCISSOR -> {
                    btnRockPlayer.backgroundTintList =
                        AppCompatResources.getColorStateList(this@MainActivity, R.color.white)
                    btnPaperPlayer.backgroundTintList =
                        AppCompatResources.getColorStateList(this@MainActivity, R.color.white)
                    btnScissorPlayer.backgroundTintList =
                        AppCompatResources.getColorStateList(
                            this@MainActivity,
                            androidx.appcompat.R.color.material_grey_300
                        )
                }
            }
            btnRockPlayer.isClickable = false
            btnPaperPlayer.isClickable = false
            btnScissorPlayer.isClickable = false
        }
    }

    private fun comSelectedButton(choice: String) {
        binding.apply {
            when (choice) {
                ROCK -> {
                    btnRockCom.backgroundTintList =
                        AppCompatResources.getColorStateList(
                            this@MainActivity, androidx.appcompat.R.color.material_grey_300
                        )
                    btnPaperCom.backgroundTintList =
                        AppCompatResources.getColorStateList(
                            this@MainActivity, R.color.white
                        )
                    btnScissorCom.backgroundTintList =
                        AppCompatResources.getColorStateList(
                            this@MainActivity, R.color.white
                        )
                }

                PAPER -> {
                    btnRockCom.backgroundTintList =
                        AppCompatResources.getColorStateList(
                            this@MainActivity, R.color.white
                        )
                    btnPaperCom.backgroundTintList =
                        AppCompatResources.getColorStateList(
                            this@MainActivity, androidx.appcompat.R.color.material_grey_300
                        )
                    btnScissorCom.backgroundTintList =
                        AppCompatResources.getColorStateList(
                            this@MainActivity, R.color.white
                        )
                }

                SCISSOR -> {
                    btnRockCom.backgroundTintList =
                        AppCompatResources.getColorStateList(
                            this@MainActivity, R.color.white
                        )
                    btnPaperCom.backgroundTintList =
                        AppCompatResources.getColorStateList(
                            this@MainActivity, R.color.white
                        )
                    btnScissorCom.backgroundTintList =
                        AppCompatResources.getColorStateList(
                            this@MainActivity, androidx.appcompat.R.color.material_grey_300
                        )
                }
            }
        }
    }

    companion object {
        const val SCISSOR = "SCISSOR"
        const val ROCK = "ROCK"
        const val PAPER = "PAPER"

        val listChoice = listOf(ROCK, SCISSOR, PAPER)
    }
}