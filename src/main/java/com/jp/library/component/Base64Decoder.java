package com.jp.library.component;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

@Component
public class Base64Decoder  implements Decoder {

    private static final String LOCAL_PATH_TO_STORE_FILES = "./src/main/resources/templates";
    private static final String CSV = "csv";
    @Override
    public String decode(String encodedFile) {

        String fileHeader = encodedFile.substring(0,encodedFile.indexOf("base64,"));
        String extension ="";
        if(fileHeader.contains(CSV)){
            extension = "."+CSV;
        }
        JSONObject jsonObject = new JSONObject(encodedFile);
        String base64 = jsonObject.getString("formData");

        String decodedFile = base64.substring(base64.indexOf("base64,")+7);
        String fileName = String.valueOf(Math.random()) + extension;
        try {
            writeIntoFile(fileName, decodedFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String pathOfFile = LOCAL_PATH_TO_STORE_FILES + File.separator + fileName;
        return pathOfFile;


    }

    private void writeIntoFile(String fileName, String decodedFile) throws IOException {

        try{
            byte[] bytes = Base64.getMimeDecoder().decode(decodedFile);
            File file = new File(LOCAL_PATH_TO_STORE_FILES + File.separator + fileName);

            FileOutputStream fos = new FileOutputStream(file);
            fos.write(bytes);
            fos.flush();
        } catch(Exception e) {
            e.printStackTrace();
        }

    }
}
