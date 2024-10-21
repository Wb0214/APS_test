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
    String[] customerName,soId;

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
                sp.saveSoId(numEditText.getText().toString());
                sp.saveCustomerName(personEditText.getText().toString());

                Intent intent = new Intent(FirstSearchActivity.this, SearchScheduleActivity.class);
                startActivity(intent);
            }
        });

        //Spinner
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,new String[]{"點焊"});
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_shape);
        listSpinner.setAdapter(adapter);

        //日期
        dot1TextView.setOnClickListener((v) -> {
            Date();
        });

        //so_id
        dot2TextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sp.saveSoId(numEditText.getText().toString());
                Get_so_id(numEditText.getText().toString());
            }
        });

        //customer_name
        dot3TextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sp.saveCustomerName(personEditText.getText().toString());
                Get_customer_name(personEditText.getText().toString());
            }
        });

    }
/////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void Date(){
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
    }

////////////////////////////////////////////////////////////////////////////////////
    //customer_name
    @Override
    public void Get_customer_name(String person){
        Log.d("tag", "Get_customer_name: "+person);
        firstSearchPresenter.Get_customerName(person,sp.loadToken());
    }
    @Override
    public void customer_name(String[] customerNames){
        Log.d("tag", "customer_name: "+customerNames);
        showCustomerName(customerNames);
    }
    @Override
    public void showCustomerName(String[] customerNames){
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("客戶名稱");
        customerName = customerNames;

        // 設置item點擊事件處理
        dialog.setItems(customerName, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                personEditText.setText(customerName[i]);
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
    }
/////////////////////////////////////////////////////////////////////////////////////////
    //so_id
    @Override
    public void Get_so_id(String id) {
        firstSearchPresenter.Get_soId(id, sp.loadToken());
    }
    @Override
    public void so_id(String[] so_id){
        Log.d("tag", "so_id: "+so_id);
        showSoId(so_id);
    }
    @Override
    public void showSoId(String[] so_id){
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("訂單單號");

        soId = so_id;
        // 設置item點擊事件處理
        dialog.setItems(soId, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                numEditText.setText(soId[i]);
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
    }
}
