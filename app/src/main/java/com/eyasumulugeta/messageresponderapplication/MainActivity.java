package com.eyasumulugeta.messageresponderapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn;
    TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.button);
        btn.setOnClickListener(this);

        tv1 = findViewById(R.id.textView);
    }

    @Override
    public void onClick(View view) {
        Uri mSmsinboxQueryUri = Uri.parse("content://sms/inbox");
        Cursor cursor1 = getContentResolver().query(mSmsinboxQueryUri, new String[]{"_id", "thread_id", "address", "person", "date", "body", "type"}, null, null, null);
        startManagingCursor(cursor1);
        String[] columns = new String[]{"address", "person", "date", "body", "type"};
        if (cursor1.getCount() > 0) {
            String count = Integer.toString(cursor1.getCount());
            while (cursor1.moveToNext()) {
                String address = cursor1.getString(cursor1.getColumnIndex(columns[0]));
                if (address.equalsIgnoreCase("+251911600787")) { //put your number here
                    String name = cursor1.getString(cursor1.getColumnIndex(columns[1]));
                    String date = cursor1.getString(cursor1.getColumnIndex(columns[2]));
                    String body = cursor1.getString(cursor1.getColumnIndex(columns[3]));
                    String type = cursor1.getString(cursor1.getColumnIndex(columns[4]));
                    Log.d("*******", "body=" + body);

                    tv1.setText("name: "+name+"\n"+"date: "+ date+"\n"+"body: "+body+"\n"+"type: "+type+"\n");
                }
            }
        }
    }
}
