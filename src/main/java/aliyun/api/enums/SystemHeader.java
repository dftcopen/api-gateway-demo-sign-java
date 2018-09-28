package aliyun.api.enums;

public enum SystemHeader {
    X_CA_SIGNATURE("X-Ca-Signature"),
    X_CA_SIGNATURE_HEADERS("X-Ca-Signature-Headers"),
    X_CA_TIMESTAMP("X-Ca-Timestamp"),
    X_CA_KEY("X-Ca-Key"),
    X_CA_REQUEST_MODE("X-Ca-Request-Mode");

    private String headerName;

    SystemHeader(String headerName){
        this.headerName = headerName;
    }

    public String getHeaderName() {
        return headerName;
    }

}
