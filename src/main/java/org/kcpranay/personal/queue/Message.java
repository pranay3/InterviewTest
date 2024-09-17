package org.kcpranay.personal.queue;

public class Message {

    private String key;

    private String content;

    public Message(String key, String content) {
        this.key = key;
        this.content = content;
    }

    @Override
    public String toString() {
        return "{" +
                "\"key\":\"" + key + '\"' +
                ", \"content\":\"" + content + '\"' +
                '}';
    }
}
