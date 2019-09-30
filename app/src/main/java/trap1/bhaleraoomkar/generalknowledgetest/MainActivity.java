package trap1.bhaleraoomkar.generalknowledgetest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Button;
import android.view.View;
import android.util.Log;
import android.widget.*;

import java.text.DecimalFormat;

import java.util.*;

public class MainActivity extends AppCompatActivity {
    Button button1;
    Button resetButton;
    EditText entry1;
    TextView text1;
    TextView hello;
    TextView scorebox;
    TextView timeLeft;
    TextView rules;
    TextView points;
    ConstraintLayout layout;

    String[] questions = new String[]{"What is 1 + 1?",
            "What state is Chicago in?",
            "What year did the Civil War end?",
            "How many protons are in an an atom of mercury?",
            "What was Juliet's last name in Shakespeare's 'Romeo and Juliet?'",
            "What is the decimal representation of 1110110?",
            "What section of Article I of the U.S. Constitution describes the purpose of the federal government?",
            "Positive feedback loops occur when there are a __ number of negative connectors.",
            "The authors of now-ubiquitous writing style guide were __ and White.",
            "The best Mobile teacher at TJ is Mr. __",
            "How many chromosomes are in a human haploid cell?",
            "Which computer scientist created Algorithm X? (Last name only)",
            "The Moon orbits the Earth at the same rate as its rotation. This is known as ___ ___",
            "To the nearest thousand, what is the speed of light in kilometers per second?",
            "What year did Thomas Jefferson die?",
            "In what year did TJ's latest renovation finish?",
            "What is the largest organ in the human body?",
            "Which class won TJ Homecoming 2018?",
            "What is the musical note a perfect fourth up from C?",
            "What is the country with the highest population density?",
            "What is the most electronegative element on the periodic table?",
            "What state is Washington University in?",
            "How many fluid ounces are in a gallon?",
            "How many cubic meters of dirt are in a 5 meter by 2 meter by 1 meter hole?",
            "How many keys are on a piano?",
            "The longest river in the world is the ___ River.",
            "On what planet have the lowest temperatures in the Solar System been reached?",
            "The oldest Ivy League university is ___ University.",
            "The youngest Ivy League university is ___ University.",
            "What country fully surrounds Lesotho?",
            "Which writer wrote 'The Jungle Book,' along with other famous works? (Last name only)",
            "What is the name of the body of water separating the UK and France?",
            "What is 2 + 2?",
            "The Chicxulub event occurred about __ million years ago",
            "When was Java first created?",
            "What is the name of TJ's principal from 2006 to 2017? (Last name only)",
            "The ring of volcanoes that encircles the Pacific Ocean is known as the Pacific Ring of ___.",
            "How many 1's are in the 5x5 identity matrix?",
            "What year did World War II end?",
            "What is the operation after exponentiaion in the hyperoperation sequence?",
            "What number is famous for using the hyperoperation sequence to become very, very large?",
            "On an equal-temperament tuned instrument, a minor second involves multiplying the frequency by the __th root of 2.",
            "The name of the most commonly used Android layout is the ____ Layout.",
            "The only two doubly landlocked countries are Lichtenstein and ___.",
            "The body of rocks between Mars and Jupiter is known as the ___ Belt.",
            "Jeff Bezos, the current richest man in the world, is the CEO of ___.",
            "What is the capital of North Korea?",
            "What is the hardest naturally occuring mineral?",
            "A cipher which involves a shift in the alphabet is also known as a ___ cipher.",
            "The deepest lake in the world is Lake ___.",
            "Crater Lake is in which state?",
            "To the nearest 10,000 kilometers, what is the circumference of the Earth?",
            "How old is the average student when they begin their first year of high school?",
            "How many bonds are in benzene?",
            "What is the largest country by area?",
            "The moon's gravity is about 1/_ as strong as Earth's.",
            "What is the 'number of the beast?'",
            "In 2010, a 7.0 magnitude earthquake hit which Caribbean country?",
            "The group that claimed responsibility for 9/11 was al-___.",
            "What is 3 + 3?",
            "What is 4 + 4?",
            "The circle above 66.5 degrees North latitude is known as the ___ Circle.",
            "Java has ___ typing, as opposed to Python's dynamic typing.",
            "How many degrees are in a circle?",
            "What is the interior angle of a pentagon in degrees?",
            "What is the southernmost U.S. state?",
            "What is the age of majority in Canaada?",
            "What is 5 + 5?",
            "What is 0 + 0?",
            "What is e^(i * pi)?"
    };
    String[] answers = new String[]
            {"2","Illinois","1865","80","Capulet","118","8","Even","Strunk", "Tra", "23", "Knuth", "Tidal Lock", "300000", "1826","2017",
    "Skin","2019","F","Monaco","Fluorine","Missouri","128","0","88","Nile","Earth","Harvard","Cornell","South Africa","Kipling","English Channel",
                    "4","66","1995","Glazer","Fire","5","1945","Tetration","Graham's Number","12","Constraint","Uzbekistan","Asteroid","Amazon","Pyongyang",
                    "Diamond","Caesar","Baikal","Oregon","40000","14","6","Russia","6","666","Haiti","Qaeda","6","8","Arctic","Static","360","108","Hawaii","19",
                    "10","0","-1"};
    TreeSet<Entry> leaderboard = new TreeSet<Entry>(Collections.<Entry>reverseOrder());
    String playerName = "";
    int numQuestions = 10;
    int pointVal = 1000;
    int currIdx = -1;
    String currAns = "";
    int score = 0;
    Timer timer = new Timer();
    int max_duration = 2000;
    int duration = max_duration;

