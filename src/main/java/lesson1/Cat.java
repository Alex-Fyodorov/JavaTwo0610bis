package lesson1;

public class Cat implements Move{
    private String name;
    private int runLength;
    private float jumpHeight;

    public Cat(int runLength, float jumpHeight) {
        this.name = "Кот";
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
