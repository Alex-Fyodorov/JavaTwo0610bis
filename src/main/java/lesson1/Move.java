package lesson1;

public interface Move {

    default boolean run (int lengthOfRun, Track t) {
        return t.getLength() <= lengthOfRun;
    }

    default boolean jump (float heightOfJump, Wall w) {
        return w.getHeight() <= heightOfJump;
    }
}
