package ke.co.afixus.nspsi;

public class Data {

    public static class Student_staff
    {
        String usertype;
        String admno_staffno;
        String stdname;

        String phoneno1;
        String phoneno2;
        String email;
        String  id;

        public void Student_staff(){
            //empty constructure gets inititated when data is loaded
        }

        public String getUsertype() {
            return usertype;
        }

        public void setUsertype(String usertype) {
            this.usertype = usertype;
        }

        public String getAdmno_staffno() {
            return admno_staffno;
        }

        public void setAdmno_staffno(String admno_staffno) {
            this.admno_staffno = admno_staffno;
        }

        public String getStdname() {
            return stdname;
        }

        public void setStdname(String stdname) {
            this.stdname = stdname;
        }

        public String getPhoneno1() {
            return phoneno1;
        }

        public void setPhoneno1(String phoneno1) {
            this.phoneno1 = phoneno1;
        }

        public String getPhoneno2() {
            return phoneno2;
        }

        public void setPhoneno2(String phoneno2) {
            this.phoneno2 = phoneno2;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Student_staff(String usertype, String admno_staffno, String stdname, String phoneno1, String phoneno2, String email, String id) {
            this.usertype = usertype;
            this.admno_staffno = admno_staffno;
            this.stdname = stdname;
            this.phoneno1 = phoneno1;
            this.phoneno2 = phoneno2;
            this.email = email;
            this.id = id;
        }
    }

    public static  class referals
    {
        //r to mean referals
        String rstdname;
        String rstdgender;
        String rstdphoneno1;
        String rstdemail;
        String stdgrade;
        //String rstdcourse;
        String  rstdintake;
        String rstdyrofjoining;
        String rstdlevels;
        String rstdcounty;



        public void referals(){

            //empty costructor
        }

        public String getRstdname() {
            return rstdname;
        }

        public String getRstdgender() {
            return rstdgender;
        }

        public String getRstdphoneno1() {
            return rstdphoneno1;
        }
        public String getRstdemail() {
            return rstdemail;
        }

        public String getStdgrade() {
            return stdgrade;
        }

        public String getRstdintake() {
            return rstdintake;
        }

        public String getRstdyrofjoining() {
            return rstdyrofjoining;
        }

        public String getRstdlevels() {
            return rstdlevels;
        }

        public String getRstdcounty() {
            return rstdcounty;
        }

        public referals(String rstdname, String rstdgender, String rstdphoneno1, String rstdemail, String stdgrade, String rstdintake, String rstdyrofjoining, String rstdlevels, String rstdcounty) {
            this.rstdname = rstdname;
            this.rstdgender = rstdgender;
            this.rstdphoneno1 = rstdphoneno1;
            this.rstdemail = rstdemail;
            this.stdgrade = stdgrade;
            this.rstdintake = rstdintake;
            this.rstdyrofjoining = rstdyrofjoining;
            this.rstdlevels = rstdlevels;
            this.rstdcounty = rstdcounty;
        }
    }


}




