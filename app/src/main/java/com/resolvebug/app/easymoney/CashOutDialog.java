package com.resolvebug.app.easymoney;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

public class CashOutDialog extends AppCompatDialogFragment {

    private TextInputEditText appliedReferralCode;
    private CashOutDialogListener cashOutDialogListener;
    private Spinner spinner;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.cashout_dialog, null);
        builder.setView(view)
                .setTitle("")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Cash Out", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String paypalEmail = appliedReferralCode.getText().toString();
                        cashOutDialogListener.fetchPaypalEmail(paypalEmail);
                    }
                });

        appliedReferralCode = view.findViewById(R.id.appliedReferralCode);
        spinner = view.findViewById(R.id.withdrawAmounts);

        ArrayList<String> withdrawAmounts = new ArrayList<>();
        withdrawAmounts.add("Not Eligible");
        withdrawAmounts.add("$ 1");
        withdrawAmounts.add("$ 5");
        withdrawAmounts.add("$ 10");
        withdrawAmounts.add("$ 15");
        withdrawAmounts.add("$ 20");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, withdrawAmounts);
        spinner.setAdapter(adapter);

        return builder.create();
    }

    public interface CashOutDialogListener {
        void fetchPaypalEmail(String paypalEmail);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            cashOutDialogListener = (CashOutDialogListener) context;
        } catch (ClassCastException cce) {
            throw new ClassCastException((context.toString() + "CashOutDialog - ClassCastException"));
        }
    }
}
