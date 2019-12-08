package ke.co.afixus.nspsi;

public class Data {
    //studentsatff trasfered here
    public static class Student_staff
    {
        String usertype;
        String admno_staffno;
        String stdname;
        String phoneno1;
        //String phoneno2;
        String email;
         //String  id;

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


        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

      // public String getId() {
      //   return id;
      //    }

       //public void setId(String id) {
       //   this.id = id;
       // }

        public Student_staff(String usertype, String admno_staffno, String stdname, String phoneno1,String email)
        {
            this.usertype = usertype;
            this.admno_staffno = admno_staffno;
            this.stdname = stdname;
            this.phoneno1 = phoneno1;
           // this.phoneno2 = phoneno2;
            this.email = email;
            //this.id = id;
        }
    }

    public static  class referals
        {
            //r to mean referals

            public void referals()
                            {
                                //empty costructor
                            }
            String refereename;
            String refereeid;
            String rstdname;
            String rstdgender;
            String rstdphoneno1;
            String rstdemail;
            String stdgrade;
            String rstdcourse;
            String  rstdintake;
            String rstdlevels;

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

            public referals(String refereename, String refereeid, String rstdname, String rstdgender, String rstdphoneno1, String rstdemail, String stdgrade, String rstdcourse, String rstdintake, String rstdlevels) {
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
            }
        }


}




