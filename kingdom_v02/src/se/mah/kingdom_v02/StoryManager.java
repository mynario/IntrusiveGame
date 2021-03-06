package se.mah.kingdom_v02;

import java.util.Calendar;
import java.util.Random;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class StoryManager extends Activity {
	private int gold = 0;
	private int food = 0;
	private int happy = 0;
	@SuppressWarnings("unused")
	private ResourcesKingdom resourcePrefs;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_story_manager);
		resourcePrefs = new ResourcesKingdom(
				StoryManager.this.getApplicationContext());
		if (ResourcesKingdom.newPlayerstate() == true) {
			Intent newEvent = new Intent(StoryManager.this, StartEvent.class);

			StoryManager.this.finish();
			startActivity(newEvent);
		}
		AlarmManager alarmMgr = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
		Intent intent = new Intent(this, AlarmReceiver.class);
		PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0,
				intent, 0);
		Calendar time = Calendar.getInstance();
		int min = ((60 - time.get(Calendar.MINUTE)));
		int max = 60;
		Random rand = new Random();
		int random = rand.nextInt(max);
		int update = random + min;
		time.setTimeInMillis(System.currentTimeMillis());

		if (ResourcesKingdom.newPlayerstate() == false) {
			if (ResourcesKingdom.getstateGame()) {
				time.add(Calendar.SECOND, 10);// put update here 
				ResourcesKingdom.setstateGame();
				alarmMgr.set(AlarmManager.RTC_WAKEUP, time.getTimeInMillis(),
						pendingIntent);
			}
		}
		loadResources();
		Log.i("food", Integer.toString(food));
		Log.i("gold", Integer.toString(gold));
		Log.i("happy", Integer.toString(happy));
		refresh();
		TextView jesterComment = (TextView) findViewById(R.id.text_jester_comment);
		String sPart1 = ResourcesKingdom.getEventName();
		int sPart2 = ResourcesKingdom.getEventOption();
		String s1 = sPart1 + "_jester_o" + Integer.toString(sPart2);
		Log.i("identificator", s1);
		 int i1 = getResources().getIdentifier(s1, "string",
		 this.getPackageName());
		 String s2 = getResources().getString(i1);
		 Log.i("line jester", s2);
		 jesterComment.setText(s2);

	}

	public void changeStat(View v) {
		Random r = new Random();
		gold = r.nextInt(3) + 1;
		food = r.nextInt(3) + 1;
		happy = r.nextInt(3) + 1;
		Button butt = (Button) findViewById(R.id.buttonChangeStat);
		butt.setText(Integer.toString(gold));

		refresh();

	}

	public void loadResources() {
		food = getStat(ResourcesKingdom.getFood());
		gold = getStat(ResourcesKingdom.getGold());
		happy = getStat(ResourcesKingdom.getHappy());

	}

	public int getStat(int number) {
		if (number < 40) {
			number = 1;
		} else if (number > 70) {
			number = 3;
		} else {
			number = 2;
		}
		return number;
	}

	public void refresh() {
		ImageView fl = (ImageView) findViewById(R.id.imageMapLF);
		ImageView fm = (ImageView) findViewById(R.id.imageMapMF);
		ImageView fh = (ImageView) findViewById(R.id.imageMapHF);
		ImageView gl = (ImageView) findViewById(R.id.imageMapLG);
		ImageView gm = (ImageView) findViewById(R.id.imageMapMG);
		ImageView gh = (ImageView) findViewById(R.id.imageMapHG);
		ImageView hl = (ImageView) findViewById(R.id.imageMapLH);
		ImageView hm = (ImageView) findViewById(R.id.imageMapMH);
		ImageView hh = (ImageView) findViewById(R.id.imageMapHH);

		switch (gold) {
		case 1: {
			gl.setVisibility(View.VISIBLE);
			gm.setVisibility(View.GONE);
			gh.setVisibility(View.GONE);
			break;
		}
		case 2: {
			gl.setVisibility(View.GONE);
			gm.setVisibility(View.VISIBLE);
			gh.setVisibility(View.GONE);
			break;
		}
		case 3: {
			gl.setVisibility(View.GONE);
			gm.setVisibility(View.GONE);
			gh.setVisibility(View.VISIBLE);
			break;
		}
		}

		switch (food) {
		case 1: {
			fl.setVisibility(View.VISIBLE);
			fm.setVisibility(View.GONE);
			fh.setVisibility(View.GONE);
			break;
		}
		case 2: {
			fl.setVisibility(View.GONE);
			fm.setVisibility(View.VISIBLE);
			fh.setVisibility(View.GONE);
			break;
		}
		case 3: {
			fl.setVisibility(View.GONE);
			fm.setVisibility(View.GONE);
			fh.setVisibility(View.VISIBLE);
			break;
		}
		}
		switch (happy) {
		case 1: {
			hl.setVisibility(View.VISIBLE);
			hm.setVisibility(View.GONE);
			hh.setVisibility(View.GONE);
			break;
		}
		case 2: {
			hl.setVisibility(View.GONE);
			hm.setVisibility(View.VISIBLE);
			hh.setVisibility(View.GONE);
			break;
		}
		case 3: {
			hl.setVisibility(View.GONE);
			hm.setVisibility(View.GONE);
			hh.setVisibility(View.VISIBLE);
			break;
		}
		}
	}

	@Override
	public void onPause() {
		super.onPause();
		loadResources();
		refresh();
	}

}
