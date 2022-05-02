package Protocol;

import Message.Message;

public class Protocol {
    private int state = 0;
    private int nbOfmessages = -1;

    public String accessMessages(Message message){
        nbOfmessages--;
        if(nbOfmessages == 0){ state = 3; }
        return "Message ID:"+message.getNumber()+" "+message.getContent();
    }

    public String accessInput(String input){
        String output = null;

        if(state == 0) {
            output = "Ready";
            state = 1;
        }else if (state == 1){
            output = "Ready for messages";
            nbOfmessages = Integer.parseInt(input);
            state = 2;
        }else if( state == 3 ){
            output = "Finished";
        }else{
            output = "Error Server";
        }
        return output;
    }
}
