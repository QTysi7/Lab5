package com.cst3104.lab5;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import java.util.ArrayList;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.AdapterView.OnItemLongClickListener;

public class MainActivity extends Activity {
    private EditText editTextMessage;
    private ListView messageListView;
    private Message.MessageAdapter messageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextMessage = findViewById(R.id.editTextMessage);
        messageListView = findViewById(R.id.messageListView);
        messageAdapter = new Message.MessageAdapter(this, new ArrayList<Message>());
        messageListView.setAdapter(messageAdapter);

        Button sendButton = findViewById(R.id.sendButton);
        Button receiveButton = findViewById(R.id.receiveButton);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String messageText = editTextMessage.getText().toString();
                messageAdapter.add(new Message(messageText, true));
                editTextMessage.getText().clear();
            }
        });

        receiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String messageText = editTextMessage.getText().toString();
                messageAdapter.add(new Message(messageText, false));
                editTextMessage.getText().clear();
            }
        });

        // Set an onItemLongClick listener for the ListView
        messageListView.setOnItemLongClickListener(new OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                // Display an AlertDialog
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                alertDialogBuilder.setTitle("Do you want to delete this?");
                alertDialogBuilder.setMessage("The selected row is: " + position);

                alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Delete the item at the specified row and update the list
                        messageAdapter.removeMessage(position);
                    }
                });

                alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Dismiss the dialog
                        dialog.dismiss();
                    }
                });

                alertDialogBuilder.show();
                return true; // Indicate that the long-click event has been handled
            }
        });
    }
}





