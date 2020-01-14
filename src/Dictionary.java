import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Dictionary {

    private String dir;
    private static JSONObject dict;
    private JSONArray verbs;
    private JSONArray f_noun;
    private JSONArray m_noun;
    private JSONArray f_adj;
    private JSONArray m_adj;
    private Random random = new Random();

    public Dictionary(String dir){
        this.dir = dir;
        reset();
    }

    public void reset(){
        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader(dir));

            dict = (JSONObject) obj;
            verbs = (JSONArray) dict.get("verbi");
            f_noun = (JSONArray) dict.get("sostantiviFemminili");
            f_adj = (JSONArray) dict.get("aggettiviFemminili");
            m_adj = (JSONArray) dict.get("aggettiviMaschili");
            m_noun = (JSONArray) dict.get("sostantiviMaschili");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean add(int choice, String word){
        switch (choice){
            case 1:
                if(verbs.contains(word)) return true;
                verbs.add(word);
                dict.put("verbi", verbs); break;
            case 2:
                if(f_noun.contains(word)) return true;
                f_noun.add(word);
                dict.put("sostantiviFemminili", f_noun); break;
            case 3:
                if(m_noun.contains(word)) return true;
                m_noun.add(word);
                dict.put("sostantiviMaschili", m_noun); break;
            case 4:
                if(f_adj.contains(word)) return true;
                f_adj.add(word);
                dict.put("aggettiviFemminili", f_adj); break;
            case 5:
                if(m_adj.contains(word)) return true;
                m_adj.add(word);
                dict.put("aggettiviMaschili", m_adj); break;
        }
        return false;
    }

    public boolean save(){
        try (FileWriter file = new FileWriter(dir)) {
            file.write(dict.toJSONString());
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String getVerb(){
        int n = random.nextInt(verbs.size());
        return (String) verbs.get(n);
    }

    public String getFAdj(){
        int n = random.nextInt(f_adj.size());
        return (String) f_adj.get(n);
    }

    public String getMAdj(){
        int n = random.nextInt(m_adj.size());
        return (String) m_adj.get(n);
    }

    public String getFNoun(){
        int n = random.nextInt(f_noun.size());
        return (String) f_noun.get(n);
    }

    public String getMNoun(){
        int n = random.nextInt(m_noun.size());
        return (String) m_noun.get(n);
    }

}
