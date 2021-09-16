package pl.pcz.gumtree.exceptions;

import lombok.Getter;

@Getter
public enum ExceptionReason {

    USER_WITH_MAIL_ACTUALLY_EXISTS ("Uzytkownik z podanym nickiem istnieje"),
    CONFIRMATION_LINK_IS_INVALID_OR_BROKEN("Confirmation link is invalid or broken");

    private String messageTemplate;

    ExceptionReason(String messageTemplate) {
        this.messageTemplate = messageTemplate;
    }

}
