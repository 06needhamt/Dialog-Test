package parcel.com.dialogtest;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Tom on 26/03/2015.
 */
public class TestInputDialog extends DialogFragment implements DialogInterface{

    TextView input;
    String Message;
    IConfirmDialogCompliant caller;

    public TestInputDialog()
    {

    }

    public TestInputDialog(String Message)
    {
        this.Message = Message;
    }

    public TestInputDialog(IConfirmDialogCompliant caller)
    {
        this.caller = caller;
    }

    public TestInputDialog(String message, IConfirmDialogCompliant caller)
    {
        this.Message = message;
        this.caller = caller;
    }
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        input = new EditText(getActivity());
        input.setTextColor(getActivity().getResources().getColor(R.color.Black));
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Enter your 4 digit pin code");
        builder.setMessage(Message);
        builder.setView(input);
        builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try
                {
                    if(input.getText().toString().length() != 4)
                    {
                        throw new Exception("Invalid Pin Length");
                    }
                    //int temp = Integer.parseInt((String) input.getText());
                    int temp = Integer.parseInt(input.getText().toString());
                    caller.doYesConfirmClick(temp);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                    Toast.makeText(getActivity().getBaseContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try
                {
                    cancel();
                    if(input.getText().toString().length() != 4)
                    {
                        throw new Exception("Invalid Pin Length");
                    }
                    //int temp = Integer.parseInt((String) input.getText());
                    int temp = Integer.parseInt(input.getText().toString());
                    caller.doNoConfirmClick(temp);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                    Toast.makeText(getActivity().getBaseContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        });
        return builder.create();
    }
    @Override
    public void cancel() {
        input.setText("0000");
    }
}
