import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import tests.GameWorldTests;
import tests.MapEditorTests;
import tests.PersistenceTests;
import tests.RendererTests;
import tests.UserInterfaceTests;

@RunWith(Suite.class)
@Suite.SuiteClasses({ PersistenceTests.class, GameWorldTests.class, MapEditorTests.class,
    RendererTests.class, UserInterfaceTests.class })
public class Tests {

}
