package com.sampleimage.upload;

import com.chapchap.adengine.chapDao.AdvertDao;
import com.chapchap.adengine.core.Utility;
import com.chapchap.adengine.request.AdsRequestModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Created by kevinjanvier on 16/06/2017.
 */
@Qualifier("chap_mysql")
@Repository()
public class AdvertRepository implements AdvertDao {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${server.port}")
    private int port;

//    @Autowired
//    private static Environment environment;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void addAdvert(Advertise advertise, String file, int campId) {
        String sql = "INSERT INTO advert_tbl(ad_id,ad_name,ad_description,ad_start_time,ad_time_created,ad_end_time,ad_duration,ad_image,ad_status,ad_type,ad_portion,ad_background,campaign_id) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";

        String name = advertise.getAdName();
        //Getting the fileName
        Utility.log.info("====File Name====" + file);
        Utility.log.info("===CampIIIIIDD " + campId);

        System.out.println("Null Y0000Y " + advertise.getAdName());
        String description = advertise.getAdDescription();
        final Date startime = advertise.getAdStartime();
        logger.info("---Timestamp Start----  " + advertise.getAdStartime());
        Timestamp timecreated = advertise.getAdtimeCreated(); // server time timecreated
        Timestamp endTime = advertise.getAdendTime();
        Timestamp duration = advertise.getAdDuration();
//        String image = advertise.getAdImage();
        logger.info("---00ImageFile " + file);
        logger.info("---CampID+++ " + campId);

//        System.out.println("Null YY " + advertise.getAdStatus());
//        System.out.println("name " + advertise.getAdName());
//        System.out.println("startime " + advertise.getAdStartime());
//        System.out.println("Status " + advertise.getAdStatus());
        String status = advertise.getAdStatus();
        String ad_type = advertise.getAdType();
        Float ad_portion = advertise.getAdPortion();
        String ad_background = advertise.getAdBackground();
        Integer camp_id = 1;
        System.out.print("Ccmpaign_id " + camp_id);

        //Insert to Mysql using Jdbc , getting the FileName from String
        jdbcTemplate.update(sql, name, description, startime, Utility.getServerDate(), endTime, duration,
                file, status, ad_type, ad_portion, ad_background, 1);

        try {
            ObjectMapper mapper = new ObjectMapper();
            System.out.println("New Advert :::" + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(advertise));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        //logger.info("JDBC Template " + advertise.toString());

        // logger.info("=== File Path Name ===== " + Utility.ServerAddress() +"/"+file);

    }


    @Override
    public Advertise getAdvertById(int ad_id) {
        return null;
    }

    @Override
    public Advertise getAdvertByname(String ad_name) {
        String sql = "SELECT * FROM advert_tbl WHERE ad_name = ?";
        return jdbcTemplate.query(sql,
                new Object[]{ad_name},
                rs -> {
                    if (!rs.next()) return null;
                    Advertise advertise = new Advertise();
                    AdsRequestModel adsRequestModel = new AdsRequestModel();
                    advertise.setAdId(rs.getInt("ad_id"));
                    advertise.setAdName(rs.getString("ad_name"));
                    advertise.setAdDescription(rs.getString("ad_description"));
                    advertise.setAdStartime(rs.getDate("ad_start_time"));
                    advertise.setAdtimeCreated(rs.getTimestamp("ad_time_created"));
                    advertise.setAdendTime(rs.getTimestamp("ad_end_time"));
                    advertise.setAdDuration(rs.getTimestamp("ad_duration"));
                    advertise.setAdImage(rs.getString("ad_image"));
                    advertise.setAdStatus(rs.getString("ad_status"));
                    advertise.setAdType(rs.getString("ad_type"));
                    advertise.setAdPortion(rs.getFloat("ad_portion"));
                    advertise.setAdBackground(rs.getString("ad_background"));
                    adsRequestModel.setCampId(rs.getInt("ads_campaign_id"));
                    return advertise;
                });

    }

    /**
     * Get List of Add
     *
     * @return
     */
    @Override
    public List<Advertise> getAllAdvert() {
        String sql = "SELECT * FROM advert_tbl";
        List<Advertise> getadd = jdbcTemplate.query(sql, new advertRowMapper());
        return getadd;
    }

    @Override
    public boolean AdvertExist(String adname) {

        String sql = "SELECT EXISTS (SELECT ad_name FROM advert_tbl WHERE ad_name = ?)";
        Integer ads = jdbcTemplate.queryForObject(sql, Integer.class, adname);
        logger.info("Ads name Exist " + adname.toString());
        return ads != null && ads > 0;
    }

    @Override
    public boolean adsImageExist(String fileName) {
        String sql = "SELECT EXISTS (SELECT ad_image FROM  advert_tbl WHERE ad_image = ?)";
        Integer adimage = jdbcTemplate.queryForObject(sql, Integer.class, fileName);
        logger.info("Ads name Exist Image " + fileName.toString());
        return adimage != null && adimage > 0;
    }

    public static class advertRowMapper implements RowMapper<Advertise> {

        @Override
        public Advertise mapRow(ResultSet rs, int rowNum) throws SQLException {
            Advertise advertise = new Advertise();
            AdsRequestModel adsRequestModel = new AdsRequestModel();
            advertise.setAdId(rs.getInt("ad_id"));
            advertise.setAdName(rs.getString("ad_name"));
            advertise.setAdDescription(rs.getString("ad_description"));
            advertise.setAdStartime(rs.getDate("ad_start_time"));
            advertise.setAdtimeCreated(rs.getTimestamp("ad_time_created"));
            advertise.setAdendTime(rs.getTimestamp("ad_end_time"));
            advertise.setAdDuration(rs.getTimestamp("ad_duration"));
            advertise.setAdImage(rs.getString("ad_image"));
            advertise.setAdStatus(rs.getString("ad_status"));
            advertise.setAdType(rs.getString("ad_type"));
            advertise.setAdPortion(rs.getFloat("ad_portion"));
            advertise.setAdBackground(rs.getString("ad_background"));
            adsRequestModel.setCampId(rs.getInt("ads_campaign_id"));

            System.out.println("camp==========::: " + adsRequestModel.getCampId());

            return advertise;
        }
    }
}
