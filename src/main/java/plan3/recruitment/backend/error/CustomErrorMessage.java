package plan3.recruitment.backend.error;

public class CustomErrorMessage {
    public CustomError message;
    public String code;

    public CustomErrorMessage(CustomError message, String code) {
        this.message = message;
        this.code = code;
    }
}
