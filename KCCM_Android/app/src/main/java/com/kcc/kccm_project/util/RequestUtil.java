package com.kcc.kccm_project.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class RequestUtil
{
    public String request(String url, String value)
    {
        OutputStreamWriter osw;
        BufferedReader bufferedReader;
        StringBuilder sb;
        String response;
        HttpURLConnection connection = null;
        try
        {
            URL requestUrl = new URL(url);
            connection = (HttpURLConnection) requestUrl.openConnection();

            connection.setRequestMethod("POST");
            connection.setRequestProperty("Accept-Charset", "application/json");
            connection.setRequestProperty("Context_Type", "application/json");
            connection.setDoInput(true);
            connection.setDoOutput(true);

            osw = new OutputStreamWriter(connection.getOutputStream());
            osw.write(value);
            osw.flush();
            osw.close();

            if ( connection.getResponseCode() == HttpURLConnection.HTTP_OK )
            {
                bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                sb = new StringBuilder();
                String result;
                while ( (result = bufferedReader.readLine()) != null )
                {
                    sb.append(result);
                }

                response = sb.toString();
                return response;
            }

        } // end try
        catch ( MalformedURLException e )
        {
            e.printStackTrace();
        }
        catch ( IOException e )
        {
            e.printStackTrace();
        }
        finally
        {
            if ( connection != null )
                connection.disconnect();
        }

        return "";
    }
}
