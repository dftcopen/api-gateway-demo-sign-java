package aliyun.api;

import aliyun.api.request.BaseRequest;
import aliyun.api.request.ByteRequest;
import aliyun.api.request.FormRequest;
import aliyun.api.request.StringRequest;
import aliyun.api.response.ApiResponse;

public interface AliYunApiService {


    /**
     * GET请求
     *
     * @param request 请求体
     * @return 远程服务返回信息
     */
    ApiResponse sendGet(BaseRequest request) throws Exception;

    /**
     * POST提交表单请求
     *
     * @param request 请求体
     * @return 远程服务返回信息
     */
    ApiResponse sendPostForm(FormRequest request) throws Exception;

    /**
     * POST提交字符串请求
     *
     * @param request 请求体
     * @return 远程服务返回信息
     */
    ApiResponse sendPostString(StringRequest request) throws Exception;

    /**
     * POST提交字节流请求
     *
     * @param request 请求体
     * @return 远程服务返回信息
     */
    ApiResponse sendPostBytes(ByteRequest request) throws Exception;

    /**
     * PUT提交字符串请求
     *
     * @param request 请求体
     * @return 远程服务返回信息
     */
    ApiResponse sendPutString(StringRequest request) throws Exception;

    /**
     * PUT提交字节流请求
     *
     * @param request 请求体
     * @return 远程服务返回信息
     */
    ApiResponse sendPutBytes(ByteRequest request) throws Exception;

    /**
     * 发送DELETE请求
     *
     * @param request 请求体
     * @return 远程服务返回信息
     */
    ApiResponse sendDelete(BaseRequest request) throws Exception;

}
