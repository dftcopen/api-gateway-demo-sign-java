package aliyun.api.request;

import java.util.List;
import java.util.Map;

/**
 * 字节流请求体
 */
public class ByteRequest extends BaseRequest {

     // 字节流参数
    private byte[] bodys;

    public ByteRequest(){
        super();
    }

    public ByteRequest(String path, Map<String, String> headers, List<String> signHeaders, Map<String, String> querys, byte[] bodys){
        super(path, headers, signHeaders, querys);
        this.bodys = bodys;
    }

    public byte[] getBodys() {
        return bodys;
    }

    public void setBodys(byte[] bodys) {
        this.bodys = bodys;
    }
}
