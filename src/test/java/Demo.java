/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import aliyun.api.AliYunApiService;
import aliyun.api.config.AliYunApiConfig;
import aliyun.api.config.Constants;
import aliyun.api.config.HttpHeader;
import aliyun.api.core.AliYunApiServiceImpl;
import aliyun.api.enums.ContentType;
import aliyun.api.request.BaseRequest;
import aliyun.api.request.ByteRequest;
import aliyun.api.request.FormRequest;
import aliyun.api.request.StringRequest;
import aliyun.api.response.ApiResponse;
import aliyun.api.utils.DigestUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 调用示例
 * 请替换APP_KEY,APP_SECRET,HOST为真实配置
 */
public class Demo {
    private AliYunApiService apiService;

    /*
     * 在Spring中可以把AliYunApiService申明为Bean
     * 在properties中可以配置AliYunApiConfig中的参数
     * 在需要使用的地方可以直接使用AliYunApiService这个Bean中的方法
     *
     */
    @Before
    public void initServer() throws Exception {
        AliYunApiConfig config = new AliYunApiConfig();

        // 必填配置
        config.setAppKey("123456");//api的appKey，更换为真实的appKey
        config.setAppSecret("654321");//api的secret，更换为真实的appSecret
        config.setHost("http://gb.api.shianxin.net");// 阿里云的API网关域名，更换为真实的阿里云API网关域名

        // 可选配置
        config.setConnectionTimeOut(5);//最长等待连接时间，单位:秒，默认为10秒
        config.setWriteTimeOut(5);//最长写入时间，单位:秒，默认为10秒
        config.setReadTimeOut(10);//最长读取时间，单位:秒，默认为20秒
        // config.setLevel("debug");// 调试级别，默认为不开启调试

        this.apiService = new AliYunApiServiceImpl(config); // 使用apiService必须设置config
    }

    /**
     * HTTP GET
     *
     * @throws Exception 异常
     */
    @Test
    public void get() throws Exception {
        //请求path
        String path = "/getUrl";
        Map<String, String> headers = new HashMap<>();
        //（必填）根据期望的Response内容类型设置
        headers.put(HttpHeader.HTTP_HEADER_ACCEPT, ContentType.CONTENT_TYPE_JSON.getContentName());

        // 自定义header 根据实际情况填写 选填
        headers.put("region", "110000");

        // 参与签名计算的自定义header
        List<String> signHeader = new ArrayList<>();
        signHeader.add("region");

        //请求的query  选填
        Map<String, String> querys = new HashMap<>();
        querys.put("version", "2");

        // 请求体
        BaseRequest request = new BaseRequest(path, headers, signHeader, querys);

        //调用服务端
        ApiResponse response = apiService.sendGet(request);
        //打印响应信息
        printResponse(response);
    }

    /**
     * HTTP POST 表单
     *
     * @throws Exception 异常
     */
    @Test
    public void postForm() throws Exception {
        //请求path
        String path = "/postform";

        //请求的Form bodys
        Map<String, String>bodys = new HashMap<>();
        bodys.put("formName1", "name1Value");
        bodys.put("formName2", "name2Value");

        Map<String, String>headers = new HashMap<>();
        //（必填）根据期望的Response内容类型设置
        headers.put(HttpHeader.HTTP_HEADER_ACCEPT, ContentType.CONTENT_TYPE_JSON.getContentName());

        // 自定义header 根据实际情况填写 选填
        headers.put("signHeader1", "header1Value");
        headers.put("signHeader2", "header2Value");

        // 参与签名计算的自定义header
        List<String> signHeaders = new ArrayList<>();
        signHeaders.add("signHeader1");
        signHeaders.add("signHeader2");

        //请求的querys  根据实际情况填写 选填
        Map<String, String>querys = new HashMap<>();
        querys.put("queryParam1", "param1Value");
        querys.put("queryParam2", "param2Value");

        // 请求体
        FormRequest request = new FormRequest(path, headers, signHeaders, querys, bodys);

        //调用服务端
        ApiResponse response = apiService.sendPostForm(request);
        //打印响应信息
        printResponse(response);
    }

