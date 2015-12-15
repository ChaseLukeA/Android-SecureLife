package edu.cvtc.android.securelife.Helper;

import edu.cvtc.android.securelife.Model.Account;
import edu.cvtc.android.securelife.Model.AccountField;
import edu.cvtc.android.securelife.Model.AccountTable;
import edu.cvtc.android.securelife.R;


public class AccountFieldViewHelper {

    public static final String FRIENDLY_NAME_LABEL = "Account Name";
    public static final String FRIENDLY_NAME_EXAMPLE = "JP's Gmail";

    public static final String TYPE_LABEL = "Account Type";
    public static final String TYPE_EXAMPLE = "Email";

    public static final String URL_LABEL = "URL";
    public static final String URL_EXAMPLE = "http://mail.google.com";

    public static final String EMAIL_LABEL = "Email Address";
    public static final String EMAIL_EXAMPLE = "john_q_public@gmail.com";

    public static final String USERNAME_LABEL = "Username";
    public static final String USERNAME_EXAMPLE = "PublicJohnQ";

    public static final String PASSWORD_LABEL = "Password";
    public static final String PASSWORD_EXAMPLE = "abc123jQp";

    public static final String SECRET_QUESTION_LABEL = "Secret Question";
    public static final String SECRET_QUESTION_EXAMPLE = "favorite search engine";

    public static final String SECRET_ANSWER_LABEL = "Secret Answer";
    public static final String SECRET_ANSWER_EXAMPLE = "Google";

    public static final String NOTES_LABEL = "Notes";
    public static final String NOTES_EXAMPLE = "this Google gmail account is used for all my Google accounts";

    public static final String BUSINESS_NAME_LABEL = "Business Name";
    public static final String BUSINESS_NAME_EXAMPLE = "Google Inc.";

    public static final String BUSINESS_ADDRESS_LABEL = "Business Address";
    public static final String BUSINESS_ADDRESS_EXAMPLE = "1600 Amphitheatre Parkway";

    public static final String BUSINESS_CITY_LABEL = "Business City";
    public static final String BUSINESS_CITY_EXAMPLE = "Mountain View";

    public static final String BUSINESS_STATE_LABEL = "Business State";
    public static final String BUSINESS_STATE_EXAMPLE = "CA";

    public static final String BUSINESS_ZIP_LABEL = "Business Zip Code";
    public static final String BUSINESS_ZIP_EXAMPLE = "94043-0000";

    public static final String BUSINESS_PHONE_NUMBER_LABEL = "Business Phone Number";
    public static final String BUSINESS_PHONE_NUMBER_EXAMPLE = "1-650-253-0000";

    public static final String BUSINESS_CONTACT_LABEL = "Business Contact";
    public static final String BUSINESS_CONTACT_EXAMPLE = "Sundar Pichai, CEO";

    public static final String ACCT_NUMBER_LABEL = "Account Number";
    public static final String ACCT_NUMBER_EXAMPLE = "1111-2222-3333-4444";

    public static final String ROUTING_NUMBER_LABEL = "Routing Number";
    public static final String ROUTING_NUMBER_EXAMPLE = "123456789";

    public static final String EXPIRATION_DATE_LABEL = "Expiration Date";
    public static final String EXPIRATION_DATE_EXAMPLE = "05/10/2030";

    public static final String CVV_NUMBER_LABEL = "CVV Number";
    public static final String CVV_NUMBER_EXAMPLE = "111";

    public static final String AVATAR_LABEL = "Avatar";
    public static final String AVATAR_EXAMPLE = "JQPublic_l33t";

    public static final String IMAGE_URI_LABEL = "Image URL";
    public static final String IMAGE_URI_EXAMPLE = "http://www.jqpublic.com/images/john.png";

    public static final String SERIAL_NUMBER_LABEL = "Serial Number";
    public static final String SERIAL_NUMBER_EXAMPLE = "123A-234B-345C-456D";

    public static final String STORAGE_SIZE_LABEL = "Storage Size";
    public static final String STORAGE_SIZE_EXAMPLE = "Google Drive: 15GB";


