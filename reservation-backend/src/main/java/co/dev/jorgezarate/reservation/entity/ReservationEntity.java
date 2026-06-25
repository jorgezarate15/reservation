package co.dev.jorgezarate.reservation.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * JPA entity representing a reservation in the system.
 */
@Entity
@Table(name = "reservations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservationEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "client_name", nullable = false)
	private String clientName;

	@Column(nullable = false)
	private LocalDate reservationDate;

	@Column(nullable = false)
	private LocalTime reservationTime;

	@Column(nullable = false)
	private String service;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private ReservationStatus status;

    
}
