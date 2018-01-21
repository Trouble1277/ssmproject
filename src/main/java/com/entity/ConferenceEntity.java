package com.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class ConferenceEntity {


    private Integer conference_id;

    private String conference_main_topic;

    private Integer fund_Id;

    private Date conference_time;

    private String conference_emcee;

    private String conference_recorder;

    private String conference_document;

    public ConferenceEntity() {
    }

    public ConferenceEntity(Integer conference_id, String conference_main_topic, Integer fund_Id, Date conference_time,
                             String conference_emcee, String conference_recorder, String conference_document) {
        this.conference_id = conference_id;
        this.conference_main_topic = conference_main_topic;
        this.fund_Id = fund_Id;
        this.conference_time = conference_time;
        this.conference_emcee = conference_emcee;
        this.conference_recorder = conference_recorder;
        this.conference_document = conference_document;
    }

    public Integer getConference_id() {
        return conference_id;
    }

    public void setConference_id(Integer conference_id) {
        this.conference_id = conference_id;
    }

    public String getConference_main_topic() {
        return conference_main_topic;
    }

    public void setConference_main_topic(String conference_main_topic) {
        this.conference_main_topic = conference_main_topic;
    }

    public Integer getFund_Id() {
        return fund_Id;
    }

    public void setFund_Id(Integer fund_Id) {
        this.fund_Id = fund_Id;
    }

    public Date getConference_time() {
        return conference_time;
    }

    public void setConference_time(Date conference_time) {
        this.conference_time = conference_time;
    }

    public String getConference_emcee() {
        return conference_emcee;
    }

    public void setConference_emcee(String conference_emcee) {
        this.conference_emcee = conference_emcee;
    }

    public String getConference_recorder() {
        return conference_recorder;
    }

    public void setConference_recorder(String conference_recorder) {
        this.conference_recorder = conference_recorder;
    }

    public String getConference_document() {
        return conference_document;
    }

    public void setConference_document(String conference_document) {
        this.conference_document = conference_document;
    }
}
