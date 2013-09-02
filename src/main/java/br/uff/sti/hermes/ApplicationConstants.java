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

    @Value("${mail.default.from}")
    public String EMAIL_FROM;

}
