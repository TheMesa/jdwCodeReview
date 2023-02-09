import java.net.URL;

public class Senator {
    
    private String first;
    private String last;
    private String state;
    private String party;
    private URL photoURL;
    
    public Senator(String first, String last, String state, String party, URL photoURL) {
        this.first = first;
        this.last = last;
        this.state = state;
        this.party = party;
        this.photoURL = photoURL;
    }
    
    public String getFirst() {
        return first;
    }
    
    public String getLast() {
        return last;
    }
    
    public String getState() {
        return state;
    }
    
    public String getParty() {
        return party;
    }
    
    public URL getPhotoURL() {
        return photoURL;
    }
    
    public String getFullName() {
        return first + " " + last;
    }
    
    public String orderableName() {
        return last + ", " + first;
    }
}
