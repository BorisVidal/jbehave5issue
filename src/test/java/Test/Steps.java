package Test;

import org.jbehave.core.annotations.AfterScenario;
import org.jbehave.core.annotations.AfterStory;
import org.jbehave.core.annotations.BeforeScenario;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

public class Steps {

    @When("short wait")
    public void shortWait() throws InterruptedException {
        Thread.sleep(500);
    }

    @When("slow wait")
    public void slowWait() throws InterruptedException {
        Thread.sleep(4000);
    }

    @Then("something")
    public void something() throws InterruptedException {
        Thread.sleep(500);
    }

    @BeforeScenario
    public void beforeScenarioThing() {
        System.out.println("****** Before Scenario Thing ******");
    }

    @AfterScenario
    public void afterScenarioThing() {
        System.out.println("****** After Scenario Thing ******");
    }

    @AfterStory
    public void afterStoryThing() {
        System.out.println("****** After Story Thing ******");
    }
}
