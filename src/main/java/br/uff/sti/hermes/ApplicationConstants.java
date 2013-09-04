/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uff.sti.hermes;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *
 * @author DanCastellani
 */
@Component
public class ApplicationConstants {
    public static final String MAIL_SEPARATOR = ";";

    @Value("${mail.default.from}")
    public String EMAIL_FROM;
    @Value("${mail.max.recipients}")
    public int MAX_RECEPIENTS_PER_EMAIL;
    /**
     * 99 is Gmail limit for smtp emails.
     */
    public static final int DEFAULT_MAX_RECEPIENTS_PER_EMAIL = 99;
}
