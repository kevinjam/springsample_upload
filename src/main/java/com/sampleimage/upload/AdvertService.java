package com.sampleimage.upload;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by kevinjanvier on 16/06/2017.
 */
@Service
public class AdvertService implements AdvertImpl {
    private Logger log = LoggerFactory.getLogger(this.getClass());


    @Autowired
    @Qualifier("chap_mysql")
    private AdvertRepository advertRepository;
    @Autowired
    private ResourceLoader resourceLoader;

    //Add an advertise
    @Override
    public boolean addAdvert(Advertise advertise, MultipartFile file) throws IOException {
        log.info("----ADService---- " + advertRepository);
        log.info("Ad Name " + advertise.getAdName());
        log.info("Debug " + advertise.toString());
        log.info("===== Data Time TTTT " + advertise.getAdStartime());
        log.info(("===== Ad Image " + advertise.getAdImage()));
        log.info(("===== FFIle ----- " + file.getOriginalFilename()));


        if (advertRepository.AdvertExist(advertise.getAdName())) {
            log.info("AdName --- " + advertise.getAdName());
            log.info("Service Data--- submited " + advertise.toString());
            return false;
        } else if (file.isEmpty()) {
            // advertRepository.addAdvert(advertise);
            log.info("File is Empty " + file.getOriginalFilename());
            log.info("File Name Path " + file.getInputStream());

            return false;
        } else {

            File fileDirect = new File("upload-dir" + "/" + file.getOriginalFilename());
            System.out.print("File Dir " + fileDirect);

            if (fileDirect.exists()) {
                System.out.println("File existed");
                //check the file exist from our repository which is our Db
                //we check the file within our directory
                if (advertRepository.adsImageExist(file.getOriginalFilename())) {
                    return false;

                } else {
                    return true;
                }

            } else {
                System.out.println("File not found!");
                Files.copy(file.getInputStream(), Paths.get("upload-dir", file.getOriginalFilename()));
            }

//            System.out.println("Advertise Object:::: " + new Advertise(advertise.getAdName(), advertise.getAdDescription()
//            ,advertise.getAdStartime(), advertise.getAdtimeCreated(), advertise.getAdendTime(), advertise.getAdDuration()
//            ,file.getOriginalFilename(), advertise.getAdStatus(), advertise.getCampaignId(), advertise.getAdType(),
//                    advertise.getAdPortion(), advertise.getAdBackground()));
            log.info("Ad from repos  " + advertise + "File " + file.getOriginalFilename());

            advertRepository.addAdvert(advertise, file.getOriginalFilename());
            System.out.println("campaign }}}}}{{{{ " +file.getOriginalFilename());
            return true;
        }
    }

    @Override
    public Advertise getAdvertById(int ad_id) {
        return null;
    }

    @Override
    public Advertise getAdvertByName(String ad_name) {

        return advertRepository.getAdvertByname(ad_name);
    }

    @Override
    public List<Advertise> getAllAdvert() {
        return advertRepository.getAllAdvert();
    }



}
