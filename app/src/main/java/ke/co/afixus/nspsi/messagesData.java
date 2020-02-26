package ke.co.afixus.nspsi;

public class messagesData {
    public messagesData() {
    }

    private String MessageTopic;
    private  String MessageBody;
    private String MessageDate;

    public String getMessageTopic() {
        return MessageTopic;
    }

    public void setMessageTopic(String messageTopic) {
        MessageTopic = messageTopic;
    }

    public String getMessageBody() {
        return MessageBody;
    }

    public void setMessageBody(String messageBody) {
        MessageBody = messageBody;
    }

    public String getMessageDate() {
        return MessageDate;
    }
    public void setMessageDate(String messageDate) {
        MessageDate = messageDate;
    }

    public messagesData(String messageTopic, String messageBody,String messageDate) {
        MessageTopic = messageTopic;
        MessageBody = messageBody;
        MessageDate = messageDate;
    }
}
