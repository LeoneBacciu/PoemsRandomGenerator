import java.util.InputMismatchException;
import java.util.Scanner;

public class MainGenerator {

    private static Scanner in = new Scanner(System.in);

    public static void main(String[] args){

        Generator generator = new Generator();

        boolean exit = false;
        while (!exit){
            System.out.println();
            System.out.println(generator.getMenu());
            System.out.println();
            int choice = 0;
            do {
                try {
                    choice = in.nextInt();
                }catch (InputMismatchException ignored){
                    in.nextLine();
                }
            }while (choice<1 || choice>5);
            switch (choice){
                case 1:
                    generator.generatePoem();
                    break;
                case 2:
                    generator.add();
                    break;
                case 3:
                    generator.isPresent();
                    break;
                case 4:
                    generator.setLines();
                    break;
                case 5:
                    exit=true;
                    break;
            }
        }

    }

}
