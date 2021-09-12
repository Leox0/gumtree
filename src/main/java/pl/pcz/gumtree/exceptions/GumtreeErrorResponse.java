package pl.pcz.gumtree.exceptions;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GumtreeErrorResponse {

    private String message;

}
