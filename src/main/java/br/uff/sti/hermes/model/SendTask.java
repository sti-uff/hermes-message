/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uff.sti.hermes.model;

/**
 *
 * @author dancastellani
 */
public class SendTask {

    public enum Status {

        TODO, DOING, DONE, LOAD_ERROR;

        public static SendTask.Status get(String status) {
            if (SendTask.Status.TODO.toString().equals(status)) {
                return SendTask.Status.TODO;
            } else if (SendTask.Status.DOING.toString().equals(status)) {
                return SendTask.Status.DOING;
            } else if (SendTask.Status.DONE.toString().equals(status)) {
                return SendTask.Status.DONE;
            }
            return SendTask.Status.LOAD_ERROR;
        }
    }
    private Integer id;
    private String sendTo;
    private String replyTo;
    private String subject;
    private String content;
    private Status status = Status.TODO;

    public SendTask() {
    }

    public SendTask(Integer id, String sendTo, String replyTo, String subject, String content, Status status) {
        this.id = id;
        this.sendTo = sendTo;
        this.replyTo = replyTo;
        this.subject = subject;
        this.content = content;
        this.status = status;
    }

    public SendTask(String to, String replyTo, String subject, String content) {
        this.sendTo = to;
        this.replyTo = replyTo;
        this.subject = subject;
        this.content = content;
    }

    /**
     * @return the sendTo
     */
    public String getSendTo() {
        return sendTo;
    }

    /**
     * @param sendTo the sendTo to set
     */
    public void setSendTo(String sendTo) {
        this.sendTo = sendTo;
    }

    @Override
    public int hashCode() {
        return getSendTo().hashCode() + subject.hashCode() * 2 + content.hashCode() * 3;
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "to: " + getSendTo() + ", replyTo: " + replyTo + ", subject: " + subject + ", content: " + content;
    }

    /**
     * @return the status
     */
    public Status getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(Status status) {
        this.status = status;
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
