# Mutation Testing Project

A small Java project that shows how **mutation testing** works, using [PIT (pitest)](https://pitest.org/) and JUnit 5.

## What is mutation testing?

Code coverage tells you which lines your tests *run*. Mutation testing tells you whether your tests would actually *catch a bug*.

PIT makes small changes to your compiled code, called **mutants**. For example:

| Original        | Mutant          | Mutator               |
|-----------------|-----------------|-----------------------|
| `a + b`         | `a - b`         | Math                  |
| `score >= 90`   | `score > 90`    | Conditional boundary  |
| `if (b == 0)`   | `if (b != 0)`   | Negate conditional    |
| `return true;`  | `return false;` | Return values         |
| `balance -= x;` | *(removed)*     | Remove/void call      |

Then it runs your tests against each mutant:

- **Killed**: a test failed, so your tests caught the bug.
- **Survived**: every test still passed, so your tests have a gap.
- **No coverage**: no test runs that line at all.

**Mutation score = killed / total mutants.**

## Project structure

```
src/main/java/com/example/mutation/
├── Calculator.java        # arithmetic -> math & negation mutators
├── GradeCalculator.java   # score -> letter grade -> boundary mutators
├── BankAccount.java       # deposits/withdrawals -> conditionals & void calls
└── DiscountService.java   # discount rules -> boundary, math, constants
src/test/java/com/example/mutation/
└── *Test.java             # JUnit 5 tests written to kill mutants
```

## Requirements

- Java 21+
- Maven 3.9+

## How to run

Run the unit tests:

```bash
mvn test
```

Run mutation testing:

```bash
mvn test-compile org.pitest:pitest-maven:mutationCoverage
```

Open the HTML report at `target/pit-reports/index.html`.

The build **fails** if the mutation score drops below 90% (`mutationThreshold` in `pom.xml`).

## Current results

```
Line Coverage: 57/57 (100%)
Generated 82 mutations, Killed 81 (99%)
```

### The one survivor: an equivalent mutant

```java
public int max(int a, int b) {
    return a >= b ? a : b;   // PIT changes this to a > b
}
```

When `a == b`, both branches return the same number, so the mutant behaves exactly like the original code. No test can kill it. This is called an **equivalent mutant**. Mutation scores rarely reach 100% because of mutants like this one.

## Try it yourself: see why weak tests fail

Replace the boundary tests in `GradeCalculatorTest` with this weaker version:

```java
@Test
void weakGradeTest() {
    assertEquals('A', grades.grade(95));
    assertEquals('B', grades.grade(85));
    assertEquals('C', grades.grade(75));
    assertEquals('D', grades.grade(65));
    assertEquals('F', grades.grade(30));
}
```

Line coverage stays at 100%, but run PIT again and the **conditional boundary** mutants (`>= 90` becoming `> 90`) survive. No test checks a score of exactly 90, 80, 70, or 60. The weaker tests cover every line but still miss real bugs, and mutation testing exposes the gap.

## Tips for killing mutants

1. **Test both sides of every boundary** (for example 89/90 and 59/60).
2. **Assert exact values**, not just "not null" or "greater than 0".
3. **Check side effects**: after a failed withdrawal, confirm the balance didn't change.
4. **Test exceptions**, including their messages when they matter.
5. **Use parameterized tests** to cover many inputs cheaply.

## CI

A GitHub Actions workflow (`.github/workflows/mutation-tests.yml`) runs PIT on every push and pull request, and uploads the HTML report as a build artifact.
