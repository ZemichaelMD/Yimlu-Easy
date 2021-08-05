package HaLePo.yimulueasy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    private static final int MY_PERMISSION_REQUEST = 1;

    private String main = "";
    private String hint = "";
    private String coder;
    private String code;
    private String status = "1";
    private String number = "0";
    private String amount = "0";
    private String ussd = "0";

    Button b_1, b_2, b_3,b_4,b_5,b_6,b_7,b_8,b_9,b_0,b_clear,b_next;

    TextView t_main;
    TextView t_hint;
    TextView t_number;
    TextView t_amount;
    TextView t_willsell;
    TextView t_to;
    TextView t_code;

    java callCode = new java();

    private void resetMain(){
        main = "";
        t_main.setText(main);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Hide the title
        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
        getSupportActionBar().hide(); //hide the title bar

        setContentView(R.layout.activity_main);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CALL_PHONE)){
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, MY_PERMISSION_REQUEST);
            }
            else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, MY_PERMISSION_REQUEST);
            }
        }

        t_main = findViewById(R.id.t_main);
        t_hint =  findViewById(R.id.t_hint);
        t_number =  findViewById(R.id.t_number);
        t_amount =  findViewById(R.id.t_amount);
        t_willsell =  findViewById(R.id.t_willsell);
        t_to = findViewById(R.id.t_to);
        t_code = findViewById(R.id.t_code);

        b_1 = (Button) findViewById(R.id.b_1);
        b_2 = (Button) findViewById(R.id.b_2);
        b_3 = (Button) findViewById(R.id.b_3);
        b_4 = (Button) findViewById(R.id.b_4);
        b_5 = (Button) findViewById(R.id.b_5);
        b_6 = (Button) findViewById(R.id.b_6);
        b_7 = (Button) findViewById(R.id.b_7);
        b_8 = (Button) findViewById(R.id.b_8);
        b_9 = (Button) findViewById(R.id.b_9);
        b_0 = (Button) findViewById(R.id.b_0);
        b_clear = (Button) findViewById(R.id.b_cancel);
        b_next = (Button) findViewById(R.id.b_call);

        b_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                main = main + "1";
                t_main.setText(main);
            }
        });
        b_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                main = main + "2";
                t_main.setText(main);
            }
        });
        b_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                main = main + "3";
                t_main.setText(main);
            }
        });
        b_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                main = main + "4";
                t_main.setText(main);
            }
        });
        b_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                main = main + "5";
                t_main.setText(main);
            }
        });
        b_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                main = main + "6";
                t_main.setText(main);
            }
        });
        b_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                main = main + "7";
                t_main.setText(main);
            }
        });
        b_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                main = main + "8";
                t_main.setText(main);
            }
        });
        b_9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                main = main + "9";
                t_main.setText(main);
            }
        });
        b_0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                main = main + "0";
                t_main.setText(main);
            }
        });
        b_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                main = "";
                t_main.setText(main);
            }
        });

        b_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //if the code is not four characters...
                if (t_main.length()!=4 && (status == "1"|| status == "2")){
                    hint = "wrong code format. Please try again..";
                    resetMain();
                    t_hint.setText(hint);
                    status = "2";
                }
                else if (t_main.length()==4 && (status =="2"|| status == "1")){

                    //Code registered -- Status 3
                    status = "3";
                    code = main;
                    coder = "Code registered";
                    hint = "Enter amount to sell...";
                    t_main.setHint("Birr");
                    t_hint.setText(hint);
                    t_code.setText(coder);
                    resetMain();
                    }
                else if (status == "3") {

                    //Amount taken -- Status 4
                    t_willsell.setVisibility(View.VISIBLE);
                    t_amount.setVisibility(View.VISIBLE);
                    t_amount.setText(main);
                    amount = main;
                    resetMain();
                    t_main.setHint("Number");
                    hint = "Enter the Phone Number...";
                    t_hint.setText(hint);
                    status="4";
                }
                else if (status == "4"){
                    if (t_main.length()!=10){
                        resetMain();
                        hint = "Incorrect Phone Number Format...";
                        t_hint.setText(hint);
                    }
                    else {

                        //number taken -- Status 4
                        t_to.setVisibility(View.VISIBLE);
                        t_number.setVisibility(View.VISIBLE);
                        t_number.setText(main);
                        number = main;
                        resetMain();
                        t_main.setHint("Send?");
                        hint = "Type '1' to send, type '2' to start over...";
                        t_hint.setText(hint);
                        status = "5";
                    }
                }
                else if (status == "5") {

                    //Ready to call -- Status 5
                    String confirmationcode = "0", ussdToCall = "*922*2*"+number+"*"+amount+"*"+code+"*1#"+"" , callstatus = "";
                    main = t_main.getText().toString();

                    System.out.println(ussdToCall);

                    main = t_main.getText().toString();
                    confirmationcode = main;

                    callCode.call(confirmationcode,ussdToCall);
                    main = "";
                if (callCode.call(confirmationcode,ussdToCall).equals("success")){
                    resetMain();}
                else if (callCode.call(confirmationcode,ussdToCall).equals(("invalid")))
                    resetMain();
                    hint = "invalid input";
                    t_hint.setText(hint);
                    System.out.println(callCode.call(confirmationcode,ussdToCall));
                    }
                }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode){
            case MY_PERMISSION_REQUEST: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED){
                        Toast.makeText(MainActivity.this, "Permission Granted!!", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(MainActivity.this, "Permission Granting Failed!", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            }
        }
    }
}
