package demo.rest;

import demo.domain.RunningInformation;
import demo.service.RunningInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RunningInformationBulkUploadController {

    @Autowired
    private RunningInformationService runningInformationService;

    @RequestMapping(value = "/runningInformation",method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void upload(@RequestBody List<RunningInformation> runningInformationList){
        runningInformationService.saveRunningInformation(runningInformationList);
    }

    @RequestMapping(value = "/purge",method = RequestMethod.DELETE)
    public void purge(){
        runningInformationService.deleteAll();
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id){
        runningInformationService.delete(id);
    }

    @RequestMapping(value = "/runningInformation/{heartRate}", method = RequestMethod.GET)
    public Page<RunningInformation> findByHeartRateGreaterThan(
            @PathVariable int heartRate,
            @RequestParam(name = "page") int page,
            @RequestParam(name = "size") int size){
        return this.runningInformationService.findByHeartRateGreaterThan(heartRate,new PageRequest(page,size));
    }
}
