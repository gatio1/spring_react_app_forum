package com.example.forum.Representation;

import com.example.forum.Tables.Topic;

public class TopicRepresentation {

    private Long id;
    
    private String topicName;

    private String topicDesc;

    public TopicRepresentation(Topic topic){
        this.setId(topic.getTopicId());
        this.setTopicName(topic.getTopicName());
        this.setTopicDesc(topic.getTopicDesc());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getTopicDesc() {
        return topicDesc;
    }

    public void setTopicDesc(String topicDesc) {
        this.topicDesc = topicDesc;
    }
}
