package aliyun.api.utils;

import aliyun.api.config.AliYunApiConfig;
import aliyun.api.response.ApiResponse;
import okhttp3.*;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class HttpClient {

    private static OkHttpClient client;
    private static final int CONNECTION_ERROR = 500;

    /**
     * 初始化OkHttpClient
     *
     * @param config 配置信息
     */
    public HttpClient(AliYunApiConfig config) {
        if (null == client) {
            client = new OkHttpClient.Builder()
                    .connectTimeout(config.getConnectionTimeOut(), TimeUnit.SECONDS)
                    .readTimeout(config.getReadTimeOut(), TimeUnit.SECONDS)
                    .writeTimeout(config.getWriteTimeOut(), TimeUnit.SECONDS)
                    .build();
        }
    }

    /**
     * 添加header
     *
     * @param headers header参数
     * @return request builder
     */
    public static Request.Builder addHeaders(Map<String, String> headers) {
        Request.Builder builder = new Request.Builder();
        for (Map.Entry<String, String> e : headers.entrySet()) {
            builder.addHeader(e.getKey(), DigestUtil.utf8ToIso88591(e.getValue()));
        }
        return builder;
    }

    /**
     * 发送GET请求
     *
     * @param url     请求的地址
     * @param builder request builder
     * @return 返回信息
     * @throws IOException 异常
     */
    public static ApiResponse sendGet(String url, Request.Builder builder) throws IOException {
        Request request = builder.url(url).get().build();
        Response response = client.newCall(request).execute();
        return getBodyString(response);
    }

    /**
     * 发送POST FORM表单
     *
     * @param url     请求地址
     * @param body    提交内容
     * @param builder request builder
     * @return 返回信息
     * @throws IOException 异常
     */
    public static ApiResponse sendPostForm(String url, Map<String, String> body, Request.Builder builder) throws IOException {
        FormBody.Builder formBuilder = new FormBody.Builder();
        for (Map.Entry<String, String> e : body.entrySet()) {
            formBuilder.add(e.getKey(), DigestUtil.utf8ToIso88591(e.getValue()));
        }
        FormBody formBody = formBuilder.build();
        Request request = builder.url(url).post(formBody).build();
        Response response = client.newCall(request).execute();
        return  getBodyString(response);
    }

    /**
     * POST STRING 请求
     *
     * @param url         请求地址
     * @param body        提交的内容
     * @param builder     request builder
     * @param contentType 内容格式
     * @return 返回信息
     * @throws IOException 异常
     */
    public static ApiResponse sendPostString(String url, String body, Request.Builder builder, String contentType) throws IOException {
        return sendPost(url, null, body, contentType, builder);
    }

    /**
     * POST BYTE 请求
     *
     * @param url         请求地址
     * @param body        提交内容
     * @param builder     request builder
     * @param contentType 内容格式
     * @return 返回信息
     * @throws IOException 异常
     */
    public static ApiResponse sendPostBytes(String url, byte[] body, Request.Builder builder, String contentType) throws IOException {
        return sendPost(url, body, null, contentType, builder);
    }

    /**
     * PUT STRING 请求
     *
     * @param url         请求地址
     * @param body        提交的内容
     * @param builder     request builder
     * @param contentType 内容格式
     * @return 返回信息
     * @throws IOException 异常
     */
    public static ApiResponse sendPutString(String url, String body, Request.Builder builder, String contentType) throws IOException {
        return sendPut(url, null, body, contentType, builder);
    }

    /**
     * PUT BYTE 请求
     *
     * @param url         请求地址
     * @param body        提交内容
     * @param builder     request builder
     * @param contentType 内容格式
     * @return 返回信息
     * @throws IOException 异常
     */
    public static ApiResponse sendPutBytes(String url, byte[] body, Request.Builder builder, String contentType) throws IOException {
        return sendPut(url, body, null, contentType, builder);
    }

    /**
     * DELETE 请求
     *
     * @param url     请求地址
     * @param builder request builder
     * @return 返回信息
     * @throws IOException 异常
     */
    public static ApiResponse sendDelete(String url, Request.Builder builder) throws IOException {
        Request request = builder.url(url).delete().build();
        Response response = client.newCall(request).execute();
        return getBodyString(response);
    }

    /**
     * POST 请求
     *
     * @param url         请求地址
     * @param buff        请求内容
     * @param str         请求内容
     * @param contentType 内容格式
     * @param builder     request builder
     * @return 返回信息
     * @throws IOException 异常
     */
    private static ApiResponse sendPost(String url, byte[] buff, String str, String contentType, Request.Builder builder) throws IOException {
        RequestBody requestBody;
        if (null != buff) {
            requestBody = RequestBody.create(MediaType.parse(contentType), buff);
        } else {
            requestBody = RequestBody.create(MediaType.parse(contentType), str);
        }
        Request request = builder.url(url).post(requestBody).build();
        Response response = client.newCall(request).execute();
        return getBodyString(response);
    }

    /**
     * PUT 请求
     *
     * @param url         请求地址
     * @param buff        请求内容
     * @param str         请求内容
     * @param contentType 内容格式
     * @param builder     request builder
     * @return 返回信息
     * @throws IOException 异常
     */
    private static ApiResponse sendPut(String url, byte[] buff, String str, String contentType, Request.Builder builder) throws IOException {
        RequestBody requestBody;
        if (null != buff) {
            requestBody = RequestBody.create(MediaType.parse(contentType), buff);
        } else {
            requestBody = RequestBody.create(MediaType.parse(contentType), str);
        }
        Request request = builder.url(url).put(requestBody).build();
        Response response = client.newCall(request).execute();
        return getBodyString(response);
    }

    /**
     * 获取请求之后返回的body中的字符串
     *
     * @param response 请求体
     * @return 返回信息
     */
    private static ApiResponse getBodyString(Response response) throws IOException {
        ApiResponse apiResponse = new ApiResponse();
        if (null != response) {
            try {
                apiResponse.setMessage(response.message());
                apiResponse.setCode(response.code());
                apiResponse.setHeaders(response.headers().toMultimap());
                apiResponse.setSuccess(response.isSuccessful());
                ResponseBody responseBody = response.body();
                if (response.isSuccessful() && null != responseBody) {
                    apiResponse.setBody(responseBody.string());
                    apiResponse.setByteStream(responseBody.byteStream());
                }
            } finally {
                response.close();
            }
        } else {
            apiResponse.setCode(CONNECTION_ERROR);
            apiResponse.setMessage("connection error");
        }
        return apiResponse;
    }
}