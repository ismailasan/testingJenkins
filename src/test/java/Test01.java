import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Test01 {
    @Test
    public void apiTest(){
        RequestSpecification spec = new RequestSpecBuilder().setBaseUri("https://restful-booker.herokuapp.com").build();
//set the base url and the path params
        spec.pathParams("first", "booking", "second",3);

//        Send th eGet request and get the response

        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();
//  {
//    "firstname": "Mark",
//    "lastname": "Brown",
//    "totalprice": 640,
//    "depositpaid": false,
//    "bookingdates": {
//        "checkin": "2017-08-07",
//        "checkout": "2020-03-19"
//    },
//    "additionalneeds": "Breakfast"
//}

//        Setting actual data

        Map<String, Object> actualData = response.as(HashMap.class);
        System.out.println(actualData);

        Map<String, Object> bookingDates = (Map)actualData.get("bookingdates");
        System.out.println(bookingDates);

        Assert.assertEquals("Mary", actualData.get("firstname"));
        Assert.assertEquals("Jones", actualData.get("lastname"));
        Assert.assertEquals(882, actualData.get("totalprice"));
        Assert.assertEquals(true, actualData.get("depositpaid"));

        Assert.assertEquals("2016-11-08", bookingDates.get("checkin"));
        Assert.assertEquals("2019-05-02", bookingDates.get("checkout"));

    }

}
