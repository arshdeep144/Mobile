package com.example.arshu.lab10;

import android.content.res.AssetFileDescriptor;
import android.content.res.Resources;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.io.IOException;
import java.util.ArrayList;

public class PlayMedia extends AppCompatActivity {

    private SurfaceView videoSurface;
    private SurfaceHolder videoSurfaceHolder;

    private MediaPlayer videoMediaPlayer;
    private MediaPlayer musicMediaPlayer;

    private SoundPool soundPool;
    private int buttonSoundId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_media);

        String[] ids = getResources().getStringArray(R.raw.);

        Spinner spinner = (Spinner)findViewById(R.id.txtVideoSourceURL);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, ids);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        videoSurface = (SurfaceView)findViewById(R.id.videoSurface);
        videoSurfaceHolder = videoSurface.getHolder();

        Resources res = getResources();

        // load a sound effect
        AssetFileDescriptor soundFd = res.openRawResourceFd(R.raw.);
        SoundPool.Builder spBuilder = new SoundPool.Builder();
        spBuilder.setMaxStreams(20);
        soundPool = spBuilder.build();

        buttonSoundId = soundPool.load(soundFd, 1);

        // load a background song
        musicMediaPlayer = new MediaPlayer();
        AssetFileDescriptor musicFd = res.openRawResourceFd(R.raw.);
        try {
            musicMediaPlayer.setDataSource(musicFd.getFileDescriptor(),
                    musicFd.getStartOffset(),
                    musicFd.getLength());

            musicMediaPlayer.prepare(); // blocking
            musicMediaPlayer.setLooping(true);
            musicMediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void downloadVideo() {
        Spinner videoSource = (Spinner)findViewById(R.id.txtVideoSourceURL);
        String url = videoSource.getText().toString();

        try {
            // prepare a media player
            videoMediaPlayer = new MediaPlayer();

            videoMediaPlayer.setDataSource(url);
            videoMediaPlayer.setDisplay(videoSurfaceHolder);
            videoMediaPlayer.setScreenOnWhilePlaying(true);
            videoMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

            // what to do when the buffer is full
            videoMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    int width = mediaPlayer.getVideoWidth();
                    int height = mediaPlayer.getVideoHeight();

                    if (width > 0 && height > 0) {
                        Log.d("MediaDemo", "Loading video: " + width + ", " + height);
                        videoSurfaceHolder.setFixedSize(width, height);
                        Button playButton = (Button)findViewById(R.id.btnPlay);
                        playButton.setEnabled(true);
                    }

                }
            });
            videoMediaPlayer.prepareAsync();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void pause(View source) {
        soundPool.autoPause();
    }

    public void play(View source) {
        soundPool.play(buttonSoundId,
                1f, 1f,
                0, 0, 1f);

        videoMediaPlayer.start();
    }
}
