package io.github.nishadchayanakhawa.taskvault.unittests.api;

import io.github.nishadchayanakhawa.taskvault.TaskVaultApplication;
import io.nishadc.automationtestingframework.testngcustomization.TestFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = TaskVaultApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class TaskVaultApplicationControllerTests extends AbstractTestNGSpringContextTests {
	@Value("${server.port}")
	private int serverPort;

	@Autowired
	private WebApplicationContext context;

	private MockMvc mvc;

	private String url;

	@BeforeClass(alwaysRun = true)
	public void setup() {
		mvc = MockMvcBuilders.webAppContextSetup(context).build();
		url = String.format("http://localhost:%d", serverPort);
	}

	@DataProvider(name = "path-list-provider", parallel = true)
	public Object[][] dpMethod() {
		return new Object[][] { { "/taskCategory" } };
	}
	
	@Test(dataProvider = "path-list-provider", groups = { "api-tests" })
	void homePage_test(String path) throws Exception {
		String testName = String.format("Page loading for %s", path);
		TestFactory.recordTest(testName);

		mvc.perform(get(url + path)).andExpect(status().isOk());
	}
}
