package pl.pcz.gumtree.exceptions;

public class GumtreeCopyException extends RuntimeException {

    public GumtreeCopyException(ExceptionReason message) {
        super(message.getMessageTemplate());
    }

}
