package fr.tvbarthel.games.chasewhisply;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.games.GamesClient;

import fr.tvbarthel.games.chasewhisply.google.BaseGameActivity;

public class HomeActivity extends BaseGameActivity {

	private GamesClient mGamesClient;
	private Button mButtonSignIn;
	private Button mLeaderBoard;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		mButtonSignIn = (Button) findViewById(R.id.home_sign_in);
		mButtonSignIn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Log.d("DEBUG", "beginUserInitiatedSignIn");
				beginUserInitiatedSignIn();
			}
		});

		mLeaderBoard = (Button) findViewById(R.id.home_leaderboard);
		mLeaderBoard.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (getGamesClient().isConnected()) {
					Log.i("DEBUG", "sign in isConnected");
					startActivityForResult(mGamesClient.getLeaderboardIntent(getResources().getString(R.string.leaderboard_easy)), 0);
				} else {
					Log.i("DEBUG", "sign in !isConnected");
				}
			}
		});


		findViewById(R.id.home_play).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				startActivity(new Intent(HomeActivity.this, GameModeChooserActivity.class));
			}
		});
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onSignInFailed() {
		Log.i("DEBUG", "sign in faileded");

	}

	@Override
	public void onSignInSucceeded() {
		Log.i("DEBUG", "sign in succeeded");
		mButtonSignIn.setBackgroundResource(R.drawable.signin_disabled);
	}
}
