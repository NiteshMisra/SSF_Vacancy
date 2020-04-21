package in.indilabz.ssf_exam.utils;

import android.app.Activity;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import org.joda.time.Period;
import org.joda.time.PeriodType;
import org.joda.time.Seconds;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;

import java.util.Timer;
import java.util.TimerTask;

public class CountdownTimer {
	private static final int ONE_SECOND = 1;
	private static final int FINISH = 0;
	private static final int ONE_SECOND_IN_MILLISECOND = 1000;
	private Activity timerActivity;
	private int seconds;
	private TextView textView;
	private Timer timer;
	private Toolbar toolbar;
	private OnCountdownFinish onCountdownFinish;

	private static final PeriodFormatter FORMAT_HOURS_MINUTES_SECONDS = new PeriodFormatterBuilder()
			.printZeroIfSupported().minimumPrintedDigits(2).appendHours()
			.printZeroIfSupported().minimumPrintedDigits(2)
			.appendSeparator(":").appendMinutes().printZeroIfSupported()
			.minimumPrintedDigits(2).appendSeparator(":").appendSeconds()
			.minimumPrintedDigits(2).toFormatter();

	public CountdownTimer(Activity timerActivity,
						  int seconds,
						  TextView textView,
						  Toolbar toolbar) {
		this.timerActivity = timerActivity;
		this.seconds = seconds;
		this.textView = textView;
		this.toolbar = toolbar;
	}

	public void start() {
		if (timer == null && !isTimeReachedZero()) {
			startTimerAndScheduleTask();
		}
	}

	private void startTimerAndScheduleTask() {
		timer = new Timer();
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				updateTextInUiThread();
			}
		};
		timer.schedule(task, 0, ONE_SECOND_IN_MILLISECOND);
	}

	public void stop() {
		if (timer != null) {
			timer.cancel();
			timer = null;
		}
	}

	private String getTimeFormatted(int seconds) {
		Seconds secondsConverted = Seconds.seconds(seconds);
		Period period = new Period(secondsConverted);
		String result = FORMAT_HOURS_MINUTES_SECONDS.print(period
				.normalizedStandard(PeriodType.time()));
		return result;
	}

	private void updateTextInUiThread() {
		timerActivity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				updateText();
			}
		});
	}

	private void updateText() {
		seconds -= ONE_SECOND;
		String result = getTimeFormatted(seconds);
		textView.setText(result);
		toolbar.setTitle(result);
		if (isTimeReachedZero()) {

			try{
				callBackOnCountdownFinish();
				timer.cancel();
			}
			catch (Exception e){
				e.printStackTrace();
			}
		}
	}

	private void callBackOnCountdownFinish() throws Exception{
		if (onCountdownFinish != null) {
			onCountdownFinish.onCountdownFinish();
		}
	}

	private boolean isTimeReachedZero() {
		return seconds == FINISH;
	}

	public void setOnCountdownFinish(OnCountdownFinish onCountdownFinish) {
		this.onCountdownFinish = onCountdownFinish;
	}

}
