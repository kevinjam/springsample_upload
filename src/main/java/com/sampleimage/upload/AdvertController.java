package com.sampleimage.upload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by kevinjanvier on 16/06/2017.
 */
@RestController
public class AdvertController {


    @Autowired
    private AdvertService advertService;

    /**
     * Upload adds remember to pass file to your json for ads_images
     * the Ads images header should pass empty
     *
     * @param file
     * @param req
     * @param errors
     * @return
     * @throws IOException
     */

    @RequestMapping(value = "/upload_adds", method = RequestMethod.POST)
    private ResponseEntity<?> addvert(@RequestParam("file") MultipartFile file, @Valid Advertise req,Advertise advertise, Errors errors) throws IOException {
       System.out.print("======= START SINGLE UPLOAD IMGAGE SAVE STUFF TO THE FILE ============= @ModelAttribute ");
        /**
         * Give a Response , Validate User field , check if the Advertise name exist and
         * check if the image exist within the Path then give a User the Response
         */
        boolean adsflag = advertService.addAdvert(advertise, file);

        System.out.print("File name " + file.getOriginalFilename());
        ErrorMessage result = new ErrorMessage("400", " Bad Request", "Ads Name Already Exist");
        if (errors.hasErrors()) {
            //If error, just return 400 bad request, along with the message
            result.setMessage(errors.getAllErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining()));
            return ResponseEntity.badRequest().body(result);
            // return ResponseEntity.badRequest().body(errorMessage);
        }
        File fileDirect = new File("upload-dir" + "/" + file.getOriginalFilename());

        if (advertise.getAdName() == null) {

            result.setMessage("There was an error while adding a campaign");
        } else if (advertise.getAdName().isEmpty()) {
            result.setMessage("advert Name can't be empty");
        } else if (advertise.getAdDescription().isEmpty()) {
            result.setMessage("advert description can't be empty");
        } else if (fileDirect.exists()) {
            result.setMessage("Image Name Already Exist ");
        }

        // Check if the Camp Name Already Exist in the Db
        //Before Posting a new  Name
        if (!adsflag) {
            // ErrorMessage errorMessage = new ErrorMessage(Constants.codebad, "Bad Request", "Campaign Name Already Exist");
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        } else {
            //Ad successful
            SuccessMessage message = new SuccessMessage("200", "Successfully");
            advertService.addAdvert(advertise, file);
            return new ResponseEntity<>(message, HttpStatus.OK);
            //log Image
            //TODO: ALLOW Users to check the File Name if exist

        }

    }


}
