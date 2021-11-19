package com.example.mymoviememoir.bean;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.mymoviememoir.utils.CastTypeConverter;
import com.example.mymoviememoir.utils.DirectTypeConverter;
import com.example.mymoviememoir.utils.ImageTypeConverter;
import com.example.mymoviememoir.utils.RatingTypeConverter;
import com.example.mymoviememoir.utils.StringTypeConverter;

import java.io.Serializable;
import java.util.List;

public class MovieBean {

    /**
     * count : 20
     * start : 0
     * total : 6
     * subjects : [{"rating":{"max":10,"average":8.2,"details":{"1":13,"3":2602,"2":123,"5":5193,"4":10975},"stars":"45","min":0},"genres":["剧情"],"title":"理查德·朱维尔的哀歌","casts":[{"avatars":{"small":"https://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1579450902.87.webp","large":"https://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1579450902.87.webp","medium":"https://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1579450902.87.webp"},"name_en":"Paul Walter Hauser","name":"保罗·沃尔特·豪泽","alt":"https://movie.douban.com/celebrity/1268250/","id":"1268250"},{"avatars":{"small":"https://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1533802988.44.webp","large":"https://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1533802988.44.webp","medium":"https://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1533802988.44.webp"},"name_en":"Sam Rockwell","name":"山姆·洛克威尔","alt":"https://movie.douban.com/celebrity/1047972/","id":"1047972"},{"avatars":{"small":"https://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p5690.webp","large":"https://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p5690.webp","medium":"https://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p5690.webp"},"name_en":"Kathy Bates","name":"凯西·贝茨","alt":"https://movie.douban.com/celebrity/1010555/","id":"1010555"}],"durations":["131分钟"],"collect_count":73067,"mainland_pubdate":"2020-01-10","has_video":true,"original_title":"Richard Jewell","subtype":"movie","directors":[{"avatars":{"small":"https://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1438777188.48.webp","large":"https://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1438777188.48.webp","medium":"https://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1438777188.48.webp"},"name_en":"Clint Eastwood","name":"克林特·伊斯特伍德","alt":"https://movie.douban.com/celebrity/1054436/","id":"1054436"}],"pubdates":["2019-11-20(AFI Fest)","2019-12-13(美国)","2020-01-10(中国大陆)"],"year":"2019","images":{"small":"https://img9.doubanio.com/view/photo/s_ratio_poster/public/p2578705064.webp","large":"https://img9.doubanio.com/view/photo/s_ratio_poster/public/p2578705064.webp","medium":"https://img9.doubanio.com/view/photo/s_ratio_poster/public/p2578705064.webp"},"alt":"https://movie.douban.com/subject/25842038/","id":"25842038"},{"rating":{"max":10,"average":7.5,"details":{"1":30,"3":3443,"2":274,"5":1413,"4":4824},"stars":"40","min":0},"genres":["喜剧","动作","科幻"],"title":"变身特工","casts":[{"avatars":{"small":"https://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p41483.webp","large":"https://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p41483.webp","medium":"https://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p41483.webp"},"name_en":"Will Smith","name":"威尔·史密斯","alt":"https://movie.douban.com/celebrity/1027138/","id":"1027138"},{"avatars":{"small":"https://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1467942867.09.webp","large":"https://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1467942867.09.webp","medium":"https://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1467942867.09.webp"},"name_en":"Tom Holland","name":"汤姆·赫兰德","alt":"https://movie.douban.com/celebrity/1325351/","id":"1325351"},{"avatars":{"small":"https://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p32735.webp","large":"https://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p32735.webp","medium":"https://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p32735.webp"},"name_en":"Rashida Jones","name":"拉什达·琼斯","alt":"https://movie.douban.com/celebrity/1031815/","id":"1031815"}],"durations":["102分钟"],"collect_count":54517,"mainland_pubdate":"2020-01-03","has_video":false,"original_title":"Spies in Disguise","subtype":"movie","directors":[{"avatars":{"small":"https://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1566284127.48.webp","large":"https://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1566284127.48.webp","medium":"https://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1566284127.48.webp"},"name_en":"Nick Bruno","name":"尼克·布鲁诺","alt":"https://movie.douban.com/celebrity/1408895/","id":"1408895"},{"avatars":{"small":"https://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1557994632.66.webp","large":"https://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1557994632.66.webp","medium":"https://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1557994632.66.webp"},"name_en":"Troy Quane","name":"特洛伊·奎安","alt":"https://movie.douban.com/celebrity/1408896/","id":"1408896"}],"pubdates":["2019-12-04(加州首映)","2019-12-25(美国)","2020-01-03(中国大陆)"],"year":"2019","images":{"small":"https://img9.doubanio.com/view/photo/s_ratio_poster/public/p2577340942.webp","large":"https://img9.doubanio.com/view/photo/s_ratio_poster/public/p2577340942.webp","medium":"https://img9.doubanio.com/view/photo/s_ratio_poster/public/p2577340942.webp"},"alt":"https://movie.douban.com/subject/27000084/","id":"27000084"},{"rating":{"max":10,"average":5.6,"details":{"1":148,"3":1299,"2":758,"5":63,"4":351},"stars":"30","min":0},"genres":["科幻","惊悚"],"title":"灭绝","casts":[{"avatars":{"small":"https://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1454118774.76.webp","large":"https://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1454118774.76.webp","medium":"https://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1454118774.76.webp"},"name_en":"Michael Peña","name":"迈克尔·佩纳","alt":"https://movie.douban.com/celebrity/1131634/","id":"1131634"},{"avatars":{"small":"https://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1386855236.97.webp","large":"https://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1386855236.97.webp","medium":"https://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1386855236.97.webp"},"name_en":"Lizzy Caplan","name":"丽兹·卡潘","alt":"https://movie.douban.com/celebrity/1009234/","id":"1009234"},{"avatars":{"small":"https://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1509274635.63.webp","large":"https://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1509274635.63.webp","medium":"https://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1509274635.63.webp"},"name_en":"Israel Broussard","name":"伊瑟尔·布罗萨德","alt":"https://movie.douban.com/celebrity/1023036/","id":"1023036"}],"durations":["95分钟","93分钟(中国大陆)"],"collect_count":17552,"mainland_pubdate":"2020-01-18","has_video":true,"original_title":"Extinction","subtype":"movie","directors":[{"avatars":{"small":"https://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/puqS3biE9tVocel_avatar_uploaded1494750717.23.webp","large":"https://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/puqS3biE9tVocel_avatar_uploaded1494750717.23.webp","medium":"https://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/puqS3biE9tVocel_avatar_uploaded1494750717.23.webp"},"name_en":"Ben Young","name":"本·扬","alt":"https://movie.douban.com/celebrity/1373883/","id":"1373883"}],"pubdates":["2018-07-27(美国)","2020-01-18(中国大陆)"],"year":"2018","images":{"small":"https://img9.doubanio.com/view/photo/s_ratio_poster/public/p2579512247.webp","large":"https://img9.doubanio.com/view/photo/s_ratio_poster/public/p2579512247.webp","medium":"https://img9.doubanio.com/view/photo/s_ratio_poster/public/p2579512247.webp"},"alt":"https://movie.douban.com/subject/26871938/","id":"26871938"},{"rating":{"max":10,"average":0,"details":{"1":0,"3":0,"2":0,"5":0,"4":0},"stars":"00","min":0},"genres":["爱情","奇幻"],"title":"我在时间尽头等你","casts":[{"avatars":{"small":"https://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1506664202.44.webp","large":"https://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1506664202.44.webp","medium":"https://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1506664202.44.webp"},"name_en":"Hong-Chi Lee","name":"李鸿其","alt":"https://movie.douban.com/celebrity/1352153/","id":"1352153"},{"avatars":{"small":"https://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1465192722.92.webp","large":"https://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1465192722.92.webp","medium":"https://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1465192722.92.webp"},"name_en":"Yitong Li","name":"李一桐","alt":"https://movie.douban.com/celebrity/1354284/","id":"1354284"},{"avatars":{"small":"https://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1511362485.44.webp","large":"https://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1511362485.44.webp","medium":"https://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1511362485.44.webp"},"name_en":"Wei Fan","name":"范伟","alt":"https://movie.douban.com/celebrity/1051526/","id":"1051526"}],"durations":["115分钟"],"collect_count":311,"mainland_pubdate":"","has_video":false,"original_title":"我在时间尽头等你","subtype":"movie","directors":[{"avatars":{"small":"https://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p59253.webp","large":"https://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p59253.webp","medium":"https://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p59253.webp"},"name_en":"Tingting Yao","name":"姚婷婷","alt":"https://movie.douban.com/celebrity/1324487/","id":"1324487"}],"pubdates":["2020(中国大陆)"],"year":"2020","images":{"small":"https://img9.doubanio.com/view/photo/s_ratio_poster/public/p2585460940.webp","large":"https://img9.doubanio.com/view/photo/s_ratio_poster/public/p2585460940.webp","medium":"https://img9.doubanio.com/view/photo/s_ratio_poster/public/p2585460940.webp"},"alt":"https://movie.douban.com/subject/26661193/","id":"26661193"},{"rating":{"max":10,"average":7.5,"details":{"1":48,"3":1405,"2":234,"5":943,"4":1734},"stars":"40","min":0},"genres":["剧情","动画"],"title":"紫罗兰永恒花园外传：永远与自动手记人偶","casts":[{"avatars":{"small":"https://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1370586618.47.webp","large":"https://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1370586618.47.webp","medium":"https://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1370586618.47.webp"},"name_en":"Yui Ishikawa","name":"石川由依","alt":"https://movie.douban.com/celebrity/1329107/","id":"1329107"},{"avatars":{"small":"https://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p4964.webp","large":"https://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p4964.webp","medium":"https://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p4964.webp"},"name_en":"Minori Chihara","name":"茅原实里","alt":"https://movie.douban.com/celebrity/1042757/","id":"1042757"},{"avatars":{"small":"https://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p21931.webp","large":"https://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p21931.webp","medium":"https://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p21931.webp"},"name_en":"Aya Endô","name":"远藤绫","alt":"https://movie.douban.com/celebrity/1008446/","id":"1008446"}],"durations":["91分钟"],"collect_count":36969,"mainland_pubdate":"2020-01-10","has_video":true,"original_title":"ヴァイオレット・エヴァーガーデン 外伝 - 永遠と自動手記人形 -","subtype":"movie","directors":[{"avatars":{"small":"https://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1564396200.09.webp","large":"https://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1564396200.09.webp","medium":"https://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1564396200.09.webp"},"name_en":"Fujita Haruka","name":"藤田春香","alt":"https://movie.douban.com/celebrity/1420526/","id":"1420526"}],"pubdates":["2019-09-06(日本)","2020-01-10(中国大陆)"],"year":"2019","images":{"small":"https://img9.doubanio.com/view/photo/s_ratio_poster/public/p2578722076.webp","large":"https://img9.doubanio.com/view/photo/s_ratio_poster/public/p2578722076.webp","medium":"https://img9.doubanio.com/view/photo/s_ratio_poster/public/p2578722076.webp"},"alt":"https://movie.douban.com/subject/33424345/","id":"33424345"},{"rating":{"max":10,"average":5.1,"details":{"1":146,"3":679,"2":615,"5":32,"4":144},"stars":"25","min":0},"genres":["惊悚","冒险"],"title":"鲨海逃生","casts":[{"avatars":{"small":"https://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p10899.webp","large":"https://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p10899.webp","medium":"https://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p10899.webp"},"name_en":"Nia Long","name":"尼娅·朗","alt":"https://movie.douban.com/celebrity/1040570/","id":"1040570"},{"avatars":{"small":"https://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p2255.webp","large":"https://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p2255.webp","medium":"https://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p2255.webp"},"name_en":"John Corbett","name":"约翰·考伯特","alt":"https://movie.douban.com/celebrity/1044870/","id":"1044870"},{"avatars":{"small":"https://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1548144197.26.webp","large":"https://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1548144197.26.webp","medium":"https://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1548144197.26.webp"},"name_en":"Sophie Nélisse","name":"苏菲·奈丽丝","alt":"https://movie.douban.com/celebrity/1323944/","id":"1323944"}],"durations":["89分钟"],"collect_count":14440,"mainland_pubdate":"2020-01-10","has_video":true,"original_title":"47 Meters Down: Uncaged","subtype":"movie","directors":[{"avatars":{"small":"https://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1498455992.67.webp","large":"https://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1498455992.67.webp","medium":"https://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1498455992.67.webp"},"name_en":"Johannes Roberts","name":"约翰内斯·罗伯茨","alt":"https://movie.douban.com/celebrity/1019388/","id":"1019388"}],"pubdates":["2019-08-16(美国)","2020-01-10(中国大陆)"],"year":"2019","images":{"small":"https://img9.doubanio.com/view/photo/s_ratio_poster/public/p2578721161.webp","large":"https://img9.doubanio.com/view/photo/s_ratio_poster/public/p2578721161.webp","medium":"https://img9.doubanio.com/view/photo/s_ratio_poster/public/p2578721161.webp"},"alt":"https://movie.douban.com/subject/27186353/","id":"27186353"}]
     * title : 正在上映的电影-北京
     */

