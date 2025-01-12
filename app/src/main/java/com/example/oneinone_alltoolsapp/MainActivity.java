package com.example.oneinone_alltoolsapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.appcompat.app.AppCompatActivity;

import com.example.oneinone_alltoolsapp.CommonTools.AspectRatio;
import com.example.oneinone_alltoolsapp.CommonTools.BodyFatCalculatorActivity;
import com.example.oneinone_alltoolsapp.CommonTools.Carloan;
import com.example.oneinone_alltoolsapp.CommonTools.ClothSize;
import com.example.oneinone_alltoolsapp.CommonTools.CompressImage;
import com.example.oneinone_alltoolsapp.CommonTools.Cryptography;
import com.example.oneinone_alltoolsapp.CommonTools.Discountcount;
import com.example.oneinone_alltoolsapp.CommonTools.ElectricityBills;
import com.example.oneinone_alltoolsapp.CommonTools.Fuelcost;
import com.example.oneinone_alltoolsapp.CommonTools.HRconverter.hrconverter;
import com.example.oneinone_alltoolsapp.CommonTools.HatSize;
import com.example.oneinone_alltoolsapp.CommonTools.Jewellery;
import com.example.oneinone_alltoolsapp.CommonTools.Morse;
import com.example.oneinone_alltoolsapp.CommonTools.Protractor;
import com.example.oneinone_alltoolsapp.CommonTools.RingSize;
import com.example.oneinone_alltoolsapp.CommonTools.Ruler;
import com.example.oneinone_alltoolsapp.CommonTools.ShoesSize;
import com.example.oneinone_alltoolsapp.CommonTools.StopWatch;
import com.example.oneinone_alltoolsapp.CommonTools.WireSize;
import com.example.oneinone_alltoolsapp.CommonTools.cookingMeasure;
import com.example.oneinone_alltoolsapp.EssentialTools.Age;
import com.example.oneinone_alltoolsapp.EssentialTools.BMI;
import com.example.oneinone_alltoolsapp.EssentialTools.BatteryInfo;
import com.example.oneinone_alltoolsapp.EssentialTools.Calculator;
import com.example.oneinone_alltoolsapp.EssentialTools.Calender;
import com.example.oneinone_alltoolsapp.EssentialTools.CompassActivity;
import com.example.oneinone_alltoolsapp.EssentialTools.Date;
import com.example.oneinone_alltoolsapp.EssentialTools.DeviceInfoActivity;
import com.example.oneinone_alltoolsapp.EssentialTools.EmergencyNumbers;
import com.example.oneinone_alltoolsapp.EssentialTools.FlashlightActivity;
import com.example.oneinone_alltoolsapp.EssentialTools.Password;
import com.example.oneinone_alltoolsapp.EssentialTools.qRcodeFragments.QrGenerator;
import com.example.oneinone_alltoolsapp.EssentialTools.Scanner;
import com.example.oneinone_alltoolsapp.EssentialTools.TemperatureActivity;
import com.example.oneinone_alltoolsapp.EssentialTools.pedometer;
import com.example.oneinone_alltoolsapp.FunwithOneinOne.Game_Starts;
import com.example.oneinone_alltoolsapp.FunwithOneinOne.Game_start_math;
import com.example.oneinone_alltoolsapp.FunwithOneinOne.TicTacActivity;
import com.example.oneinone_alltoolsapp.MaathsandFinance.AngleInfo;
import com.example.oneinone_alltoolsapp.MaathsandFinance.Ap;
import com.example.oneinone_alltoolsapp.MaathsandFinance.Area;
import com.example.oneinone_alltoolsapp.MaathsandFinance.AreaInfo;
import com.example.oneinone_alltoolsapp.MaathsandFinance.Combination;
import com.example.oneinone_alltoolsapp.MaathsandFinance.Counter;
import com.example.oneinone_alltoolsapp.MaathsandFinance.Cubic;
import com.example.oneinone_alltoolsapp.MaathsandFinance.Divide;
import com.example.oneinone_alltoolsapp.MaathsandFinance.Exponent;
import com.example.oneinone_alltoolsapp.MaathsandFinance.Gp;
import com.example.oneinone_alltoolsapp.MaathsandFinance.Hp;
import com.example.oneinone_alltoolsapp.MaathsandFinance.Linear;
import com.example.oneinone_alltoolsapp.MaathsandFinance.NumberBase;
import com.example.oneinone_alltoolsapp.MaathsandFinance.Permutation;
import com.example.oneinone_alltoolsapp.MaathsandFinance.Probability;
import com.example.oneinone_alltoolsapp.MaathsandFinance.Quadratic;
import com.example.oneinone_alltoolsapp.MaathsandFinance.Roots;
import com.example.oneinone_alltoolsapp.MaathsandFinance.ShapeInfo;
import com.example.oneinone_alltoolsapp.MaathsandFinance.Splitter;
import com.example.oneinone_alltoolsapp.MaathsandFinance.Temperature;
import com.example.oneinone_alltoolsapp.MaathsandFinance.UnitInfo;
import com.example.oneinone_alltoolsapp.MaathsandFinance.Volume;
import androidx.appcompat.app.AppCompatDelegate;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView animatedTextView = findViewById(R.id.animated_text_view);
        startTextViewAnimation(animatedTextView);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);


        Button compass = findViewById(R.id.compass);
        Button flash = findViewById(R.id.flashlight);
        Button calculator = findViewById(R.id.calculator);
        Button temperature = findViewById(R.id.temperature);
        Button deviceinfo = findViewById(R.id.deviceinfo);
        Button sos = findViewById(R.id.sos);
        Button qr = findViewById(R.id.Qr);
        Button number = findViewById(R.id.number);
        Button password = findViewById(R.id.password);
        Button batteryinfo = findViewById(R.id.battery);
        Button pedometer = findViewById(R.id.pedometer);
        Button bmi = findViewById(R.id.BMI);
        Button scanner = findViewById(R.id.scanner);
        Button fuelcost = findViewById(R.id.fuelcost);
        Button cooking = findViewById(R.id.cooking);
        Button shoes = findViewById(R.id.shoes);
        Button ruler = findViewById(R.id.ruler);
        Button protractor = findViewById(R.id.protractor);
        Button jewellery = findViewById(R.id.jewellery);
        Button cloth = findViewById(R.id.cloth);
        Button hat = findViewById(R.id.hat);
        Button ring = findViewById(R.id.ring);
        Button wire = findViewById(R.id.wire);
        Button compressimage = findViewById(R.id.compreeimage);
        Button hextorgb = findViewById(R.id.hextorgb);
        Button aspectratio = findViewById(R.id.aspectratio);
        Button energy = findViewById(R.id.energy);
        Button electricitybill = findViewById(R.id.electricitybill);
        Button morse = findViewById(R.id.morse);
        Button cryptography = findViewById(R.id.cryptography);
        Button stopwatch = findViewById(R.id.stopwatch);
        Button discount = findViewById(R.id.discount);
        Button carloan = findViewById(R.id.carloan);
        Button counting = findViewById(R.id.counting);
        Button linear = findViewById(R.id.linear);
        Button quadratic = findViewById(R.id.quadratics);
        Button cubic = findViewById(R.id.cubic);
        Button counter = findViewById(R.id.counter);
        Button splitter = findViewById(R.id.splitter);
        Button permutation = findViewById(R.id.permutation);
        Button combination = findViewById(R.id.combination);
        Button root = findViewById(R.id.root);
        Button area = findViewById(R.id.area);
        Button ap = findViewById(R.id.ap);
        Button hp = findViewById(R.id.hp);
        Button gp = findViewById(R.id.gp);
        Button probability = findViewById(R.id.probability);
        Button exponent = findViewById(R.id.exponentandpower);
        Button divisibility = findViewById(R.id.divisibility);
        Button volume = findViewById(R.id.volume);
        Button areainfo = findViewById(R.id.areainfo);
        Button unitinfo = findViewById(R.id.unit);
        Button shapeinfo = findViewById(R.id.shapesinfo);
        Button angleinfo = findViewById(R.id.rootsinfo);
        Button calender = findViewById(R.id.calender);
        Button date = findViewById(R.id.date);
        Button age = findViewById(R.id.age);
        Button quiz = findViewById(R.id.quiz);
        Button tictac = findViewById(R.id.tictac);
        Button math = findViewById(R.id.math);



        compass.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(MainActivity.this, CompassActivity.class);
                        startActivity(i);
                    }
                });

        deviceinfo.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(MainActivity.this, DeviceInfoActivity.class);
                        startActivity(i);
                    }
                });

        flash.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(MainActivity.this, FlashlightActivity.class);
                        startActivity(i);
                    }
                });
        temperature.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(MainActivity.this, TemperatureActivity.class);
                        startActivity(i);
                    }
                });
        calculator.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(MainActivity.this, Calculator.class);
                        startActivity(i);
                    }
                });
        fuelcost.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(MainActivity.this, Fuelcost.class);
                        startActivity(i);
                    }
                });
        batteryinfo.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(MainActivity.this, BatteryInfo.class);
                        startActivity(i);
                    }
                });
        pedometer.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(MainActivity.this, pedometer.class);
                        startActivity(i);
                    }
                });
    bmi.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(MainActivity.this, BMI.class);
                        startActivity(i);
                    }
                });
    password.setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(MainActivity.this, Password.class);
                    startActivity(i);
                }
            });
    calender.setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(MainActivity.this, Calender.class);
                    startActivity(i);
                }
            });
    date.setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(MainActivity.this, Date.class);
                    startActivity(i);
                }
            });
    age.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(MainActivity.this, Age.class);
                        startActivity(i);
                    }
                });
    discount.setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(MainActivity.this, Discountcount.class);
                    startActivity(i);
                }
            });
    carloan.setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(MainActivity.this, Carloan.class);
                    startActivity(i);
                }
            });

    jewellery.setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(MainActivity.this, Jewellery.class);
                    startActivity(i);
                }
            });
    electricitybill.setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(MainActivity.this, ElectricityBills.class);
                    startActivity(i);
                }
            });

    energy.setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(MainActivity.this, BodyFatCalculatorActivity.class);
                    startActivity(i);
                }
            });
    aspectratio.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(MainActivity.this, AspectRatio.class);
                        startActivity(i);
                    }
                });
    cloth.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(MainActivity.this, ClothSize.class);
                        startActivity(i);
                    }
                });
    shoes.setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(MainActivity.this, ShoesSize.class);
                    startActivity(i);
                }
            });
    hat.setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(MainActivity.this, HatSize.class);
                    startActivity(i);
                }
            });
    ring.setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(MainActivity.this, RingSize.class);
                    startActivity(i);
                }
            });
    wire.setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(MainActivity.this, WireSize.class);
                    startActivity(i);
                }
            });
    ruler.setOnClickListener(
        new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Ruler.class);
                startActivity(i);
            }
        });
    protractor.setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(MainActivity.this, Protractor.class);
                    startActivity(i);
                }
            });

    stopwatch.setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(MainActivity.this, StopWatch.class);
                    startActivity(i);
                }
            });

    morse.setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(MainActivity.this, Morse.class);
                    startActivity(i);
                }
            });
    cryptography.setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(MainActivity.this, Cryptography.class);
                    startActivity(i);
                }
            });
    counting.setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(MainActivity.this, Counter.class);
                    startActivity(i);
                }
            });
    linear.setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(MainActivity.this, Linear.class);
                    startActivity(i);
                }
            });
    quadratic.setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(MainActivity.this, Quadratic.class);
                    startActivity(i);
                }
            });
    cubic.setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(MainActivity.this, Cubic.class);
                    startActivity(i);
                }
            });
    number.setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(MainActivity.this, NumberBase.class);
                    startActivity(i);
                }
            });
    permutation.setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(MainActivity.this, Permutation.class);
                    startActivity(i);
                }
            });
    combination.setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(MainActivity.this, Combination.class);
                    startActivity(i);
                }
            });
    splitter.setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(MainActivity.this, Splitter.class);
                    startActivity(i);
                }
            });
    area.setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(MainActivity.this, Area.class);
                    startActivity(i);
                }
            });
        hextorgb.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(MainActivity.this, hrconverter.class);
                        startActivity(i);
                    }
                });
        root.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(MainActivity.this, Roots.class);
                        startActivity(i);
                    }
                });
        probability.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(MainActivity.this, Probability.class);
                        startActivity(i);
                    }
                });
        exponent.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(MainActivity.this, Exponent.class);
                        startActivity(i);
                    }
                });
        divisibility.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(MainActivity.this, Divide.class);
                        startActivity(i);
                    }
                });
        gp.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(MainActivity.this, Gp.class);
                        startActivity(i);
                    }
                });
        ap.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(MainActivity.this, Ap.class);
                        startActivity(i);
                    }
                });
        hp.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(MainActivity.this, Hp.class);
                        startActivity(i);
                    }
                });
        tictac.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(MainActivity.this, TicTacActivity.class);
                        startActivity(i);
                    }
                });
        sos.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(MainActivity.this, EmergencyNumbers.class);
                        startActivity(i);
                    }
                });

        qr.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(MainActivity.this, QrGenerator.class);
                        startActivity(i);
                    }
                });
        scanner.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(MainActivity.this, Scanner.class);
                        startActivity(i);
                    }
                });
        cooking.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(MainActivity.this, cookingMeasure.class);
                        startActivity(i);
                    }
                });
        compressimage.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(MainActivity.this, CompressImage.class);
                        startActivity(i);
                    }
                });
        counter.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(MainActivity.this, Temperature.class);
                        startActivity(i);
                    }
                });

        volume.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(MainActivity.this, Volume.class);
                        startActivity(i);
                    }
                });
        quiz.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(MainActivity.this, Game_Starts.class);
                        startActivity(i);
                    }
                });

        math.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(MainActivity.this, Game_start_math.class);
                        startActivity(i);
                    }
                });
        areainfo.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(MainActivity.this, AreaInfo.class);
                        startActivity(i);
                    }
                });
        unitinfo.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(MainActivity.this, UnitInfo.class);
                        startActivity(i);
                    }
                });
        shapeinfo.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(MainActivity.this, ShapeInfo.class);
                        startActivity(i);
                    }
                });
        angleinfo.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(MainActivity.this, AngleInfo.class);
                        startActivity(i);
                    }
                });
    }

    private void startTextViewAnimation(TextView textView) {
        textView.setVisibility(View.VISIBLE);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.slide_in);
        textView.startAnimation(animation);
    }


}