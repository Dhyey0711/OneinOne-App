package com.example.oneinone_alltoolsapp.FunwithOneinOne;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.example.oneinone_alltoolsapp.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Fun_Facts extends AppCompatActivity {

    private TextView tvQuestion, tvTimer, tvScore;
    private Button btnAnswer1, btnAnswer2, btnAnswer3, btnAnswer4, btnStart;
    private List<Question> questionsList;
    private Question currentQuestion;
    private int score = 0;
    private int questionIndex = 0;
    private CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fun_facts);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        tvQuestion = findViewById(R.id.tvQuestion1);
        tvTimer = findViewById(R.id.tvTimer);
        tvScore = findViewById(R.id.tvScore1);
        btnAnswer1 = findViewById(R.id.btnAnswera1);
        btnAnswer2 = findViewById(R.id.btnAnswerb2);
        btnAnswer3 = findViewById(R.id.btnAnswerc3);
        btnAnswer4 = findViewById(R.id.btnAnswerd4);

        btnAnswer1.setOnClickListener(answerClickListener);
        btnAnswer2.setOnClickListener(answerClickListener);
        btnAnswer3.setOnClickListener(answerClickListener);
        btnAnswer4.setOnClickListener(answerClickListener);

        loadQuestions();
        startGame();
    }

    private void loadQuestions() {
        questionsList = new ArrayList<>();

        questionsList.add(new Question("What is the world's largest ocean?", "Pacific Ocean", "Atlantic Ocean", "Indian Ocean", "Arctic Ocean"));
        questionsList.add(new Question("Which planet is known as the Red Planet?", "Mars", "Venus", "Jupiter", "Mercury"));
        questionsList.add(new Question("Who painted the Mona Lisa?", "Leonardo da Vinci", "Vincent van Gogh", "Pablo Picasso", "Michelangelo"));
        questionsList.add(new Question("What is the tallest mountain in the world?", "Mount Everest", "K2", "Kangchenjunga", "Lhotse"));
        questionsList.add(new Question("What is the capital city of Japan?", "Tokyo", "Kyoto", "Osaka", "Hiroshima"));
        questionsList.add(new Question("What is the smallest country in the world?", "Vatican City", "Monaco", "San Marino", "Liechtenstein"));
        questionsList.add(new Question("Which is the longest river in the world?", "Nile", "Amazon", "Yangtze", "Mississippi"));
        questionsList.add(new Question("What is the hardest natural substance on Earth?", "Diamond", "Gold", "Platinum", "Titanium"));
        questionsList.add(new Question("Which element has the chemical symbol 'O'?", "Oxygen", "Gold", "Silver", "Iron"));
        questionsList.add(new Question("Who wrote 'Romeo and Juliet'?", "William Shakespeare", "Charles Dickens", "Mark Twain", "Jane Austen"));

        questionsList.add(new Question("Which country gifted the Statue of Liberty to the USA?", "France", "England", "Spain", "Germany"));
        questionsList.add(new Question("What is the largest desert in the world?", "Sahara Desert", "Arabian Desert", "Gobi Desert", "Kalahari Desert"));
        questionsList.add(new Question("Who discovered penicillin?", "Alexander Fleming", "Marie Curie", "Isaac Newton", "Albert Einstein"));
        questionsList.add(new Question("What is the capital of Australia?", "Canberra", "Sydney", "Melbourne", "Brisbane"));
        questionsList.add(new Question("Which mammal is known to have the most powerful bite in the world?", "Hippopotamus", "Lion", "Tiger", "Grizzly Bear"));
        questionsList.add(new Question("Who was the first man to step on the moon?", "Neil Armstrong", "Buzz Aldrin", "Yuri Gagarin", "John Glenn"));
        questionsList.add(new Question("What is the largest planet in our solar system?", "Jupiter", "Saturn", "Neptune", "Earth"));
        questionsList.add(new Question("Which country is known as the Land of the Rising Sun?", "Japan", "China", "South Korea", "Thailand"));
        questionsList.add(new Question("What is the chemical symbol for water?", "H2O", "O2", "CO2", "HO2"));
        questionsList.add(new Question("Which fruit is known to keep the doctor away if eaten daily?", "Apple", "Banana", "Orange", "Grapes"));

        questionsList.add(new Question("What is the tallest animal in the world?", "Giraffe", "Elephant", "Rhinoceros", "Horse"));
        questionsList.add(new Question("Which continent is the Sahara Desert located on?", "Africa", "Asia", "Australia", "North America"));
        questionsList.add(new Question("Who invented the telephone?", "Alexander Graham Bell", "Thomas Edison", "Nikola Tesla", "James Watt"));
        questionsList.add(new Question("Which country is known for inventing the pizza?", "Italy", "Greece", "France", "Turkey"));
        questionsList.add(new Question("What is the main ingredient in guacamole?", "Avocado", "Tomato", "Cucumber", "Pepper"));
        questionsList.add(new Question("What is the smallest planet in our solar system?", "Mercury", "Venus", "Mars", "Pluto"));
        questionsList.add(new Question("Which bird is known for its colorful feathers and dancing?", "Peacock", "Flamingo", "Parrot", "Penguin"));
        questionsList.add(new Question("Which country hosted the 2016 Summer Olympics?", "Brazil", "China", "Japan", "USA"));
        questionsList.add(new Question("Which vitamin is known as the sunshine vitamin?", "Vitamin D", "Vitamin C", "Vitamin A", "Vitamin B12"));
        questionsList.add(new Question("Who is the author of the Harry Potter series?", "J.K. Rowling", "J.R.R. Tolkien", "George R.R. Martin", "C.S. Lewis"));

        questionsList.add(new Question("Which animal is known as the King of the Jungle?", "Lion", "Tiger", "Elephant", "Leopard"));
        questionsList.add(new Question("What is the longest bone in the human body?", "Femur", "Tibia", "Humerus", "Fibula"));
        questionsList.add(new Question("What is the hardest part of the human body?", "Tooth Enamel", "Skull", "Femur", "Ribs"));
        questionsList.add(new Question("Which organ is responsible for pumping blood throughout the body?", "Heart", "Lungs", "Liver", "Kidneys"));
        questionsList.add(new Question("Which planet has a day that lasts longer than its year?", "Venus", "Mercury", "Earth", "Mars"));
        questionsList.add(new Question("What is the capital of Canada?", "Ottawa", "Toronto", "Vancouver", "Montreal"));
        questionsList.add(new Question("Which language is the most widely spoken in the world?", "English", "Mandarin Chinese", "Spanish", "Hindi"));
        questionsList.add(new Question("What is the national flower of Japan?", "Cherry Blossom", "Lotus", "Rose", "Tulip"));
        questionsList.add(new Question("Which animal can sleep standing up?", "Horse", "Lion", "Elephant", "Dog"));
        questionsList.add(new Question("Which planet is closest to the Sun?", "Mercury", "Venus", "Earth", "Mars"));

        questionsList.add(new Question("What is the primary ingredient in chocolate?", "Cocoa", "Milk", "Sugar", "Butter"));
        questionsList.add(new Question("Which ancient wonder was located in Egypt?", "Great Pyramid of Giza", "Hanging Gardens of Babylon", "Statue of Zeus", "Colossus of Rhodes"));
        questionsList.add(new Question("What is the name of the Greek god of the sea?", "Poseidon", "Zeus", "Apollo", "Hades"));
        questionsList.add(new Question("Which animal is the largest land mammal?", "Elephant", "Rhino", "Hippopotamus", "Giraffe"));
        questionsList.add(new Question("Which month has an extra day during a leap year?", "February", "March", "April", "January"));
        questionsList.add(new Question("Which planet is known for its rings?", "Saturn", "Jupiter", "Uranus", "Neptune"));
        questionsList.add(new Question("Who invented the World Wide Web?", "Tim Berners-Lee", "Steve Jobs", "Bill Gates", "Mark Zuckerberg"));
        questionsList.add(new Question("What is the capital of France?", "Paris", "Rome", "Berlin", "Madrid"));
        questionsList.add(new Question("Which organ in the human body produces insulin?", "Pancreas", "Liver", "Kidneys", "Heart"));
        questionsList.add(new Question("Which famous ship sank in 1912?", "Titanic", "Britannic", "Lusitania", "Olympic"));

        questionsList.add(new Question("What is the largest mammal in the world?", "Blue Whale", "Elephant", "Giraffe", "Orca"));
        questionsList.add(new Question("Which metal is liquid at room temperature?", "Mercury", "Lead", "Gold", "Silver"));
        questionsList.add(new Question("Which planet is known as the Morning Star?", "Venus", "Mars", "Jupiter", "Mercury"));
        questionsList.add(new Question("What is the smallest unit of life?", "Cell", "Atom", "Molecule", "Organ"));
        questionsList.add(new Question("Which fruit is known as the king of fruits?", "Mango", "Apple", "Banana", "Grapes"));
        questionsList.add(new Question("What is the name of the longest river in Africa?", "Nile", "Congo", "Zambezi", "Limpopo"));
        questionsList.add(new Question("Which organ is known as the powerhouse of the cell?", "Mitochondria", "Nucleus", "Ribosome", "Chloroplast"));
        questionsList.add(new Question("What is the chemical symbol for gold?", "Au", "Ag", "Fe", "Pb"));
        questionsList.add(new Question("Which is the most populous country in the world?", "China", "India", "USA", "Indonesia"));
        questionsList.add(new Question("What is the capital of Italy?", "Rome", "Venice", "Florence", "Milan"));


        Collections.shuffle(questionsList);
    }

    private void startGame() {
        score = 0;
        questionIndex = 0;
        tvScore.setText("Score: " + score);

        startTimer();
        loadNextQuestion();
    }

    private void startTimer() {
        timer = new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                tvTimer.setText("Time: " + millisUntilFinished / 1000 + "s");
            }

            @Override
            public void onFinish() {
                endGame();
            }
        }.start();
    }

    private void loadNextQuestion() {
        if (questionIndex < 10) { // Limit to 10 questions
            currentQuestion = questionsList.get(questionIndex);
            tvQuestion.setText(currentQuestion.getQuestion());
            List<String> answers = currentQuestion.getShuffledAnswers();
            btnAnswer1.setText(answers.get(0));
            btnAnswer2.setText(answers.get(1));
            btnAnswer3.setText(answers.get(2));
            btnAnswer4.setText(answers.get(3));
            questionIndex++;
        } else {
            endGame();
        }
    }

    private void endGame() {
        timer.cancel();
        provideFeedback();  // Provide feedback based on the score
    }

    private View.OnClickListener answerClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Button clickedButton = (Button) v;
            if (clickedButton.getText().equals(currentQuestion.getCorrectAnswer())) {
                score++;
                tvScore.setText("Score: " + score);
            }
            loadNextQuestion();
        }
    };

    private void provideFeedback() {
        String feedbackMessage;

        if (score >= 8) {
            feedbackMessage = "Excellent! Your score is " + score + ". You're a fun facts guru!";
        } else if (score >= 5) {
            feedbackMessage = "Good job! Your score is " + score + ". You know quite a few fun facts!";
        } else {
            feedbackMessage = "Your score is " + score + ". Keep learning more fun facts!";
        }

        new AlertDialog.Builder(this)
                .setTitle("Game Over")
                .setMessage(feedbackMessage)
                .setPositiveButton("Restart", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        startGame();  // Restart the game
                    }
                })
                .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        finish();  // Close the activity or navigate to another screen
                    }
                })
                .setCancelable(false)
                .show();
    }

    private static class Question {
        private String question;
        private String correctAnswer;
        private String[] wrongAnswers;

        public Question(String question, String correctAnswer, String... wrongAnswers) {
            this.question = question;
            this.correctAnswer = correctAnswer;
            this.wrongAnswers = wrongAnswers;
        }

        public String getQuestion() {
            return question;
        }

        public String getCorrectAnswer() {
            return correctAnswer;
        }

        public List<String> getShuffledAnswers() {
            List<String> answers = new ArrayList<>();
            answers.add(correctAnswer);
            Collections.addAll(answers, wrongAnswers);
            Collections.shuffle(answers);
            return answers;
        }
    }
}
