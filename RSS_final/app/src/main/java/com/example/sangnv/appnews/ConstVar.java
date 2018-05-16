package com.example.sangnv.appnews;

public class ConstVar {

    public static final String[] VNEXPRESS_LINKS = {
            "http://vnexpress.net/rss/tin-moi-nhat.rss",
            "http://vnexpress.net/rss/thoi-su.rss",
            "http://vnexpress.net/rss/the-gioi.rss",
            "http://vnexpress.net/rss/the-thao.rss",
            "http://vnexpress.net/rss/gia-dinh.rss",
            "http://vnexpress.net/rss/du-lich.rss",
            "http://vnexpress.net/rss/khoa-hoc.rss",
            "http://vnexpress.net/rss/so-hoa.rss",
    };

    public static final String[] VNEXPRESS_KEY = {
            "Tin mới",
            "Thời sự",
            "Thế giới",
            "Thể thao",
            "Gia đình",
            "Du lịch",
            "Khoa học",
            "Số hóa",
    };

    /*public static final String[] VNNET_LINKS = {
            "http://vietnamnet.vn/rss/tin-noi-bat.rss",
            "http://vietnamnet.vn/rss/the-thao.rss",
            "http://vietnamnet.vn/rss/cong-nghe.rss",
            "http://vietnamnet.vn/rss/kinh-doanh.rss",
            "http://vietnamnet.vn/rss/giao-duc.rss",
            "http://vietnamnet.vn/rss/chinh-tri.rss",
            "http://vietnamnet.vn/rss/giai-tri.rss",
            "http://vietnamnet.vn/rss/suc-khoe.rss",
            "http://vietnamnet.vn/rss/doi-song.rss",
            "http://vietnamnet.vn/rss/quoc-te.rss",
            "http://vietnamnet.vn/rss/goc-nhin-thang.rss"
    };

    public static final String[] VNNET_KEY = {
            "Tin mới nhất",
            "Thể thao",
            "Công nghệ",
            "Kinh doanh",
            "Giáo dục",
            "Chính trị",
            "Giải trí",
            "Sức khỏe",
            "Đời sống",
            "Quốc tế",
            "Góc nhìn thẳng",
    };*/

    public static final String[] DANTRI_LINKS = {
            "http://dantri.com.vn/trangchu.rss",
            "http://dantri.com.vn/the-thao.rss",
            "http://dantri.com.vn/suc-manh-so.rss",
            "http://dantri.com.vn/xa-hoi.rss",
            "http://dantri.com.vn/giai-tri.rss",
            "http://dantri.com.vn/giao-duc-khuyen-hoc.rss",
            "http://dantri.com.vn/the-gioi.rss",
            "http://dantri.com.vn/kinh-doanh.rss",
            "http://dantri.com.vn/chuyen-la.rss",
    };

    public static final String[]DANTRI_KEY = {
            "Trang chủ",
            "Thể thao",
            "Công nghệ",
            "Xã hội",
            "Giải trí",
            "Giáo dục",
            "Thế giới",
            "Kinh doanh",
            "Chuyện lạ",
    };

    public static final String[] NLD_LINKS = {
        "http://nld.com.vn/tin-moi-nhat.rss",
        "http://nld.com.vn/thoi-su-trong-nuoc.rss",
        "http://nld.com.vn/thoi-su-quoc-te.rss",
        "http://nld.com.vn/kinh-te.rss",
        "http://nld.com.vn/giao-duc-khoa-hoc.rss",
        "http://nld.com.vn/phap-luat.rss",
        "http://nld.com.vn/the-thao.rss",
        "http://nld.com.vn/suc-khoe.rss",
        "http://nld.com.vn/cong-nghe-thong-tin.rss",
        "http://nld.com.vn/thu-gian.rss"
    };

    public static final String[] NLD_KEY = {
        "Tin mới nhất",
        "Thời sự trong nước",
        "Thời sự quốc tế",
        "Kinh tế",
        "Khoa học",
        "Pháp luật",
        "Thể thao",
        "Sức khỏe",
        "Công nghệ",
        "Thư giãn",
    };

    public static final String[] VOV_LINK = {
        "https://vov.vn/rss/the-thao-214.rss",
        "https://vov.vn/rss/cong-nghe-449.rss",
        "https://vov.vn/rss/chinh-tri-209.rss",
        "https://vov.vn/rss/the-gioi-213.rss",
        "https://vov.vn/rss/phap-luat-237.rss",
        "https://vov.vn/rss/kinh-te-212.rss",
        "https://vov.vn/rss/suc-khoe-311.rss",
        "https://vov.vn/rss/quan-su-quoc-phong-445.rss",
        "https://vov.vn/rss/doi-song-218.rss",
    };
    public static final String[] VOV_KEY = {
            "Thể thao",
            "Công nghệ",
            "Chính trị",
            "Thế giới",
            "Pháp luật",
            "Kinh tế",
            "Sức khỏe",
            "Quân sự",
            "Đời sống"
    };

    public static final String[] LIST_WEB_NAME = {
            "VnExpress",
            "Dân trí",
            "Người lao động",
            "VOV",
            "Đã lưu"
    };

    public static final String[] DA_LUU_LINK = {
            "da_luu"
    };
    public static final String[] DA_LUU_KEY = {
            "Đã lưu"
    };


    public static final String[][] LIST_LINK = {
            VNEXPRESS_LINKS, DANTRI_LINKS,
            NLD_LINKS, VOV_LINK, DA_LUU_LINK
    };
    public static final String[][] LIST_KEY = {
            VNEXPRESS_KEY, DANTRI_KEY,
            NLD_KEY, VOV_KEY, DA_LUU_KEY
    };
}
