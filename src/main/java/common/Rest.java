package common;

//import com.ontada.qa.shared.utils.config.Configuration;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
//import org.apache.logging.log4j.core.Logger;
import org.testng.Assert;

public final class Rest {
    private static final Logger log = LogManager.getLogger(Rest.class);

    public Rest() {
    }

    private static void setBaseURL(String baseURL) {
        Assert.assertNotNull(baseURL, "baseURL cannot be empty");
        RestAssured.baseURI = baseURL;
    }

    private static void setPort(String port) {
        RestAssured.port = Integer.parseInt(port, 10);
    }

//    private static void setConfig() {
//        RestAssured.config = RestAssuredConfig.config().httpClient(HttpClientConfig.httpClientConfig().setParam("http.socket.timeout", Configuration.common.getHttpSocketTimeoutApi()).setParam("http.connection.timeout", Configuration.common.getHttpConnectionTimeoutApi())).connectionConfig((new ConnectionConfig()).closeIdleConnectionsAfterEachResponseAfter(10L, TimeUnit.SECONDS));
//    }

    private static RequestSpecification authorization(IAuth context) {
        return Objects.isNull(context) ? RestAssured.given().filter(new AllureRestAssured()) : context.auth();
    }

    private static RequestSpecification init(HttpRestContext context) {
        Assert.assertNotNull(context);
//        context.setURI(URLManager.reformatURI(context.getURI()));
        setBaseURL(context.getBaseURL());
        if (Objects.nonNull(context.getPort())) {
            setPort(context.getPort());
        }

//        setConfig();
        RequestSpecification requestSpecification = authorization(context.getAuth());
        requestSpecification.relaxedHTTPSValidation();
        if (!context.getRequestHeader().isEmpty()) {
            requestSpecification.headers(context.getRequestHeader());
        }

        if (!context.getPathParams().isEmpty()) {
            requestSpecification.pathParams(context.getPathParams());
        }

        if (!context.getFormParams().isEmpty()) {
            requestSpecification.formParams(context.getFormParams());
        }

        if (!context.getQueryParams().isEmpty()) {
            requestSpecification.queryParams(context.getQueryParams());
        }

        if (Objects.nonNull(context.getRequestContentType()) && !context.getRequestContentType().equals("")) {
            requestSpecification.contentType(context.getRequestContentType().toString());
        }

        requestSpecification.given().log().all();
        return requestSpecification;
    }

    public static HttpRestContext GET(HttpRestContext context) {
        Response response = null;
        RequestSpecification requestSpecification = init(context);
        if (Objects.isNull(context.getHttpRestMethod()) || context.getHttpRestMethod().equals("")) {
            context.setHttpRestMethod(Method.GET);
        }

        if (Objects.nonNull(context.getURI()) && !context.getURI().equals("")) {
            response = (Response)requestSpecification.get(context.getURI(), new Object[0]);
        } else {
            response = (Response)requestSpecification.get();
        }

        if (Objects.isNull(response)) {
            throw new NullPointerException("Null return value from GET response");
        } else {
            ((ValidatableResponse)response.then()).log().all();
            context.setStatusCode(response.statusCode());
            context.setResponseContentType1(response.getContentType());
            Iterator var3 = response.headers().iterator();

            while(var3.hasNext()) {
                Header header = (Header)var3.next();
                context.getResponseHeaderParams().put(header.getName(), header.getValue());
            }

            context.setResponseBody(response.getBody().asString());
            context.setTimeTakenInSeconds(response.getTime());
            return context;
        }
    }

