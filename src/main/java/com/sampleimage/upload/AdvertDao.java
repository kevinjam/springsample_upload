package com.sampleimage.upload;

import java.util.List;

/**
 * Created by kevinjanvier on 16/06/2017.
 */
public interface AdvertDao {
    void addAdvert(Advertise advertise, String file);  //add Advert

    Advertise getAdvertById(int ad_id);  //get Advert by Id

    Advertise getAdvertByname(String ad_name); //Advert byName

    List<Advertise> getAllAdvert(); //AllAdvert

    boolean AdvertExist(String campName);

    boolean adsImageExist(String fileName);


}
