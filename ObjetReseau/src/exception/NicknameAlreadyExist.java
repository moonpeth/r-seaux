package exception;

public class NicknameAlreadyExist extends Throwable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public String toString(){
		return "The Nickname you are trying to add already exist";
	}
}
