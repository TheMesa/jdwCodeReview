import java.util.List;

public class AlphabeticalPrinter implements SenatorListPrinter {

    @Override
    public void print(List<Senator> senators) {
        for(int i = 0; i < senators.size(); i++) {
            for (int j = i + 1; j < senators.size(); j++) {
                int compared = senators.get(i).getLast().compareTo(senators.get(j).getLast());
                if (compared == 0) {
                    compared = senators.get(i).getFirst().compareTo(senators.get(j).getFirst());  
                }
                if (compared > 0) {
                    Senator temp = senators.get(i);
                    senators.set(i, senators.get(j));
                    senators.set(j, temp);
                }
                
            }
        }
        for (int i = 0; i < senators.size(); i++) {
            Senator s = senators.get(i);
            System.out.printf("%s %s (%s-%s)\n", s.getFirst(), s.getLast(), s.getParty(), s.getState());
        }
    }

}
