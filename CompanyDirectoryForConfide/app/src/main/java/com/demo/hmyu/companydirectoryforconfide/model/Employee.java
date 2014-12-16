package com.demo.hmyu.companydirectoryforconfide.model;

/**
 * Created by Hsiang-Min on 12/15/14.
 */
public class Employee {

//    {
//        "first_name": "Anne",
//            "last_name": "Carr",
//            "title": "Product Infrastructure Associate",
//            "emails": {
//        "work": "acarr0@bloomberg.com",
//                "personal": "acarr0@berkeley.edu"
//    },
//        "website": "http://www.mockaroo.com/schemas/7061",
//            "social": {
//        "twitter": "@so",
//                "facebook": null
//    },
//        "birthday": null,
//            "photo_url": "https://s3.amazonaws.com/uifaces/faces/twitter/michaelcomiskey/128.jpg",
//            "phones": {
//        "work": "180-137-3989",
//                "personal": null
//    }
//    },

    private String first_name;
    private String last_name;
    private String title;
    private Emails emails;
    private String website;
    private Social social;
    private String birthday;
    private String photo_url;
    private Phones phones;
   

    public String getFirst_name() {
        if(first_name==null){
            first_name = "";
        }
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        if(last_name==null){
            last_name = "";
        }
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Emails getEmails() {
        return emails;
    }

    protected void setEmails(Emails emails) {
        this.emails = emails;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Social getSocial() {
        return social;
    }

    public void setSocial(Social social) {
        this.social = social;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPhoto_url() {
        return photo_url;
    }

    public void setPhoto_url(String photo_url) {
        this.photo_url = photo_url;
    }

    public Phones getPhones() {
        return phones;
    }

    public void setPhones(Phones phones) {
        this.phones = phones;
    }


    public Employee() {
    }

    public static class Emails {
        public String getWork() {
            return work;
        }

        public void setWork(String work) {
            this.work = work;
        }

        public String getPersonal() {
            return personal;
        }

        public void setPersonal(String personal) {
            this.personal = personal;
        }

        private String work;
        private String personal;

    }

    public static class Social {
        public String getTwitter() {
            return twitter;
        }

        public void setTwitter(String twitter) {
            this.twitter = twitter;
        }

        public String getFacebook() {
            return facebook;
        }

        public void setFacebook(String facebook) {
            this.facebook = facebook;
        }

        private String twitter;
        private String facebook;
    }

    public static class Phones {
        public String getWork() {
            return work;
        }

        public void setWork(String work) {
            this.work = work;
        }

        public String getPersonal() {
            return personal;
        }

        public void setPersonal(String personal) {
            this.personal = personal;
        }

        private String work;
        private String personal;
    }

}
