package lesson1;

public class Man implements Move {
    private String name;
    private int runLength;
    private float jumpHeight;

    public Man(int runLength, float jumpHeight) {
        this.name = "Человек";
        this.runLength = runLength;
        this.jumpHeight = jumpHeight;
    }

    public String getName() {
        return name;
    }

    public int getRunLength() {
        return runLength;
    }

    public float getJumpHeight() {
        return jumpHeight;
    }
}