    public static HttpRestContext POST(HttpRestContext context) {
        Response response = null;
        RequestSpecification requestSpecification = init(context);
        if (Objects.isNull(context.getHttpRestMethod()) || context.getHttpRestMethod().equals("")) {
            context.setHttpRestMethod(Method.POST);
        }

        if (Objects.nonNull(context.getRequestBody()) && !context.getRequestBody().equals("")) {
            requestSpecification.body(context.getRequestBody());
        }

        Iterator var3;
        if (!context.getMultiParts().isEmpty()) {
            var3 = context.getMultiParts().entrySet().iterator();

            while(var3.hasNext()) {
                Map.Entry<String, Object> entry = (Map.Entry)var3.next();
                requestSpecification.multiPart((String)entry.getKey(), entry.getValue());
            }
        }

        if (Objects.nonNull(context.getURI()) && !context.getURI().equals("")) {
            response = (Response)requestSpecification.post(context.getURI(), new Object[0]);
        } else {
            response = (Response)requestSpecification.post();
        }

        if (Objects.isNull(response)) {
            throw new NullPointerException("Null return value from POST response");
        } else {
            ((ValidatableResponse)response.then()).log().all();
            context.setStatusCode(response.statusCode());
            context.setResponseContentType1(response.contentType());
            var3 = response.headers().iterator();

            while(var3.hasNext()) {
                Header header = (Header)var3.next();
                context.getResponseHeaderParams().put(header.getName(), header.getValue());
            }

            context.setResponseBody(response.getBody().asString());
            context.setTimeTakenInSeconds(response.getTime());
            return context;
        }
    }

    public static HttpRestContext PUT(HttpRestContext context) {
        Response response = null;
        RequestSpecification requestSpecification = init(context);
        if (Objects.nonNull(context.getHttpRestMethod()) || context.getHttpRestMethod().equals("")) {
            context.setHttpRestMethod(Method.PUT);
        }

        if (Objects.nonNull(context.getRequestBody()) && !context.getRequestBody().equals("")) {
            requestSpecification.body(context.getRequestBody());
        }

        Iterator var3;
        if (!context.getMultiParts().isEmpty()) {
            var3 = context.getMultiParts().entrySet().iterator();

            while(var3.hasNext()) {
                Map.Entry<String, Object> entry = (Map.Entry)var3.next();
                requestSpecification.multiPart((String)entry.getKey(), entry.getValue());
            }
        }

        if (Objects.nonNull(context.getURI()) && !context.getURI().equals("")) {
            response = (Response)requestSpecification.put(context.getURI(), new Object[0]);
        } else {
            response = (Response)requestSpecification.put();
        }

        if (Objects.isNull(response)) {
            throw new NullPointerException("Null return value from PUT response");
        } else {
            ((ValidatableResponse)response.then()).log().all();
            context.setStatusCode(response.statusCode());
            context.setResponseContentType1(response.contentType());
            var3 = response.headers().iterator();

            while(var3.hasNext()) {
                Header header = (Header)var3.next();
                context.getResponseHeaderParams().put(header.getName(), header.getValue());
            }

            context.setResponseBody(response.getBody().asString());
            context.setTimeTakenInSeconds(response.getTime());
            return context;
        }
    }

    public static HttpRestContext DELETE(HttpRestContext context) {
        Response response = null;
        RequestSpecification requestSpecification = init(context);
        if (Objects.nonNull(context.getHttpRestMethod()) || context.getHttpRestMethod().equals("")) {
            context.setHttpRestMethod(Method.DELETE);
        }

        if (Objects.nonNull(context.getBaseURL()) && !context.getBaseURL().equals("")) {
            response = (Response)requestSpecification.delete(context.getURI(), new Object[0]);
        } else {
            response = (Response)requestSpecification.delete();
        }

        if (Objects.isNull(response)) {
            throw new NullPointerException("Null return value from GET response");
        } else {
            ((ValidatableResponse)response.then()).log().all();
            context.setStatusCode(response.statusCode());
            context.setResponseContentType1(response.getContentType());
            Iterator var3 = response.headers().iterator();

            while(var3.hasNext()) {
                Header header = (Header)var3.next();
                context.getResponseHeaderParams().put(header.getName(), header.getValue());
            }

            context.setResponseBody(response.getBody().asString());
            context.setTimeTakenInSeconds(response.getTime());
            return context;
        }
    }
}
