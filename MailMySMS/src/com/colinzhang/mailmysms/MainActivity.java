package com.colinzhang.mailmysms;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //sendEmail("÷–Œƒ≤‚ ‘","ºÃ–¯≤‚ ‘");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    public void sendEmail(String subject, String body) {
		Intent intent = new Intent("com.google.android.gm.action.AUTO_SEND");  
		intent.setType("plain/text"); 
		String[] reciver = new String[] { "me@colinzhang.com" };
		intent.putExtra(android.content.Intent.EXTRA_EMAIL, reciver);  
		intent.putExtra(android.content.Intent.EXTRA_SUBJECT, subject);  
		intent.putExtra(android.content.Intent.EXTRA_TEXT, body); 
		startActivity(intent);
    }
    
   
    
}
