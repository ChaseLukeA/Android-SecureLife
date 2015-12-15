package edu.cvtc.android.securelife.View;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import edu.cvtc.android.securelife.Model.Account;
import edu.cvtc.android.securelife.R;


public class AccountSummaryView extends LinearLayout {

    private TextView summaryNameTextView,
                     summaryTypeTextView,
                     summaryEmailTextView,
                     summaryUsernameTextView;

    private Account account;

    private OnAccountChangeListener onAccountChangeListener;


    public AccountSummaryView(Context context, Account account) {

        super(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.layout_account_summary_view, this, true);

        this.summaryNameTextView = (TextView) findViewById(R.id.account_summary_friendly_name_textview);
        this.summaryTypeTextView = (TextView) findViewById(R.id.account_summary_type_textview);
        this.summaryEmailTextView = (TextView) findViewById(R.id.account_summary_email_textview);
        this.summaryUsernameTextView = (TextView) findViewById(R.id.account_summary_username_textview);

        setAccount(account);
    }


    public Account getAccount() {

        return account;
    }


    public void setAccount(final Account account) {

        this.account = account;

        this.summaryNameTextView.setText(account.getFriendlyName());
        this.summaryTypeTextView.setText(account.getType());
        this.summaryEmailTextView.setText(account.getEmail());
        this.summaryUsernameTextView.setText(account.getUsername());

        requestLayout();
    }


    public static interface OnAccountChangeListener {

        public void onAccountChanged(AccountSummaryView view, Account account);
    }


    public void setOnAccountChangeListener(OnAccountChangeListener listener) {

        this.onAccountChangeListener = listener;
    }


    protected void notifyOnAccountChangeListener() {

        if (null != onAccountChangeListener) {

            onAccountChangeListener.onAccountChanged(this, account);
        }
    }

}
