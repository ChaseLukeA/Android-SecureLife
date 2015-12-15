package edu.cvtc.android.securelife.Controller;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.View;
import android.view.ViewGroup;

import edu.cvtc.android.securelife.Model.Account;
import edu.cvtc.android.securelife.Model.AccountTable;
import edu.cvtc.android.securelife.View.AccountSummaryView;
import edu.cvtc.android.securelife.View.AccountSummaryView.OnAccountChangeListener;


public class AccountCursorAdapter extends CursorAdapter {

    private OnAccountChangeListener listener;


    public AccountCursorAdapter(Context context, Cursor accountCursor, int flags) {

        super(context, accountCursor, flags);

        this.listener = null;
    }


    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {

        Account account = initAccountFromDatabaseRecord(cursor);

        AccountSummaryView summaryView = new AccountSummaryView(context, account);
        summaryView.setOnAccountChangeListener(this.listener);

        return summaryView;
    }


    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        Account account = initAccountFromDatabaseRecord(cursor);

        ((AccountSummaryView) view).setOnAccountChangeListener(null);
        ((AccountSummaryView) view).setAccount(account);
        ((AccountSummaryView) view).setOnAccountChangeListener(this.listener);
    }


    private Account initAccountFromDatabaseRecord(Cursor cursor) {

        return new Account(
                cursor.getInt(AccountTable.ACCOUNT_ID_COL),
                cursor.getString(AccountTable.FRIENDLY_NAME_COL),
                cursor.getString(AccountTable.TYPE_COL),
                cursor.getString(AccountTable.URL_COL),
                cursor.getString(AccountTable.EMAIL_COL),
                cursor.getString(AccountTable.USERNAME_COL),
                cursor.getString(AccountTable.PASSWORD_COL),
                cursor.getString(AccountTable.SECRET_QUESTION_COL),
                cursor.getString(AccountTable.SECRET_ANSWER_COL),
                cursor.getString(AccountTable.NOTES_COL),
                cursor.getString(AccountTable.BUSINESS_NAME_COL),
                cursor.getString(AccountTable.BUSINESS_ADDRESS_COL),
                cursor.getString(AccountTable.BUSINESS_CITY_COL),
                cursor.getString(AccountTable.BUSINESS_STATE_COL),
                cursor.getString(AccountTable.BUSINESS_ZIP_COL),
                cursor.getString(AccountTable.BUSINESS_PHONE_NUMBER_COL),
                cursor.getString(AccountTable.BUSINESS_CONTACT_COL),
                cursor.getString(AccountTable.ACCT_NUMBER_COL),
                cursor.getString(AccountTable.ROUTING_NUMBER_COL),
                cursor.getString(AccountTable.EXPIRATION_DATE_COL),
                cursor.getString(AccountTable.CVV_NUMBER_COL),
                cursor.getString(AccountTable.AVATAR_COL),
                cursor.getString(AccountTable.IMAGE_URI_COL),
                cursor.getString(AccountTable.SERIAL_NUMBER_COL),
                cursor.getString(AccountTable.STORAGE_SIZE_COL)
        );
    }

}
