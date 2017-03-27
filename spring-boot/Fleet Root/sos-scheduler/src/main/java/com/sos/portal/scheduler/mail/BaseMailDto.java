package com.sos.portal.scheduler.mail;



/**
 * 
 */
public class BaseMailDto {
	/*send from */
	private String mailFromAddress;
	/*send to*/
	private String mailToAddress;
	/*send cc*/
	private String mailCcAddress;
	/*send bcc*/
	private String mailBccAddress;
	/*attachment*/
	private String attachment;
	/*subject template*/
	private String subjectTemplate;
	/*body template*/
	private String bodyTemplate;
	/*language*/
	private String locale;
	/*message*/
	private String mailBody;
	/*mail title*/
	private String mailSubject;
	
	public BaseMailDto(){
	}
	public BaseMailDto(String locale,String mailFromAddress){
		this.locale=locale;
		this.mailFromAddress=mailFromAddress;
	}
	
	public String getMailBccAddress() {
		return mailBccAddress;
	}

	public void setMailBccAddress(String mailBccAddress) {
		this.mailBccAddress = mailBccAddress;
	}

	public String getMailCcAddress() {
		return mailCcAddress;
	}

	public void setMailCcAddress(String mailCcAddress) {
		this.mailCcAddress = mailCcAddress;
	}

	public String getMailToAddress() {
		return mailToAddress;
	}

	public void setMailToAddress(String mailToAddress) {
		this.mailToAddress = mailToAddress;
	}

	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	public String getMailFromAddress() {
		return mailFromAddress;
	}

	public void setMailFromAddress(String mailFromAddress) {
		this.mailFromAddress = mailFromAddress;
	}

	public String getSubjectTemplate() {
		return subjectTemplate;
	}

	public void setSubjectTemplate(String subjectTemplate) {
		this.subjectTemplate = subjectTemplate;
	}

	public String getBodyTemplate() {
		return bodyTemplate;
	}

	public void setBodyTemplate(String bodyTemplate) {
		this.bodyTemplate = bodyTemplate;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}
	public String getMailBody() {
		return mailBody;
	}
	public void setMailBody(String mailBody) {
		this.mailBody = mailBody;
	}
	public String getMailSubject() {
		return mailSubject;
	}
	public void setMailSubject(String mailSubject) {
		this.mailSubject = mailSubject;
	}

}
