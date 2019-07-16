package project.spring.mongo.service.exception;

public class ObjectNotFound extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public ObjectNotFound(String msg) {
		super(msg);
	}
}
