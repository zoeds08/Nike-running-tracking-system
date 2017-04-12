package demo.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;


public interface RunningInformationRepository extends JpaRepository<RunningInformation,Long> {
    Page<RunningInformation> findByHeartRateGreaterThan(
            @Param("heartRate") int heartRate,
            Pageable pageable
    );

    Page<RunningInformation> findByHeartRate(
            @Param("heartRate") int heartRate,
            Pageable pageable
    );

    Page<RunningInformation> findAllRunningInformationOrderByHealthLevel(
            Pageable pageable
    );
}