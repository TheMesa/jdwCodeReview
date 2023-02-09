import java.util.List;

public class ByStatePrinter implements SenatorListPrinter {

    @Override
    public void print(List<Senator> senators) {
        for (int i = 0; i < senators.size(); i++) {
            for (int j = i + 1; j < senators.size(); j++) {
                int compared = senators.get(i).getState().compareTo(senators.get(j).getState());
                if (compared == 0) {
                    compared = senators.get(i).getLast().compareTo(senators.get(j).getLast()); 
                    if (compared == 0) {
                        compared = senators.get(i).getFirst().compareTo(senators.get(j).getFirst());
                    }
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
            if (i + 1 < senators.size()) {
                if (senators.get(i).getState().compareTo(senators.get(i + 1).getState()) == 0) {
                    Senator t = senators.get(i + 1);
                    System.out.printf("%s:\n   %s (%s)\n   %s (%s)\n", s.getState(), s.getFullName(), s.getParty(), t.getFullName(), t.getParty());
                    i++;
                } else {
                    System.out.printf("%s:\n   %s (%s)\n", s.getState(), s.getFullName(), s.getParty());
                } 
            } else {
                System.out.printf("%s:\n   %s (%s)\n", s.getState(), s.getFullName(), s.getParty());
            }
         
        }
    }

}
