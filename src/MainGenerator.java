import java.util.Scanner;

public class MainGenerator {

    public static void main(String[] args){

        Generator generator = new Generator();
        Scanner in = new Scanner(System.in);
        boolean exit = false;
        while (!exit){
            System.out.println();
            System.out.println(generator.getMenu());
            System.out.println();
            int choice = 0;
            do {
                choice = in.nextInt();
            }while (choice<1 || choice>4);
            switch (choice){
                case 1:
                    generator.generatePoem(5);
                    break;
                case 2:
                    generator.add();
                    break;
                case 3:
                    generator.isPresent(true);
                    break;
                case 4:
                    exit=true;
                    break;
            }
        }

    }

}
