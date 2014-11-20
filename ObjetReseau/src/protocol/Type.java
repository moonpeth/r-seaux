package protocol;

public enum Type {
	ADD, ADDS, GET, OK, EXIT, EXCEPTION;
	static Type getType(String s){
		switch(s){
		case ("add") :return ADD;
		case ("adds") :return ADDS;
		case ("get") :return GET;
		case ("ok") :return OK;
		case ("exit") :return EXIT;
		default: return EXCEPTION;
		}
		
	}
}
