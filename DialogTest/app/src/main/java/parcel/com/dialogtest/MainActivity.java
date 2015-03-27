package parcel.com.dialogtest;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity implements IConfirmDialogCompliant {

    TextView pinNumber;
    int enteredpin;
    final int correctpin = 1234;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pinNumber = (TextView) findViewById(R.id.pinnumber);
        TestInputDialog t = new TestInputDialog("Enter your 4 digit pin",this);
        t.show(getFragmentManager(),null);
    }

    private boolean checkPin() {
        return enteredpin == correctpin;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void doYesConfirmClick(int pin) {
        enteredpin = pin;
        pinNumber.setText((CharSequence) String.valueOf(pin));
        if(checkPin())
        {
            Toast.makeText(getBaseContext(),"Correct pin entered",Toast.LENGTH_LONG).show();
        }
        else
        {
            TestInputDialog tnew = new TestInputDialog("Incorrect pin entered try again",this);
            tnew.show(getFragmentManager(),null);
        }
    }

    @Override
    public void doNoConfirmClick(int pin) {
        enteredpin = pin;
        pinNumber.setText((CharSequence) String.valueOf(pin));
    }
}
