package framework.steps;

import framework.utils.TestHelper;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {

    @Before
    public void before() {
        TestHelper.openBrowser();
    }


    @After
    public void after() {
        TestHelper.closeBrowser();
    }
}
