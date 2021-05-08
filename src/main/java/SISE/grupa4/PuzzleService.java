package SISE.grupa4;

public class PuzzleService {
    private PuzzleService() {
    }

    public static int MAX_DEPTH = 20;

    public static boolean check(Node node) {
        //check if node is a rectangle
        if ((node.getConfiguration().length) % node.getWidth() != 0) return false;
        int tab[] = new int[node.getConfiguration().length - 1];

        for (int i = 0, j = 0; i < node.getConfiguration().length; i++, j++) {
            //check if values are not above what they should be
            if (node.getConfiguration()[i] >= node.getConfiguration().length) return false;

            if (node.getConfiguration()[i] == 0) j--;
            else {
                //check if there is a 0
                if(j == tab.length) return false;
                tab[j] = node.getConfiguration()[i];
            }
        }

        int count = 0;
        for (int i = 0; i < tab.length - 1; i++){
            for (int j = i + 1; j < tab.length; j++){
                if(tab[i] == tab[j]) return false; //check if values are not repeating
                if(tab[i] > tab[j]) count++;
            }
        }


        if(node.getWidth() % 2 == 0){
            count += (node.getConfiguration().length - node.getPosition()) / 4 % 2;
        }

        return count % 2 == 0;
    }

    public static int getPosition(int[] configuration){
        for (int i = 0; i < configuration.length; i++) {
            if (configuration[i] == 0) return i;
        }
        return 0;
    }

    public static Direction[] getDirections(String s){
        Direction[] directions = new Direction[4];
        for (int i = 0; i < 4; i++) {
            switch (s.getBytes()[i]) {
                case 'L':
                    directions[i] = Direction.LEFT;
                    break;
                case 'R':
                    directions[i] = Direction.RIGHT;
                    break;
                case 'U':
                    directions[i] = Direction.UP;
                    break;
                case 'D':
                    directions[i] = Direction.DOWN;
            }
        }
        return directions;
    }
}
