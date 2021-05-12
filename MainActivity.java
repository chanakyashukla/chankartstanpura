package com.chanakya.chankartstanpuramale;

import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private SoundPool soundPool;
    private int sound1, sound2, sound3, sound4;
    private int sound1StreamId, sound2StreamId, sound3StreamId, sound4StreamId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();

            soundPool = new SoundPool.Builder()
                    .setMaxStreams(4)
                    .setAudioAttributes(audioAttributes)
                    .build();
        } else {
            soundPool = new SoundPool(4, AudioManager.STREAM_MUSIC, 0);
        }

        sound1 = soundPool.load(this, R.raw.sound1,1);
        sound2 = soundPool.load(this, R.raw.sound2,1);
        sound3 = soundPool.load(this, R.raw.sound3,1);
        sound4 = soundPool.load(this, R.raw.sound4,1);

    }

    public void playSound (View v) {
        switch (v.getId()) {
            case R.id.button_sound1:
                sound1StreamId = soundPool.play(sound1,1,1,0,-1,1);
                soundPool.pause(sound2StreamId);
                soundPool.pause(sound3StreamId);
                soundPool.pause(sound4StreamId);
                break;
            case R.id.button_sound2:
                sound2StreamId = soundPool.play(sound2,1,1,0,-1,1);
                soundPool.pause(sound1StreamId);
                soundPool.pause(sound3StreamId);
                soundPool.pause(sound4StreamId);
                break;
            case R.id.button_sound3:
                sound3StreamId = soundPool.play(sound3,1,1,0,-1,1);
                soundPool.pause(sound1StreamId);
                soundPool.pause(sound2StreamId);
                soundPool.pause(sound4StreamId);
                break;
            case R.id.button_sound4:
                sound4StreamId = soundPool.play(sound4,1,1,0,-1,1);
                soundPool.pause(sound1StreamId);
                soundPool.pause(sound2StreamId);
                soundPool.pause(sound3StreamId);
                break;
            case R.id.button_reset:
                soundPool.autoPause();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        soundPool.release();
        soundPool = null;

    }
}
