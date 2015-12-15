package edu.cvtc.android.securelife.View;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import edu.cvtc.android.securelife.Controller.AccountFieldAdapter;
import edu.cvtc.android.securelife.Model.Account;
import edu.cvtc.android.securelife.Model.AccountContentProvider;
import edu.cvtc.android.securelife.Model.AccountField;
import edu.cvtc.android.securelife.Model.AccountTable;
import edu.cvtc.android.securelife.R;
import edu.cvtc.android.securelife.Helper.AccountFieldViewHelper;


public class AccountDetailActivity extends AppCompatActivity {

    private Account account;

    private int detailType;

    private ListView accountFieldListView;

    private ArrayList<AccountField> accountFieldList = new ArrayList<>();

    private AccountFieldAdapter accountFieldAdapter;

    public static final int DETAIL_TYPE_DETAIL = 0,
                            DETAIL_TYPE_ADD = 1,
                            DETAIL_TYPE_EDIT = 2;

    private boolean editable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_account_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        accountFieldAdapter = new AccountFieldAdapter(this, accountFieldList);

        account = getIntent().getParcelableExtra("account");
        if (null == account) {
            account = new Account();
        }

        detailType = getIntent().getIntExtra("detailType", DETAIL_TYPE_DETAIL);

        initDetailType();
        initAccountFieldsListView();
        initAccountFields(editable);
    }


    private void initDetailType() {

        switch (detailType) {
            case DETAIL_TYPE_ADD:
                editable = true;
                this.setTitle(getString(R.string.activity_detail_type_add_title));
                break;
            case DETAIL_TYPE_EDIT:
                editable = true;
                this.setTitle(getString(R.string.activity_detail_type_edit_title));
                break;
            case DETAIL_TYPE_DETAIL:
                editable = false;
                this.setTitle(getString(R.string.activity_detail_type_detail_title));
                break;
            default:
                // NOTES: only throwing this exception here, once, since if the detailType value
                // passed in to the AccountDetailActivity class, this app will stop here anyway;
                // no further exceptions related to detailType will be thrown in this class
                throw new IllegalArgumentException(getString(R.string.activity_detail_detail_type_exception), null);
        }
    }


    private void initAccountFieldsListView() {

        accountFieldListView = (ListView) findViewById(R.id.account_fields_listview);
        accountFieldListView.setAdapter(accountFieldAdapter);
    }


    private void initAccountFields(final boolean editable) {

        Log.d("accountId: ", String.valueOf(account.getAccountId()));
        // NOTES: set field to start at "1" because I do not want the accountId to be created/shown
        // in an AccountFieldView list, it's used for back-end database purposes only;
        // set condition to "...length - 1" because Parcelable.Creator is also a field of an
        // Account that does not need an AccountFieldView created for either
        for (int field = 1; field < account.getClass().getDeclaredFields().length - 1; field++) {

            final AccountField accountField = AccountFieldViewHelper.setValuesFromAccount(field, account);

            accountField.setEditable(editable);

            // NOTES: only showing account fields that are populated in detailed view
            if (!(detailType == DETAIL_TYPE_DETAIL && accountField.getValue().isEmpty())) {
                accountFieldList.add(accountField);
            }
        }
        accountFieldAdapter.notifyDataSetChanged();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        final MenuInflater inflater = getMenuInflater();

        if (detailType == DETAIL_TYPE_DETAIL) {

            inflater.inflate(R.menu.menu_account_detail, menu);
        }
        else {  // detailType "add" or "edit"

            inflater.inflate(R.menu.menu_account_maintenance, menu);
        }

        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        final Intent intent;

        switch (item.getItemId()) {
            case R.id.account_detail_edit:
                reloadActivity(DETAIL_TYPE_EDIT);
                return true;
            case R.id.account_detail_delete:
                confirmAction(getString(R.string.activity_detail_delete_confirm_type), getString(R.string.action_delete_confirm), getString(R.string.action_yes), getString(R.string.action_no));
                return true;
            case R.id.account_detail_cancel:
                finish();
                return true;
            case R.id.account_maintenance_save:
                if (detailType == DETAIL_TYPE_ADD) {
                    initAccount();
                    saveAccount();
                }
                else if (detailType == DETAIL_TYPE_EDIT) {
                    initAccount();
                    updateAccount();
                }
                finish();
                return true;
            case R.id.account_maintenance_cancel:
                confirmAction(getString(R.string.activity_detail_cancel_confirm_type), getString(R.string.action_cancel_confirm_save), getString(R.string.action_yes), getString(R.string.action_no));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private void initAccount() {

        for (int field = 0; field < accountFieldListView.getCount(); field++) {

            final AccountFieldView accountFieldView =
                    (AccountFieldView) accountFieldListView.getAdapter().getView(field, null, null);

            final AccountField accountField = accountFieldView.getField();

            // NOTES: using "field + 1" because desired start field is column 1 & ArrayList starts at 0
            account = AccountFieldViewHelper.setValuesFromAccountFieldView(
                    field + 1, account, accountField.getValue()
            );
        }
    }


    private void saveAccount() {

        final Uri uri = Uri.parse(AccountContentProvider.CONTENT_URI + "/accounts/" + account.getAccountId());
        getContentResolver().insert(uri, getContentValues(account));

        final int accountId = Integer.parseInt(uri.getLastPathSegment());
        account.setAccountId(accountId);

        Toast.makeText(this, R.string.activity_detail_toast_save_success, Toast.LENGTH_SHORT).show();
    }


    public void updateAccount() {

        final Uri uri = Uri.parse(AccountContentProvider.CONTENT_URI + "/accounts/" + account.getAccountId());
        getContentResolver().update(uri, getContentValues(account), null, null);

        Toast.makeText(AccountDetailActivity.this, R.string.activity_detail_toast_update_success, Toast.LENGTH_SHORT).show();
    }


    private void deleteAccount() {

        final Uri uri = Uri.parse(AccountContentProvider.CONTENT_URI + "/accounts/" + account.getAccountId());
        Log.d("deleteAccount: ", String.valueOf(uri));
        getContentResolver().delete(uri, null, null);

        Toast.makeText(AccountDetailActivity.this, R.string.activity_detail_toast_delete_success, Toast.LENGTH_SHORT).show();
    }


    private ContentValues getContentValues(final Account account) {

        final ContentValues cv = new ContentValues();
        cv.put(AccountTable.FRIENDLY_NAME_KEY, account.getFriendlyName());
        cv.put(AccountTable.TYPE_KEY, account.getType());
        cv.put(AccountTable.URL_KEY, account.getUrl());
        cv.put(AccountTable.EMAIL_KEY, account.getEmail());
        cv.put(AccountTable.USERNAME_KEY, account.getUsername());
        cv.put(AccountTable.PASSWORD_KEY, account.getPassword());
        cv.put(AccountTable.SECRET_QUESTION_KEY, account.getSecretQuestion());
        cv.put(AccountTable.SECRET_ANSWER_KEY, account.getSecretAnswer());
        cv.put(AccountTable.NOTES_KEY, account.getNotes());
        cv.put(AccountTable.BUSINESS_NAME_KEY, account.getBusinessName());
        cv.put(AccountTable.BUSINESS_ADDRESS_KEY, account.getBusinessAddress());
        cv.put(AccountTable.BUSINESS_CITY_KEY, account.getBusinessCity());
        cv.put(AccountTable.BUSINESS_STATE_KEY, account.getBusinessState());
        cv.put(AccountTable.BUSINESS_ZIP_KEY, account.getBusinessZip());
        cv.put(AccountTable.BUSINESS_PHONE_NUMBER_KEY, account.getBusinessPhoneNumber());
        cv.put(AccountTable.BUSINESS_CONTACT_KEY, account.getBusinessContact());
        cv.put(AccountTable.ACCT_NUMBER_KEY, account.getAcctNumber());
        cv.put(AccountTable.ROUTING_NUMBER_KEY, account.getRoutingNumber());
        cv.put(AccountTable.EXPIRATION_DATE_KEY, account.getExpirationDate());
        cv.put(AccountTable.CVV_NUMBER_KEY, account.getCvvNumber());
        cv.put(AccountTable.AVATAR_KEY, account.getAvatar());
        cv.put(AccountTable.IMAGE_URI_KEY, account.getImageUri());
        cv.put(AccountTable.SERIAL_NUMBER_KEY, account.getSerialNumber());
        cv.put(AccountTable.STORAGE_SIZE_KEY, account.getStorageSize());

        return cv;
    }


    private void confirmAction(final String confirmType, String messageText, String trueButtonText, String falseButtonText) {

        final String leave = getResources().getString(R.string.activity_detail_cancel_confirm_type);
        final String delete = getResources().getString(R.string.activity_detail_delete_confirm_type);

        final AlertDialog.Builder builder = new AlertDialog.Builder(this, 0);

        builder.setTitle(confirmType);
        builder.setMessage(messageText);

        builder.setPositiveButton(trueButtonText, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {

                if (confirmType.equals(leave)) {

                    finish();
                } else if (confirmType.equals(delete)) {

                    deleteAccount();
                    finish();
                }

                dialog.dismiss();
            }

        });

        builder.setNegativeButton(falseButtonText, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                Log.d("confirmAction: ", "negative clicked");
                dialog.dismiss();
            }
        });

        final AlertDialog alert = builder.create();
        alert.show();
    }


    private void reloadActivity(final int detailType) {

        final Intent intent = getIntent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        intent.putExtra("detailType", detailType);
        finish();

        overridePendingTransition(0, 0);
        startActivity(intent);
        overridePendingTransition(0, 0);
    }

}
