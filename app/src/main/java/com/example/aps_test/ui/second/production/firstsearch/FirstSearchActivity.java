package com.example.aps_test.ui.second.production.firstsearch;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.APS_test.R;
import com.example.aps_test.sharedPreferences.SP;
import com.example.aps_test.ui.second.production.firstsearch.search_schedule.SearchScheduleActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class FirstSearchActivity extends AppCompatActivity implements FirstSearchContract.view{
    private Button searchButton;;
    private ImageView backButton;
    private TextView dot1TextView,dot2TextView,dot3TextView,NameTextView;
    private EditText dataEditText, numEditText, personEditText;
    private Spinner listSpinner;
    private SP sp;
    private Context context = this;

    private FirstSearchPresenter firstSearchPresenter;
    private String[] items ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_first_search);

        Intent intent = getIntent();

        backButton = findViewById(R.id.firstsearch_back_iv);
        searchButton = findViewById(R.id.firstsearch_search_btn);
        dot1TextView = findViewById(R.id.firstsearch_dot1_tv);
        dot2TextView = findViewById(R.id.firstsearch_dot2_tv);
        dot3TextView = findViewById(R.id.firstsearch_dot3_tv);
        dataEditText = findViewById(R.id.firstsearch_data_et);
        numEditText = findViewById(R.id.firstsearch_num_et);
        personEditText = findViewById(R.id.firstsearch_person_et);
        listSpinner = findViewById(R.id.firstsearch_list_sp);
        NameTextView = findViewById(R.id.firstsearch_name_tv);

        sp = new SP(this);
        NameTextView.setText(sp.loadName());

        firstSearchPresenter =  new FirstSearchPresenter(this,this);
        //返回
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //確定跳轉
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FirstSearchActivity.this, SearchScheduleActivity.class);
                startActivity(intent);
            }
        });

        //Spinner
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,new String[]{"點焊"});
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_shape);
        listSpinner.setAdapter(adapter);

        //dialog
        //日期
        dot1TextView.setOnClickListener((v) -> {
            Calendar calendar = Calendar.getInstance();
            DatePickerDialog dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                // 當日期選擇完畢並按下"OK"按鈕 的事件觸發處理
                @Override
                public void onDateSet(DatePicker datePicker, int y, int m, int d) {
                    StringBuilder result = new StringBuilder();
                    result.append(y).append("-").append(m+1).append("-").append(d);
                    dataEditText.setText(result);
                }
            },calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
            dialog.show();
        });

        //輸入單號
        dot2TextView.setOnClickListener((v) -> {
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            final EditText editText = new EditText(this);
            dialog.setTitle("輸入單號");
            //設置列表字串文字
            String[] items = {"列表1", "列表2", "列表3", "列表4","列表5"};

            // 設置item點擊事件處理
            dialog.setItems(items, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Toast.makeText(context, "你選擇" + items[i], Toast.LENGTH_SHORT).show();
                }
            });

            //設置左邊按鈕和點擊事件
            dialog.setNegativeButton("關閉", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    // TODO: ...
                }
            });
            //顯示Dialog
            dialog.show();
        });

        dot3TextView.setOnClickListener((v) -> {
            Get_customer_name(personEditText.getText().toString());

        });
    }

    @Override
    public void Get_customer_name(String person){
        Log.e("PERPP",person);
        firstSearchPresenter.Get_customerName(person,sp.loadToken());
    }

    @Override
    public void customer_name(String[] customerNames){
        Log.d("tag", "customer_name: "+customerNames);
        items = customerNames;

        dot3TextView.setOnClickListener((v) -> {
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            final EditText editText2 = new EditText(this);
            dialog.setTitle("輸入客戶");
            //設置列表字串文字
            customer_name(items);
            // 設置item點擊事件處理
            dialog.setItems(items, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Toast.makeText(context, "你選擇" + items[i], Toast.LENGTH_SHORT).show();
                }
            });
            //設置左邊按鈕和點擊事件
            dialog.setNegativeButton("關閉", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    // TODO: ...
                }
            });
            //顯示Dialog
            dialog.show();
        });
    }
}