package variphy.exception;

public class InvalidInputException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String errorMessage;

	public InvalidInputException(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
