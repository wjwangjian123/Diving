package com.example.edive.model;

import com.example.edive.frame.ApiConfig;
import com.example.edive.frame.ICommonModel;
import com.example.edive.frame.ICommonView;
import com.example.edive.frame.NetConfig;
import com.example.edive.frame.NetManager;

import okhttp3.RequestBody;

public class LoginModel implements ICommonModel {
    @Override
    public void getData(ICommonView view, int whichApi, Object[] t) {
        NetManager manager = NetManager.getNetManager();
        switch (whichApi){
            case ApiConfig.PASSWORD_LOGIN:
                String type = (String) t[0];
                String uname = (String) t[1];
                String password = (String) t[2];
                manager.method(manager.getNetService(NetConfig.BASE_URL2).getPasswordLogin(type,uname,password),view,whichApi);
                break;
            case ApiConfig.VER_GETCODE:
                manager.method(manager.getNetService(NetConfig.BASE_URL2).getVerGetcode((String) t[0]),view,whichApi);
                break;
            case ApiConfig.VER_LOGIN:
                String code = (String) t[0];
                String mobile = (String) t[1];

                manager.method(manager.getNetService(NetConfig.BASE_URL2).getVerLogin(code,mobile),view,whichApi);
                break;
            case ApiConfig.REGISTER_DATA:
                String username = (String) t[0];
                String pass = (String) t[1];
                String authCode = (String) t[2];
                String invitationCode = (String) t[3];
                manager.method(manager.getNetService(NetConfig.BASE_URL2).getRegister(username,pass,authCode,invitationCode),view,whichApi);
                break;
            case ApiConfig.FIND_PASSWORD:
                RequestBody requestBodys = (RequestBody) t[0];
                manager.method(manager.getNetService(NetConfig.BASE_URL2).getFindPassword(requestBodys),view,whichApi);
                break;
        }
    }
}
