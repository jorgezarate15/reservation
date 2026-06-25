package co.dev.jorgezarate.reservation.service;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.dev.jorgezarate.reservation.entity.ReservationEntity;
import co.dev.jorgezarate.reservation.entity.ReservationStatus;
import co.dev.jorgezarate.reservation.exception.ReservationBusinessException;
import co.dev.jorgezarate.reservation.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;

/**
 * Application service for reservation business operations.
 */
@Service
@RequiredArgsConstructor
@Transactional
public class ReservationService {

	private final ReservationRepository reservationRepository;

	/**
	 * Creates a reservation when no other reservation exists for the same date and time.
	 *
	 * @param clientName      name of the client making the reservation
	 * @param reservationDate date of the reservation
	 * @param reservationTime time of the reservation
	 * @param service         requested service
	 * @return the persisted reservation
	 * @throws ReservationBusinessException when a reservation already exists for the date and time
	 */
	public ReservationEntity create(
			String clientName,
			LocalDate reservationDate,
			LocalTime reservationTime,
			String service) {
		if (reservationRepository.existsByReservationDateAndReservationTime(
				reservationDate, reservationTime)) {
			throw new ReservationBusinessException(
					"A reservation already exists for the given date and time");
		}

		var reservation = new ReservationEntity();
		reservation.setClientName(clientName);
		reservation.setReservationDate(reservationDate);
		reservation.setReservationTime(reservationTime);
		reservation.setService(service);
		reservation.setStatus(ReservationStatus.ACTIVE);

		return reservationRepository.save(reservation);
	}

	/**
	 * Cancels an active reservation by its identifier.
	 *
	 * @param id reservation identifier
	 * @return the cancelled reservation
	 * @throws ReservationBusinessException when the reservation does not exist or is already cancelled
	 */
	public ReservationEntity cancel(Long id) {
		var reservation = reservationRepository.findById(id)
				.orElseThrow(() -> new ReservationBusinessException(
						"Reservation not found with id: " + id));

		if (reservation.getStatus() == ReservationStatus.CANCELLED) {
			throw new ReservationBusinessException("Reservation is already cancelled");
		}

		reservation.setStatus(ReservationStatus.CANCELLED);
		return reservationRepository.save(reservation);
	}
}
