package dk.tec.velfaerdsapp;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.exoplayer2.ui.PlayerView;

import Adapter.VideoAdapter;
import Strengths.Strengths;
import QuestionsAdapter.BesAdapter;

//import com.google.android.material.slider.Slider;


public class BesPage extends TouchActivityHandler {

    private static final String TAG = "questionsPage";

    public static ProgressBar questionsProgressBar;
    public static ImageView imgNextPage;
    public static int count;
    public static int answeredCount;
    ImageView videobtn;
    PlayerView playerView;
    ListView listOfQuestions;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bes_page);
        questionsProgressBar = findViewById(R.id.questionsProgressBar);
        listOfQuestions = findViewById(R.id.listOfQuestions);
        imgNextPage = findViewById(R.id.imgNextPage);
        videobtn = findViewById(R.id.btnYoutube);
        playerView = findViewById(R.id.player_view);
        BesAdapter questionsAdapter = new BesAdapter(BesPage.this, Strengths.getBesList());
        listOfQuestions.setAdapter(questionsAdapter);
        playerView.setVisibility(View.GONE);
        count = questionsAdapter.getCount();
        questionsProgressBar.setMax(questionsAdapter.getCount());
        questionsProgressBar.setProgress(answeredCount);
        checkPoints();

    TextView txtDinAvatar = findViewById(R.id.txtBesDinAvatar);
        txtDinAvatar.setText(gJob + " " + gName);


        videobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerView.setVisibility(View.VISIBLE);
                VideoAdapter video = new VideoAdapter(BesPage.this, R.raw.besvid, playerView);
                video.play();

            }
        });

    }

    public static void checkPoints(){
        imgNextPage.setAlpha(0.0f);

        if (answeredCount == count){
            imgNextPage.setVisibility(View.VISIBLE);
            imgNextPage.animate().alpha(1.0f).setDuration(1000);
            imageBounce();
        }
        else {
            imgNextPage.setVisibility(View.GONE);
        }
    }

    public static void imageBounce(){
        ObjectAnimator pulse = ObjectAnimator.ofPropertyValuesHolder(
                imgNextPage,
                PropertyValuesHolder.ofFloat("scaleX", 1.2f),
                PropertyValuesHolder.ofFloat("scaleY", 1.2f));
        pulse.setDuration(1500);

        pulse.setRepeatCount(ValueAnimator.INFINITE);
        pulse.setRepeatMode(ObjectAnimator.REVERSE);

        pulse.start();
    }
}