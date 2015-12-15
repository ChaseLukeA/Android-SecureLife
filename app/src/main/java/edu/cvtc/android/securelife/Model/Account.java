package edu.cvtc.android.securelife.Model;


import android.os.Parcel;
import android.os.Parcelable;

public class Account implements Parcelable {

    private int accountId;

    // required information
    private String friendlyName;
    private String type;

    // basic information
    private String url;
    private String email;
    private String username;
    private String password;
    private String secretQuestion;
    private String secretAnswer;
    private String notes;

    // business information
    private String businessName;
    private String businessAddress;
    private String businessCity;
    private String businessState;
    private String businessZip;
    private String businessPhoneNumber;
    private String businessContact;

    // financial information
    private String acctNumber;
    private String routingNumber;
    private String expirationDate;
    private String cvvNumber;

    // digital information
    private String avatar;
    private String imageUri;
    private String serialNumber;
    private String storageSize;


    // empty constructor for creation of new account without values
    public Account() {
        this.friendlyName = "";
        this.type = "";
        this.url = "";
        this.email = "";
        this.username = "";
        this.password = "";
        this.secretQuestion = "";
        this.secretAnswer = "";
        this.notes = "";
        this.businessName = "";
        this.businessAddress = "";
        this.businessCity = "";
        this.businessState = "";
        this.businessZip = "";
        this.businessPhoneNumber = "";
        this.businessContact = "";
        this.acctNumber = "";
        this.routingNumber = "";
        this.expirationDate = "";
        this.cvvNumber = "";
        this.avatar = "";
        this.imageUri = "";
        this.serialNumber = "";
        this.storageSize = "";
    }


    // partial constructor for creation of account objects with only required fields
    public Account(String friendlyName, String type) {
        this.friendlyName = friendlyName;
        this.type = type;
    }


    // partial constructor for creation of new account objects; accountId missing since it
    // will be getting that automatically assigned by the database
    public Account(String friendlyName, String type, String url, String email,
                   String username, String password, String secretQuestion,
                   String secretAnswer, String notes, String businessName, String businessAddress,
                   String businessCity, String businessState, String businessZip, String businessPhoneNumber,
                   String businessContact, String acctNumber, String routingNumber, String expirationDate,
                   String cvvNumber, String avatar, String imageUri, String serialNumber, String storageSize) {
        this.friendlyName = friendlyName;
        this.type = type;
        this.url = url;
        this.email = email;
        this.username = username;
        this.password = password;
        this.secretQuestion = secretQuestion;
        this.secretAnswer = secretAnswer;
        this.notes = notes;
        this.businessName = businessName;
        this.businessAddress = businessAddress;
        this.businessCity = businessCity;
        this.businessState = businessState;
        this.businessZip = businessZip;
        this.businessPhoneNumber = businessPhoneNumber;
        this.businessContact = businessContact;
        this.acctNumber = acctNumber;
        this.routingNumber = routingNumber;
        this.expirationDate = expirationDate;
        this.cvvNumber = cvvNumber;
        this.avatar = avatar;
        this.imageUri = imageUri;
        this.serialNumber = serialNumber;
        this.storageSize = storageSize;
    }


    // full constructor for creation of account objects from database rows
    public Account(int accountId, String friendlyName, String type, String url, String email,
                   String username, String password, String secretQuestion,
                   String secretAnswer, String notes, String businessName, String businessAddress,
                   String businessCity, String businessState, String businessZip, String businessPhoneNumber,
                   String businessContact, String acctNumber, String routingNumber, String expirationDate,
                   String cvvNumber, String avatar, String imageUri, String serialNumber, String storageSize) {
        this.accountId = accountId;
        this.friendlyName = friendlyName;
        this.type = type;
        this.url = url;
        this.email = email;
        this.username = username;
        this.password = password;
        this.secretQuestion = secretQuestion;
        this.secretAnswer = secretAnswer;
        this.notes = notes;
        this.businessName = businessName;
        this.businessAddress = businessAddress;
        this.businessCity = businessCity;
        this.businessState = businessState;
        this.businessZip = businessZip;
        this.businessPhoneNumber = businessPhoneNumber;
        this.businessContact = businessContact;
        this.acctNumber = acctNumber;
        this.routingNumber = routingNumber;
        this.expirationDate = expirationDate;
        this.cvvNumber = cvvNumber;
        this.avatar = avatar;
        this.imageUri = imageUri;
        this.serialNumber = serialNumber;
        this.storageSize = storageSize;
    }


    // parcel constructor
    public Account(Parcel parcel) {
        String[] values = new String[25];
        parcel.readStringArray(values);

        this.accountId = Integer.parseInt(values[0]);
        this.friendlyName = values[1];
        this.type = values[2];
        this.url = values[3];
        this.email = values[4];
        this.username = values[5];
        this.password = values[6];
        this.secretQuestion = values[7];
        this.secretAnswer = values[8];
        this.notes = values[9];
        this.businessName = values[10];
        this.businessAddress = values[11];
        this.businessCity = values[12];
        this.businessState = values[13];
        this.businessZip = values[14];
        this.businessPhoneNumber = values[15];
        this.businessContact = values[16];
        this.acctNumber = values[17];
        this.routingNumber = values[18];
        this.expirationDate = values[19];
        this.cvvNumber = values[20];
        this.avatar = values[21];
        this.imageUri = values[22];
        this.serialNumber = values[23];
        this.storageSize = values[24];
    }


    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getFriendlyName() {
        return friendlyName;
    }

