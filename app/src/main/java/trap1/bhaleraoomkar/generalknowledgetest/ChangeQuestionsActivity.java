package trap1.bhaleraoomkar.generalknowledgetest;

import android.content.Intent;
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;


import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class ChangeQuestionsActivity extends AppCompatActivity {

    TextView numQuestions;
    SeekBar questionSlider;
    Button submitButton;
    int currQuestions;


    SeekBar.OnSeekBarChangeListener changeQ = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            currQuestions = progress+1;
            numQuestions.setText(String.format("%d", currQuestions));

        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {}

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {}
    };

    View.OnClickListener submit = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent resultIntent = new Intent();
            resultIntent.putExtra(getString(R.string.newNumQ), currQuestions);
            setResult(RESULT_OK, resultIntent);
            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_questions);
        numQuestions = (TextView)findViewById(R.id.points);
        questionSlider = (SeekBar)findViewById(R.id.questionSlider);
        submitButton = (Button)findViewById(R.id.submitQuestions);

        Intent intent = getIntent();

        currQuestions = intent.getIntExtra(getString(R.string.currQ), 10);

        questionSlider.setProgress(currQuestions-1);

        numQuestions.setText(String.format("%d", currQuestions));
        questionSlider.setOnSeekBarChangeListener(changeQ);
        submitButton.setOnClickListener(submit);

    }

}