    /**
     * HTTP POST 字符串
     *
     * @throws Exception 异常
     */
    @Test
    public void postString() throws Exception {
        //请求path
        String path = "/postString";
        //Body内容
        JSONArray array = new JSONArray();
        array.add("123");
        array.add("456");
        array.add("789");
        array.add("012");
        String bodys = array.toJSONString();
        Map<String, String>headers = new HashMap<>();
        //（必填）根据期望的Response内容类型设置
        headers.put(HttpHeader.HTTP_HEADER_ACCEPT, ContentType.CONTENT_TYPE_JSON.getContentName());
        //（必填）（POST和PUT请求此项必选，POST FORM不用填写）请求Body内容格式
        headers.put(HttpHeader.HTTP_HEADER_CONTENT_TYPE, ContentType.CONTENT_TYPE_JSON.getContentName());
        //（可选）Body MD5,服务端会校验Body内容是否被篡改,建议Body非Form表单时添加此Header
        headers.put(HttpHeader.HTTP_HEADER_CONTENT_MD5, DigestUtil.base64AndMD5(bodys));

        // 自定义header 根据实际情况填写 选填
        //headers.put("Authorization", "sdfee");

        // 参与签名计算的自定义header
        List<String> signHeaders = new ArrayList<>();
        //signHeaders.add("Authorization");

        //请求的query  选填
        Map<String, String> querys = new HashMap<>();
        /*querys.put("queryParam1", "param1Value");
        querys.put("queryParam2", "param2Value");*/

        // 请求体
        StringRequest request = new StringRequest(path, headers, signHeaders, querys, bodys);

        //调用服务端
        ApiResponse response = apiService.sendPostString(request);
        //打印响应信息
        printResponse(response);
    }

    /**
     * HTTP POST 字节数组
     *
     * @throws Exception 异常
     */
    @Test
    public void postBytes() throws Exception {
        //请求path
        String path = "/poststream";
        //Body内容
        byte[] bodys = "demo bytes body content".getBytes(Constants.ENCODING);

        Map<String, String>headers = new HashMap<>();
        //（必填）根据期望的Response内容类型设置
        headers.put(HttpHeader.HTTP_HEADER_ACCEPT, ContentType.CONTENT_TYPE_JSON.getContentName());
        //（必填）（POST和PUT请求此项必选，POST FORM不用填写）请求Body内容格式
        headers.put(HttpHeader.HTTP_HEADER_CONTENT_TYPE, ContentType.CONTENT_TYPE_TEXT.getContentName());
        //（可选）Body MD5,服务端会校验Body内容是否被篡改,建议Body非Form表单时添加此Header
        headers.put(HttpHeader.HTTP_HEADER_CONTENT_MD5, DigestUtil.base64AndMD5(bodys));

        // 自定义header 根据实际情况填写 选填
        headers.put("signHeader1", "header1Value");
        headers.put("signHeader2", "header2Value");

        // 参与签名计算的自定义header
        List<String> signHeaders = new ArrayList<>();
        signHeaders.add("signHeader1");
        signHeaders.add("signHeader2");

        //请求的query  选填
        Map<String, String> querys = new HashMap<>();
        querys.put("queryParam1", "param1Value");
        querys.put("queryParam2", "param2Value");

        // 请求体
        ByteRequest request = new ByteRequest(path, headers, signHeaders, querys, bodys);

        //调用服务端
        ApiResponse response = apiService.sendPostBytes(request);
        //打印响应信息
        printResponse(response);
    }

