package Test;

import java.nio.charset.Charset;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.embedder.Embedder;
import org.jbehave.core.embedder.StoryControls;
import org.jbehave.core.failures.PassingUponPendingStep;
import org.jbehave.core.i18n.LocalizedKeywords;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.UnderscoredToCapitalized;
import org.jbehave.core.junit.JUnitStory;
import org.jbehave.core.parsers.RegexPrefixCapturingPatternParser;
import org.jbehave.core.parsers.RegexStoryParser;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.FreemarkerProcessor;
import org.jbehave.core.reporters.FreemarkerViewGenerator;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.jbehave.core.steps.MarkUnmatchedStepsAsPending;
import org.jbehave.core.steps.ParameterControls;
import org.jbehave.core.steps.StepFinder;

public class JBehave5Test extends JUnitStory {
    private static Embedder embedder;
    public JBehave5Test() {
        super();
        embedder = configuredEmbedder();
        embedder.embedderControls()
            .useStoryTimeouts("3")
            .doIgnoreFailureInView(true)
            .doIgnoreFailureInStories(true)
            .useThreads(1)
            .doFailOnStoryTimeout(false);
    }

    @Override
    public Configuration configuration() {
        return new MostUsefulConfiguration()
            .useStoryLoader(new LoadFromClasspath(this.getClass()))
            .useStoryControls(new StoryControls()
                .doDryRun(false)
                .doSkipScenariosAfterFailure(false)
                .doResetStateBeforeStory(true))
            .useFailureStrategy(new PassingUponPendingStep())
            .useKeywords(new LocalizedKeywords())
            .useStoryParser(new RegexStoryParser())
            .usePendingStepStrategy(new PassingUponPendingStep())
            .useStepCollector(new MarkUnmatchedStepsAsPending(new StepFinder(new StepFinder
                .ByPriorityField())))
            .useViewGenerator(new FreemarkerViewGenerator(new UnderscoredToCapitalized(), FreemarkerProcessor.class,
                Charset.defaultCharset()))
            .useParameterControls(new ParameterControls()
                .useDelimiterNamedParameters(true))
            .useStepPatternParser(new RegexPrefixCapturingPatternParser())
            .useStoryReporterBuilder(new StoryReporterBuilder()
                .withDefaultFormats()
                .withFormats(Format.CONSOLE, Format.TXT, Format.HTML));
    }

    @Override
    public InjectableStepsFactory stepsFactory() {
        return new InstanceStepsFactory(configuration(), new Steps());
    }
}
