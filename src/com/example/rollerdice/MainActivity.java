package com.example.rollerdice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import com.example.rollerdice.R;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity // implements SensorEventListener
{

	private SensorManager mSensorManager;
	private Sensor mAccelerometer;

	private float mAccel; // acceleration apart from gravity
	private float mAccelCurrent; // current acceleration including gravity
	private float mAccelLast; // last acceleration including gravity
	private ShakeEventListener mSensorListener;

	HashMap<String, Integer> gStats = new HashMap<String, Integer>();
	HashMap<String, Integer> rStats = new HashMap<String, Integer>();
	
	int throwsCount = 0;
	private TextView redCount;
	private TextView greenCount;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);

		redCount = (TextView) findViewById(R.id.editText2);
		// redCount.setOnFocusChangeListener(new OnFocusChangeListener() {
		//
		// @Override
		// public void onFocusChange(View v, boolean hasFocus) {
		// // TODO Auto-generated method stub
		//
		// }
		// });

		greenCount = (TextView) findViewById(R.id.editText3);

		final TextView redThrows = (TextView) findViewById(R.id.textViewRedThrows);
		final TextView greenThrows = (TextView) findViewById(R.id.textViewGreenThrows);

		final TextView stats = (TextView) findViewById(R.id.textViewThowsCountVal);

		int l = R.string.greenDicesCount;

		mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
		mAccelerometer = mSensorManager
				.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

		mAccel = 0.00f;
		mAccelCurrent = SensorManager.GRAVITY_EARTH;
		mAccelLast = SensorManager.GRAVITY_EARTH;

		mSensorListener = new ShakeEventListener();

		mSensorListener
				.setOnShakeListener(new ShakeEventListener.OnShakeListener() {

					public void onShake() {

						throwsCount++;

						if (redCount.getText().toString() == null
								|| redCount.getText().toString().length() == 0) {
							redCount.setText(new StringBuffer("0"));
						}

						if (greenCount.getText().toString() == null
								|| greenCount.getText().toString().length() == 0) {
							greenCount.setText(new StringBuffer("0"));
						}

						int r = Integer.parseInt(redCount.getText().toString());
						int g = Integer.parseInt(greenCount.getText()
								.toString());

						List<DiceFace> rl = new ArrayList<DiceFace>();
						
						for (int i = 0; i < r; i++) {
							RedAttackDice dice = new RedAttackDice();
							dice.roll(1);
							Integer s = rStats.get(dice.getDiceFace()
									.getFaceName());
							if (s == null) {
								s = Integer.valueOf(0);

							}
							s++;
							rStats.put(dice.getDiceFace().getFaceName(), s);

							rl.add(dice.getDiceFace());
						}

						List<DiceFace> gl = new ArrayList<DiceFace>();
						
						for (int j = 0; j < g; j++) {
							GreenDefenseDice dice = new GreenDefenseDice();
							dice.roll(1);

							Integer s = gStats.get(dice.getDiceFace()
									.getFaceName());
							if (s == null) {
								s = Integer.valueOf(0);
							}
							s++;
							gStats.put(dice.getDiceFace().getFaceName(), s);
							gl.add(dice.getDiceFace());
						}

						StringBuffer reds = new StringBuffer();
						// Lancers Dés rouges
						for (DiceFace f : rl) {
							reds.append(f.getFaceName() + " ");
						}
						redThrows.setText(reds);

						// Lancers Dés verts
						StringBuffer greens = new StringBuffer();
						for (DiceFace f : gl) {
							greens.append(f.getFaceName() + " ");
						}
						greenThrows.setText(greens);

						// Nb de lancers
						StringBuffer statsBuff = new StringBuffer();
						statsBuff.append(throwsCount);

						statsBuff.append(" Rouges : ");
						int size = rStats.entrySet().size();
						for (Entry<String, Integer> l : rStats.entrySet()) {
							statsBuff.append((int)((float)l.getValue() / size * 100)
									+ "% " + l.getKey() + ", ");
						}

						statsBuff.append(" Verts : ");
						size = gStats.entrySet().size();
						for (Entry<String, Integer> l : gStats.entrySet()) {
							statsBuff.append( (int) ((float)l.getValue() / size * 100) + "% "
									+ l.getKey() + ", ");
						}

						stats.setText(statsBuff);

						Toast.makeText(
								MainActivity.this,
								"Lancer " + r + " dés rouges " + g
										+ " dés verts " + " (" + throwsCount
										+ ")  ", Toast.LENGTH_SHORT).show();
					}
				});
	}

	protected void onResume() {
		super.onResume();
		// mSensorManager.registerListener(this, mAccelerometer,
		// SensorManager.SENSOR_DELAY_NORMAL);

		mSensorManager.registerListener(mSensorListener,
				mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
				SensorManager.SENSOR_DELAY_UI);
	}

	protected void onPause() {
		super.onPause();
		// mSensorManager.unregisterListener(this);

		mSensorManager.unregisterListener(mSensorListener);
	}

	@Override
	protected void onStop() {
		// unregister listener
		// mSensorManager.unregisterListener(this);

		mSensorManager.unregisterListener(mSensorListener);
		super.onStop();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
