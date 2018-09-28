package aliyun.api.request;

import java.util.List;
import java.util.Map;

/**
 * 表单请求体
 */
public class FormRequest extends BaseRequest {

    //FORM表单参数
    private Map<String, String> bodys;

    public FormRequest(){
        super();
    }

    public FormRequest(String path, Map<String, String> headers, List<String> signHeaders, Map<String, String> querys, Map<String, String> bodys){
        super(path, headers, signHeaders, querys);
        this.bodys = bodys;
    }

    public Map<String, String> getBodys() {
        return bodys;
    }

    public void setBodys(Map<String, String> bodys) {
        this.bodys = bodys;
    }
}
