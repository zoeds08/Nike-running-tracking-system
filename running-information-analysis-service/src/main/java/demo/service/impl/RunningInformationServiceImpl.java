package demo.service.impl;

import demo.domain.RunningInformation;
import demo.domain.RunningInformationRepository;
import demo.service.RunningInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RunningInformationServiceImpl implements RunningInformationService {

    private final String DBG = "----->";

    private RunningInformationRepository runningInformationRepository;

    @Autowired
    public RunningInformationServiceImpl(RunningInformationRepository runningInformationRepository){
        System.out.println(DBG + "Inside constructor of RunningInformationServiceImpl");
        this.runningInformationRepository = runningInformationRepository;
    }

    @Override
    public List<RunningInformation> saveRunningInformation(List<RunningInformation> runningInformationList) {
        return runningInformationRepository.save(runningInformationList);
    }

    @Override
    public Page<RunningInformation> findByHeartRate(int heartRate, Pageable pageable) {
        System.out.println(DBG + "Inside findByHeartRate method with HR " + String.valueOf(heartRate));
        return runningInformationRepository.findByHeartRate(heartRate,pageable);
    }

    @Override
    public Page<RunningInformation> findByHeartRateGreaterThan(int heartRate, Pageable pageable) {
        return runningInformationRepository.findByHeartRateGreaterThan(heartRate, pageable);
    }

    @Override
    public Page<RunningInformation> findAllRunningInformationOrderByHealthLevel(Pageable pageable) {
        return runningInformationRepository.findAllRunningInformationOrderByHealthLevel(pageable);
    }

    @Override
    public void deleteAll() {
        runningInformationRepository.deleteAll();
    }

    @Override
    public void delete(String runningId) {
        runningInformationRepository.delete(runningId);
    }

}
