/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Fuentes;

/**
 *
 * @author Rubén
 */

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class ServiceResponse {
    private String _UrlService;
    
    public void setUri(String uri){
        this._UrlService=uri;
    }
        
//    ServiceResponse(String urlService){
//        _UrlService = urlService;
//    }
    
    public String postObject(String targetURL, String requestJSON ){
        HttpURLConnection connection = null;
        InputStream is = null;

        try {
            //Create connection
            URL url = null;
            if (targetURL.isEmpty())
                url = new URL(_UrlService);
            else
                url = new URL(targetURL);
            
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty( "charset", "utf-8");
            if (!requestJSON.isEmpty()) {
                byte[] jsonBody = requestJSON.getBytes();
                connection.setRequestProperty("Content-Length", Integer.toString(jsonBody.length));
            }
            
            connection.setUseCaches(false);

            //Send request
            System.out.println(requestJSON);
            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            wr.writeBytes(requestJSON);
            wr.close();

            //Get Response  

            try {
                is = connection.getInputStream();
            } catch (IOException ioe) {
                if (connection instanceof HttpURLConnection) {
                    HttpURLConnection httpConn = (HttpURLConnection) connection;
                    int statusCode = httpConn.getResponseCode();
                    if (statusCode != 200) {
                        is = httpConn.getErrorStream();
                    }
                }
            }

            BufferedReader rd = new BufferedReader(new InputStreamReader(is));


            StringBuilder response = new StringBuilder(); // or StringBuffer if Java version 5+
            String line;
            while ((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            rd.close();
            return response.toString();
        } catch (Exception e) {

            e.printStackTrace();
            return null;

        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }   
    
    public String getObject(String targetURL){
        HttpURLConnection conn = null;
        
        try {
            //Create connection
            URL url = null;
            if (targetURL.isEmpty())
                url = new URL(_UrlService);
            else
                url = new URL(targetURL);
            
            conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Error : HTTP Error Code : " 
                        + conn.getResponseCode());
            }
            InputStreamReader streamReader = new InputStreamReader(conn.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(streamReader);
            String output;
            String result = "";
            while ((output = bufferedReader.readLine()) != null ) {
                System.out.println(output);
                result += output;
            }
            conn.disconnect();
            
            return result;
        }
        catch (Exception ex){
            ex.getMessage();
            ex.printStackTrace();
            return null;
        }
        finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
    }


}
