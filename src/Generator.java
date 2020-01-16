import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import java.util.prefs.Preferences;

public class Generator {

    private Dictionary dictionary;
    private Random random = new Random();
    private Scanner in  = new Scanner(System.in);
    private static Preferences prefs = Preferences.userRoot();

    public Generator(){
        dictionary = new Dictionary("/home/stark/IdeaProjects/scuola4/src/words.json");
    }

    public String getMenu(){
        return "Vuoi:\n\t1. Generare una poesia\n\t2. Aggiungere una parola\n\t3. Verificare la presenza di una parola\n\t4. Impostare il numero di versi della poesia\n\t5. Uscire";
    }

    public void isPresent(){
        System.out.println("Inserisci la parola:\t");
        String word;
        do{
            word=in.nextLine();
        }while (word.equals(""));
        if(dictionary.contains(word)) System.out.println("La parola è presente");
        else System.out.println("La parola non è presente");
    }

    public void add() {
        int choice = 0;
        System.out.println("Che categoria?\n\t1. Verbi\n\t2. Sostantivi Femminili\n\t3. Sostantivi Maschili\n\t4. Aggettivi Femminili\n\t5. Aggettivi maschili");
        do {
            try {
                choice = in.nextInt();
            }catch (InputMismatchException ignored){
                in.nextLine();
            }
        }while (choice<1 || choice>6);
        System.out.print("Inserisci la parola:\t");
        System.out.println();
        String word;
        do{
            word=in.nextLine();
        }while (word.equals(""));
        if(!dictionary.add(choice, word)) System.out.println("La parola è già presente");
    }

    public void setLines(){
        System.out.println("Quanti versi?");
        int len = 0;
        do{
            try {
                len = in.nextInt();
            }catch (InputMismatchException ignored){
                in.nextLine();
            }
        }while (len<=0);
        prefs.putInt("POEM_LINES", len);
    }

    public void generatePoem(){
        int len = prefs.getInt("POEM_LINES", 5);
        System.out.println(generateTitle().toUpperCase());

        for (int i = 0; i < len; i++) {
            System.out.println(generateRandom());
        }
    }

    public String generate(int type){
        switch (type){
            case 0:
                return getNounAdj(true)+getVerb()+getNounAdj(true);
            case 1:
                return getNounAdj(false)+getVerb()+getNounAdj(true);
            case 2:
                return getNounAdj(false)+getVerb()+getNounAdj(false);
            case 3:
                return getNounAdj(true)+getVerb()+getNounAdj(false);
            default:
                return "error";
        }
    }

    public String generateTitle(){
        return getNounAdj(random.nextBoolean());
    }

    public String generateRandom(){
        return generate(random.nextInt(4));
    }

    private String getNounAdj(boolean noun_first){
        boolean sex = random.nextBoolean();
        if(noun_first){
            if(sex) return dictionary.getMNoun()+" "+dictionary.getMAdj();
            else return dictionary.getFNoun()+" "+dictionary.getFAdj();
        }else {
            if(sex) return dictionary.getMAdj()+" "+dictionary.getMNoun();
            else return dictionary.getFAdj()+" "+dictionary.getFNoun();
        }
    }

    private String getVerb(){
        return " "+dictionary.getVerb()+" ";
    }

}
