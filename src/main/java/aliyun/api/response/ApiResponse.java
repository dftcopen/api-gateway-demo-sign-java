package aliyun.api.response;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 响应体
 */
public class ApiResponse {
    // 请求是否成功
    private boolean isSuccess;
    // 请求返回的headers
    private Map<String, String> headers;
    // 流
    private InputStream byteStream;
    // 状态码
    private int code;
    // 消息
    private String message;
    // 返回信息体
    private String body;

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, List<String>> headers) {
        Map<String, String> tempHeader = new HashMap<>();
        if (null != headers){
            for (Map.Entry<String, List<String>> e : headers.entrySet()) {
                tempHeader.put(e.getKey(),String.join(",", e.getValue()));
            }
        }
        this.headers = tempHeader;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public InputStream getByteStream() {
        return byteStream;
    }

    public void setByteStream(InputStream byteStream) {
        this.byteStream = byteStream;
    }
}
