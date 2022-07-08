package appeared.alaki.events;

public class ChatEvent extends Event{
    private final String text;

    public ChatEvent(String text){
        this.text = text;
    }

    public String getText(){
        return text;
    }
}
