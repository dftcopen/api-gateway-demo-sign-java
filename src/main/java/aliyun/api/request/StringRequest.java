package aliyun.api.request;

import java.util.List;
import java.util.Map;

/**
 * 字符串请求体
 */
public class StringRequest extends BaseRequest{

    //字符串参数
    private String bodys;

    public StringRequest(){
        super();
    }

    public StringRequest(String path, Map<String, String> headers, List<String> signHeaders, Map<String, String> querys, String bodys){
        super(path, headers, signHeaders, querys);
        this.bodys = bodys;
    }

    public String getBodys() {
        return bodys;
    }

    public void setBodys(String bodys) {
        this.bodys = bodys;
    }
}
