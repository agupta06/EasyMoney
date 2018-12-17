package com.resolvebug.app.easymoney;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public class ReferralDialog extends AppCompatDialogFragment {

    private TextView referralCode;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.referral_dialog, null);
        Bundle bundle = this.getArguments();
        final String code = bundle.getString("referralCode");
        builder.setView(view)
                .setTitle("")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .setPositiveButton("Refer", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        referApp(code);
                    }
                });
        referralCode = view.findViewById(R.id.referralCode);
        referralCode.setText(code);
        return builder.create();
    }

    private void referApp(String referralCode) {
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("text/plain");
        share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
        share.putExtra(Intent.EXTRA_SUBJECT, "Earn Money");
        share.putExtra(Intent.EXTRA_TEXT, "Hey,\n\nEarn Money is a fast, easy and simple app that I use to earn money anytime, anywhere.\n\nUse Referral Code : " + referralCode + "\n\nGet it for free at : http://play.google.com/store/apps/details?id=" + getActivity().getPackageName());
        startActivity(Intent.createChooser(share, "Share Bahi Khata with friends"));
    }
}
