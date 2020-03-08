package com.example.quizapplication.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.quizapplication.R;
import com.example.quizapplication.models.RatingTableModel;

import java.util.List;

public class RatingAdapter extends ArrayAdapter<RatingTableModel> {

    private Context mContext;
    int mResource ;

    public RatingAdapter(@NonNull Context context, int resource, @NonNull List<RatingTableModel> objects) {
        super(context, resource, objects);

        mContext = context;
        mResource = resource;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        String login = getItem(position).getLogin();
        Double score = getItem(position).getScore();
        Integer amounnt = getItem(position).getAmount();

        RatingTableModel model = new RatingTableModel(login, score, amounnt);

        LayoutInflater inflater =  LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false );


        TextView txLogin  = (TextView) convertView.findViewById(R.id.tvLogin);
        TextView txScore  = (TextView) convertView.findViewById(R.id.tvScore);
        TextView txAmount  = (TextView) convertView.findViewById(R.id.tvAmountTest);


        txLogin.setText(login);
        txScore.setText(score.toString());
        txAmount.setText(amounnt.toString());


        return convertView;

    }

}