    /**
     * HTTP PUT 字符串
     *
     * @throws Exception 异常
     */
    @Test
    public void putString() throws Exception {
        //请求path
        String path = "/putstring";
        //Body内容
        String body = "demo string body content";

        Map<String, String>headers = new HashMap<>();
        //（必填）根据期望的Response内容类型设置
        headers.put(HttpHeader.HTTP_HEADER_ACCEPT, ContentType.CONTENT_TYPE_JSON.getContentName());
        //（必填）（POST和PUT请求此项必选，POST FORM不用填写）请求Body内容格式
        headers.put(HttpHeader.HTTP_HEADER_CONTENT_TYPE, ContentType.CONTENT_TYPE_TEXT.getContentName());
        //（可选）Body MD5,服务端会校验Body内容是否被篡改,建议Body非Form表单时添加此Header
        headers.put(HttpHeader.HTTP_HEADER_CONTENT_MD5, DigestUtil.base64AndMD5(body));

        // 自定义header 根据实际情况填写 选填
        headers.put("signHeader1", "header1Value");
        headers.put("signHeader2", "header2Value");

        // 参与签名计算的自定义header
        List<String> signHeaders = new ArrayList<>();
        signHeaders.add("signHeader1");
        signHeaders.add("signHeader2");

        // 请求体
        StringRequest request = new StringRequest(path, headers, signHeaders, null, body);

        //调用服务端
        ApiResponse response = apiService.sendPutString(request);
        //打印响应信息
        printResponse(response);
    }

    /**
     * HTTP PUT 字节数组
     *
     * @throws Exception 异常
     */
    @Test
    public void putBytesBody() throws Exception {
        //请求path
        String path = "/putstream";
        //Body内容
        byte[] body = "demo bytes body content".getBytes(Constants.ENCODING);

        Map<String, String>headers = new HashMap<>();
        //（必填）根据期望的Response内容类型设置
        headers.put(HttpHeader.HTTP_HEADER_ACCEPT, ContentType.CONTENT_TYPE_JSON.getContentName());
        //（必填）（POST和PUT请求此项必选，POST FORM不用填写）请求Body内容格式
        headers.put(HttpHeader.HTTP_HEADER_CONTENT_TYPE, ContentType.CONTENT_TYPE_TEXT.getContentName());
        //（可选）Body MD5,服务端会校验Body内容是否被篡改,建议Body非Form表单时添加此Header
        headers.put(HttpHeader.HTTP_HEADER_CONTENT_MD5, DigestUtil.base64AndMD5(body));

        // 自定义header 根据实际情况填写 选填
        headers.put("signHeader1", "header1Value");
        headers.put("signHeader2", "header2Value");

        // 参与签名计算的自定义header
        List<String> signHeaders = new ArrayList<>();
        signHeaders.add("signHeader1");
        signHeaders.add("signHeader2");

        // 请求体
        ByteRequest request = new ByteRequest(path, headers, signHeaders, null, body);

        //调用服务端
        ApiResponse response = apiService.sendPutBytes(request);
        //打印响应信息
        printResponse(response);
    }

    /**
     * HTTP DELETE
     *
     * @throws Exception 异常
     */
    @Test
    public void delete() throws Exception {
        //请求path
        String path = "/delete";

        Map<String, String>headers = new HashMap<>();
        //（必填）根据期望的Response内容类型设置
        headers.put(HttpHeader.HTTP_HEADER_ACCEPT, ContentType.CONTENT_TYPE_JSON.getContentName());

        // 请求体
        BaseRequest request = new BaseRequest(path, headers, null, null);

        //调用服务端
        ApiResponse response = apiService.sendDelete(request);
        //打印响应信息
        printResponse(response);
    }

    /**
     * 打印信息
     *
     * @param response 响应体
     */
    private void printResponse(ApiResponse response) {
        // 是否请求成功的重要标识
        System.out.println("isSuccess：" + response.isSuccess());
        // 响应的状态码，此状态码为HTTP的响应状态码
        System.out.println("code:" + response.getCode());
        // 响应成功或失败返回的状态消息
        System.out.println("message:" + response.getMessage());
        // 响应的头(header)信息
        System.out.println("headers:" + JSON.toJSONString(response.getHeaders()));
        // 响应返回消息，注意：body中的内容是字符串，字符串内容可以为文本、json或xml
        System.out.println("body:" + response.getBody());
        // 响应的流，注意：如果响应的是oct-stream，比如文件类型的，文件的流在此对象中
        System.out.println("byteStream:" + response.getByteStream());
    }
}
