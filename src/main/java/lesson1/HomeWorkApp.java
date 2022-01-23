package lesson1;

public class HomeWorkApp {
    public static void main(String[] args) {

        Move[] runner = new Move[3];
        runner[0] = new Man(42000, 1.5f);
        runner[1] = new Cat(50, 1);
        runner[2] = new Robot(3000, 0.5f);

        Object[] obstacle = new Object[4];
        obstacle[0] = new Track(45);
        obstacle[1] = new Wall(0.75f);
        obstacle[2] = new Track(5000);
        obstacle[3] = new Wall(1.2f);

        for (Move move : runner) {
            boolean isRan = false;
            int run;
            float jump;
            String name;

            if (move instanceof Man) {
                run = ((Man) move).getRunLength();
                jump = ((Man) move).getJumpHeight();
                name = ((Man) move).getName();
            } else if (move instanceof Cat) {
                run = ((Cat) move).getRunLength();
                jump = ((Cat) move).getJumpHeight();
                name = ((Cat) move).getName();
            } else {
                run = ((Robot) move).getRunLength();
                jump = ((Robot) move).getJumpHeight();
                name = ((Robot) move).getModel();
            }

            for (Object obs : obstacle) {

                if (obs instanceof Track) {
                    isRan = move.run(run, (Track) obs);
                } else {
                    isRan = move.jump(jump, (Wall) obs);
                }

                if (!isRan) break;
            }

            if (isRan) {
                System.out.println(name + " успешно дошёл до финиша.");
            } else {
                System.out.println(name + " не добежал.");
            }
        }
    }
}
