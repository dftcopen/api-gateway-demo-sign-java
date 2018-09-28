package aliyun.api.request;

import java.util.List;
import java.util.Map;

/**
 * 基础请求体
 */
public class BaseRequest {
    // 请求接口
    private String path;
    // 自定义的头信息
    private Map<String, String> headers;
    // 自定义需要签名的头信息
    private List<String> signHeaders;
    // url参数
    private Map<String, String> querys;

    public BaseRequest(){}

    public BaseRequest(String path, Map<String, String> headers, List<String> signHeaders, Map<String, String> querys){
        this.path = path;
        this.headers = headers;
        this.signHeaders = signHeaders;
        this.querys = querys;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public Map<String, String> getQuerys() {
        return querys;
    }

    public void setQuerys(Map<String, String> querys) {
        this.querys = querys;
    }

    public List<String> getSignHeaders() {
        return signHeaders;
    }

    public void setSignHeaders(List<String> signHeaders) {
        this.signHeaders = signHeaders;
    }
}
