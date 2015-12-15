package edu.cvtc.android.securelife.Model;


import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import java.util.Arrays;
import java.util.HashSet;

import edu.cvtc.android.securelife.Helper.AccountDatabaseHelper;
import edu.cvtc.android.securelife.R;
import edu.cvtc.android.securelife.View.AccountSummaryActivity;
import edu.cvtc.android.securelife.View.SearchActivity;


public class AccountContentProvider extends ContentProvider {

    private AccountDatabaseHelper database;

    private static final int ACCOUNT_ID = 1;
    private static final int ACCOUNT_SORTER = 2;

    private static final String AUTHORITY = "edu.cvtc.android.securelife.contentprovider";
    private static final String BASE_PATH = "accounts";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + BASE_PATH);

    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        uriMatcher.addURI(AUTHORITY, BASE_PATH + "/accounts/#", ACCOUNT_ID);
        uriMatcher.addURI(AUTHORITY, BASE_PATH + "/sorters/#", ACCOUNT_SORTER);
    }


    @Override
    public boolean onCreate() {

        this.database = new AccountDatabaseHelper(getContext(),
                AccountDatabaseHelper.DB_NAME, null,
                AccountDatabaseHelper.DB_VERSION);

        return false;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        String orderBy;

        checkColumns(projection);

        final SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(AccountTable.DATABASE_ACCOUNTS_TABLE);

        final int uriType = uriMatcher.match(uri);

        switch (uriType) {
            case ACCOUNT_SORTER:
                final int sortValue = Integer.parseInt(uri.getLastPathSegment());

                if (AccountSummaryActivity.searchQuery == true) {

                    final String searchTerm = AccountSummaryActivity.searchTerm;

                    queryBuilder.appendWhere(
                        AccountTable.FRIENDLY_NAME_KEY + " LIKE '%" + searchTerm + "%' OR " +
                        AccountTable.TYPE_KEY + " LIKE '%" + searchTerm + "%' OR " +
                        AccountTable.URL_KEY + " LIKE '%" + searchTerm + "%' OR " +
                        AccountTable.EMAIL_KEY + " LIKE '%" + searchTerm + "%' OR " +
                        AccountTable.USERNAME_KEY + " LIKE '%" + searchTerm + "%' OR " +
                        AccountTable.PASSWORD_KEY + " LIKE '%" + searchTerm + "%' OR " +
                        AccountTable.SECRET_QUESTION_KEY + " LIKE '%" + searchTerm + "%' OR " +
                        AccountTable.SECRET_ANSWER_KEY + " LIKE '%" + searchTerm + "%' OR " +
                        AccountTable.NOTES_KEY + " LIKE '%" + searchTerm + "%' OR " +
                        AccountTable.BUSINESS_NAME_KEY + " LIKE '%" + searchTerm + "%' OR " +
                        AccountTable.BUSINESS_ADDRESS_KEY + " LIKE '%" + searchTerm + "%' OR " +
                        AccountTable.BUSINESS_CITY_KEY + " LIKE '%" + searchTerm + "%' OR " +
                        AccountTable.BUSINESS_STATE_KEY + " LIKE '%" + searchTerm + "%' OR " +
                        AccountTable.BUSINESS_ZIP_KEY + " LIKE '%" + searchTerm + "%' OR " +
                        AccountTable.BUSINESS_PHONE_NUMBER_KEY + " LIKE '%" + searchTerm + "%' OR " +
                        AccountTable.BUSINESS_CONTACT_KEY + " LIKE '%" + searchTerm + "%' OR " +
                        AccountTable.ACCT_NUMBER_KEY + " LIKE '%" + searchTerm + "%' OR " +
                        AccountTable.ROUTING_NUMBER_KEY + " LIKE '%" + searchTerm + "%' OR " +
                        AccountTable.EXPIRATION_DATE_KEY + " LIKE '%" + searchTerm + "%' OR " +
                        AccountTable.CVV_NUMBER_KEY + " LIKE '%" + searchTerm + "%' OR " +
                        AccountTable.AVATAR_KEY + " LIKE '%" + searchTerm + "%' OR " +
                        AccountTable.IMAGE_URI_KEY + " LIKE '%" + searchTerm + "%' OR " +
                        AccountTable.SERIAL_NUMBER_KEY + " LIKE '%" + searchTerm + "%' OR " +
                        AccountTable.STORAGE_SIZE_KEY + " LIKE '%" + searchTerm + "%'"
                    );
                }

                if (sortValue == AccountSummaryActivity.SORT_BY_TYPE) {

                    orderBy = AccountTable.TYPE_KEY;
                }
                else {
                    orderBy = AccountTable.FRIENDLY_NAME_KEY;
                }
                break;
            default:
                throw new IllegalArgumentException(String.valueOf(R.string.content_provider_uri_exception) + uri);
        }

        SQLiteDatabase db = this.database.getWritableDatabase();
        Cursor cursor = queryBuilder.query(db, projection, selection, null, null, null, orderBy);
        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
    }


    private void checkColumns(String[] projection) {

        String[] available = {
                AccountTable.ACCOUNT_ID_KEY,
                AccountTable.FRIENDLY_NAME_KEY,
                AccountTable.TYPE_KEY,
                AccountTable.URL_KEY,
                AccountTable.EMAIL_KEY,
                AccountTable.USERNAME_KEY,
                AccountTable.PASSWORD_KEY,
                AccountTable.SECRET_QUESTION_KEY,
                AccountTable.SECRET_ANSWER_KEY,
                AccountTable.NOTES_KEY,
                AccountTable.BUSINESS_NAME_KEY,
                AccountTable.BUSINESS_ADDRESS_KEY,
                AccountTable.BUSINESS_CITY_KEY,
                AccountTable.BUSINESS_STATE_KEY,
                AccountTable.BUSINESS_ZIP_KEY,
                AccountTable.BUSINESS_PHONE_NUMBER_KEY,
                AccountTable.BUSINESS_CONTACT_KEY,
                AccountTable.ACCT_NUMBER_KEY,
                AccountTable.ROUTING_NUMBER_KEY,
                AccountTable.EXPIRATION_DATE_KEY,
                AccountTable.CVV_NUMBER_KEY,
                AccountTable.AVATAR_KEY,
                AccountTable.IMAGE_URI_KEY,
                AccountTable.SERIAL_NUMBER_KEY,
                AccountTable.STORAGE_SIZE_KEY
        };

        if (null != projection) {
            HashSet<String> requestedColumns = new HashSet<>(Arrays.asList(projection));
            HashSet<String> availableColumns = new HashSet<>(Arrays.asList(available));

            if (!availableColumns.containsAll(requestedColumns)) {
                throw new IllegalArgumentException(String.valueOf(R.string.content_provider_check_columns_exception));
            }
        }
    }


    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }


    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {

        SQLiteDatabase db = this.database.getWritableDatabase();

        long accountId = 0;

        int uriType = uriMatcher.match(uri);

        switch(uriType)	{
            case ACCOUNT_ID:
                accountId = db.insert(AccountTable.DATABASE_ACCOUNTS_TABLE, null, values);
                break;
            default:
                throw new IllegalArgumentException(String.valueOf(R.string.content_provider_uri_exception) + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);

        return Uri.parse(BASE_PATH + "/" + accountId);
    }


    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {

        SQLiteDatabase db = this.database.getWritableDatabase();

        int numberOfRowsDeleted = 0;

        int uriType = uriMatcher.match(uri);

        switch (uriType) {
            case ACCOUNT_ID:
                String accountId = uri.getLastPathSegment();

                numberOfRowsDeleted = db.delete(
                        AccountTable.DATABASE_ACCOUNTS_TABLE,
                        AccountTable.ACCOUNT_ID_KEY + "=" + accountId,
                        null);
                break;
            default:
                throw new IllegalArgumentException(String.valueOf(R.string.content_provider_uri_exception) + uri);
        }

        if (numberOfRowsDeleted > 0) {

            getContext().getContentResolver().notifyChange(uri, null);
        }

        return numberOfRowsDeleted;
    }


    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {

        SQLiteDatabase db = this.database.getWritableDatabase();

        int numberOfRowsUpdated = 0;

        int uriType = uriMatcher.match(uri);

        switch (uriType) {
            case ACCOUNT_ID:
                String accountId = uri.getLastPathSegment();

                if (!TextUtils.isEmpty(selection)) {

                    numberOfRowsUpdated = db.update(
                            AccountTable.DATABASE_ACCOUNTS_TABLE, values,
                            AccountTable.ACCOUNT_ID_KEY + "=" + accountId +
                                    " AND " + selection, null);
                }
                else {

                    numberOfRowsUpdated = db.update(
                            AccountTable.DATABASE_ACCOUNTS_TABLE, values,
                            AccountTable.ACCOUNT_ID_KEY + "=" + accountId, null);
                }
                break;
            default:
                throw new IllegalArgumentException(String.valueOf(R.string.content_provider_uri_exception) + uri);
        }

        if (numberOfRowsUpdated > 0) {

            getContext().getContentResolver().notifyChange(uri, null);
        }

        return numberOfRowsUpdated;
    }
}
