import static org.hamcrest.Matchers.equalTo;
import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.expect;
import static com.jayway.restassured.matcher.RestAssuredMatchers.*;

import org.junit.Before;
import org.junit.Test;

import com.jayway.restassured.RestAssured;

public class RestAssuredTest {
	
	@Before
	public void init(){
		RestAssured.baseURI  = "http://localhost";
		RestAssured.port     = 9898;
		RestAssured.basePath = "/projetoFinal";
	}
	
//	@Test
	public void testAssured(){
		 expect().
		    statusCode(200).
		    body(
		      "email", equalTo("test@hascode.com"),
		      "firstName", equalTo("Tim"),
		      "lastName", equalTo("Testerman"),
		      "id", equalTo("1")).
		    when().
		    get("/service/single-user");
	}

}
