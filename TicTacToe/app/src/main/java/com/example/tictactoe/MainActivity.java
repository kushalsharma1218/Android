package com.example.tictactoe;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    static int chance = 1;
    static int[] gamestate = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    static int[][] arr = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 4, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {2, 4, 6}};
    TextView winner_text;
    Button play_again;
    GridLayout gridLayout;
    LinearLayout linearLayout;
    boolean gameIsActive = true;
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void setImage(View view) {
        ImageView imageView = (ImageView) view;
        int tag = Integer.parseInt((String) imageView.getTag());
        if (gamestate[tag] == 2 && gameIsActive) {

            gamestate[tag] = chance;

            imageView.setTranslationY(-1000f);
            if (chance == 0) {
                imageView.setImageResource(R.drawable.red);
                chance = 1;
            } else {
                imageView.setImageResource(R.drawable.yellow);
                chance = 0;
            }

            imageView.animate().translationYBy(1000f).rotation(360).setDuration(300);
        }
        for (int[] a : arr) {
            if (gamestate[a[0]] == gamestate[a[1]]) {
                if (gamestate[a[1]] == gamestate[a[2]] && gamestate[a[0]] != 2) {
                    gridLayout.setAlpha(0.5f);
                    gameIsActive = false;
                    linearLayout.setVisibility(View.VISIBLE);
                    winner_text.setText("Player win :"+gamestate[a[0]]);
                }
            }
            else
                {
                    boolean b =true;
                    for(int i=0;i<gamestate.length;i++)
                    {
                        if(gamestate[i]==2)b = false;
                    }
                    if(b)
                    {
                        gridLayout.setAlpha(0.5f);
                        gameIsActive = false;
                        linearLayout.setVisibility(View.VISIBLE);
                        winner_text.setText("Its a tie");
                        winner_text.setGravity(View.TEXT_ALIGNMENT_CENTER);
                        winner_text.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    }
                }
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        winner_text = findViewById(R.id.text_playagain);
        gridLayout = findViewById(R.id.board_game);
        linearLayout = findViewById(R.id.winner_layout);
        play_again = findViewById(R.id.play_again);
        play_again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gridLayout.setAlpha(1f);
                gameIsActive = true;
                Arrays.fill(gamestate, 2);
                for(int i =0;i<gridLayout.getChildCount();i++)
                {
                    ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
                }
                linearLayout.setVisibility(View.INVISIBLE);
            }
        });
    }
}
