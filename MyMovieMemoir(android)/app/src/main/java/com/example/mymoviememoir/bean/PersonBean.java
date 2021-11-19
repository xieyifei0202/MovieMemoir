package com.example.mymoviememoir.bean;

public class PersonBean {


    /**
     * creid : 201
     * passwordhash : 38b3eff8baf56627478ec76a704e9b52
     * perid : {"address":"1728 Dandenong Rd","dob":"1996-02-02T00:00:00+11:00","fname":"Yifei","gender":"Male","perid":101,"postcode":3168,"pstate":"VIC","sname":"Xie"}
     * signupdate : 2019-04-23T00:00:00+10:00
     * username : yxie0034@student.monash.edu
     */

    private int creid;
    private String passwordhash;
    private PeridBean perid;
    private String signupdate;
    private String username;

    public PersonBean() {

    }

    public int getCreid() {
        return creid;
    }

    public void setCreid(int creid) {
        this.creid = creid;
    }

    public String getPasswordhash() {
        return passwordhash;
    }

    public void setPasswordhash(String passwordhash) {
        this.passwordhash = passwordhash;
    }

    public PeridBean getPerid() {
        return perid;
    }

    public void setPerid(PeridBean perid) {
        this.perid = perid;
    }

    public String getSignupdate() {
        return signupdate;
    }

    public void setSignupdate(String signupdate) {
        this.signupdate = signupdate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public static class PeridBean {
        /**
         * address : 1728 Dandenong Rd
         * dob : 1996-02-02T00:00:00+11:00
         * fname : Yifei
         * gender : Male
         * perid : 101
         * postcode : 3168
         * pstate : VIC
         * sname : Xie
         */

        private String address;
        private String dob;
        private String fname;
        private String gender;
        private int perid;
        private int postcode;
        private String pstate;
        private String sname;

        public PeridBean() {

        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getDob() {
            return dob;
        }

        public void setDob(String dob) {
            this.dob = dob;
        }

        public String getFname() {
            return fname;
        }

        public void setFname(String fname) {
            this.fname = fname;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public int getPerid() {
            return perid;
        }

        public void setPerid(int perid) {
            this.perid = perid;
        }

        public int getPostcode() {
            return postcode;
        }

        public void setPostcode(int postcode) {
            this.postcode = postcode;
        }

        public String getPstate() {
            return pstate;
        }

        public void setPstate(String pstate) {
            this.pstate = pstate;
        }

        public String getSname() {
            return sname;
        }

        public void setSname(String sname) {
            this.sname = sname;
        }
    }
}
