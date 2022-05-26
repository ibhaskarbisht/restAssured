package FreshRestAssured.TestingRA;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.hamcrest.core.IsEqual;
import org.json.simple.JSONObject;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;
import static io.restassured.RestAssured.given;

public class restAssuredPractice {
	
	ResponseSpecification spec = null;
	@BeforeClass
	public void setSpecification() {
		spec = RestAssured.expect();
		spec.contentType(ContentType.JSON);
		spec.statusCode(200);
		spec.statusLine("HTTP/1.1 200 OK");
		reportGenerator.genReport();
	}
	
	@Test
	public void getUsers() {
		
		reportGenerator.test = reportGenerator.report.startTest("getUsers", "Fetch list");		
		
		try
		{
			RestAssured.baseURI = "https://dummy.restapiexample.com/";
			given().contentType(ContentType.JSON).when().get("api/v1/employees").then().body("message", IsEqual.equalTo("Successfully! All Records has been fetched."));
		}
		
		catch(Exception e)
		{
			reportGenerator.test.log(LogStatus.ERROR, "Exception",e.getMessage());
		}
	
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void createUser() {
		
		reportGenerator.test = reportGenerator.report.startTest("createUser","Create User");
		JSONObject params = new JSONObject();
		params.put("name", "Bhaskar");
		params.put("age", "21");
		params.put("salary", "150");
		RestAssured.baseURI = "https://dummy.restapiexample.com/";
		given().header("Content-Type", "application/json").body(params.toJSONString()).post("api/v1/create");
		


	}
	
	@Test
	public void getUser() {
		
		reportGenerator.test = reportGenerator.report.startTest("getUser","Fetch current User");
		RestAssured.baseURI = "https://dummy.restapiexample.com/";
		
		try
		{
			given().when().get("api/v1/employee/5777").then().spec(spec).extract().path("message");
		}
		catch(Exception e)
		{
			reportGenerator.test.log(LogStatus.ERROR, "Exception",e.getMessage());
		}
		

	}
	
	
	@Test
	public void deleteUser() {
		
		reportGenerator.test = reportGenerator.report.startTest("deleteUser","Delete User");
		RestAssured.baseURI = "https://dummy.restapiexample.com/";
		
		try
		{
			given().delete("/api/v1/delete/7").then().assertThat().body("message",IsEqual.equalTo("Successfully! Record has been deleted"));

		}
		catch(Exception e)
		{
			reportGenerator.test.log(LogStatus.ERROR, "Exception",e.getMessage());
		}
	}
	
	@Test
	public void updateUser() {
		
		reportGenerator.test = reportGenerator.report.startTest("updateUser","Update User");
		RestAssured.baseURI = "https://dummy.restapiexample.com/";
		
		try
		{
			given().put("/api/v1/update/7").then().assertThat().body("message", IsEqual.equalTo("Successfully! Record has been updated"));

		}
		catch(Exception e)
		{
			reportGenerator.test.log(LogStatus.ERROR, "Exception",e.getMessage());
		}
		
	}
	
	

	@AfterClass
	public void clearReport()
	{
		reportGenerator.report.flush();
	}
	

}
