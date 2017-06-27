package com.sampleimage.upload;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * Created by kevinjanvier on 16/06/2017.
 */
public interface AdvertImpl {

    boolean addAdvert(Advertise advertise, MultipartFile file) throws IOException;  //add advertis

    Advertise getAdvertById(int ad_id);  //get advertise by Id

    Advertise getAdvertByName(String ad_name); //advertise byName

    List<Advertise> getAllAdvert(); //alladvertise


}