    // to assist setting Account object values from each AccountFieldView
    public static Account setValuesFromAccountFieldView(int field, Account account, String value) {

        switch (field) {
            case AccountTable.FRIENDLY_NAME_COL:
                account.setFriendlyName(value);
                return account;
            case AccountTable.TYPE_COL:
                account.setType(value);
                return account;
            case AccountTable.URL_COL:
                account.setUrl(value);
                return account;
            case AccountTable.EMAIL_COL:
                account.setEmail(value);
                return account;
            case AccountTable.USERNAME_COL:
                account.setUsername(value);
                return account;
            case AccountTable.PASSWORD_COL:
                account.setPassword(value);
                return account;
            case AccountTable.SECRET_QUESTION_COL:
                account.setSecretQuestion(value);
                return account;
            case AccountTable.SECRET_ANSWER_COL:
                account.setSecretAnswer(value);
                return account;
            case AccountTable.NOTES_COL:
                account.setNotes(value);
                return account;
            case AccountTable.BUSINESS_NAME_COL:
                account.setBusinessName(value);
                return account;
            case AccountTable.BUSINESS_ADDRESS_COL:
                account.setBusinessAddress(value);
                return account;
            case AccountTable.BUSINESS_CITY_COL:
                account.setBusinessCity(value);
                return account;
            case AccountTable.BUSINESS_STATE_COL:
                account.setBusinessState(value);
                return account;
            case AccountTable.BUSINESS_ZIP_COL:
                account.setBusinessZip(value);
                return account;
            case AccountTable.BUSINESS_PHONE_NUMBER_COL:
                account.setBusinessPhoneNumber(value);
                return account;
            case AccountTable.BUSINESS_CONTACT_COL:
                account.setBusinessContact(value);
                return account;
            case AccountTable.ACCT_NUMBER_COL:
                account.setAcctNumber(value);
                return account;
            case AccountTable.ROUTING_NUMBER_COL:
                account.setRoutingNumber(value);
                return account;
            case AccountTable.EXPIRATION_DATE_COL:
                account.setExpirationDate(value);
                return account;
            case AccountTable.CVV_NUMBER_COL:
                account.setCvvNumber(value);
                return account;
            case AccountTable.AVATAR_COL:
                account.setAvatar(value);
                return account;
            case AccountTable.IMAGE_URI_COL:
                account.setImageUri(value);
                return account;
            case AccountTable.SERIAL_NUMBER_COL:
                account.setSerialNumber(value);
                return account;
            case AccountTable.STORAGE_SIZE_COL:
                account.setStorageSize(value);
                return account;
            default:
                throw new IllegalArgumentException(String.valueOf(R.string.account_field_view_helper_exception));
        }
    }


