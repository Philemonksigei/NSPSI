package ke.co.afixus.nspsi;

//This is the data source, for reffereals recuclerview
class Refferrals
{
    //r to mean referals
   private String refereename;
   private String refereeid;
   private  String rstdname;
   private String rstdgender;
   private String rstdphoneno1;
   private String rstdemail;
   private String stdgrade;
   private String rstdcourse;
   private  String  rstdintake;
   private String rstdlevels;
    private String myDate;

    public Refferrals() {
    }

    public String getRefereename() {
        return refereename;
    }

    public void setRefereename(String refereename) {
        this.refereename = refereename;
    }

    public String getRefereeid() {
        return refereeid;
    }

    public void setRefereeid(String refereeid) {
        this.refereeid = refereeid;
    }

    public String getRstdname() {
        return rstdname;
    }

    public void setRstdname(String rstdname) {
        this.rstdname = rstdname;
    }

    public String getRstdgender() {
        return rstdgender;
    }

    public void setRstdgender(String rstdgender) {
        this.rstdgender = rstdgender;
    }

    public String getRstdphoneno1() {
        return rstdphoneno1;
    }

    public void setRstdphoneno1(String rstdphoneno1) {
        this.rstdphoneno1 = rstdphoneno1;
    }

    public String getRstdemail() {
        return rstdemail;
    }

    public void setRstdemail(String rstdemail) {
        this.rstdemail = rstdemail;
    }

    public String getStdgrade() {
        return stdgrade;
    }

    public void setStdgrade(String stdgrade) {
        this.stdgrade = stdgrade;
    }

    public String getRstdcourse() {
        return rstdcourse;
    }

    public void setRstdcourse(String rstdcourse) {
        this.rstdcourse = rstdcourse;
    }

    public String getRstdintake() {
        return rstdintake;
    }

    public void setRstdintake(String rstdintake) {
        this.rstdintake = rstdintake;
    }

    public String getRstdlevels() {
        return rstdlevels;
    }

    public void setRstdlevels(String rstdlevels) {
        this.rstdlevels = rstdlevels;
    }

    public String getMyDate() {
        return myDate;
    }

    public void setMyDate(String myDate) {
        this.myDate = myDate;
    }

    public Refferrals(String refereename, String refereeid, String rstdname,
                      String rstdgender, String rstdphoneno1, String rstdemail,
                      String stdgrade, String rstdcourse, String rstdintake,
                      String rstdlevels, String myDate)
    {
        this.refereename = refereename;
        this.refereeid = refereeid;
        this.rstdname = rstdname;
        this.rstdgender = rstdgender;
        this.rstdphoneno1 = rstdphoneno1;
        this.rstdemail = rstdemail;
        this.stdgrade = stdgrade;
        this.rstdcourse = rstdcourse;
        this.rstdintake = rstdintake;
        this.rstdlevels = rstdlevels;
        this.myDate = myDate;
    }
}
