package ke.co.afixus.nspsi;

public class suggestionsData {
    private String sboxText;
    private String sboxDate;

    public suggestionsData() {
    }

    public String getSboxText() {
        return sboxText;
    }

    public void setSboxText(String sboxText) {
        this.sboxText = sboxText;
    }

    public String getSboxDate() {
        return sboxDate;
    }

    public void setSboxDate(String sboxDate) {
        this.sboxDate = sboxDate;
    }

    public suggestionsData(String sboxText, String sboxDate) {
        this.sboxText = sboxText;
        this.sboxDate = sboxDate;
    }
}
