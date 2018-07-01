package com.application.homeapp.firstaws;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class SmsSender {


    public void sendMessage(int number, String toPhoneNumber) {
        final String ACCOUNT_SID =
                "AC0123516d7ee1173094bfb6820aaef5ad";
        final String AUTH_TOKEN =
                "148a0fef01c244a48525d265759074b4";

        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        for (int i = 0; i < number; i++) {

            String messageBody = "Number "+ i+" \n"+ java.time.Clock.systemUTC().instant().toString();
            Message message = Message.creator(new PhoneNumber(toPhoneNumber), new PhoneNumber("+13372261993"), messageBody).create();
            System.out.println(message.getSid());
        }
    }
}