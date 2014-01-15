package se.mah.kingdom_v02;

import java.util.Calendar;
import java.util.Random;

import se.mah.kingdom_v02.R.id;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class DeathEvent extends Activity {

	TextView deathSentence;
	String[] kings;
	TextView kingText;
	int numberInArray = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_death_event);
		
		deathSentence = (TextView) findViewById(id.text_intro_story_text_p1);
		kingText = (TextView) findViewById(id.text_story_ranking);
		ImageView button = (ImageView) findViewById(id.restartbutton);
		
		Resources resStory = getResources();
		kings = resStory.getStringArray(R.array.kings_ranking);
		int ranking = ResourcesKingdom.CombinedResources();
		
		if(ranking <12)
		{
			kingText.setText(kings[13]);
			numberInArray = 13;
		}
		else if(ranking <18&& ranking >=12)
		{
			kingText.setText(kings[12]);
			numberInArray = 12;
		}
		else if(ranking <24&& ranking >=18)
		{
			kingText.setText(kings[11]);
			numberInArray = 11;
		}
		else if(ranking <30&& ranking >=24)
		{
			kingText.setText(kings[10]);
			numberInArray = 10;
		}
		else if(ranking <36&& ranking >=30)
		{
			kingText.setText(kings[9]);
			numberInArray = 9;
		}
		else if(ranking <42&& ranking >=36)
		{
			kingText.setText(kings[8]);
			numberInArray = 8;
		}
		else if(ranking <48&& ranking >=42)
		{
			kingText.setText(kings[7]);
			numberInArray = 7;
		}
		else if(ranking <54&& ranking >=48)
		{
			kingText.setText(kings[6]);
			numberInArray = 6;
		}
		else if(ranking <60&& ranking >=54)
		{
			kingText.setText(kings[5]);
			numberInArray = 5;
		}
		else if(ranking <66&& ranking >=60)
		{
			kingText.setText(kings[4]);
			numberInArray = 4;
		}
		else if(ranking <72&& ranking >=66)
		{
			kingText.setText(kings[3]);
			numberInArray = 3;
		}
		else if(ranking <78&& ranking >=72)
		{
			kingText.setText(kings[2]);
			numberInArray = 2;
		}
		else if(ranking <84&& ranking >=78)
		{
			kingText.setText(kings[1]);
			numberInArray = 1;
		}
		else if(ranking <100 && ranking >=84)
		{
			kingText.setText(kings[0]);
			numberInArray = 0;
		}
		
		//button.setText("Klick to find out more about " + kings[numberInArray]);
		
		if (ResourcesKingdom.getFood() <= 0)
			deathSentence.setText("You starved to death!");
		else if (ResourcesKingdom.getGold() <= 0)
			deathSentence.setText("You went bankrupt!");
		else if(ResourcesKingdom.getAmountOfEventsMade() >=12)
			deathSentence.setText("You grew old and died peacefully in your sleep.");
		else
			deathSentence.setText("You died during a revolt!");
		ResourcesKingdom.resetGame();
	}

	public void Restart(View v) {
		ResourcesKingdom.newPlayerChange();
		Intent intent2 = new Intent(this, StoryManager.class);
		intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		DeathEvent.this.finish();
		startActivity(intent2);
	}
	
	public void Something(View v)
	{
		Resources resStory = getResources();
		String[] webpage = resStory.getStringArray(R.array.kings_webpage);
		Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        intent.setData(Uri.parse(webpage[numberInArray]));
        startActivity(intent);
	}

}