    // to assist creating AccountFieldView objects from Account object values
    public static AccountField setValuesFromAccount(int field, Account account) {

        switch (field) {
            case AccountTable.FRIENDLY_NAME_COL:
                return new AccountField(
                        FRIENDLY_NAME_LABEL,
                        FRIENDLY_NAME_EXAMPLE,
                        account.getFriendlyName()
                );
            case AccountTable.TYPE_COL:
                // TODO: change return here to include setSpinner(true)
                return new AccountField(
                        TYPE_LABEL,
                        TYPE_EXAMPLE,
                        account.getType()
                );
            case AccountTable.URL_COL:
                return new AccountField(
                        URL_LABEL,
                        URL_EXAMPLE,
                        account.getUrl()
                );
            case AccountTable.EMAIL_COL:
                return new AccountField(
                        EMAIL_LABEL,
                        EMAIL_EXAMPLE,
                        account.getEmail()
                );
            case AccountTable.USERNAME_COL:
                return new AccountField(
                        USERNAME_LABEL,
                        USERNAME_EXAMPLE,
                        account.getUsername()
                );
            case AccountTable.PASSWORD_COL:
                return new AccountField(
                        PASSWORD_LABEL,
                        PASSWORD_EXAMPLE,
                        account.getPassword()
                );
            case AccountTable.SECRET_QUESTION_COL:
                return new AccountField(
                        SECRET_QUESTION_LABEL,
                        SECRET_QUESTION_EXAMPLE,
                        account.getSecretQuestion()
                );
            case AccountTable.SECRET_ANSWER_COL:
                return new AccountField(
                        SECRET_ANSWER_LABEL,
                        SECRET_ANSWER_EXAMPLE,
                        account.getSecretAnswer()
                );
            case AccountTable.NOTES_COL:
                return new AccountField(
                        NOTES_LABEL,
                        NOTES_EXAMPLE,
                        account.getNotes()
                );
            case AccountTable.BUSINESS_NAME_COL:
                return new AccountField(
                        BUSINESS_NAME_LABEL,
                        BUSINESS_NAME_EXAMPLE,
                        account.getBusinessName()
                );
            case AccountTable.BUSINESS_ADDRESS_COL:
                return new AccountField(
                        BUSINESS_ADDRESS_LABEL,
                        BUSINESS_ADDRESS_EXAMPLE,
                        account.getBusinessAddress()
                );
            case AccountTable.BUSINESS_CITY_COL:
                return new AccountField(
                        BUSINESS_CITY_LABEL,
                        BUSINESS_CITY_EXAMPLE,
                        account.getBusinessCity()
                );
            case AccountTable.BUSINESS_STATE_COL:
                return new AccountField(
                        BUSINESS_STATE_LABEL,
                        BUSINESS_STATE_EXAMPLE,
                        account.getBusinessState()
                );
            case AccountTable.BUSINESS_ZIP_COL:
                return new AccountField(
                        BUSINESS_ZIP_LABEL,
                        BUSINESS_ZIP_EXAMPLE,
                        account.getBusinessZip()
                );
            case AccountTable.BUSINESS_PHONE_NUMBER_COL:
                return new AccountField(
                        BUSINESS_PHONE_NUMBER_LABEL,
                        BUSINESS_PHONE_NUMBER_EXAMPLE,
                        account.getBusinessPhoneNumber()
                );
            case AccountTable.BUSINESS_CONTACT_COL:
                return new AccountField(
                        BUSINESS_CONTACT_LABEL,
                        BUSINESS_CONTACT_EXAMPLE,
                        account.getBusinessContact()
                );
            case AccountTable.ACCT_NUMBER_COL:
                return new AccountField(
                        ACCT_NUMBER_LABEL,
                        ACCT_NUMBER_EXAMPLE,
                        account.getAcctNumber()
                );
            case AccountTable.ROUTING_NUMBER_COL:
                return new AccountField(
                        ROUTING_NUMBER_LABEL,
                        ROUTING_NUMBER_EXAMPLE,
                        account.getRoutingNumber()
                );
            case AccountTable.EXPIRATION_DATE_COL:
                return new AccountField(
                        EXPIRATION_DATE_LABEL,
                        EXPIRATION_DATE_EXAMPLE,
                        account.getExpirationDate()
                );
            case AccountTable.CVV_NUMBER_COL:
                return new AccountField(
                        CVV_NUMBER_LABEL,
                        CVV_NUMBER_EXAMPLE,
                        account.getCvvNumber()
                );
            case AccountTable.AVATAR_COL:
                return new AccountField(
                        AVATAR_LABEL,
                        AVATAR_EXAMPLE,
                        account.getAvatar()
                );
            case AccountTable.IMAGE_URI_COL:
                return new AccountField(
                        IMAGE_URI_LABEL,
                        IMAGE_URI_EXAMPLE,
                        account.getImageUri()
                );
            case AccountTable.SERIAL_NUMBER_COL:
                return new AccountField(
                        SERIAL_NUMBER_LABEL,
                        SERIAL_NUMBER_EXAMPLE,
                        account.getSerialNumber()
                );
            case AccountTable.STORAGE_SIZE_COL:
                return new AccountField(
                        STORAGE_SIZE_LABEL,
                        STORAGE_SIZE_EXAMPLE,
                        account.getStorageSize()
                );
            default:
                throw new IllegalArgumentException(String.valueOf(R.string.account_field_view_helper_exception));
        }
    }
}
