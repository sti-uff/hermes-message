/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uff.sti.hermes.model;

import java.sql.Date;

/**
 *
 * @author dancastellani
 */
public class SendTask {

    public enum status {

        TODO, DOING, DONE;
    }
    private String to;
    private String replyTo;
    private String subject;
    private String content;
    private Date createdAt = new Date(System.currentTimeMillis());
    private status status;

    public SendTask(String to, String replyTo, String subject, String content) {
        this.to = to;
        this.replyTo = replyTo;
        this.subject = subject;
        this.content = content;
    }

    @Override
    public String toString() {
        return "to: " + to + ", replyTo: " + replyTo + ", subject: " + subject + ", content: " + content;
    }

    /**
     * @return the status
     */
    public status getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(status status) {
        this.status = status;
    }

    /**
     * @return the createdAt
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * @return the to
     */
    public String getTo() {
        return to;
    }

    /**
     * @param to the to to set
     */
    public void setTo(String to) {
        this.to = to;
    }

    /**
     * @return the replyTo
     */
    public String getReplyTo() {
        return replyTo;
    }

    /**
     * @param replyTo the replyTo to set
     */
    public void setReplyTo(String replyTo) {
        this.replyTo = replyTo;
    }

    /**
     * @return the subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * @param subject the subject to set
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }
}
