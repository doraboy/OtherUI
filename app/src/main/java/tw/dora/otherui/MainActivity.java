package tw.dora.otherui;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.widget.ViewFlipper;

import java.util.Calendar;

import co.ceryle.segmentedbutton.SegmentedButtonGroup;

public class MainActivity extends AppCompatActivity {
    private CheckBox opt1;
    private RadioGroup years;
    //private RadioButton year1,year2,year3,year4;
    private ToggleButton tbtn;
    private SegmentedButtonGroup sbg;
    private TextView newdate;
    private int yy, mm, dd;
    private ViewFlipper vf;
    private boolean vf_Vertical=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        opt1 = findViewById(R.id.opt1);
        opt1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(MainActivity.this, "OK", Toast.LENGTH_SHORT)
                            .show();
                }
            }
        });


        years = findViewById(R.id.years);

        years.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.year1:
                        Toast.makeText(MainActivity.this, "0-19", Toast.LENGTH_SHORT)
                                .show();
                        break;
                    case R.id.year2:
                        Toast.makeText(MainActivity.this, "20~39", Toast.LENGTH_SHORT)
                                .show();
                        break;
                    case R.id.year3:
                        Toast.makeText(MainActivity.this, "40~59", Toast.LENGTH_SHORT)
                                .show();
                        break;
                    case R.id.year4:
                        Toast.makeText(MainActivity.this, "60+", Toast.LENGTH_SHORT)
                                .show();
                        break;
                }
            }
        });

        tbtn = findViewById(R.id.tbtn);
        tbtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(MainActivity.this,
                        (isChecked ? "開" : "關"),
                        Toast.LENGTH_SHORT)
                        .show();
            }
        });

        sbg = findViewById(R.id.sbg);
        sbg.setOnPositionChangedListener(new SegmentedButtonGroup.OnPositionChangedListener() {
            @Override
            public void onPositionChanged(int position) {
                Toast.makeText(MainActivity.this,
                        "pos: " + position,
                        Toast.LENGTH_SHORT)
                        .show();

            }
        });

        newdate = findViewById(R.id.newdate);

        vf = findViewById(R.id.vf);
        View v0 = vf.getChildAt(0);
        View v1 = vf.getChildAt(1);
        View v2 = vf.getChildAt(2);
        View v3 = vf.getChildAt(3);
        View v4 = vf.getChildAt(4);
        View v5 = vf.getChildAt(5);
        View v6 = vf.getChildAt(6);
        View v7 = vf.getChildAt(7);
        View v8 = vf.getChildAt(8);
        View v9 = vf.getChildAt(9);

        FlipperOnClick listener = new FlipperOnClick();

        v0.setOnClickListener(listener);
        v1.setOnClickListener(listener);
        v2.setOnClickListener(listener);
        v3.setOnClickListener(listener);
        v4.setOnClickListener(listener);
        v5.setOnClickListener(listener);
        v6.setOnClickListener(listener);
        v7.setOnClickListener(listener);
        v8.setOnClickListener(listener);
        v9.setOnClickListener(listener);
    }

    public void test1(View view) {
        if(vf_Vertical){
            vf.setInAnimation(this,R.anim.leftright_show);
            vf.setOutAnimation(this,R.anim.leftright_hide);
        }else{
            vf.setInAnimation(this,R.anim.updown_show);
            vf.setOutAnimation(this,R.anim.updown_hide);
        }
        vf_Vertical = !vf_Vertical;

    }

    private class FlipperOnClick implements View.OnClickListener{


        @Override
        public void onClick(View v) {
            vf.showNext();
        }
    }

    public void chDate(View view) {
        Calendar cal = Calendar.getInstance();
        if(yy==0){//第一次執行預設值為現在日期
            yy = cal.get(Calendar.YEAR);
            //英文月份從0(即一月)開始算
            mm = cal.get(Calendar.MONTH);
            dd = cal.get(Calendar.DAY_OF_MONTH);
        }


        new DatePickerDialog(this,
                android.app.AlertDialog.THEME_DEVICE_DEFAULT_LIGHT,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        newdate.setText(year+"-"+(month+1)+"-"+dayOfMonth);
                        yy = year;
                        mm = month;
                        dd = dayOfMonth;
                    }
                },
                yy, mm, dd).show();

    }
}
