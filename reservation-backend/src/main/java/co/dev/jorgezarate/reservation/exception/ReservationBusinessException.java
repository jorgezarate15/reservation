package co.dev.jorgezarate.reservation.exception;

/**
 * Thrown when a reservation operation violates a business rule.
 */
public class ReservationBusinessException extends RuntimeException {

	/**
	 * Creates an exception with the given detail message.
	 *
	 * @param message description of the violated business rule
	 */
	public ReservationBusinessException(String message) {
		super(message);
	}
}
