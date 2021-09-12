package pl.pcz.gumtree.exceptions;

import lombok.Getter;

@Getter
public enum ExceptionReason {

    USER_WITH_MAIL_ACTUALLY_EXISTS ("Uzytkownik z podanym nickiem istnieje");

    private String messageTemplate;

    ExceptionReason(String messageTemplate) {
        this.messageTemplate = messageTemplate;
    }

}
