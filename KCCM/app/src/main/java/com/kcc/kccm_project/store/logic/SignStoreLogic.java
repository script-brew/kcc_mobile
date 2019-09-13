package com.kcc.kccm_project.store.logic;

import android.os.AsyncTask;
import com.kcc.kccm_project.Entity.UserInfo;
import com.kcc.kccm_project.store.SignStore;
import com.kcc.kccm_project.util.RequestUtil;

public class SignStoreLogic implements SignStore
{
    private final String signInUrl = "https://us-central1-mobile-kcc.cloudfunctions.net/signup";
    @Override
    public String create(String userInfo)
    {
        String response = "";
        try
        {
            NetworTask networTask = new NetworTask(signInUrl, userInfo);
            networTask.execute(response);
            Thread.sleep(1000);
        }
        catch ( InterruptedException e )
        {
            e.printStackTrace();
        }

        return response;
    }

    @Override
    public UserInfo retrieve(String schoolNumber)
    {
        return null;
    }

    @Override
    public void update(UserInfo userInfo)
    {
        //TODO
    }

    @Override
    public void delete(String schoolNumber)
    {
        //TODO
    }

    public class NetworTask extends AsyncTask<String, Void, String>
    {
        String url;
        String value;

        public NetworTask(String url, String value)
        {
            this.url = url;
            this.value = value;
        }

        @Override
        protected String doInBackground(String... params)
        {
            RequestUtil requestUtil = new RequestUtil();
            params[0] = requestUtil.request(url, value);

            return params[0];
        }

        @Override
        protected void onPostExecute(String result)
        {
            super.onPostExecute(result);
        }
    }
}
