package HaLePo.yimulueasy;

import android.content.Intent;
import android.net.Uri;
import androidx.appcompat.app.AppCompatActivity;


class java extends AppCompatActivity {

    Uri ussdToCallableUri(String ussd) {
        //convert a string to callable URI, whatever that means... :D

        String uriString = "";

        if (!ussd.startsWith("tel:"))
            uriString += "tel:";

        for (char c : ussd.toCharArray()) {

            if (c == '#')
                uriString += Uri.encode("#");
            else
                uriString += c;
        }
        return Uri.parse(uriString);
    }

     String call(String confirmationCode, String numbertocall) {
        //calls the number or the USSD code

        String returnstring = "success";

        if (confirmationCode.equals("1")) {
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(ussdToCallableUri(numbertocall));
            try {
                startActivity(intent);
            } catch (SecurityException e) {
                e.printStackTrace();
            }
        }
        else if (confirmationCode.equals("2")) returnstring = "cancel";
        else returnstring = "invalid";
        return returnstring ;
    }
}