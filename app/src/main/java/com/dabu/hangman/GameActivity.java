package com.dabu.hangman;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;


public class GameActivity extends ActionBarActivity {

    String mword = "work";
    int faidCounter = 0;
    int mGuessedLetter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
    }

    /**
     * Retrieving the letter  introduced on the EditText
     *
     * @param v (button clicked)
     */
    public void introduceLetter(View v) {
        EditText myEditText = (EditText) findViewById(R.id.editTextLetter);

        String letter = myEditText.getText().toString();

        myEditText.setText("");

        Log.d("myletter", "the letter is " + letter);

        checkLetter(letter);
    }

    /**
     * Checking if the leter introduced matchs any letter in the word
     *
     * @param introducedLetter
     */
    public  void checkLetter(String introducedLetter){

        char charIntroduced = introducedLetter.charAt(0);

        boolean letterGuessed = false;

        for (int i = 0; i < mword.length() ; i++) {
            char charFromWord = mword.charAt(i);
            Log.d("mylog","there letter we check is  "+charFromWord);
            if( charFromWord == charIntroduced ) {
                Log.d("mylog","there was one match  ");

                showGuessLetter(i , charIntroduced);

                letterGuessed = true;

                mGuessedLetter++;
            }


        }


        if(letterGuessed == false) {


            letterFail(Character.toString(charIntroduced));

        }

        if(mGuessedLetter == mword.length()) {
            cleanScreen();
        }
    }


    public void cleanScreen() {
        TextView textViewFailed = (TextView) findViewById(R.id.textView6);

        textViewFailed.setText("");

        mGuessedLetter = 0;

        faidCounter = 0;

        LinearLayout layoutLetter = (LinearLayout) findViewById(R.id.layoutLetters);


        for (int i = 0; i < layoutLetter.getChildCount(); i++) {
            TextView textView = (TextView) layoutLetter.getChildAt(i);
            textView.setText("_");
        }

        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setImageResource(R.drawable.hangman_0);


    }

    public void letterFail(String letterFailed){

        TextView textViewFailed = (TextView) findViewById(R.id.textView6);

        String previousFailded = textViewFailed.getText().toString();
        textViewFailed.setText(previousFailded+letterFailed);

        faidCounter++;
        ImageView imageView = (ImageView) findViewById(R.id.imageView);

        if( faidCounter == 1) {
            imageView.setImageResource(R.drawable.hangman_1);
        }else if (faidCounter == 2) {
            imageView.setImageResource(R.drawable.hangman_2);
        }else if (faidCounter == 3) {
            imageView.setImageResource(R.drawable.hangman_3);
        }else if (faidCounter == 4) {
            imageView.setImageResource(R.drawable.hangman_4);
        }else if (faidCounter == 5) {
            imageView.setImageResource(R.drawable.hangman_5);
        }else if (faidCounter == 6) {
            Intent gameover = new Intent(this, GameOverActivity.class);
            startActivity(gameover);
        }



    }


    /**
     * display letter guessed by user
     *
     * @param position of letter
     * @param letterGuessed
     */
    public void showGuessLetter(int position , char letterGuessed) {
        LinearLayout layoutLetter = (LinearLayout) findViewById(R.id.layoutLetters);

        TextView textView = (TextView) layoutLetter.getChildAt( position);
        textView.setText(Character.toString(letterGuessed));
    }




//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_game, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
}
