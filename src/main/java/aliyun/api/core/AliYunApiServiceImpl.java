package aliyun.api.core;

import aliyun.api.AliYunApiService;
import aliyun.api.config.*;
import aliyun.api.enums.ContentType;
import aliyun.api.enums.SystemHeader;
import aliyun.api.request.BaseRequest;
import aliyun.api.request.ByteRequest;
import aliyun.api.request.FormRequest;
import aliyun.api.request.StringRequest;
import aliyun.api.response.ApiResponse;
import aliyun.api.utils.HttpClient;
import aliyun.api.utils.SignUtil;
import okhttp3.Request;
import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AliYunApiServiceImpl implements AliYunApiService {

    // 配置
    private final AliYunApiConfig config;

    /**
     * 初始化配置信息和OkHttpClient
     *
     * @param config 配置
     */
    public AliYunApiServiceImpl(AliYunApiConfig config) throws Exception {
        if (null != config) {
            new HttpClient(config);
            this.config = config;
        } else {
            throw new IllegalArgumentException("配置为空");
        }
    }

    /**
     * GET请求
     *
     * @param request 请求体
     * @return API返回的信息
     * @throws Exception 异常
     */
    public ApiResponse sendGet(BaseRequest request) throws Exception {
        ApiResponse result = null;
        Map<String, String> headers = request.getHeaders();
        if (vaildHeader(headers, HttpMethod.GET)) {
            headers = initBasicHeader(HttpMethod.GET, request.getPath(), headers, request.getQuerys(), null, request.getSignHeaders());
            String url = initUrl(config.getHost(), request.getPath(), request.getQuerys());
            Request.Builder builder = HttpClient.addHeaders(headers);
            result = HttpClient.sendGet(url, builder);
        }
        return result;
    }

    /**
     * POST Form请求
     *
     * @param request 请求体
     * @return API返回的信息
     * @throws Exception 异常
     */
    public ApiResponse sendPostForm(FormRequest request) throws Exception {
        ApiResponse result = null;
        Map<String, String> headers = request.getHeaders();
        if (vaildHeader(headers, HttpMethod.POST_FORM)) {
            headers.put(HttpHeader.HTTP_HEADER_CONTENT_TYPE, ContentType.CONTENT_TYPE_FORM.getContentName());
            headers = initBasicHeader(HttpMethod.POST, request.getPath(), headers, request.getQuerys(), request.getBodys(), request.getSignHeaders());
            String url = initUrl(config.getHost(), request.getPath(), request.getQuerys());
            Request.Builder builder = HttpClient.addHeaders(headers);
            result = HttpClient.sendPostForm(url, request.getBodys(), builder);
        }
        return result;
    }

    /**
     * POST String请求
     *
     * @param request 请求体
     * @return API返回的信息
     * @throws Exception 异常
     */
    public ApiResponse sendPostString(StringRequest request) throws Exception {
        ApiResponse result = null;
        Map<String, String> headers = request.getHeaders();
        if (vaildHeader(headers, HttpMethod.POST)) {
            headers = initBasicHeader(HttpMethod.POST, request.getPath(), headers, request.getQuerys(), null, request.getSignHeaders());
            String url = initUrl(config.getHost(), request.getPath(), request.getQuerys());
            Request.Builder builder = HttpClient.addHeaders(headers);
            result = HttpClient.sendPostString(url, request.getBodys(), builder, headers.get(HttpHeader.HTTP_HEADER_CONTENT_TYPE));
        }
        return result;
    }

    /**
     * POST byte请求
     *
     * @param request 请求体
     * @return API返回的信息
     * @throws Exception 异常
     */
    public ApiResponse sendPostBytes(ByteRequest request) throws Exception {
        ApiResponse result = null;
        Map<String, String> headers = request.getHeaders();
        if (vaildHeader(headers, HttpMethod.POST)) {
            headers = initBasicHeader(HttpMethod.POST, request.getPath(), headers, request.getQuerys(), null, request.getSignHeaders());
            String url = initUrl(config.getHost(), request.getPath(), request.getQuerys());
            Request.Builder builder = HttpClient.addHeaders(headers);
            result = HttpClient.sendPostBytes(url, request.getBodys(), builder, headers.get(HttpHeader.HTTP_HEADER_CONTENT_TYPE));
        }
        return result;
    }

    /**
     * PUT String请求
     *
     * @param request 请求体
     * @return API返回的信息
     * @throws Exception 异常
     */
    public ApiResponse sendPutString(StringRequest request) throws Exception {
        ApiResponse result = null;
        Map<String, String> headers = request.getHeaders();
        if (vaildHeader(headers, HttpMethod.PUT)) {
            headers = initBasicHeader(HttpMethod.PUT, request.getPath(), headers, request.getQuerys(), null, request.getSignHeaders());
            String url = initUrl(config.getHost(), request.getPath(), request.getQuerys());
            Request.Builder builder = HttpClient.addHeaders(headers);
            result = HttpClient.sendPutString(url, request.getBodys(), builder, headers.get(HttpHeader.HTTP_HEADER_CONTENT_TYPE));
        }
        return result;
    }

    /**
     * PUT byte请求
     *
     * @param request 请求体
     * @return API返回的信息
     * @throws Exception 异常
     */
    public ApiResponse sendPutBytes(ByteRequest request) throws Exception {
        ApiResponse result = null;
        Map<String, String> headers = request.getHeaders();
        if (vaildHeader(headers, HttpMethod.PUT)) {
            headers = initBasicHeader(HttpMethod.PUT, request.getPath(), headers, request.getQuerys(), null, request.getSignHeaders());
            String url = initUrl(config.getHost(), request.getPath(), request.getQuerys());
            Request.Builder builder = HttpClient.addHeaders(headers);
            result = HttpClient.sendPutBytes(url, request.getBodys(), builder, headers.get(HttpHeader.HTTP_HEADER_CONTENT_TYPE));
        }
        return result;
    }

    /**
     * DELETE请求
     *
     * @param request 请求体
     * @return API返回的信息
     * @throws Exception 异常
     */
    public ApiResponse sendDelete(BaseRequest request) throws Exception {
        ApiResponse result = null;
        Map<String, String> headers = request.getHeaders();
        if (vaildHeader(headers, HttpMethod.DELETE)) {
            headers = initBasicHeader(HttpMethod.DELETE, request.getPath(), headers, request.getQuerys(), null, request.getSignHeaders());
            String url = initUrl(config.getHost(), request.getPath(), request.getQuerys());
            Request.Builder builder = HttpClient.addHeaders(headers);
            result = HttpClient.sendDelete(url, builder);
        }
        return result;
    }

    /**
     * Header校验
     *
     * @param header 头信息
     * @param method 请求方法
     * @return 是否验证通过
     * @throws IllegalArgumentException 验证异常
     */
    private boolean vaildHeader(Map<String, String> header, String method) throws IllegalArgumentException {
        if (null == header || header.isEmpty()) {
            throw new IllegalArgumentException(method + "请求的header不能为空");
        }
        if (StringUtils.isBlank(header.get(HttpHeader.HTTP_HEADER_ACCEPT))) {
            throw new IllegalArgumentException(method + "请求的header中，" + HttpHeader.HTTP_HEADER_ACCEPT + "必须设置");
        }
        if ((method.equals(HttpMethod.POST) || method.equals(HttpMethod.PUT)) && StringUtils.isBlank(header.get(HttpHeader.HTTP_HEADER_CONTENT_TYPE))) {
            throw new IllegalArgumentException(method + "请求的header中，" + HttpHeader.HTTP_HEADER_CONTENT_TYPE + "必须设置");
        }
        return true;
    }

    /**
     * 初始化基础Header
     *
     * @param method     请求方法
     * @param path       请求接口
     * @param headers    请求header参数
     * @param querys     请求queryString参数
     * @param bodys      请求body参数
     * @param signHeader 需要自定义的请求header签名
     * @return 基础HEADER
     */
    private Map<String, String> initBasicHeader(String method, String path,
                                                Map<String, String> headers,
                                                Map<String, String> querys,
                                                Map<String, String> bodys,
                                                List<String> signHeader) {
        if (headers == null) {
            headers = new HashMap<>();
        }

        if (StringUtils.isNotBlank(config.getLevel())) {
            headers.put(SystemHeader.X_CA_REQUEST_MODE.getHeaderName(), config.getLevel());
        }

        headers.put(SystemHeader.X_CA_TIMESTAMP.getHeaderName(), String.valueOf(new Date().getTime()));
        headers.put(SystemHeader.X_CA_KEY.getHeaderName(), config.getAppKey());
        headers.put(SystemHeader.X_CA_SIGNATURE.getHeaderName(),
                SignUtil.sign(config.getAppSecret(), method, path, headers, querys, bodys, signHeader));

        return headers;
    }

    /**
     * 初始化URL
     *
     * @param host   请求的API域名
     * @param path   请求的接口
     * @param querys 请求的queryString参数
     * @return URL
     * @throws UnsupportedEncodingException 异常
     */
    private static String initUrl(String host, String path, Map<String, String> querys) throws UnsupportedEncodingException {
        StringBuilder sbUrl = new StringBuilder();
        sbUrl.append(host);
        if (!StringUtils.isBlank(path)) {
            sbUrl.append(path);
        }
        if (null != querys) {
            StringBuilder sbQuery = new StringBuilder();
            for (Map.Entry<String, String> query : querys.entrySet()) {
                if (0 < sbQuery.length()) {
                    sbQuery.append(Constants.SPE3);
                }
                if (StringUtils.isBlank(query.getKey()) && !StringUtils.isBlank(query.getValue())) {
                    sbQuery.append(query.getValue());
                }
                if (!StringUtils.isBlank(query.getKey())) {
                    sbQuery.append(query.getKey());
                    if (!StringUtils.isBlank(query.getValue())) {
                        sbQuery.append(Constants.SPE4);
                        sbQuery.append(URLEncoder.encode(query.getValue(), Constants.ENCODING));
                    }
                }
            }
            if (0 < sbQuery.length()) {
                sbUrl.append(Constants.SPE5).append(sbQuery);
            }
        }

        return sbUrl.toString();
    }
}
