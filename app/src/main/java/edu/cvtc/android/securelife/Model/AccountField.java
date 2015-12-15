package edu.cvtc.android.securelife.Model;


public class AccountField {

    private String label;
    private String example;
    private String key;
    private String value;
    private boolean editable;
    private boolean spinner;


    // partial constructor for setting AccountField labels, hints, etc
    public AccountField(String label, String example, String value) {

        this.label = label;
        this.example = example;
        this.value = value;
    }


    // full constructor
    public AccountField(String label, String example, String key, String value, boolean editable, boolean spinner) {

        this.label = label;
        this.example = example;
        this.key = key;
        this.value = value;
        this.editable = editable;
        this.spinner = spinner;
    }


    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    public boolean isSpinner() {
        return spinner;
    }

    public void setSpinner(boolean spinner) {
        this.spinner = spinner;
    }


    @Override
    public boolean equals(Object obj) {

        return obj instanceof AccountField
                && ((AccountField) obj).getLabel().equals(label)
                && ((AccountField) obj).getExample().equals(example)
                && ((AccountField) obj).getKey().equals(key)
                && ((AccountField) obj).getValue().equals(value)
                && ((AccountField) obj).isEditable() == editable
                && ((AccountField) obj).isSpinner() == spinner;
    }

}
