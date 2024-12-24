package common;

import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

public class HttpRestContext {
    private String baseURL;
    private String URI;
    private String port;
    private Method httpRestMethod;
    private Map<String, String> requestHeader = new HashMap<>();
    private Map<String, Object> pathParams = new HashMap<>();
    private Map<String, Object> queryParams = new HashMap<>();
    private Map<String, Object> formParams = new HashMap<>();
    private Map<String, String> responseHeader = new HashMap<>();
    private Map<String, Object> multiParts = new HashMap<>();
    private String requestBody;
    private String responseBody;
    private ContentType requestContentType;
//    private ContentType responseContentType;
    private String responseContentType;
    private int statusCode;
    private long timeTakenInSeconds;
    private IAuth auth;
    private Response response;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public String getBaseURL() {
        return baseURL;
    }

    public void setBaseURL(String baseURL) {
        this.baseURL = baseURL;
    }

    public String getURI() {
        return URI;
    }

    public void setURI(String URI) {
        this.URI = URI;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public Method getHttpRestMethod() {
        return httpRestMethod;
    }

    public void setHttpRestMethod(Method httpRestMethod) {
        this.httpRestMethod = httpRestMethod;
    }

    public Map<String, String> getRequestHeader() {
        return requestHeader;
    }

    public void setRequestHeader(Map<String, String> requestHeader) {
        this.requestHeader = requestHeader;
    }

    public Map<String, Object> getPathParams() {
        return pathParams;
    }

    public void setPathParams(Map<String, Object> pathParams) {
        this.pathParams = pathParams;
    }

    public Map<String, Object> getQueryParams() {
        return queryParams;
    }

    public void setQueryParams(Map<String, Object> queryParams) {
        this.queryParams = queryParams;
    }

    public Map<String, Object> getFormParams() {
        return formParams;
    }

    public void setFormParams(Map<String, Object> formParams) {
        this.formParams = formParams;
    }

    public Map<String, String> getResponseHeader() {
        return responseHeader;
    }

    public void setResponseHeader(Map<String, String> responseHeader) {
        this.responseHeader = responseHeader;
    }

    public Map<String, Object> getMultiParts() {
        return multiParts;
    }

    public void setMultiParts(Map<String, Object> multiParts) {
        this.multiParts = multiParts;
    }

    public String getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(String requestBody) {
        this.requestBody = requestBody;
    }

    public String getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody;
    }

    public ContentType getRequestContentType() {
        return requestContentType;
    }

    public void setRequestContentType(ContentType requestContentType) {
        this.requestContentType = requestContentType;
    }

//    public ContentType getResponseContentType() {
//        return responseContentType;
//    }
//
//    public void setResponseContentType(ContentType responseContentType) {
//        this.responseContentType = responseContentType;
//    }

    public String getResponseContentType() {
        return responseContentType;
    }

    public void setResponseContentType(String responseContentType1) {
        this.responseContentType = responseContentType1;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public long getTimeTakenInSeconds() {
        return timeTakenInSeconds;
    }

    public void setTimeTakenInSeconds(long timeTakenInSeconds) {
        this.timeTakenInSeconds = timeTakenInSeconds;
    }

    public IAuth getAuth() {
        return auth;
    }

    public void setAuth(IAuth auth) {
        this.auth = auth;
    }
}
