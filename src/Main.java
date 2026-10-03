import com.aitu.sdp.AsciiRenderer;
import com.aitu.sdp.Circle;
import com.aitu.sdp.RasterRenderer;
import com.aitu.sdp.Shape;
import com.aitu.sdp.Square;
import com.aitu.sdp.VectorRenderer;

public final class Main {
    public static void main(String[] args) {
        if (args.length != 1 || !"--demo".equals(args[0])) {
            System.out.println("Usage: java -cp out Main --demo");
            return;
        }

        int passed = 0;
        passed += checkResult("T1", new Circle("circle-1", 2, new VectorRenderer()),
                "VectorRenderer", "VECTOR circle radius=2");
        passed += checkResult("T2", new Circle("circle-1", 2, new RasterRenderer()),
                "RasterRenderer", "RASTER circle radius=2");
        passed += checkResult("T3", new Square("square-1", 3, new VectorRenderer()),
                "VectorRenderer", "VECTOR square side=3");
        passed += checkResult("T4", new Square("square-1", 3, new RasterRenderer()),
                "RasterRenderer", "RASTER square side=3");
        passed += checkRuntimeSwitch();
        passed += checkResult("T6", new Circle("circle-1", 2, new AsciiRenderer()),
                "AsciiRenderer", "ASCII circle radius=2");
        passed += checkResult("T7", new Square("square-1", 3, new AsciiRenderer()),
                "AsciiRenderer", "ASCII square side=3");

        int total = 7;
        System.out.println("SUMMARY: " + passed + "/" + total + " PASS");
        if (passed != total) {
            System.exit(1);
        }
    }

    private static int checkResult(String testId, Shape shape, String rendererName, String expected) {
        String actual = shape.execute();
        boolean passed = expected.equals(actual);
        System.out.println(testId + " " + (passed ? "PASS" : "FAIL") + " | "
                + shape.getClass().getSimpleName() + " + " + rendererName + " | result=" + actual);
        if (!passed) {
            System.out.println("  expected=" + expected);
        }
        return passed ? 1 : 0;
    }

    private static int checkRuntimeSwitch() {
        Circle original = new Circle("circle-switch", 2, new VectorRenderer());
        String originalId = original.getId();
        int originalRadius = original.getRadius();
        String before = original.execute();

        original.setImplementation(new RasterRenderer());
        Circle afterSwitch = original;
        String after = afterSwitch.execute();

        boolean sameObject = original == afterSwitch;
        boolean stateUnchanged = originalId.equals(afterSwitch.getId())
                && originalRadius == afterSwitch.getRadius();
        boolean passed = sameObject && stateUnchanged
                && "VECTOR circle radius=2".equals(before)
                && "RASTER circle radius=2".equals(after);

        System.out.println("T5 " + (passed ? "PASS" : "FAIL")
                + " | Circle + VectorRenderer -> RasterRenderer | sameObject=" + sameObject
                + " | stateUnchanged=" + stateUnchanged);
        System.out.println("  id=" + originalId + " -> " + afterSwitch.getId()
                + " | radius=" + originalRadius + " -> " + afterSwitch.getRadius());
        System.out.println("  before=" + before + " | after=" + after);
        if (!passed) {
            System.out.println("  expected=sameObject=true | stateUnchanged=true"
                    + " | before=VECTOR circle radius=2 | after=RASTER circle radius=2");
        }
        return passed ? 1 : 0;
    }
}
