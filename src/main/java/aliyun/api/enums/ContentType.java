package aliyun.api.enums;

public enum ContentType {
    CONTENT_TYPE_FORM("application/x-www-form-urlencoded; charset=UTF-8"),
    CONTENT_TYPE_STREAM("application/octet-stream; charset=UTF-8"),
    CONTENT_TYPE_JSON("application/json; charset=UTF-8"),
    CONTENT_TYPE_XML("application/xml; charset=UTF-8"),
    CONTENT_TYPE_TEXT("application/text; charset=UTF-8");
    private String contentName;
    ContentType(String contentName){
        this.contentName = contentName;
    }

    public String getContentName() {
        return contentName;
    }

    public void setContentName(String contentName) {
        this.contentName = contentName;
    }
}
