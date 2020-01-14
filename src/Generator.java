import java.util.Random;
import java.util.Scanner;

public class Generator {

    private Dictionary dictionary;
    private Random random = new Random();
    private Scanner in  = new Scanner(System.in);

    public Generator(){
        dictionary = new Dictionary("/home/stark/IdeaProjects/scuola4/src/words.json");
    }

    public String getMenu(){
        return "Vuoi:\n\t1. Generare una poesia\n\t2. Aggiungere una parola\n\t3. Verificare la presenza di una parola\n\t4. Uscire";
    }

    public boolean isPresent(boolean print){
        System.out.println("Inserisci la parola:\t");
        String word = "";
        do{
            word=in.nextLine();
        }while (word.equals(""));
        for (int i = 1; i < 6; i++) {
            if(dictionary.add(i, word)){
                dictionary.reset();
                if(print) System.out.println("La parola è presente");
                return true;
            }
        }
        dictionary.reset();
        if(print) System.out.println("La parola non è presente");
        return false;
    }

    public void add() {
        int choice = 0;
        do {
            System.out.println("Che categoria?\n\t1. Verbi\n\t2. Sostantivi Femminili\n\t3. Sostantivi Maschili\n\t4. Aggettivi Femminili\n\t5. Aggettivi maschili");
            choice = in.nextInt();
        }while (choice<1 || choice>6);
        System.out.print("Inserisci la parola:\t");
        System.out.println();
        String word = "";
        do{
            word=in.nextLine();
        }while (word.equals(""));
        if(dictionary.add(choice, word)) System.out.println("La parola è già presente");
        dictionary.save();
    }

    public void generatePoem(int len){
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
