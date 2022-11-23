package by.toharrius.quizer.tasks.math_tasks;

import by.toharrius.quizer.Task;
import by.toharrius.quizer.TaskGenerator;

import java.util.EnumSet;
import java.util.concurrent.ThreadLocalRandom;

public interface MathTask extends Task {
    interface Generator extends TaskGenerator {
        double getMinNumber();
        double getMaxNumber();
        double getRoundingCoefficient();
        EnumSet<MathOperation> getAllowed();
        /**
         * @return разница между максимальным и минимальным возможным числом
         */
        default double getDiffNumber() {
            return getMaxNumber() - getMinNumber();
        }
        default double generateOperand() {
            var generated = getMinNumber() + Math.random() * getDiffNumber();
            return Math.round(generated
                    * getRoundingCoefficient())
                    / getRoundingCoefficient();
        }
        default MathOperation generateMathOperation() {
            var r = ThreadLocalRandom.current();
            var stream = getAllowed().stream();
            int index = r.nextInt((int)stream.count());
            return stream.skip(index).findFirst().get();
        }
    }
}
