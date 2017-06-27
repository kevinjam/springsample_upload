package com.sampleimage.upload;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.jpa.repository.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by kevinjanvier on 20/06/2017.
 */
@Entity
@Table(name = "advert_tbl", catalog = "ad_enginedb")
public class Advertise implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ad_id")
    private int adId;

    @NotNull
    @NotBlank(message = "advert Name can't be empty")
    @Size(min = 2, max = 100)
    @Column(name = "ad_name" , nullable = false)
    private String adName;

    @NotNull
    @NotBlank(message = "advert Description is empty")
    @Column(name = "ad_description")
    private String adDescription;

    @Column(name = "ad_start_time", nullable = false)
    private Date adStartime;

    @Column(name = "ad_time_created")
    private Timestamp adtimeCreated;

    @Column(name = "ad_end_time")
    private Timestamp adendTime;

    @Column(name = "ad_duration")
    private Timestamp adDuration;

    @Column(name = "ad_image", nullable = false)
    private String adImage;

    @Column(name = "ad_status" , length = 255)
    @Enumerated(EnumType.STRING)
    private Status adStatus;

//    @Column(name = "campaign_id")
//    private Integer campaignId;

    @Column(name = "ad_type")
    @Enumerated(EnumType.STRING)
    private Type adType;

    @Column(name = "ad_portion")
    private Float adPortion;

    @Column(name = "ad_background")
    private String adBackground;

    //One Ads can only belongs to one Campaign Id
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "campaign_id")
//    private Campaign campaign;

    public Advertise() {
    }

    //Constructor for the file name
    public Advertise(Advertise advertise, String originalFilename) {
        this.adImage = originalFilename;
        System.out.println("Advertise CCC " + originalFilename);

    }
    public Advertise(String adName, String adDescription, Date adStartime,
                     Timestamp adtimeCreated, Timestamp adendTime, Timestamp adDuration,
                     String adImage, Status adStatus, Type adType,
                     Float adPortion, String adBackground) {
        this.adName = adName;
        this.adDescription = adDescription;
        this.adStartime = adStartime;
        this.adtimeCreated = adtimeCreated;
        this.adendTime = adendTime;
        this.adDuration = adDuration;
        this.adImage = adImage;
        this.adStatus = adStatus;
        this.adType = adType;
        this.adPortion = adPortion;
        this.adBackground = adBackground;

        System.out.println("adDescription: " +adDescription);
        System.out.println("Timestamp adStartime:" + adStartime);
        System.out.println(" --adtimeCreated:" + adtimeCreated);
        System.out.println("Ad---Image " + adImage);
    }


    public int getAdId() {
        return adId;
    }

    public void setAdId(int adId) {
        this.adId = adId;
    }

    public String getAdName() {
        return adName;
    }

    public void setAdName(String adName) {
        this.adName = adName;
    }

    public String getAdDescription() {
        return adDescription;
    }

    public void setAdDescription(String adDescription) {
        this.adDescription = adDescription;
    }

    public Date getAdStartime() {

        System.out.println("getAdStartime " + adStartime);
        return adStartime;
    }

    public void setAdStartime(Date adStartime) {
        this.adStartime = adStartime;
    }

    public Timestamp getAdtimeCreated() {
        return adtimeCreated;
    }

    public void setAdtimeCreated(Timestamp adtimeCreated) {
        this.adtimeCreated = adtimeCreated;
    }

    public Timestamp getAdendTime() {
        return adendTime;
    }

    public void setAdendTime(Timestamp adendTime) {
        this.adendTime = adendTime;
    }

    public Timestamp getAdDuration() {
        return adDuration;
    }

    public void setAdDuration(Timestamp adDuration) {
        this.adDuration = adDuration;
    }

    public String getAdImage() {
        System.out.println("Ad -- getAdImage " + adImage);
        return adImage;
    }

    public void setAdImage(String adImage) {
        this.adImage = adImage;
    }

    public String getAdStatus() {
        System.out.println("zStatus Obj " + adStatus.name());
        return adStatus.name();
    }

    public void setAdStatus(String adStatus) {
        this.adStatus = Status.valueOf(adStatus);
    }


    public String getAdType() {
        System.out.println("zStatus Obj " + adType.name());

        return adType.name();
    }

    public void setAdType(String adType) {
        this.adType = Type.valueOf(adType);
    }

    public Float getAdPortion() {
        return adPortion;
    }

    public void setAdPortion(Float adPortion) {
        this.adPortion = adPortion;
    }

    public String getAdBackground() {
        return adBackground;
    }

    public void setAdBackground(String adBackground) {
        this.adBackground = adBackground;
    }


    @Override
    public String toString() {
        return "Advertise{" +
                "adId=" + adId +
                ", adName='" + adName + '\'' +
                ", adDescription='" + adDescription + '\'' +
                ", adStartime=" + adStartime +
                ", adtimeCreated=" + adtimeCreated +
                ", adendTime=" + adendTime +
                ", adDuration=" + adDuration +
                ", adImage='" + adImage + '\'' +
                ", adStatus=" + adStatus +
                ", adType=" + adType +
                ", adPortion=" + adPortion +
                ", adBackground='" + adBackground + '\'' +
                '}';
    }
}
