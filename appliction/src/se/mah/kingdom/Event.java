package se.mah.kingdom;

import java.util.zip.Inflater;

import android.hardware.Camera.Parameters;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.PowerManager;
import android.provider.Settings;
import android.app.Activity;
import android.app.KeyguardManager;
import android.app.KeyguardManager.KeyguardLock;
import android.content.Context;
import android.view.View;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;

public class Event extends Activity {
	private MediaPlayer event_player;
	private AudioManager event_am;
    private static PowerManager.WakeLock wakeLock;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_wake_up);
		wakeDevice();
		getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD); 
		event_am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
		event_am.setMode(AudioManager.MODE_NORMAL);
		event_am.setSpeakerphoneOn(true);
		event_am.setBluetoothScoOn(false);
		event_player = MediaPlayer.create(this,
				Settings.System.DEFAULT_RINGTONE_URI);
		event_player.setAudioStreamType(AudioManager.STREAM_MUSIC);
		event_player.start();
	}
    public void wakeDevice() {
        PowerManager powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
        wakeLock = powerManager.newWakeLock((PowerManager.SCREEN_BRIGHT_WAKE_LOCK | PowerManager.FULL_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP), "TAG");
        wakeLock.acquire();

        KeyguardManager keyguardManager = (KeyguardManager) getSystemService(Context.KEYGUARD_SERVICE);
        runOnUiThread(new Runnable(){
            public void run(){
                getWindow().addFlags( 
                          WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                        | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
                        | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                        | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD); 
                
            }
            
        });
    }
	public void onStart() {
		event_player.start();
		super.onStart();
	}

	public void onDestroy() {
		event_player.stop(); 
		event_player.release();
		super.onDestroy();
	}

	public void onPause() {
		event_player.pause();
		super.onPause();
	}

	public void phone_button(View v) {
		event_player.stop();
		event_am.setMode(AudioManager.MODE_IN_CALL);
		event_am.setSpeakerphoneOn(false);
		event_am.setBluetoothScoOn(true);
		event_player = MediaPlayer.create(Event.this, R.raw.event_voice);
		event_player.start();
	}
}