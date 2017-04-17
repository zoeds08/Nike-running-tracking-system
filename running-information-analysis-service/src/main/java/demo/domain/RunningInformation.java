package demo.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.Random;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Entity
@Table(name = "private")
public class RunningInformation {

    public enum HealthWarningLevel{
        LOW, NORMAL, HIGH;
    }

    private final String DBG = "----->";

    @Id
    @GeneratedValue
    private Long id;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "username", column = @Column(name = "userName")),
            @AttributeOverride(name = "address", column = @Column(name = "userAddress"))
    })
    private final UserInfo userInfo;

    private String runningId;

    private double latitude;
    private double longitude;

    private double runningDistance;
    private double totalRunningTime;

    private int heartRate = 0;
    private HealthWarningLevel healthWarningLevel;

    private Date timestamp = new Date();

    public RunningInformation(){
        this.userInfo = null;
    }

    public RunningInformation(String username, String address){

        System.out.println(DBG + "Inside constructor 1");
        System.out.println(DBG + username);
        System.out.println(DBG + address);

        this.userInfo = new UserInfo(username,address);
    }

    //Deserialize json object and create Java object
    @JsonCreator
    public RunningInformation(
            @JsonProperty("runningId") String runningId,
            @JsonProperty("latitude") String latitude,
            @JsonProperty("longitude") String longitude,
            @JsonProperty("runningDistance") String runningDistance,
            @JsonProperty("totalRunningTime") String totalRunningTime,
            @JsonProperty("heartRate") String heartRate,
            @JsonProperty("timestamp") String timestamp,
            @JsonProperty("userInfo") UserInfo userInfo){

        this.runningId = runningId;
        this.latitude = Double.parseDouble(latitude);
        this.longitude = Double.parseDouble(longitude);
        this.runningDistance = Double.parseDouble(runningDistance);
        this.totalRunningTime = Double.parseDouble(totalRunningTime);
        this.timestamp = new Date();
        this.userInfo = userInfo;

        this.heartRate = _getRandomHeartRate(60,200);

        if(this.heartRate > 120){
            this.healthWarningLevel = HealthWarningLevel.HIGH;
        }
        else if(this.heartRate > 75){
            this.healthWarningLevel = HealthWarningLevel.NORMAL;
        }
        else if(this.heartRate >= 60){
            this.healthWarningLevel = HealthWarningLevel.LOW;
        }
        else{
            //option 1: DANGER
            //option 2: Intentionally left blank
            //option 3: Exception
            //option 4: Print warning
        }
        System.out.println(DBG + this.heartRate);
    }

    public RunningInformation(UserInfo userInfo){
        this.userInfo = userInfo;
    }

    public String getUserName(){
        return this.userInfo == null ? null:this.userInfo.getUsername();
    }

    public String getUserAddress(){
        return this.userInfo == null ? null:this.userInfo.getAddress();
    }

    private int _getRandomHeartRate(int min, int max){
        Random rd = new Random();
        return min + rd.nextInt(max-min+1);
    }
}
