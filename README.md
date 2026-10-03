# Assignment 3 | Bridge Pattern

**Student:** Sabyr Aslan | **Group:** SE-2528 | **Topic:** A - Drawing  
**Repository:** https://github.com/asikxz008/Assigment3-sdp-Aslan  
**Base commit:** `91f14595faf97a7e6e1086b431af3445ab93eda2`  
**Submitted source commit:** `807f2ed62e4d7a3943703d7e21214f50b375d3a8`

Shape type and rendering style vary independently. Shapes retain their ID and dimension; a constructor-injected `Renderer` handles rendering. Only JDK classes are used.

| Role | Class | Source path |
|---|---|---|
| Abstraction | Shape | src/com/aitu/sdp/Shape.java |
| A1 | Circle | src/com/aitu/sdp/Circle.java |
| A2 | Square | src/com/aitu/sdp/Square.java |
| Implementor | Renderer | src/com/aitu/sdp/Renderer.java |
| I1 | VectorRenderer | src/com/aitu/sdp/VectorRenderer.java |
| I2 | RasterRenderer | src/com/aitu/sdp/RasterRenderer.java |
| I3 | AsciiRenderer | src/com/aitu/sdp/AsciiRenderer.java |
| Client | Main | src/Main.java |

Bridge field: `Shape.renderer` (private, interface-typed). `Shape.execute()` is implemented in `Circle.execute()` and `Square.execute()`. `Shape.setImplementation(Renderer)` replaces the renderer. `Main.checkRuntimeSwitch()` implements T5 using `==`, ID/radius comparisons, and both actual results.

## Build and run

Use JDK 17 or later from the project folder:

```sh
javac --release 17 -encoding UTF-8 -d out "@sources.txt"
java -cp out Main --demo
```

## Expected checks

Each check compares real output with its expected value. Failures print the expected result; the summary is computed, and a failed demo exits with status 1.

| Check | Expected result |
|---|---|
| T1 | `VECTOR circle radius=2` |
| T2 | `RASTER circle radius=2` |
| T3 | `VECTOR square side=3` |
| T4 | `RASTER square side=3` |
| T5 | Same Circle (`==`); ID `circle-switch` and radius `2` unchanged; `VECTOR circle radius=2` before, `RASTER circle radius=2` after |
| T6 | `ASCII circle radius=2` |
| T7 | `ASCII square side=3` |

Expected summary: `SUMMARY: 7/7 PASS`. Actual output is in `demo-output.txt`.

The base commit passes T1-T5. The source extension adds only `AsciiRenderer.java` and changes `Main.java`; existing hierarchies remain unchanged. `extension.diff` records `git diff 91f14595faf97a7e6e1086b431af3445ab93eda2 HEAD -- src`. See `report.pdf` for UML and design evidence.
