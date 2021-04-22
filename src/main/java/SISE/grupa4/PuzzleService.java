package SISE.grupa4;

public class PuzzleService {


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

        System.out.println(count);
        //check if node is solvable
        return count % 2 == 0;
    }


}