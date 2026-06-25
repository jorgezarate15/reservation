package co.dev.jorgezarate.reservation.repository;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.data.jpa.repository.JpaRepository;

import co.dev.jorgezarate.reservation.entity.ReservationEntity;

/**
 * Spring Data JPA repository for {@link ReservationEntity} persistence operations.
 */
public interface ReservationRepository extends JpaRepository<ReservationEntity, Long> {

	/**
	 * Checks whether a reservation already exists for the given date and time.
	 *
	 * @param reservationDate the reservation date to match
	 * @param reservationTime the reservation time to match
	 * @return {@code true} if at least one reservation exists for the date and time
	 */
	boolean existsByReservationDateAndReservationTime(LocalDate reservationDate, LocalTime reservationTime);
}
