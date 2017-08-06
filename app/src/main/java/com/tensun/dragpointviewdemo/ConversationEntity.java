package com.tensun.dragpointviewdemo;

/**
 * Created by lijinfeng on 2017/7/26.
 */

public class ConversationEntity {

    /** 預設的文本內容樣品 */
    public static final String TEST_JSON = "[" +

            "{\"username\":\"1\",\"nickname\":\"Google\",\"avatar\":" +
            "\"https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcRRSx_RdQ7hWD51PMaSQ-DWVNSo6AO5fvNyntBSlYx2mPdy3GOq\",\"messageN" +
            "um\":34,\"lastMessage\":\"The latest challenge to Google's AI dominance comes from an unlikely place -- Firefox\",\"lastTime\":\"03:16\",\"isRead\":fa" +
            "lse}," +

            "{\"username\":\"2\",\"nickname\":\"Apple\",\"avatar\":\"https://cdn2-techbang.pixfs.net/system/images/198471/original/ce26ab20ecf07c084b08df86503cfaa4.png?1421726222" +
            "ky.com/600x-/uploadImages/upload/20140909/upload/201409/0txy3iz3byujpg.jpg\",\"messa" +
            "geNum\":37,\"lastMessage\":\"Apple planning a cellular-capable Watch that cuts reliance on iPhone: report\",\"lastTime\":\"13:17\",\"isRea" +
            "d\":false}," +

            "{\"username\":\"3\",\"nickname\":\"Amazon\",\"avatar\":\"http://data.1freewallpapers.com/" +
            "download/amazon-logo-brand-company-1400x1050.jpg\",\"messageNum\":15,\"lastMessage\":\"Why didn't" +
            "What's the One Word Burning Up Earnings Calls This Quarter? 'Amazon'\",\"lastTime\":\"11:34\",\"isRead\":false}," +

            "{\"username\":\"4\",\"nickname\":\"Microsoft\",\"avatar\":\"http://www.linkconsulting.es/" +
            "wp-content/uploads/2015/12/microsoft-1.jpg\",\"messageNum\":10,\"lastMessage\":\"Why didn't" +
            "Sales on the Google Daydream View, Microsoft Surface Book, and more of the best tech deals\",\"lastTime\":\"12:47\",\"isRead\":false}," +

            "{\"username\":\"5\",\"nickname\":\"AT&T\",\"avatar\":\"https://4.bp.blogspot.com/-RvR6eCl5KPo/VjjbEuymHOI/AAAAAAAAGw0/" +
            "gijwMfZ1q80/s1600/bitebrands---logo-perusahaan-dunia-77.jpg\",\"messageNum\":27,\"lastMessage\":\"Why didn't" +
            "AT&T to debut an entertainment-driven store Monday in Newport Beach\",\"lastTime\":\"02:57\",\"isRead\":false}," +

            "{\"username\":\"6\",\"nickname\":\"Facebook\",\"avatar\":\"http://wallpapercave.com/" +
            "wp/Tvdhcxl.jpg\",\"messageNum\":32,\"lastMessage\":\"Why didn't" +
            "Facebook's translations are now powered completely by AI\",\"lastTime\":\"22:42\",\"isRead\":false}," +

            "{\"username\":\"7\",\"nickname\":\"IBM\",\"avatar\":\"http://images.indianexpress.com/2017/05/" +
            "ibm-logo-759.jpg\",\"messageNum\":29,\"lastMessage\":\"Why didn't" +
            "IBM and Sony cram up to 330 terabytes into tiny tape cartridge\",\"lastTime\":\"19:49\",\"isRead\":false}," +
            "]";

    private String username;
    private String nickname;
    private String avatar;
    private int messageNum;
    private String lastMessage;
    private String lastTime;
    private boolean isRead;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getMessageNum() {
        return messageNum;
    }

    public void setMessageNum(int messageNum) {
        this.messageNum = messageNum;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public String getLastTime() {
        return lastTime;
    }

    public void setLastTime(String lastTime) {
        this.lastTime = lastTime;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public void setRead(){
        setRead(true);
        setMessageNum(0);
    }
}
