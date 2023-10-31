package com.cst3104.lab5;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;

public class Message {
    private String text;
    private boolean isSent;

    public Message(String text, boolean isSent) {
        this.text = text;
        this.isSent = isSent;
    }

    public String getText() {
        return text;
    }

    public boolean isSent() {
        return isSent;
    }

    public static class MessageAdapter extends BaseAdapter {
        private Context context;
        private ArrayList<Message> messages;

        public MessageAdapter(Context context, ArrayList<Message> messages) {
            this.context = context;
            this.messages = messages;
        }

        @Override
        public int getCount() {
            return messages.size();
        }

        @Override
        public Object getItem(int position) {
            return messages.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.message_row, parent, false);
            }

            TextView senderText = convertView.findViewById(R.id.senderText);
            TextView messageText = convertView.findViewById(R.id.messageText);

            Message message = messages.get(position);
            senderText.setText(message.isSent() ? "Sender:" : "Receiver:");
            messageText.setText(message.getText());

            return convertView;
        }

        public void add(Message message) {
        }

        public void removeMessage(int position) {
        }
    }
}