    HashSet<Integer> seen = new HashSet<Integer>();
    ArrayList<Integer> indices = new ArrayList<Integer>();

    DecimalFormat formatTime = new DecimalFormat("0.0");

    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;



    View.OnClickListener reset = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            seen = new HashSet<Integer>();
            indices = new ArrayList<Integer>();
            int curr = -1;
            for(int i = 0; i < numQuestions; i++){
                do {
                    curr = (int) (Math.random() * questions.length);
                }while(seen.contains(curr));
                indices.add(curr);
                seen.add(curr);
            }
            score = 0;
            currIdx = -1;
            duration = max_duration;
            timer = new Timer();
            resetButton.setVisibility(View.GONE);
            rules.setVisibility(View.GONE);
            timeLeft.setText("\n");
            points.setText("");
            scorebox.setText(String.format("Score: %s", score));
            name.onClick(v);
        }
    };

    View.OnClickListener resetLeaderboard = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            leaderboard.clear();
            editor.clear();
            editor.commit();
            scorebox.setText(String.format("Final Score: %s\n\nTop 5 scores:\n", score));
            resetButton.setVisibility(View.GONE);
        }
    };

    View.OnClickListener name = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            hello.setVisibility(View.INVISIBLE);
            text1.setText(getString(R.string.name_enter));
            entry1.setVisibility(View.VISIBLE);
            entry1.setEnabled(true);
            button1.setText(getString(R.string.click_button));
            button1.setOnClickListener(getName);
            entry1.setOnKeyListener(getNameKey);

        }
    };

    View.OnKeyListener getNameKey = new View.OnKeyListener() {
        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {
            if(keyCode == KeyEvent.KEYCODE_ENTER  && event.getAction() == KeyEvent.ACTION_DOWN) {
                button1.setOnClickListener(null);
                playerName = entry1.getText().toString();
                entry1.setText("");
                timeLeft.setText(getString(R.string.time, "" + formatTime.format(duration / 100)));
                points.setText(getString(R.string.points, "" + pointVal));
                newQ.onClick(v);
                return true;
            }
            return false;
        }
    };



    View.OnClickListener getName = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            entry1.setOnKeyListener(null);
            playerName = entry1.getText().toString();
            entry1.setText("");
            timeLeft.setText(getString(R.string.time, ""+formatTime.format(duration/100)));
            points.setText(getString(R.string.points, ""+pointVal));
            newQ.onClick(v);
        }
    };

    View.OnClickListener end = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            layout.setBackgroundColor(Color.WHITE);
            timeLeft.setText("\n");
            points.setText("");
            entry1.setVisibility(View.GONE);
            if(leaderboard.size() == 0 || score >= leaderboard.first().getScore()) {
                Context context = getApplicationContext();
                String text = "New High Score!";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
            resetButton.setVisibility(View.VISIBLE);
            long time = System.currentTimeMillis();
            String endString = String.format("Final Score: %s\n\nTop 5 scores:\n", score);
            String saveString = "";
            leaderboard.add(new Entry(playerName, score, time));
            int i = 0;
            for(Entry e: leaderboard){
                endString = endString + (i+1) + ". " + e.toString() + "\n";
                saveString = saveString + "a" + e.toFullString() + "\n";
                i++;
                if(i==5) break;
            }
            editor.putString(getString(R.string.high_scores_key), saveString);
            editor.commit();
            text1.setText(getString(R.string.game_over));
            scorebox.setText(endString);
            button1.setText(getString(R.string.play_again));
            button1.setOnClickListener(reset);
        }
    };

    View.OnClickListener newQ = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            layout.setBackgroundColor(Color.WHITE);
            currIdx++;
            entry1.setVisibility(View.VISIBLE);
            points.setVisibility(View.VISIBLE);
            timeLeft.setVisibility(View.VISIBLE);
            duration = max_duration;
            timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if(currIdx >= numQuestions){
                                timer.cancel();

                            }
                            else if(currIdx >= 0) {
                                duration--;
                                String currentTime = getString(R.string.time,""+formatTime.format((double)(duration/100.0)));
                                timeLeft.setText(currentTime);
                                String currentPoints = getString(R.string.points, ""+Math.round((double) pointVal * (.5 + .5 * ((double) duration / (double) (max_duration)))));
                                points.setText(currentPoints);
                            }
                            if(duration<=0){
                                button1.setOnClickListener(response);
                                button1.setText(getString(R.string.click_button_continue));
                                text1.setText(getString(R.string.out_of_time));
                                timeLeft.setVisibility(View.GONE);
                                points.setVisibility(View.GONE);
                                entry1.setVisibility(View.GONE);
                                timer.cancel();

                            }
                        }
                    });
                }
            }, 0, 10);
            if(currIdx >= numQuestions){
                end.onClick(v);
            }else {
                entry1.setEnabled(true);
                text1.setText(String.format("Question %s:\n%s", currIdx + 1, questions[indices.get(currIdx)]));
                button1.setText(getString(R.string.click_button));
                button1.setOnClickListener(response);
                entry1.setOnKeyListener(responseKey);
            }
        }
    };

    View.OnKeyListener responseKey = new View.OnKeyListener() {
        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {
            if(keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                button1.setOnClickListener(null);
                timer.cancel();


                currAns = entry1.getText().toString().toLowerCase().trim();
                entry1.setVisibility(View.GONE);
                timeLeft.setVisibility(View.GONE);
                points.setVisibility(View.GONE);
                entry1.setEnabled(false);
                entry1.setText("");
                if (currAns.equals(answers[indices.get(currIdx)].toLowerCase()) && duration > 0) {
                    layout.setBackgroundColor(Color.GREEN);
                    long points = Math.round((double) pointVal * (.5 + .5 * ((double) duration / (double) (max_duration))));
                    text1.setText(getString(R.string.right, points));
                    score += points;

                } else {
                    layout.setBackgroundColor(Color.RED);
                    text1.setText(String.format("%s The correct answer is %s.", getString(R.string.wrong), answers[indices.get(currIdx)]));
                }
                scorebox.setText(String.format("Score: %s", score));
                button1.setText(getString(R.string.click_button_continue));
                button1.setOnClickListener(newQ);
                return true;
            }
            return false;
        }
    };

    View.OnClickListener response = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            entry1.setOnKeyListener(null);
            timer.cancel();



            currAns = entry1.getText().toString().toLowerCase().trim();
            entry1.setVisibility(View.GONE);
            timeLeft.setVisibility(View.GONE);
            points.setVisibility(View.GONE);
            entry1.setEnabled(false);
            entry1.setText("");
            if (currAns.equals(answers[indices.get(currIdx)].toLowerCase()) && duration > 0) {
                layout.setBackgroundColor(Color.GREEN);
                long points = Math.round((double)pointVal * (.5 + .5*((double)duration/(double)(max_duration))));
                text1.setText(getString(R.string.right, points));
                score += points;

            } else {
                layout.setBackgroundColor(Color.RED);
                text1.setText(String.format("%s The correct answer is %s.", getString(R.string.wrong), answers[indices.get(currIdx)]));
            }
            scorebox.setText(String.format("Score: %s", score));
            button1.setText(getString(R.string.click_button_continue));
            button1.setOnClickListener(newQ);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPref = getSharedPreferences(getString(R.string.high_scores_key), Context.MODE_PRIVATE);
        editor = sharedPref.edit();



        String high_scores = sharedPref.getString(getString(R.string.high_scores_key), "");

        String[] split_vals = high_scores.trim().split("\n");

        for(String s: split_vals){
            if(s.equals("")) continue;
            StringTokenizer st = new StringTokenizer(s);
            String name = st.nextToken().substring(1);
            int score = Integer.parseInt(st.nextToken());
            long time = Long.parseLong(st.nextToken());
            leaderboard.add(new Entry(name, score, time));
        }

        button1 = (Button)findViewById(R.id.clickButton);
        entry1 = (EditText)findViewById(R.id.answer);
        text1 = (TextView)findViewById(R.id.textBox);
        scorebox = (TextView)findViewById(R.id.score);
        timeLeft = (TextView)findViewById(R.id.timeLeft);
        resetButton = (Button)findViewById(R.id.resetLeaderboard);
        rules = (TextView)findViewById(R.id.rules);
        points = (TextView)findViewById(R.id.points);
        layout = (ConstraintLayout)findViewById(R.id.layout);
        hello = (TextView)findViewById(R.id.hello);
        button1.setOnClickListener(reset);
        resetButton.setOnClickListener(resetLeaderboard);



    }





}
class Entry implements Comparable<Entry>{
    private String name;
    private int score;
    private long time;

    public Entry(String n, int s, long t){
        name = n;
        score = s;
        time = t;
    }

    public int compareTo(Entry e){
        if(score < e.score) return -1;
        else if(score > e.score) return 1;
        else if(time < e.time) return -1;
        else if(time > e.time) return 1;
        else return 0;
    }

    public String toString(){
        return name + ": " + score;
    }

    public String toFullString(){
        return name + " " + score + " " + time;
    }

    public String getName(){return name;}
    public int getScore(){return score;}
}