    public void setFriendlyName(String friendlyName) {
        this.friendlyName = friendlyName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSecretQuestion() {
        return secretQuestion;
    }

    public void setSecretQuestion(String secretQuestion) {
        this.secretQuestion = secretQuestion;
    }

    public String getSecretAnswer() {
        return secretAnswer;
    }

    public void setSecretAnswer(String secretAnswer) {
        this.secretAnswer = secretAnswer;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getBusinessAddress() {
        return businessAddress;
    }

    public void setBusinessAddress(String businessAddress) {
        this.businessAddress = businessAddress;
    }

    public String getBusinessCity() {
        return businessCity;
    }

    public void setBusinessCity(String businessCity) {
        this.businessCity = businessCity;
    }

    public String getBusinessState() {
        return businessState;
    }

    public void setBusinessState(String businessState) {
        this.businessState = businessState;
    }

    public String getBusinessZip() {
        return businessZip;
    }

    public void setBusinessZip(String businessZip) {
        this.businessZip = businessZip;
    }

    public String getBusinessPhoneNumber() {
        return businessPhoneNumber;
    }

    public void setBusinessPhoneNumber(String businessPhoneNumber) {
        this.businessPhoneNumber = businessPhoneNumber;
    }

    public String getBusinessContact() {
        return businessContact;
    }

    public void setBusinessContact(String businessContact) {
        this.businessContact = businessContact;
    }

    public String getAcctNumber() {
        return acctNumber;
    }

    public void setAcctNumber(String acctNumber) {
        this.acctNumber = acctNumber;
    }

    public String getRoutingNumber() {
        return routingNumber;
    }

    public void setRoutingNumber(String routingNumber) {
        this.routingNumber = routingNumber;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getCvvNumber() {
        return cvvNumber;
    }

    public void setCvvNumber(String cvvNumber) {
        this.cvvNumber = cvvNumber;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getStorageSize() {
        return storageSize;
    }

    public void setStorageSize(String storageSize) {

        this.storageSize = storageSize;
    }


    @Override
    public boolean equals(Object obj) {

        return obj instanceof Account && ((Account) obj).getAccountId() == accountId;

// NOTES: potentially don't need these but am keeping just in case I want to implement specific
// checks later using these comparators and don't want to rewrite them all again
//                && ((Account) obj).getFriendlyName().equals(friendlyName)
//                && ((Account) obj).getType().equals(type)
//                && ((Account) obj).getUrl().equals(url)
//                && ((Account) obj).getEmail().equals(email)
//                && ((Account) obj).getUsername().equals(username)
//                && ((Account) obj).getPassword().equals(password)
//                && ((Account) obj).getSecretQuestion().equals(secretQuestion)
//                && ((Account) obj).getSecretAnswer().equals(secretAnswer)
//                && ((Account) obj).getNotes().equals(notes)
//                && ((Account) obj).getBusinessName().equals(businessName)
//                && ((Account) obj).getBusinessAddress().equals(businessAddress)
//                && ((Account) obj).getBusinessCity().equals(businessCity)
//                && ((Account) obj).getBusinessState().equals(businessState)
//                && ((Account) obj).getBusinessZip().equals(businessZip)
//                && ((Account) obj).getBusinessPhoneNumber().equals(businessPhoneNumber)
//                && ((Account) obj).getBusinessContact().equals(businessContact)
//                && ((Account) obj).getAcctNumber().equals(acctNumber)
//                && ((Account) obj).getRoutingNumber().equals(routingNumber)
//                && ((Account) obj).getExpirationDate().equals(expirationDate)
//                && ((Account) obj).getCvvNumber().equals(cvvNumber)
//                && ((Account) obj).getAvatar().equals(avatar)
//                && ((Account) obj).getImageUri().equals(imageUri)
//                && ((Account) obj).getSerialNumber().equals(serialNumber)
//                && ((Account) obj).getStorageSize().equals(storageSize);
    }


    @Override
    public int describeContents() {
        return 0;
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeStringArray(new String[]
            {
                String.valueOf(this.accountId),
                this.friendlyName,
                this.type,
                this.url,
                this.email,
                this.username,
                this.password,
                this.secretQuestion,
                this.secretAnswer,
                this.notes,
                this.businessName,
                this.businessAddress,
                this.businessCity,
                this.businessState,
                this.businessZip,
                this.businessPhoneNumber,
                this.businessContact,
                this.acctNumber,
                this.routingNumber,
                this.expirationDate,
                this.cvvNumber,
                this.avatar,
                this.imageUri,
                this.serialNumber,
                this.storageSize
            }
        );
    }


    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {

        @Override
        public Object createFromParcel(Parcel source) {

            return new Account(source);
        }

        @Override
        public Object[] newArray(int size) {
            return new Account[size];
        }
    };

}
