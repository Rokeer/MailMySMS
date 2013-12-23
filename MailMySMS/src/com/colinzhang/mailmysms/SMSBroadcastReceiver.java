package com.colinzhang.mailmysms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.SyncStateContract.Constants;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class SMSBroadcastReceiver extends BroadcastReceiver {
	public static final String ACTION = "android.provider.Telephony.SMS_RECEIVED"; 
	 @Override
		public void onReceive(Context context, Intent intent) {
			if (intent.getAction().equals(ACTION)) {
				StringBuffer SMSAddress = new StringBuffer();
				StringBuffer SMSContent = new StringBuffer();
				Bundle bundle = intent.getExtras();
				if (bundle != null) {
					Object[] pdusObjects = (Object[]) bundle.get("pdus");
					SmsMessage[] messages = new SmsMessage[pdusObjects.length];
					for (int i = 0; i < pdusObjects.length; i++) {
						messages[i] = SmsMessage
								.createFromPdu((byte[]) pdusObjects[i]);
					}
					for (SmsMessage message : messages) {
						SMSAddress.append(message.getDisplayOriginatingAddress());
						SMSContent.append(message.getDisplayMessageBody());
					}
				}
				System.out.println(SMSAddress.toString()+" said:\""+SMSContent+"\"");
				//Toast.makeText(context, SMSAddress.toString()+" said:\""+SMSContent.toString()+"\"", Toast.LENGTH_LONG).show();
				
				Intent intentSend = new Intent("com.google.android.gm.action.AUTO_SEND");  
				intentSend.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				intentSend.setType("plain/text"); 
				String[] reciver = new String[] { "me@colinzhang.com" };
				intentSend.putExtra(android.content.Intent.EXTRA_EMAIL, reciver);  
				intentSend.putExtra(android.content.Intent.EXTRA_SUBJECT, SMSAddress.toString());  
				intentSend.putExtra(android.content.Intent.EXTRA_TEXT, SMSContent.toString()); 
				context.startActivity(intentSend);
			}
			
			
		}

}