    private int count;
    private int start;
    private int total;
    private String title;
    private List<SubjectsBean> subjects;

    public MovieBean() {

    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<SubjectsBean> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<SubjectsBean> subjects) {
        this.subjects = subjects;
    }

    @Entity
    public static class SubjectsBean implements Serializable {
        /**
         * rating : {"max":10,"average":8.2,"details":{"1":13,"3":2602,"2":123,"5":5193,"4":10975},"stars":"45","min":0}
         * genres : ["剧情"]
         * title : 理查德·朱维尔的哀歌
         * casts : [{"avatars":{"small":"https://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1579450902.87.webp","large":"https://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1579450902.87.webp","medium":"https://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1579450902.87.webp"},"name_en":"Paul Walter Hauser","name":"保罗·沃尔特·豪泽","alt":"https://movie.douban.com/celebrity/1268250/","id":"1268250"},{"avatars":{"small":"https://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1533802988.44.webp","large":"https://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1533802988.44.webp","medium":"https://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1533802988.44.webp"},"name_en":"Sam Rockwell","name":"山姆·洛克威尔","alt":"https://movie.douban.com/celebrity/1047972/","id":"1047972"},{"avatars":{"small":"https://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p5690.webp","large":"https://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p5690.webp","medium":"https://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p5690.webp"},"name_en":"Kathy Bates","name":"凯西·贝茨","alt":"https://movie.douban.com/celebrity/1010555/","id":"1010555"}]
         * durations : ["131分钟"]
         * collect_count : 73067
         * mainland_pubdate : 2020-01-10
         * has_video : true
         * original_title : Richard Jewell
         * subtype : movie
         * directors : [{"avatars":{"small":"https://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1438777188.48.webp","large":"https://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1438777188.48.webp","medium":"https://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1438777188.48.webp"},"name_en":"Clint Eastwood","name":"克林特·伊斯特伍德","alt":"https://movie.douban.com/celebrity/1054436/","id":"1054436"}]
         * pubdates : ["2019-11-20(AFI Fest)","2019-12-13(美国)","2020-01-10(中国大陆)"]
         * year : 2019
         * images : {"small":"https://img9.doubanio.com/view/photo/s_ratio_poster/public/p2578705064.webp","large":"https://img9.doubanio.com/view/photo/s_ratio_poster/public/p2578705064.webp","medium":"https://img9.doubanio.com/view/photo/s_ratio_poster/public/p2578705064.webp"}
         * alt : https://movie.douban.com/subject/25842038/
         * id : 25842038
         */

