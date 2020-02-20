package com.efarrar.cof_howtoplayasoundwithmediaplayerclass;

/*
 URL: https://youtu.be/C_Ka7cKwXW0
 */


import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    MediaPlayer player;     //var for MediaPlayer

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //1 - play method that checks if player is null, start playing the song
    //2 - added the setOnCompletionListener to call the stopPlayer() once song is completed
    public void play(View v) {
        if (player == null) {
            player = MediaPlayer.create(this, R.raw.song);
            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    stopPlayer();
                }
            });
        }
        player.start();
    }

    //pause the song if the player is != null
    public void pause(View v) {
        if (player != null) {
            player.pause();
        }

    }

    //method to stop and release the song from memory; method will call private method stopPlayer
    public void stop(View v) {
        stopPlayer();
    }

    //method to check if player is != null call the release method, set player to null, use Toast
    private void stopPlayer(){
        if (player != null ) {
            player.release();
            player = null;

            //Toast to show popup the the "MediaPlayer released"
            Toast.makeText(this, "MediaPlayer released", Toast.LENGTH_SHORT).show();
        }
    }

    /*overriding the onStop() for the natural stopping of the song  to release from memory using the
        stopPlayer() we created.
     */
    @Override
    protected void onStop(){
        super.onStop();
        stopPlayer();
    }
}
