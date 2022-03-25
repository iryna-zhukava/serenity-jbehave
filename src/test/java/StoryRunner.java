import net.serenitybdd.core.di.WebDriverInjectors;
import net.serenitybdd.jbehave.SerenityReporter;
import net.serenitybdd.jbehave.SerenityStories;
import net.thucydides.core.webdriver.DriverConfiguration;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.embedder.Embedder;
import org.jbehave.core.embedder.StoryControls;
import org.jbehave.core.failures.FailingUponPendingStep;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.reporters.FilePrintStreamFactory;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.jbehave.core.steps.ParameterConverters;
import org.jbehave.core.steps.Steps;
import org.reflections.Reflections;
import org.reflections.scanners.Scanner;

import java.nio.charset.StandardCharsets;
import java.util.*;

import static org.jbehave.core.reporters.Format.*;

public class StoryRunner extends SerenityStories {
    private static final String STEPS_PACKAGE = "projects";
    private static final String RESOURCES_PATH = "src/test/resources";
    private static final String STORY_PATH_TEMPLATE = "stories/%s.story";
    private static final String STORY_ENV_VARIABLE = "story";

    public StoryRunner() {
    }

    @Override
    public InjectableStepsFactory stepsFactory() {
        List<Steps> stepFileList = new ArrayList();
        Reflections reflections = new Reflections("steps", new Scanner[0]);
        Set<Class<? extends Steps>> allClasses = reflections.getSubTypesOf(Steps.class);
        allClasses.forEach((aClass) -> {
            try {
                Steps step = (Steps) aClass.newInstance();
                stepFileList.add(step);
            } catch (IllegalAccessException | InstantiationException var4) {
                var4.toString();
            }

        });
        return new InstanceStepsFactory(this.configuration(), stepFileList);
    }

    @Override
    public List<String> storyPaths() {
        String storyToInclude = System.getenv("story");
        String storyPath = String.format("stories/%s.story", storyToInclude);
        return (new StoryFinder()).findPaths("src/test/resources", storyPath, (String) null);
    }

//    @Override
//    public void run() {
//        Embedder embedder = configuredEmbedder();
//        //  embedder.useMetaFilters(getMetaFilters());
//        embedder.embedderControls().doIgnoreFailureInStories(true)
//                .doIgnoreFailureInView(false);
//        try {
//            embedder.runStoriesAsPaths(storyPaths());
//        } finally {
//            embedder.generateCrossReference();
//        }
//    }

}