        @TypeConverters(RatingTypeConverter.class)
        private RatingBean rating;

        private String title;
        private int collect_count;
        private String mainland_pubdate;
        private boolean has_video;
        private String original_title;
        private String subtype;
        private String year;

        @TypeConverters(ImageTypeConverter.class)
        private ImagesBean images;

        private String alt;

        @NonNull
        @PrimaryKey
        private String id;

        private String userName;

        @TypeConverters(StringTypeConverter.class)
        private List<String> genres;

        @TypeConverters(CastTypeConverter.class)
        private List<CastsBean> casts;

        @TypeConverters(StringTypeConverter.class)
        private List<String> durations;

        @TypeConverters(DirectTypeConverter.class)
        private List<DirectorsBean> directors;

        @TypeConverters(StringTypeConverter.class)
        private List<String> pubdates;


        private int isGallery;

        private int isWatch;

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public int getIsGallery() {
            return isGallery;
        }

        public void setIsGallery(int isGallery) {
            this.isGallery = isGallery;
        }

        public int getIsWatch() {
            return isWatch;
        }

        public void setIsWatch(int isWatch) {
            this.isWatch = isWatch;
        }

        public SubjectsBean() {

        }

        public RatingBean getRating() {
            return rating;
        }

        public void setRating(RatingBean rating) {
            this.rating = rating;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getCollect_count() {
            return collect_count;
        }

        public void setCollect_count(int collect_count) {
            this.collect_count = collect_count;
        }

        public String getMainland_pubdate() {
            return mainland_pubdate;
        }

        public void setMainland_pubdate(String mainland_pubdate) {
            this.mainland_pubdate = mainland_pubdate;
        }

        public boolean isHas_video() {
            return has_video;
        }

        public void setHas_video(boolean has_video) {
            this.has_video = has_video;
        }

        public String getOriginal_title() {
            return original_title;
        }

        public void setOriginal_title(String original_title) {
            this.original_title = original_title;
        }

        public String getSubtype() {
            return subtype;
        }

        public void setSubtype(String subtype) {
            this.subtype = subtype;
        }

        public String getYear() {
            return year;
        }

        public void setYear(String year) {
            this.year = year;
        }

        public ImagesBean getImages() {
            return images;
        }

        public void setImages(ImagesBean images) {
            this.images = images;
        }

        public String getAlt() {
            return alt;
        }

        public void setAlt(String alt) {
            this.alt = alt;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public List<String> getGenres() {
            return genres;
        }

        public void setGenres(List<String> genres) {
            this.genres = genres;
        }

        public List<CastsBean> getCasts() {
            return casts;
        }

        public void setCasts(List<CastsBean> casts) {
            this.casts = casts;
        }

        public List<String> getDurations() {
            return durations;
        }

        public void setDurations(List<String> durations) {
            this.durations = durations;
        }

        public List<DirectorsBean> getDirectors() {
            return directors;
        }

        public void setDirectors(List<DirectorsBean> directors) {
            this.directors = directors;
        }

        public List<String> getPubdates() {
            return pubdates;
        }

        public void setPubdates(List<String> pubdates) {
            this.pubdates = pubdates;
        }

        public static class RatingBean implements Serializable{
            /**
             * max : 10
             * average : 8.2
             * details : {"1":13,"3":2602,"2":123,"5":5193,"4":10975}
             * stars : 45
             * min : 0
             */

            private int max;
            private double average;
            private String stars;
            private int min;

            public RatingBean() {

            }

            public int getMax() {
                return max;
            }

            public void setMax(int max) {
                this.max = max;
            }

            public double getAverage() {
                return average;
            }

            public void setAverage(double average) {
                this.average = average;
            }

            public String getStars() {
                return stars;
            }

            public void setStars(String stars) {
                this.stars = stars;
            }

            public int getMin() {
                return min;
            }

            public void setMin(int min) {
                this.min = min;
            }
        }

        public static class ImagesBean implements Serializable{
            /**
             * small : https://img9.doubanio.com/view/photo/s_ratio_poster/public/p2578705064.webp
             * large : https://img9.doubanio.com/view/photo/s_ratio_poster/public/p2578705064.webp
             * medium : https://img9.doubanio.com/view/photo/s_ratio_poster/public/p2578705064.webp
             */

            private String small;
            private String large;
            private String medium;

            public ImagesBean() {

            }

            public String getSmall() {
                return small;
            }

            public void setSmall(String small) {
                this.small = small;
            }

            public String getLarge() {
                return large;
            }

            public void setLarge(String large) {
                this.large = large;
            }

            public String getMedium() {
                return medium;
            }

            public void setMedium(String medium) {
                this.medium = medium;
            }
        }

        public static class CastsBean implements Serializable{
            /**
             * avatars : {"small":"https://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1579450902.87.webp","large":"https://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1579450902.87.webp","medium":"https://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1579450902.87.webp"}
             * name_en : Paul Walter Hauser
             * name : 保罗·沃尔特·豪泽
             * alt : https://movie.douban.com/celebrity/1268250/
             * id : 1268250
             */

            private AvatarsBean avatars;
            private String name_en;
            private String name;
            private String alt;
            private String id;

            public CastsBean() {

            }

            public AvatarsBean getAvatars() {
                return avatars;
            }

            public void setAvatars(AvatarsBean avatars) {
                this.avatars = avatars;
            }

            public String getName_en() {
                return name_en;
            }

            public void setName_en(String name_en) {
                this.name_en = name_en;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getAlt() {
                return alt;
            }

            public void setAlt(String alt) {
                this.alt = alt;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public static class AvatarsBean implements Serializable{
                /**
                 * small : https://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1579450902.87.webp
                 * large : https://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1579450902.87.webp
                 * medium : https://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1579450902.87.webp
                 */

                private String small;
                private String large;
                private String medium;

                public AvatarsBean() {

                }

                public String getSmall() {
                    return small;
                }

                public void setSmall(String small) {
                    this.small = small;
                }

                public String getLarge() {
                    return large;
                }

                public void setLarge(String large) {
                    this.large = large;
                }

                public String getMedium() {
                    return medium;
                }

                public void setMedium(String medium) {
                    this.medium = medium;
                }
            }
        }

        public static class DirectorsBean implements Serializable{
            /**
             * avatars : {"small":"https://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1438777188.48.webp","large":"https://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1438777188.48.webp","medium":"https://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1438777188.48.webp"}
             * name_en : Clint Eastwood
             * name : 克林特·伊斯特伍德
             * alt : https://movie.douban.com/celebrity/1054436/
             * id : 1054436
             */

            private AvatarsBeanX avatars;
            private String name_en;
            private String name;
            private String alt;
            private String id;

            public DirectorsBean() {

            }

            public AvatarsBeanX getAvatars() {
                return avatars;
            }

            public void setAvatars(AvatarsBeanX avatars) {
                this.avatars = avatars;
            }

            public String getName_en() {
                return name_en;
            }

            public void setName_en(String name_en) {
                this.name_en = name_en;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getAlt() {
                return alt;
            }

            public void setAlt(String alt) {
                this.alt = alt;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public static class AvatarsBeanX implements Serializable{
                /**
                 * small : https://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1438777188.48.webp
                 * large : https://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1438777188.48.webp
                 * medium : https://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1438777188.48.webp
                 */

                private String small;
                private String large;
                private String medium;

                public AvatarsBeanX() {

                }

                public String getSmall() {
                    return small;
                }

                public void setSmall(String small) {
                    this.small = small;
                }

                public String getLarge() {
                    return large;
                }

                public void setLarge(String large) {
                    this.large = large;
                }

                public String getMedium() {
                    return medium;
                }

                public void setMedium(String medium) {
                    this.medium = medium;
                }
            }
        }
    }
}
